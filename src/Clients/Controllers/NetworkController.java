package Clients.Controllers;

import Clients.Models.VatTu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)
        ) {
            socket.setSoTimeout(2000);

            pw.println("LOGIN|" + user + "|" + pass);

            String response = br.readLine();

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
        } catch (SocketTimeoutException te) {
            return LoginResult.CONNECTION_FAILED;
        } catch (IOException e) {
            return LoginResult.CONNECTION_FAILED;
        } catch (Exception e) {
            System.err.println("Login error: " + e.getMessage());
            return LoginResult.BAD_RESPONSE;
        }
    }

    public List<VatTu> fetchProducts() {
        String host = "localhost";
        int port = 9999;
        try (
                Socket socket = new Socket(host, port);
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)
        ) {
            socket.setSoTimeout(5000);

            pw.println("FETCH_PRODUCTS");

            String response = br.readLine();

            if (response == null) {
                System.err.println("No response from server");
                return new ArrayList<>();
            }

            if (response.startsWith("OK|")) {
                String json = response.substring(3);
                return parseJsonToVatTu(json);
            } else {
                System.err.println("Server error: " + response);
                return new ArrayList<>();
            }

        } catch (ConnectException ce) {
            System.err.println("Server offline");
            return new ArrayList<>();
        } catch (SocketTimeoutException te) {
            System.err.println("Connection timeout");
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Fetch products error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private List<VatTu> parseJsonToVatTu(String json) {
        List<VatTu> list = new ArrayList<>();
        try {
            // Remove [ and ]
            json = json.trim();
            if (json.startsWith("[")) json = json.substring(1);
            if (json.endsWith("]")) json = json.substring(0, json.length() - 1);

            if (json.isEmpty()) return list;

            // Split by } , {
            String[] items = json.split("\\},\\{");
            for (String item : items) {
                item = item.replace("{", "").replace("}", "").trim();
                VatTu vt = parseObject(item);
                if (vt != null) {
                    list.add(vt);
                }
            }
        } catch (Exception e) {
            System.err.println("Parse error: " + e.getMessage());
        }
        return list;
    }

    private VatTu parseObject(String item) {
        try {
            int id = 0;
            String tenVatTu = "";
            int nhaCungCapId = 0;
            String donViTinh = "";
            double giaTien = 0;
            int soLuong = 0;
            String moTa = "";

            String[] pairs = item.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            for (String pair : pairs) {
                pair = pair.trim();
                int colonIndex = pair.indexOf(":");
                if (colonIndex <= 0) continue;

                String key = pair.substring(0, colonIndex).trim().replace("\"", "");
                String value = pair.substring(colonIndex + 1).trim();

                switch (key) {
                    case "id":
                        id = Integer.parseInt(value);
                        break;
                    case "tenVatTu":
                        tenVatTu = removeQuotes(value);
                        break;
                    case "nhaCungCapId":
                        nhaCungCapId = Integer.parseInt(value);
                        break;
                    case "donViTinh":
                        donViTinh = removeQuotes(value);
                        break;
                    case "giaTien":
                        giaTien = Double.parseDouble(value);
                        break;
                    case "soLuong":
                        soLuong = Integer.parseInt(value);
                        break;
                    case "moTa":
                        moTa = removeQuotes(value);
                        break;
                }
            }

            VatTu vt = new VatTu();
            vt.setId(id);
            vt.setTenVatTu(tenVatTu);
            vt.setNhaCungCapId(nhaCungCapId);
            vt.setDonViTinh(donViTinh);
            vt.setGiaTien(giaTien);
            vt.setSoLuong(soLuong);
            vt.setMoTa(moTa);
            return vt;
        } catch (Exception e) {
            System.err.println("Parse object error: " + e.getMessage());
            return null;
        }
    }

    private String removeQuotes(String str) {
        if (str.startsWith("\"") && str.endsWith("\"")) {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }
}