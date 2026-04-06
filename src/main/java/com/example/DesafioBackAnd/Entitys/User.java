package com.example.DesafioBackAnd.Entitys;

import com.example.DesafioBackAnd.DTOS.UserDTO;
import com.example.DesafioBackAnd.Enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity (name = "users")
@Table (name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String document; // CPF ou CNPJ único

    @Column(unique = true)
    private String email;

    private String password;

    private BigDecimal balance; // Saldo sempre em BigDecimal por segurança financeira

    @Enumerated(EnumType.STRING)
    private UserType userType; // COMMON ou MERCHANT

    // Construtor especializado para converter DTO em Entidade
    public User(UserDTO data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.balance = data.balance();
        this.userType = data.userType();
        this.password = data.password();
        this.document = data.document();
        this.email = data.email();
    }


}

