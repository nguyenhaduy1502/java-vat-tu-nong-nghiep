-- SQL script for chart data visualization
-- Execute this script to add both sales and purchase data to make charts more visible
USE QL_VatTuNongNghiep
GO

----------------------------------------------------------
-- PART 1: SALES DATA FOR MONTHLY SALES CHART
----------------------------------------------------------

-- Add sales for January 2025
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250011, '2025-01-05', 25001, N'phamthilan', 2450000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250011, 2001, 5, 280000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250011, 2013, 8, 38000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250011, 2025, 3, 87000)

INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250012, '2025-01-15', 25003, N'trinhthimai', 3250000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250012, 2005, 6, 210000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250012, 2016, 10, 46000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250012, 2041, 3, 600000)

-- Add sales for February 2025
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250013, '2025-02-03', 25005, N'nguyenhoangnam', 2800000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250013, 2003, 4, 300000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250013, 2017, 7, 48000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250013, 2047, 1, 1350000)

INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250014, '2025-02-18', 25007, N'phamthilan', 4100000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250014, 2006, 8, 290000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250014, 2018, 5, 42000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250014, 2041, 4, 600000)

-- Add sales for March 2025
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250015, '2025-03-07', 25009, N'nguyenhoangnam', 3850000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250015, 2008, 5, 285000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250015, 2019, 9, 55000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250015, 2047, 2, 1350000)

INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250016, '2025-03-22', 25002, N'vothithanh', 5450000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250016, 2001, 10, 280000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250016, 2020, 12, 47000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250016, 2041, 5, 600000)

-- Add sales for May 2025
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250017, '2025-05-05', 25004, N'phamthilan', 4980000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250017, 2002, 8, 250000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250017, 2022, 6, 85000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250017, 2041, 4, 600000)

INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250018, '2025-05-18', 25006, N'vothithanh', 6720000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250018, 2004, 12, 320000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250018, 2023, 8, 82000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250018, 2047, 2, 1350000)

-- Add sales for June 2025
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250019, '2025-06-10', 25008, N'nguyenhoangnam', 7850000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250019, 2005, 15, 210000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250019, 2024, 10, 79000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250019, 2041, 7, 600000)

INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250020, '2025-06-25', 25010, N'phamthilan', 8320000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250020, 2006, 10, 290000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250020, 2025, 8, 87000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250020, 2047, 3, 1350000)

-- Add sales for July 2025
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250021, '2025-07-08', 25001, N'phamthilan', 8950000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250021, 2007, 12, 240000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250021, 2026, 9, 84000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250021, 2041, 8, 600000)

INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250022, '2025-07-20', 25003, N'trinhthimai', 9180000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250022, 2008, 11, 285000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250022, 2027, 7, 80000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250022, 2047, 4, 1350000)

-- Add sales for August 2025
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250023, '2025-08-05', 25005, N'nguyenhoangnam', 7250000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250023, 2009, 10, 295000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250023, 2028, 6, 88000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250023, 2041, 6, 600000)

INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250024, '2025-08-18', 25007, N'phamthilan', 6950000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250024, 2010, 9, 225000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250024, 2029, 5, 78000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250024, 2047, 3, 1350000)

-- Add sales for September 2025
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250025, '2025-09-07', 25009, N'nguyenhoangnam', 5680000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250025, 2001, 8, 280000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250025, 2030, 4, 81000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250025, 2041, 5, 600000)

INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250026, '2025-09-22', 25002, N'vothithanh', 4950000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250026, 2002, 6, 250000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250026, 2031, 3, 100000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250026, 2047, 2, 1350000)

-- Add sales for October 2025
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250027, '2025-10-05', 25004, N'phamthilan', 8150000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250027, 2003, 9, 300000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250027, 2032, 5, 105000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250027, 2041, 9, 600000)

INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250028, '2025-10-18', 25006, N'vothithanh', 9350000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250028, 2004, 13, 320000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250028, 2033, 7, 103000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250028, 2047, 3, 1350000)

-- Add sales for November 2025
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250029, '2025-11-10', 25008, N'nguyenhoangnam', 9650000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250029, 2005, 18, 210000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250029, 2034, 9, 104000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250029, 2041, 10, 600000)

INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250030, '2025-11-25', 25010, N'phamthilan', 10250000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250030, 2006, 15, 290000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250030, 2035, 8, 101000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250030, 2047, 4, 1350000)

-- Add sales for December 2025
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250031, '2025-12-08', 25001, N'phamthilan', 12850000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250031, 2007, 20, 240000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250031, 2036, 15, 107000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250031, 2041, 12, 600000)

INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (250032, '2025-12-20', 25003, N'trinhthimai', 14250000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250032, 2008, 18, 285000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250032, 2037, 12, 106000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (250032, 2047, 6, 1350000)

-- Optional: Add data for the previous year (2024) to show year-over-year trends
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (240001, '2024-01-15', 25001, N'phamthilan', 1850000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (240001, 2001, 3, 280000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (240001, 2015, 5, 35000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (240001, 2045, 9, 90000)

INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (240002, '2024-03-20', 25003, N'trinhthimai', 2980000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (240002, 2002, 4, 250000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (240002, 2016, 6, 46000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (240002, 2041, 3, 600000)

INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (240003, '2024-06-10', 25005, N'nguyenhoangnam', 3650000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (240003, 2003, 5, 300000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (240003, 2017, 7, 48000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (240003, 2047, 1, 1350000)

INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (240004, '2024-09-05', 25007, N'phamthilan', 4250000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (240004, 2004, 6, 320000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (240004, 2018, 8, 42000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (240004, 2041, 4, 600000)

INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (240005, '2024-12-18', 25009, N'nguyenhoangnam', 5850000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (240005, 2005, 8, 210000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (240005, 2019, 10, 55000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (240005, 2047, 2, 1350000)

----------------------------------------------------------
-- PART 2: PURCHASE DATA FOR PROFIT TREND CHART
----------------------------------------------------------

-- January 2025 Purchases
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250010, 1001, '2025-01-10', N'nguyenduyhoang', 7500000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250010, 2001, 15, 250000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250010, 2002, 12, 220000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250010, 2003, 10, 270000)

-- February 2025 Purchases
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250011, 1002, '2025-02-08', N'hoangminhduy', 2100000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250011, 2011, 25, 35000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250011, 2012, 20, 40000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250011, 2013, 15, 30000)

-- March 2025 Purchases
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250012, 1003, '2025-03-12', N'leminhtuan', 2050000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250012, 2021, 12, 60000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250012, 2022, 10, 70000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250012, 2023, 8, 68000)

-- April 2025 Purchases
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250013, 1004, '2025-04-15', N'tranquanghieu', 2600000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250013, 2031, 12, 85000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250013, 2032, 10, 90000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250013, 2033, 8, 87000)

-- May 2025 Purchases
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250014, 1005, '2025-05-10', N'nguyenduyhoang', 4250000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250014, 2041, 5, 500000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250014, 2047, 2, 1200000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250014, 2042, 5, 120000)

-- June 2025 Purchases
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250015, 1001, '2025-06-14', N'tranquanghieu', 7800000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250015, 2004, 10, 290000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250015, 2005, 15, 180000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250015, 2006, 12, 260000)

-- July 2025 Purchases
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250016, 1002, '2025-07-12', N'hoangminhduy', 2750000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250016, 2014, 20, 32000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250016, 2015, 25, 28000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250016, 2016, 30, 37000)

-- August 2025 Purchases
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250017, 1003, '2025-08-10', N'leminhtuan', 3300000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250017, 2024, 15, 64000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250017, 2025, 18, 71000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250017, 2026, 20, 69000)

-- September 2025 Purchases
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250018, 1004, '2025-09-08', N'hoangminhduy', 3900000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250018, 2034, 12, 88000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250018, 2035, 15, 86000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250018, 2036, 18, 92000)

-- October 2025 Purchases
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250019, 1005, '2025-10-05', N'tranquanghieu', 9200000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250019, 2041, 8, 500000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250019, 2047, 5, 1200000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250019, 2048, 10, 150000)

-- November 2025 Purchases
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250020, 1001, '2025-11-12', N'leminhtuan', 10500000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250020, 2007, 20, 210000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250020, 2008, 15, 255000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250020, 2009, 18, 265000)

-- December 2025 Purchases
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250021, 1002, '2025-12-09', N'nguyenduyhoang', 12000000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250021, 2017, 30, 39000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250021, 2018, 25, 33000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250021, 2019, 20, 45000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250021, 2041, 10, 500000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250021, 2047, 3, 1200000)

-- Verify the data by executing the following queries
-- SELECT * FROM HoaDonBan WHERE YEAR(NgayBan) >= 2024 ORDER BY NgayBan
-- SELECT * FROM PhieuNhap WHERE YEAR(NgayNhap) = 2025 ORDER BY NgayNhap 