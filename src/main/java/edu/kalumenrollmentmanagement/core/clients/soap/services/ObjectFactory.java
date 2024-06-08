
package edu.kalumenrollmentmanagement.core.clients.soap.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.kalumenrollmentmanagement.core.clients.soap.services package. 
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

    private final static QName _ExecuteCandidateNumber_QNAME = new QName("http://beans.core.enrollmentprocess.kalum.edu/", "executeCandidateNumber");
    private final static QName _ExecuteCandidateNumberResponse_QNAME = new QName("http://beans.core.enrollmentprocess.kalum.edu/", "executeCandidateNumberResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.kalumenrollmentmanagement.core.clients.soap.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExecuteCandidateNumber }
     * 
     */
    public ExecuteCandidateNumber createExecuteCandidateNumber() {
        return new ExecuteCandidateNumber();
    }

    /**
     * Create an instance of {@link ExecuteCandidateNumberResponse }
     * 
     */
    public ExecuteCandidateNumberResponse createExecuteCandidateNumberResponse() {
        return new ExecuteCandidateNumberResponse();
    }

    /**
     * Create an instance of {@link CandidateGenerateNumberRequest }
     * 
     */
    public CandidateGenerateNumberRequest createCandidateGenerateNumberRequest() {
        return new CandidateGenerateNumberRequest();
    }

    /**
     * Create an instance of {@link CandidateGenerateNumberResponse }
     * 
     */
    public CandidateGenerateNumberResponse createCandidateGenerateNumberResponse() {
        return new CandidateGenerateNumberResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteCandidateNumber }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExecuteCandidateNumber }{@code >}
     */
    @XmlElementDecl(namespace = "http://beans.core.enrollmentprocess.kalum.edu/", name = "executeCandidateNumber")
    public JAXBElement<ExecuteCandidateNumber> createExecuteCandidateNumber(ExecuteCandidateNumber value) {
        return new JAXBElement<ExecuteCandidateNumber>(_ExecuteCandidateNumber_QNAME, ExecuteCandidateNumber.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteCandidateNumberResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ExecuteCandidateNumberResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://beans.core.enrollmentprocess.kalum.edu/", name = "executeCandidateNumberResponse")
    public JAXBElement<ExecuteCandidateNumberResponse> createExecuteCandidateNumberResponse(ExecuteCandidateNumberResponse value) {
        return new JAXBElement<ExecuteCandidateNumberResponse>(_ExecuteCandidateNumberResponse_QNAME, ExecuteCandidateNumberResponse.class, null, value);
    }

}
