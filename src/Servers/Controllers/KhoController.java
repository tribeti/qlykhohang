package Servers.Controllers;

import Common.Models.Kho;
import Servers.DAOs.KhoDAO;

import java.util.List;

public class KhoController {
    private final KhoDAO dao;

    public KhoController() {
        this.dao = new KhoDAO();
    }

    public List<Kho> getKhoList() {
        return dao.getAll();
    }

    public String themKho(String tenKho, String diaChi) {
        if (tenKho == null || tenKho.trim().isEmpty()) {
            return "Tên kho không được để trống";
        }

        Kho kho = new Kho(tenKho, diaChi);

        if (dao.add(kho)) {
            return "Thành công";
        } else {
            return "Lỗi khi thêm vào Database!";
        }
    }

    public String suaKho(int id, String tenKho, String diaChi) {
        if (tenKho == null || tenKho.trim().isEmpty()) {
            return "Tên kho không được để trống!";
        }

        Kho kho = new Kho();
        kho.setId(id);
        kho.setTenKho(tenKho);
        kho.setDiaChi(diaChi);

        if (dao.update(kho)) {
            return "Thành công";
        } else {
            return "Lỗi khi cập nhật!";
        }
    }

    public String xoaKho(int id) {
        if (dao.delete(id)) {
            return "Thành công";
        } else {
            return "Không thể xóa (Có thể Kho này đang được sử dụng)!";
        }
    }
}
