package Clients.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NhapKhoView extends JPanel {

    public JTextField txtMaPhieu;
    public JComboBox<String> cbNhaCungCap;
    public JComboBox<String> cbUser;
    public JFormattedTextField txtNgayNhap;

    public JComboBox<String> cbVatTu;
    public JTextField txtSoLuong;
    public JTextField txtDonGia;

    public JTextArea txtGhiChu;

    public JButton btnThem;
    public JButton btnSua;
    public JButton btnXoa;
    public JButton btnReset;

    public JTable tableNhapKho;
    public DefaultTableModel tableModel;

    public NhapKhoView() {
        setLayout(new BorderLayout());

        // ============= FORM PANEL =============
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Thông tin nhập kho"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        txtMaPhieu = new JTextField(15);
        cbNhaCungCap = new JComboBox<>();
        cbUser = new JComboBox<>();
        txtNgayNhap = new JFormattedTextField(java.time.LocalDateTime.now());
        txtNgayNhap.setColumns(15);

        cbVatTu = new JComboBox<>();
        txtSoLuong = new JTextField(10);
        txtDonGia = new JTextField(10);

        txtGhiChu = new JTextArea(3, 20);
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setWrapStyleWord(true);

        // Row 1
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Mã phiếu:"), gbc);
        gbc.gridx = 1;
        form.add(txtMaPhieu, gbc);

        gbc.gridx = 2;
        form.add(new JLabel("Nhà cung cấp:"), gbc);
        gbc.gridx = 3;
        form.add(cbNhaCungCap, gbc);

        // Row 2
        gbc.gridx = 0; gbc.gridy = 1;
        form.add(new JLabel("Nhân viên nhập:"), gbc);
        gbc.gridx = 1;
        form.add(cbUser, gbc);

        gbc.gridx = 2;
        form.add(new JLabel("Ngày nhập:"), gbc);
        gbc.gridx = 3;
        form.add(txtNgayNhap, gbc);

        // Row 3
        gbc.gridx = 0; gbc.gridy = 2;
        form.add(new JLabel("Vật tư:"), gbc);
        gbc.gridx = 1;
        form.add(cbVatTu, gbc);

        gbc.gridx = 2;
        form.add(new JLabel("Số lượng:"), gbc);
        gbc.gridx = 3;
        form.add(txtSoLuong, gbc);

        // Row 4
        gbc.gridx = 0; gbc.gridy = 3;
        form.add(new JLabel("Đơn giá:"), gbc);
        gbc.gridx = 1;
        form.add(txtDonGia, gbc);

        gbc.gridx = 2;
        form.add(new JLabel("Ghi chú:"), gbc);

        gbc.gridx = 3;
        form.add(new JScrollPane(txtGhiChu), gbc);

        add(form, BorderLayout.NORTH);

        // ============= TABLE PANEL =============
        tableModel = new DefaultTableModel(
                new Object[]{
                        "ID", "Mã phiếu", "Nhà cung cấp", "Nhân viên",
                        "Ngày nhập", "Vật tư", "Số lượng", "Đơn giá", "Ghi chú"
                }, 0
        );

        tableNhapKho = new JTable(tableModel);
        add(new JScrollPane(tableNhapKho), BorderLayout.CENTER);

        // ============= BUTTONS PANEL =============
        JPanel actions = new JPanel();
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnReset = new JButton("Reset");

        actions.add(btnThem);
        actions.add(btnSua);
        actions.add(btnXoa);
        actions.add(btnReset);

        add(actions, BorderLayout.SOUTH);
    }
}
