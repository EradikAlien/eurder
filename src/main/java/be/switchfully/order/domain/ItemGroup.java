package be.switchfully.order.domain;

import be.switchfully.customExceptions.NoItemFoundException;
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
        if (validateItem(item)) {
            this.item = item;
            this.amount = amount;
            item.stock = item.calculateStockLeft(amount);
            this.shippingDate = calculateShippingDate(item.getStock(), amount);
            this.totalPrice = calculateTotalPrice();
        } else throw new NoItemFoundException("No item found");
    }

    public ItemGroup(ItemGroup itemGroup) {
        this.item = itemGroup.item;
        this.amount = itemGroup.amount;
        item.stock = item.calculateStockLeft(amount);
        this.shippingDate = calculateShippingDate(item.getStock(), amount);
        this.totalPrice = calculateTotalPrice();
    }

    protected ItemGroup() {
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

    private LocalDate calculateShippingDate(int itemInStock, int amountOrdered) {
        if (itemInStock > 0 && (itemInStock - amountOrdered) >= 0) {
            return LocalDate.now().plusDays(1);
        } else {
            return LocalDate.now().plusWeeks(1);
        }
    }

    private double calculateTotalPrice() {
        return item.getPrice() * amount;
    }

    private boolean validateItem(Item item) {
        return item != null;
    }
}
