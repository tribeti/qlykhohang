import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class App extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public App() {
        initUI();
    }

    static void main(String[] args) {
        // Setup giao diện FlatLaf trước khi tạo cửa sổ
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        // Chạy ứng dụng
        SwingUtilities.invokeLater(() -> {
            new App().setVisible(true);
        });
    }

    private void initUI() {
        setTitle("Đăng nhập Hệ thống");
        setSize(400, 350); // Kích thước cửa sổ
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Căn giữa màn hình
        setResizable(false); // Không cho kéo giãn

        // Layout chính: Dùng GridBagLayout để căn giữa các phần tử
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding xung quanh
        add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các ô
        gbc.fill = GridBagConstraints.HORIZONTAL; // Kéo giãn chiều ngang
        gbc.gridx = 0;

        // --- 1. Tiêu đề ---
        JLabel lblTitle = new JLabel("WELCOME BACK", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(51, 153, 255)); // Màu xanh đẹp
        gbc.gridy = 0;
        panel.add(lblTitle, gbc);

        // --- 2. Ô nhập Username ---
        txtUsername = new JTextField(20);
        // Tính năng xịn của FlatLaf: Placeholder (Chữ mờ)
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tài khoản / Email");
        // Bo tròn góc
        txtUsername.putClientProperty(FlatClientProperties.COMPONENT_ROUND_RECT, true);

        gbc.gridy = 1;
        panel.add(txtUsername, gbc);

        // --- 3. Ô nhập Password ---
        txtPassword = new JPasswordField(20);
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mật khẩu");
        // Tính năng xịn: Hiện nút con mắt để xem pass
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        txtPassword.putClientProperty(FlatClientProperties.COMPONENT_ROUND_RECT, true);

        gbc.gridy = 2;
        panel.add(txtPassword, gbc);

        // --- 4. Nút Login ---
        btnLogin = new JButton("ĐĂNG NHẬP");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBackground(new Color(51, 153, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.putClientProperty(FlatClientProperties.COMPONENT_ROUND_RECT, true);

        // Sự kiện khi bấm nút
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        gbc.gridy = 3;
        gbc.ipady = 10; // Làm nút cao hơn một chút
        panel.add(btnLogin, gbc);
    }

    // --- Xử lý Logic Đăng nhập ---
    private void handleLogin() {
        String user = txtUsername.getText();
        String pass = new String(txtPassword.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Gọi hàm kết nối DB
        if (checkLoginFromDB(user, pass)) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
            // TODO: Mở form chính của phần mềm tại đây
            // new MainWindow().setVisible(true);
            // this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!", "Thất bại", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- Kết nối TiDB Cloud / MySQL ---
    private boolean checkLoginFromDB(String usernameInput, String passwordInput) {
        Properties props = new Properties();

        // 1. Đọc file config
        try (FileInputStream fis = new FileInputStream("env.properties")) {
            props.load(fis);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy file cấu hình!");
            return false;
        }
        String host = props.getProperty("HOST");
        String port = props.getProperty("PORT");
        String database = props.getProperty("DATABASE");
        String dbUser = props.getProperty("USERNAME");
        String dbPass = props.getProperty("PASSWORD");
        String url = "jdbc:mysql://" + dbUser + ":" + dbPass + "@" + host + ":" + port + "/" + database + "?sslMode=VERIFY_IDENTITY";
        //3jt6tJD83xD8Wjm.root:<PASSWORD>@gateway01.ap-southeast-1.prod.aws.tidbcloud.com:4000/test
        try (Connection conn = DriverManager.getConnection(url)) {
            // Câu lệnh SQL kiểm tra (Dùng ? để tránh Hack SQL Injection)
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usernameInput);
            pstmt.setString(2, passwordInput);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi kết nối Server: " + e.getMessage());
            return false;
        }
    }
}