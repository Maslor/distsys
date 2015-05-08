package pt.ulisboa.tecnico.sdis.id.ws.impl;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ReservedConverter {
		
	private String userPass;
	private String service;
	private int nonce;
	
	public ReservedConverter(byte[] reserved){
		
		byte[] passByte = Arrays.copyOfRange(reserved, 0, 6);
		byte[] nonceByte = Arrays.copyOfRange(reserved, 6, reserved.length );
		nonce = ByteBuffer.wrap(nonceByte).getInt();
		String password = new String(passByte);
		userPass = password;
	}
	
	
	public String getUserPass(){
		return userPass;
	}
	
	
	public String getService(){
		return service;
	}
	
	
	public int getNonce(){
		return nonce;
	}
}
