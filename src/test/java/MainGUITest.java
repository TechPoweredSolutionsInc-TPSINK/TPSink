import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  @author Phuoc Le
 *  Date 5/31/22
 *
 * This class is made to test out MainGUI class.
 */
class MainGUITest {
    /**
     * mainG is used to for tests.
     */
    private MainGUI mainG;

    /**
     * Set up before we test all methods
     * @author Phuoc Le
     */
    @BeforeEach
    void setUp() {
        mainG = new MainGUI();
    }

    /**
     * testNonNull is to check if the initiation of object is successful or not.
     * @author Phuoc Le
     */
    @Test
    void testNonNull(){
        assertNotNull(mainG);
    }
}