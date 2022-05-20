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

public class GUI implements ActionListener {
    private JTextArea weather;
    private JTextField enterIP;
    private Client ma;
    public GUI(){
        weather = new JTextArea(5,30);
        enterIP= new JTextField();
        ma = new Client();
        setupGui();
    }
    private void setupGui()
    {
        JFrame frame = new JFrame("Weather App for IPs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel welcomeLabel = new JLabel("Current Weather");
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.black);
        JPanel logoWelcomePanel = new JPanel();
        logoWelcomePanel.add(welcomeLabel);
        JPanel movieListPanel = new JPanel();
        weather.setText("");
        weather.setFont(new Font("Helvetica", Font.PLAIN, 16));
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

    public void actionPerformed(ActionEvent e) {
            JButton button = (JButton)(e.getSource());
            String text = button.getText();
            if (text.equals("Enter")){
                int ipadd = Integer.parseInt(enterIP.getText());
            }
            else if (text.equals("Reset")){
                enterIP.setText("");
            }
    }
}
