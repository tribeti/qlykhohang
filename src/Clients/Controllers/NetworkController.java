package Clients.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class NetworkController {
    private String currentRole = "USER"; // Biến lưu role tạm thời

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
                    this.currentRole = parts[1]; // Lưu role server gửi về
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
            e.printStackTrace();
            return LoginResult.BAD_RESPONSE;
        }
    }
}