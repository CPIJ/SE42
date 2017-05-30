
package webAuction;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webAuction package. 
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

    private final static QName _NewBidResponse_QNAME = new QName("http://web.webAuction/", "newBidResponse");
    private final static QName _FindItemsByDescription_QNAME = new QName("http://web.webAuction/", "findItemsByDescription");
    private final static QName _GetItem_QNAME = new QName("http://web.webAuction/", "getItem");
    private final static QName _OfferItem_QNAME = new QName("http://web.webAuction/", "offerItem");
    private final static QName _RevokeItemResponse_QNAME = new QName("http://web.webAuction/", "revokeItemResponse");
    private final static QName _OfferItemResponse_QNAME = new QName("http://web.webAuction/", "offerItemResponse");
    private final static QName _RevokeItem_QNAME = new QName("http://web.webAuction/", "revokeItem");
    private final static QName _FindItemsByDescriptionResponse_QNAME = new QName("http://web.webAuction/", "findItemsByDescriptionResponse");
    private final static QName _NewBid_QNAME = new QName("http://web.webAuction/", "newBid");
    private final static QName _GetItemResponse_QNAME = new QName("http://web.webAuction/", "getItemResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webAuction
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OfferItemResponse }
     * 
     */
    public OfferItemResponse createOfferItemResponse() {
        return new OfferItemResponse();
    }

    /**
     * Create an instance of {@link RevokeItem }
     * 
     */
    public RevokeItem createRevokeItem() {
        return new RevokeItem();
    }

    /**
     * Create an instance of {@link GetItemResponse }
     * 
     */
    public GetItemResponse createGetItemResponse() {
        return new GetItemResponse();
    }

    /**
     * Create an instance of {@link FindItemsByDescriptionResponse }
     * 
     */
    public FindItemsByDescriptionResponse createFindItemsByDescriptionResponse() {
        return new FindItemsByDescriptionResponse();
    }

    /**
     * Create an instance of {@link NewBid }
     * 
     */
    public NewBid createNewBid() {
        return new NewBid();
    }

    /**
     * Create an instance of {@link FindItemsByDescription }
     * 
     */
    public FindItemsByDescription createFindItemsByDescription() {
        return new FindItemsByDescription();
    }

    /**
     * Create an instance of {@link GetItem }
     * 
     */
    public GetItem createGetItem() {
        return new GetItem();
    }

    /**
     * Create an instance of {@link NewBidResponse }
     * 
     */
    public NewBidResponse createNewBidResponse() {
        return new NewBidResponse();
    }

    /**
     * Create an instance of {@link RevokeItemResponse }
     * 
     */
    public RevokeItemResponse createRevokeItemResponse() {
        return new RevokeItemResponse();
    }

    /**
     * Create an instance of {@link OfferItem }
     * 
     */
    public OfferItem createOfferItem() {
        return new OfferItem();
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link FontysTime }
     * 
     */
    public FontysTime createFontysTime() {
        return new FontysTime();
    }

    /**
     * Create an instance of {@link Money }
     * 
     */
    public Money createMoney() {
        return new Money();
    }

    /**
     * Create an instance of {@link Category }
     * 
     */
    public Category createCategory() {
        return new Category();
    }

    /**
     * Create an instance of {@link Bid }
     * 
     */
    public Bid createBid() {
        return new Bid();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NewBidResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.webAuction/", name = "newBidResponse")
    public JAXBElement<NewBidResponse> createNewBidResponse(NewBidResponse value) {
        return new JAXBElement<NewBidResponse>(_NewBidResponse_QNAME, NewBidResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindItemsByDescription }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.webAuction/", name = "findItemsByDescription")
    public JAXBElement<FindItemsByDescription> createFindItemsByDescription(FindItemsByDescription value) {
        return new JAXBElement<FindItemsByDescription>(_FindItemsByDescription_QNAME, FindItemsByDescription.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.webAuction/", name = "getItem")
    public JAXBElement<GetItem> createGetItem(GetItem value) {
        return new JAXBElement<GetItem>(_GetItem_QNAME, GetItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfferItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.webAuction/", name = "offerItem")
    public JAXBElement<OfferItem> createOfferItem(OfferItem value) {
        return new JAXBElement<OfferItem>(_OfferItem_QNAME, OfferItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RevokeItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.webAuction/", name = "revokeItemResponse")
    public JAXBElement<RevokeItemResponse> createRevokeItemResponse(RevokeItemResponse value) {
        return new JAXBElement<RevokeItemResponse>(_RevokeItemResponse_QNAME, RevokeItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfferItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.webAuction/", name = "offerItemResponse")
    public JAXBElement<OfferItemResponse> createOfferItemResponse(OfferItemResponse value) {
        return new JAXBElement<OfferItemResponse>(_OfferItemResponse_QNAME, OfferItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RevokeItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.webAuction/", name = "revokeItem")
    public JAXBElement<RevokeItem> createRevokeItem(RevokeItem value) {
        return new JAXBElement<RevokeItem>(_RevokeItem_QNAME, RevokeItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindItemsByDescriptionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.webAuction/", name = "findItemsByDescriptionResponse")
    public JAXBElement<FindItemsByDescriptionResponse> createFindItemsByDescriptionResponse(FindItemsByDescriptionResponse value) {
        return new JAXBElement<FindItemsByDescriptionResponse>(_FindItemsByDescriptionResponse_QNAME, FindItemsByDescriptionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NewBid }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.webAuction/", name = "newBid")
    public JAXBElement<NewBid> createNewBid(NewBid value) {
        return new JAXBElement<NewBid>(_NewBid_QNAME, NewBid.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://web.webAuction/", name = "getItemResponse")
    public JAXBElement<GetItemResponse> createGetItemResponse(GetItemResponse value) {
        return new JAXBElement<GetItemResponse>(_GetItemResponse_QNAME, GetItemResponse.class, null, value);
    }

}
