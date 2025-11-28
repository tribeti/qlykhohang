package Servers.Controllers;

import Servers.DAOs.NhaCungCapDAO;
import Servers.Models.NhaCungCap;

import java.util.List;

public class NhaCungCapController {

    private final NhaCungCapDAO dao;

    public NhaCungCapController() {
        this.dao = new NhaCungCapDAO();
    }

    public List<NhaCungCap> getDanhSachNCC() {
        return dao.getAll();
    }

    public String themNCC(String ten, String email, String diaChi) {
        if (ten == null || ten.trim().isEmpty()) {
            return "Tên nhà cung cấp không được để trống!";
        }

        NhaCungCap ncc = new NhaCungCap(ten, email, diaChi);

        if (dao.add(ncc)) {
            return "Thành công";
        } else {
            return "Lỗi khi thêm vào Database!";
        }
    }

    public String suaNCC(int id, String ten, String email, String diaChi) {
        if (ten == null || ten.trim().isEmpty()) {
            return "Tên nhà cung cấp không được để trống!";
        }

        NhaCungCap ncc = new NhaCungCap();
        ncc.setId(id);
        ncc.setTenNhaCungCap(ten);
        ncc.setEmail(email);
        ncc.setDiaChi(diaChi);

        if (dao.update(ncc)) {
            return "Thành công";
        } else {
            return "Lỗi khi cập nhật!";
        }
    }

    public String xoaNCC(int id) {
        if (dao.delete(id)) {
            return "Thành công";
        } else {
            return "Không thể xóa (Có thể NCC này đang được sử dụng)!";
        }
    }
}