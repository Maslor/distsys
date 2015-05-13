
package pt.ulisboa.tecnico.sdis.store.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for store complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="store">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="docUserPair" type="{urn:pt:ulisboa:tecnico:sdis:store:ws}docUserPair"/>
 *         &lt;element name="contents" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "store", propOrder = {
    "docUserPair",
    "contents"
})
public class Store {

    @XmlElement(required = true)
    protected DocUserPair docUserPair;
    @XmlElement(required = true)
    protected byte[] contents;

    /**
     * Gets the value of the docUserPair property.
     * 
     * @return
     *     possible object is
     *     {@link DocUserPair }
     *     
     */
    public DocUserPair getDocUserPair() {
        return docUserPair;
    }

    /**
     * Sets the value of the docUserPair property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocUserPair }
     *     
     */
    public void setDocUserPair(DocUserPair value) {
        this.docUserPair = value;
    }

    /**
     * Gets the value of the contents property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getContents() {
        return contents;
    }

    /**
     * Sets the value of the contents property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setContents(byte[] value) {
        this.contents = value;
    }

}
