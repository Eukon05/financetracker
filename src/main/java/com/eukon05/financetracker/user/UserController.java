package com.eukon05.financetracker.user;

import com.eukon05.financetracker.user.dto.RegisterDTO;
import com.eukon05.financetracker.user.dto.UpdateEmailDTO;
import com.eukon05.financetracker.user.dto.UpdatePasswordDTO;
import com.eukon05.financetracker.user.dto.UserDTO;
import com.eukon05.financetracker.user.mapper.UserModelMapper;
import com.eukon05.financetracker.user.usecase.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {

    private final UserFacade userFacade;
    private final UserModelMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createUser(@RequestBody @Valid RegisterDTO dto, HttpServletRequest request) {
        userFacade.createUser(dto, request.getRequestURL().toString().replace("/users", ""));
    }

    @GetMapping("/me")
    UserDTO getUser(Principal principal) {
        return mapper.mapUserToUserDTO(userFacade.getUserByUsernameOrThrow(principal.getName()));
    }

    @PutMapping("/me/email")
    void updateEmail(Principal principal, @Valid @RequestBody UpdateEmailDTO dto, HttpServletRequest request) {
        userFacade.updateUserEmail(principal.getName(), dto.email(), request.getRequestURL().toString().replace("/users/me/email", ""));
    }

    @PutMapping("/me/password")
    void updatePassword(Principal principal, @Valid @RequestBody UpdatePasswordDTO dto, HttpServletRequest request) {
        userFacade.updateUserPassword(principal.getName(), dto.password(), request.getRequestURL().toString().replace("/users/me/password", ""));
    }

}
