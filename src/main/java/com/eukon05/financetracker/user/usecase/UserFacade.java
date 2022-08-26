package com.eukon05.financetracker.user.usecase;

import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.dto.RegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final CreateUserUseCase createUserUseCase;
    private final CheckUserExistsUseCase checkUserExistsUseCase;
    private final GetUserUseCase getUserUseCase;

    public void createUser(RegisterDTO dto, String rootUrl) {
        createUserUseCase.createUser(dto, rootUrl);
    }

    public boolean checkUserExistsByUsername(String username) {
        return checkUserExistsUseCase.checkUserExistsByUsername(username);
    }

    public User getUserByUsernameOrThrow(String username) {
        return getUserUseCase.getUserByUsernameOrThrow(username);
    }


}
