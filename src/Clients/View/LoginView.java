package Clients.View;

import Clients.Controllers.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    public JTextField txtUsername = new JTextField(20);
    public JPasswordField txtPassword = new JPasswordField(20);
    public JButton btnLogin = new JButton("Đăng nhập");
    public JLabel lblMessage = new JLabel(" ");

    private LoginController controller;

    public LoginView() {
        super("Đăng nhập - Quản lý vật tư");
        initUI();
        controller = new LoginController(this);
    }

    private void initUI() {
        // --- Set Look & Feel ---
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace(); // Or use a proper logger
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 280);
        setLocationRelativeTo(null);

        // --- Root Panel ---
        JPanel root = new JPanel(new BorderLayout());
        root.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        // --- Title ---
        JLabel title = new JLabel("QUẢN LÝ VẬT TƯ - THIẾT BỊ", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        root.add(title, BorderLayout.NORTH);

        // --- Form Panel ---
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.anchor = GridBagConstraints.WEST;

        // Username
        c.gridx = 0; c.gridy = 0;
        form.add(new JLabel("Tài khoản:"), c);
        c.gridx = 1;
        form.add(txtUsername, c);

        // Password
        c.gridx = 0; c.gridy = 1;
        form.add(new JLabel("Mật khẩu:"), c);
        c.gridx = 1;
        form.add(txtPassword, c);

        // Login Button
        c.gridx = 1; c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER;
        form.add(btnLogin, c);

        // Message Label
        c.gridx = 1; c.gridy = 3;
        form.add(lblMessage, c);

        root.add(form, BorderLayout.CENTER);

        add(root);
    }
}
