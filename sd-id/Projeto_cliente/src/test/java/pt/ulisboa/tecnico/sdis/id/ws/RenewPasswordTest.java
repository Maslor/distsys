package pt.ulisboa.tecnico.sdis.id.ws;

import org.junit.*;

import static org.junit.Assert.*;

/**
 *  Test suite
 */
public class RenewPasswordTest {

    // static members
	private static SDId port;
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
   
    private RenewPassword rp;

    // initialization and clean-up for each test

    @Before
    public void setUp() {
    	rp = new RenewPassword();
    }

    @After
    public void tearDown() {
    	rp = null;
    }


    // tests
    
    @Test(expected = UserDoesNotExist_Exception.class)
    public void testRemoveNonExisting() throws UserDoesNotExist_Exception {
    	final String id = "obligadoooo";
    	port.renewPassword(id);
    }

}
