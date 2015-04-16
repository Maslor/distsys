package pt.ulisboa.tecnico.sdis.id.ws;

import org.junit.*;

import static org.junit.Assert.*;

/**
 *  Test suite
 */
public class RenewPasswordTest {

    // static members


    // one-time initialization and clean-up

    @BeforeClass
    public static void oneTimeSetUp() {

    }

    @AfterClass
    public static void oneTimeTearDown() {

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
    public void testRemoveNonExisting() {
    	final String id = "carla";
    	rp.setUserId(id);
    }

}