
package edu.kalumenrollmentmanagement.core.clients.soap.services;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "GenerateNumberFileServiceImplService", targetNamespace = "http://beans.core.enrollmentprocess.kalum.edu/", wsdlLocation = "http://localhost:8080/GenerateNumberFileServiceImplService/GenerateNumberFileServiceImpl?wsdl")
public class GenerateNumberFileServiceImplService
    extends Service
{

    private final static URL GENERATENUMBERFILESERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException GENERATENUMBERFILESERVICEIMPLSERVICE_EXCEPTION;
    private final static QName GENERATENUMBERFILESERVICEIMPLSERVICE_QNAME = new QName("http://beans.core.enrollmentprocess.kalum.edu/", "GenerateNumberFileServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/GenerateNumberFileServiceImplService/GenerateNumberFileServiceImpl?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        GENERATENUMBERFILESERVICEIMPLSERVICE_WSDL_LOCATION = url;
        GENERATENUMBERFILESERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public GenerateNumberFileServiceImplService() {
        super(__getWsdlLocation(), GENERATENUMBERFILESERVICEIMPLSERVICE_QNAME);
    }

    public GenerateNumberFileServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), GENERATENUMBERFILESERVICEIMPLSERVICE_QNAME, features);
    }

    public GenerateNumberFileServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, GENERATENUMBERFILESERVICEIMPLSERVICE_QNAME);
    }

    public GenerateNumberFileServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, GENERATENUMBERFILESERVICEIMPLSERVICE_QNAME, features);
    }

    public GenerateNumberFileServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GenerateNumberFileServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IGenerateNumberFileService
     */
    @WebEndpoint(name = "GenerateNumberFileServiceImplPort")
    public IGenerateNumberFileService getGenerateNumberFileServiceImplPort() {
        return super.getPort(new QName("http://beans.core.enrollmentprocess.kalum.edu/", "GenerateNumberFileServiceImplPort"), IGenerateNumberFileService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IGenerateNumberFileService
     */
    @WebEndpoint(name = "GenerateNumberFileServiceImplPort")
    public IGenerateNumberFileService getGenerateNumberFileServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://beans.core.enrollmentprocess.kalum.edu/", "GenerateNumberFileServiceImplPort"), IGenerateNumberFileService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (GENERATENUMBERFILESERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw GENERATENUMBERFILESERVICEIMPLSERVICE_EXCEPTION;
        }
        return GENERATENUMBERFILESERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
