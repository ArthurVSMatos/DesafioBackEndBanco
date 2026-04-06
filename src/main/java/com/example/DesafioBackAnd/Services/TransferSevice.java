package com.example.DesafioBackAnd.Services;

import com.example.DesafioBackAnd.DTOS.TransferDTO;
import com.example.DesafioBackAnd.Entitys.AuthorizationResponse;
import com.example.DesafioBackAnd.Entitys.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransferSevice {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    public void transfer(TransferDTO data) throws Exception {
        // FIND PAYER AND PAYEE BY ID
        User payer = userService.findUserById(data.payer());
        User payee = userService.findUserById(data.payee());


        userService.validateTransaction(payer, data.value());

        boolean isAuthorized = this.authorizeTransaction();
        if (!isAuthorized) {
            throw new Exception("Transferência não autorizada pelo serviço externo.");
        }


        payer.setBalance(payer.getBalance().subtract(data.value()));
        payee.setBalance(payee.getBalance().add(data.value()));


        userService.saveUser(payer);
        userService.saveUser(payee);

        try {
            this.notificationService.sendNotification(payer, "Transferência de R$ " + data.value() + " enviada com sucesso.");
            this.notificationService.sendNotification(payee, "Você recebeu uma transferência de R$ " + data.value());
        } catch (Exception e) {
            // Apenas logamos o erro no terminal para controle interno
            System.err.println("Alerta: A transferência foi feita, mas as notificações falharam: " + e.getMessage());
        }
    }

    public boolean authorizeTransaction() {
        try {

            ResponseEntity<AuthorizationResponse> response = restTemplate.getForEntity(
                    "https://util.devi.tools/api/v2/authorize",
                    AuthorizationResponse.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {

                return "success".equalsIgnoreCase(response.getBody().status());
            }

            return false;
        } catch (Exception e) {

            System.err.println("Erro crítico no autorizador externo: " + e.getMessage());
            return false;
        }
    }
}