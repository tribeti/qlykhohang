package Servers.DAOs;

import Common.Models.NhaCungCap;
import Servers.MySQLConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO {

    public List<NhaCungCap> getAll() {
        List<NhaCungCap> list = new ArrayList<>();
        String sql = "SELECT * FROM nha_cung_cap";

        try (Connection conn = MySQLConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap(
                        rs.getInt("id"),
                        rs.getString("ten_nha_cung_cap"),
                        rs.getString("email"),
                        rs.getString("dia_chi")
                );
                list.add(ncc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(NhaCungCap ncc) {
        String sql = "INSERT INTO nha_cung_cap (ten_nha_cung_cap, email, dia_chi) VALUES (?, ?, ?)";

        try (Connection conn = MySQLConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ncc.getTenNhaCungCap());
            stmt.setString(2, ncc.getEmail());
            stmt.setString(3, ncc.getDiaChi());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(NhaCungCap ncc) {
        String sql = "UPDATE nha_cung_cap SET ten_nha_cung_cap = ?, email = ?, dia_chi = ? WHERE id = ?";

        try (Connection conn = MySQLConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ncc.getTenNhaCungCap());
            stmt.setString(2, ncc.getEmail());
            stmt.setString(3, ncc.getDiaChi());
            stmt.setInt(4, ncc.getId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM nha_cung_cap WHERE id = ?";

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
