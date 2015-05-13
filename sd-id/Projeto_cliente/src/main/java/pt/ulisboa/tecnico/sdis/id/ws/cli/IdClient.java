package pt.ulisboa.tecnico.sdis.id.ws.cli;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.ws.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import static javax.xml.bind.DatatypeConverter.parseBase64Binary;
import static javax.xml.bind.DatatypeConverter.printBase64Binary;
import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;
import pt.ulisboa.tecnico.sdis.id.ws.uddi.UDDINaming;
import pt.ulisboa.tecnico.sdis.id.ws.*; // classes generated from WSDL


public class IdClient {
	
	private SDId SdId; 
    private Scanner keyboardSc;
    private String menuOptions = " 1 - Create User\n 2 - Renew Password\n 3 - Remove User\n 4 - Authentication\n 5 - Exit\n";
    private int nonce = 0; /*part 2 proj*/
    private String servicePassword = "";
    
    private byte[] ticket;
            
    
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
        System.out.printf("Write service name: ");
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
						
    			case 4: String cliPass = null;
    					userId = readArgumentUser();
    					if(userId.equals("alice")){
    						cliPass = "Aaa1";
    					}
    					if(userId.equals("bruno")){
    						cliPass = "Bbb2";
    					}
    					if(userId.equals("carla")){
    						cliPass = "Ccc3";
    					}
    					if(userId.equals("duarte")){
    						cliPass = "Ddd4";
    					}
    					if(userId.equals("eduardo")){
    						cliPass = "Eee5";
    					}
    					String service = readPassword();
    					String filepath = "/Users/David/Documents/IST/SDis/Projeto_cliente/src/main/resources/ReservedArgs.xml";
    					Document xmlDocument = null;
    					nonce += 1;
    					
    					/*creating a xml with 2 attributes, one for nonce and other for service
    					 * 
    					 */
    					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder docBuilder = null;
						try {
							docBuilder = docFactory.newDocumentBuilder();
						} catch (ParserConfigurationException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						try {
							xmlDocument = docBuilder.parse(filepath);
						} catch (SAXException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						
						/*updating the xml attributes with the nonce and the service*/
    			        Node header = xmlDocument.getElementsByTagName("header").item(0);
    			        NodeList xmlAttributes = header.getChildNodes();
    			        for (int i = 0; i < xmlAttributes.getLength(); i++) {
    			        	 
    		                   Node node = xmlAttributes.item(i);
    		 
    				   
    		                   if ("service".equals(node.getNodeName())) {
    		                	   node.setTextContent(service);
    		                   }
    		 
    		                   
    		                   if ("nonce".equals(node.getNodeName())) {
    		                	   node.setTextContent(String.valueOf(nonce));
    		                   }
    		 
    			        }
    			        
    			        
    			        /*updating the xml*/
    			        TransformerFactory transformerFactoryXML = TransformerFactory.newInstance();
						Transformer transformerXML = null;
						try {
							transformerXML = transformerFactoryXML.newTransformer();
						} catch (TransformerConfigurationException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
    					DOMSource sourceXML = new DOMSource(xmlDocument);
    					StreamResult resultXML = new StreamResult(new File(filepath));
						try {
							transformerXML.transform(sourceXML, resultXML);
						} catch (TransformerException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
    			        
    			        /*converting the xml to a byte array*/
    			        TransformerFactory transformerFactory = TransformerFactory.newInstance();
						Transformer transformer = null;
						try {
							transformer = transformerFactory.newTransformer();
						} catch (TransformerConfigurationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		    			        DOMSource source = new DOMSource(xmlDocument);
		    			        
		    			        ByteArrayOutputStream bos=new ByteArrayOutputStream();
		    			        StreamResult result=new StreamResult(bos);
						try {
							transformer.transform(source, result);
						} catch (TransformerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
    			        byte[] auth=bos.toByteArray();
   
    			        
    			        
						try {
							byte[] authSucc = SdId.requestAuthentication(userId, auth);
							AuthSuccessConv authResponse = new AuthSuccessConv(authSucc);
							byte[] dataCli = parseBase64Binary(authResponse.getDataCli());
							ticket = parseBase64Binary(authResponse.getTicket());
									
							
							
							
							MessageDigest sha;
							byte[] cliPassByte = cliPass.getBytes();
							try {
								sha = MessageDigest.getInstance("SHA-1");
								cliPassByte = sha.digest(cliPassByte);
								cliPassByte = Arrays.copyOf(cliPassByte, 16);
							} catch (NoSuchAlgorithmException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							try {
							
								Cipher cipherCli = Cipher.getInstance("AES/ECB/PKCS5Padding");
								SecretKeySpec skeyCli = new SecretKeySpec(cliPassByte, "AES");
								try {
									cipherCli.init(Cipher.DECRYPT_MODE, skeyCli);
									byte[] decriptedServiceKey = cipherCli.doFinal(dataCli);
									
									byte[] decriptedKey = new byte[6];
									byte[] decriptedNonceByte = new byte[4];
									decriptedKey = Arrays.copyOfRange(decriptedServiceKey, 0, 6);
									decriptedNonceByte =Arrays.copyOfRange(decriptedServiceKey, 6, 10);
									nonce = ByteBuffer.wrap(decriptedNonceByte).getInt();
									servicePassword = new String(decriptedKey);
									System.out.printf("service key is - %s ||| nonce is - %d\n", servicePassword, nonce);
								} catch (InvalidKeyException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalBlockSizeException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (BadPaddingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} catch (NoSuchAlgorithmException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (NoSuchPaddingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
							
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
