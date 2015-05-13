package pt.ulisboa.tecnico.sdis.id.ws.impl;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ReservedConverter {
		
	private String userPass;
	private String service;
	private int nonce;
	
	public ReservedConverter(byte[] reserved){
		
		byte[] nonceByte = Arrays.copyOfRange(reserved, 0, 4);
		byte[] servByte = Arrays.copyOfRange(reserved, 4, reserved.length);
		nonce = ByteBuffer.wrap(nonceByte).getInt();
		String serv = new String(servByte);
		service = serv;
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
