package traltb.example;

import java.util.regex.Pattern;

public class AccountService {

    private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{7,}$";
    public boolean registerAccount(String username, String password, String email) {

        if (username == null || username.trim().length() < 3) {
            return false;
        }
        if (password == null || !Pattern.matches(PASSWORD_PATTERN, password)) {
            return false;
        }
        if (email == null || !isValidEmail(email)) {
            return false;
        }
        return true;
    }
    public boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_PATTERN, email);
    }
}