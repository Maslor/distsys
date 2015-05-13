package pt.ulisboa.tecnico.sdis.id.ws.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AuthResponse {

	private String cliData;
	private String ticket;
	private byte[] response;
	
	public AuthResponse(String dataString, String ticketString){
		cliData = dataString;
		ticket = ticketString; 
		String responsePath = "/Users/David/Documents/IST/SDis/Proj1/src/main/resources/AuthResponse.xml";
		Document xmlDocument = null;
		
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
			xmlDocument = docBuilder.parse(responsePath);
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

	   
               if ("cliData".equals(node.getNodeName())) {
            	   node.setTextContent(cliData);
               }

               
               if ("ticket".equals(node.getNodeName())) {
            	   node.setTextContent(ticket);
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
		StreamResult resultXML = new StreamResult(new File(responsePath));
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
		response=bos.toByteArray();
		
	}
	
	public byte[] getResponse(){
		return response;
	}
	
}
