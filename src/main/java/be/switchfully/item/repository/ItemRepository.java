package be.switchfully.item.repository;

import be.switchfully.item.domain.Item;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
@ApplicationScoped
public class ItemRepository implements PanacheRepository<Item> {
}
