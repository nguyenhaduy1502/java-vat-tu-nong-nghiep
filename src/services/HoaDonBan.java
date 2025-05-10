/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.HoaDonBanDAO;
import java.math.BigDecimal;
import java.util.List;
import models.HoaDonBanDTO;

/**
 *
 * @author vinhp
 */
public class HoaDonBan {
   private HoaDonBanDAO hoaDonBanDAO;

    // Constructor khởi tạo HoaDonBanDAO
    public HoaDonBan() throws Exception {
        hoaDonBanDAO = new HoaDonBanDAO();
    }

    // Thêm hóa đơn bán
    public boolean themHoaDonBan(HoaDonBanDTO hoaDonBan) {
        try {
            return hoaDonBanDAO.themHoaDonBan(hoaDonBan);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa hóa đơn bán theo mã hóa đơn
    public boolean xoaHoaDonBan(int maHD) {
        try {
            return hoaDonBanDAO.xoaHoaDonBan(maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật hóa đơn bán
    public boolean suaHoaDonBan(HoaDonBanDTO hoaDonBan) {
        try {
            return hoaDonBanDAO.suaHoaDonBan(hoaDonBan);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách tất cả hóa đơn bán
    public List<HoaDonBanDTO> dsHoaDonBan() {
        try {
            return hoaDonBanDAO.dsHoaDonBan();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Tìm hóa đơn bán theo mã hóa đơn
    public HoaDonBanDTO timHoaDonBan(int maHD) {
        try {
            return hoaDonBanDAO.timHoaDonBan(maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean capNhatTongTien(int maHD, BigDecimal tongTien) {
        try {
            return hoaDonBanDAO.capNhatTongTien(maHD, tongTien);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
}

}
