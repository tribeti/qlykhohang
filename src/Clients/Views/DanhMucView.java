package Clients.Views;

import Clients.Controllers.CoreController;
import Common.Models.VatTu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DanhMucView extends JPanel {

    private final CoreController controller = new CoreController();
    private DefaultTableModel model;

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
        String[] cols = {"Mã", "Tên vật tư", "Đơn vị tính", "Giá", "Số lượng", "Mô tả", "Nhà cung cấp"};
        model = new DefaultTableModel(cols, 0) {
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

        loadData();
        btnRefresh.addActionListener(_ -> loadData());
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

                // Đổ dữ liệu vào bảng
                for (VatTu vt : danhSach) {
                    model.addRow(new Object[]{
                            vt.getId(),
                            vt.getTenVatTu(),
                            vt.getDonViTinh(),
                            vt.getGiaTien(),
                            vt.getSoLuong(),
                            vt.getMoTa(),
                            vt.getNhaCungCapId()
                    });
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + ex.getMessage(),
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
