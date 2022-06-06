import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import javax.swing.*;
import java.io.File;

/**
 * @author Xavier Hines
 * Date 5/9/22
 * @version 0.1.2
 */
public class Login {
    /**
     * name of the window
     */
    private String windowName;
    /**
     * Where the username will be stored?
     */
    private String userName;
    /**
     * where the user password will be stored?
     */
    private String password;
    /**
     * So that User information can be read from and written to .yml files
     */
    private final ObjectMapper objMap = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));

    /**
     * default constructor
     * @author Xavier Hines
     */
    public Login() {
    }

    /**
     * @author Xavier Hines
     * @param windowName
     */
    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }

    /**
     * @author Xavier Hines
     * @return window name
     */
    public String getWindowName() {
        return windowName;
    }

    /**
     * @author Xavier Hines
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @author Xavier Hines
     * @param password password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @author Xavier Hines
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @author Xavier Hines
     * @param userName sets username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "\nname: " + userName + "\npassword: " + password + "\nwindow name: " + windowName;
    }

    /**
     * accepts username and reads the User information from that users'
     * info.yml file.
     * @author Xavier Hines
     * @param name the username used to login
     * @return a login object with information read from .yml file
     * @throws Exception
     */
    //TODO make a version that excepts file name/user name and gets info from that file
    public Login readLoginInfo(String name) throws Exception {
        File ymlFile = new File("src/main/DataFiles/"+name + "Info.yml");
        Login getLogin = objMap.readValue(ymlFile, Login.class);
        System.out.println(getLogin);
        return getLogin;
    }

    /**
     * Action that will accept a string name and password when the user
     * attempts to login and checks them against the saved username and password.
     * @author Xavier Hines
     * @param success Label to indicate if correct or not
     * @param name String representing username
     * @param pass String representing password
     */
    public void checkUserAndPassword(JLabel success, LoginGUI gui,String name, String pass) throws Exception {
        // TODO: Upon successful Login dispose other windows
        Login loginInfo = readLoginInfo(name);
        if (name.equals(loginInfo.userName) &&
                pass.equals(loginInfo.password)) {
            success.setText("Login successful");
            gui.dispose();
            gui.getParentGUI().dispose();
            new UserGUI(name);
        } else {
            success.setText("Wrong User Name Or Pass");
        }
    }
}
