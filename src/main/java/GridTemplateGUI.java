import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * An abstract class containing basic functionality for managing {@link Appliance} and {@link User} objects.
 * @author Jasper Newkirk
 * @version 1.0.0
 */
public abstract class GridTemplateGUI extends JFrame {
    /**
     * The default width of the window.
     */
    private static final int WIDTH = 1280;
    /**
     * The default height of the window.
     */
    private static final int HEIGHT = 720;
    /**
     * The main {@link JPanel} for display in the window.
     */
    private final JPanel panel = new JPanel();
    /**
     * The {@link JPanel} holding all {@link JButton}'s in the frame.
     */
    private final JPanel buttonPanel = new JPanel();
    /**
     * The {@link JPanel} holding the "go back" button for this frame.
     */
    private final JPanel backButtonPanel = new JPanel();

    /**
     * Constructs a new {@link GridTemplateGUI} window with a grid of {@link JButton}'s denoting all directories in
     * the current {@code path}.
     * @author Jasper Newkirk
     * @param path The parent directory.
     * @throws HeadlessException when the environment does not support a keyboard, display, or mouse.
     */
    public GridTemplateGUI(String path) throws HeadlessException {
//        System.out.println(path);
        setSize(WIDTH, HEIGHT);
        panel.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(initButtonPanel(path)); // makes buttons scrollable
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("← Go Back");
        backButton.addActionListener(getBackButtonActionListener());
        backButtonPanel.add(backButton);

        panel.add(backButtonPanel, BorderLayout.NORTH);

        add(panel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    /**
     * Initializes the button panel consisting of all directories and files present in {@code path}.
     * Empty directories will return only an add button.
     * @author Jasper Newkirk
     * @param path the directory to which the button's will populate.
     * @return a button panel consisting of all directories and files present in {@code path}.
     */
    private JPanel initButtonPanel(String path) {
        JPanel buttonPanel = new JPanel(new GridLayout(0, 2)); // infinite rows
        File[] labels = GridTemplate.loadDir(path);
        if (labels != null) {
            for (File f : labels) {
                JButton b = new JButton(f.getName());
                b.setFont(MainGUI.getPreferredFont());
                String buttonPath = f.getPath().substring(f.getPath().indexOf("\\") + 1);
                b.addActionListener(getButtonActionListener(buttonPath));
                b.addMouseListener(getMouseAdapter(b, getPopupContextMenu(b, buttonPath)));
                b.setPreferredSize(new Dimension(0, HEIGHT / 4));
                buttonPanel.add(b);
            }
        }
        JButton addButton = new JButton("+ ADD");
        addButton.setFont(MainGUI.getPreferredFont());
        addButton.setBackground(Color.GREEN.darker()); // temporary for visualization
        addButton.addActionListener(getAddButtonActionListener());
        buttonPanel.add(addButton);
        return buttonPanel;
    }

    private MouseAdapter getMouseAdapter(JButton parent, JPopupMenu menu) {
        return new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //right mouse click event
                if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
                    menu.show(parent, e.getX(), e.getY());
                }
            }
        };
    }

    /**
     * Returns the {@link JPopupMenu} associated with the {@code parent} {@link JButton}.
     * @author Jasper Newkirk
     * @param parent the {@link JButton} to be assoiated with the {@link JPopupMenu}.
     * @return the {@link JPopupMenu} associated with the {@code parent} {@link JButton}.
     */
    private JPopupMenu getPopupContextMenu(JButton parent, String path) {
        JPopupMenu menu = new JPopupMenu("Menu");

        JMenuItem properties = new JMenuItem("Properties");
        JMenuItem delete = new JMenuItem("Delete");
        delete.addActionListener(getDeleteButtonActionListener(parent, path));

        menu.add(properties);
        menu.add(delete);
        return menu;
    }

    private ActionListener getDeleteButtonActionListener(JButton parent, String path) {
        return (e) -> {
            GridTemplate.removeAll(path);
            refresh();
        };
    }

    private ActionListener getPropertiesButtonActionListener(JButton parent) {
        return (e) -> {
            //TODO: implement this correctly, grabbing from yml.
            System.out.println("Properties of " + parent.getText());
        };
    }

    /**
     * Returns a functional {@link JButton} within a {@link JPanel} used to go back to the previous screen.
     * @author Jasper Newkirk
     * @return a functional {@link JButton} within a {@link JPanel} used to go back to the previous screen.
     */
    protected abstract ActionListener getBackButtonActionListener();

    /**
     * Returns the {@link ActionListener} to be present in each directory-related button in this window.
     * @author Jasper Newkirk
     * @param path the directory associated with the {@link JButton} button.
     * @return the {@link ActionListener} to be present in each directory-related button in this window.
     */
    protected abstract ActionListener getButtonActionListener(String path);

    /**
     * Returns the {@link ActionListener} to be executed upon pressing the "Add" button in this window.
     * @author Jasper Newkirk
     * @return the {@link ActionListener} to be executed upon pressing the "Add" button in this window.
     */
    protected abstract ActionListener getAddButtonActionListener();

    /**
     * Refreshes the current {@link GridTemplateGUI}.
     * @author Jasper Newkirk
     */
    protected abstract void refresh();

}