package Servers.DAOs;

import Servers.Models.LoaiVatTu;
import Servers.MySQLConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoaiVatTuDAO {

    public List<LoaiVatTu> getAll() {
        List<LoaiVatTu> list = new ArrayList<>();
        String sql = "SELECT * FROM loai_vat_tu";

        try (Connection conn = MySQLConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                LoaiVatTu loaiVatTu = new LoaiVatTu(
                        rs.getInt("id"),
                        rs.getString("ten_loai"),
                        rs.getString("mo_ta")
                );
                list.add(loaiVatTu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(LoaiVatTu loaiVatTu) {
        String sql = "INSERT INTO loai_vat_tu (ten_loai,mo_ta) VALUE (?,?)";
        try (Connection conn = MySQLConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, loaiVatTu.getTenloai());
            stmt.setString(2, loaiVatTu.getMoTa());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(LoaiVatTu loaiVatTu) {
        String sql = "UPDATE loai_vat_tu SET ten_loai = ?, mo_ta = ? WHERE id = ?";
        try (Connection conn = MySQLConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, loaiVatTu.getTenloai());
            stmt.setString(2, loaiVatTu.getMoTa());
            stmt.setInt(3, loaiVatTu.getId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
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
