package Clients.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class NetworkController {
    private final String host = "localhost";
    private final int port = 9999;

    public boolean pingServer() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 500); // timeout 0.5s
            return true;
        } catch (IOException e) {
            return false; // không connect được → server chết
        }
    }

    public LoginResult login(String user, String pass) {
        if (!pingServer()) {
            return LoginResult.SERVER_OFFLINE;
        }

        try (
                Socket socket = new Socket(host, port);
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)
        ) {
            socket.setSoTimeout(2000);

            pw.println("LOGIN|" + user + "|" + pass);

            String response = br.readLine();

            if (response == null) {
                return LoginResult.BAD_RESPONSE; // server ko trả gì
            }

            return switch (response) {
                case "OK" -> LoginResult.SUCCESS;
                case "FAIL" -> LoginResult.WRONG_CREDENTIALS;
                default -> LoginResult.BAD_RESPONSE; // server trả rác
            };

        } catch (ConnectException ce) {
            return LoginResult.SERVER_OFFLINE;
        } catch (SocketTimeoutException te) {
            return LoginResult.CONNECTION_FAILED; // timeout
        } catch (IOException e) {
            return LoginResult.CONNECTION_FAILED; // lỗi IO
        } catch (Exception e) {
            e.printStackTrace();
            return LoginResult.BAD_RESPONSE;
        }
    }
}
