/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.SanPhamDAO;
import java.util.List;
import models.SanPhamDTO;

/**
 *
 * @author vinhp
 */
public class SanPhamService {
   private SanPhamDAO sanPhamDAO;

    public SanPhamService() throws Exception {
        sanPhamDAO = new SanPhamDAO();
    }

    // Thêm sản phẩm
    public boolean themSanPham(SanPhamDTO sp) {
        try {
            return sanPhamDAO.themSanPham(sp);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa sản phẩm theo mã
    public boolean xoaSanPham(int maSP) {
        try {
            return sanPhamDAO.xoaSanPham(maSP);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Sửa thông tin sản phẩm
    public boolean suaSanPham(SanPhamDTO sp) {
        try {
            return sanPhamDAO.suaSanPham(sp);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Tìm sản phẩm theo mã
    public Object[] timSanPham(int maSP) {
        try {
            return sanPhamDAO.timSanPham(maSP);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Lấy danh sách sản phẩm
    public List<Object[]> dsSanPham() {
        try {
            return sanPhamDAO.dsSanPham();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    } 
}
