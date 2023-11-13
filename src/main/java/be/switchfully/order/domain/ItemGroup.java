package be.switchfully.order.domain;

import be.switchfully.item.domain.Item;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@SequenceGenerator(name = "itemGroup_seq", allocationSize = 1)
public class ItemGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemGroup_seq")
    private Long id;
    private int amount;
    private LocalDate shippingDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    public ItemGroup(Item item, int amount) {
        this.item = item;
        this.amount = amount;
        this.shippingDate = LocalDate.now().plusDays(1);
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
}
