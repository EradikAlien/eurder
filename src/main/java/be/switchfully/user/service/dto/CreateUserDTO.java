package be.switchfully.user.service.dto;

import be.switchfully.user.domain.Address;

public record CreateUserDTO
        (String firstname,
         String lastname,
         String emailAddress,
         String password,
         Address address,
         String phoneNumber) {
}
