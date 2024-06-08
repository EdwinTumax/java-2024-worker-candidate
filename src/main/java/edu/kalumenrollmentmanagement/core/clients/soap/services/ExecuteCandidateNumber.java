
package edu.kalumenrollmentmanagement.core.clients.soap.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para executeCandidateNumber complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="executeCandidateNumber"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arg0" type="{http://beans.core.enrollmentprocess.kalum.edu/}candidateGenerateNumberRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "executeCandidateNumber", propOrder = {
    "arg0"
})
public class ExecuteCandidateNumber {

    protected CandidateGenerateNumberRequest arg0;

    /**
     * Obtiene el valor de la propiedad arg0.
     * 
     * @return
     *     possible object is
     *     {@link CandidateGenerateNumberRequest }
     *     
     */
    public CandidateGenerateNumberRequest getArg0() {
        return arg0;
    }

    /**
     * Define el valor de la propiedad arg0.
     * 
     * @param value
     *     allowed object is
     *     {@link CandidateGenerateNumberRequest }
     *     
     */
    public void setArg0(CandidateGenerateNumberRequest value) {
        this.arg0 = value;
    }

}
