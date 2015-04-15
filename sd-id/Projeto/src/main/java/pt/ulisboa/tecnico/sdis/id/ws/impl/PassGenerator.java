package pt.ulisboa.tecnico.sdis.id.ws.impl;

import java.util.UUID;

/*random alphanumeric password generator.
 * algorithm found in http://stackoverflow.com/questions/18069434/generating-alphanumeric-random-string-in-java
 */
public class PassGenerator {
	
	private String password;
	
	public String newPass(){
		StringBuffer buffer = new StringBuffer();
		int size = 6;  /* size of the password */
		
		while(buffer.length() < size){
			buffer.append(uuidString());
		}
		return buffer.substring(0, size);
	}
	
	public String uuidString() {
	    return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public void setPassword(){
		password = newPass();
	}
	
	public String getPassword(){
		return password;
	}
}
