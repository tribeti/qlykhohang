package Servers.Controllers;

import Common.Models.Kho;
import Common.Models.NhaCungCap;
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
    private final KhoController khoController = new KhoController();
    private final NhaCungCapController nccController = new NhaCungCapController();

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
                socket;
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())
        ) {
            Object request = ois.readObject();

            if (request instanceof String requestStr) {
                String[] parts = requestStr.split("\\|", 3);

                switch (parts[0]) {
                    case "LOGIN" -> {
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
                    }
                    case "FETCH_PRODUCTS" -> {
                        System.out.println("Server: Received FETCH_PRODUCTS request. Fetching data...");
                        List<VatTu> danhSach = vatTuController.getDanhSachVatTu();
                        System.out.println("Server: Found " + danhSach.size() + " products. Sending to client...");
                        oos.writeObject(danhSach);
                    }
                    case "ADD_PRODUCT" -> {
                        VatTu vatTu = (VatTu) ois.readObject();
                        String result = vatTuController.themVatTu(
                                vatTu.getTenVatTu(),
                                vatTu.getNhaCungCapId(),
                                vatTu.getDonViTinh(),
                                String.valueOf(vatTu.getGiaTien()),
                                String.valueOf(vatTu.getSoLuong()),
                                vatTu.getMoTa(),
                                vatTu.getNgayTao(),
                                vatTu.getKhoId(),
                                vatTu.isTinhTrang()
                        );
                        oos.writeObject(result);
                    }
                    case "UPDATE_PRODUCT" -> {
                        VatTu vatTu = (VatTu) ois.readObject();
                        String result = vatTuController.suaVatTu(
                                vatTu.getId(),
                                vatTu.getTenVatTu(),
                                vatTu.getNhaCungCapId(),
                                vatTu.getDonViTinh(),
                                String.valueOf(vatTu.getGiaTien()),
                                String.valueOf(vatTu.getSoLuong()),
                                vatTu.getMoTa(),
                                vatTu.getNgayTao(),
                                vatTu.getKhoId(),
                                vatTu.isTinhTrang()
                        );
                        oos.writeObject(result);
                    }
                    case "DELETE_PRODUCT" -> {
                        if (parts.length > 1) {
                            try {
                                int id = Integer.parseInt(parts[1]);
                                String result = vatTuController.xoaVatTu(id);
                                oos.writeObject(result);
                            } catch (NumberFormatException e) {
                                oos.writeObject("Lỗi: ID không hợp lệ");
                            }
                        } else {
                            oos.writeObject("Lỗi: Thiếu ID");
                        }
                    }
                    case "FETCH_KHO" -> {
                        System.out.println("Server: Received FETCH_KHO request. Fetching data...");
                        List<Kho> danhSach = khoController.getKhoList();
                        System.out.println("Server: Found " + danhSach.size() + " khos. Sending to client...");
                        oos.writeObject(danhSach);
                    }
                    case "ADD_KHO" -> {
                        String tenKho = (String) ois.readObject();
                        String diaChi = (String) ois.readObject();
                        String result = khoController.themKho(tenKho, diaChi);
                        oos.writeObject(result);
                    }
                    case "UPDATE_KHO" -> {
                        int id = (int) ois.readObject();
                        String tenKho = (String) ois.readObject();
                        String diaChi = (String) ois.readObject();
                        String result = khoController.suaKho(id, tenKho, diaChi);
                        oos.writeObject(result);
                    }
                    case "DELETE_KHO" -> {
                        if (parts.length > 1) {
                            try {
                                int id = Integer.parseInt(parts[1]);
                                String result = khoController.xoaKho(id);
                                oos.writeObject(result);
                            } catch (NumberFormatException e) {
                                oos.writeObject("Lỗi: ID không hợp lệ");
                            }
                        } else {
                            oos.writeObject("Lỗi: Thiếu ID");
                        }
                    }
                    case "FETCH_NCC" -> {
                        System.out.println("Server: Received FETCH_NCC request. Fetching data...");
                        List<NhaCungCap> danhSach = nccController.getDanhSachNCC();
                        System.out.println("Server: Found " + danhSach.size() + " NCCs. Sending to client...");
                        oos.writeObject(danhSach);
                    }
                    case "ADD_NCC" -> {
                        String ten = (String) ois.readObject();
                        String email = (String) ois.readObject();
                        String diaChi = (String) ois.readObject();
                        String result = nccController.themNCC(ten, email, diaChi);
                        oos.writeObject(result);
                    }
                    case "UPDATE_NCC" -> {
                        int id = (int) ois.readObject();
                        String ten = (String) ois.readObject();
                        String email = (String) ois.readObject();
                        String diaChi = (String) ois.readObject();
                        String result = nccController.suaNCC(id, ten, email, diaChi);
                        oos.writeObject(result);
                    }
                    case "DELETE_NCC" -> {
                        if (parts.length > 1) {
                            try {
                                int id = Integer.parseInt(parts[1]);
                                String result = nccController.xoaNCC(id);
                                oos.writeObject(result);
                            } catch (NumberFormatException e) {
                                oos.writeObject("Lỗi: ID không hợp lệ");
                            }
                        } else {
                            oos.writeObject("Lỗi: Thiếu ID");
                        }
                    }
                }
            }
            oos.flush();

        } catch (Exception e) {
            System.err.println("Server error: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
        }
    }
}
