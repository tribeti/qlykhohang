package Clients.View;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UserLogsView extends JPanel {

    public JTable tblLogs;
    public DefaultTableModel tblModel;

    public JButton btnRefresh;
    public JComboBox<String> cbUserFilter;

    public UserLogsView() {
        setLayout(new BorderLayout());

        // ---------- TOP PANEL: filter / refresh ----------
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("User:"));
        cbUserFilter = new JComboBox<>();
        cbUserFilter.addItem("Tất cả");
        topPanel.add(cbUserFilter);

        btnRefresh = new JButton("Refresh");
        topPanel.add(btnRefresh);

        add(topPanel, BorderLayout.NORTH);

        // ---------- TABLE PANEL ----------
        tblModel = new DefaultTableModel(
                new Object[]{"ID", "User", "Action", "Created At"}, 0
        );
        tblLogs = new JTable(tblModel);
        tblLogs.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(tblLogs);
        add(scrollPane, BorderLayout.CENTER);
    }
}
