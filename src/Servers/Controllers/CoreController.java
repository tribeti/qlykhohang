package Servers.Controllers;

public class CoreController {
    // cho muc dich testing ko phai la class hoan chinh
    static void main() {
        NhaCungCapController nhaCungCapController = new NhaCungCapController();
        VatTuController vatTuController = new VatTuController();
        NetworkController networkController = new NetworkController();
        KhoController khoController = new KhoController();

        System.out.println(nhaCungCapController.getDanhSachNCC());
        System.out.println(vatTuController.getDanhSachVatTu());
        System.out.println(khoController.getKhoList());
        networkController.startServer();
    }
}
