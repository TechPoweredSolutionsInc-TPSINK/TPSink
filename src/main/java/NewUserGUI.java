import javax.swing.*;
import java.io.IOException;

/**
 *  Original Author
 *  @author Xavier Hines
 *  Date 5/23/22
 *
 *  @author Jeremy Tirador <a href="tirabyte@uw.edu">tirabyte@uw.edu</a>
 *  Updated 6/5/22
 *
 *  Original InfoGUI is now repurposed to NewUserGUI
 *  @version 0.1.0
 */
public class NewUserGUI extends JFrame {
    //maybe use as base for add new user
    private final JPanel panel = new JPanel();

    /**
     * Constructs a small GUI with Jlables and text box for
     * user to enter information
     */
    public NewUserGUI() {
        setSize(350,200);
        setTitle("Create New User");

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

        //Password Label
        JLabel userPassword = new JLabel("Password");
        userPassword.setBounds(10,80,80,25);
        panel.add(userPassword);

        //Password Text
        JTextField passwordText = new JTextField();
        passwordText.setBounds(100,80,165,25);
        panel.add(passwordText);

        //success label
        JLabel success = new JLabel("");
        success.setBounds(100,110,80,25);
        panel.add(success);


        // Login Button
        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(10,110,80,25);
        // Action for writing information to .yml
        enterButton.addActionListener(e ->
            {
                try {
                    new User().yamlWrite(success, nameText.getText(),
                                         passwordText.getText(),
                                         emailText.getText());
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
