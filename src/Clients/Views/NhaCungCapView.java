package Clients.Views;

import Clients.Controllers.CoreController;
import Common.Models.NhaCungCap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class NhaCungCapView extends JPanel {
    private final CoreController controller = new CoreController();
    private DefaultTableModel model;
    private JTable table;

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

        btnAdd.addActionListener(_ -> onAddNCC());
        btnEdit.addActionListener(_ -> onEditNCC());
        btnDelete.addActionListener(_ -> onDeleteNCC());
    }

    private void loadData() {
        model.setRowCount(0);

        SwingUtilities.invokeLater(() -> {
            try {
                List<NhaCungCap> danhSach = controller.getDanhSachNCC();

                if (danhSach == null || danhSach.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không có dữ liệu hoặc không thể kết nối server!",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                for (NhaCungCap ncc : danhSach) {
                    model.addRow(new Object[]{
                            ncc.getId(),
                            ncc.getTenNhaCungCap(),
                            ncc.getEmail(),
                            ncc.getDiaChi()
                    });
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + ex.getMessage(),
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void onAddNCC() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtTen = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtDiaChi = new JTextField();

        panel.add(new JLabel("Tên nhà cung cấp:"));
        panel.add(txtTen);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Địa chỉ:"));
        panel.add(txtDiaChi);

        int result = JOptionPane.showConfirmDialog(this, panel, "Thêm nhà cung cấp", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String ten = txtTen.getText().trim();
            String email = txtEmail.getText().trim();
            String diaChi = txtDiaChi.getText().trim();

            if (ten.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SwingUtilities.invokeLater(() -> {
                String response = controller.themNCC(ten, email, diaChi);
                if (response.equals("Thành công")) {
                    JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + response, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    private void onEditNCC() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để sửa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int id = (int) model.getValueAt(selectedRow, 0);
        String ten = (String) model.getValueAt(selectedRow, 1);
        String email = (String) model.getValueAt(selectedRow, 2);
        String diaChi = (String) model.getValueAt(selectedRow, 3);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtTen = new JTextField(ten);
        JTextField txtEmail = new JTextField(email);
        JTextField txtDiaChi = new JTextField(diaChi);

        panel.add(new JLabel("Tên nhà cung cấp:"));
        panel.add(txtTen);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Địa chỉ:"));
        panel.add(txtDiaChi);

        int result = JOptionPane.showConfirmDialog(this, panel, "Sửa nhà cung cấp", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String newTen = txtTen.getText().trim();
            String newEmail = txtEmail.getText().trim();
            String newDiaChi = txtDiaChi.getText().trim();

            if (newTen.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SwingUtilities.invokeLater(() -> {
                String response = controller.suaNCC(id, newTen, newEmail, newDiaChi);
                if (response.equals("Thành công")) {
                    JOptionPane.showMessageDialog(this, "Cập nhật nhà cung cấp thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + response, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    private void onDeleteNCC() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để xóa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int id = (int) model.getValueAt(selectedRow, 0);
            SwingUtilities.invokeLater(() -> {
                String response = controller.xoaNCC(id);
                if (response.equals("Thành công")) {
                    JOptionPane.showMessageDialog(this, "Xóa nhà cung cấp thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + response, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
}