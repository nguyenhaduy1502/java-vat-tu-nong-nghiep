/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import models.NhanVienDTO;
import dao.NhanVienDAO;
import java.util.List;

/**
 *
 * @author vinhp
 */
public class NhanVien {
    private NhanVienDAO nhanVienDAO;
    
    public NhanVien() throws Exception {
        this.nhanVienDAO = new NhanVienDAO();
    }
    
    // Thêm nhân viên
    public boolean themNhanVien(NhanVienDTO nhanVien) throws Exception {
        return nhanVienDAO.themNhanVien(nhanVien);
    }
    
    // Xóa nhân viên
    public boolean xoaNhanVien(int maNV) throws Exception {
        return nhanVienDAO.xoaNhanVien(maNV);
    }
    
    // Sửa thông tin nhân viên
    public boolean suaNhanVien(NhanVienDTO nhanVien) throws Exception {
        return nhanVienDAO.suaNhanVien(nhanVien);
    }
    
    // Tìm nhân viên theo mã
    public Object[] timNhanVien(int maNV) throws Exception {
        return nhanVienDAO.timNhanVien(maNV);
    }
    
    // Lấy danh sách tất cả nhân viên
    public List<Object[]> dsNhanVien() throws Exception {
        return nhanVienDAO.dsNhanVien();
    }
}
