package Common.Models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class VatTu implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private String tenVatTu;
    private int nhaCungCapId;
    private String donViTinh;
    private double giaTien;
    private int soLuong;
    private String moTa;
    private LocalDateTime ngayTao;
    private int khoId;
    private boolean tinhTrang;

    public VatTu() {
    }

    public VatTu(int id, String tenVatTu, int nhaCungCapId, String donViTinh, double giaTien, int soLuong, String moTa, LocalDateTime ngayTao, int khoId, boolean tinhTrang) {
        this.id = id;
        this.tenVatTu = tenVatTu;
        this.nhaCungCapId = nhaCungCapId;
        this.donViTinh = donViTinh;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.khoId = khoId;
        this.tinhTrang = tinhTrang;
    }

    public VatTu(String tenVatTu, int nhaCungCapId, String donViTinh, double giaTien, int soLuong, String moTa, LocalDateTime ngayTao, int khoId, boolean tinhTrang) {
        this.tenVatTu = tenVatTu;
        this.nhaCungCapId = nhaCungCapId;
        this.donViTinh = donViTinh;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.khoId = khoId;
        this.tinhTrang = tinhTrang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKhoId() {
        return khoId;
    }

    public void setKhoId(int khoId) {
        this.khoId = khoId;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getTenVatTu() {
        return tenVatTu;
    }

    public void setTenVatTu(String tenVatTu) {
        this.tenVatTu = tenVatTu;
    }

    public int getNhaCungCapId() {
        return nhaCungCapId;
    }

    public void setNhaCungCapId(int nhaCungCapId) {
        this.nhaCungCapId = nhaCungCapId;
    }

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return "VatTu{" +
                "id=" + id +
                ", tenVatTu='" + tenVatTu + '\'' +
                ", nhaCungCapId=" + nhaCungCapId +
                ", donViTinh='" + donViTinh + '\'' +
                ", giaTien=" + giaTien +
                ", soLuong=" + soLuong +
                ", moTa='" + moTa + '\'' +
                ", ngayTao=" + ngayTao +
                ", khoId=" + khoId +
                '}';
    }
}
