
package org.example;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.1
 * Generated source version: 3.0
 * 
 */
@WebServiceClient(name = "PersonService", targetNamespace = "http://example.org/", wsdlLocation = "file:/C:/Users/wojte/Desktop/reservation_system/Cwiczenie4-client/src/main/resources/personservice.wsdl")
public class PersonService_Service
    extends Service
{

    private static final URL PERSONSERVICE_WSDL_LOCATION;
    private static final WebServiceException PERSONSERVICE_EXCEPTION;
    private static final QName PERSONSERVICE_QNAME = new QName("http://example.org/", "PersonService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/wojte/Desktop/reservation_system/Cwiczenie4-client/src/main/resources/personservice.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PERSONSERVICE_WSDL_LOCATION = url;
        PERSONSERVICE_EXCEPTION = e;
    }

    public PersonService_Service() {
        super(__getWsdlLocation(), PERSONSERVICE_QNAME);
    }

    public PersonService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), PERSONSERVICE_QNAME, features);
    }

    public PersonService_Service(URL wsdlLocation) {
        super(wsdlLocation, PERSONSERVICE_QNAME);
    }

    public PersonService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PERSONSERVICE_QNAME, features);
    }

    public PersonService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PersonService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PersonService
     */
    @WebEndpoint(name = "PersonServiceImplPort")
    public PersonService getPersonServiceImplPort() {
        return super.getPort(new QName("http://example.org/", "PersonServiceImplPort"), PersonService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PersonService
     */
    @WebEndpoint(name = "PersonServiceImplPort")
    public PersonService getPersonServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://example.org/", "PersonServiceImplPort"), PersonService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PERSONSERVICE_EXCEPTION!= null) {
            throw PERSONSERVICE_EXCEPTION;
        }
        return PERSONSERVICE_WSDL_LOCATION;
    }

}
