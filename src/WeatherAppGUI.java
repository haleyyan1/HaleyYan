import javax.swing.JFrame;
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

public class WeatherAppGUI implements ActionListener {
    private JTextArea weather;
    private JTextField enterIP;
    private APIWeatherClient ma;
    private ArrayList<String> history;

    public WeatherAppGUI(){
        weather = new JTextArea(30,30);
        enterIP= new JTextField();
        ma = new APIWeatherClient();
        history = new ArrayList<String>();
        setupGui();
    }
    private void setupGui()
    {
        JFrame frame = new JFrame("Weather App for IPs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel welcomeLabel = new JLabel("Weather App");
        welcomeLabel.setFont(new Font("Lucida Bright", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.black);
        JPanel logoWelcomePanel = new JPanel();
        logoWelcomePanel.add(welcomeLabel);
        JPanel movieListPanel = new JPanel();
        weather.setText("Enter an IP address");
        weather.setFont(new Font("Lucida Bright", Font.PLAIN, 16));
        weather.setWrapStyleWord(true);
        weather.setLineWrap(true);
        movieListPanel.add(weather);
        JPanel entryPanel = new JPanel();
        JLabel movieLabel = new JLabel("IP Address: ");
        enterIP = new JTextField(10);
        JButton sendButton = new JButton("Enter");
        JButton resetButton = new JButton("Reset");
        entryPanel.add(movieLabel);
        entryPanel.add(enterIP);
        entryPanel.add(sendButton);
        entryPanel.add(resetButton);
        frame.add(logoWelcomePanel, BorderLayout.NORTH);
        frame.add(movieListPanel, BorderLayout.CENTER);
        frame.add(entryPanel, BorderLayout.SOUTH);
        sendButton.addActionListener(this);
        resetButton.addActionListener(this);
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

    public void actionPerformed(ActionEvent e) {
            JButton button = (JButton)(e.getSource());
            String text = button.getText();
            if (text.equals("Enter")){
                String ipadd = enterIP.getText();
                try {
                    Location loc = new Location(APIWeatherClient.makeAPICall(ipadd));
                    weather.setText(loc.toString());
                    //weather.setText(APIWeatherClient.makeAPICall(ipadd));
                    if (!(APIWeatherClient.makeAPICall(ipadd).equals("{\"error\":{\"code\":1006,\"message\":\"No matching location found.\"}}"))&&!alreadySearched(ipadd)){
                    history.add(ipadd);}
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            else if (text.equals("Reset")){
                if (history.size()!=0){
                weather.setText("Enter an IP address\n\nPreviously searched IPS:\n"+listHistory()); }
                else{
                    weather.setText("Enter an IP address");
                }
            }
    }
}