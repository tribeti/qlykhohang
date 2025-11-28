package Servers.Models;

public class NhaCungCap {
    private int id;
    private String tenNhaCungCap;
    private String email;
    private String diaChi;

    public NhaCungCap() {
    }

    public NhaCungCap(int id, String tenNhaCungCap, String email, String diaChi) {
        this.id = id;
        this.tenNhaCungCap = tenNhaCungCap;
        this.email = email;
        this.diaChi = diaChi;
    }

    public NhaCungCap(String tenNhaCungCap, String email, String diaChi) {
        this.tenNhaCungCap = tenNhaCungCap;
        this.email = email;
        this.diaChi = diaChi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }


    @Override
    public String toString() {
        return "NhaCungCap{" +
                "tenNhaCungCap='" + tenNhaCungCap + '\'' +
                ", email='" + email + '\'' +
                ", diaChi='" + diaChi +
                '}';
    }
}
