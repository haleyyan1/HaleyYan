import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;
import java.awt.Image;

public class WeatherAppGUI implements ActionListener {

    private JTextArea weather;
    private JTextField enterZIP;
    private ArrayList<String> history;
    private static String unitSystem = "Metric";
    JFrame frame = new JFrame("Weather App for Zip Codes");
    JLabel weatherPic = new JLabel("Weather App");
    JPanel titlePanel = new JPanel();
    JPanel weatherListPanel = new JPanel();
    JButton unit = new JButton(unitSystem);
    JLabel zipLabel = new JLabel("Zip code: ");
    JPanel entryPanel = new JPanel();
    JButton enterButton = new JButton("Enter");
    JButton clearButton = new JButton("Clear");

    public WeatherAppGUI(){
        weather = new JTextArea(20,35);
        enterZIP= new JTextField();
        history = new ArrayList<String>();
        setupGui();
    }

    private void setupGui()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        weatherPic.setFont(new Font("Lucida Bright", Font.BOLD, 20));
        weatherPic.setForeground(Color.black);
        titlePanel.add(weatherPic);
        weather.setText("Enter a zip code");
        weather.setFont(new Font("Lucida Bright", Font.PLAIN, 16));
        weather.setWrapStyleWord(true);
        weather.setLineWrap(true);
        weatherListPanel.add(weather);
        enterZIP = new JTextField(10);
        entryPanel.add(unit);
        entryPanel.add(zipLabel);
        entryPanel.add(enterZIP);
        entryPanel.add(enterButton);
        entryPanel.add(clearButton);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(weatherListPanel, BorderLayout.CENTER);
        frame.add(entryPanel, BorderLayout.SOUTH);
        unit.addActionListener(this);
        enterButton.addActionListener(this);
        clearButton.addActionListener(this);
        frame.pack();
        frame.setVisible(true);
    }

    private String listHistory(){
        String s ="";
        for (int i = 0; i<history.size();i++){
            s+=history.get(i)+"\n";
        }
        return s;
    }

    private boolean alreadySearched(String s){
        boolean b = false;
        for (int i = 0;i<history.size();i++){
            if (s.equals(history.get(i))){
                b=true;
            }
        }
        return b;
    }

    public void setData(){
        String zip = enterZIP.getText();
        try {
            if (!(APIWeatherClient.makeAPICall(zip).equals("{\"error\":{\"code\":1006,\"message\":\"No matching location found.\"}}"))&&!zip.equals(""))
            {
                    Location loc = new Location(APIWeatherClient.makeAPICall(zip), unitSystem);
                    weather.setText("Zip code: "+zip+"\n\n"+loc.toString());
            }
            else
            {
                weather.setText("No zip code entered.");
            }
            if (!alreadySearched(zip)&&!(APIWeatherClient.makeAPICall(zip).equals("{\"error\":{\"code\":1006,\"message\":\"No matching location found.\"}}"))&&!zip.equals("")){
                history.add(zip);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
            JButton button = (JButton)(e.getSource());
            String text = button.getText();
            if (text.equals("Enter")){
                setData();
            }
            else if (text.equals("Clear")){
                if (history.size()!=0){
                    weather.setText("Enter a zip code\n\nPreviously searched zip codes:\n"+listHistory());
                    enterZIP.setText("");
                }
                else {
                    weather.setText("Enter a zip code\n");
                }
            }
            else if (text.equals("Metric")){
                unitSystem="Customary";
                unit.setText("Customary");
                setData();
            }
            else if (text.equals("Customary")){
                unitSystem="Metric";
                unit.setText("Metric");
                setData();
            }
    }
    public static String getUnitSystem(){
        return unitSystem;
    }
}
