package traltb.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountServiceTest {

    AccountService accountService = new AccountService();


    static final String RESULT_FILE = "ket_qua_test.csv";


    @BeforeAll
    static void setupFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESULT_FILE))) {

            writer.write("Time,Username,Password,Email,Expected,Actual,Status");
            writer.newLine();
            System.out.println("--> Đã tạo file lưu kết quả: " + RESULT_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest(name = "Index {index}: User={0}")
    @DisplayName("Kiểm tra Đăng ký và Lưu kết quả")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testRegisterAccount(String username, String password, String email, boolean expected) {


        boolean actualResult = accountService.registerAccount(username, password, email);


        String status = (expected == actualResult) ? "PASSED" : "FAILED";


        saveResultToFile(username, password, email, expected, actualResult, status);


        assertEquals(expected, actualResult,
                "Lỗi tại User: " + username);
    }


    private void saveResultToFile(String user, String pass, String email, boolean exp, boolean act, String status) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESULT_FILE, true))) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));


            String line = String.format("%s,%s,%s,%s,%b,%b,%s",
                    time, user, pass, email, exp, act, status);

            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Không thể ghi file: " + e.getMessage());
        }
    }


    @AfterAll
    static void done() {
        System.out.println("--> Đã hoàn tất kiểm thử. Mời bạn xem file " + RESULT_FILE);
    }
}