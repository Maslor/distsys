package pt.ulisboa.tecnico.sdis.id.ws;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *  Test suite
 */
public class RequestAuthenticationTest {

    // static members


    // one-time initialization and clean-up

    @BeforeClass
    public static void oneTimeSetUp() {

    }

    @AfterClass
    public static void oneTimeTearDown() {

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

    @Test
    public void testGetReserved() {
    	final String id = "duarte";
    	final String address = "duarte@tecnico.ulisboa.pt";
    	final byte[] pass = "Dddd4".getBytes();
    	
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
    	
    }
    
    @Test(expected = AuthReqFailed_Exception.class)
    public void testReqAuthNonExisting() {
    	ra.setUserId("Gabriel");
    }
    
    @Test(expected = AuthReqFailed_Exception.class)
    public void testReqAuthWrongPass() {
    	final String id = "duarte";
    	final String address = "duarte@tecnico.ulisboa.pt";
    	final byte[] pass = "Dddd4".getBytes();
    	final byte[] wrongPass = "GGGGGa5".getBytes();
    	
    	user.setUserId(id);
    	user.setEmailAddress(address);
    	
    	ra.setUserId(id);
    	ra.setReserved(wrongPass);
    }
    
    

}