package auction.domain;

import javax.persistence.Entity;

@Entity
public class Furniture extends Item {

    private String material;

    public Furniture(User seller, Category category, String description, String material) {
        super(seller, category, description);
        this.material = material;
    }

    public Furniture() {

    }

    //region Getters & Setters

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    //endregion

}
