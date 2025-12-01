package Servers.DAOs;

import Common.Models.Kho;
import Servers.MySQLConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KhoDAO {

    public List<Kho> getAll() {
        List<Kho> list = new ArrayList<>();
        String sql = "SELECT * FROM kho";

        try (Connection conn = MySQLConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Kho kho = new Kho(
                        rs.getInt("id"),
                        rs.getString("ten_kho"),
                        rs.getString("dia_chi")
                );
                list.add(kho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(Kho kho) {
        String sql = "INSERT INTO kho (ten_kho,dia_chi) VALUE (?,?)";
        try (Connection conn = MySQLConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, kho.getTenKho());
            stmt.setString(2, kho.getDiaChi());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Kho kho) {
        String sql = "UPDATE kho SET ten_kho = ?, dia_chi = ? WHERE id = ?";
        try (Connection conn = MySQLConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, kho.getTenKho());
            stmt.setString(2, kho.getDiaChi());
            stmt.setInt(3, kho.getId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM kho WHERE id = ?";
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
