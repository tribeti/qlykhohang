package Servers.Models;

public class Kho {
    private int id;
    private String tenKho;
    private String diaChi;

    public Kho() {
    }

    public Kho(int id, String tenKho, String diaChi) {
        this.id = id;
        this.tenKho = tenKho;
        this.diaChi = diaChi;
    }

    public Kho(String diaChi, String tenKho) {
        this.diaChi = diaChi;
        this.tenKho = tenKho;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTenKho() {
        return tenKho;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

    @Override
    public String toString() {
        return "Kho{" +
                "id=" + id +
                ", tenKho='" + tenKho + '\'' +
                ", diaChi='" + diaChi + '\'' +
                '}';
    }
}
