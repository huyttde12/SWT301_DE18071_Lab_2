package traltb;

import java.util.regex.Pattern;

public class AccountService {

    // Phương thức kiểm tra email hợp lệ
    public boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        // Regex đơn giản để kiểm tra định dạng email
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    // Phương thức đăng ký tài khoản
    public boolean registerAccount(String username, String password, String email) {
        // Kiểm tra username không được null hoặc rỗng
        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        // Kiểm tra password phải lớn hơn 6 ký tự
        if (password == null || password.length() <= 6) {
            return false;
        }

        // Kiểm tra email hợp lệ
        if (!isValidEmail(email)) {
            return false;
        }

        // Nếu tất cả điều kiện đều thỏa mãn
        return true;
    }
}