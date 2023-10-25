package be.switchfully.item.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Item extends PanacheEntityBase {
    @Id
    private String id;
    public String name;
    public String description;
    public double price;
    public int stock;

    public Item(String name, String description, double price, int stock) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
    public Item() {
    }

    public String getId() {
        return id;
    }
}
