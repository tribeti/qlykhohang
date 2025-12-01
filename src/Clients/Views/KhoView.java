package Clients.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class KhoView extends JPanel {

    public JTextField txtTenKho;
    public JTextArea txtDiaChi;

    public JButton btnThem;
    public JButton btnSua;
    public JButton btnXoa;
    public JButton btnReset;

    public JTable tblKho;
    public DefaultTableModel tblModel;

    public KhoView() {
        setLayout(new BorderLayout(10,10));

        // ---------- FORM + BUTTON PANEL ----------
        JPanel topPanel = new JPanel(new BorderLayout(5,5));

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin kho"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        txtTenKho = new JTextField(20);
        txtDiaChi = new JTextArea(3,20);
        txtDiaChi.setLineWrap(true);
        txtDiaChi.setWrapStyleWord(true);
        JScrollPane addressScroll = new JScrollPane(txtDiaChi);

        // Row 1: Tên kho
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Tên kho:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtTenKho, gbc);

        // Row 2: Địa chỉ
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Địa chỉ:"), gbc);
        gbc.gridx = 1;
        formPanel.add(addressScroll, gbc);

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

        // ---------- TABLE PANEL ----------
        tblModel = new DefaultTableModel(
                new Object[]{"ID", "Tên kho", "Địa chỉ"}, 0
        );
        tblKho = new JTable(tblModel);
        JScrollPane scrollTable = new JScrollPane(tblKho);

        add(scrollTable, BorderLayout.CENTER);
    }
}
