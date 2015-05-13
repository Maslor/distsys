package pt.ulisboa.tecnico.sdis.store.ws.impl;

import javax.xml.ws.Endpoint;

import example.ws.uddi.UDDINaming;

public class StoreMain {

    public static void main(String[] args) {
        // Check arguments
        if (args.length == 0 || args.length == 2) {
            System.err.println("Argument(s) missing!");
            System.err.println("Usage: java " + StoreMain.class.getName()
                    + " wsURL OR uddiURL wsName wsURL");
            return;
        }
        String uddiURL = null;
        String wsName = null;
        String wsURL = null;
        String wsURL2 = null;
        String wsURL3 = null;
        String wsURL4 = null;
        if (args.length == 1) {
            wsURL = args[0];
        } else if (args.length >= 3) {
            uddiURL = args[0];
            wsName = args[1];
            wsURL = args[2];
            wsURL2 = args[3];
            wsURL3 = args[4];
            wsURL4 = args[5];
        }
        String wsTestUrl = wsURL + "/test";

        Endpoint endpoint = null;
        Endpoint endpoint2 = null;
        Endpoint endpoint3 = null;
        Endpoint endpoint4 = null;
        Endpoint testEndpoint = null;

        UDDINaming uddiNaming = null;
        UDDINaming uddiNaming2 = null;
        UDDINaming uddiNaming3 = null;
        UDDINaming uddiNaming4 = null;

        try {
            StoreImpl impl = new StoreImpl();
            StoreImpl impl2 = new StoreImpl();
            StoreImpl impl3 = new StoreImpl();
            StoreImpl impl4 = new StoreImpl();
            
            
            if (System.getProperty("store-ws.test") != null) {
                System.out.println("Populating test data...");
            }
            endpoint = Endpoint.create(impl);
            endpoint2 = Endpoint.create(impl);
            endpoint3 = Endpoint.create(impl);
            endpoint4 = Endpoint.create(impl);

            // publish endpoint
            System.out.printf("Starting %s%n", wsURL);
            endpoint.publish(wsURL);
            endpoint2.publish(wsURL2);
            endpoint3.publish(wsURL3);
            endpoint4.publish(wsURL4);

            // publish to UDDI
            if (uddiURL != null) {
                System.out.printf("Publishing '%s' to UDDI at %s%n", wsName,
                        uddiURL);
                uddiNaming = new UDDINaming(uddiURL);
                uddiNaming.rebind(wsName, wsURL);
                uddiNaming2 = new UDDINaming(uddiURL);
                uddiNaming2.rebind(wsName, wsURL2);
                uddiNaming3 = new UDDINaming(uddiURL);
                uddiNaming3.rebind(wsName, wsURL3);
                uddiNaming4 = new UDDINaming(uddiURL);
                uddiNaming4.rebind(wsName, wsURL4);
            }

            if ("true".equalsIgnoreCase(System.getProperty("ws.test"))) {
                impl.reset();
                
                System.out.printf("Starting %s%n", wsTestUrl);
                testEndpoint = Endpoint.create(new TestControl());
                testEndpoint.publish(wsTestUrl);
            }

            // wait
            System.out.println("Awaiting connections");
            System.out.println("Press enter to shutdown");
            System.in.read();

        } catch (Exception e) {
            System.out.printf("Caught exception: %s%n", e);
            e.printStackTrace();

        } finally {
            try {
                if (endpoint != null) {
                    // stop endpoint
                    endpoint.stop();
                    System.out.printf("Stopped %s%n", wsURL);
                }
                if (testEndpoint != null) {
                    // stop test endpoint
                    testEndpoint.stop();
                    System.out.printf("Stopped %s%n", wsTestUrl);
                }
            } catch (Exception e) {
                System.out.printf("Caught exception when stopping: %s%n", e);
            }
            try {
                if (uddiNaming != null) {
                    // delete from UDDI
                    uddiNaming.unbind(wsName);
                    System.out.printf("Deleted '%s' from UDDI%n", wsName);
                }
            } catch (Exception e) {
                System.out.printf("Caught exception when deleting: %s%n", e);
            }
        }

    }

}
