import java.io.File;
/**
 * Holds file related functionality for the {@link GridTemplateGUI} class.
 * @author Jasper Newkirk
 * @version 1.0.0
 */
public class GridTemplate {
    /**
     * Creates and returns the child paths and files in the given {@code dir} directory as a {@link File} array.
     * @author Jasper Newkirk
     * @param dir the directory to be loaded from.
     * @return the child paths and files in the given {@code dir} directory as a {@link File} array or
     * {@code null} if the directory is empty.
     */
    static File[] loadDir(String dir) {
        File f = new File(dir);
        if (f.exists() && f.isDirectory()) return f.listFiles();
        return null;
    }

    /**
     * Deletes all the files in the given {@code path}.
     * @author Jasper Newkirk
     * @param path the {@code path} to be deleted.
     */
    static void removeAll(String path) {
        File[] files = loadDir("User\\" + path);
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    for(File f1 : f.listFiles()) {
                        f1.delete();
                    }
                }
                f.delete();
            }
        }
        new File("User\\" + path).delete();
    }
}
