package auction.domain;

import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

import javax.persistence.*;

@Entity
public class Bid {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(targetEntity = FontysTime.class)
    private FontysTime time;

    @OneToOne(cascade = CascadeType.PERSIST, targetEntity = User.class)
    private User buyer;

    @OneToOne(cascade = CascadeType.PERSIST, targetEntity = Money.class)
    private Money amount;

    @OneToOne(mappedBy = "highest", targetEntity = Item.class)
    private Item item;

    public static final Bid DEFAULT = new Bid(User.DEFAULT, new Money(0, Money.EURO));

    public Bid() {

    }

    public Bid(User buyer, Money amount) {
        this.buyer = buyer;
        this.amount = amount;
    }


    //region Getters & Setters

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    //endregion

    public FontysTime getTime() {
        return time;
    }

    public User getBuyer() {
        return buyer;
    }

    public Money getAmount() {
        return amount;
    }

    public void setTime(FontysTime time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}