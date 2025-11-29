package Clients.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DanhMucView extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSearch;
    private JButton btnAdd, btnEdit, btnDelete;

    public DanhMucView() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        // --- Top panel: search ---
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Tìm");
        top.add(new JLabel("Tìm:"));
        top.add(txtSearch);
        top.add(btnSearch);
        add(top, BorderLayout.NORTH);

        // --- Table ---
        String[] cols = {"Mã", "Tên", "ĐVT", "SL tối thiểu", "Nhà cung cấp"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- Bottom panel: buttons ---
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAdd = new JButton("Thêm");
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        bottom.add(btnAdd);
        bottom.add(btnEdit);
        bottom.add(btnDelete);
        add(bottom, BorderLayout.SOUTH);

        // --- Sample data ---
        model.addRow(new Object[]{"VT001", "Tua vít", "Cái", 5, "Công ty A"});
    }
}
