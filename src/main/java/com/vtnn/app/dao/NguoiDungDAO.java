/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vtnn.app.dao;

import com.vtnn.app.dbservice.SQLServerConnection;
import com.vtnn.app.models.NguoiDungDTO;
import java.sql.*;
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

}
