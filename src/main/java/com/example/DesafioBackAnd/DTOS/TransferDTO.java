package com.example.DesafioBackAnd.DTOS;

import java.math.BigDecimal;

public record TransferDTO(BigDecimal value , Long payer , Long payee) {
}
