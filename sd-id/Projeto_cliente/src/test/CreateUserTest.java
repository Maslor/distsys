package pt.ulisboa.tecnico.sdis.id.ws;

import org.junit.*;

import pt.ulisboa.tecnico.sdis.id.ws.CreateUser;
import static org.junit.Assert.*;

/**
 *  Test suite
 */
public class CreateUserTest {

    // static members


    // one-time initialization and clean-up

    @BeforeClass
    public static void oneTimeSetUp() {

    }

    @AfterClass
    public static void oneTimeTearDown() {

    }


    // members
    private CreateUser user1, user2, user3, user4, user5;

    // initialization and clean-up for each test

    @Before
    public void setUp() {
    	
    	user1 = new CreateUser();
    	user2 = new CreateUser();
    	user3 = new CreateUser();
    	user4 = new CreateUser();
    	user5 = new CreateUser();
    	
    }

    @After
    public void tearDown() {
    	
    	user1 = null;
    	user2 = null;
    	user3 = null;
    	user4 = null;
    	user5 = null;
    	
    }


    // tests
		// assertEquals(expected, actual);
    	// if the assert fails, the test fails

    @Test(expected = InvalidUser_Exception.class)
    public void testSetIdNull() {
    	user1.setUserId("");    
    }
    @Test(expected = InvalidUser_Exception.class)
    public void testSetIdInt() {
    	user1.setUserId("5");    
    }
    
    @Test(expected = InvalidEmail_Exception.class)
    public void testSetEmailAddress1() {
    	user1.setEmailAddress("aloalo");
    }
    @Test(expected = InvalidEmail_Exception.class)
    public void testSetEmailAddress2() {
    	user1.setEmailAddress("@aloalo");
    }
    
    @Test(expected = InvalidEmail_Exception.class)
    public void testSetEmailAddress3() {
    	user1.setEmailAddress("aloalo@");
    }
    
    
}
