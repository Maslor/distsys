package pt.ulisboa.tecnico.sdis.id.ws.impl;

import javax.jws.*;
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
		
		}else{
			InvalidEmail invalidEmailAddress = new InvalidEmail();
			invalidEmailAddress.setEmailAddress(emailAddress);
			throw new InvalidEmail_Exception("Invalid Email, format is a@b\n", invalidEmailAddress);

		}
		
		
		/*check if the user is valid*/
		
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
		return null;
	}

}
