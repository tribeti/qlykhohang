package Clients.View;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private JPanel sideMenu;
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public MainView(String username) {
        super("Dashboard - Quản lý vật tư");
        initUI(username);
    }

    private void initUI(String username) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        // --- Side Menu ---
        sideMenu = new JPanel();
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
    }
}
