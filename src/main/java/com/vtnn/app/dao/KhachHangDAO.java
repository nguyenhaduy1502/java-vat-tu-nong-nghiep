/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vtnn.app.dao;

import com.vtnn.app.dbservice.SQLServerConnection;
import com.vtnn.app.models.KhachHangDTO;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author vinhp
 */
public class KhachHangDAO {
    private SQLServerConnection connection;
    
    public KhachHangDAO() throws Exception {
        this.connection = new SQLServerConnection();
    }
    
    // Lấy mã khách hàng lớn nhất
    public int layMaKHLonNhat() throws Exception {
        String query = "SELECT MAX(MaKH) AS MaxMaKH FROM KhachHang";
        try (Connection conn = connection.getConnect();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt("MaxMaKH");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Tạo mã khách hàng tự động
    public int taoMaKH() throws Exception {
        int namHienTai = LocalDate.now().getYear() % 100; // lấy 2 số cuối của năm, ví dụ: 2025 -> 25
        int maKHMax = layMaKHLonNhat();

        int soThuTu = 1;

        if (maKHMax > 0 && maKHMax / 10000 == namHienTai) {
            soThuTu = maKHMax % 10000 + 1;
        }

        int maMoi = namHienTai * 10000 + soThuTu; // ghép lại thành yyxxxx
        return maMoi;
    }

    
    // Thêm khách hàng
    public boolean themKhachHang(KhachHangDTO khachHang) throws Exception {
        String query = "INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {

            int maKH = taoMaKH(); // sinh mã khách hàng tự động theo cấu trúc yyxxxx
            khachHang.setMaKH(maKH); // gán mã khách hàng cho DTO

            statement.setInt(1, maKH);
            statement.setString(2, khachHang.getTenKH());
            statement.setInt(3, khachHang.getGioiTinh());
            statement.setString(4, khachHang.getSoDienThoai());
            statement.setString(5, khachHang.getDiaChi());
            statement.setString(6, khachHang.getEmail());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Xóa khách hàng
    public boolean xoaKhachHang(int maKH) throws Exception {
        String query = "DELETE FROM KhachHang WHERE MaKH = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, maKH);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Sửa thông tin khách hàng
    public boolean suaKhachHang(KhachHangDTO khachHang) throws Exception {
        String query = "UPDATE KhachHang SET TenKH = ?, GioiTinh = ?, SoDienThoai = ?, DiaChi = ?, Email = ? WHERE MaKH = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, khachHang.getTenKH());
            statement.setInt(2, khachHang.getGioiTinh());
            statement.setString(3, khachHang.getSoDienThoai());
            statement.setString(4, khachHang.getDiaChi());
            statement.setString(5, khachHang.getEmail());
            statement.setInt(6, khachHang.getMaKH());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách khách hàng
    public List<Object[]> dsKhachHang() throws Exception {
        List<Object[]> ds = new ArrayList<>();
        String query = """
                       SELECT KhachHang.MaKH, KhachHang.TenKH, GioiTinh.LoaiGT, KhachHang.SoDienThoai, KhachHang.DiaChi, KhachHang.Email
                       FROM KhachHang
                       JOIN GioiTinh ON KhachHang.GioiTinh = GioiTinh.MaGT
                       """;
        try (Connection conn = connection.getConnect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                ds.add(new Object[]{
                    rs.getInt("MaKH"),
                    rs.getString("TenKH"),
                    rs.getString("LoaiGT"),
                    rs.getString("SoDienThoai"),
                    rs.getString("DiaChi"),
                    rs.getString("Email")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi lấy danh sách khách hàng", e);
        }
        return ds;
    }

    // Tìm khách hàng theo mã
    public Object[] timKhachHang(int maKH) throws Exception {
        String query = """
                       SELECT KhachHang.MaKH, KhachHang.TenKH, GioiTinh.LoaiGT, KhachHang.SoDienThoai, KhachHang.DiaChi, KhachHang.Email
                       FROM KhachHang
                       JOIN GioiTinh ON KhachHang.GioiTinh = GioiTinh.MaGT
                       WHERE KhachHang.MaKH = ?
                       """;
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, maKH);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Object[] {
                        rs.getInt("MaKH"),
                        rs.getString("TenKH"),
                        rs.getString("LoaiGT"),
                        rs.getString("SoDienThoai"),
                        rs.getString("DiaChi"),
                        rs.getString("Email")
                    };
                } else {
                    throw new Exception("Không tìm thấy khách hàng với mã: " + maKH);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi tìm khách hàng theo mã", e);
        }
    }
}
