package com.eukon05.financetracker.user.usecase;

import com.eukon05.financetracker.email.usecase.EmailFacade;
import com.eukon05.financetracker.token.Token;
import com.eukon05.financetracker.token.TokenType;
import com.eukon05.financetracker.user.RoleType;
import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.UserRepository;
import com.eukon05.financetracker.user.dto.RegisterDTO;
import com.eukon05.financetracker.user.mapper.UserModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserModelMapper mapper;
    private final EmailFacade emailFacade;
    private final UserRepository userRepository;

    @Override
    public void createUser(RegisterDTO dto, String rootUrl) {
        User user = mapper.mapRegisterRequestDTOToUser(dto);
        user.getRoles().add(RoleType.USER);

        Token token = new Token(TokenType.CONFIRM_REGISTRATION);
        user.getTokens().add(token);
        userRepository.save(user);

        emailFacade.sendRegistrationEmail(dto.username(), dto.email(), rootUrl, token.getId());
    }

}
