/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author vinhp
 */
public class HoaDonBanDTO {
    private int maHD;
    private Date ngayBan;
    private int maKH;
    private String nguoiTao;
    private BigDecimal tongTien;

    public HoaDonBanDTO() {
    }

    public HoaDonBanDTO(int maHD, Date ngayBan, int maKH, String nguoiTao, BigDecimal tongTien) {
        this.maHD = maHD;
        this.ngayBan = ngayBan;
        this.maKH = maKH;
        this.nguoiTao = nguoiTao;
        this.tongTien = tongTien;
    }

    public int getMaHD() {
        return maHD;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public int getMaKH() {
        return maKH;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "HoaDonBanDTO{" + "maHD=" + maHD + ", ngayBan=" + ngayBan + ", maKH=" + maKH + ", nguoiTao=" + nguoiTao + ", tongTien=" + tongTien + '}';
    }
    
    
}
