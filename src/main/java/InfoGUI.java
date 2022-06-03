import javax.swing.*;
import java.io.IOException;

/**
 *  @author Xavier Hines
 *  Date 5/23/22
 *
 *  This class contains GUI to let user enter information such as there name
 *  and email addresss
 *  @version 0.1.0
 */
public class InfoGUI extends JFrame {
    //TODO: dispose of this or adjust it to fit create new user
    //TODO: This is a Band aid right now do not use
    //maybe use as base for add new user
    private final JPanel panel = new JPanel();

    /**
     * Constructs a small GUI with Jlables and text box for
     * user to enter information
     */
    public InfoGUI() {
        setSize(350,200);
        setTitle("User Info");

        panel.setLayout(null);
        // name label
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10,20,80,25);
        panel.add(nameLabel);

        //name Textbox
        JTextField nameText = new JTextField();
        nameText.setBounds(100,20,165,25);
        panel.add(nameText);

        //email Label
        JLabel userEmail = new JLabel("Email");
        userEmail.setBounds(10,50,80,25);
        panel.add(userEmail);

        //email Text
        JTextField emailText = new JTextField();
        emailText.setBounds(100,50,165,25);
        panel.add(emailText);

        //success label
        JLabel success = new JLabel("");
        success.setBounds(120,80,300,25);
        panel.add(success);


        // Login Button
        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(10,80,80,25);
        // Action for writing information to .yml
        enterButton.addActionListener(e ->
                {
                    try {
                        new User().yamlWrite(success, nameText.getText(), emailText.getText());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
        );
        panel.add(enterButton);


        add(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
