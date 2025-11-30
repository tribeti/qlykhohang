package Servers.Models;

public class LoaiVatTu {
    private int id;
    private String tenLoai;
    private String moTa;

    public LoaiVatTu() {
    }

    public LoaiVatTu(int id, String tenloai, String moTa) {
        this.id = id;
        this.moTa = moTa;
        this.tenloai = tenloai;
    }

    public LoaiVatTu(String moTa, String tenloai) {
        this.moTa = moTa;
        this.tenloai = tenloai;
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

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    @Override
    public String toString() {
        return "LoaiVatTu{" +
                "id=" + id +
                ", tenloai='" + tenloai + '\'' +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}
