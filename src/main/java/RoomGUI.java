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

    /**
     *
     * @return
     */
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
            //Creates a series of Jlabels and dialog boxes for createing a room and describing
            //what is in the room or it purpose
            JDialog newRoomDialog = new JDialog(this, "Create Appliance:");
            newRoomDialog.setLayout( new FlowLayout() );
            newRoomDialog.add( new JLabel ("Create a New Appliance:"));
            JTextField roomName = new JTextField("", 20);
            newRoomDialog.add(roomName);
            newRoomDialog.add( new JLabel ("Description:"));
            JTextField description = new JTextField("", 20);
            newRoomDialog.add(description);
            
            //New button 'Save' that will save the Room name and description and 
            //create a new room folder
            JButton okButton = new JButton ("Save");
            okButton.addActionListener(l -> {
                // TODO: Test this
                // TODO: add functionality for dimensions
                new Appliance(roomName.getText(), path, description.getText());
                newRoomDialog.dispose();
                dispose();
                new RoomGUI(path.substring(path.indexOf("\\") + 1).substring(0, path.substring(path.indexOf("\\") + 1).length() - 1));
            });
            
            newRoomDialog.add(okButton);
            newRoomDialog.setSize(400,200);
            newRoomDialog.setVisible(true);
            add(newRoomDialog);
        };
    }
}

    public String getPrevPath() {
        return prevPath;
    }

    public String getPath() {
        return path;
    }
}
