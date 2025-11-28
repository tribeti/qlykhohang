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
    private JCheckBox cbShowPassword;

    public App() {
        initUI();
    }

    static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new App().setVisible(true);
        });
    }

    private void initUI() {
        setTitle("Đăng nhập Hệ thống");
        setSize(400, 380);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);
        add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // --- 1. Tiêu đề ---
        JLabel lblTitle = new JLabel("WELCOME BACK", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(51, 153, 255));
        gbc.gridy = 0;
        panel.add(lblTitle, gbc);

        // --- 2. Ô nhập Username ---
        txtUsername = new JTextField(20);
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        // Dùng TitledBorder thay cho Placeholder của FlatLaf
        txtUsername.setBorder(BorderFactory.createTitledBorder("Tài khoản / Email"));

        gbc.gridy = 1;
        panel.add(txtUsername, gbc);

        // --- 3. Ô nhập Password ---
        txtPassword = new JPasswordField(20);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        // Dùng TitledBorder thay cho Placeholder
        txtPassword.setBorder(BorderFactory.createTitledBorder("Mật khẩu"));

        gbc.gridy = 2;
        panel.add(txtPassword, gbc);

        // --- 3.5 Checkbox hiện mật khẩu (Thay cho nút con mắt) ---
        cbShowPassword = new JCheckBox("Hiện mật khẩu");
        cbShowPassword.setBackground(Color.WHITE);
        cbShowPassword.setFocusPainted(false);
        cbShowPassword.addActionListener(e -> {
            if (cbShowPassword.isSelected()) {
                txtPassword.setEchoChar((char) 0); // Hiện chữ
            } else {
                txtPassword.setEchoChar('•'); // Ẩn chữ (ký tự chấm tròn)
            }
        });

        GridBagConstraints gbcCb = (GridBagConstraints) gbc.clone();
        gbcCb.insets = new Insets(0, 15, 5, 10);
        gbcCb.gridy = 3;
        panel.add(cbShowPassword, gbcCb);

        // --- 4. Nút Login ---
        btnLogin = new JButton("ĐĂNG NHẬP");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBackground(new Color(51, 153, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setOpaque(true);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        gbc.gridy = 4;
        gbc.ipady = 10;
        panel.add(btnLogin, gbc);
    }

    private void handleLogin() {
        String user = txtUsername.getText();
        String pass = new String(txtPassword.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (checkLoginFromDB(user, pass)) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
            // new MainWindow().setVisible(true);
            // this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!", "Thất bại", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean checkLoginFromDB(String usernameInput, String passwordInput) {
        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream("env.properties")) {
            props.load(fis);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy file cấu hình env.properties!");
            return false;
        }

        String host = props.getProperty("HOST");
        String port = props.getProperty("PORT");
        String database = props.getProperty("DATABASE");
        String dbUser = props.getProperty("USERNAME");
        String dbPass = props.getProperty("PASSWORD");

        String url = "jdbc:mysql://" + dbUser + ":" + dbPass + "@" + host + ":" + port + "/" + database + "?sslMode=VERIFY_IDENTITY";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass)) {
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