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
    private Long id;
    @OneToMany
    @JoinTable(name = "Ordered_ItemGroup",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "itemGroup_id")})
    @Cascade(CascadeType.ALL)
    private List<ItemGroup> itemGroups;
    @ManyToOne
    private User user;

    public Order(List<ItemGroup> itemGroups, User user) {
        this.itemGroups = itemGroups;
        this.user = user;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public List<ItemGroup> getItemGroups() {
        return itemGroups;
    }
}
