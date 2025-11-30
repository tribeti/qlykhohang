package Servers.Controllers;

import Servers.DAOs.UserDAO;
import Servers.Models.User;

public class UserController {
    private final UserDAO dao;

    UserController() {
        this.dao = new UserDAO();
    }

    public User Login(String inputUser, String inputPass) {
        return dao.checkLogin(inputUser, inputPass);
    }
}
