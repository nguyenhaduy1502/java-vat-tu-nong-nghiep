/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbservice.SQLServerConnection;
import java.math.BigDecimal;
import models.ChiTietNhapDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinhp
 */
public class ChiTietNhapDAO {
    private SQLServerConnection connection;

    // Constructor khởi tạo kết nối CSDL
    public ChiTietNhapDAO() throws Exception {
        this.connection = new SQLServerConnection();
    }

    // Phương thức thêm chi tiết nhập
    public boolean themChiTietNhap(ChiTietNhapDTO chiTietNhap) throws Exception {
        String query = "INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, chiTietNhap.getMaPN());
            statement.setInt(2, chiTietNhap.getMaSP());
            statement.setInt(3, chiTietNhap.getSoLuong());
            statement.setBigDecimal(4, chiTietNhap.getDonGia());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức xóa chi tiết nhập theo mã phiếu nhập và mã sản phẩm
    public boolean xoaChiTietNhap(int maPN, int maSP) throws Exception {
        String query = "DELETE FROM ChiTietNhap WHERE MaPN = ? AND MaSP = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, maPN);
            statement.setInt(2, maSP);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức cập nhật chi tiết nhập
    public boolean suaChiTietNhap(ChiTietNhapDTO chiTietNhap) throws Exception {
        String query = "UPDATE ChiTietNhap SET SoLuong = ?, DonGia = ? WHERE MaPN = ? AND MaSP = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, chiTietNhap.getSoLuong());
            statement.setBigDecimal(2, chiTietNhap.getDonGia());
            statement.setInt(3, chiTietNhap.getMaPN());
            statement.setInt(4, chiTietNhap.getMaSP());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức lấy danh sách chi tiết nhập theo mã phiếu nhập
    public List<ChiTietNhapDTO> dsChiTietNhapTheoMaPN(int maPN) throws Exception {
        List<ChiTietNhapDTO> dsChiTietNhap = new ArrayList<>();
        String query = "SELECT * FROM ChiTietNhap WHERE MaPN = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, maPN);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int maSP = resultSet.getInt("MaSP");
                    int soLuong = resultSet.getInt("SoLuong");
                    BigDecimal donGia = resultSet.getBigDecimal("DonGia");
                    dsChiTietNhap.add(new ChiTietNhapDTO(maPN, maSP, soLuong, donGia));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching chi tiết nhập for MaPN: " + maPN, e);
        }
        return dsChiTietNhap;
    }

    // Phương thức tìm chi tiết nhập theo mã phiếu nhập và mã sản phẩm
    public ChiTietNhapDTO timChiTietNhap(int maPN, int maSP) throws Exception {
        String query = "SELECT * FROM ChiTietNhap WHERE MaPN = ? AND MaSP = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, maPN);
            statement.setInt(2, maSP);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int soLuong = resultSet.getInt("SoLuong");
                    BigDecimal donGia = resultSet.getBigDecimal("DonGia");
                    return new ChiTietNhapDTO(maPN, maSP, soLuong, donGia);
                } else {
                    throw new Exception("Không tìm thấy chi tiết nhập với MaPN: " + maPN + " và MaSP: " + maSP);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error when searching for chi tiết nhập", e);
        }
    }
    
    // Phương thức tính tổng tiền theo mã phiếu nhập
    public BigDecimal layTongTienTheoMaPN(int maPN) throws Exception {
        String query = "SELECT SUM(SoLuong * DonGia) AS TongTien FROM ChiTietNhap WHERE MaPN = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, maPN);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    BigDecimal tongTien = resultSet.getBigDecimal("TongTien");
                    return tongTien != null ? tongTien : BigDecimal.ZERO;
                } else {
                    return BigDecimal.ZERO;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi tính tổng tiền cho MaPN: " + maPN, e);
        }
    }

}
