package Servers.DAOs;

import Servers.Models.LoaiVatTu;
import Servers.MySQLConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// raw sql cho crud
public class LoaiVatTuDAO {
    // C : them, tao moi
    public boolean Create(LoaiVatTu lvt) {
        String sql = "INSERT INTO loai_vat_tu (ten_loai, mo_ta, ngay_tao) VALUES (?, ?, NOW())";

        try (Connection conn = MySQLConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lvt.getTenLoai());
            stmt.setString(2, lvt.getMoTa());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // R : doc, xem du lieu
    public List<LoaiVatTu> ReadAll() {
        List<LoaiVatTu> list = new ArrayList<>();
        String sql = "SELECT * FROM loai_vat_tu ORDER BY id ASC";

        try (Connection conn = MySQLConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                LoaiVatTu lvt = new LoaiVatTu(
                        rs.getInt("id"),
                        rs.getString("ten_loai"),
                        rs.getString("mo_ta"),
                        rs.getTimestamp("ngay_tao")
                );
                list.add(lvt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // U : cap nhat thong tin
    public boolean Update(LoaiVatTu lvt) {
        String sql = "UPDATE loai_vat_tu SET ten_loai = ?, mo_ta = ? WHERE id = ?";

        try (Connection conn = MySQLConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lvt.getTenLoai());
            stmt.setString(2, lvt.getMoTa());
            stmt.setInt(3, lvt.getId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // D : xoa vat the
    public boolean Delete(int id) {
        String sql = "DELETE FROM loai_vat_tu WHERE id = ?";

        try (Connection conn = MySQLConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
