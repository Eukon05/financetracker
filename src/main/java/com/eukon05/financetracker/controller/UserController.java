package com.eukon05.financetracker.controller;

import com.eukon05.financetracker.dto.RegisterDTO;
import com.eukon05.financetracker.service.user.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid RegisterDTO dto, HttpServletRequest request) {
        userFacade.createUser(dto, request.getRequestURL().toString().replace("/users", ""));
    }

}
