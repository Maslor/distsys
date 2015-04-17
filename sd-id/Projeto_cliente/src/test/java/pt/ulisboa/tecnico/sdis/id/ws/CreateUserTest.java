package pt.ulisboa.tecnico.sdis.id.ws;

import org.junit.*;

import pt.ulisboa.tecnico.sdis.id.ws.CreateUser;
import static org.junit.Assert.*;

/**
 *  Test suite
 */
public class CreateUserTest {

    // static members
	
	private static SDId port;
	private static String user = "duarte";
	private static String address = "alice@tecnico.ulisboa.pt";

    // one-time initialization and clean-up

    @BeforeClass
    public static void oneTimeSetUp() {
    	SDId_Service service = new SDId_Service();
    	port = service.getSDIdImplPort();

    }

    @AfterClass
    public static void oneTimeTearDown() {
    	port = null;
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
    public void testSetIdNull() throws InvalidUser_Exception, EmailAlreadyExists_Exception, InvalidEmail_Exception, UserAlreadyExists_Exception{
    	
    	port.createUser("", "a@b");
    }
    @Test(expected = InvalidUser_Exception.class)
    public void testSetIdInt() throws EmailAlreadyExists_Exception, InvalidEmail_Exception, InvalidUser_Exception, UserAlreadyExists_Exception {
    	port.createUser("5","a@b");
    }
    
    @Test(expected = InvalidEmail_Exception.class)
    public void testSetInvalidEmailString() throws EmailAlreadyExists_Exception, InvalidEmail_Exception, InvalidUser_Exception, UserAlreadyExists_Exception {
    	port.createUser(user, "alo"); 
    }
    @Test(expected = InvalidEmail_Exception.class)
    public void testSetInvalidEmailName() throws EmailAlreadyExists_Exception, InvalidEmail_Exception, InvalidUser_Exception, UserAlreadyExists_Exception {
    	port.createUser(user,"@aloalo");
    }
    
    @Test(expected = InvalidEmail_Exception.class)
    public void testSetInvalidEmailServer() throws EmailAlreadyExists_Exception, InvalidEmail_Exception, InvalidUser_Exception, UserAlreadyExists_Exception {
    	port.createUser(user,"@aloalo");
    }
    
    /*@Test
    public void testGetId() throws EmailAlreadyExists_Exception, InvalidEmail_Exception, InvalidUser_Exception, UserAlreadyExists_Exception {
    	port.createUser(user, address);
    	assertEquals("duarte",...);
    	Não sei testar quando dá bem ahah
    }*/
  
    @Test(expected = EmailAlreadyExists_Exception.class)
    public void testSameAddress() throws EmailAlreadyExists_Exception, InvalidEmail_Exception, InvalidUser_Exception, UserAlreadyExists_Exception {
    	port.createUser("carla", address);
    	port.createUser("duarte",address);
    }
    
    @Test(expected = UserAlreadyExists_Exception.class)
    public void testSameUserId() throws EmailAlreadyExists_Exception, InvalidEmail_Exception, InvalidUser_Exception, UserAlreadyExists_Exception {
    	port.createUser(user,"hello@alolo");
    	port.createUser(user,"bye@aloalo");
    }
    
}