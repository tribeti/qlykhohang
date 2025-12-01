package Clients.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BaoCaoTonKhoView extends JPanel {

    // Form fields
    public JComboBox<String> cboKho;
    public JComboBox<String> cboVatTu;
    public JTextField txtSoLuong;
    public JFormattedTextField txtNgayBaoCao;

    // Buttons
    public JButton btnThem;
    public JButton btnSua;
    public JButton btnXoa;
    public JButton btnReset;

    // Table
    public JTable tblTonKho;
    public DefaultTableModel tblModel;

    public BaoCaoTonKhoView() {
        setLayout(new BorderLayout(10, 10));

        // ======= PANEL FORM + BUTTON =======
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin báo cáo"));

        cboKho = new JComboBox<>();
        cboVatTu = new JComboBox<>();
        txtSoLuong = new JTextField();
        txtNgayBaoCao = new JFormattedTextField(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd").format(java.time.LocalDate.now()));

        formPanel.add(new JLabel("Kho:"));
        formPanel.add(cboKho);
        formPanel.add(new JLabel("Vật tư:"));
        formPanel.add(cboVatTu);
        formPanel.add(new JLabel("Số lượng:"));
        formPanel.add(txtSoLuong);
        formPanel.add(new JLabel("Ngày báo cáo:"));
        formPanel.add(txtNgayBaoCao);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnReset = new JButton("Làm mới");

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnReset);

        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // ======= TABLE =======
        tblModel = new DefaultTableModel(
                new Object[]{"ID", "Kho", "Vật tư", "Số lượng", "Ngày báo cáo"}, 0
        );
        tblTonKho = new JTable(tblModel);
        JScrollPane scrollPane = new JScrollPane(tblTonKho);

        add(scrollPane, BorderLayout.CENTER);
    }
}
