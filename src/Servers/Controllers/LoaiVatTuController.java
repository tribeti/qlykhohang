package Servers.Controllers;

import Servers.DAOs.LoaiVatTuDAO;
import Servers.Models.LoaiVatTu;

import java.util.List;

public class LoaiVatTuController {
    private final LoaiVatTuDAO dao;

    public LoaiVatTuController() {
        this.dao = new LoaiVatTuDAO();
    }

    public List<LoaiVatTu> getLoaiVatTuList() {
        return dao.getAll();
    }

    public String themLoaiVatTu(String tenLoai, String moTa) {
        if (tenLoai == null || tenLoai.trim().isEmpty()) {
            return "Tên không được để trống";
        }

        LoaiVatTu loaiVatTu = new LoaiVatTu(tenLoai, moTa);

        if (dao.add(loaiVatTu)) {
            return "Thành công";
        } else {
            return "Lỗi khi thêm vào Database!";
        }
    }

    public String suaLoaiVatTu(int id, String tenLoai, String moTa) {
        if (tenLoai == null || tenLoai.trim().isEmpty()) {
            return "Tên không được để trống!";
        }

        LoaiVatTu loaiVatTu = new LoaiVatTu();
        loaiVatTu.setId(id);
        loaiVatTu.setTenloai(tenLoai);
        loaiVatTu.setMoTa(moTa);

        if (dao.update(loaiVatTu)) {
            return "Thành công";
        } else {
            return "Lỗi khi cập nhật!";
        }
    }

    public String xoaLoaiVatTu(int id) {
        if (dao.delete(id)) {
            return "Thành công";
        } else {
            return "Không thể xóa (Có thể LVT đang được sử dụng)!";
        }
    }
}

