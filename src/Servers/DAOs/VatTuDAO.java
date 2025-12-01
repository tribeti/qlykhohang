package Servers.DAOs;


import Common.Models.VatTu;
import Servers.MySQLConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VatTuDAO {
    public List<VatTu> getAll() {
        List<VatTu> list = new ArrayList<>();

        String sql = "SELECT * FROM vat_tu";

        try (Connection conn = MySQLConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                VatTu vt = new VatTu(
                        rs.getInt("id"),
                        rs.getString("ten_vat_tu"),
                        rs.getInt("nha_cung_cap_id"),
                        rs.getString("don_vi_tinh"),
                        rs.getDouble("gia_tien"),
                        rs.getInt("so_luong"),
                        rs.getString("mo_ta"),
                        rs.getObject("ngay_tao", LocalDateTime.class),
                        rs.getInt("kho_id"),
                        rs.getBoolean("tinh_trang")
                );
                // Set tên NCC để hiển thị ra bảng
//                vt.setTenNhaCungCap(rs.getString("ten_nha_cung_cap"));

                list.add(vt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Thêm mới
    public boolean add(VatTu vt) {
        String sql = "INSERT INTO vat_tu (ten_vat_tu, nha_cung_cap_id, don_vi_tinh, gia_tien, so_luong, mo_ta) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vt.getTenVatTu());

            // Xử lý null cho ID nhà cung cấp
            if (vt.getNhaCungCapId() > 0) {
                stmt.setInt(2, vt.getNhaCungCapId());
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }

            stmt.setString(3, vt.getDonViTinh());
            stmt.setDouble(4, vt.getGiaTien());
            stmt.setInt(5, vt.getSoLuong());
            stmt.setString(6, vt.getMoTa());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Cập nhật
    public boolean update(VatTu vt) {
        String sql = "UPDATE vat_tu SET ten_vat_tu=?, nha_cung_cap_id=?, don_vi_tinh=?, " +
                "gia_tien=?, so_luong=?, mo_ta=? WHERE id=?";

        try (Connection conn = MySQLConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vt.getTenVatTu());

            if (vt.getNhaCungCapId() > 0) {
                stmt.setInt(2, vt.getNhaCungCapId());
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }

            stmt.setString(3, vt.getDonViTinh());
            stmt.setDouble(4, vt.getGiaTien());
            stmt.setInt(5, vt.getSoLuong());
            stmt.setString(6, vt.getMoTa());
            stmt.setInt(7, vt.getId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. Xóa
    public boolean delete(int id) {
        String sql = "DELETE FROM vat_tu WHERE id = ?";
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
