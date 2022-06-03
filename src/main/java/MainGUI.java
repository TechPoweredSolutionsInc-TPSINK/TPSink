import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jasper Newkirk
 * @author Xavier Hines
 * Date 5/9/22
 */
public class MainGUI extends JFrame implements ActionListener  {

    /**
     * Width of GUI window, based on {@link Main#getPathToLogo()} dimensions.
     */
    private static final int WIDTH = 684;
    /**
     * Height of GUI window, based on {@link Main#getPathToLogo()} dimensions.
     */
    private static final int HEIGHT = 729;

    /**
     * Paints and image onto the main GUI
     */
    private JPanel panel = new JPanel(new BorderLayout()) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(new ImageIcon(Main.getPathToLogo()).getImage(), 0, 0, null);
        }
    };

    /**
     * Constructs a new MainGUI, painting image as background and adding
     * Login and About buttons to the main window
     */
    public MainGUI(){
        // Init buttonPanel that holds Main buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(0,0,0,0));

        JButton aboutButton = new JButton(About.getWindowName());
        JButton loginButton = new JButton("Login");
        JButton enterSettings = new JButton("User Info");
        //Action Listeners for aboutButton and loginButton
        aboutButton.addActionListener(e -> new AboutGUI());
        loginButton.addActionListener(e -> {
            try {
                new LoginGUI(this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        enterSettings.addActionListener(e -> new InfoGUI());

        buttonPanel.add(aboutButton);
        buttonPanel.add(loginButton);
        buttonPanel.add(enterSettings);

        panel.add(buttonPanel, BorderLayout.LINE_END);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(About.getGroupName());
        add(panel);
        pack();
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new AboutGUI();
    }
}
