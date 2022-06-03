import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
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




    private final ObjectMapper objMap = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));

    /**
     * Default constructor
     */
    public User(){}

    /**
     * Constructor when information needs to be stored or passed
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
     * @return username
     */
    public String getUserName() {return userName;}

    /**
     * will set username
     * @param userName username passed in
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * will return password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * will return password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * will return email
     * @return email
     */
    public String getUserEmail() {return userEmail;}

    /**
     * will set email
     * @param userEmail email
     */
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}

    /**
     * overides toString method to print user information
     * @return return string with username, password, and email
     */
    @Override
    public String toString() {
        return "\nname: " + userName + "\npassword: " + password + "\nemail: " + userEmail;
    }

    /**
     * will read UserInfo.yml
     * @throws Exception
     */
    public void yamlRead() throws Exception {
        File ymlFile = new File("src/main/DataFiles/UserInfo.yml");
        User TDA = objMap.readValue(ymlFile, User.class);
        System.out.println(TDA);
    }

    /**
     * will read yaml file passed to it
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
     * will write information to userInfo yaml
     * @param success Jlabel to modify text to inform user
     * @param n name
     * @param e email
     * @throws IOException
     */
    public void yamlWrite(JLabel success, String n, String e) throws IOException {
        success.setText("Entered");
        User TDA = new User(
                n,
                password,
                e
        );
        objMap.writeValue(new File("src/main/DataFiles/UserInfo.yml"), TDA);
    }

    /**
     * takes a user object and writes to UserInfo with the information
     * passed in the user object
     * @param u User object
     * @throws IOException
     */
    public void userImportWrite(User u) throws IOException {
        User TDA = new User(
                u.userName,
                u.password,
                u.userEmail
        );
        objMap.writeValue(new File("src/main/DataFiles/UserInfo.yml"), TDA);
    }

    /**
     * Will import the user information in a yaml file that the user selects
     * through a file chooser.
     * @throws Exception
     */
    public void userImport() throws Exception {
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("src/main/DataFiles"));

        int response = fileChooser.showSaveDialog(null);

        if(response == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }

        userImportWrite(yamlReadFile(file));
    }

    /**
     * will export a yaml file with the current information stored in the UserInfo
     * yaml file in DataFiles
     * @throws IOException
     */
    public void userExport() throws IOException {
        File f = null;
        File newFile = new File("export.yml");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("src/main/DataFiles"));

        int response = fileChooser.showOpenDialog(null);

        if(response == JFileChooser.APPROVE_OPTION) {
            f = new File(fileChooser.getSelectedFile().getAbsolutePath());
        }

        assert f != null;
        //TODO make so can choose where copy is sent
        //doesn't finish export until program closes
        Files.copy(f.toPath(), newFile.toPath());
    }
}
