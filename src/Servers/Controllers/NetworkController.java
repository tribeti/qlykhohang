package Servers.Controllers;

import Common.Models.VatTu;
import Servers.Models.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class NetworkController {
    private final UserController userController = new UserController();
    private final VatTuController vatTuController = new VatTuController();

    public void startServer() {
        try (ServerSocket server = new ServerSocket(9999)) {
            System.out.println("Server started on port 9999");

            while (true) {
                Socket socket = server.accept();
                new Thread(() -> handleClient(socket)).start();
            }

        } catch (Exception e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    private void handleClient(Socket socket) {
        try (
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())
        ) {
            String request = (String) ois.readObject();
            if (request == null) {
                return;
            }

            String[] parts = request.split("\\|");

            if (parts[0].equals("LOGIN")) {
                if (parts.length >= 3) {
                    String user = parts[1];
                    String pass = parts[2];

                    User u = userController.Login(user, pass);
                    if (u != null) {
                        oos.writeObject("OK|" + u.getRole());
                    } else {
                        oos.writeObject("FAIL");
                    }
                } else {
                    oos.writeObject("FAIL");
                }
            } else if (parts[0].equals("FETCH_PRODUCTS")) {
                System.out.println("Server: Received FETCH_PRODUCTS request. Fetching data...");
                List<VatTu> danhSach = vatTuController.getDanhSachVatTu();
                System.out.println("Server: Found " + danhSach.size() + " products. Sending to client...");
                oos.writeObject(danhSach);
            }
            oos.flush();

        } catch (Exception e) {
            System.err.println("Server error: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
        }
    }
}
