package domain;

import javax.persistence.*;

@Entity()
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Shape {

    @Id
    @GeneratedValue
    private int id;
    private int x;
    private int y;

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Shape() {

    }

    //region Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //endregion
}
