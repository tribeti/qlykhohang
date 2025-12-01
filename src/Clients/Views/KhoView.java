package Clients.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class KhoView extends JFrame {

    public JTextField txtTenKho;
    public JTextArea txtDiaChi;

    public JButton btnThem;
    public JButton btnSua;
    public JButton btnXoa;
    public JButton btnReset;

    public JTable tblKho;
    public DefaultTableModel tblModel;

    public KhoView() {
        setTitle("Quản lý Kho");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---------- FORM INPUT ----------
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin kho"));
        GridBagConstraints gbc = new GridBagConstraints();

        txtTenKho = new JTextField(20);
        txtDiaChi = new JTextArea(3, 20);
        txtDiaChi.setLineWrap(true);
        txtDiaChi.setWrapStyleWord(true);
        JScrollPane addressScroll = new JScrollPane(txtDiaChi);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Tên kho:"), gbc);

        gbc.gridx = 1;
        formPanel.add(txtTenKho, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Địa chỉ:"), gbc);

        gbc.gridx = 1;
        formPanel.add(addressScroll, gbc);

        // ---------- BUTTONS ----------
        JPanel buttonPanel = new JPanel();
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnReset = new JButton("Làm mới");

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnReset);

        // ---------- TABLE ----------
        tblModel = new DefaultTableModel(
                new Object[]{"ID", "Tên kho", "Địa chỉ"}, 0
        );
        tblKho = new JTable(tblModel);

        JScrollPane scrollTable = new JScrollPane(tblKho);

        // ---------- ADD TO FRAME ----------
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollTable, BorderLayout.SOUTH);

        setVisible(true);
    }
}
