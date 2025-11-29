package Servers.Controllers;

public class CoreController {
    static void main() {
        NhaCungCapController nhaCungCapController = new NhaCungCapController();
        VatTuController vatTuController = new VatTuController();
        NetworkController networkController = new NetworkController();

        networkController.startServer();
        System.out.println(nhaCungCapController.getDanhSachNCC());
        System.out.println(vatTuController.getDanhSachVatTu());
    }
}
