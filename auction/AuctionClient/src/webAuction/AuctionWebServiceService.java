
package webAuction;

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
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "AuctionWebServiceService", targetNamespace = "http://web.webAuction/", wsdlLocation = "http://localhost:5000/WebAuction/Auction?wsdl")
public class AuctionWebServiceService
    extends Service
{

    private final static URL AUCTIONWEBSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException AUCTIONWEBSERVICESERVICE_EXCEPTION;
    private final static QName AUCTIONWEBSERVICESERVICE_QNAME = new QName("http://web.webAuction/", "AuctionWebServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:5000/WebAuction/Auction?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        AUCTIONWEBSERVICESERVICE_WSDL_LOCATION = url;
        AUCTIONWEBSERVICESERVICE_EXCEPTION = e;
    }

    public AuctionWebServiceService() {
        super(__getWsdlLocation(), AUCTIONWEBSERVICESERVICE_QNAME);
    }

    public AuctionWebServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), AUCTIONWEBSERVICESERVICE_QNAME, features);
    }

    public AuctionWebServiceService(URL wsdlLocation) {
        super(wsdlLocation, AUCTIONWEBSERVICESERVICE_QNAME);
    }

    public AuctionWebServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, AUCTIONWEBSERVICESERVICE_QNAME, features);
    }

    public AuctionWebServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AuctionWebServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns AuctionWebService
     */
    @WebEndpoint(name = "AuctionWebServicePort")
    public AuctionWebService getAuctionWebServicePort() {
        return super.getPort(new QName("http://web.webAuction/", "AuctionWebServicePort"), AuctionWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AuctionWebService
     */
    @WebEndpoint(name = "AuctionWebServicePort")
    public AuctionWebService getAuctionWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://web.webAuction/", "AuctionWebServicePort"), AuctionWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (AUCTIONWEBSERVICESERVICE_EXCEPTION!= null) {
            throw AUCTIONWEBSERVICESERVICE_EXCEPTION;
        }
        return AUCTIONWEBSERVICESERVICE_WSDL_LOCATION;
    }

}
