package Clients.View;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UsersView extends JPanel {

    public JTextField txtUsername;
    public JPasswordField txtPassword;
    public JComboBox<String> cbRole;

    public JButton btnThem;
    public JButton btnSua;
    public JButton btnXoa;
    public JButton btnReset;

    public JTable tblUsers;
    public DefaultTableModel tblModel;

    public UsersView() {
        setLayout(new BorderLayout());

        // ---------- FORM PANEL ----------
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin User"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        cbRole = new JComboBox<>(new String[]{"Admin", "Nhân viên"});

        // Row 1
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtUsername, gbc);

        // Row 2
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtPassword, gbc);

        // Row 3
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1;
        formPanel.add(cbRole, gbc);

        add(formPanel, BorderLayout.NORTH);

        // ---------- TABLE PANEL ----------
        tblModel = new DefaultTableModel(
                new Object[]{"ID", "Username", "Role"}, 0
        );
        tblUsers = new JTable(tblModel);
        add(new JScrollPane(tblUsers), BorderLayout.CENTER);

        // ---------- BUTTON PANEL ----------
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnReset = new JButton("Làm mới");

        btnPanel.add(btnThem);
        btnPanel.add(btnSua);
        btnPanel.add(btnXoa);
        btnPanel.add(btnReset);

        add(btnPanel, BorderLayout.SOUTH);
    }
}
