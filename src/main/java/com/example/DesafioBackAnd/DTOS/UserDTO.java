package com.example.DesafioBackAnd.DTOS;

import com.example.DesafioBackAnd.Enums.UserType;

import java.math.BigDecimal;

public record UserDTO(
        String firstName,
        String lastName,
        String document,
        BigDecimal balance,
        String email,
        String password,
        UserType userType
) {
}
