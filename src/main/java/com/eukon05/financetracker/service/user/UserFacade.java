package com.eukon05.financetracker.service.user;

import com.eukon05.financetracker.dto.RegisterDTO;
import com.eukon05.financetracker.service.user.usecase.checkUserExists.CheckUserExistsUseCase;
import com.eukon05.financetracker.service.user.usecase.createUser.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final CreateUserUseCase createUserUseCase;
    private final CheckUserExistsUseCase checkUserExistsUseCase;

    public void createUser(RegisterDTO dto, String rootUrl) {
        createUserUseCase.createUser(dto, rootUrl);
    }

    public boolean checkUserExistsByUsername(String username) {
        return checkUserExistsUseCase.checkUserExistsByUsername(username);
    }


}
