package Clients.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NhapKhoView extends JPanel {

    private JComboBox<String> cbNhaCungCap;
    private JTable tableItems;
    private DefaultTableModel itemsModel;

    public NhapKhoView() {
        setLayout(new BorderLayout());
        init();
    }

    private void init() {
        // Panel trên cùng: chọn nhà cung cấp và ngày nhập
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cbNhaCungCap = new JComboBox<>(new String[]{"Công ty A", "Công ty B"});
        top.add(new JLabel("Nhà cung cấp:"));
        top.add(cbNhaCungCap);
        top.add(new JLabel("Ngày nhập:"));
        top.add(new JTextField(10));
        add(top, BorderLayout.NORTH);

        // Bảng danh sách vật tư
        String[] cols = {"Mã", "Tên", "ĐVT", "Số lượng", "Ghi chú"};
        itemsModel = new DefaultTableModel(cols, 0);
        tableItems = new JTable(itemsModel);
        add(new JScrollPane(tableItems), BorderLayout.CENTER);

        // Panel dưới cùng: nút thao tác
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.add(new JButton("Thêm vật tư"));
        bottom.add(new JButton("Xóa mục"));
        bottom.add(new JButton("Lưu phiếu nhập"));
        add(bottom, BorderLayout.SOUTH);
    }
}
