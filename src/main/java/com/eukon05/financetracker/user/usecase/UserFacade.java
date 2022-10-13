package com.eukon05.financetracker.user.usecase;

import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.dto.RegisterDTO;
import com.eukon05.financetracker.user.dto.UserDTO;
import com.eukon05.financetracker.user.mapper.UserModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final CreateUserUseCase createUserUseCase;
    private final CheckUserExistsUseCase checkUserExistsUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserEmailUseCase updateUserEmailUseCase;
    private final UpdateUserPasswordUseCase updateUserPasswordUseCase;
    private final UserModelMapper mapper;

    public void createUser(RegisterDTO dto, String rootUrl) {
        createUserUseCase.createUser(dto, rootUrl);
    }

    public boolean checkUserExistsByUsername(String username) {
        return checkUserExistsUseCase.checkUserExistsByUsername(username);
    }

    public User getUserByUsernameOrThrow(String username) {
        return getUserUseCase.getUserByUsernameOrThrow(username);
    }

    public UserDTO getUserDTO(String username) {
        return mapper.mapUserToUserDTO(getUserByUsernameOrThrow(username));
    }

    public User getUserByEmailOrThrow(String email) {
        return getUserUseCase.getUserByEmailOrThrow(email);
    }

    public void updateUserEmail(String username, String newEmail, String rootUrl) {
        updateUserEmailUseCase.updateUserEmail(username, newEmail, rootUrl);
    }

    public void updateUserPassword(String username, String newPassword, String rootUrl) {
        updateUserPasswordUseCase.updateUserPassword(username, newPassword, rootUrl);
    }


}
