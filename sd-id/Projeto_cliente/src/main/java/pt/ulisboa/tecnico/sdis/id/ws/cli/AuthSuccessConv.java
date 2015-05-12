package pt.ulisboa.tecnico.sdis.id.ws.cli;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AuthSuccessConv {
	
	private String dataCli;
	private String ticket;
	
	public AuthSuccessConv(byte[] authSucc){
		
		Document xmlDocument = null;
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
			xmlDocument = builder.parse(new ByteArrayInputStream(authSucc));
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

               if ("cliData".equals(node.getNodeName())) {
            	   dataCli = node.getTextContent();
               }

               if ("ticket".equals(node.getNodeName())) {
            	   ticket = node.getTextContent();
               }
        }
		
	}
	
	
	public String getDataCli(){
		return dataCli;
	}
	
	public String getTicket(){
		return ticket;
	}
	
}
