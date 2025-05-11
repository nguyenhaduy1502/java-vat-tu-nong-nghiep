/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.NguoiDungDAO;
import models.NguoiDungDTO;

/**
 *
 * @author vinhp
 */
public class NguoiDung {
    private NguoiDungDAO nguoiDungDAO;

    public NguoiDung() throws Exception {
        nguoiDungDAO = new NguoiDungDAO();
    }

    public boolean themNguoiDung(NguoiDungDTO nd) throws Exception {
        return nguoiDungDAO.themNguoiDung(nd);
    }

    public boolean xoaNguoiDung(String tenDangNhap) throws Exception {
        return nguoiDungDAO.xoaNguoiDung(tenDangNhap);
    }
    
    public boolean xoaNguoiDungTheoMaNV(int maNV) throws Exception {
        return nguoiDungDAO.xoaNguoiDungTheoMaNV(maNV);
    }

    public boolean suaMatKhau(String tenDangNhap, String matKhauMoi) throws Exception {
        return nguoiDungDAO.suaMatKhau(tenDangNhap, matKhauMoi);
    }

    public NguoiDungDTO timNguoiDung(String tenDangNhap) throws Exception {
        return nguoiDungDAO.timNguoiDung(tenDangNhap);
    }
    
    public NguoiDungDTO timNguoiDungTheoMaNV(int maNV) throws Exception {
        return nguoiDungDAO.timNguoiDungTheoMaNV(maNV);
    }

}
