/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vtnn.app.models;

import java.math.BigDecimal;

/**
 *
 * @author vinhp
 */
public class ChiTietBanDTO {
    private int maHD;
    private int maSP;
    private int soLuong;
    private BigDecimal donGia;


    public ChiTietBanDTO() {
    }

    public ChiTietBanDTO(int maHD, int maSP, int soLuong, BigDecimal donGia) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public int getMaHD() {
        return maHD;
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

    public void setMaHD(int maHD) {
        this.maHD = maHD;
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
        return "ChiTietBanDTO{" + "maHD=" + maHD + ", maSP=" + maSP + ", soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }
    
    
}
