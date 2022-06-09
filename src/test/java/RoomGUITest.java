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
 * This class is made to test out RoomGUI class.
 */
class RoomGUITest {

    /**
     * roomG is used to for tests.
     */
    private RoomGUI roomG;

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
     * Set up before we test all methods
     * @author Phuoc Le
     */
    @BeforeEach
    void setUp() {
        roomG = new RoomGUI("TestPerson\\Kitchen");
    }

    /**
     * testNonNull is to check if the initiation of object is successful or not.
     * @author Phuoc Le
     */
    @Test
    void testNonNull(){
        assertNotNull(roomG);
    }

    /**
     * testPreviousPath is to check if the previous path is saved correctly or not.
     * @author Phuoc Le
     */
    @Test
    void testPreviousPath(){
        assertEquals("TestPerson", roomG.getPrevPath());
    }

    /**
     * testPath is to check if the path is saved correctly or not.
     * @author Phuoc Le
     */
    @Test
    void testPath(){
        assertEquals("User\\TestPerson\\Kitchen\\", roomG.getPath()); // the path is used to call is different from path is saved
    }

    /**
     * testRoomGUITile is to check if title is set correctly or not.
     * @author Phuoc Le
     */
    @Test
    void testRoomGUITile(){
        assertEquals("Kitchen", roomG.getTitle());
    }


}