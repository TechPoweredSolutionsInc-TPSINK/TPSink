import javax.swing.*;
import java.awt.*;

/**
 * @author Jasper NewKirk
 * @author Xavier Hines
 * Date 5/9/22
 */
public class AboutGUI extends JFrame {

    /**
     * Panel to set all the about information on
     */
    private final JPanel panel = new JPanel(new BorderLayout());


    /**
     * Constructor that when called creates new about GUI containing
     * authors of application the user it is assigned to and the version.
     * @author Xavier Hines
     * @author Jasper Newkirk
     */
    public AboutGUI() {
        panel.setBorder(BorderFactory.createEmptyBorder(20,30,10,30));
        panel.setLayout(new GridLayout(0, 1));


        JPanel groupPanel = new JPanel();
        // add group headings and names
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));
        for(String heading : About.getHeadings()) {
            groupPanel.add(new JLabel(heading));
        }
        for(String name : About.getMembers()) {
            groupPanel.add(new JLabel("    - " + name));
        }
        panel.add(groupPanel, BorderLayout.NORTH);

        // add version number
        panel.add(new JLabel("Version: " + About.getVersion()));
        // frame related stuff
        add(panel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(About.getWindowName());
        pack();
        setVisible(true);
        setResizable(false);
    }
}
