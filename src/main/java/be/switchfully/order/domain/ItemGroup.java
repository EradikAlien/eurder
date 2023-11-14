package be.switchfully.order.domain;

import be.switchfully.item.domain.Item;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@SequenceGenerator(name = "itemGroup_seq", allocationSize = 1)
public class ItemGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemGroup_seq")
    @Column(name = "ITEMGROUP_ID")
    private Long id;
    @Column(name = "AMOUNT")
    private int amount;
    @Column(name = "SHIPPING_DATE")
    private LocalDate shippingDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ITEM_ID")
    private Item item;
    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    public ItemGroup(Item item, int amount) {
        this.item = item;
        this.amount = amount;
        this.shippingDate = calculateShippingDate(item.getStock(), amount);
        item.stock = item.calculateStockLeft(amount);
        this.totalPrice = calculateTotalPrice();
    }

    public ItemGroup() {
    }

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDate calculateShippingDate(int itemInStock, int amountOrdered) {
        if(itemInStock > 0 && (itemInStock - amountOrdered) >= 0) {
            return LocalDate.now().plusDays(1);
        } else {
            return LocalDate.now().plusWeeks(1);
        }
    }
    private double calculateTotalPrice(){
        return item.getPrice() * amount;
    }
}
