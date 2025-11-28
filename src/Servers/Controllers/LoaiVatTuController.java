package Servers.Controllers;

import Servers.DAOs.LoaiVatTuDAO;
import Servers.Models.LoaiVatTu;

import java.util.List;

public class LoaiVatTuController {

    private final LoaiVatTuDAO dao;

    public LoaiVatTuController() {
        this.dao = new LoaiVatTuDAO();
    }

    public List<LoaiVatTu> getDanhSachVatTu() {
        return dao.ReadAll();
    }

    public String themVatTu(String ten, String moTa) {
        if (ten == null || ten.trim().isEmpty()) {
            return "Tên loại vật tư không được để trống!";
        }

        LoaiVatTu lvt = new LoaiVatTu(ten, moTa);
        if (dao.Create(lvt)) {
            return "Thành công";
        } else {
            return "Lỗi khi thêm vào CSDL!";
        }
    }

    public String suaVatTu(int id, String ten, String moTa) {
        if (ten == null || ten.trim().isEmpty()) {
            return "Tên loại không được rỗng!";
        }

        LoaiVatTu lvt = new LoaiVatTu();
        lvt.setId(id);
        lvt.setTenLoai(ten);
        lvt.setMoTa(moTa);

        if (dao.Update(lvt)) {
            return "Thành công";
        } else {
            return "Lỗi cập nhật!";
        }
    }

    // Xử lý logic xóa
    public String xoaVatTu(int id) {
        if (dao.Delete(id)) {
            return "Thành công";
        } else {
            return "Lỗi xóa (Có thể id không tồn tại)!";
        }
    }
}