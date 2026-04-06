package com.example.DesafioBackAnd.Entitys;

import java.util.Map;

public record AuthorizationResponse(String status, Map<String, Object> data) {
}
