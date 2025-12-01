package Clients.Views;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public MainView(String username, String role) {
        super("Dashboard - Quản lý vật tư");
        initUI(username, role);
    }

    private void initUI(String username, String role) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        // --- Side Menu ---
        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sideMenu.setPreferredSize(new Dimension(220, getHeight()));

        JLabel lblUser = new JLabel("Xin chào, " + username);
        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblUser.setFont(new Font("SansSerif", Font.BOLD, 14));
        sideMenu.add(lblUser);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 10)));

        // Buttons
        JButton btnDanhMuc = new JButton("Quản lý danh mục thiết bị");
        JButton btnKho = new JButton("Quản lý kho");
        JButton btnNhaCungCap = new JButton("Quản lý nhà cung cấp");
        JButton btnXuatTon = new JButton("Quản lý xuất kho - tồn");
        JButton btnThongKe = new JButton("Thống kê");
        JButton btnLogout = new JButton("Đăng xuất");
        JButton btnQuanLyUser = getJButton(role);

        for (JButton b : new JButton[]{btnDanhMuc, btnKho, btnNhaCungCap, btnXuatTon, btnThongKe, btnLogout}) {
            b.setAlignmentX(Component.LEFT_ALIGNMENT);
            b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
            sideMenu.add(b);
            sideMenu.add(Box.createRigidArea(new Dimension(0, 6)));
        }

        // --- Content Panel ---
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        // Add views
        DanhMucView danhMucView = new DanhMucView();
        KhoView khoView = new KhoView();
        NhaCungCapView nhaCungCapView = new NhaCungCapView();
        XuatTonView xuatTonView = new XuatTonView();
        ThongKeView thongKeView = new ThongKeView();

        contentPanel.add(danhMucView, "DanhMuc");
        contentPanel.add(xuatTonView, "XuatTon");
        contentPanel.add(thongKeView, "ThongKe");
        contentPanel.add(khoView, "Kho");
        contentPanel.add(nhaCungCapView, "NhaCungCap");

        // --- Layout Main Frame ---
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sideMenu, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Button listeners - chỉ cần gọi cardLayout.show()
        btnDanhMuc.addActionListener(_ -> cardLayout.show(contentPanel, "DanhMuc"));
        btnXuatTon.addActionListener(_ -> cardLayout.show(contentPanel, "XuatTon"));
        btnThongKe.addActionListener(_ -> cardLayout.show(contentPanel, "ThongKe"));
        btnKho.addActionListener(_ -> cardLayout.show(contentPanel, "Kho"));
        btnNhaCungCap.addActionListener(_ -> cardLayout.show(contentPanel, "NhaCungCap"));

        btnLogout.addActionListener(_ -> {
            this.dispose();
            new LoginView().setVisible(true);
        });

        if (btnQuanLyUser != null) {
            sideMenu.add(btnQuanLyUser);
            sideMenu.add(Box.createRigidArea(new Dimension(0, 6)));
        }

        setVisible(true);
    }

    private JButton getJButton(String role) {
        JButton btnQuanLyUser = null;

        if (role != null && role.equalsIgnoreCase("ADMIN")) {
            btnQuanLyUser = new JButton("Quản lý Tài khoản (Admin)");
            btnQuanLyUser.setAlignmentX(Component.LEFT_ALIGNMENT);
            btnQuanLyUser.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
            btnQuanLyUser.addActionListener(_ -> {
                // cardLayout.show(contentPanel, "AdminPanel");
                JOptionPane.showMessageDialog(this, "Tính năng chỉ dành cho Admin!");
            });
        }
        return btnQuanLyUser;
    }
}