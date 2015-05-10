package pt.ulisboa.tecnico.sdis.store.ws.impl;

import javax.jws.WebService;

@WebService(targetNamespace = "urn:pt:ulisboa:tecnico:ws:test")
public class TestControl {

    public void reset() {
        System.out.println("Resetting data for tests...");
        StoreImpl.reset();
    }

    public void terminate() {
        System.out
                .println("Received shutdown command. Shutting the server down...");
        System.exit(0);
    }

}
