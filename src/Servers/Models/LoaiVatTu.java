package Servers.Models;

public class LoaiVatTu {
    private int id;
    private String tenLoai;
    private String moTa;

    public LoaiVatTu() {
    }

    public LoaiVatTu(int id, String tenLoai, String moTa) {
        this.id = id;
        this.tenLoai = tenLoai;
        this.moTa = moTa;
    }

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

    @Override
    public String toString() {
        return "LoaiVatTu{" +
                "id=" + id +
                ", tenLoai='" + tenLoai + '\'' +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}
