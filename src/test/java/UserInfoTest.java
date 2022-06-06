import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Xavier Hines
 * @version 1.0
 * Date: 6/5/2022
 */
public class UserInfoTest {
    /**
     * creates file from the test.yml file
     */
    static File testFile = new File("src/main/DataFiles/Test.yml");

    /**
     * Test that the username is correct and readable from file
     * @author Xavier Hines
     * @throws Exception
     */
    @Test
    void testUsername1() throws Exception {
        User tester = new User().yamlReadFile(testFile);

        assertEquals("TestPerson", tester.getUserName());
    }

    /**
     * Test that the username is correctly returning the username
     * @author Xavier Hines
     * @throws Exception
     */
    @Test
    void testUsername2() throws Exception {
        User tester = new User().yamlReadFile(testFile);

        if(tester.getUserName().equals("")) {
            fail();
        } else {
            assertTrue(true);
        }
    }

    /**
     * Test that the password is correct and readable from file
     * @author Xavier Hines
     * @throws Exception
     */
    @Test
    void testUserPassword1() throws Exception {
        User tester = new User().yamlReadFile(testFile);

        assertEquals("12345", tester.getPassword());
    }

    /**
     * Test that the password is correctly returning the username
     * @author Xavier Hines
     * @throws Exception
     */
    @Test
    void testUserPassword2() throws Exception {
        User tester = new User().yamlReadFile(testFile);

        if(tester.getPassword().equals("")) {
            fail();
        } else {
            assertTrue(true);
        }
    }

    /**
     * Test that the email is correct and readable from file
     * @author Xavier Hines
     * @throws Exception
     */
    @Test
    void testUserEmail1() throws Exception {
        User tester = new User().yamlReadFile(testFile);

        assertEquals("TestPerson@email.com", tester.getUserEmail());
    }

    /**
     * Test that the email is correctly returning the username
     * @author Xavier Hines
     * @throws Exception
     */
    @Test
    void testUserEmail2() throws Exception {
        User tester = new User().yamlReadFile(testFile);

        if(tester.getUserEmail().equals("")) {
            fail();
        } else {
            assertTrue(true);
        }
    }
}
