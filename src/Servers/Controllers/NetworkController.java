package Servers.Controllers;

import Servers.Models.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkController {
    private final UserController userController = new UserController();

    public void startServer() {
        try (ServerSocket server = new ServerSocket(9999)) {
            System.out.println("Server started on port 9999");

            while (true) {
                Socket socket = server.accept();
                new Thread(() -> handleClient(socket)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket socket) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String line = br.readLine();
            if (line == null) {
                return;
            }
            String[] parts = line.split("\\|");

            if (parts[0].equals("LOGIN")) {
                String user = parts[1];
                String pass = parts[2];

                User u = userController.Login(user, pass);
                if (u != null) {
                    pw.println("OK|" + u.getRole());
                } else {
                    pw.println("FAIL");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
