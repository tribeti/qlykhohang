package Clients;

import Clients.View.LoginView;
import javax.swing.SwingUtilities;

import javax.swing.*;


public class MainApp {
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
            LoginView login = new LoginView();
            login.setVisible(true);
        });
    }
}