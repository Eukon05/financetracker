package com.eukon05.financetracker.user.dto;

import com.eukon05.financetracker.user.RoleType;

import java.math.BigDecimal;
import java.util.List;

public record UserDTO(String username, String email, BigDecimal balance, List<RoleType> roles) {
}
