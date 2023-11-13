package be.switchfully.order.repository;

import be.switchfully.order.domain.ItemGroup;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemGroupRepository implements PanacheRepository<ItemGroup> {
}
