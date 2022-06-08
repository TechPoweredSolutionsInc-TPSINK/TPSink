import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

/**
 *  @author Xavier Hines
 *  Date 5/23/22
 *
 * This class handles the yaml reading and writing for the user information
 * such as password name and email further functionality may be added
 *  @version 0.1.0
 */
public class User {
    /**
     * will contain userName through different iterations
     */
    private String userName;

    /**
     * will contain password through different iterations
     */
    private String password;

    /**
     * will contain userEmail through different iterations
     */
    private String userEmail;

   /**
     * Used to read from and write to .yml files
     */
    private final ObjectMapper objMap = new ObjectMapper(
                                        new YAMLFactory().disable(
                                            YAMLGenerator.Feature.
                                            WRITE_DOC_START_MARKER));
  
    /**
     * Default constructor
     * @author Xavier Hines
     */
    public User(){}

    /**
     * Constructor when information needs to be stored or passed
     * @author Xavier Hines
     * @param name username
     * @param pass user password
     * @param email user email
     */
    public User(String name, String pass, String email) {
        //TODO need to create UserInfo.yml and login info when new user constructed
        userName = name;
        password = pass;
        userEmail = email;
    }

    /**
     * will return username
     * @author Xavier Hines
     * @return username
     */
    public String getUserName() {return userName;}

    /**
     * will set username
     * @author Xavier Hines
     * @param userName username passed in
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * will return password
     * @author Xavier Hines
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * will return password
     * @author Xavier Hines
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * will return email
     * @author Xavier Hines
     * @return email
     */
    public String getUserEmail() {return userEmail;}

    /**
     * will set email
     * @author Xavier Hines
     * @param userEmail email
     */
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}

    /**
     * overrides toString method to print user information
     * @author Xavier Hines
     * @return return string with username, password, and email
     */
    @Override
    public String toString() {
        return "\nname: " + userName + "\npassword: " + password + "\nemail: " + userEmail;
    }

    /**
     * will read a User's yml file
     * @author Xavier Hines
     * @author Jeremy Tirador
     * @throws Exception
     */
    public void yamlRead(String n) throws Exception {
        File ymlFile = new File("src/main/DataFiles/" + n + ".yml");
        User TDA = objMap.readValue(ymlFile, User.class);
        System.out.println(TDA);
    }

    /**
     * will read yaml file passed to it
     * @author Xavier Hines
     * @param F yaml file
     * @return user object with values read from yaml file
     * @throws Exception
     */
    public User yamlReadFile(File F) throws Exception {
        User userTDA = objMap.readValue(F, User.class);
        System.out.println(userTDA.toString());
        return userTDA;
    }

    /**
     * @author Jeremy Tirador
     *
     * will write information to new/existing yaml file
     * @param n name
     * @param pass password
     * @param e email
     * @throws IOException
     */
    public void yamlWrite(String n, String pass, String e) throws IOException {
        User TDA = new User(
                n,
                pass,
                e
        );
        initHiddenDir("DataFiles");
        objMap.writeValue(new File("DataFiles/" + n + "Info.yml"), TDA);
    }

    /**
     * takes a user object and writes to new/existing file with the information
     * passed in the user object
     * @author Xavier Hines
     * @param u User object
     * @throws IOException
     */
    public void userImportWrite(User u) throws IOException {
        User TDA = new User(
                u.userName,
                u.password,
                u.userEmail
        );
        objMap.writeValue(new File("DataFiles/" + u.userName +"Info.yml"), TDA);
    }

    /**
     * Will import the user information in a yaml file that the user selects
     * through a file chooser.
     * @author Xavier Hines
     * @throws Exception
     */
    public void userImport() throws Exception {
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("DataFiles"));

        int response = fileChooser.showSaveDialog(null);

        if(response == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }

        userImportWrite(yamlReadFile(file));
    }

    /**
     * will export a yaml file with the current information stored in the UserInfo
     * yaml file in DataFiles
     * @author Xavier Hines
     * @throws IOException
     */
    public void userExport() throws IOException {
        File f = null;
        File newFile = new File("export.yml");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("DataFiles"));

        int response = fileChooser.showOpenDialog(null);

        if(response == JFileChooser.APPROVE_OPTION) {
            f = new File(fileChooser.getSelectedFile().getAbsolutePath());
        }

        assert f != null;
        //TODO make so can choose where copy is sent
        //doesn't finish export until program closes
        Files.copy(f.toPath(), newFile.toPath());
    }

    /**
     * Creates the hidden {@code path} folder.
     * @author Jasper Newkirk
     */
    protected static void initHiddenDir(String path) {
        File user = new File(path);
        try {
            user.mkdirs();
            Files.setAttribute(FileSystems.getDefault().getPath(path.contains("\\") ? path.substring(0, path.indexOf("\\")) : path), "dos:hidden", true);
        } catch (IOException e) {
            path = "." + path;
            user = new File(path);
            user.mkdirs();
        }
    }
}
