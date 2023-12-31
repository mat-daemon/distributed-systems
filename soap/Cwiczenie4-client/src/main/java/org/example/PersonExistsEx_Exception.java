
package org.example;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.1
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "PersonExistsEx", targetNamespace = "http://example.org/")
public class PersonExistsEx_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private final PersonExistsEx faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public PersonExistsEx_Exception(String message, PersonExistsEx faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public PersonExistsEx_Exception(String message, PersonExistsEx faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: org.example.PersonExistsEx
     */
    public PersonExistsEx getFaultInfo() {
        return faultInfo;
    }

}
