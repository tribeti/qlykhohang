package Clients.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NhaCungCapView extends JFrame {

    public JTextField txtTenNCC;
    public JTextField txtEmail;
    public JTextArea txtDiaChi;

    public JButton btnThem;
    public JButton btnSua;
    public JButton btnXoa;
    public JButton btnReset;

    public JTable tblNCC;
    public DefaultTableModel tblModel;

    public NhaCungCapView() {
        setTitle("Quản Lý Nhà Cung Cấp");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ----------- PANEL FORM -----------
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin nhà cung cấp"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        txtTenNCC = new JTextField(20);
        txtEmail = new JTextField(20);

        txtDiaChi = new JTextArea(3, 20);
        txtDiaChi.setLineWrap(true);
        txtDiaChi.setWrapStyleWord(true);
        JScrollPane scrollDiaChi = new JScrollPane(txtDiaChi);

        // Row 1: Tên NCC
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Tên nhà cung cấp:"), gbc);

        gbc.gridx = 1;
        formPanel.add(txtTenNCC, gbc);

        // Row 2: Email
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        formPanel.add(txtEmail, gbc);

        // Row 3: Địa chỉ
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Địa chỉ:"), gbc);

        gbc.gridx = 1;
        formPanel.add(scrollDiaChi, gbc);

        // ----------- PANEL BUTTON -----------
        JPanel buttonPanel = new JPanel();
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnReset = new JButton("Làm mới");

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnReset);

        // ----------- TABLE -----------
        tblModel = new DefaultTableModel(
                new Object[]{"ID", "Tên nhà cung cấp", "Email", "Địa chỉ"}, 0
        );

        tblNCC = new JTable(tblModel);
        JScrollPane scrollTable = new JScrollPane(tblNCC);

        // ----------- ADD TO FRAME -----------
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollTable, BorderLayout.SOUTH);

        setVisible(true);
    }
}
