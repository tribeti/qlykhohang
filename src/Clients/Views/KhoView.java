package Clients.Views;

import Clients.Controllers.CoreController;
import Common.Models.Kho;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class KhoView extends JPanel {
    private final CoreController controller = new CoreController();
    private DefaultTableModel model;
    private JTable table;

    public KhoView() {
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
        String[] cols = {"Mã", "Tên kho", "Địa chỉ"};
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
        JButton btnAdd = new JButton("Thêm");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        bottom.add(btnAdd);
        bottom.add(btnEdit);
        bottom.add(btnDelete);
        add(bottom, BorderLayout.SOUTH);

        loadData();
        btnRefresh.addActionListener(_ -> loadData());

        btnAdd.addActionListener(_ -> onAddKho());
        btnEdit.addActionListener(_ -> onEditKho());
        btnDelete.addActionListener(_ -> onDeleteKho());
    }

    private void loadData() {
        model.setRowCount(0);

        SwingUtilities.invokeLater(() -> {
            try {
                List<Kho> danhSach = controller.getDanhSachKho();

                if (danhSach == null || danhSach.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không có dữ liệu hoặc không thể kết nối server!",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                for (Kho kho : danhSach) {
                    model.addRow(new Object[]{
                            kho.getId(),
                            kho.getTenKho(),
                            kho.getDiaChi()
                    });
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + ex.getMessage(),
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void onAddKho() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtTenKho = new JTextField();
        JTextField txtDiaChi = new JTextField();

        panel.add(new JLabel("Tên kho:"));
        panel.add(txtTenKho);
        panel.add(new JLabel("Địa chỉ:"));
        panel.add(txtDiaChi);

        int result = JOptionPane.showConfirmDialog(this, panel, "Thêm kho", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String tenKho = txtTenKho.getText().trim();
            String diaChi = txtDiaChi.getText().trim();

            if (tenKho.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên kho không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SwingUtilities.invokeLater(() -> {
                String response = controller.themKho(tenKho, diaChi);
                if (response.equals("Thành công")) {
                    JOptionPane.showMessageDialog(this, "Thêm kho thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + response, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    private void onEditKho() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn kho để sửa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int id = (int) model.getValueAt(selectedRow, 0);
        String tenKho = (String) model.getValueAt(selectedRow, 1);
        String diaChi = (String) model.getValueAt(selectedRow, 2);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtTenKho = new JTextField(tenKho);
        JTextField txtDiaChi = new JTextField(diaChi);

        panel.add(new JLabel("Tên kho:"));
        panel.add(txtTenKho);
        panel.add(new JLabel("Địa chỉ:"));
        panel.add(txtDiaChi);

        int result = JOptionPane.showConfirmDialog(this, panel, "Sửa kho", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String newTenKho = txtTenKho.getText().trim();
            String newDiaChi = txtDiaChi.getText().trim();

            if (newTenKho.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên kho không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SwingUtilities.invokeLater(() -> {
                String response = controller.suaKho(id, newTenKho, newDiaChi);
                if (response.equals("Thành công")) {
                    JOptionPane.showMessageDialog(this, "Cập nhật kho thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + response, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    private void onDeleteKho() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn kho để xóa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int id = (int) model.getValueAt(selectedRow, 0);
            SwingUtilities.invokeLater(() -> {
                String response = controller.xoaKho(id);
                if (response.equals("Thành công")) {
                    JOptionPane.showMessageDialog(this, "Xóa kho thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + response, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
}
