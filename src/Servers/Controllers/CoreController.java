package Servers.Controllers;

public class CoreController {
    // cho muc dich testing ko phai la class hoan chinh
    static void main() {
        NhaCungCapController nhaCungCapController = new NhaCungCapController();
        NetworkController networkController = new NetworkController();

        System.out.println(nhaCungCapController.getDanhSachNCC());
        networkController.startServer();
    }
}
