package Clients.Views;

import Clients.Controllers.LoginController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JFrame {
    // Màu sắc chủ đạo
    private final Color PRIMARY_COLOR = new Color(51, 153, 255);
    private final Color HOVER_COLOR = new Color(30, 120, 220);
    private final Color BG_COLOR = new Color(245, 247, 250);
    private final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    public JTextField txtUsername;
    public JPasswordField txtPassword;
    public JButton btnLogin;
    public JLabel lblMessage;
    // Thêm checkbox
    public JCheckBox chkShowPass;

    public LoginView() {
        super("Đăng nhập - Quản lý vật tư");
        initUI();
        LoginController _ = new LoginController(this);
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BG_COLOR);
        mainPanel.setBorder(new EmptyBorder(40, 50, 40, 50));

        JLabel lblIcon = new JLabel("📦");
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitle = new JLabel("QUẢN LÝ VẬT TƯ");
        lblTitle.setFont(TITLE_FONT);
        lblTitle.setForeground(new Color(50, 50, 50));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubTitle = new JLabel("Đăng nhập hệ thống");
        lblSubTitle.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblSubTitle.setForeground(Color.GRAY);
        lblSubTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(20, 20, 20, 20)
        ));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.setMaximumSize(new Dimension(400, 280));

        JLabel lblUser = new JLabel("Tài khoản");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        txtUsername = createStyledTextField();

        JLabel lblPass = new JLabel("Mật khẩu");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 12));
        txtPassword = createStyledPasswordField();

        chkShowPass = new JCheckBox("Hiển thị mật khẩu");
        chkShowPass.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        chkShowPass.setBackground(Color.WHITE); // Cùng màu nền với form
        chkShowPass.setFocusPainted(false);
        chkShowPass.setCursor(new Cursor(Cursor.HAND_CURSOR));

        char defaultEchoChar = txtPassword.getEchoChar();
        chkShowPass.addActionListener(_ -> {
            if (chkShowPass.isSelected()) {
                txtPassword.setEchoChar((char) 0);
            } else {
                txtPassword.setEchoChar(defaultEchoChar);
            }
        });

        formPanel.add(lblUser);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(txtUsername);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(lblPass);
        formPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        formPanel.add(txtPassword);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(chkShowPass);

        btnLogin = new JButton("ĐĂNG NHẬP");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(PRIMARY_COLOR);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(new EmptyBorder(10, 0, 10, 0));
        btnLogin.setMaximumSize(new Dimension(400, 45));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(PRIMARY_COLOR);
            }
        });

        getRootPane().setDefaultButton(btnLogin);

        lblMessage = new JLabel(" ");
        lblMessage.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblMessage.setForeground(Color.RED);
        lblMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- Lắp ráp ---
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(lblIcon);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(lblTitle);
        mainPanel.add(lblSubTitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(btnLogin);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(lblMessage);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(MAIN_FONT);
        field.setPreferredSize(new Dimension(300, 35));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                new EmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setFont(MAIN_FONT);
        field.setPreferredSize(new Dimension(300, 35));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                new EmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }
}