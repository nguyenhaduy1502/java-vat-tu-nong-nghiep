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
public class SanPhamDTO {
    private int maSP;
    private String tenSP;
    private int donViTinh;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private int soLuongTon;
    private int nhomHang;
    
    public SanPhamDTO() {
    }

    public SanPhamDTO(int maSP, String tenSP, int donViTinh, BigDecimal giaNhap, BigDecimal giaBan, int soLuongTon, int nhomHang) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.donViTinh = donViTinh;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
        this.nhomHang = nhomHang;
    }

    public int getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public int getDonViTinh() {
        return donViTinh;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public int getNhomHang() {
        return nhomHang;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setDonViTinh(int donViTinh) {
        this.donViTinh = donViTinh;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public void setNhomHang(int nhomHang) {
        this.nhomHang = nhomHang;
    }

    @Override
    public String toString() {
        return "SanPhamDTO{" + "maSP=" + maSP + ", tenSP=" + tenSP + ", donViTinh=" + donViTinh + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", soLuongTon=" + soLuongTon + ", nhomHang=" + nhomHang + '}';
    }
    
    
}
