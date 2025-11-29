package Clients.Controllers;

import Clients.View.LoginView;
import javax.swing.*;

public class LoginController {

    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
        initEvents();
    }

    private void initEvents() {
        view.btnLogin.addActionListener(e -> login());
    }

    private void login() {
        String username = view.txtUsername.getText();
        String password = new String(view.txtPassword.getPassword());

        // ví dụ validate đơn giản
        if(username.isEmpty() || password.isEmpty()) {
            view.lblMessage.setText("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        // TODO: gọi server để kiểm tra
        boolean success = username.equals("admin") && password.equals("123"); 

        if(success) {
            view.lblMessage.setText("Đăng nhập thành công!");
            // mở MainView
            SwingUtilities.invokeLater(() -> {
                view.setVisible(false);
                new MainView(username).setVisible(true);
            });
        } else {
            view.lblMessage.setText("Sai tài khoản hoặc mật khẩu!");
        }
    }
}
