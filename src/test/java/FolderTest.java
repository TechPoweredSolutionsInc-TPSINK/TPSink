import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xavier Hines
 * @version 1.0
 * Date: 6/5/2022
 */
public class FolderTest {
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
     * Creates a new User directory and test that directory path
     * against the given directory path if they are the same then
     * the Directory was successfully created
     * @author Xavier Hines
     */
    @Test
    void testAddUser() {
        UserGUI tester = new UserGUI("TestPerson");

        assertEquals(tempUserDir, Path.of(tester.getPath()));
    }

    /**
     * Creates a new Room directory and test that directory path
     * against the given directory path if they are the same then
     * the Directory was successfully created
     * @author Xavier Hines
     */
    @Test
    void testAddRoom() {
        RoomGUI tester = new RoomGUI(tempUserDir.toString());

        assertEquals(tempRoomDir, Path.of(tester.getPath()));
    }

    /**
     * Creates a new Appliance directory and test that directory path
     * against the given directory path if they are the same then
     * the Directory was successfully created
     * @author Xavier Hines
     */
    @Test
    void testAddAppliance() {
        ApplianceGUI tester = new ApplianceGUI(tempRoomDir.toString());

        assertEquals(tempApplianceDir, Path.of(tester.getPath()));
    }
}
