
package pt.ulisboa.tecnico.sdis.store.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pt.ulisboa.tecnico.sdis.store.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Store_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:store:ws", "store");
    private final static QName _CreateDocResponse_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:store:ws", "createDocResponse");
    private final static QName _StoreResponse_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:store:ws", "storeResponse");
    private final static QName _UserAlreadyExists_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:store:ws", "userAlreadyExists");
    private final static QName _UserDoesNotExist_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:store:ws", "userDoesNotExist");
    private final static QName _ListDocsResponse_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:store:ws", "listDocsResponse");
    private final static QName _CapacityExceeded_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:store:ws", "CapacityExceeded");
    private final static QName _LoadResponse_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:store:ws", "loadResponse");
    private final static QName _DocDoesNotExist_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:store:ws", "docDoesNotExist");
    private final static QName _DocAlreadyExists_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:store:ws", "docAlreadyExists");
    private final static QName _CreateDoc_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:store:ws", "createDoc");
    private final static QName _ListDocs_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:store:ws", "listDocs");
    private final static QName _Load_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:store:ws", "load");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pt.ulisboa.tecnico.sdis.store.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListDocsResponse }
     * 
     */
    public ListDocsResponse createListDocsResponse() {
        return new ListDocsResponse();
    }

    /**
     * Create an instance of {@link UserDoesNotExist }
     * 
     */
    public UserDoesNotExist createUserDoesNotExist() {
        return new UserDoesNotExist();
    }

    /**
     * Create an instance of {@link CapacityExceeded }
     * 
     */
    public CapacityExceeded createCapacityExceeded() {
        return new CapacityExceeded();
    }

    /**
     * Create an instance of {@link CreateDocResponse }
     * 
     */
    public CreateDocResponse createCreateDocResponse() {
        return new CreateDocResponse();
    }

    /**
     * Create an instance of {@link StoreResponse }
     * 
     */
    public StoreResponse createStoreResponse() {
        return new StoreResponse();
    }

    /**
     * Create an instance of {@link Store }
     * 
     */
    public Store createStore() {
        return new Store();
    }

    /**
     * Create an instance of {@link UserAlreadyExists }
     * 
     */
    public UserAlreadyExists createUserAlreadyExists() {
        return new UserAlreadyExists();
    }

    /**
     * Create an instance of {@link ListDocs }
     * 
     */
    public ListDocs createListDocs() {
        return new ListDocs();
    }

    /**
     * Create an instance of {@link Load }
     * 
     */
    public Load createLoad() {
        return new Load();
    }

    /**
     * Create an instance of {@link CreateDoc }
     * 
     */
    public CreateDoc createCreateDoc() {
        return new CreateDoc();
    }

    /**
     * Create an instance of {@link DocAlreadyExists }
     * 
     */
    public DocAlreadyExists createDocAlreadyExists() {
        return new DocAlreadyExists();
    }

    /**
     * Create an instance of {@link DocDoesNotExist }
     * 
     */
    public DocDoesNotExist createDocDoesNotExist() {
        return new DocDoesNotExist();
    }

    /**
     * Create an instance of {@link LoadResponse }
     * 
     */
    public LoadResponse createLoadResponse() {
        return new LoadResponse();
    }

    /**
     * Create an instance of {@link DocUserPair }
     * 
     */
    public DocUserPair createDocUserPair() {
        return new DocUserPair();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Store }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:store:ws", name = "store")
    public JAXBElement<Store> createStore(Store value) {
        return new JAXBElement<Store>(_Store_QNAME, Store.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateDocResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:store:ws", name = "createDocResponse")
    public JAXBElement<CreateDocResponse> createCreateDocResponse(CreateDocResponse value) {
        return new JAXBElement<CreateDocResponse>(_CreateDocResponse_QNAME, CreateDocResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StoreResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:store:ws", name = "storeResponse")
    public JAXBElement<StoreResponse> createStoreResponse(StoreResponse value) {
        return new JAXBElement<StoreResponse>(_StoreResponse_QNAME, StoreResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserAlreadyExists }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:store:ws", name = "userAlreadyExists")
    public JAXBElement<UserAlreadyExists> createUserAlreadyExists(UserAlreadyExists value) {
        return new JAXBElement<UserAlreadyExists>(_UserAlreadyExists_QNAME, UserAlreadyExists.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserDoesNotExist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:store:ws", name = "userDoesNotExist")
    public JAXBElement<UserDoesNotExist> createUserDoesNotExist(UserDoesNotExist value) {
        return new JAXBElement<UserDoesNotExist>(_UserDoesNotExist_QNAME, UserDoesNotExist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListDocsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:store:ws", name = "listDocsResponse")
    public JAXBElement<ListDocsResponse> createListDocsResponse(ListDocsResponse value) {
        return new JAXBElement<ListDocsResponse>(_ListDocsResponse_QNAME, ListDocsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CapacityExceeded }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:store:ws", name = "CapacityExceeded")
    public JAXBElement<CapacityExceeded> createCapacityExceeded(CapacityExceeded value) {
        return new JAXBElement<CapacityExceeded>(_CapacityExceeded_QNAME, CapacityExceeded.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:store:ws", name = "loadResponse")
    public JAXBElement<LoadResponse> createLoadResponse(LoadResponse value) {
        return new JAXBElement<LoadResponse>(_LoadResponse_QNAME, LoadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocDoesNotExist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:store:ws", name = "docDoesNotExist")
    public JAXBElement<DocDoesNotExist> createDocDoesNotExist(DocDoesNotExist value) {
        return new JAXBElement<DocDoesNotExist>(_DocDoesNotExist_QNAME, DocDoesNotExist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocAlreadyExists }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:store:ws", name = "docAlreadyExists")
    public JAXBElement<DocAlreadyExists> createDocAlreadyExists(DocAlreadyExists value) {
        return new JAXBElement<DocAlreadyExists>(_DocAlreadyExists_QNAME, DocAlreadyExists.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateDoc }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:store:ws", name = "createDoc")
    public JAXBElement<CreateDoc> createCreateDoc(CreateDoc value) {
        return new JAXBElement<CreateDoc>(_CreateDoc_QNAME, CreateDoc.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListDocs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:store:ws", name = "listDocs")
    public JAXBElement<ListDocs> createListDocs(ListDocs value) {
        return new JAXBElement<ListDocs>(_ListDocs_QNAME, ListDocs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Load }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:store:ws", name = "load")
    public JAXBElement<Load> createLoad(Load value) {
        return new JAXBElement<Load>(_Load_QNAME, Load.class, null, value);
    }

}
