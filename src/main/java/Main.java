import java.awt.*;
import java.io.File;

/**
 * @author Xavier Hines
 * Date 5/12/22
 */
public class Main {

    /**
     * Path for main window home Image
     */
    private static final String PATH_TO_LOGO = "Assets/TPSink.png";

    /**
     * main method, runs program
     * @author Xavier Hines
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //Room r = new Room();
        //r.yamlRead();
        //User us = new User();
        //new UserGUI("Xavier");
        //us.yamlRead();
        //us.yamlWrite();
        //new MainGUI();
        //new RoomGUI("Test");
        //new UserGUI("Jeremy");
        //new User();
        new MainGUI();
        //new RoomGUI("Xavier");
//        Login a = new Login();
//        a.yamlRead();

    }

    /**
     * returns main image path
     * @author Jasper Newkirk
     * @return main image path
     */
    public static String getPathToLogo() {
        return PATH_TO_LOGO;
    }
}

