package be.switchfully.order.domain;

import be.switchfully.user.domain.User;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Entity
@SequenceGenerator(name = "order_seq", allocationSize = 1)
@Table(name = "O_RDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @Column(name = "ORDER_ID")
    private Long id;
    @OneToMany
    @JoinTable(name = "ORDERED_ITEMGROUP",
            joinColumns = {@JoinColumn(name = "ORDER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ITEMGROUP_ID")})
    @Cascade(CascadeType.ALL)
    private List<ItemGroup> itemGroups;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @Column(name = "ORDER_TOTAL_PRICE")
    private double orderTotalPrice;

    public Order(List<ItemGroup> itemGroups, User user) {
        this.itemGroups = itemGroups;
        this.user = user;
        this.orderTotalPrice = calculateOrderTotalPrice();
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public List<ItemGroup> getItemGroups() {
        return itemGroups;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public double calculateOrderTotalPrice() {
        double totalPrice = 0.0;
        for (ItemGroup itemGroup : itemGroups) {
            totalPrice += itemGroup.getTotalPrice();
        }
        return totalPrice;
    }
}
