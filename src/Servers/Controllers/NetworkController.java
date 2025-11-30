package Servers.Controllers;

import Servers.Models.User;
import Servers.Models.VatTu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String line = br.readLine();
            if (line == null) {
                return;
            }
            String[] parts = line.split("\\|", 2);

            if (parts[0].equals("LOGIN")) {
                String[] loginParts = line.split("\\|");
                String user = loginParts[1];
                String pass = loginParts[2];

                User u = userController.Login(user, pass);
                if (u != null) {
                    pw.println("OK|" + u.getRole());
                } else {
                    pw.println("FAIL");
                }
            } else if (parts[0].equals("FETCH_PRODUCTS")) {
                List<VatTu> danhSach = vatTuController.getDanhSachVatTu();
                String json = convertToJson(danhSach);
                pw.println("OK|" + json);
            }

        } catch (Exception e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }

    private String convertToJson(List<VatTu> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            VatTu vt = list.get(i);
            sb.append("{");
            sb.append("\"id\":").append(vt.getId()).append(",");
            sb.append("\"tenVatTu\":\"").append(escapeJson(vt.getTenVatTu())).append("\",");
            sb.append("\"nhaCungCapId\":").append(vt.getNhaCungCapId()).append(",");
            sb.append("\"donViTinh\":\"").append(escapeJson(vt.getDonViTinh())).append("\",");
            sb.append("\"giaTien\":").append(vt.getGiaTien()).append(",");
            sb.append("\"soLuong\":").append(vt.getSoLuong()).append(",");
            sb.append("\"moTa\":\"").append(escapeJson(vt.getMoTa())).append("\"");
            sb.append("}");
            if (i < list.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    private String escapeJson(String str) {
        if (str == null) return "";
        return str.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}
