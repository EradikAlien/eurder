package be.switchfully.item.service.dto;

public record CreateAndUpdateItemDTO(
        String name,
        String description,
        double price,
        int stock) {
}
