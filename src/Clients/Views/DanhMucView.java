package Clients.Views;

import Clients.Controllers.CoreController;
import Common.Models.VatTu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DanhMucView extends JPanel {

    private final CoreController controller = new CoreController();
    private DefaultTableModel model;
    private JTable table;

    public DanhMucView() {
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
        String[] cols = {"Mã", "Tên vật tư", "Đơn vị tính", "Giá", "Số lượng", "Mô tả", "Nhà cung cấp", "Kho ID", "Ngày chỉnh sửa", "Tình trạng"};
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

        btnAdd.addActionListener(_ -> onAddProduct());
        btnEdit.addActionListener(_ -> onEditProduct());
        btnDelete.addActionListener(_ -> onDeleteProduct());
    }

    private void loadData() {
        model.setRowCount(0);

        SwingUtilities.invokeLater(() -> {
            try {
                List<VatTu> danhSach = controller.getDanhSachVatTu();

                if (danhSach == null || danhSach.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không có dữ liệu hoặc không thể kết nối server!",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


                for (VatTu vt : danhSach) {
                    String ngayTaoHienThi = "";
                    if (vt.getNgayTao() != null) {
                        ngayTaoHienThi = vt.getNgayTao().format(formatter);
                    }

                    model.addRow(new Object[]{
                            vt.getId(),
                            vt.getTenVatTu(),
                            vt.getDonViTinh(),
                            vt.getGiaTien(),
                            vt.getSoLuong(),
                            vt.getMoTa(),
                            vt.getNhaCungCapId(),
                            vt.getKhoId(),
                            ngayTaoHienThi,
                            vt.isTinhTrang() ? "Tốt" : "Hư hỏng"
                    });
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + ex.getMessage(),
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void onAddProduct() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        AddVatTuDialog dialog = new AddVatTuDialog(frame);
        dialog.setVisible(true);

        VatTu result = dialog.getResult();
        if (result != null) {
            SwingUtilities.invokeLater(() -> {
                String response = controller.themVatTu(result);
                if (response.equals("Thành công")) {
                    JOptionPane.showMessageDialog(this, "Thêm vật tư thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + response, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    private void onEditProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vật tư để sửa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Lấy dữ liệu từ bảng
        int id = (int) model.getValueAt(selectedRow, 0);
        String tenVatTu = (String) model.getValueAt(selectedRow, 1);
        String donViTinh = (String) model.getValueAt(selectedRow, 2);
        double giaTien = (double) model.getValueAt(selectedRow, 3);
        int soLuong = (int) model.getValueAt(selectedRow, 4);
        String moTa = (String) model.getValueAt(selectedRow, 5);
        int nccId = (int) model.getValueAt(selectedRow, 6);
        int khoId = (int) model.getValueAt(selectedRow, 7);
        String tinhTrangStr = (String) model.getValueAt(selectedRow, 9);
        boolean tinhTrang = tinhTrangStr.equals("Tốt");

        VatTu vatTu = new VatTu(id, tenVatTu, nccId, donViTinh, giaTien, soLuong, moTa, null, khoId, tinhTrang);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        EditVatTuDialog dialog = new EditVatTuDialog(frame, vatTu);
        dialog.setVisible(true);

        VatTu result = dialog.getResult();
        if (result != null) {
            SwingUtilities.invokeLater(() -> {
                String response = controller.suaVatTu(result);
                if (response.equals("Thành công")) {
                    JOptionPane.showMessageDialog(this, "Cập nhật vật tư thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + response, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    private void onDeleteProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vật tư để xóa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int id = (int) model.getValueAt(selectedRow, 0);
            SwingUtilities.invokeLater(() -> {
                String response = controller.xoaVatTu(id);
                if (response.equals("Thành công")) {
                    JOptionPane.showMessageDialog(this, "Xóa vật tư thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + response, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
}
