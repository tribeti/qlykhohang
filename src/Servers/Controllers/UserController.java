package Servers.Controllers;

import Servers.DAOs.UserDAO;
import Servers.Models.User;

public class UserController {
    private final UserDAO dao;

    UserController() {
        this.dao = new UserDAO();
    }

    public boolean Login(String inputUser, String inputPass) {
        User u = dao.checkLogin(inputUser, inputPass);

        return u != null;
    }
}
