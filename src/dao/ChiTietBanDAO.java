/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbservice.SQLServerConnection;
import java.math.BigDecimal;
import models.ChiTietBanDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinhp
 */
public class ChiTietBanDAO {
    private SQLServerConnection connection;

    // Constructor khởi tạo kết nối CSDL
    public ChiTietBanDAO() throws Exception {
        this.connection = new SQLServerConnection();
    }

    // Thêm chi tiết bán
    public boolean themChiTietBan(ChiTietBanDTO chiTietBan) throws Exception {
        String query = "INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, chiTietBan.getMaHD());
            statement.setInt(2, chiTietBan.getMaSP());
            statement.setInt(3, chiTietBan.getSoLuong());
            statement.setBigDecimal(4, chiTietBan.getDonGia());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa chi tiết bán theo mã hóa đơn và mã sản phẩm
    public boolean xoaChiTietBan(int maHD, int maSP) throws Exception {
        String query = "DELETE FROM ChiTietBan WHERE MaHD = ? AND MaSP = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, maHD);
            statement.setInt(2, maSP);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật chi tiết bán
    public boolean suaChiTietBan(ChiTietBanDTO chiTietBan) throws Exception {
        String query = "UPDATE ChiTietBan SET SoLuong = ?, DonGia = ? WHERE MaHD = ? AND MaSP = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, chiTietBan.getSoLuong());
            statement.setBigDecimal(2, chiTietBan.getDonGia());
            statement.setInt(3, chiTietBan.getMaHD());
            statement.setInt(4, chiTietBan.getMaSP());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách chi tiết bán của một hóa đơn
    public List<ChiTietBanDTO> dsChiTietBan(int maHD) throws Exception {
        List<ChiTietBanDTO> chiTietBans = new ArrayList<>();
        String query = "SELECT MaHD, MaSP, SoLuong, DonGia FROM ChiTietBan WHERE MaHD = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, maHD);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ChiTietBanDTO chiTietBan = new ChiTietBanDTO();
                    chiTietBan.setMaHD(resultSet.getInt("MaHD"));
                    chiTietBan.setMaSP(resultSet.getInt("MaSP"));
                    chiTietBan.setSoLuong(resultSet.getInt("SoLuong"));
                    chiTietBan.setDonGia(resultSet.getBigDecimal("DonGia"));
                    chiTietBans.add(chiTietBan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching details of the bill", e);
        }
        return chiTietBans;
    }

    // Tìm chi tiết bán theo mã hóa đơn và mã sản phẩm
    public ChiTietBanDTO timChiTietBan(int maHD, int maSP) throws Exception {
        String query = "SELECT MaHD, MaSP, SoLuong, DonGia FROM ChiTietBan WHERE MaHD = ? AND MaSP = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, maHD);
            statement.setInt(2, maSP);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ChiTietBanDTO chiTietBan = new ChiTietBanDTO();
                    chiTietBan.setMaHD(resultSet.getInt("MaHD"));
                    chiTietBan.setMaSP(resultSet.getInt("MaSP"));
                    chiTietBan.setSoLuong(resultSet.getInt("SoLuong"));
                    chiTietBan.setDonGia(resultSet.getBigDecimal("DonGia"));
                    return chiTietBan;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching detail by Bill and Product ID", e);
        }
    }
    
    //Phương thức tính tổng tiền theo mã hóa đơn
    public BigDecimal layTongTienTheoMaHD(int maHD) throws Exception {
        String query = "SELECT SUM(SoLuong * DonGia) AS TongTien FROM ChiTietBan WHERE MaHD = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, maHD);
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
            throw new SQLException("Lỗi khi tính tổng tiền chi tiết bán theo MaHD: " + maHD, e);
        }
    }
}
