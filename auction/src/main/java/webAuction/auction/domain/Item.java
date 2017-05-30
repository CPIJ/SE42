package webAuction.auction.domain;

import webAuction.nl.fontys.util.Money;

import javax.persistence.*;

@Entity
public class Item implements Comparable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(targetEntity = User.class)
    private User seller;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;

    private String description;

    @OneToOne(targetEntity = Bid.class)
    private Bid highest;

    public Item() {

    }

    public Item(User seller, Category category, String description) {
        this.seller = seller;
        this.category = category;
        this.description = description;
    }

    public int compareTo(Object arg0) {
        //TODO
        return -1;
    }

    public boolean equals(Object o) {
        //TODO
        return false;
    }

    public int hashCode() {
        //TODO
        return 0;
    }

    //region Getters & Setters
    public Long getId() {
        return id;
    }

    public User getSeller() {
        return seller;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Bid getHighestBid() {
        return highest;
    }

    public Bid newBid(User buyer, Money amount) {
        if (highest != null && highest.getAmount().compareTo(amount) >= 0) {
            return null;
        }
        highest = new Bid(buyer, amount);
        return highest;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bid getHighest() {
        return highest;
    }

    public void setHighest(Bid highest) {
        this.highest = highest;
    }

    //endregion
}
