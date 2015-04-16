package pt.ulisboa.tecnico.sdis.id.ws;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *  Test suite
 */
public class RemoveUserTest {

    // static members


    // one-time initialization and clean-up

    @BeforeClass
    public static void oneTimeSetUp() {

    }

    @AfterClass
    public static void oneTimeTearDown() {

    }


    // members
    private RemoveUser remove1, remove2;
    private CreateUser user1;

    // initialization and clean-up for each test

    @Before
    public void setUp() {
    	remove1 = new RemoveUser();
    	remove2 = new RemoveUser();
    }

    @After
    public void tearDown() {
    	remove1 = null;
    	remove2 = null;
    }


    // tests

    @Test
    public void testRemoveExisting() {
    	final String id = "bruno";
    	user1.setUserId(id);
    	remove1.setUserId(id);
    	assertEquals(null,user1.getUserId());	
    }
    
    @Test(expected = UserDoesNotExist_Exception.class)
    public void testRemoveNonExisting() {
    	final String id2 = "carla";
    	remove2.setUserId(id2);
    }

}
