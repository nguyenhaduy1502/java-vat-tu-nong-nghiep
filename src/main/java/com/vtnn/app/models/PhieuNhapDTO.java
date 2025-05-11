/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vtnn.app.models;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author vinhp
 */
public class PhieuNhapDTO {
    private int maPN;
    private Date ngayNhap;
    private int maNCC;
    private String nguoiTao;
    private BigDecimal tongTien;

    public PhieuNhapDTO() {
    }

    public PhieuNhapDTO(int maPN, Date ngayNhap, int maNCC, String nguoiTao, BigDecimal tongTien) {
        this.maPN = maPN;
        this.ngayNhap = ngayNhap;
        this.maNCC = maNCC;
        this.nguoiTao = nguoiTao;
        this.tongTien = tongTien;
    }

    public int getMaPN() {
        return maPN;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "PhieuNhapDTO{" + "maPN=" + maPN + ", ngayNhap=" + ngayNhap + ", maNCC=" + maNCC + ", nguoiTao=" + nguoiTao + ", tongTien=" + tongTien + '}';
    }
    
    
    
    
}
