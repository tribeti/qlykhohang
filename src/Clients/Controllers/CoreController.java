package Clients.Controllers;

import Clients.Models.VatTu;

import java.util.List;

public class CoreController {
    private final NetworkController networkController = new NetworkController();

    public NetworkController getNetworkController() {
        return networkController;
    }

    public List<VatTu> getDanhSachVatTu() {
        return networkController.fetchProducts();
    }
}
