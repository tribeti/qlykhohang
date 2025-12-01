package Clients.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LichSuGiaoDichView extends JFrame {

    public JComboBox<String> cboVatTu;
    public JComboBox<String> cboUser;

    public JTextField txtSoLuongThayDoi;
    public JComboBox<String> cboLoaiGiaoDich;

    public JFormattedTextField txtNgayGiaoDich;

    public JTextArea txtGhiChu;

    public JButton btnThem;
    public JButton btnSua;
    public JButton btnXoa;
    public JButton btnReset;

    public JTable tblLichSu;
    public DefaultTableModel tblModel;

    public LichSuGiaoDichView() {
        setTitle("Lịch Sử Giao Dịch");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ----------- FORM PANEL -----------
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin giao dịch"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        cboVatTu = new JComboBox<>();
        cboUser = new JComboBox<>();

        txtSoLuongThayDoi = new JTextField(15);

        cboLoaiGiaoDich = new JComboBox<>(new String[]{
                "Nhập kho", "Xuất kho", "Điều chỉnh tăng", "Điều chỉnh giảm", "Khác"
        });

        txtNgayGiaoDich = new JFormattedTextField(java.time.LocalDateTime.now());
        txtNgayGiaoDich.setColumns(15);

        txtGhiChu = new JTextArea(3, 20);
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setWrapStyleWord(true);
        JScrollPane scrollGhiChu = new JScrollPane(txtGhiChu);

        // Add items to form grid
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Vật tư:"), gbc);

        gbc.gridx = 1;
        formPanel.add(cboVatTu, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Người dùng:"), gbc);

        gbc.gridx = 1;
        formPanel.add(cboUser, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Số lượng thay đổi:"), gbc);

        gbc.gridx = 1;
        formPanel.add(txtSoLuongThayDoi, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Loại giao dịch:"), gbc);

        gbc.gridx = 1;
        formPanel.add(cboLoaiGiaoDich, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Ngày giao dịch:"), gbc);

        gbc.gridx = 1;
        formPanel.add(txtNgayGiaoDich, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Ghi chú:"), gbc);

        gbc.gridx = 1;
        formPanel.add(scrollGhiChu, gbc);

        // ----------- BUTTON PANEL -----------
        JPanel buttonPanel = new JPanel();
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnReset = new JButton("Làm mới");
        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnReset);

        // ----------- TABLE PANEL -----------
        tblModel = new DefaultTableModel(
                new Object[]{"ID", "Vật tư", "User", "Số lượng", "Loại GD", "Ngày giao dịch", "Ghi chú"}, 
                0
        );
        tblLichSu = new JTable(tblModel);
        JScrollPane scrollTable = new JScrollPane(tblLichSu);

        // ----------- ADD TO FRAME -----------
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollTable, BorderLayout.SOUTH);

        setVisible(true);
    }
}
