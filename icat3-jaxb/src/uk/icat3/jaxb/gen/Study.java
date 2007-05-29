//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-382 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.05.24 at 10:57:38 AM BST 
//


package uk.icat3.jaxb.gen;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 *  Study level group. 
 * 				Must contain the group investigation. 
 * 				All the others parameters are optional but if any is filled, a unique name must be provided. 
 * 				(HELP Can I specify that in the schema?)
 * 
 * <p>Java class for Study complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Study">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{}str255" minOccurs="0"/>
 *         &lt;element name="purpose" type="{}str4000" minOccurs="0"/>
 *         &lt;element name="status" type="{}Status" minOccurs="0"/>
 *         &lt;element name="related_material" type="{}str4000" minOccurs="0"/>
 *         &lt;element name="study_creation_date" type="{}timeStamp" minOccurs="0"/>
 *         &lt;element name="study_manager" type="{}Fedid" minOccurs="0"/>
 *         &lt;element name="investigation" type="{}Investigation" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Study", propOrder = {
    "name",
    "purpose",
    "status",
    "relatedMaterial",
    "studyCreationDate",
    "studyManager",
    "investigation"
})
public class Study {

    protected String name;
    protected String purpose;
    protected Status status;
    @XmlElement(name = "related_material")
    protected String relatedMaterial;
    @XmlElement(name = "study_creation_date")
    protected XMLGregorianCalendar studyCreationDate;
    @XmlElement(name = "study_manager")
    protected String studyManager;
    @XmlElement(required = true)
    protected List<Investigation> investigation;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the purpose property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * Sets the value of the purpose property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurpose(String value) {
        this.purpose = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Status }
     *     
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Status }
     *     
     */
    public void setStatus(Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the relatedMaterial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelatedMaterial() {
        return relatedMaterial;
    }

    /**
     * Sets the value of the relatedMaterial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelatedMaterial(String value) {
        this.relatedMaterial = value;
    }

    /**
     * Gets the value of the studyCreationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStudyCreationDate() {
        return studyCreationDate;
    }

    /**
     * Sets the value of the studyCreationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStudyCreationDate(XMLGregorianCalendar value) {
        this.studyCreationDate = value;
    }

    /**
     * Gets the value of the studyManager property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudyManager() {
        return studyManager;
    }

    /**
     * Sets the value of the studyManager property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudyManager(String value) {
        this.studyManager = value;
    }

    /**
     * Gets the value of the investigation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the investigation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvestigation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Investigation }
     * 
     * 
     */
    public List<Investigation> getInvestigation() {
        if (investigation == null) {
            investigation = new ArrayList<Investigation>();
        }
        return this.investigation;
    }

}