/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.math.BigDecimal;

/**
 *
 * @author vinhp
 */
public class ChiTietNhapDTO {
    private int maPN;
    private int maSP;
    private int soLuong;
    private BigDecimal donGia;

    public ChiTietNhapDTO() {
    }

    public ChiTietNhapDTO(int maPN, int maSP, int soLuong, BigDecimal donGia) {
        this.maPN = maPN;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public int getMaPN() {
        return maPN;
    }

    public int getMaSP() {
        return maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "ChiTietNhapDTO{" + "maPN=" + maPN + ", maSP=" + maSP + ", soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }
    
    
}
