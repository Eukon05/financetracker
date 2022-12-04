package com.eukon05.financetracker.email;

public final class EmailConstants {

    private EmailConstants() {
    }

    public static final String REGISTRATION_MAIL_SUBJECT = "Confirm your email";
    public static final String REGISTRATION_MAIL_TEXT = "Hi %s, please visit the link below to confirm your email address and activate your account: \n%s";
    public static final String REGISTRATION_LINK = "%s/confirm-registration?token=%s";

    public static final String EMAIL_CHANGE_CONFIRMATION_MAIL_SUBJECT = "Confirm email change";
    public static final String EMAIL_CHANGE_CONFIRMATION_MAIL_TEXT = "Hi %s, we've received a request to change your email to: %s\nIf you want to proceed with this change, please visit the link below:\n%s";
    public static final String EMAIL_CHANGE_CONFIRMATION_MAIL_LINK = "%s/confirm-email-change?token=%s";

    public static final String PASSWORD_RESET_CONFIRMATION_MAIL_SUBJECT = "Confirm password reset";
    public static final String PASSWORD_RESET_CONFIRMATION_MAIL_TEXT = "Hi %s, we've received a request to reset your password.\nAfter reset, your password will be changed to: %s\nIf you want to proceed with this change, please visit the link below:\n%s";
    public static final String PASSWORD_RESET_CONFIRMATION_LINK = "%s/confirm-password-change?token=%s";


}
