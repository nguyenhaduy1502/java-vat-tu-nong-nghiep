package com.vtnn.app.services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.vtnn.app.models.HoaDonBanDTO;
import com.vtnn.app.models.ChiTietBanDTO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

public class PDFGeneratorService {
    
    public void generateInvoicePDF(HoaDonBanDTO hoaDon, List<ChiTietBanDTO> chiTietBan, String outputPath) throws FileNotFoundException, IOException {
        // Path to the font file (make sure this file exists in your project)
        String fontPath = "src/main/resources/fonts/DejaVuSans.ttf";
        PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);

        // Create PDF writer
        PdfWriter writer = new PdfWriter(new File(outputPath));
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.setFont(font);
        
        // Add title
        Paragraph title = new Paragraph("HÓA ĐƠN BÁN HÀNG")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20)
                .setBold();
        document.add(title);
        
        // Add invoice information
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        document.add(new Paragraph("Mã hóa đơn: " + hoaDon.getMaHD()));
        document.add(new Paragraph("Ngày bán: " + dateFormat.format(hoaDon.getNgayBan())));
        document.add(new Paragraph("Mã khách hàng: " + hoaDon.getMaKH()));
        document.add(new Paragraph("Người tạo: " + hoaDon.getNguoiTao()));
        
        // Create table for items
        Table table = new Table(new float[]{1, 3, 2, 2, 2});
        table.setWidth(UnitValue.createPercentValue(100));
        
        // Add table headers
        table.addHeaderCell(new Cell().add(new Paragraph("STT")));
        table.addHeaderCell(new Cell().add(new Paragraph("Sản phẩm")));
        table.addHeaderCell(new Cell().add(new Paragraph("Số lượng")));
        table.addHeaderCell(new Cell().add(new Paragraph("Đơn giá")));
        table.addHeaderCell(new Cell().add(new Paragraph("Thành tiền")));
        
        // Add items
        int stt = 1;
        for (ChiTietBanDTO item : chiTietBan) {
            table.addCell(new Cell().add(new Paragraph(String.valueOf(stt++))));
            table.addCell(new Cell().add(new Paragraph("SP" + item.getMaSP())));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(item.getSoLuong()))));
            table.addCell(new Cell().add(new Paragraph(formatCurrency(item.getDonGia()))));
            table.addCell(new Cell().add(new Paragraph(formatCurrency(item.getDonGia().multiply(new BigDecimal(item.getSoLuong()))))));
        }
        
        document.add(table);
        
        // Add total
        Paragraph total = new Paragraph("Tổng tiền: " + formatCurrency(hoaDon.getTongTien()))
                .setTextAlignment(TextAlignment.RIGHT)
                .setBold();
        document.add(total);
        
        // Close document
        document.close();
    }
    
    private String formatCurrency(BigDecimal amount) {
        return String.format("%,.0f VNĐ", amount.doubleValue());
    }
} 