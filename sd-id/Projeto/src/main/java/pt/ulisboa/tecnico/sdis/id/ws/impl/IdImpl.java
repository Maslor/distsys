package pt.ulisboa.tecnico.sdis.id.ws.impl;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.jws.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import static javax.xml.bind.DatatypeConverter.parseBase64Binary;
import static javax.xml.bind.DatatypeConverter.printBase64Binary;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

import pt.ulisboa.tecnico.sdis.id.ws.*; // classes generated from WSDL

@WebService(
		endpointInterface="pt.ulisboa.tecnico.sdis.id.ws.SDId", 
	    wsdlLocation="SD-ID.1_1.wsdl",
	    name="SdId",
	    portName="SDIdImplPort",
	    targetNamespace="urn:pt:ulisboa:tecnico:sdis:id:ws",
	    serviceName="SDId"
)
public class IdImpl implements SDId {
	
	private HashMap<CreateUser, String> registedUsers = new HashMap <CreateUser, String>();
	private HashMap<String, Integer> usersNonces = new HashMap <String, Integer>();
	private String storeServiceKeyString = "c25qrx";
	private String storeServiceName = "sd-store";
	private String[] baseUsers = {"alice", "bruno", "carla", "duarte", "eduardo"};
	private String[] baseUsersPass = {"Aaa1", "Bbb2", "Ccc3", "Ddd4", "Eee5"};
	private String[] baseUsersMail = {"alice@tecnico.pt", "bruno@tecnico.pt", "carla@tecnico.pt", "duarte@tecnico.pt", "eduardo@tecnico.pt"};
	
	/* test purposes*/
	public IdImpl(){
		for (int i = 0; i < baseUsers.length; i++){
			CreateUser newUser = new CreateUser();
			newUser.setUserId(baseUsers[i]);
			newUser.setEmailAddress(baseUsersMail[i]);
			registedUsers.put(newUser, baseUsersPass[i]);
			usersNonces.put(baseUsers[i], 0);
		}
		
	}
	
	
	public void createUser(String userId, String emailAddress)
			throws EmailAlreadyExists_Exception, InvalidEmail_Exception,
			InvalidUser_Exception, UserAlreadyExists_Exception {
		// TODO Auto-generated method stub
		
		/*check if the email is valid */
		if(emailAddress.contains("@")){
			String[] emailParts = emailAddress.split("@");
			int partsSize = emailParts.length;
			
			if (partsSize != 2){
				InvalidEmail invalidEmailAddress = new InvalidEmail();
				invalidEmailAddress.setEmailAddress(emailAddress);
				throw new InvalidEmail_Exception("Invalid Email, format is a@b\n", invalidEmailAddress);	
			}
			
			if (emailParts[0].equals("")) {
				InvalidEmail invalidEmailAddress = new InvalidEmail();
				invalidEmailAddress.setEmailAddress(emailAddress);
				throw new InvalidEmail_Exception("Invalid Email, format is a@b\n", invalidEmailAddress);
			}
		
		}else{
			InvalidEmail invalidEmailAddress = new InvalidEmail();
			invalidEmailAddress.setEmailAddress(emailAddress);
			throw new InvalidEmail_Exception("Invalid Email, format is a@b\n", invalidEmailAddress);

		}
		
		
		/*check if the user is valid*/
		if(userId.equals(" ") || userId.equals("")){
			InvalidUser invalidUser = new InvalidUser();
			invalidUser.setUserId(userId);
			throw new InvalidUser_Exception("Invalid user, user must not contain spaces or be empty", invalidUser);
		}
		
		/*check if the user or email already exist*/
		for (CreateUser users:registedUsers.keySet()){
			if(users.getUserId().equals(userId) || users.getEmailAddress().equals(emailAddress)){
				if(users.getUserId().equals(userId)){
					UserAlreadyExists alreadyExistsUser = new UserAlreadyExists();
					alreadyExistsUser.setUserId(userId);
					throw new UserAlreadyExists_Exception("User already exists\n", alreadyExistsUser);
				}else{
					if(users.getEmailAddress().equals(emailAddress)){
						EmailAlreadyExists alreadyExistsEmail = new EmailAlreadyExists();
						alreadyExistsEmail.setEmailAddress(emailAddress);
						throw new EmailAlreadyExists_Exception("Email already exists\n", alreadyExistsEmail);
					}
				}
			}
		}
		
		/* if nothing wrong, finally creates the user */
		CreateUser newUser = new CreateUser();
		newUser.setUserId(userId);
		newUser.setEmailAddress(emailAddress);
		/*generates a random password for the user*/
		PassGenerator newPass = new PassGenerator();
		newPass.setPassword();
		String newUserPass = newPass.getPassword();
		/*registers the user in the server data*/
		registedUsers.put(newUser, newUserPass);
		usersNonces.put(userId, 0);  /*added for part 2 of the project*/
		System.out.printf("The password for the user %s is %s\n", userId, newUserPass);
	}

	public void renewPassword(String userId) throws UserDoesNotExist_Exception {
		// TODO Auto-generated method stub
		/*checks if the user exists*/
		for (CreateUser users:registedUsers.keySet()){
			if(users.getUserId().equals(userId)){
				PassGenerator newPass = new PassGenerator();
				newPass.setPassword();
				String userNewPass = newPass.getPassword();
				registedUsers.put(users, userNewPass);
				System.out.printf("The new password is for the user %s is %s.\n", userId, userNewPass);
				return;
			}
		}
		
		/*if the user doesnt exist, throws exception*/
		UserDoesNotExist nonexistantUser = new UserDoesNotExist();
		nonexistantUser.setUserId(userId);
		throw new UserDoesNotExist_Exception("User does not exist\n", nonexistantUser);
	}

	public void removeUser(String userId) throws UserDoesNotExist_Exception {
		// TODO Auto-generated method stub
		for(CreateUser users:registedUsers.keySet()){
			if(users.getUserId().equals(userId)){
				registedUsers.remove(users);
				return;	
			}
		}
		
		/*if the user doesnt exist, throws exception*/
		UserDoesNotExist nonexistantUser = new UserDoesNotExist();
		nonexistantUser.setUserId(userId);
		throw new UserDoesNotExist_Exception("User does not exist\n", nonexistantUser);
	}

	
	
	public byte[] requestAuthentication(String userId, byte[] reserved)
			throws AuthReqFailed_Exception {
		// TODO Auto-generated method stub
		
		
		
		Document xmlDocument = null;
		String userPass = null;
		byte[] userPassByte = null;
		int userNonce = 0;
		String userService = null;
		
		if(reserved.equals(null)){
			AuthReqFailed authFailed = new AuthReqFailed();
			authFailed.setReserved(reserved);
			throw new AuthReqFailed_Exception("Authentication Failed, invalid arguments.\n", authFailed);
			
		}else{
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    factory.setNamespaceAware(true);
		    DocumentBuilder builder = null;
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				xmlDocument = builder.parse(new ByteArrayInputStream(reserved));
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    Node header = xmlDocument.getElementsByTagName("header").item(0);
	        NodeList xmlAttributes = header.getChildNodes();
	        for (int i = 0; i < xmlAttributes.getLength(); i++) {
	        	 
                   Node node = xmlAttributes.item(i);
 
                   if ("service".equals(node.getNodeName())) {
                	   userService = node.getTextContent();
                   }
 
                   if ("nonce".equals(node.getNodeName())) {
                	   userNonce = Integer.parseInt(node.getTextContent());
                   }
	        }
		}
		
		
		
		for(CreateUser users:registedUsers.keySet()){
			if (users.getUserId().equals(userId)){
				/*if((registedUsers.get(users)).equals(userPass)){*/
					if((usersNonces.get(userId))>userNonce || !(userService.equals(storeServiceName))){
						AuthReqFailed authFailed = new AuthReqFailed();
						authFailed.setReserved(reserved);
						throw new AuthReqFailed_Exception("Authentication Failed\n", authFailed);
					}else{
						usersNonces.put(userId,(usersNonces.get(userId)+1)); /*updating user nonce on server side*/
						userPass = registedUsers.get(users);
						userPassByte = userPass.getBytes();
						System.out.printf("Authentication Successful\n");
						
						/*******creating the ticket and all the encrypted data to return*******/
						
						/*streams to store and bytes of a converted object*/
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						ObjectOutput out = null;
						
						/*creating the ticket to send to the client*/
						KerberosTicket serviceTicket = new KerberosTicket(userId, "SdStore");
						String serviceKeyCli = serviceTicket.getServiceKey();
						System.out.printf("Service Key is - %s\n", serviceKeyCli); /*test purposes*/
						byte[] storeServiceKeyByte = storeServiceKeyString.getBytes();
						
						/*creating the byte array of the data to the client*/
						byte[] serviceKeyCliByte = serviceKeyCli.getBytes();
						byte[] userNonceByte = ByteBuffer.allocate(4).putInt(userNonce).array();
						byte[] dataCli = new byte[serviceKeyCliByte.length + userNonceByte.length];
						System.arraycopy(serviceKeyCliByte, 0, dataCli, 0, serviceKeyCliByte.length);
						System.arraycopy(userNonceByte, 0, dataCli, serviceKeyCliByte.length, userNonceByte.length);
						
						
						/*digesting the keys to create keys long enough for use on AES algorithm*/
						MessageDigest sha;
						try {
							sha = MessageDigest.getInstance("SHA-1");
							storeServiceKeyByte = sha.digest(storeServiceKeyByte);
							storeServiceKeyByte = Arrays.copyOf(storeServiceKeyByte, 16);
							userPassByte = sha.digest(userPassByte);
							userPassByte = Arrays.copyOf(userPassByte, 16);
						} catch (NoSuchAlgorithmException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						/*converting the kerberos ticket to byte array for further encryption*/
						try {
							  out = new ObjectOutputStream(bos);   
							  out.writeObject(serviceTicket);						  
						} catch (IOException ex) {
							    // ignore close exception
						}
						
						byte[] ticketToEncrypt = bos.toByteArray();
						
						/*attempts to cipher the ticket byte array and the data for the client using AES algorithm*/
						try {
							Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
							Cipher cipherCli = Cipher.getInstance("AES/ECB/PKCS5Padding");
							SecretKeySpec skey = new SecretKeySpec(storeServiceKeyByte, "AES");
							SecretKeySpec skeyCli = new SecretKeySpec(userPassByte, "AES");
							try {
								cipher.init(Cipher.ENCRYPT_MODE, skey);
								byte[] encriptedTicket = cipher.doFinal(ticketToEncrypt);
								String encriptedTicketString = printBase64Binary(encriptedTicket);
								cipherCli.init(Cipher.ENCRYPT_MODE, skeyCli);
								byte[] encriptedDataCli = cipherCli.doFinal(dataCli);
								String encriptedStringCli = printBase64Binary(encriptedDataCli);
								byte[] authSuccess = new AuthResponse(encriptedStringCli, encriptedTicketString).getResponse();
		
								return authSuccess;
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
					}
					
					
				/*}else{
					AuthReqFailed authFailed = new AuthReqFailed();
					authFailed.setReserved(reserved);
					throw new AuthReqFailed_Exception("Incorrect password\n", authFailed);
				}*/
			}
		
		}
		
		AuthReqFailed authFailed = new AuthReqFailed();
		authFailed.setReserved(reserved);
		throw new AuthReqFailed_Exception("User does not exist\n", authFailed);
		
	}

}
