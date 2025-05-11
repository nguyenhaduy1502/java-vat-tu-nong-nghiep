/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vtnn.app.services;

import com.vtnn.app.dao.ChiTietBanDAO;
import java.math.BigDecimal;
import java.util.List;
import com.vtnn.app.models.ChiTietBanDTO;

/**
 *
 * @author vinhp
 */
public class ChiTietBan {
    private ChiTietBanDAO chiTietBanDAO;

    // Constructor khởi tạo đối tượng ChiTietBanDAO
    public ChiTietBan() throws Exception {
        this.chiTietBanDAO = new ChiTietBanDAO();
    }

    // Thêm chi tiết bán mới
    public boolean themChiTietBan(ChiTietBanDTO chiTietBan) throws Exception {
        return chiTietBanDAO.themChiTietBan(chiTietBan);
    }

    // Xóa chi tiết bán theo mã hóa đơn và mã sản phẩm
    public boolean xoaChiTietBan(int maHD, int maSP) throws Exception {
        return chiTietBanDAO.xoaChiTietBan(maHD, maSP);
    }

    // Cập nhật chi tiết bán (số lượng, đơn giá)
    public boolean suaChiTietBan(ChiTietBanDTO chiTietBan) throws Exception {
        return chiTietBanDAO.suaChiTietBan(chiTietBan);
    }

    // Lấy danh sách chi tiết bán của một hóa đơn
    public List<ChiTietBanDTO> dsChiTietBan(int maHD) throws Exception {
        return chiTietBanDAO.dsChiTietBan(maHD);
    }

    // Tìm chi tiết bán theo mã hóa đơn và mã sản phẩm
    public ChiTietBanDTO timChiTietBan(int maHD, int maSP) throws Exception {
        return chiTietBanDAO.timChiTietBan(maHD, maSP);
    }
    
    // Tính tổng tiền chi tiết bán theo mã hóa đơn
    public BigDecimal layTongTienTheoMaHD(int maHD) throws Exception {
        return chiTietBanDAO.layTongTienTheoMaHD(maHD);
    }
}
