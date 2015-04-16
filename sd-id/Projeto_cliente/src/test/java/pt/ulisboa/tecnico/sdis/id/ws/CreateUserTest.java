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
    private CreateUser user1, user2;

    // initialization and clean-up for each test

    @Before
    public void setUp() {
    	
    	user1 = new CreateUser();
    	user2 = new CreateUser();
    	
    }

    @After
    public void tearDown() {
    	
    	user1 = null;
    	user2 = null;
    
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
    public void testSetInvalidEmailString() {
    	user1.setEmailAddress("aloalo");
    }
    @Test(expected = InvalidEmail_Exception.class)
    public void testSetInvalidEmailName() {
    	user1.setEmailAddress("@aloalo");
    }
    
    @Test(expected = InvalidEmail_Exception.class)
    public void testSetInvalidEmailServer() {
    	user1.setEmailAddress("aloalo@");
    }
    
    @Test
    public void testGetId() {
    	final String id = "alice";
    	user1.setUserId(id);
    	assertEquals("alice",user1.getUserId());
    }
    
    @Test
    public void testGetAddress() {
    	final String address = "alice@tecnico.ulisboa.pt";
    	user1.setEmailAddress(address);
    	assertEquals("alice@tecnico.ulisboa.pt",user1.getEmailAddress());
    }
    
    @Test(expected = EmailAlreadyExists_Exception.class)
    public void testSameAddress() {
    	final String address = "alice@tecnico.ulisboa.pt";
    	user1.setEmailAddress(address);
    	user2.setEmailAddress(address);
    }
    
    @Test(expected = UserAlreadyExists_Exception.class)
    public void testSameUserId() {
    	final String id = "alice";
    	user1.setUserId(id);
    	user2.setUserId(id);
    }
    
}