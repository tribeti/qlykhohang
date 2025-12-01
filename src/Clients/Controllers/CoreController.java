package Clients.Controllers;

import Common.Models.Kho;
import Common.Models.NhaCungCap;
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

    // ===== Kho Methods =====
    public List<Kho> getDanhSachKho() {
        return networkController.fetchKhoList();
    }

    public String themKho(String tenKho, String diaChi) {
        return networkController.addKho(tenKho, diaChi);
    }

    public String suaKho(int id, String tenKho, String diaChi) {
        return networkController.updateKho(id, tenKho, diaChi);
    }

    public String xoaKho(int id) {
        return networkController.deleteKho(id);
    }

    // ===== NhaCungCap Methods =====
    public List<NhaCungCap> getDanhSachNCC() {
        return networkController.fetchNCCList();
    }

    public String themNCC(String ten, String email, String diaChi) {
        return networkController.addNCC(ten, email, diaChi);
    }

    public String suaNCC(int id, String ten, String email, String diaChi) {
        return networkController.updateNCC(id, ten, email, diaChi);
    }

    public String xoaNCC(int id) {
        return networkController.deleteNCC(id);
    }
}
