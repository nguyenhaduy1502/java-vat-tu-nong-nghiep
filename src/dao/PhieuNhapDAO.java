/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbservice.SQLServerConnection;
import java.math.BigDecimal;
import models.PhieuNhapDTO;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinhp
 */
public class PhieuNhapDAO {
    private SQLServerConnection connection;

    public PhieuNhapDAO() throws Exception {
        this.connection = new SQLServerConnection();
    }

    // Lấy mã phiếu nhập lớn nhất hiện có
    public int layMaPNLonNhat() throws Exception {
        String query = "SELECT MAX(MaPN) AS MaxMaPN FROM PhieuNhap";
        try (Connection conn = connection.getConnect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("MaxMaPN");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi lấy mã phiếu nhập lớn nhất", e);
        }
        return 0;
    }

    // Hàm tạo mã phiếu nhập tự động
    public int taoMaPN() throws Exception {
        int nam = LocalDate.now().getYear() % 100; // lấy 2 số cuối năm
        int maPNMax = layMaPNLonNhat();
        int soThuTu = 1;

        if (maPNMax > 0 && maPNMax / 100000 == nam) {
            soThuTu = maPNMax % 100000 + 1;
        }

        return nam * 100000 + soThuTu;
    }

    // Thêm phiếu nhập
    public boolean themPhieuNhap(PhieuNhapDTO pn) throws Exception {
        String query = "INSERT INTO PhieuNhap (MaPN, NgayNhap, MaNCC, NguoiTao, TongTien) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            int maPN = taoMaPN();
            pn.setMaPN(maPN);

            stmt.setInt(1, maPN);
            stmt.setDate(2, pn.getNgayNhap());
            stmt.setInt(3, pn.getMaNCC());
            stmt.setString(4, pn.getNguoiTao());
            stmt.setBigDecimal(5, pn.getTongTien());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa phiếu nhập
    public boolean xoaPhieuNhap(int maPN) throws Exception {
        String query = "DELETE FROM PhieuNhap WHERE MaPN = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, maPN);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Sửa phiếu nhập
    public boolean suaPhieuNhap(PhieuNhapDTO pn) throws Exception {
        String query = "UPDATE PhieuNhap SET NgayNhap = ?, MaNCC = ?, NguoiTao = ?, TongTien = ? WHERE MaPN = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, pn.getNgayNhap());
            stmt.setInt(2, pn.getMaNCC());
            stmt.setString(3, pn.getNguoiTao());
            stmt.setBigDecimal(4, pn.getTongTien());
            stmt.setInt(5, pn.getMaPN());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Danh sách phiếu nhập
    public List<Object[]> dsPhieuNhap() throws Exception {
        List<Object[]> ds = new ArrayList<>();
        String query = """
                SELECT pn.MaPN, pn.NgayNhap, ncc.TenNCC, pn.NguoiTao, pn.TongTien
                FROM PhieuNhap pn
                JOIN NhaCungCap ncc ON pn.MaNCC = ncc.MaNCC
                """;
        try (Connection conn = connection.getConnect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                ds.add(new Object[]{
                        rs.getInt("MaPN"),
                        rs.getDate("NgayNhap"),
                        rs.getString("TenNCC"),
                        rs.getString("NguoiTao"),
                        rs.getBigDecimal("TongTien")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi lấy danh sách phiếu nhập", e);
        }
        return ds;
    }

    // Tìm phiếu nhập theo mã
    public Object[] timPhieuNhap(int maPN) throws Exception {
        String query = """
                SELECT pn.MaPN, pn.NgayNhap, ncc.TenNCC, pn.NguoiTao, pn.TongTien
                FROM PhieuNhap pn
                JOIN NhaCungCap ncc ON pn.MaNCC = ncc.MaNCC
                WHERE pn.MaPN = ?
                """;
        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, maPN);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Object[]{
                            rs.getInt("MaPN"),
                            rs.getDate("NgayNhap"),
                            rs.getString("TenNCC"),
                            rs.getString("NguoiTao"),
                            rs.getBigDecimal("TongTien")
                    };
                } else {
                    throw new Exception("Không tìm thấy phiếu nhập với mã: " + maPN);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi tìm phiếu nhập", e);
        }
    }
    
    public boolean capNhatTongTien(int maPN, BigDecimal tongTien) throws Exception {
        String query = "UPDATE PhieuNhap SET TongTien = ? WHERE MaPN = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setBigDecimal(1, tongTien);
            statement.setInt(2, maPN);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
