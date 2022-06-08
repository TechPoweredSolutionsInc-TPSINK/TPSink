import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Jasper Newkirk
 * @author Xavier Hines
 * Date 5/9/22
 */
public class MainGUI extends JFrame implements ActionListener  {

    /**
     * Width of GUI window, based on {@link Main#getLogoUrl()} dimensions.
     */
    private static final int WIDTH = 684;
    /**
     * Height of GUI window, based on {@link Main#getLogoUrl()} dimensions.
     */
    private static final int HEIGHT = 729;
    /**
     * The preferred {@link Font} for this application.
     */
    private static final Font font = new Font("Sans-Serif", Font.PLAIN, 40);

    /**
     * Paints and image onto the main GUI
     */
    private JPanel panel = new JPanel(new BorderLayout()) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                g.drawImage(new ImageIcon(new URL(Main.getLogoUrl()), "Sink").getImage(), 0, 0, null);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * Constructs a new MainGUI, painting image as background and adding
     * Login and About buttons to the main window
     * @author Xavier Hines
     * @author Jasper Newkirk
     */
    public MainGUI(){
        // Init buttonPanel that holds Main buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(0,0,0,0));

        JButton aboutButton = new JButton(About.getWindowName());
        JButton loginButton = new JButton("Login");
        JButton enterSettings = new JButton("New User");
        aboutButton.setFont(font);
        loginButton.setFont(font);
        enterSettings.setFont(font);
        //Action Listeners for aboutButton and loginButton
        aboutButton.addActionListener(e -> new AboutGUI());
        loginButton.addActionListener(e -> {
            try {
                new LoginGUI(this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        enterSettings.addActionListener(e -> new NewUserGUI());

        buttonPanel.add(loginButton);
        buttonPanel.add(enterSettings);
        buttonPanel.add(aboutButton);

        panel.add(buttonPanel, BorderLayout.LINE_START);

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

    /**
     * Returns the {@link MainGUI#font} associated with the {@link MainGUI}.
     * @author Jasper Newkirk
     * @return the {@link MainGUI#font} associated with the {@link MainGUI}.
     */
    public static Font getPreferredFont() {
        return font;
    }
}
