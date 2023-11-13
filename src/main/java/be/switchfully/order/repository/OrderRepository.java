package be.switchfully.order.repository;

import be.switchfully.order.domain.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {
    public List<Order> findByUserId(UUID userID) {
        return list("user.id", userID);
    }
}
