package Servers.Models;

import java.sql.Timestamp;

public class LoaiVatTu {
    private int id;
    private String tenLoai;
    private String moTa;
    private Timestamp ngayTao;

    public LoaiVatTu() {
    }

    // dung de query trong db
    public LoaiVatTu(int id, String tenLoai, String moTa, Timestamp ngayTao) {
        this.id = id;
        this.tenLoai = tenLoai;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
    }

    // dung de insert vao db
    public LoaiVatTu(String tenLoai, String moTa) {
        this.tenLoai = tenLoai;
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public String toString() {
        return "LoaiVatTu{" +
                "id=" + id +
                ", tenLoai='" + tenLoai + '\'' +
                ", moTa='" + moTa + '\'' +
                ", ngayTao=" + ngayTao +
                '}';
    }
}
