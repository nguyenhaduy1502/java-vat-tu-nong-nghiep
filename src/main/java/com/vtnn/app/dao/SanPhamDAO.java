/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vtnn.app.dao;

import com.vtnn.app.dbservice.SQLServerConnection;
import com.vtnn.app.models.SanPhamDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinhp
 */
public class SanPhamDAO {
    private SQLServerConnection connection;

    public SanPhamDAO() throws Exception {
        this.connection = new SQLServerConnection();
    }

    // Lấy mã sản phẩm lớn nhất
    public int layMaSPLonNhat() throws Exception {
        String query = "SELECT MAX(MaSP) AS MaxMaSP FROM SanPham";
        try (Connection conn = connection.getConnect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("MaxMaSP");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi lấy mã sản phẩm lớn nhất", e);
        }
        return 0;
    }

    // Tạo mã sản phẩm mới
    public int taoMaSP() throws Exception {
        return layMaSPLonNhat() + 1;
    }

    // Thêm sản phẩm
    public boolean themSanPham(SanPhamDTO sp) throws Exception {
        String query = """
            INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            int maSP = taoMaSP(); // Tạo mã mới
            sp.setMaSP(maSP);     // Gán vào DTO

            stmt.setInt(1, maSP);
            stmt.setString(2, sp.getTenSP());
            stmt.setInt(3, sp.getDonViTinh());
            stmt.setBigDecimal(4, sp.getGiaNhap());
            stmt.setBigDecimal(5, sp.getGiaBan());
            stmt.setInt(6, sp.getSoLuongTon());
            stmt.setInt(7, sp.getNhomHang());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi thêm sản phẩm", e);
        }
    }

    // Xóa sản phẩm
    public boolean xoaSanPham(int maSP) throws Exception {
        String query = "DELETE FROM SanPham WHERE MaSP = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, maSP);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi xóa sản phẩm", e);
        }
    }

    // Cập nhật sản phẩm
    public boolean suaSanPham(SanPhamDTO sp) throws Exception {
        String query = """
            UPDATE SanPham SET TenSP = ?, DonViTinh = ?, GiaNhap = ?, GiaBan = ?, SoLuongTon = ?, NhomHang = ?
            WHERE MaSP = ?
        """;
        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, sp.getTenSP());
            stmt.setInt(2, sp.getDonViTinh());
            stmt.setBigDecimal(3, sp.getGiaNhap());
            stmt.setBigDecimal(4, sp.getGiaBan());
            stmt.setInt(5, sp.getSoLuongTon());
            stmt.setInt(6, sp.getNhomHang());
            stmt.setInt(7, sp.getMaSP());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi cập nhật sản phẩm", e);
        }
    }

    // Tìm sản phẩm theo mã
    public Object[] timSanPham(int maSP) throws Exception {
        String query = """
            SELECT SanPham.MaSP, SanPham.TenSP, DonViTinh.LoaiDVT, SanPham.GiaNhap, SanPham.GiaBan, SanPham.SoLuongTon, NhomHang.TenNH
            FROM SanPham
            JOIN DonViTinh ON SanPham.DonViTinh = DonViTinh.MaDVT
            JOIN NhomHang ON SanPham.NhomHang = NhomHang.MaNH
            WHERE SanPham.MaSP = ?
        """;

        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, maSP);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Object[]{
                        rs.getInt("MaSP"),
                        rs.getString("TenSP"),
                        rs.getString("LoaiDVT"),
                        rs.getBigDecimal("GiaNhap"),
                        rs.getBigDecimal("GiaBan"),
                        rs.getInt("SoLuongTon"),
                        rs.getString("TenNH")
                    };
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi tìm sản phẩm", e);
        }
        return null;
    }

    // Lấy danh sách tất cả sản phẩm
    public List<Object[]> dsSanPham() throws Exception {
        List<Object[]> danhSach = new ArrayList<>();
        String query = """
            SELECT SanPham.MaSP, SanPham.TenSP, DonViTinh.LoaiDVT, SanPham.GiaNhap, SanPham.GiaBan, SanPham.SoLuongTon, NhomHang.TenNH
            FROM SanPham
            JOIN DonViTinh ON SanPham.DonViTinh = DonViTinh.MaDVT
            JOIN NhomHang ON SanPham.NhomHang = NhomHang.MaNH
        """;

        try (Connection conn = connection.getConnect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                danhSach.add(new Object[]{
                    rs.getInt("MaSP"),
                    rs.getString("TenSP"),
                    rs.getString("LoaiDVT"),
                    rs.getBigDecimal("GiaNhap"),
                    rs.getBigDecimal("GiaBan"),
                    rs.getInt("SoLuongTon"),
                    rs.getString("TenNH")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi lấy danh sách sản phẩm", e);
        }
        return danhSach;
    }
    
    // Lấy danh sách sản phẩm theo mã nhóm hàng
    public List<Object[]> dsSanPhamTheoNhomHang(int maNhomHang) throws Exception {
        List<Object[]> danhSach = new ArrayList<>();
        String query = """
            SELECT SanPham.MaSP, SanPham.TenSP, DonViTinh.LoaiDVT, SanPham.GiaNhap, SanPham.GiaBan, SanPham.SoLuongTon, NhomHang.TenNH
            FROM SanPham
            JOIN DonViTinh ON SanPham.DonViTinh = DonViTinh.MaDVT
            JOIN NhomHang ON SanPham.NhomHang = NhomHang.MaNH
            WHERE SanPham.NhomHang = ?
        """;

        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, maNhomHang);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    danhSach.add(new Object[]{
                        rs.getInt("MaSP"),
                        rs.getString("TenSP"),
                        rs.getString("LoaiDVT"),
                        rs.getBigDecimal("GiaNhap"),
                        rs.getBigDecimal("GiaBan"),
                        rs.getInt("SoLuongTon"),
                        rs.getString("TenNH")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi lấy sản phẩm theo nhóm hàng", e);
        }
        return danhSach;
    }
}

