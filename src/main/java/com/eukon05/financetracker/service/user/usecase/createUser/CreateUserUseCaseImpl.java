package com.eukon05.financetracker.service.user.usecase.createUser;

import com.eukon05.financetracker.common.RoleType;
import com.eukon05.financetracker.common.TokenType;
import com.eukon05.financetracker.domain.Token;
import com.eukon05.financetracker.domain.User;
import com.eukon05.financetracker.dto.RegisterDTO;
import com.eukon05.financetracker.mapper.user.UserModelMapper;
import com.eukon05.financetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CreateUserUseCaseImpl implements CreateUserUseCase {

    private static final String REGISTRATION_MAIL_SUBJECT = "Confirm your email";
    private static final String REGISTRATION_MAIL_TEXT = "Hi %s, please visit the link below to confirm your email address and activate your account: \n%s";
    private static final String REGISTRATION_LINK = "%s/confirm-registration?token=%s";

    private final UserModelMapper mapper;
    private final JavaMailSender mailSender;
    private final UserRepository userRepository;

    @Override
    public void createUser(RegisterDTO dto, String rootUrl) {
        User user = mapper.mapRegisterRequestDTOToUser(dto);
        user.getRoles().add(RoleType.USER);

        Token token = new Token(TokenType.CONFIRM_REGISTRATION);
        user.getTokens().add(token);
        userRepository.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dto.email());
        message.setSubject(REGISTRATION_MAIL_SUBJECT);
        message.setText(String.format(REGISTRATION_MAIL_TEXT, dto.name(), String.format(REGISTRATION_LINK, rootUrl, token.getId())));

        mailSender.send(message);
    }
}
