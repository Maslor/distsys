
package pt.ulisboa.tecnico.sdis.id.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pt.ulisboa.tecnico.sdis.id.ws package. 
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

    private final static QName _RequestAuthenticationResponse_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:id:ws", "requestAuthenticationResponse");
    private final static QName _RequestAuthentication_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:id:ws", "requestAuthentication");
    private final static QName _AuthReqFailed_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:id:ws", "AuthReqFailed");
    private final static QName _InvalidUser_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:id:ws", "InvalidUser");
    private final static QName _CreateUser_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:id:ws", "createUser");
    private final static QName _EmailAlreadyExists_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:id:ws", "EmailAlreadyExists");
    private final static QName _RemoveUser_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:id:ws", "removeUser");
    private final static QName _RemoveUserResponse_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:id:ws", "removeUserResponse");
    private final static QName _RenewPasswordResponse_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:id:ws", "renewPasswordResponse");
    private final static QName _RenewPassword_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:id:ws", "renewPassword");
    private final static QName _CreateUserResponse_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:id:ws", "createUserResponse");
    private final static QName _UserDoesNotExist_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:id:ws", "UserDoesNotExist");
    private final static QName _UserAlreadyExists_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:id:ws", "UserAlreadyExists");
    private final static QName _InvalidEmail_QNAME = new QName("urn:pt:ulisboa:tecnico:sdis:id:ws", "InvalidEmail");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pt.ulisboa.tecnico.sdis.id.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UserAlreadyExists }
     * 
     */
    public UserAlreadyExists createUserAlreadyExists() {
        return new UserAlreadyExists();
    }

    /**
     * Create an instance of {@link InvalidEmail }
     * 
     */
    public InvalidEmail createInvalidEmail() {
        return new InvalidEmail();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link UserDoesNotExist }
     * 
     */
    public UserDoesNotExist createUserDoesNotExist() {
        return new UserDoesNotExist();
    }

    /**
     * Create an instance of {@link RenewPassword }
     * 
     */
    public RenewPassword createRenewPassword() {
        return new RenewPassword();
    }

    /**
     * Create an instance of {@link RenewPasswordResponse }
     * 
     */
    public RenewPasswordResponse createRenewPasswordResponse() {
        return new RenewPasswordResponse();
    }

    /**
     * Create an instance of {@link RemoveUserResponse }
     * 
     */
    public RemoveUserResponse createRemoveUserResponse() {
        return new RemoveUserResponse();
    }

    /**
     * Create an instance of {@link RemoveUser }
     * 
     */
    public RemoveUser createRemoveUser() {
        return new RemoveUser();
    }

    /**
     * Create an instance of {@link InvalidUser }
     * 
     */
    public InvalidUser createInvalidUser() {
        return new InvalidUser();
    }

    /**
     * Create an instance of {@link RequestAuthentication }
     * 
     */
    public RequestAuthentication createRequestAuthentication() {
        return new RequestAuthentication();
    }

    /**
     * Create an instance of {@link AuthReqFailed }
     * 
     */
    public AuthReqFailed createAuthReqFailed() {
        return new AuthReqFailed();
    }

    /**
     * Create an instance of {@link CreateUser }
     * 
     */
    public CreateUser createCreateUser() {
        return new CreateUser();
    }

    /**
     * Create an instance of {@link EmailAlreadyExists }
     * 
     */
    public EmailAlreadyExists createEmailAlreadyExists() {
        return new EmailAlreadyExists();
    }

    /**
     * Create an instance of {@link RequestAuthenticationResponse }
     * 
     */
    public RequestAuthenticationResponse createRequestAuthenticationResponse() {
        return new RequestAuthenticationResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestAuthenticationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:id:ws", name = "requestAuthenticationResponse")
    public JAXBElement<RequestAuthenticationResponse> createRequestAuthenticationResponse(RequestAuthenticationResponse value) {
        return new JAXBElement<RequestAuthenticationResponse>(_RequestAuthenticationResponse_QNAME, RequestAuthenticationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestAuthentication }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:id:ws", name = "requestAuthentication")
    public JAXBElement<RequestAuthentication> createRequestAuthentication(RequestAuthentication value) {
        return new JAXBElement<RequestAuthentication>(_RequestAuthentication_QNAME, RequestAuthentication.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthReqFailed }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:id:ws", name = "AuthReqFailed")
    public JAXBElement<AuthReqFailed> createAuthReqFailed(AuthReqFailed value) {
        return new JAXBElement<AuthReqFailed>(_AuthReqFailed_QNAME, AuthReqFailed.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:id:ws", name = "InvalidUser")
    public JAXBElement<InvalidUser> createInvalidUser(InvalidUser value) {
        return new JAXBElement<InvalidUser>(_InvalidUser_QNAME, InvalidUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:id:ws", name = "createUser")
    public JAXBElement<CreateUser> createCreateUser(CreateUser value) {
        return new JAXBElement<CreateUser>(_CreateUser_QNAME, CreateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailAlreadyExists }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:id:ws", name = "EmailAlreadyExists")
    public JAXBElement<EmailAlreadyExists> createEmailAlreadyExists(EmailAlreadyExists value) {
        return new JAXBElement<EmailAlreadyExists>(_EmailAlreadyExists_QNAME, EmailAlreadyExists.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:id:ws", name = "removeUser")
    public JAXBElement<RemoveUser> createRemoveUser(RemoveUser value) {
        return new JAXBElement<RemoveUser>(_RemoveUser_QNAME, RemoveUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:id:ws", name = "removeUserResponse")
    public JAXBElement<RemoveUserResponse> createRemoveUserResponse(RemoveUserResponse value) {
        return new JAXBElement<RemoveUserResponse>(_RemoveUserResponse_QNAME, RemoveUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RenewPasswordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:id:ws", name = "renewPasswordResponse")
    public JAXBElement<RenewPasswordResponse> createRenewPasswordResponse(RenewPasswordResponse value) {
        return new JAXBElement<RenewPasswordResponse>(_RenewPasswordResponse_QNAME, RenewPasswordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RenewPassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:id:ws", name = "renewPassword")
    public JAXBElement<RenewPassword> createRenewPassword(RenewPassword value) {
        return new JAXBElement<RenewPassword>(_RenewPassword_QNAME, RenewPassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:id:ws", name = "createUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserDoesNotExist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:id:ws", name = "UserDoesNotExist")
    public JAXBElement<UserDoesNotExist> createUserDoesNotExist(UserDoesNotExist value) {
        return new JAXBElement<UserDoesNotExist>(_UserDoesNotExist_QNAME, UserDoesNotExist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserAlreadyExists }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:id:ws", name = "UserAlreadyExists")
    public JAXBElement<UserAlreadyExists> createUserAlreadyExists(UserAlreadyExists value) {
        return new JAXBElement<UserAlreadyExists>(_UserAlreadyExists_QNAME, UserAlreadyExists.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidEmail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:ulisboa:tecnico:sdis:id:ws", name = "InvalidEmail")
    public JAXBElement<InvalidEmail> createInvalidEmail(InvalidEmail value) {
        return new JAXBElement<InvalidEmail>(_InvalidEmail_QNAME, InvalidEmail.class, null, value);
    }

}
