
package edu.kalumenrollmentmanagement.core.clients.soap.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para executeCandidateNumberResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="executeCandidateNumberResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://beans.core.enrollmentprocess.kalum.edu/}candidateGenerateNumberResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "executeCandidateNumberResponse", propOrder = {
    "_return"
})
public class ExecuteCandidateNumberResponse {

    @XmlElement(name = "return")
    protected CandidateGenerateNumberResponse _return;

    /**
     * Obtiene el valor de la propiedad return.
     * 
     * @return
     *     possible object is
     *     {@link CandidateGenerateNumberResponse }
     *     
     */
    public CandidateGenerateNumberResponse getReturn() {
        return _return;
    }

    /**
     * Define el valor de la propiedad return.
     * 
     * @param value
     *     allowed object is
     *     {@link CandidateGenerateNumberResponse }
     *     
     */
    public void setReturn(CandidateGenerateNumberResponse value) {
        this._return = value;
    }

}
