package Clients.View;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VatTuView extends JPanel {

    // Form vật tư
    public JTextField txtTenVatTu;
    public JComboBox<String> cbNhaCungCap;
    public JTextField txtDonViTinh;
    public JTextField txtGiaTien;
    public JTextField txtSoLuong;
    public JTextArea txtMoTa;
    public JComboBox<String> cbKho;

    // Buttons
    public JButton btnThem;
    public JButton btnSua;
    public JButton btnXoa;
    public JButton btnReset;

    // Table
    public JTable tblVatTu;
    public DefaultTableModel tblModel;

    public VatTuView() {
        setLayout(new BorderLayout());

        // ---------- FORM PANEL ----------
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin Vật Tư"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        txtTenVatTu = new JTextField(20);
        cbNhaCungCap = new JComboBox<>();
        txtDonViTinh = new JTextField(10);
        txtGiaTien = new JTextField(10);
        txtSoLuong = new JTextField(10);
        txtMoTa = new JTextArea(3,20);
        txtMoTa.setLineWrap(true);
        txtMoTa.setWrapStyleWord(true);
        cbKho = new JComboBox<>();

        // Row 1
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Tên vật tư:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtTenVatTu, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Nhà cung cấp:"), gbc);
        gbc.gridx = 3;
        formPanel.add(cbNhaCungCap, gbc);

        // Row 2
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Đơn vị tính:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtDonViTinh, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Giá tiền:"), gbc);
        gbc.gridx = 3;
        formPanel.add(txtGiaTien, gbc);

        // Row 3
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Số lượng:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtSoLuong, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Kho:"), gbc);
        gbc.gridx = 3;
        formPanel.add(cbKho, gbc);

        // Row 4
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Mô tả:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        formPanel.add(new JScrollPane(txtMoTa), gbc);
        gbc.gridwidth = 1;

        add(formPanel, BorderLayout.NORTH);

        // ---------- TABLE PANEL ----------
        tblModel = new DefaultTableModel(
                new Object[]{"ID", "Tên vật tư", "Nhà cung cấp", "Đơn vị tính", "Giá tiền", "Số lượng", "Mô tả", "Kho", "Ngày tạo"}, 0
        );
        tblVatTu = new JTable(tblModel);
        add(new JScrollPane(tblVatTu), BorderLayout.CENTER);

        // ---------- BUTTON PANEL ----------
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnReset = new JButton("Làm mới");

        btnPanel.add(btnThem);
        btnPanel.add(btnSua);
        btnPanel.add(btnXoa);
        btnPanel.add(btnReset);

        add(btnPanel, BorderLayout.SOUTH);
    }
}
