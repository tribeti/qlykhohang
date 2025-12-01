package Clients.Views;

import Clients.Controllers.CoreController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NhaCungCapView extends JPanel {
    private final CoreController controller = new CoreController();

    public NhaCungCapView() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        // --- Top panel: search ---
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Tìm");
        JButton btnRefresh = new JButton("Tải lại");
        top.add(new JLabel("Tìm:"));
        top.add(txtSearch);
        top.add(btnSearch);
        top.add(btnRefresh);
        add(top, BorderLayout.NORTH);

        // --- Table ---
        String[] cols = {"Mã", "Tên nhà cung cấp", "Email", "Địa chỉ"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- Bottom panel: buttons ---
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAdd = new JButton("Thêm");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        bottom.add(btnAdd);
        bottom.add(btnEdit);
        bottom.add(btnDelete);
        add(bottom, BorderLayout.SOUTH);
    }
}