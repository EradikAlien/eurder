package be.switchfully.user.service.dto;

import be.switchfully.user.domain.Address;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String firstname,
        String lastname,
        String emailAddress,
        Address address,
        String phoneNumber) {
}
