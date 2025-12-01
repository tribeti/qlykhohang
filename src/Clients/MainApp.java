package Clients;

import Clients.Views.LoginView;

import javax.swing.*;


public class MainApp {
    static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginView login = new LoginView();
            login.setVisible(true);
        });
    }
}