/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vtnn.app.models;

/**
 *
 * @author vinhp
 */
public class NhaCungCapDTO {
    private int maNCC;
    private String tenNCC;
    private String dienThoai;
    private String diaChi;
    
    public NhaCungCapDTO() {
    }

    public NhaCungCapDTO(int maNCC, String tenNCC, String dienThoai, String diaChi) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.dienThoai = dienThoai;
        this.diaChi = diaChi;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "NhaCungCapDTO{" + "maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", dienThoai=" + dienThoai + ", diaChi=" + diaChi + '}';
    }
    
 
}
