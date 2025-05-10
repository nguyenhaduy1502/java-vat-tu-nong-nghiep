/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.KhachHangDAO;
import java.util.List;
import models.KhachHangDTO;

/**
 *
 * @author vinhp
 */
public class KhachHang {
    private KhachHangDAO khachHangDAO;

    public KhachHang() throws Exception {
        this.khachHangDAO = new KhachHangDAO();
    }

    public boolean themKhachHang(KhachHangDTO khachHang) throws Exception {
        return khachHangDAO.themKhachHang(khachHang);
    }

    public boolean xoaKhachHang(int maKH) throws Exception {
        return khachHangDAO.xoaKhachHang(maKH);
    }

    public boolean suaKhachHang(KhachHangDTO khachHang) throws Exception {
        return khachHangDAO.suaKhachHang(khachHang);
    }

    public Object[] timKhachHang(int maKH) throws Exception {
        return khachHangDAO.timKhachHang(maKH);
    }

    public List<Object[]> dsKhachHang() throws Exception {
        return khachHangDAO.dsKhachHang();
    }
}
