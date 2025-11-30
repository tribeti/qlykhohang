package Clients.Views;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public MainView(String username) {
        super("Dashboard - Quản lý vật tư");
        initUI(username);
    }

    private void initUI(String username) {
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
        JButton btnNhapKho = new JButton("Quản lý nhập kho");
        JButton btnXuatTon = new JButton("Quản lý xuất kho - tồn");
        JButton btnThongKe = new JButton("Thống kê");
        JButton btnLogout = new JButton("Đăng xuất");

        for (JButton b : new JButton[]{btnDanhMuc, btnNhapKho, btnXuatTon, btnThongKe, btnLogout}) {
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
        NhapKhoView nhapKhoView = new NhapKhoView();
        XuatTonView xuatTonView = new XuatTonView();
        ThongKeView thongKeView = new ThongKeView();

        contentPanel.add(danhMucView, "DanhMuc");
        contentPanel.add(nhapKhoView, "NhapKho");
        contentPanel.add(xuatTonView, "XuatTon");
        contentPanel.add(thongKeView, "ThongKe");

        // --- Layout Main Frame ---
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sideMenu, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Button listeners - chỉ cần gọi cardLayout.show()
        btnDanhMuc.addActionListener(_ -> cardLayout.show(contentPanel, "DanhMuc"));
        btnNhapKho.addActionListener(_ -> cardLayout.show(contentPanel, "NhapKho"));
        btnXuatTon.addActionListener(_ -> cardLayout.show(contentPanel, "XuatTon"));
        btnThongKe.addActionListener(_ -> cardLayout.show(contentPanel, "ThongKe"));

        btnLogout.addActionListener(_ -> {
            // Xử lý đăng xuất
            this.dispose();
            // Mở lại LoginView
            new LoginView().setVisible(true);
        });

        setVisible(true);
    }
}