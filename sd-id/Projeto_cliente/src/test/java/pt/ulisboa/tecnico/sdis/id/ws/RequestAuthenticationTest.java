package pt.ulisboa.tecnico.sdis.id.ws;

import org.junit.*;

import static org.junit.Assert.*;

/**
 *  Test suite
 */
public class RequestAuthenticationTest {

    // static members
	private static SDId port;
	private static String id = "duarte";
	private static String address = "duarte@tecnico.ulisboa.pt";
	private static byte[] pass = "Dddd4".getBytes();

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
    private RequestAuthentication ra;
    private CreateUser user;

    // initialization and clean-up for each test

    @Before
    public void setUp() {
    ra = new RequestAuthentication();	
    user = new CreateUser();
    }

    @After
    public void tearDown() {
    ra = null;
    user = null;
    }


    // tests

   /* @Test
    public void testGetReserved() {
    	
    	
    	user.setUserId(id);
    	user.setEmailAddress(address);
    	
    	ra.setUserId(id);
    	ra.setReserved(pass);
    	
    	assertEquals("Dddd4".getBytes(),ra.getReserved());
    	
    }
    
    @Test
    public void testGetId() {
    	final String id = "duarte";
    	final String address = "duarte@tecnico.ulisboa.pt";
    	final byte[] pass = "Dddd4".getBytes();
    	
    	user.setUserId(id);
    	user.setEmailAddress(address);
    	
    	ra.setUserId(id);
    	ra.setReserved(pass);
    	
    	assertEquals(id,ra.getUserId());
    	
    }*/
    
    @Test(expected = AuthReqFailed_Exception.class)
    public void testReqAuthNonExisting() throws AuthReqFailed_Exception {
    	port.requestAuthentication("basankdnsdjnakdjnkasjdn", pass);
    }
    
    @Test(expected = AuthReqFailed_Exception.class)
    public void testReqAuthWrongPass() throws EmailAlreadyExists_Exception, InvalidEmail_Exception, InvalidUser_Exception, UserAlreadyExists_Exception, AuthReqFailed_Exception {
    	
    	final byte[] wrongPass = "GGGGGa5".getBytes();
    	
    	port.createUser("whatusayintome", "asdsamdjasmdasmdkjas@kdmfjkmaskdjmaskdm");
    	
    	port.requestAuthentication("whatusayintome", wrongPass);
    }
    
    

}