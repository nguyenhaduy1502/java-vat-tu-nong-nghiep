/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbservice.SQLServerConnection;
import java.math.BigDecimal;
import models.HoaDonBanDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinhp
 */
public class HoaDonBanDAO {
    private SQLServerConnection connection;

    // Constructor khởi tạo kết nối CSDL
    public HoaDonBanDAO() throws Exception {
        this.connection = new SQLServerConnection();
    }

    // Phương thức thêm hóa đơn bán
    public boolean themHoaDonBan(HoaDonBanDTO hoaDonBan) throws Exception {
        String query = "INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, hoaDonBan.getMaHD());
            statement.setDate(2, hoaDonBan.getNgayBan());
            statement.setInt(3, hoaDonBan.getMaKH());
            statement.setString(4, hoaDonBan.getNguoiTao());
            statement.setBigDecimal(5, hoaDonBan.getTongTien());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức xóa hóa đơn bán theo mã hóa đơn
    public boolean xoaHoaDonBan(int maHD) throws Exception {
        String query = "DELETE FROM HoaDonBan WHERE MaHD = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, maHD);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức cập nhật hóa đơn bán
    public boolean suaHoaDonBan(HoaDonBanDTO hoaDonBan) throws Exception {
        String query = "UPDATE HoaDonBan SET NgayBan = ?, MaKH = ?, NguoiTao = ?, TongTien = ? WHERE MaHD = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setDate(1, hoaDonBan.getNgayBan());
            statement.setInt(2, hoaDonBan.getMaKH());
            statement.setString(3, hoaDonBan.getNguoiTao());
            statement.setBigDecimal(4, hoaDonBan.getTongTien());
            statement.setInt(5, hoaDonBan.getMaHD());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức lấy danh sách hóa đơn bán
    public List<HoaDonBanDTO> dsHoaDonBan() throws Exception {
        List<HoaDonBanDTO> dsHoaDonBan = new ArrayList<>();
        String query = "SELECT * FROM HoaDonBan";
        try (Connection conn = connection.getConnect();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int maHD = resultSet.getInt("MaHD");
                Date ngayBan = resultSet.getDate("NgayBan");
                int maKH = resultSet.getInt("MaKH");
                String nguoiTao = resultSet.getString("NguoiTao");
                BigDecimal tongTien = resultSet.getBigDecimal("TongTien");
                dsHoaDonBan.add(new HoaDonBanDTO(maHD, ngayBan, maKH, nguoiTao, tongTien));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error fetching sales invoices", e);
        }
        return dsHoaDonBan;
    }

    // Phương thức tìm hóa đơn bán theo mã hóa đơn
    public HoaDonBanDTO timHoaDonBan(int maHD) throws Exception {
        String query = "SELECT * FROM HoaDonBan WHERE MaHD = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, maHD);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Date ngayBan = resultSet.getDate("NgayBan");
                    int maKH = resultSet.getInt("MaKH");
                    String nguoiTao = resultSet.getString("NguoiTao");
                    BigDecimal tongTien = resultSet.getBigDecimal("TongTien");
                    return new HoaDonBanDTO(maHD, ngayBan, maKH, nguoiTao, tongTien);
                } else {
                    throw new Exception("Không tìm thấy hóa đơn bán với mã: " + maHD);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error when searching for sales invoice", e);
        }
    }
    
    //Phương thức cập nhật tổng tiền theo mã hd
    public boolean capNhatTongTien(int maHD, BigDecimal tongTien) throws Exception {
        String query = "UPDATE HoaDonBan SET TongTien = ? WHERE MaHD = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setBigDecimal(1, tongTien);
            statement.setInt(2, maHD);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi cập nhật tổng tiền cho hóa đơn bán MaHD: " + maHD, e);
        }
    }

}
