package pt.ulisboa.tecnico.sdis.id.ws;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *  Test suite
 */
public class RemoveUserTest {

    // static members
	private static SDId port;
	private static String user = "bruno";
	private static String address ="bruno@tecnico.ulisboa.pt";
	

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
    private RemoveUser remove1, remove2;
    private CreateUser user1;

    // initialization and clean-up for each test

    @Before
    public void setUp() {
    	remove1 = new RemoveUser();
    	remove2 = new RemoveUser();
    	user1 = new CreateUser();
    }

    @After
    public void tearDown() {
    	remove1 = null;
    	remove2 = null;
    	user1 = null;
    }


    // tests

    /*@Test
    public void testRemoveExisting() throws EmailAlreadyExists_Exception, InvalidEmail_Exception, InvalidUser_Exception, UserAlreadyExists_Exception, UserDoesNotExist_Exception {
    	
    	final int a = port.createUser(user,address);
    	port.removeUser(user);
    	
    }*/
    
    @Test(expected = UserDoesNotExist_Exception.class)
    public void testRemoveNonExisting() throws UserDoesNotExist_Exception {
    	port.removeUser(user);
    }

}