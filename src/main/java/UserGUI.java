import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *  A class responsible creating a new User panel.
 *  @author Jasper Newkirk
 */
public class UserGUI extends GridTemplateGUI{

    /**
     * The string representation of the current path.
     */
    private String path;

    /**
     * Constructs a new {@link UserGUI} window with a grid of {@link JButton}'s denoting all directories in the current {@code path}.
     * @param username The name of the {@link User}, to be assocaited with the parent directory.
     * @throws HeadlessException when the environment does not support a keyboard, display, or mouse.
     */
    public UserGUI(String username) {
        super("User\\" + username + "\\");
        this.path = "User\\" + username + "\\";
        setTitle(username); // Override title back to simple name
        User.initHiddenDir(path);
    }

    /**
     * Returns an {@link ActionListener} used to go back to the previous screen.
     * @author Jasper Newkirk
     * @return an {@link ActionListener} used to go back to the previous screen.
     */
    @Override
    protected ActionListener getBackButtonActionListener() {
        return e -> {
            dispose();
            try {
                new MainGUI();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
    }

    /**
     * Returns the {@link ActionListener} to be present in each directory-related button in this {@link UserGUI} window.
     * @author Jasper Newkirk
     * @param path the directory associated with the {@link JButton} button.
     * @return the {@link ActionListener} to be present in each directory-related button in this {@link UserGUI} window.
     */
    @Override
    protected ActionListener getButtonActionListener(String path) {
        return e -> {
            dispose();
            new RoomGUI(path);
        };
    }

    /**
     * Returns the {@link ActionListener} to be executed upon pressing the "Add" button in this {@link UserGUI} window.
     * @author Jasper Newkirk
     * @return the {@link ActionListener} to be executed upon pressing the "Add" button in this {@link UserGUI} window.
     */
    @Override
    protected ActionListener getAddButtonActionListener() {
        return e -> {
            // Creates a series of JLabels and dialog boxes for creating a room
            JDialog newRoomDialog = new JDialog(this, "Create Room:");
            newRoomDialog.setLayout( new FlowLayout() );
            newRoomDialog.add( new JLabel ("Create a New Room:"));
            JTextField roomName = new JTextField("", 20);
            newRoomDialog.add(roomName);
            newRoomDialog.add( new JLabel ("Description:"));
            JTextField description = new JTextField("", 20);
            newRoomDialog.add(description);
            JButton okButton = new JButton ("Save");

            // save the room name and description
            okButton.addActionListener(l -> {
                // TODO: Test this
                // TODO: add functionality for or delete the dimensions
                new Room(roomName.getText(), path, description.getText());
                newRoomDialog.dispose();
                refresh();
            });
            newRoomDialog.add(okButton);
            newRoomDialog.setSize(400,200);
            newRoomDialog.setVisible(true);
            try {
                add(newRoomDialog);
            } catch (IllegalArgumentException ignore){}
        };
    }

    /**
     * Refreshes and updates the current {@link UserGUI}.
     * @author Jasper Newkirk
     */
    @Override
    protected void refresh() {
        dispose();
        new UserGUI(path.substring(path.indexOf("\\") + 1).substring(0, path.substring(path.indexOf("\\") + 1).length() - 1));
    }

    /**
     * Returns the current directory.
     * @author Jasper Newkirk
     * @return the current directory.
     */
    public String getPath() {
        return path;
    }
}
