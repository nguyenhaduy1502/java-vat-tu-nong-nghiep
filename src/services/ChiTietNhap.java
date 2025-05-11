/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.ChiTietNhapDAO;
import java.math.BigDecimal;
import java.util.List;
import models.ChiTietNhapDTO;

/**
 *
 * @author vinhp
 */
public class ChiTietNhap {
    private ChiTietNhapDAO chiTietNhapDAO;

    // Constructor khởi tạo ChiTietNhapDAO
    public ChiTietNhap() throws Exception {
        chiTietNhapDAO = new ChiTietNhapDAO();
    }

    // Thêm chi tiết nhập
    public boolean themChiTietNhap(ChiTietNhapDTO chiTietNhap) {
        try {
            return chiTietNhapDAO.themChiTietNhap(chiTietNhap);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa chi tiết nhập theo mã phiếu nhập và mã sản phẩm
    public boolean xoaChiTietNhap(int maPN, int maSP) {
        try {
            return chiTietNhapDAO.xoaChiTietNhap(maPN, maSP);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật chi tiết nhập
    public boolean suaChiTietNhap(ChiTietNhapDTO chiTietNhap) {
        try {
            return chiTietNhapDAO.suaChiTietNhap(chiTietNhap);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách chi tiết nhập của phiếu nhập
    public List<ChiTietNhapDTO> dsChiTietNhap(int maPN) {
        try {
            return chiTietNhapDAO.dsChiTietNhapTheoMaPN(maPN);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Tìm chi tiết nhập theo mã phiếu nhập và mã sản phẩm
    public ChiTietNhapDTO timChiTietNhap(int maPN, int maSP) {
        try {
            return chiTietNhapDAO.timChiTietNhap(maPN, maSP);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Tính tổng tiền của một phiếu nhập
    public BigDecimal layTongTienTheoMaPN(int maPN) {
        try {
            return chiTietNhapDAO.layTongTienTheoMaPN(maPN);
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }
}
