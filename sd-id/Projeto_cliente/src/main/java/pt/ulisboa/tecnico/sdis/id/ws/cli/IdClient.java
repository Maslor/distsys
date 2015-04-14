package pt.ulisboa.tecnico.sdis.id.ws.cli;

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
    	String argument = "";
    	do {
            System.out.printf("Choose a username: ");
            argument = keyboardSc.next();
        } while (argument == "");
        return argument;
    }
    
    public String readArgumentEmail(){
    	String argument = "";
    	do {
            System.out.printf("Choose a email address: ");
            argument = keyboardSc.next();
        } while (argument == "");
        return argument;
    }
    
    public void manageUsers(){
    	int option;
    	String userId;
    	String emailAddress;
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
							e.printStackTrace();
						} catch (InvalidEmail_Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvalidUser_Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (UserAlreadyExists_Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    					break;
    			
    			case 2: userId = readArgumentUser();
						try {
							SdId.renewPassword(userId);
						} catch (UserDoesNotExist_Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
				
    			case 3: userId = readArgumentUser();
						try {
							SdId.removeUser(userId);
						} catch (UserDoesNotExist_Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
						
    			case 4: userId = readArgumentUser();
    					//SdId.requestAuthentication(userId, [1]);
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

       // System.out.println("Remote call ...");
      //  String result = port.sayHello("friend");
      //  System.out.println(result);
        IdClient id = new IdClient(port);
        id.manageUsers();
    }

}
