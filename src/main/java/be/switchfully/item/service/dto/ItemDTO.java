package be.switchfully.item.service.dto;

public record ItemDTO(
        Long id,
        String name,
        String description,
        double price,
        int stock) {
}
