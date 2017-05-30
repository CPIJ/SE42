package auction.domain;

import javax.persistence.*;
import java.util.Iterator;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String email;

    @OneToMany()
    private Set<Item> offeredItems;

    public User(String email) {
        this.email = email;
    }

    public static final User DEFAULT = new User("default@default.nl");

    // No-Args constructor, required by JPA.
    public User() {

    }

    public User(String email, Set<Item> offeredItems) {
        this.email = email;
        this.offeredItems = offeredItems;
    }

    // Waarom moet deze volgens de tekst private zijn?
    public void addItem(Item item) {
        if (item == null) return;

        item.setSeller(this);
        this.offeredItems.add(item);
    }

    //region Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Iterator<Item> getOfferedItems() {
        return offeredItems.iterator();
    }

    public void setOfferedItems(Set<Item> offeredItems) {
        this.offeredItems = offeredItems;
    }

    public int numberOfOfferdItems() {
        return offeredItems.size();
    }
    //endregion


}
