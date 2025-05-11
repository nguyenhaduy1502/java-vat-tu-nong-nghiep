/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vtnn.app.dao;

import com.vtnn.app.dbservice.SQLServerConnection;
import com.vtnn.app.models.NguoiDungDTO;
import com.vtnn.app.models.UserRole;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author vinhp
 */
public class NguoiDungDAO {
    private SQLServerConnection connection;

    public NguoiDungDAO() throws Exception {
        this.connection = new SQLServerConnection();
    }

    public boolean themNguoiDung(NguoiDungDTO nd) throws Exception {
        String query = "INSERT INTO NguoiDung (TenDangNhap, MatKhau, MaNV) VALUES (?, ?, ?)";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, nd.getTenDangNhap());
            statement.setString(2, nd.getMatKhau());
            statement.setInt(3, nd.getMaNV());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean xoaNguoiDung(String tenDangNhap) throws Exception {
        String query = "DELETE FROM NguoiDung WHERE TenDangNhap = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, tenDangNhap);
            return statement.executeUpdate() > 0;
        }
    }
    
    public boolean xoaNguoiDungTheoMaNV(int maNV) throws Exception {
        String query = "DELETE FROM NguoiDung WHERE MaNV = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, maNV);
            return statement.executeUpdate() > 0;
        }
    }

    public boolean suaMatKhau(String tenDangNhap, String matKhauMoi) throws Exception {
        String query = "UPDATE NguoiDung SET MatKhau = ? WHERE TenDangNhap = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, matKhauMoi);
            statement.setString(2, tenDangNhap);
            return statement.executeUpdate() > 0;
        }
    }

    public NguoiDungDTO timNguoiDung(String tenDangNhap) throws Exception {
        String query = "SELECT * FROM NguoiDung WHERE TenDangNhap = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, tenDangNhap);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new NguoiDungDTO(
                        rs.getString("TenDangNhap"),
                        rs.getString("MatKhau"),
                        rs.getInt("MaNV")
                    );
                }
                return null;
            }
        }
    }
    
    public NguoiDungDTO timNguoiDungTheoMaNV(int maNV) throws Exception {
    String query = "SELECT * FROM NguoiDung WHERE MaNV = ?";
    try (Connection conn = connection.getConnect();
         PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setInt(1, maNV);
        try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                return new NguoiDungDTO(
                    rs.getString("TenDangNhap"),
                    rs.getString("MatKhau"),
                    rs.getInt("MaNV")
                );
            }
            return null;
        }
    }
}

    public NguoiDungDTO findByUsername(String username) throws Exception {
        String sql = "SELECT nd.TenDangNhap, nd.MatKhau, nd.MaNV, nv.VaiTro " +
                     "FROM NguoiDung nd " +
                     "JOIN NhanVien nv ON nd.MaNV = nv.MaNV " +
                     "WHERE nd.TenDangNhap = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new NguoiDungDTO(
                    rs.getString("TenDangNhap"),
                    rs.getString("MatKhau"),
                    rs.getInt("MaNV"),
                    UserRole.fromMaVT(rs.getInt("VaiTro"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NguoiDungDTO> findAll() throws Exception {
        List<NguoiDungDTO> users = new ArrayList<>();
        String sql = "SELECT nd.TenDangNhap, nd.MatKhau, nd.MaNV, nv.VaiTro " +
                     "FROM NguoiDung nd " +
                     "JOIN NhanVien nv ON nd.MaNV = nv.MaNV";
        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(new NguoiDungDTO(
                    rs.getString("TenDangNhap"),
                    rs.getString("MatKhau"),
                    rs.getInt("MaNV"),
                    UserRole.fromMaVT(rs.getInt("VaiTro"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean insert(NguoiDungDTO user) throws Exception {
        String sql = "INSERT INTO NguoiDung (TenDangNhap, MatKhau, MaNV) VALUES (?, ?, ?)";
        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getTenDangNhap());
            stmt.setString(2, user.getMatKhau());
            stmt.setInt(3, user.getMaNV());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(NguoiDungDTO user) throws Exception {
        String sql = "UPDATE NguoiDung SET MatKhau = ? WHERE TenDangNhap = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getMatKhau());
            stmt.setString(2, user.getTenDangNhap());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String tenDangNhap) throws Exception {
        String sql = "DELETE FROM NguoiDung WHERE TenDangNhap = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tenDangNhap);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
