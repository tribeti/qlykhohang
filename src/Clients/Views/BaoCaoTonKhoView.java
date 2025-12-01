package Clients.Views;


import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;


public class BaoCaoTonKhoView extends JFrame {

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
        setTitle("Báo Cáo Tồn Kho");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // ======= PANEL FORM =======
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin báo cáo"));

        cboKho = new JComboBox<>();
        cboVatTu = new JComboBox<>();
        txtSoLuong = new JTextField();
        txtNgayBaoCao = new JFormattedTextField(java.time.LocalDate.now());

        formPanel.add(new JLabel("Kho:"));
        formPanel.add(cboKho);

        formPanel.add(new JLabel("Vật tư:"));
        formPanel.add(cboVatTu);

        formPanel.add(new JLabel("Số lượng:"));
        formPanel.add(txtSoLuong);

        formPanel.add(new JLabel("Ngày báo cáo:"));
        formPanel.add(txtNgayBaoCao);

        // ======= PANEL BUTTON =======
        JPanel buttonPanel = new JPanel();
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnReset = new JButton("Làm mới");

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnReset);

        // ======= TABLE =======
        tblModel = new DefaultTableModel(
                new Object[]{"ID", "Kho", "Vật tư", "Số lượng", "Ngày báo cáo"}, 0
        );
        tblTonKho = new JTable(tblModel);
        JScrollPane scrollPane = new JScrollPane(tblTonKho);

        // Add components
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }
}
