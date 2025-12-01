package Clients.Controllers;

import Common.Models.Kho;
import Common.Models.NhaCungCap;
import Common.Models.VatTu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class NetworkController {
    private String currentRole = "USER";

    public String getCurrentRole() {
        return currentRole;
    }

    public LoginResult login(String user, String pass) {
        String host = "localhost";
        int port = 9999;
        try (
                Socket socket = new Socket(host, port);
                // Sử dụng ObjectStream cho cả gửi và nhận
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            socket.setSoTimeout(2000);

            // Gửi yêu cầu đăng nhập dưới dạng String
            oos.writeObject("LOGIN|" + user + "|" + pass);
            oos.flush();

            // Đọc phản hồi từ server
            String response = (String) ois.readObject();

            if (response == null) {
                return LoginResult.BAD_RESPONSE;
            }

            if (response.startsWith("OK")) {
                String[] parts = response.split("\\|");
                if (parts.length > 1) {
                    this.currentRole = parts[1];
                }
                return LoginResult.SUCCESS;
            } else if (response.equals("FAIL")) {
                return LoginResult.WRONG_CREDENTIALS;
            } else {
                return LoginResult.BAD_RESPONSE;
            }

        } catch (ConnectException ce) {
            return LoginResult.SERVER_OFFLINE;
        } catch (IOException | ClassNotFoundException e) {
            return LoginResult.CONNECTION_FAILED;
        } catch (Exception e) {
            System.err.println("Login error: " + e.getMessage());
            return LoginResult.BAD_RESPONSE;
        }
    }

    @SuppressWarnings("unchecked")
    public List<VatTu> fetchProducts() {
        String host = "localhost";
        int port = 9999;
        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            socket.setSoTimeout(5000);
            oos.writeObject("FETCH_PRODUCTS");
            oos.flush();
            Object response = ois.readObject();
            if (response instanceof List<?>) {
                return (List<VatTu>) response;
            } else {
                System.err.println("Client: Received unexpected object type: " + response.getClass().getName());
                return new ArrayList<>();
            }

        } catch (ConnectException ce) {
            System.err.println("Client: Server offline.");
            return new ArrayList<>();
        } catch (SocketTimeoutException te) {
            System.err.println("Client: Connection timeout.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Client: Error reading from server - " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Client: An unexpected error occurred - " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public String addProduct(VatTu vatTu) {
        String host = "localhost";
        int port = 9999;
        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            socket.setSoTimeout(5000);
            oos.writeObject("ADD_PRODUCT");
            oos.writeObject(vatTu);
            oos.flush();
            String response = (String) ois.readObject();
            return response != null ? response : "Lỗi: Không nhận được phản hồi từ server";
        } catch (ConnectException ce) {
            return "Lỗi: Server offline";
        } catch (SocketTimeoutException te) {
            return "Lỗi: Kết nối timeout";
        } catch (IOException | ClassNotFoundException e) {
            return "Lỗi: " + e.getMessage();
        }
    }

    public String updateProduct(VatTu vatTu) {
        String host = "localhost";
        int port = 9999;
        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            socket.setSoTimeout(5000);
            oos.writeObject("UPDATE_PRODUCT");
            oos.writeObject(vatTu);
            oos.flush();
            String response = (String) ois.readObject();
            return response != null ? response : "Lỗi: Không nhận được phản hồi từ server";
        } catch (ConnectException ce) {
            return "Lỗi: Server offline";
        } catch (SocketTimeoutException te) {
            return "Lỗi: Kết nối timeout";
        } catch (IOException | ClassNotFoundException e) {
            return "Lỗi: " + e.getMessage();
        }
    }

    public String deleteProduct(int id) {
        String host = "localhost";
        int port = 9999;
        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            socket.setSoTimeout(5000);
            oos.writeObject("DELETE_PRODUCT|" + id);
            oos.flush();
            String response = (String) ois.readObject();
            return response != null ? response : "Lỗi: Không nhận được phản hồi từ server";
        } catch (ConnectException ce) {
            return "Lỗi: Server offline";
        } catch (SocketTimeoutException te) {
            return "Lỗi: Kết nối timeout";
        } catch (IOException | ClassNotFoundException e) {
            return "Lỗi: " + e.getMessage();
        }
    }

    // ===== Kho Methods =====
    @SuppressWarnings("unchecked")
    public List<Kho> fetchKhoList() {
        String host = "localhost";
        int port = 9999;
        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            socket.setSoTimeout(5000);
            oos.writeObject("FETCH_KHO");
            oos.flush();
            Object response = ois.readObject();
            if (response instanceof List<?>) {
                return (List<Kho>) response;
            } else {
                System.err.println("Client: Received unexpected object type: " + response.getClass().getName());
                return new ArrayList<>();
            }
        } catch (ConnectException ce) {
            System.err.println("Client: Server offline.");
            return new ArrayList<>();
        } catch (SocketTimeoutException te) {
            System.err.println("Client: Connection timeout.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Client: Error reading from server - " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public String addKho(String tenKho, String diaChi) {
        String host = "localhost";
        int port = 9999;
        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            socket.setSoTimeout(5000);
            oos.writeObject("ADD_KHO");
            oos.writeObject(tenKho);
            oos.writeObject(diaChi);
            oos.flush();
            String response = (String) ois.readObject();
            return response != null ? response : "Lỗi: Không nhận được phản hồi từ server";
        } catch (ConnectException ce) {
            return "Lỗi: Server offline";
        } catch (SocketTimeoutException te) {
            return "Lỗi: Kết nối timeout";
        } catch (IOException | ClassNotFoundException e) {
            return "Lỗi: " + e.getMessage();
        }
    }

    public String updateKho(int id, String tenKho, String diaChi) {
        String host = "localhost";
        int port = 9999;
        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            socket.setSoTimeout(5000);
            oos.writeObject("UPDATE_KHO");
            oos.writeObject(id);
            oos.writeObject(tenKho);
            oos.writeObject(diaChi);
            oos.flush();
            String response = (String) ois.readObject();
            return response != null ? response : "Lỗi: Không nhận được phản hồi từ server";
        } catch (ConnectException ce) {
            return "Lỗi: Server offline";
        } catch (SocketTimeoutException te) {
            return "Lỗi: Kết nối timeout";
        } catch (IOException | ClassNotFoundException e) {
            return "Lỗi: " + e.getMessage();
        }
    }

    public String deleteKho(int id) {
        String host = "localhost";
        int port = 9999;
        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            socket.setSoTimeout(5000);
            oos.writeObject("DELETE_KHO|" + id);
            oos.flush();
            String response = (String) ois.readObject();
            return response != null ? response : "Lỗi: Không nhận được phản hồi từ server";
        } catch (ConnectException ce) {
            return "Lỗi: Server offline";
        } catch (SocketTimeoutException te) {
            return "Lỗi: Kết nối timeout";
        } catch (IOException | ClassNotFoundException e) {
            return "Lỗi: " + e.getMessage();
        }
    }

    // ===== NhaCungCap Methods =====
    @SuppressWarnings("unchecked")
    public List<NhaCungCap> fetchNCCList() {
        String host = "localhost";
        int port = 9999;
        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            socket.setSoTimeout(5000);
            oos.writeObject("FETCH_NCC");
            oos.flush();
            Object response = ois.readObject();
            if (response instanceof List<?>) {
                return (List<NhaCungCap>) response;
            } else {
                System.err.println("Client: Received unexpected object type");
                return new ArrayList<>();
            }
        } catch (ConnectException ce) {
            System.err.println("Client: Server offline.");
            return new ArrayList<>();
        } catch (SocketTimeoutException te) {
            System.err.println("Client: Connection timeout.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Client: Error reading from server - " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public String addNCC(String ten, String email, String diaChi) {
        String host = "localhost";
        int port = 9999;
        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            socket.setSoTimeout(5000);
            oos.writeObject("ADD_NCC");
            oos.writeObject(ten);
            oos.writeObject(email);
            oos.writeObject(diaChi);
            oos.flush();
            String response = (String) ois.readObject();
            return response != null ? response : "Lỗi: Không nhận được phản hồi từ server";
        } catch (ConnectException ce) {
            return "Lỗi: Server offline";
        } catch (SocketTimeoutException te) {
            return "Lỗi: Kết nối timeout";
        } catch (IOException | ClassNotFoundException e) {
            return "Lỗi: " + e.getMessage();
        }
    }

    public String updateNCC(int id, String ten, String email, String diaChi) {
        String host = "localhost";
        int port = 9999;
        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            socket.setSoTimeout(5000);
            oos.writeObject("UPDATE_NCC");
            oos.writeObject(id);
            oos.writeObject(ten);
            oos.writeObject(email);
            oos.writeObject(diaChi);
            oos.flush();
            String response = (String) ois.readObject();
            return response != null ? response : "Lỗi: Không nhận được phản hồi từ server";
        } catch (ConnectException ce) {
            return "Lỗi: Server offline";
        } catch (SocketTimeoutException te) {
            return "Lỗi: Kết nối timeout";
        } catch (IOException | ClassNotFoundException e) {
            return "Lỗi: " + e.getMessage();
        }
    }

    public String deleteNCC(int id) {
        String host = "localhost";
        int port = 9999;
        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            socket.setSoTimeout(5000);
            oos.writeObject("DELETE_NCC|" + id);
            oos.flush();
            String response = (String) ois.readObject();
            return response != null ? response : "Lỗi: Không nhận được phản hồi từ server";
        } catch (ConnectException ce) {
            return "Lỗi: Server offline";
        } catch (SocketTimeoutException te) {
            return "Lỗi: Kết nối timeout";
        } catch (IOException | ClassNotFoundException e) {
            return "Lỗi: " + e.getMessage();
        }
    }
}
