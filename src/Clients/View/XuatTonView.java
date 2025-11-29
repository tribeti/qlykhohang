package Clients.View;

import Clients.Controllers.LoginController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class XuatTonView extends JPanel {
    private JTable tblTon;
    private DefaultTableModel model;

    public XuatTonView() {
        setLayout(new BorderLayout());
        init();
    }

    private void init() {
        JTabbedPane tabs = new JTabbedPane();

        // Tab Xuất kho
        JPanel panelXuat = new JPanel(new BorderLayout());
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Người nhận:"));
        top.add(new JTextField(12));
        top.add(new JLabel("Ngày:"));
        top.add(new JTextField(8));
        panelXuat.add(top, BorderLayout.NORTH);

        JTable tblXuat = new JTable(new DefaultTableModel(
                new String[]{"Mã", "Tên", "SL"}, 0
        ));
        panelXuat.add(new JScrollPane(tblXuat), BorderLayout.CENTER);

        JButton btnLuuXuat = new JButton("Lưu phiếu xuất");
        panelXuat.add(btnLuuXuat, BorderLayout.SOUTH);

        // Tab Tồn kho
        JPanel panelTon = new JPanel(new BorderLayout());
        String[] cols = {"Mã", "Tên", "Nhập tổng", "Xuất tổng", "Tồn"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tblTon = new JTable(model);
        model.addRow(new Object[]{"VT001", "Tua vít", 100, 35, 65});
        panelTon.add(new JScrollPane(tblTon), BorderLayout.CENTER);

        // Thêm tabs vào JTabbedPane
        tabs.addTab("Xuất kho", panelXuat);
        tabs.addTab("Tồn kho", panelTon);

        // Thêm JTabbedPane vào panel chính
        add(tabs, BorderLayout.CENTER);
    }
}
