package com.eukon05.financetracker.user.usecase;

import com.eukon05.financetracker.email.usecase.EmailFacade;
import com.eukon05.financetracker.token.Token;
import com.eukon05.financetracker.token.TokenType;
import com.eukon05.financetracker.user.RoleType;
import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.UserRepository;
import com.eukon05.financetracker.user.dto.RegisterDTO;
import com.eukon05.financetracker.user.exception.AdminUserAlreadyExistsException;
import com.eukon05.financetracker.user.exception.EmailTakenException;
import com.eukon05.financetracker.user.exception.UsernameTakenException;
import com.eukon05.financetracker.user.mapper.UserModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserModelMapper mapper;
    private final EmailFacade emailFacade;
    private final UserRepository userRepository;

    @Value("${masterPassword}")
    private String masterPassword;

    @Override
    public User createUser(RegisterDTO dto, String rootUrl) {

        if (userRepository.existsByEmail(dto.email())) {
            throw new EmailTakenException(dto.email());
        } else if (userRepository.existsByUsername(dto.username())) {
            throw new UsernameTakenException(dto.username());
        }

        User user = mapper.mapRegisterDTOToUser(dto);
        user.getRoles().add(RoleType.USER);

        Token token = new Token(TokenType.CONFIRM_REGISTRATION);
        user.getTokens().add(token);
        userRepository.save(user);

        emailFacade.sendRegistrationEmail(dto.username(), dto.email(), rootUrl, token.getId());

        return user;
    }

    @Override
    public void createAdminUser(String masterPassword, RegisterDTO dto, String rootUrl) {
        if (!this.masterPassword.equals(masterPassword)) {
            throw new AccessDeniedException(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }

        if (userRepository.existsByRolesContaining(RoleType.ADMIN)) {
            throw new AdminUserAlreadyExistsException();
        }

        User user = createUser(dto, rootUrl);
        user.getRoles().add(RoleType.ADMIN);
        userRepository.save(user);
    }

}
