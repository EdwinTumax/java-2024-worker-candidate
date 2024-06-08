
package edu.kalumenrollmentmanagement.core.clients.soap.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IGenerateNumberFileService", targetNamespace = "http://beans.core.enrollmentprocess.kalum.edu/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IGenerateNumberFileService {


    /**
     * 
     * @param arg0
     * @return
     *     returns edu.kalumenrollmentmanagement.core.clients.soap.services.CandidateGenerateNumberResponse
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "executeCandidateNumber", targetNamespace = "http://beans.core.enrollmentprocess.kalum.edu/", className = "edu.kalumenrollmentmanagement.core.clients.soap.services.ExecuteCandidateNumber")
    @ResponseWrapper(localName = "executeCandidateNumberResponse", targetNamespace = "http://beans.core.enrollmentprocess.kalum.edu/", className = "edu.kalumenrollmentmanagement.core.clients.soap.services.ExecuteCandidateNumberResponse")
    @Action(input = "http://beans.core.enrollmentprocess.kalum.edu/IGenerateNumberFileService/executeCandidateNumberRequest", output = "http://beans.core.enrollmentprocess.kalum.edu/IGenerateNumberFileService/executeCandidateNumberResponse")
    public CandidateGenerateNumberResponse executeCandidateNumber(
        @WebParam(name = "arg0", targetNamespace = "")
        CandidateGenerateNumberRequest arg0);

}
