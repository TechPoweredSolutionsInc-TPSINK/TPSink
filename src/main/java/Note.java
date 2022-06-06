import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Class that handles the creation of notes in the form of {@link Note#fileType} files.
 * @author Jasper Newkirk
 * @version 1.0.0
 */
public class Note {
    /**
     * The default file type.
     */
    public static final String fileType = ".docx";

    /**
     * Constructs a new file with the given {@code fileName} in the given {@code path}. This new file will be
     * a {@link Note#fileType} file.
     * @authors Jasper Newkirk
     * @param fileName the name of the file to be created.
     * @param path the parent directory of the file.
     */
    public Note(String fileName, String path) {
        // TODO: Test this
        String filePath = path + "\\" + fileName;
        File f = new File(filePath + ".docx");
        int i = 1;
        while (f.exists()) {
            f = new File(filePath + "(" + i++ + ").docx");
        }
        try {
            f.createNewFile();
        } catch (IOException ex) {
        }
    }

    /**
     * Opens a file at the given {@code path}.
     * @author Jasper Newkirk
     * @param path the path of the file to be opened.
     */
    public static void open(String path) {
        File f = new File("User\\" + path);
        System.out.println(path);
        Desktop desktop = Desktop.getDesktop();
        if(f.exists()) {
            try {
                desktop.open(f);
            } catch (IOException ex) {
            }
        }
    }
}
