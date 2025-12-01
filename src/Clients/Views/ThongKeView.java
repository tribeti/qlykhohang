package Clients.Views;

import javax.swing.*;
import java.awt.*;

public class ThongKeView extends JPanel {

    public ThongKeView() {
        setLayout(new BorderLayout());
        init();
    }

    private void init() {
        // Panel trên cùng: lựa chọn loại thống kê và khoảng thời gian
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Loại thống kê:"));
        top.add(new JComboBox<>(new String[]{"Nhập theo tháng", "Xuất theo tháng", "Tồn thấp"}));
        top.add(new JLabel("Khoảng thời gian:"));
        top.add(new JTextField(8));
        add(top, BorderLayout.NORTH);

        // Panel trung tâm: hiển thị biểu đồ
        JPanel center = new JPanel(new BorderLayout());
        center.add(new JLabel("[BIỂU ĐỒ HIỆN THỊ Ở ĐÂY]", SwingConstants.CENTER), BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);
    }
}
