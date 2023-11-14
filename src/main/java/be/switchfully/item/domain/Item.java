package be.switchfully.item.domain;

import be.switchfully.order.domain.ItemGroup;
import jakarta.persistence.*;

import java.util.List;

@Entity
@SequenceGenerator(name = "item_seq", allocationSize = 1)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @Column(name = "ITEM_ID")
    private Long id;
    @Column(name = "NAME")
    public String name;
    @Column(name = "DESCRIPTION")
    public String description;
    @Column(name = "PRICE")
    public double price;
    @Column(name = "STOCK")
    public int stock;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<ItemGroup> itemGroups;

    private Item(String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
    public static Item createItem(String name, String description, double price, int stock) {
        return new Item(name, description, price, stock);
    }

    protected Item() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
    public int calculateStockLeft(int amountOrdered) {
        stock -= amountOrdered;
        return stock;
    }
}
