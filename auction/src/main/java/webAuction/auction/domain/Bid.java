package webAuction.auction.domain;

import webAuction.nl.fontys.util.FontysTime;
import webAuction.nl.fontys.util.Money;

import javax.persistence.*;

@Entity
public class Bid {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(targetEntity = FontysTime.class)
    private FontysTime time;

    @OneToOne(targetEntity = User.class)
    private User buyer;

    @OneToOne(targetEntity = Money.class)
    private Money amount;

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
}
