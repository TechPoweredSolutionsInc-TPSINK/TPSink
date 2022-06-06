import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A class containing basic functionality for managing {@link Appliance} objects in a {@link User}.
 *  @author Jasper Newkirk
 *  @author Phuoc Le
 *  @version 1.0.0
 */
public class RoomGUI extends GridTemplateGUI{
    /**
     * The path the room containing this {@link Appliance}.
     */
    private final String prevPath;
    /**
     * The current directory.
     */
    private final String path;
    /**
     * Constructs a new {@link RoomGUI} window with a grid of {@link JButton}'s denoting all directories in the current {@code path}.
     * @param path The parent directory.
     * @throws HeadlessException when the environment does not support a keyboard, display, or mouse.
     */
    public RoomGUI(String path) {
        super("User\\" + path + "\\");
        this.path = "User\\" + path + "\\";
        setTitle(path.substring(path.lastIndexOf("\\") + 1)); // Override title back to simple name
        this.prevPath = path.substring(0, path.lastIndexOf("\\"));
//        System.out.println(getPath()+ " after");
//        System.out.println(getPrevPath());
    }

    @Override
    protected void refresh() {
        dispose();
        new RoomGUI(path.substring(path.indexOf("\\") + 1).substring(0, path.substring(path.indexOf("\\") + 1).length() - 1));
    }

    /**
     * returns the folder path of the newly created room
     * @author Xavier Hines
     * @return the folder path of the new room
     */
    public String getPath() {
        return path;
    }

    @Override
    protected ActionListener getBackButtonActionListener() {
        return e -> {
            dispose();
            new UserGUI(prevPath); // is going back to user
        };
    }

    @Override
    protected ActionListener getButtonActionListener(String path) {
        return e -> {
            dispose();
            System.out.println(path);
            new ApplianceGUI(path);
        };
    }

    @Override
    protected ActionListener getAddButtonActionListener() {
        return e -> {
            // Creates a series of JLabels and dialog boxes for creating an appliance
            JDialog newApplianceDialog = new JDialog(this, "Create Appliance:");
            newApplianceDialog.setLayout( new FlowLayout() );
            newApplianceDialog.add( new JLabel ("Create a New Appliance:"));
            JTextField roomName = new JTextField("", 20);
            newApplianceDialog.add(roomName);
            newApplianceDialog.add( new JLabel ("Description:"));
            JTextField description = new JTextField("", 20);
            newApplianceDialog.add(description);
            
            // save the appliance name and description
            JButton okButton = new JButton ("Save");
            okButton.addActionListener(l -> {
                // TODO: Test this
                // TODO: add functionality for dimensions
                new Appliance(roomName.getText(), path, description.getText());
                newApplianceDialog.dispose();
                refresh();
            });
            
            newApplianceDialog.add(okButton);
            newApplianceDialog.setSize(400,200);
            newApplianceDialog.setVisible(true);
            try {
                add(newApplianceDialog);
            } catch (IllegalArgumentException ignore){}
        };
    }

    /**
     * Returns the previous path relative to the current {@link RoomGUI#path}.
     * @author Phuoc Le
     * @return the previous path relative to the current {@link RoomGUI#path}.
     */
    public String getPrevPath() {
        return prevPath;
    }

}
