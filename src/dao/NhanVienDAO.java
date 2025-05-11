/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbservice.SQLServerConnection;
import models.NhanVienDTO;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinhp
 */
public class NhanVienDAO {
    private SQLServerConnection connection;

    //Constructor khởi tạo kết nối CSDL
    public NhanVienDAO() throws Exception {
        this.connection = new SQLServerConnection();
    }
    
    //Phương thức lấy MaNV dòng cuối
    public int layMaNVLonNhat() throws Exception {
        String query = "SELECT MAX(MaNV) AS MaxMaNV FROM NhanVien";
        try (Connection conn = connection.getConnect();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt("MaxMaNV");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi lấy mã nhân viên lớn nhất", e);
        }
        return 0;
    }
    
    //Hàm tạo tự động mã nhân viên
    public int taoMaNV() throws Exception {
        int namHienTai = LocalDate.now().getYear() % 100; // lấy 2 số cuối của năm, ví dụ: 2025 -> 25
        int maNVMax = layMaNVLonNhat();

        int soThuTu = 1;

        if (maNVMax > 0 && maNVMax / 1000 == namHienTai) {
            soThuTu = maNVMax % 1000 + 1;
        }

        int maMoi = namHienTai * 1000 + soThuTu; // ghép lại thành yyxxx
        return maMoi;
    }
    
    //Phương thức thêm nhân viên
    public boolean themNhanVien(NhanVienDTO nhanVien) throws Exception {
        String query = "INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai, DiaChi, Email, VaiTro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connection.getConnect();
             PreparedStatement statement = conn.prepareStatement(query)) {

            int maNV = taoMaNV(); // sinh mã tự động dựa trên năm + số thứ tự
            nhanVien.setMaNV(maNV); // gán mã nhân viên cho DTO

            statement.setInt(1, maNV);
            statement.setString(2, nhanVien.getHoTen());
            statement.setDate(3, nhanVien.getNgaySinh());
            statement.setInt(4, nhanVien.getGioiTinh());
            statement.setString(5, nhanVien.getSoDienThoai());
            statement.setString(6, nhanVien.getDiaChi());
            statement.setString(7, nhanVien.getEmail());
            statement.setInt(8, nhanVien.getVaiTro());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Phương thức xóa nhân viên
    public boolean xoaNhanVien(int maNV) throws Exception {
        String query = "DELETE FROM NhanVien WHERE MaNV = ?";
        try (Connection conn = connection.getConnect();
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, maNV);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Phương thức cập nhật thông tin nhân viên
    public boolean suaNhanVien(NhanVienDTO nhanVien) throws Exception {
        String query = "UPDATE NhanVien SET HoTen = ?, NgaySinh = ?, GioiTinh = ?, SoDienThoai = ?, DiaChi = ?, Email = ?, VaiTro = ? WHERE MaNV = ?";
        try (Connection conn = connection.getConnect();
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, nhanVien.getHoTen());
            statement.setDate(2, nhanVien.getNgaySinh());
            statement.setInt(3, nhanVien.getGioiTinh());
            statement.setString(4, nhanVien.getSoDienThoai());
            statement.setString(5, nhanVien.getDiaChi());
            statement.setString(6, nhanVien.getEmail());
            statement.setInt(7, nhanVien.getVaiTro());
            statement.setInt(8, nhanVien.getMaNV());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Phương thức lấy tất cả nhân viên
    public List<Object[]> dsNhanVien() throws Exception {
        List<Object[]> dsNhanVien = new ArrayList<>();
        String query = """
                       SELECT NhanVien.MaNV, NhanVien.HoTen, NhanVien.NgaySinh, GioiTinh.LoaiGT, NhanVien.SoDienThoai, NhanVien.DiaChi, NhanVien.Email, VaiTro.TenVT
                       FROM NhanVien
                       JOIN GioiTinh ON NhanVien.GioiTinh = GioiTinh.MaGT
                       JOIN VaiTro ON NhanVien.VaiTro = VaiTro.MaVT
                       """;
        try (Connection conn = connection.getConnect();
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            while(resultSet.next()) {
                int maNV = resultSet.getInt("MaNV");
                String hoTen = resultSet.getString("HoTen");
                Date ngaySinh = resultSet.getDate("NgaySinh");
                String gioiTinh = resultSet.getString("LoaiGT");
                String soDienThoai = resultSet.getString("SoDienThoai");
                String diaChi = resultSet.getString("DiaChi");
                String email = resultSet.getString("Email");
                String vaiTro = resultSet.getString("TenVT");
                
                dsNhanVien.add(new Object[] {
                    maNV, hoTen, ngaySinh, gioiTinh, soDienThoai, diaChi, email, vaiTro
                });    
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi lấy thông tin chi tiết của nhân viên", e);
        }
        return dsNhanVien;
    }
    
    //Phuơng thức tìm nhân viên bằng MaNV
    public Object[] timNhanVien(int maNV) throws Exception {
        String query = """
                       SELECT NhanVien.MaNV, NhanVien.HoTen, NhanVien.NgaySinh, GioiTinh.LoaiGT, NhanVien.SoDienThoai, NhanVien.DiaChi, NhanVien.Email, VaiTro.TenVT
                       FROM NhanVien
                       JOIN GioiTinh ON NhanVien.GioiTinh = GioiTinh.MaGT
                       JOIN VaiTro ON NhanVien.VaiTro = VaiTro.MaVT
                       WHERE NhanVien.MaNV = ?
                       """;
        try (Connection conn = connection.getConnect();
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, maNV);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Object[] {
                        resultSet.getInt("MaNV"),
                        resultSet.getString("HoTen"),
                        resultSet.getDate("NgaySinh"),
                        resultSet.getString("LoaiGT"),
                        resultSet.getString("SoDienThoai"),
                        resultSet.getString("DiaChi"),
                        resultSet.getString("Email"),
                        resultSet.getString("TenVT")
                    };
                } else {
                    throw new Exception("Không tìm thấy nhân viên với mã: " + maNV);
                }
            }  
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi tìm kiếm nhân viên theo mã", e);
        }
    }
}
