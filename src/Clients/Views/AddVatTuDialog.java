package Clients.Views;

import Common.Models.VatTu;

import javax.swing.*;
import java.time.LocalDateTime;

public class AddVatTuDialog extends JDialog {
    private JTextField txtTenVatTu;
    private JTextField txtNhaCungCapId;
    private JTextField txtDonViTinh;
    private JTextField txtGiaTien;
    private JTextField txtSoLuong;
    private JTextArea txtMoTa;
    private JTextField txtKhoId;
    private JCheckBox chkTinhTrang;
    private VatTu result = null;

    public AddVatTuDialog(JFrame parent) {
        super(parent, "Thêm vật tư", true);
        initComponents();
        setSize(400, 450);
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tên vật tư
        panel.add(new JLabel("Tên vật tư:"));
        txtTenVatTu = new JTextField();
        panel.add(txtTenVatTu);
        panel.add(Box.createVerticalStrut(5));

        // Nhà cung cấp ID
        panel.add(new JLabel("Nhà cung cấp ID:"));
        txtNhaCungCapId = new JTextField();
        panel.add(txtNhaCungCapId);
        panel.add(Box.createVerticalStrut(5));

        // Đơn vị tính
        panel.add(new JLabel("Đơn vị tính:"));
        txtDonViTinh = new JTextField();
        panel.add(txtDonViTinh);
        panel.add(Box.createVerticalStrut(5));

        // Giá tiền
        panel.add(new JLabel("Giá tiền:"));
        txtGiaTien = new JTextField();
        panel.add(txtGiaTien);
        panel.add(Box.createVerticalStrut(5));

        // Số lượng
        panel.add(new JLabel("Số lượng:"));
        txtSoLuong = new JTextField();
        panel.add(txtSoLuong);
        panel.add(Box.createVerticalStrut(5));

        // Kho ID
        panel.add(new JLabel("Kho ID:"));
        txtKhoId = new JTextField();
        panel.add(txtKhoId);
        panel.add(Box.createVerticalStrut(5));

        // Mô tả
        panel.add(new JLabel("Mô tả:"));
        txtMoTa = new JTextArea(3, 20);
        txtMoTa.setLineWrap(true);
        txtMoTa.setWrapStyleWord(true);
        panel.add(new JScrollPane(txtMoTa));
        panel.add(Box.createVerticalStrut(5));

        // Tình trạng
        chkTinhTrang = new JCheckBox("Tốt (không chọn = Hư hỏng)");
        chkTinhTrang.setSelected(true);
        panel.add(chkTinhTrang);
        panel.add(Box.createVerticalStrut(10));

        // Buttons
        JPanel btnPanel = new JPanel();
        JButton btnOK = new JButton("OK");
        JButton btnCancel = new JButton("Hủy");
        btnPanel.add(btnOK);
        btnPanel.add(btnCancel);
        panel.add(btnPanel);

        btnOK.addActionListener(_ -> onOK());
        btnCancel.addActionListener(_ -> onCancel());

        add(panel);
    }

    private void onOK() {
        try {
            if (txtTenVatTu.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên vật tư không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (txtDonViTinh.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Đơn vị tính không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int nccId = Integer.parseInt(txtNhaCungCapId.getText());
            double giaTien = Double.parseDouble(txtGiaTien.getText());
            int soLuong = Integer.parseInt(txtSoLuong.getText());
            int khoId = Integer.parseInt(txtKhoId.getText());

            if (giaTien < 0 || soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Giá và số lượng phải >= 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            result = new VatTu(
                    txtTenVatTu.getText(),
                    nccId,
                    txtDonViTinh.getText(),
                    giaTien,
                    soLuong,
                    txtMoTa.getText(),
                    LocalDateTime.now(),
                    khoId,
                    chkTinhTrang.isSelected()
            );

            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Các trường số không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        result = null;
        dispose();
    }

    public VatTu getResult() {
        return result;
    }
}
