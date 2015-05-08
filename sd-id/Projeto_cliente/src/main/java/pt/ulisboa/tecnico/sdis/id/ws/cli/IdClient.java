package pt.ulisboa.tecnico.sdis.id.ws.cli;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Scanner;

import javax.xml.ws.*;

import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;
import pt.ulisboa.tecnico.sdis.id.ws.uddi.UDDINaming;
import pt.ulisboa.tecnico.sdis.id.ws.*; // classes generated from WSDL


public class IdClient {
	
	private SDId SdId; 
    private Scanner keyboardSc;
    private String menuOptions = " 1 - Create User\n 2 - Renew Password\n 3 - Remove User\n 4 - Authentication\n 5 - Exit\n";
    private int nonce = 0; /*part 2 proj*/
    
    public IdClient(SDId port){
    	this.SdId = port;
        keyboardSc = new Scanner(System.in);
    }
    
    public String getMenuOptions(){
    	return menuOptions;
    }
    
    public int readOption(){
    	int option;
    	do {
            System.out.printf("Choose an option from 1 to 5\n");
            option = keyboardSc.nextInt();
        } while (option < 1 || option > 5);
        return option;
    }
    
    public String readArgumentUser(){
    	String argument = " ";
        System.out.printf("Choose a username: ");
        argument = keyboardSc.next();
        return argument;
    }
    
    public String readArgumentEmail(){
    	String argument = "";
        System.out.printf("Choose a email address: ");
        argument = keyboardSc.next();
        return argument;
    }
    
    public String readPassword(){
    	String argument = "";
        System.out.printf("Write password: ");
        argument = keyboardSc.next();
        return argument;
    }
    
    public void manageUsers(){
    	int option;
    	String userId;
    	String emailAddress;
    	String pass;
    	while(true){
        	System.out.printf("%s", getMenuOptions());
    		option = readOption();
    		
    		switch(option){
    			case 1: userId = readArgumentUser();
    					emailAddress = readArgumentEmail();
						try {
							SdId.createUser(userId, emailAddress);
						} catch (EmailAlreadyExists_Exception e) {
							// TODO Auto-generated catch block
							System.out.printf(e.getMessage());
						} catch (InvalidEmail_Exception e) {
							// TODO Auto-generated catch block
							System.out.printf(e.getMessage());
						} catch (InvalidUser_Exception e) {
							// TODO Auto-generated catch block
							System.out.printf(e.getMessage());
						} catch (UserAlreadyExists_Exception e) {
							// TODO Auto-generated catch block
							System.out.printf(e.getMessage());
						}
    					break;
    			
    			case 2: userId = readArgumentUser();
						try {
							SdId.renewPassword(userId);
						} catch (UserDoesNotExist_Exception e) {
							// TODO Auto-generated catch block
							System.out.printf(e.getMessage());
						}
						break;
				
    			case 3: userId = readArgumentUser();
						try {
							SdId.removeUser(userId);
						} catch (UserDoesNotExist_Exception e) {
							// TODO Auto-generated catch block
							System.out.printf(e.getMessage());
						}
						break;
						
    			case 4: userId = readArgumentUser();
    					pass = readPassword();
    					nonce += 1;
    					byte[] nonceByte = ByteBuffer.allocate(4).putInt(nonce).array();
    					byte[] password = pass.getBytes();
    					byte[] auth = new byte[password.length + nonceByte.length];
    					System.arraycopy(password, 0, auth, 0, password.length);
						System.arraycopy(nonceByte, 0, auth, password.length, nonceByte.length);
						try {
							SdId.requestAuthentication(userId, auth);
						} catch (AuthReqFailed_Exception e) {
							// TODO Auto-generated catch block
							System.out.printf(e.getMessage());
						}
						
    					break;
    			
    			case 5:	return;
    		}			
    	}
    }

    
    public static void main(String[] args) throws Exception {
        // Check arguments
        if (args.length < 2) {
            System.err.println("Argument(s) missing!");
            System.err.printf("Usage: java %s uddiURL name%n", IdClient.class.getName());
            return;
        }

        String uddiURL = args[0];
        String name = args[1];

        System.out.printf("Contacting UDDI at %s%n", uddiURL);
        UDDINaming uddiNaming = new UDDINaming(uddiURL);

        System.out.printf("Looking for '%s'%n", name);
        String endpointAddress = uddiNaming.lookup(name);

        if (endpointAddress == null) {
            System.out.println("Not found!");
            return;
        } else {
            System.out.printf("Found %s%n", endpointAddress);
        }

        System.out.println("Creating stub ...");
        SDId_Service service = new SDId_Service();
        SDId port = service.getSDIdImplPort();

        System.out.println("Setting endpoint address ...");
        BindingProvider bindingProvider = (BindingProvider) port;
        Map<String, Object> requestContext = bindingProvider.getRequestContext();
        requestContext.put(ENDPOINT_ADDRESS_PROPERTY, endpointAddress);

       
        IdClient id = new IdClient(port);
        id.manageUsers();
    }

}
