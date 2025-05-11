/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.PhieuNhapDAO;
import java.math.BigDecimal;
import java.util.List;
import models.PhieuNhapDTO;

/**
 *
 * @author vinhp
 */
public class PhieuNhap {
    private PhieuNhapDAO phieuNhapDAO;

    public PhieuNhap() throws Exception {
        this.phieuNhapDAO = new PhieuNhapDAO();
    }

    // Thêm phiếu nhập
    public boolean themPhieuNhap(PhieuNhapDTO pn) throws Exception {
        return phieuNhapDAO.themPhieuNhap(pn);
    }

    // Xóa phiếu nhập
    public boolean xoaPhieuNhap(int maPN) throws Exception {
        return phieuNhapDAO.xoaPhieuNhap(maPN);
    }

    // Cập nhật phiếu nhập
    public boolean suaPhieuNhap(PhieuNhapDTO pn) throws Exception {
        return phieuNhapDAO.suaPhieuNhap(pn);
    }

    // Lấy danh sách phiếu nhập
    public List<Object[]> dsPhieuNhap() throws Exception {
        return phieuNhapDAO.dsPhieuNhap();
    }

    // Tìm phiếu nhập theo mã
    public Object[] timPhieuNhap(int maPN) throws Exception {
        return phieuNhapDAO.timPhieuNhap(maPN);
    }

    // Lấy mã phiếu nhập lớn nhất
    public int layMaPNLonNhat() throws Exception {
        return phieuNhapDAO.layMaPNLonNhat();
    }
    
    // Cập nhật tổng tiền của phiếu nhập
    public boolean capNhatTongTien(int maPN, BigDecimal tongTien) {
        try {
            return phieuNhapDAO.capNhatTongTien(maPN, tongTien);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
