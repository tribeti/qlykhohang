package Clients.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class NetworkController {
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

            return switch (response) {
                case "OK" -> LoginResult.SUCCESS;
                case "FAIL" -> LoginResult.WRONG_CREDENTIALS;
                default -> LoginResult.BAD_RESPONSE;
            };

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