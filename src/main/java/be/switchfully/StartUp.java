package be.switchfully;


import be.switchfully.item.domain.Item;
import be.switchfully.item.repository.ItemRepository;
import be.switchfully.user.domain.Address;
import be.switchfully.user.domain.User;
import be.switchfully.user.repository.UserRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import static be.switchfully.item.domain.Item.createItem;
import static be.switchfully.user.domain.User.createAdmin;
import static be.switchfully.user.domain.User.createMember;

@Singleton
public class StartUp {
    @Inject
    UserRepository userRepository;
    @Inject
    ItemRepository itemRepository;

    @Transactional
    public void loadUsers(@Observes StartupEvent event) {
        userRepository.deleteAll();

        Address address1 = new Address("Teststraat", "36", "7090", "Testberg");

        User user1 = createMember("Laurent", "Herry", "laurent@email.be", "user", address1, "123456789");
        User admin = createAdmin("admin@email.be", "admin");
        userRepository.persist(user1);
        userRepository.persist(admin);

        itemRepository.deleteAll();

        Item item1 = createItem("item 1", "description of item 1", 10.0, 5);
        Item item2= createItem("item 2", "description of item 2", 15.5, 10);
        itemRepository.persist(item1);
        itemRepository.persist(item2);

    }
}
