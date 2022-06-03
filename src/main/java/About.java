/**
 *  @author Jasper Newkirk
 *  Date 5/9/22
 *
 *  This class has all the info about the program itself!
 *  @version 0.1.0
 */
public class About {
    private static Login L;
    static {
        try {
            L = new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * The current version number of the program.
     */
    private static final String VERSION = "0.1.2";

    /**
     * returns Version
     * @return Version
     */
    public static String getVersion() {
        return VERSION;
    }

    /**
     * The group name.
     */
    private static final String GROUP_NAME = "TPSink";

    /**
     * @return GROUP_NAME
     */
    public static String getGroupName() {
        return GROUP_NAME;
    }

    /**
     * An array of about info headings
     */
    //TODO need to fix to call username a different way
    private static final String[] HEADINGS = {"This app is registered to: " + L.getUserName() + "This app is provided by: " + GROUP_NAME};

    /**
     * @return HEADINGS
     */
    public static String[] getHeadings() {
        return HEADINGS;
    }

    /**
     * An array of group member names.
     */
    private static final String[] MEMBERS = {"Xavier Hines", "Jasper Newkirk", "Jeremy Tirador", " Phuoc Le"};

    /**
     * @return GET_MEMBERS
     */
    public static String[] getMembers() {
        return MEMBERS;
    }

    /**
     * Stores Windows name
     */
    private static final String WINDOW_NAME = "About";

    /**
     * @return WINDOW_NAME
     */
    public static String getWindowName() {
        return WINDOW_NAME;
    }

}
