package Clients.Controllers;

import Common.Models.VatTu;

import java.util.List;

public class CoreController {
    private final NetworkController networkController = new NetworkController();

    public NetworkController getNetworkController() {
        return networkController;
    }

    public List<VatTu> getDanhSachVatTu() {
        return networkController.fetchProducts();
    }

    public String themVatTu(VatTu vatTu) {
        return networkController.addProduct(vatTu);
    }

    public String suaVatTu(VatTu vatTu) {
        return networkController.updateProduct(vatTu);
    }

    public String xoaVatTu(int id) {
        return networkController.deleteProduct(id);
    }
}
