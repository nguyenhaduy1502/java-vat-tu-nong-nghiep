
-- Tạo cơ sở dữ liệu
CREATE DATABASE QL_VatTuNongNghiep
GO

USE QL_VatTuNongNghiep
GO

--Các bảng định danh
CREATE TABLE GioiTinh (
	MaGT INT NOT NULL PRIMARY KEY,
	LoaiGT NVARCHAR(10) NOT NULL
)

INSERT INTO GioiTinh (MaGT, LoaiGT) VALUES (1, N'Nam')
INSERT INTO GioiTinh (MaGT, LoaiGT) VALUES (2, N'Nữ')
--
CREATE TABLE VaiTro (
	MaVT INT NOT NULL PRIMARY KEY,
	TenVT NVARCHAR(30) NOT NULL
)

INSERT INTO VaiTro (MaVT, TenVT) VALUES (1, N'Quản lý')
INSERT INTO VaiTro (MaVT, TenVT) VALUES (2, N'Nhân viên bán hàng')
INSERT INTO VaiTro (MaVT, TenVT) VALUES (3, N'Kế toán')
INSERT INTO VaiTro (MaVT, TenVT) VALUES (4, N'Nhân viên kho')
--
CREATE TABLE DonViTinh (
	MaDVT INT NOT NULL PRIMARY KEY IDENTITY,
	LoaiDVT NVARCHAR(20) NOT NULL
)

INSERT INTO DonViTinh (LoaiDVT) VALUES (N'Bao')
INSERT INTO DonViTinh (LoaiDVT) VALUES (N'Cái')
INSERT INTO DonViTinh (LoaiDVT) VALUES (N'Chai')
INSERT INTO DonViTinh (LoaiDVT) VALUES (N'Sản phẩm')
INSERT INTO DonViTinh (LoaiDVT) VALUES (N'Thùng')
INSERT INTO DonViTinh (LoaiDVT) VALUES (N'Túi')

SELECT * FROM DonViTinh
DELETE FROM DonViTinh WHERE MaDVT = 2
DBCC CHECKIDENT ('DonViTinh', RESEED, 6);
--

CREATE TABLE NhomHang (
	MaNH INT NOT NULL PRIMARY KEY,
	TenNH NVARCHAR(50) NOT NULL
)

INSERT INTO NhomHang (MaNH, TenNH) VALUES (1, N'Phân bón gốc')
INSERT INTO NhomHang (MaNH, TenNH) VALUES (2, N'Phân bón lá')
INSERT INTO NhomHang (MaNH, TenNH) VALUES (3, N'Thuốc trừ sâu')
INSERT INTO NhomHang (MaNH, TenNH) VALUES (4, N'Thuốc diệt cỏ')
INSERT INTO NhomHang (MaNH, TenNH) VALUES (5, N'Nông cụ')

-------------------------------------------------------------------
-- Bảng NhanVien
CREATE TABLE NhanVien (
    MaNV INT PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    NgaySinh DATE,
    GioiTinh INT REFERENCES GioiTinh(MaGT),
    SoDienThoai NVARCHAR(20) NOT NULL,
    DiaChi NVARCHAR(255),
    Email NVARCHAR(100),
	VaiTro INT NOT NULL REFERENCES VaiTro(MaVT)
)


-- Bảng NguoiDung
CREATE TABLE NguoiDung (
    TenDangNhap NVARCHAR(50) PRIMARY KEY,
    MatKhau NVARCHAR(100) NOT NULL,
    MaNV INT UNIQUE FOREIGN KEY REFERENCES NhanVien(MaNV)
)

-- Bảng NhaCungCap
CREATE TABLE NhaCungCap (
    MaNCC INT PRIMARY KEY,
    TenNCC NVARCHAR(100) NOT NULL,
    DienThoai NVARCHAR(20),
    DiaChi NVARCHAR(255)
)

-- Bảng SanPham
CREATE TABLE SanPham (
    MaSP INT PRIMARY KEY,
    TenSP NVARCHAR(100) NOT NULL,
    DonViTinh INT NOT NULL REFERENCES DonViTinh(MaDVT),
    GiaNhap DECIMAL(18,2) NOT NULL,
    GiaBan DECIMAL(18,2) NOT NULL,
    SoLuongTon INT NOT NULL,
    NhomHang INT NOT NULL REFERENCES NhomHang(MaNH)
)

-- Bảng KhachHang
CREATE TABLE KhachHang (
    MaKH INT PRIMARY KEY,
    TenKH NVARCHAR(100) NOT NULL,
	GioiTinh INT REFERENCES GioiTinh(MaGT),
    SoDienThoai NVARCHAR(20) NOT NULL,
    DiaChi NVARCHAR(255),
    Email NVARCHAR(100)
)

-- Bảng PhieuNhap
CREATE TABLE PhieuNhap (
    MaPN INT PRIMARY KEY,
    NgayNhap DATE NOT NULL,
    MaNCC INT FOREIGN KEY REFERENCES NhaCungCap(MaNCC),
	NguoiTao NVARCHAR(50) FOREIGN KEY REFERENCES NguoiDung(TenDangNhap),
	TongTien DECIMAL(18,0) NOT NULL
)

-- Bảng ChiTietNhap
CREATE TABLE ChiTietNhap (
    MaPN INT FOREIGN KEY REFERENCES PhieuNhap(MaPN),
    MaSP INT FOREIGN KEY REFERENCES SanPham(MaSP),
    SoLuong INT NOT NULL,
    DonGia DECIMAL(18,0) NOT NULL,
    PRIMARY KEY (MaPN, MaSP)
)

-- Bảng HoaDonBan
CREATE TABLE HoaDonBan (
    MaHD INT PRIMARY KEY,
    NgayBan DATE NOT NULL,
    MaKH INT FOREIGN KEY REFERENCES KhachHang(MaKH),
    NguoiTao NVARCHAR(50) FOREIGN KEY REFERENCES NguoiDung(TenDangNhap),
    TongTien DECIMAL(18,0) NOT NULL
)

-- Bảng ChiTietBan
CREATE TABLE ChiTietBan (
    MaHD INT FOREIGN KEY REFERENCES HoaDonBan(MaHD),
    MaSP INT FOREIGN KEY REFERENCES SanPham(MaSP),
    SoLuong INT NOT NULL,
    DonGia DECIMAL(18,0) NOT NULL,
    PRIMARY KEY (MaHD, MaSP)
)

---------------------------------------------------------------------------------------
--INSERT NhanVien
INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai, DiaChi, Email, VaiTro) VALUES (2501, N'Nguyễn Thị Mai', '1990-05-12', 2, '0901234567', N'12/5 Nguyễn Văn Cừ, Phường 1, Quận 5, TP. HCM', N'nguyenmaithi@example.com', 1)
INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai, DiaChi, Email, VaiTro) VALUES (2502, N'Trần Quang Hiếu', '1985-09-21', 1, '0912345678', N'24/3 Lê Thị Riêng, Phường 3, Quận 1, TP. HCM', N'tranquanghieu@example.com', 4)
INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai, DiaChi, Email, VaiTro) VALUES (2503, N'Lê Minh Tuấn', '1992-01-15', 1, '0923456789', N'87/1 Ngô Quyền, Phường 5, Quận 10, TP. HCM', N'leminhtuan@example.com', 4)
INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai, DiaChi, Email, VaiTro) VALUES (2504, N'Phạm Thị Lan', '1988-11-30', 2, '0934567890', N'45/6 Hồ Hảo Hớn, Phường 2, Quận 11, TP. HCM', N'phamthilan@example.com', 2)
INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai, DiaChi, Email, VaiTro) VALUES (2505, N'Nguyễn Hoàng Nam', '1991-07-05', 1, '0945678901', N'19/8 Trường Sa, Phường 4, Quận 3, TP. HCM', N'nguyenhoangnam@example.com', 2)
INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai, DiaChi, Email, VaiTro) VALUES (2506, N'Võ Thị Thanh', '1993-03-18', 2, '0956789012', N'56/9 Đường 3/2, Phường 8, Quận 10, TP. HCM', N'vothithanh@example.com', 2)
INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai, DiaChi, Email, VaiTro) VALUES (2507, N'Nguyễn Duy Hoàng', '1987-02-22', 1, '0967890123', N'102/11 Phan Xích Long, Phường 5, Quận Phú Nhuận, TP. HCM', N'nguyenduyhoang@example.com', 4)
INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai, DiaChi, Email, VaiTro) VALUES (2508, N'Trịnh Thị Mai', '1994-06-13', 2, '0978901234', N'221/17 Bà Hom, Phường 13, Quận 6, TP. HCM', N'trinhthimai@example.com', 2)
INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai, DiaChi, Email, VaiTro) VALUES (2509, N'Hoàng Minh Duy', '1990-04-27', 1, '0989012345', N'77/23 Tân Kỳ Tân Quý, Phường 15, Quận Tân Phú, TP. HCM', N'hoangminhduy@example.com', 4)
INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai, DiaChi, Email, VaiTro) VALUES (2510, N'Vũ Thị Mai Linh', '1989-12-03', 2, '0990123456', N'68/2 Kinh Dương Vương, Phường 10, Quận Bình Tân, TP. HCM', N'vuthimailinh@example.com', 3)

SELECT NhanVien.MaNV, NhanVien.HoTen, NhanVien.NgaySinh, GioiTinh.LoaiGT, NhanVien.SoDienThoai, NhanVien.DiaChi, NhanVien.Email, VaiTro.TenVT
FROM NhanVien
JOIN GioiTinh ON NhanVien.GioiTinh = GioiTinh.MaGT
JOIN VaiTro ON NhanVien.VaiTro = VaiTro.MaVT

--INSERT NguoiDung
INSERT INTO NguoiDung (TenDangNhap, MatKhau, MaNV) VALUES (N'nguyenthimai', N'password123', 2501)
INSERT INTO NguoiDung (TenDangNhap, MatKhau, MaNV) VALUES (N'tranquanghieu', N'password123', 2502)
INSERT INTO NguoiDung (TenDangNhap, MatKhau, MaNV) VALUES (N'leminhtuan', N'password123', 2503)
INSERT INTO NguoiDung (TenDangNhap, MatKhau, MaNV) VALUES (N'phamthilan', N'password123', 2504)
INSERT INTO NguoiDung (TenDangNhap, MatKhau, MaNV) VALUES (N'nguyenhoangnam', N'password123', 2505)
INSERT INTO NguoiDung (TenDangNhap, MatKhau, MaNV) VALUES (N'vothithanh', N'password123', 2506)
INSERT INTO NguoiDung (TenDangNhap, MatKhau, MaNV) VALUES (N'nguyenduyhoang', N'password123', 2507)
INSERT INTO NguoiDung (TenDangNhap, MatKhau, MaNV) VALUES (N'trinhthimai', N'password123', 2508)
INSERT INTO NguoiDung (TenDangNhap, MatKhau, MaNV) VALUES (N'hoangminhduy', N'password123', 2509)
INSERT INTO NguoiDung (TenDangNhap, MatKhau, MaNV) VALUES (N'vuthimailinh', N'password123', 2510)

SELECT * FROM NguoiDung

--INSERT NhaCungCap
INSERT INTO NhaCungCap (MaNCC, TenNCC, DienThoai, DiaChi) VALUES (1001, N'Công ty TNHH Nông Dược Xanh', '0901123456', N'123 Quốc lộ 1A, Quận 12, TP. HCM')
INSERT INTO NhaCungCap (MaNCC, TenNCC, DienThoai, DiaChi) VALUES (1002, N'Công ty CP Phân Bón Việt Mỹ', '0912345678', N'45 Trường Chinh, Quận Tân Bình, TP. HCM')
INSERT INTO NhaCungCap (MaNCC, TenNCC, DienThoai, DiaChi) VALUES (1003, N'DNTN Hóa Chất Nông Nghiệp Thanh Bình', '0923456789', N'78 Lê Văn Lương, Quận 7, TP. HCM')
INSERT INTO NhaCungCap (MaNCC, TenNCC, DienThoai, DiaChi) VALUES (1004, N'Công ty TNHH Nông Nghiệp Sạch Miền Tây', '0934567890', N'66 Nguyễn Văn Linh, Quận Ninh Kiều, Cần Thơ')
INSERT INTO NhaCungCap (MaNCC, TenNCC, DienThoai, DiaChi) VALUES (1005, N'Công ty TNHH TM Nông Dược Miền Nam', '0945678901', N'10 Trần Hưng Đạo, TP. Mỹ Tho, Tiền Giang')

SELECT * FROM NhaCungCap

--INSERT SanPham
-- Nhóm 1: Phân bón gốc
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2001, N'Đạm Phú Mỹ', 1, 250000, 280000, 50, 1)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2002, N'Lân Văn Điển', 1, 220000, 250000, 60, 1)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2003, N'NPK Bình Điền 16-16-8', 1, 270000, 300000, 70, 1)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2004, N'Phân Kali Canada', 1, 290000, 320000, 40, 1)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2005, N'Phân Hữu Cơ Vi Sinh', 1, 180000, 210000, 80, 1)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2006, N'NPK Đầu Trâu 20-20-15', 1, 260000, 290000, 90, 1)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2007, N'Phân Super Lân', 1, 210000, 240000, 55, 1)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2008, N'Đạm Cà Mau', 1, 255000, 285000, 65, 1)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2009, N'NPK Phú Mỹ 16-16-8', 1, 265000, 295000, 75, 1)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2010, N'Phân Hữu Cơ Đầu Trâu', 1, 195000, 225000, 85, 1)

-- Nhóm 2: Phân bón lá
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2011, N'Phân bón lá HVP 401', 3, 35000, 45000, 100, 2)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2012, N'Phân bón lá Đầu Trâu 501', 3, 40000, 50000, 110, 2)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2013, N'Phân bón lá B1 USA', 3, 30000, 38000, 120, 2)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2014, N'Phân bón lá Humik USA', 3, 32000, 42000, 90, 2)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2015, N'Phân bón lá Organic', 3, 28000, 35000, 95, 2)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2016, N'Phân bón lá BioGreen', 3, 37000, 46000, 85, 2)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2017, N'Phân bón lá Seaweed', 3, 39000, 48000, 115, 2)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2018, N'Phân bón lá Amino Plus', 3, 33000, 42000, 105, 2)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2019, N'Phân bón lá Yara Vita', 3, 45000, 55000, 75, 2)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2020, N'Phân bón lá Grow More', 3, 37000, 47000, 95, 2)

-- Nhóm 3: Thuốc trừ sâu
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2021, N'Thuốc trừ sâu Confidor', 3, 60000, 75000, 60, 3)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2022, N'Thuốc trừ sâu Regent', 3, 70000, 85000, 55, 3)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2023, N'Thuốc trừ sâu Sherpa', 3, 68000, 82000, 50, 3)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2024, N'Thuốc trừ sâu Tungin', 3, 64000, 79000, 65, 3)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2025, N'Thuốc trừ sâu Karate', 3, 71000, 87000, 70, 3)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2026, N'Thuốc trừ sâu Vibasudo', 3, 69000, 84000, 45, 3)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2027, N'Thuốc trừ sâu Comda', 3, 65000, 80000, 60, 3)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2028, N'Thuốc trừ sâu Chess', 3, 72000, 88000, 55, 3)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2029, N'Thuốc trừ sâu Marshal', 3, 63000, 78000, 75, 3)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2030, N'Thuốc trừ sâu Actara', 3, 66000, 81000, 85, 3)

-- Nhóm 4: Thuốc diệt cỏ
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2031, N'Thuốc cỏ Glyphosate', 3, 85000, 100000, 40, 4)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2032, N'Thuốc cỏ Ronstar', 3, 90000, 105000, 35, 4)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2033, N'Thuốc cỏ Dual Gold', 3, 87000, 103000, 30, 4)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2034, N'Thuốc cỏ Sencor', 3, 88000, 104000, 45, 4)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2035, N'Thuốc cỏ Butachlor', 3, 86000, 101000, 50, 4)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2036, N'Thuốc cỏ Basagran', 3, 92000, 107000, 55, 4)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2037, N'Thuốc cỏ Metribuzin', 3, 89000, 106000, 38, 4)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2038, N'Thuốc cỏ Select', 3, 93000, 109000, 28, 4)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2039, N'Thuốc cỏ Targa', 3, 87000, 102000, 42, 4)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2040, N'Thuốc cỏ Ancom', 3, 91000, 108000, 36, 4)

-- Nhóm 5: Nông cụ
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2041, N'Bình xịt điện', 2, 500000, 600000, 20, 5)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2042, N'Cuốc sắt cán gỗ', 2, 120000, 150000, 30, 5)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2043, N'Cào răng sắt', 2, 80000, 100000, 25, 5)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2044, N'Dao phát cỏ', 2, 60000, 75000, 35, 5)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2045, N'Bình tưới 5 lít', 2, 70000, 90000, 40, 5)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2046, N'Kéo cắt cành', 2, 50000, 65000, 28, 5)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2047, N'Máy cắt cỏ mini', 2, 1200000, 1350000, 15, 5)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2048, N'Bình xịt áp lực 8L', 2, 150000, 180000, 22, 5)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2049, N'Dụng cụ gieo hạt', 2, 95000, 115000, 18, 5)
INSERT INTO SanPham (MaSP, TenSP, DonViTinh, GiaNhap, GiaBan, SoLuongTon, NhomHang) VALUES (2050, N'Bình phun thuốc 16L', 2, 300000, 350000, 26, 5)


SELECT SanPham.MaSP, SanPham.TenSP, DonViTinh.LoaiDVT, SanPham.GiaNhap, SanPham.GiaBan, SanPham.SoLuongTon, NhomHang.TenNH
FROM SanPham
JOIN DonViTinh ON SanPham.DonViTinh = DonViTinh.MaDVT
JOIN NhomHang ON SanPham.NhomHang = NhomHang.MaNH

--INSERT KhachHang
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25001, N'Nguyễn Văn An', 1, '0902345678', N'123 Lê Lai, Quận 1, TP. HCM', 'nguyenvanan@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25002, N'Lê Thị Bích', 2, '0913456789', N'45 Trần Hưng Đạo, Quận 5, TP. HCM', 'lethibich@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25003, N'Phạm Văn Cường', 1, '0924567890', N'78 Nguyễn Trãi, Quận 3, TP. HCM', 'phamvancuong@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25004, N'Trần Thị Dung', 2, '0935678901', N'56/15 Hoàng Diệu, Quận 4, TP. HCM', 'tranthidung@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25005, N'Hồ Minh Đức', 1, '0946789012', N'89 Phan Đình Phùng, Quận Phú Nhuận, TP. HCM', 'hominhduc@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25006, N'Ngô Thị Em', 2, '0957890123', N'12/45 Âu Cơ, Quận Tân Bình, TP. HCM', 'ngothiem@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25007, N'Đinh Văn Hải', 1, '0968901234', N'34 Nguyễn Hữu Cảnh, Bình Thạnh, TP. HCM', 'dinhvanhai@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25008, N'Vũ Thị Hoa', 2, '0979012345', N'221 Lê Văn Sỹ, Quận 3, TP. HCM', 'vuthihoa@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25009, N'Lý Minh Khánh', 1, '0980123456', N'56 Nguyễn Thị Minh Khai, Quận 1, TP. HCM', 'lyminhkanh@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25010, N'Bùi Thị Lan', 2, '0991234567', N'77/37 Cách Mạng Tháng 8, Quận 10, TP. HCM', 'buithilan@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25011, N'Phan Văn Long', 1, '0903456789', N'98/12 Lý Thường Kiệt, Quận 11, TP. HCM', 'phanvanlong@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25012, N'Trương Thị My', 2, '0914567890', N'145 Nguyễn Văn Cừ, Quận 5, TP. HCM', 'truongthimy@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25013, N'Đoàn Minh Nghĩa', 1, '0925678901', N'12 Đặng Văn Ngữ, Phú Nhuận, TP. HCM', 'doanminhnghia@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25014, N'Võ Thị Oanh', 2, '0936789012', N'56 Nguyễn Văn Trỗi, Phú Nhuận, TP. HCM', 'vothioanh@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25015, N'Nguyễn Đức Phương', 1, '0947890123', N'34 Hoàng Văn Thụ, Tân Bình, TP. HCM', 'nguyenducphuong@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25016, N'Lê Thị Quỳnh', 2, '0958901234', N'89/9 Trần Quốc Thảo, Phú Nhuận, TP. HCM', 'lethiquynh@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25017, N'Phạm Văn Sơn', 1, '0969012345', N'102/53 Phan Xích Long, Phú Nhuận, TP. HCM', 'phamvanson@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25018, N'Trần Thị Trang', 2, '0970123456', N'221 Bà Hom, Quận 6, TP. HCM', 'tranthitrang@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25019, N'Hoàng Minh Tuấn', 1, '0981234567', N'77 Tân Kỳ Tân Quý, Tân Phú, TP. HCM', 'hoangminhtuan@example.com')
INSERT INTO KhachHang (MaKH, TenKH, GioiTinh, SoDienThoai, DiaChi, Email) VALUES (25020, N'Vũ Thị Vân', 2, '0992345678', N'68/6 Kinh Dương Vương, Bình Tân, TP. HCM', 'vuthivan@example.com')

SELECT KhachHang.MaKH, KhachHang.TenKH, GioiTinh.LoaiGT, KhachHang.SoDienThoai, KhachHang.DiaChi, KhachHang.Email
FROM KhachHang
JOIN GioiTinh ON KhachHang.GioiTinh = GioiTinh.MaGT

--INSERT PhieuNhap
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250001, 1001, '2025-04-01', N'nguyenduyhoang', 8300000)
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250002, 1002, '2025-04-02', N'hoangminhduy', 2208000)
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250003, 1003, '2025-04-03', N'hoangminhduy', 2322000)
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250004, 1004, '2025-04-04', N'tranquanghieu', 2780000)
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250005, 1005, '2025-04-05', N'leminhtuan', 3800000)
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250006, 1001, '2025-04-06', N'leminhtuan', 8330000)
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250007, 1002, '2025-04-07', N'nguyenduyhoang', 1839000)
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250008, 1003, '2025-04-08', N'leminhtuan', 2531000)
INSERT INTO PhieuNhap (MaPN, MaNCC, NgayNhap, NguoiTao, TongTien) VALUES (250009, 1004, '2025-04-09', N'tranquanghieu', 2703000)


--INSERT ChiTietNhap
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250001, 2001, 10, 250000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250001, 2002, 8, 220000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250001, 2003, 6, 270000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250001, 2004, 4, 290000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250001, 2005, 7, 180000)

INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250002, 2011, 20, 35000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250002, 2012, 15, 40000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250002, 2013, 10, 30000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250002, 2014, 12, 32000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250002, 2015, 8, 28000)

INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250003, 2021, 9, 60000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250003, 2022, 7, 70000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250003, 2023, 5, 68000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250003, 2024, 6, 64000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250003, 2025, 8, 71000)

INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250004, 2031, 10, 85000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250004, 2032, 6, 90000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250004, 2033, 4, 87000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250004, 2034, 5, 88000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250004, 2035, 7, 86000)

INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250005, 2041, 3, 500000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250005, 2042, 10, 120000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250005, 2043, 5, 80000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250005, 2044, 7, 60000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250005, 2045, 4, 70000)

INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250006, 2006, 9, 260000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250006, 2007, 6, 210000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250006, 2008, 8, 255000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250006, 2009, 5, 265000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250006, 2010, 7, 195000)

INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250007, 2016, 11, 37000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250007, 2017, 9, 39000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250007, 2018, 12, 33000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250007, 2019, 7, 45000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250007, 2020, 10, 37000)

INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250008, 2026, 8, 69000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250008, 2027, 10, 65000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250008, 2028, 6, 72000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250008, 2029, 9, 63000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250008, 2030, 5, 66000)

INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250009, 2036, 5, 92000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250009, 2037, 8, 89000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250009, 2038, 6, 93000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250009, 2039, 7, 87000)
INSERT INTO ChiTietNhap (MaPN, MaSP, SoLuong, DonGia) VALUES (250009, 2040, 4, 91000)

--INSERT HoaDonBan
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (26001, '2025-04-10', 25001, N'phamthilan', 1404000)
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (26002, '2025-04-11', 25002, N'vothithanh', 2235000)
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (26003, '2025-04-12', 25003, N'trinhthimai', 4496000)
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (26004, '2025-04-13', 25004, N'phamthilan', 2001000)
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (26005, '2025-04-14', 25005, N'nguyenhoangnam', 3038000)
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (26006, '2025-04-15', 25006, N'vothithanh', 2995000)
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (26007, '2025-04-16', 25007, N'phamthilan', 2593000)
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (26008, '2025-04-17', 25008, N'nguyenhoangnam', 2333000)
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (26009, '2025-04-18', 25009, N'nguyenhoangnam', 2010000)
INSERT INTO HoaDonBan (MaHD, NgayBan, MaKH, NguoiTao, TongTien) VALUES (26010, '2025-04-19', 25010, N'phamthilan', 5031000)

SELECT * FROM HoaDonBan

--INSERT ChiTietBan
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26001, 2001, 2, 280000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26001, 2012, 3, 50000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26001, 2023, 1, 82000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26001, 2033, 4, 103000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26001, 2043, 2, 100000)

INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26002, 2003, 5, 300000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26002, 2014, 4, 42000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26002, 2025, 2, 87000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26002, 2035, 3, 101000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26002, 2045, 1, 90000)

INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26003, 2005, 10, 200000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26003, 2016, 6, 46000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26003, 2027, 3, 80000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26003, 2037, 5, 106000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26003, 2047, 1, 1350000)

INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26004, 2008, 3, 285000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26004, 2017, 5, 48000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26004, 2022, 2, 85000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26004, 2038, 4, 109000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26004, 2042, 2, 150000)

INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26005, 2010, 7, 225000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26005, 2019, 8, 55000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26005, 2030, 4, 81000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26005, 2040, 3, 108000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26005, 2044, 5, 75000)

INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26006, 2002, 6, 250000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26006, 2013, 9, 38000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26006, 2024, 2, 79000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26006, 2032, 7, 105000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26006, 2046, 4, 65000)

INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26007, 2004, 5, 320000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26007, 2020, 3, 47000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26007, 2029, 6, 78000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26007, 2039, 2, 102000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26007, 2048, 1, 180000)

INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26008, 2007, 4, 240000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26008, 2018, 7, 42000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26008, 2021, 3, 75000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26008, 2034, 6, 104000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26008, 2049, 2, 115000)

INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26009, 2009, 2, 295000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26009, 2015, 10, 35000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26009, 2026, 5, 84000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26009, 2031, 3, 100000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26009, 2050, 1, 350000)

INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26010, 2006, 8, 290000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26010, 2012, 4, 50000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26010, 2028, 2, 88000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26010, 2036, 5, 107000)
INSERT INTO ChiTietBan (MaHD, MaSP, SoLuong, DonGia) VALUES (26010, 2041, 3, 600000)

-----------------------------------------------------------------------------------------------------