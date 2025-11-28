package Servers.Models;

import java.sql.Timestamp;

public class VatTu {
    private int id;
    private String tenVatTu;
    private int nhaCungCapId;
    private String donViTinh;
    private double giaTien;
    private int soLuong;
    private String moTa;
    private Timestamp ngayTao;
    private String tenNhaCungCap;

    public VatTu() {
    }

    public VatTu(int id, String tenVatTu, int nhaCungCapId, String donViTinh,
                 double giaTien, int soLuong, String moTa, Timestamp ngayTao) {
        this.id = id;
        this.tenVatTu = tenVatTu;
        this.nhaCungCapId = nhaCungCapId;
        this.donViTinh = donViTinh;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
    }

    public VatTu(String tenVatTu, int nhaCungCapId, String donViTinh,
                 double giaTien, int soLuong, String moTa) {
        this.tenVatTu = tenVatTu;
        this.nhaCungCapId = nhaCungCapId;
        this.donViTinh = donViTinh;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    @Override
    public String toString() {
        return "VatTu{" +
                "tenVatTu='" + tenVatTu + '\'' +
                ", nhaCungCapId=" + nhaCungCapId +
                ", donViTinh='" + donViTinh + '\'' +
                ", giaTien=" + giaTien +
                ", soLuong=" + soLuong +
                ", moTa='" + moTa + '\'' +
                ", ngayTao=" + ngayTao +
                ", tenNhaCungCap='" + tenNhaCungCap + '\'' +
                '}';
    }
}