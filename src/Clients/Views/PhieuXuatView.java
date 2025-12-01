package Clients.Views;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PhieuXuatView extends JPanel {

    // Form phiếu xuất
    public JTextField txtMaPhieu;
    public JComboBox<String> cbUser;
    public JComboBox<String> cbKho;
    public JFormattedTextField txtNgayXuat;
    public JTextArea txtGhiChu;

    // Thông tin vật tư
    public JComboBox<String> cbVatTu;
    public JTextField txtSoLuong;

    // Buttons
    public JButton btnThem;
    public JButton btnSua;
    public JButton btnXoa;
    public JButton btnReset;

    // Bảng hiển thị danh sách phiếu xuất
    public JTable tblPhieuXuat;
    public DefaultTableModel tblModel;

    public PhieuXuatView() {
        setLayout(new BorderLayout());

        // ---------- FORM PANEL ----------
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin Phiếu Xuất"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        txtMaPhieu = new JTextField(15);
        cbUser = new JComboBox<>();
        cbKho = new JComboBox<>();
        txtNgayXuat = new JFormattedTextField(java.time.LocalDateTime.now());
        txtNgayXuat.setColumns(15);
        txtGhiChu = new JTextArea(3,20);
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setWrapStyleWord(true);

        cbVatTu = new JComboBox<>();
        txtSoLuong = new JTextField(10);

        // Row 1
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Mã phiếu:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtMaPhieu, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Người xuất:"), gbc);
        gbc.gridx = 3;
        formPanel.add(cbUser, gbc);

        // Row 2
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Kho:"), gbc);
        gbc.gridx = 1;
        formPanel.add(cbKho, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Ngày xuất:"), gbc);
        gbc.gridx = 3;
        formPanel.add(txtNgayXuat, gbc);

        // Row 3
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Ghi chú:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        formPanel.add(new JScrollPane(txtGhiChu), gbc);
        gbc.gridwidth = 1;

        // Row 4 - Vật tư
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Vật tư:"), gbc);
        gbc.gridx = 1;
        formPanel.add(cbVatTu, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Số lượng:"), gbc);
        gbc.gridx = 3;
        formPanel.add(txtSoLuong, gbc);

        add(formPanel, BorderLayout.NORTH);

        // ---------- TABLE PANEL ----------
        tblModel = new DefaultTableModel(
                new Object[]{"ID", "Mã Phiếu", "Người Xuất", "Kho", "Ngày Xuất", "Vật Tư", "Số Lượng", "Ghi Chú"}, 0
        );
        tblPhieuXuat = new JTable(tblModel);
        add(new JScrollPane(tblPhieuXuat), BorderLayout.CENTER);

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
