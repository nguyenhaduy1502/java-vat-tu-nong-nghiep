package com.vtnn.app.services;

import com.vtnn.app.models.HoaDonBanDTO;
import com.vtnn.app.models.ChiTietBanDTO;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PDFGeneratorExample {
    public static void main(String[] args) {
        try {
            // Create a sample invoice
            HoaDonBanDTO hoaDon = new HoaDonBanDTO(
                1,                          // maHD
                new Date(System.currentTimeMillis()), // ngayBan
                1001,                       // maKH
                "Nguyễn Văn A",            // nguoiTao
                new BigDecimal("1500000")   // tongTien
            );

            // Create sample invoice details
            List<ChiTietBanDTO> chiTietBan = new ArrayList<>();
            chiTietBan.add(new ChiTietBanDTO(
                1,                          // maHD
                1,                          // maSP
                2,                          // soLuong
                new BigDecimal("500000")    // donGia
            ));
            chiTietBan.add(new ChiTietBanDTO(
                1,                          // maHD
                2,                          // maSP
                1,                          // soLuong
                new BigDecimal("500000")    // donGia
            ));

            // Generate PDF
            PDFGeneratorService pdfGenerator = new PDFGeneratorService();
            pdfGenerator.generateInvoicePDF(hoaDon, chiTietBan, "invoice.pdf");
            
            System.out.println("PDF generated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 