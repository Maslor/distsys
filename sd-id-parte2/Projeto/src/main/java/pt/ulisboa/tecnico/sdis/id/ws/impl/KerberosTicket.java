package pt.ulisboa.tecnico.sdis.id.ws.impl;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;





public class KerberosTicket {

	private String source;
	private String destination;
	private Calendar initTime;
	private Calendar expTime;
	private String serviceKey;
	
	
	public KerberosTicket(String client, String service){
		source = client;
		destination = service;
        initTime = Calendar.getInstance();
        expTime = Calendar.getInstance();
        expTime.add(Calendar.MINUTE, 30);
		PassGenerator newKey = new PassGenerator();
		newKey.setPassword();
		serviceKey = newKey.getPassword();
		
	}
	
	
	public String getSource(){
		return source;
	}
	
	
	public String getDestination(){
		return destination;
	}
	
	
	public String getServiceKey(){
		return serviceKey;
	}
	
	
	public Calendar getInitTime(){
		return initTime;
	}
	
	
	public Calendar getExpTime(){
		return expTime;
	}
	
	
	
}
