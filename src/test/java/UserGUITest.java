import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  @author Phuoc Le
 *  Date 5/31/22
 *
 * This class is made to test out UserGUI class.
 */
class UserGUITest {
    /**
     * userG is used to for tests.
     */
    private UserGUI userG;

    /**
     * Path of a user directory
     */
    static Path tempUserDir;
    /**
     * path of a room directory
     */
    static Path tempRoomDir;
    /**
     * path of an appliance directory
     */
    static Path tempApplianceDir;


    /**
     * Path of another user directory
     */
    static Path tempUserDir2;
    /**
     * path of a room directory
     */
    static Path tempRoomDir2;
    /**
     * path of an appliance directory
     */
    static Path tempApplianceDir2;

    /**
     * will create a directory for a User, Room, and Appliance before the tests run
     * @author Xavier Hines
     * @throws IOException
     */
    @BeforeAll
    public static void createFolder() throws IOException {
        tempUserDir = Path.of(new UserGUI("TestPerson").getPath());
        tempRoomDir = Path.of(new RoomGUI(tempUserDir.toString()).getPath());
        tempApplianceDir = Path.of(new ApplianceGUI(tempRoomDir.toString()).getPath());
    }
    /**
     * will delete the User directory after the tests which will also delete
     * all the other directories as they are held within User
     * @author Xavier Hines
     * @throws IOException
     */
    @AfterAll
    public static void deleteFolder() throws IOException {
        Files.delete(tempUserDir);
    }

    /**
     * Set up before we test all methods
     * @author Phuoc Le
     */
    @BeforeEach
    void setUp() {
        userG = new UserGUI("TestPerson");
    }

    /**
     * testNonNull is to check if the initiation of object is successful or not.
     * @author Phuoc Le
     */

    @Test
    void testNonNull(){
        assertNotNull(userG);
    }

    /**
     * testUserGUI is to check if the path is saved correctly or not.
     * @author Phuoc Le
     */
    @Test
    void testUserGUI(){
        assertEquals("User\\TestPerson\\", userG.getPath());
    }


    /**
     * testNewUserGUI is to check if new user is added correctly.
     * @author Phuoc Le
     */
    @Test
    void testNewUserGUI() throws IOException {
        tempUserDir2 = Path.of(new UserGUI("JohnWick").getPath());
        tempRoomDir2 = Path.of(new RoomGUI(tempUserDir2.toString()).getPath());
        tempApplianceDir2 = Path.of(new ApplianceGUI(tempRoomDir2.toString()).getPath());

        UserGUI newUser = new UserGUI ("JohnWick");
        assertNotNull(newUser);
        assertEquals("User\\JohnWick\\", newUser.getPath());

        Files.delete(tempUserDir2);
    }

    /**
     * testUserGUITile is to check if title is set correctly or not.
     * @author Phuoc Le
     */
    @Test
    void testUserGUITitle(){
        assertEquals("TestPerson", userG.getTitle());
    }
}