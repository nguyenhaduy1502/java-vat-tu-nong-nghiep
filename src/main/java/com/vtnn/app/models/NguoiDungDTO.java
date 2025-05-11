/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vtnn.app.models;

/**
 *
 * @author vinhp
 */
public class NguoiDungDTO {
    private int maNguoiDung;
    private String tenDangNhap;
    private String matKhau;
    private int maNV;
    private UserRole vaiTro;
    private boolean trangThai;

    public NguoiDungDTO() {
    }

    public NguoiDungDTO(String tenDangNhap, String matKhau, int maNV) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maNV = maNV;
        this.trangThai = true;
    }

    public NguoiDungDTO(String tenDangNhap, String matKhau, int maNV, UserRole vaiTro) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maNV = maNV;
        this.vaiTro = vaiTro;
        this.trangThai = true;
    }

    public NguoiDungDTO(int maNguoiDung, String tenDangNhap, String matKhau, int maNV, UserRole vaiTro, boolean trangThai) {
        this.maNguoiDung = maNguoiDung;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maNV = maNV;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public UserRole getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(UserRole vaiTro) {
        this.vaiTro = vaiTro;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
}
