import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *  A class responsible creating a new User panel.
 *  @author Jasper Newkirk
 *  @version 0.0.1
 */
public class UserGUI extends GridTemplateGUI{

    /**
     * the string representation fo the current path
     */
    private String path;

    public UserGUI(String username) {
        super("User\\" + username + "\\");
        this.path = "User\\" + username + "\\";
        setTitle(username); // Override title back to simple name
        initUser();
    }

    /**
     * returns the path of the new user folder
     * @author Xavier Hines
     * @return the path of the new user folder
     */
    public String getPath() {
        return path;
    }

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

    @Override
    protected ActionListener getButtonActionListener(String path) {
        return e -> {
            dispose();

//            System.out.println(path + " before");
            new RoomGUI(path);
        };
    }

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
    @Override
    protected void refresh() {
        dispose();
        new UserGUI(path.substring(path.indexOf("\\") + 1).substring(0, path.substring(path.indexOf("\\") + 1).length() - 1));
    }

    /**
     * Creates the {@code User} folder with a nested folder of the username if login successful.
     * @author Jasper Newkirk
     */
    private void initUser() {
        File user = new File(path);
        try {
            user.mkdirs();
            Files.setAttribute(FileSystems.getDefault().getPath("User"), "dos:hidden", true);
        } catch (IOException e) {
            user = new File(path);
            user.mkdirs();
            path = "." + path;
        }
    }

}
