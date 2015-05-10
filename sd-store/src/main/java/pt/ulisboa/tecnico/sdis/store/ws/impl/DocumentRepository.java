package pt.ulisboa.tecnico.sdis.store.ws.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DocumentRepository {
    HashMap<String, Document> documents;

    public DocumentRepository() {
        documents = new HashMap<String, Document>();
    }

    public boolean addNewDocument(String docId) {
        if (documents.get(docId) != null)
            return false;
        documents.put(docId, new Document());
        return true;
    }

    public Document getDocument(String docId) {
        return documents.get(docId);
    }

    public int getTotalSize() {
        return 0;
    }

    public int getTotalSizeWithoutDocument(String docId) {
        return 0;
    }

    public List<String> listDocs(String userId) {
        return new ArrayList<String>(documents.keySet());
    }
}
