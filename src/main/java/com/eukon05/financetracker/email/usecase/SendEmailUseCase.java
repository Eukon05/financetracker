package com.eukon05.financetracker.email.usecase;

interface SendEmailUseCase {

    void sendEmail(String recipient, String subject, String content);

}
