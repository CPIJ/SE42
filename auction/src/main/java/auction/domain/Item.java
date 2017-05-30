package auction.domain;

import nl.fontys.util.Money;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Item implements Comparable<Item> {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(targetEntity = User.class)
    private User seller;

    @ManyToOne(cascade = CascadeType.PERSIST, targetEntity = Category.class)
    private Category category;

    private String description;

    @OneToOne(cascade = CascadeType.PERSIST, targetEntity = Bid.class)
    @JoinColumn(nullable = false)
    private Bid highest;

    public Item() {

    }

    public Item(User seller, Category category, String description) {
        this.seller = seller;
        this.category = category;
        this.description = description;
        this.seller.addItem(this);

        if (this.getHighestBid() == null) {
            this.setHighest(Bid.DEFAULT);
        }
    }

    public int compareTo(Item other) {
        return id.compareTo(other.getId());
    }

    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Item)) return false;

        return Objects.equals(id, ((Item) other).getId());
    }

    // Van hier: https://stackoverflow.com/questions/2265503/why-do-i-need-to-override-the-equals-and-hashcode-methods-in-java
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        return prime * result + ((id == null) ? 0 : id.hashCode());
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
