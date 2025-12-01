package Servers.Controllers;

import Common.Models.VatTu;
import Servers.DAOs.VatTuDAO;

import java.time.LocalDateTime;
import java.util.List;

public class VatTuController {

    private final VatTuDAO dao;

    public VatTuController() {
        this.dao = new VatTuDAO();
    }

    public List<VatTu> getDanhSachVatTu() {
        return dao.getAll();
    }

    public String themVatTu(String ten, int nccId, String dvt, String giaStr, String slStr, String moTa, LocalDateTime ngayTao, int khoId) {
        if (ten == null || ten.trim().isEmpty()) return "Tên vật tư không được để trống!";
        if (dvt == null || dvt.isEmpty()) return "Đơn vị tính không được để trống!";

        double gia;
        int soLuong;
        try {
            gia = Double.parseDouble(giaStr);
            soLuong = Integer.parseInt(slStr);
            if (gia < 0 || soLuong < 0) return "Giá và số lượng phải >= 0";
        } catch (NumberFormatException e) {
            return "Giá tiền hoặc Số lượng phải là số!";
        }

        VatTu vt = new VatTu(ten, nccId, dvt, gia, soLuong, moTa, ngayTao, khoId, true);

        if (dao.add(vt)) {
            return "Thành công";
        } else {
            return "Lỗi thêm dữ liệu!";
        }
    }

    public String suaVatTu(int id, String ten, int nccId, String dvt, String giaStr, String slStr, String moTa) {
        if (ten.isEmpty()) return "Tên vật tư rỗng!";

        double gia;
        int soLuong;
        try {
            gia = Double.parseDouble(giaStr);
            soLuong = Integer.parseInt(slStr);
            if (gia < 0 || soLuong < 0) return "Giá và số lượng phải >= 0";
        } catch (NumberFormatException e) {
            return "Giá hoặc Số lượng sai định dạng!";
        }

        VatTu vt = new VatTu();
        vt.setId(id);
        vt.setTenVatTu(ten);
        vt.setNhaCungCapId(nccId);
        vt.setDonViTinh(dvt);
        vt.setGiaTien(gia);
        vt.setSoLuong(soLuong);
        vt.setMoTa(moTa);

        if (dao.update(vt)) {
            return "Thành công";
        } else {
            return "Lỗi cập nhật!";
        }
    }

    public String xoaVatTu(int id) {
        if (dao.delete(id)) {
            return "Thành công";
        } else {
            return "Lỗi xóa!";
        }
    }
}