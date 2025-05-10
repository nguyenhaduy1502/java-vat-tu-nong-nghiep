/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbservice.SQLServerConnection;
import models.NhaCungCapDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinhp
 */
public class NhaCungCapDAO {
    private SQLServerConnection connection;

    public NhaCungCapDAO() throws Exception {
        this.connection = new SQLServerConnection();
    }

    public int layMaNCCLonNhat() throws Exception {
        String query = "SELECT MAX(MaNCC) AS MaxMaNCC FROM NhaCungCap";
        try (Connection conn = connection.getConnect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("MaxMaNCC");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean themNhaCungCap(NhaCungCapDTO ncc) throws Exception {
        String query = "INSERT INTO NhaCungCap (MaNCC, TenNCC, DienThoai, DiaChi) VALUES (?, ?, ?, ?)";
        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            int maNCC = layMaNCCLonNhat() + 1;

            stmt.setInt(1, maNCC);
            stmt.setString(2, ncc.getTenNCC());
            stmt.setString(3, ncc.getDienThoai());
            stmt.setString(4, ncc.getDiaChi());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoaNhaCungCap(int maNCC) throws Exception {
        String query = "DELETE FROM NhaCungCap WHERE MaNCC = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, maNCC);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaNhaCungCap(NhaCungCapDTO ncc) throws Exception {
        String query = "UPDATE NhaCungCap SET TenNCC = ?, DienThoai = ?, DiaChi = ? WHERE MaNCC = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, ncc.getTenNCC());
            stmt.setString(2, ncc.getDienThoai());
            stmt.setString(3, ncc.getDiaChi());
            stmt.setInt(4, ncc.getMaNCC());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public NhaCungCapDTO timNhaCungCap(int maNCC) throws Exception {
        String query = "SELECT * FROM NhaCungCap WHERE MaNCC = ?";
        try (Connection conn = connection.getConnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, maNCC);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    NhaCungCapDTO ncc = new NhaCungCapDTO();
                    ncc.setMaNCC(rs.getInt("MaNCC"));
                    ncc.setTenNCC(rs.getString("TenNCC"));
                    ncc.setDienThoai(rs.getString("DienThoai"));
                    ncc.setDiaChi(rs.getString("DiaChi"));
                    return ncc;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi tìm nhà cung cấp theo mã", e);
        }
    }


    public List<NhaCungCapDTO> dsNhaCungCap() throws Exception {
        List<NhaCungCapDTO> ds = new ArrayList<>();
        String query = "SELECT * FROM NhaCungCap";
        try (Connection conn = connection.getConnect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                NhaCungCapDTO ncc = new NhaCungCapDTO();
                ncc.setMaNCC(rs.getInt("MaNCC"));
                ncc.setTenNCC(rs.getString("TenNCC"));
                ncc.setDienThoai(rs.getString("DienThoai"));
                ncc.setDiaChi(rs.getString("DiaChi"));
                ds.add(ncc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi lấy danh sách nhà cung cấp", e);
        }
        return ds;
    }

}
