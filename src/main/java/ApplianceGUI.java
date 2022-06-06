import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * A class containing basic functionality for managing {@link Appliance} objects in a {@link Appliance}.
 *  @author Jasper Newkirk
 *  @version 1.0.0
 */
public class ApplianceGUI extends GridTemplateGUI{
    /**
     * The path the room containing this {@link Appliance}.
     */
    private final String prevPath;
    /**
     * The current path to this {@link Appliance}.
     */
    private final String path;
    /**
     * Constructs a new {@link ApplianceGUI} window with a grid of {@link JButton}'s denoting all directories in the current {@code path}.
     * @param path The parent directory.
     * @throws HeadlessException when the environment does not support a keyboard, display, or mouse.
     */
    public ApplianceGUI(String path) {
        super("User\\" + path + "\\");
        this.path = "User\\" + path + "\\";
        setTitle(path.substring(path.lastIndexOf("\\") + 1)); // Override title back to simple name
        this.prevPath = path.substring(0, path.lastIndexOf("\\"));
    }

    @Override
    protected void refresh() {
        dispose();
        new ApplianceGUI(path.substring(path.indexOf("\\") + 1).substring(0, path.substring(path.indexOf("\\") + 1).length() - 1));
    }

    /**
     * returns the path of the newly created appliance folder
     * @return path of new appliance folder
     */
    public String getPath() {
        return path;
    }

    @Override
    protected ActionListener getBackButtonActionListener() {
        return e -> {
            dispose();
            new RoomGUI(prevPath); // is going back to user
        };
    }

    @Override
    protected ActionListener getButtonActionListener(String path) {
        return e -> {
            Note.open(path);
        };
    }

    @Override
    protected ActionListener getAddButtonActionListener() {
        return e -> {
            // Creates a series of JLabels and dialog boxes for creating a note
            JDialog newNoteDialog = new JDialog(this, "Create Note:");
            newNoteDialog.setLayout( new FlowLayout() );
            newNoteDialog.add( new JLabel ("Create a New Note:"));
            JTextField fileName = new JTextField("", 20);
            newNoteDialog.add(fileName);
            JButton okButton = new JButton ("Save");

            // save the note
            okButton.addActionListener(l -> {
                new Note(fileName.getText(), path);
                newNoteDialog.dispose();
                refresh();
            });
            newNoteDialog.add(okButton);
            newNoteDialog.setSize(400,200);
            newNoteDialog.setVisible(true);
            try {
                add(newNoteDialog);
            } catch (IllegalArgumentException ignore){}
        };
    }
    public String getPrevPath() {
        return prevPath;
    }

}