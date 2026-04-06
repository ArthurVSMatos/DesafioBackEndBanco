package com.example.DesafioBackAnd.Services;

import com.example.DesafioBackAnd.DTOS.UserDTO;
import com.example.DesafioBackAnd.Entitys.User;
import com.example.DesafioBackAnd.Enums.UserType;
import com.example.DesafioBackAnd.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User payer, BigDecimal amount) throws Exception {
        if (payer.getUserType() == UserType.MERCHANT) {
            throw new Exception("Lojistas não podem realizar transferências.");
        }

        if (payer.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente para realizar a transferência.");
        }
    }

    //FIND USER BY ID
    public User findUserById(Long id) throws Exception {
        return this.repository.findById(id)
                .orElseThrow(() -> new Exception("Usuário não encontrado."));
    }

    //SAVE USER
    public void saveUser(User user) {
        this.repository.save(user);
    }

   //CREAT USER
    public User createUser(UserDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return this.repository.findAll();
    }
}
