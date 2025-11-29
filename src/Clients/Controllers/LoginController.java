package Clients.Controllers;

import Clients.Views.LoginView;
import Clients.Views.MainView;

import javax.swing.*;

public class LoginController {

    private final LoginView view;
    private final NetworkController net = new NetworkController();

    public LoginController(LoginView view) {
        this.view = view;
        initEvents();
    }

    private void initEvents() {
        view.btnLogin.addActionListener(_ -> login());
    }

    private void login() {
        String username = view.txtUsername.getText();
        String password = new String(view.txtPassword.getPassword());

        // ví dụ validate đơn giản
        if (username.isEmpty() || password.isEmpty()) {
            view.lblMessage.setText("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        LoginResult result = net.login(username, password);

        switch (result) {
            case SUCCESS -> {
                view.lblMessage.setText("Đăng nhập thành công!");

                SwingUtilities.invokeLater(() -> {
                    view.setVisible(false);
                    new MainView(username).setVisible(true);
                });
            }

            case WRONG_CREDENTIALS -> view.lblMessage.setText("Sai tài khoản hoặc mật khẩu!");
            case SERVER_OFFLINE -> view.lblMessage.setText("Server không hoạt động!");
            case CONNECTION_FAILED -> view.lblMessage.setText("Lỗi kết nối đến server!");
            case BAD_RESPONSE -> view.lblMessage.setText("Server trả dữ liệu không hợp lệ!");
        }
    }
}
