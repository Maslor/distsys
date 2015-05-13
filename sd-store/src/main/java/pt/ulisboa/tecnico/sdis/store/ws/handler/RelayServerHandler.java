package pt.ulisboa.tecnico.sdis.store.ws.handler;
import java.util.*;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.*;
import javax.xml.ws.handler.MessageContext.Scope;
import javax.xml.ws.handler.soap.*;


/**
 *  This is the handler server class of the Relay example.
 *
 *  #4 The server handler receives data from the client handler (via inbound SOAP message header).
 *  #5 The server handler passes data to the server (via message context).
 *
 *  *** GO TO server class to see what happens next! ***
 *
 *  #8 The server class receives data from the server (via message context).
 *  #9 The server handler passes data to the client handler (via outbound SOAP message header).
 *
 *  *** GO BACK TO client handler to see what happens next! ***
 */

public class RelayServerHandler implements SOAPHandler<SOAPMessageContext> {

    public static final String REQUEST_PROPERTY = "my.request.property";
    public static final String RESPONSE_PROPERTY = "my.response.property";

    public static final String REQUEST_HEADER = "myRequestHeader";
    public static final String REQUEST_NS = "urn:example";

    public static final String RESPONSE_HEADER = "myResponseHeader";
    public static final String RESPONSE_NS = REQUEST_NS;

    public static final String CLASS_NAME = RelayServerHandler.class.getSimpleName();
    public static final String TOKEN = "server-handler";


    public boolean handleMessage(SOAPMessageContext smc) {
        Boolean outbound = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outbound) {
            // outbound message

            // *** #8 ***
            // get token from response context
            String propertyValue = (String) smc.get(RESPONSE_PROPERTY);
            System.out.printf("%s received '%s'%n", CLASS_NAME, propertyValue);

            // put token in response SOAP header
            try {
                // get SOAP envelope
                SOAPMessage msg = smc.getMessage();
                SOAPPart sp = msg.getSOAPPart();
                SOAPEnvelope se = sp.getEnvelope();

                // add header
                SOAPHeader sh = se.getHeader();
                if (sh == null)
                    sh = se.addHeader();

                // add header element (name, namespace prefix, namespace)
                Name name = se.createName(RESPONSE_HEADER, "e", RESPONSE_NS);
                SOAPHeaderElement element = sh.addHeaderElement(name);

                // *** #9 ***
                // add header element value
                String newValue = propertyValue + "," + TOKEN;
                element.addTextNode(newValue);

                System.out.printf("%s put token '%s' on response message header%n", CLASS_NAME, TOKEN);

            } catch (SOAPException e) {
                System.out.printf("Failed to add SOAP header because of %s%n", e);
            }


        } else {
            // inbound message

            // get token from request SOAP header
            try {
                // get SOAP envelope header
                SOAPMessage msg = smc.getMessage();
                SOAPPart sp = msg.getSOAPPart();
                SOAPEnvelope se = sp.getEnvelope();
                SOAPHeader sh = se.getHeader();

                // check header
                if (sh == null) {
                    System.out.println("Header not found.");
                    return true;
                }

                // get first header element
                Name name = se.createName(REQUEST_HEADER, "e", REQUEST_NS);
                Iterator it = sh.getChildElements(name);
                // check header element
                if (!it.hasNext()) {
                    System.out.printf("Header element %s not found.%n", REQUEST_HEADER);
                    return true;
                }
                SOAPElement element = (SOAPElement) it.next();

                // *** #4 ***
                // get header element value
                String headerValue = element.getValue();
                System.out.printf("%s got '%s'%n", CLASS_NAME, headerValue);

                // *** #5 ***
                // put token in request context
                String newValue = headerValue + "," + TOKEN;
                System.out.printf("%s put token '%s' on request context%n", CLASS_NAME, TOKEN);
                smc.put(REQUEST_PROPERTY, newValue);
                // set property scope to application so that server class can access property
                smc.setScope(REQUEST_PROPERTY, Scope.APPLICATION);

            } catch (SOAPException e) {
                System.out.printf("Failed to get SOAP header because of %s%n", e);
            }

        }

        return true;
    }

    public boolean handleFault(SOAPMessageContext smc) {
        return true;
    }

    public Set<QName> getHeaders() {
        return null;
    }

    public void close(MessageContext messageContext) {
    }

}
