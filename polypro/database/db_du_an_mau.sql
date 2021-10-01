CREATE DATABASE Polypro
GO
USE Polypro
GO

CREATE TABLE NhanVien(
MaNV nvarchar(50) NOT NULL,
MatKhau nvarchar(50) NOT NULL,
HoTen nvarchar(50) NOT NULL,
VaiTro bit NOT NULL DEFAULT 0,
PRIMARY KEY(MaNV)
)
GO

CREATE TABLE ChuyenDe(
MaCD nchar(5) NOT NULL,
TenCD nvarchar(50) NOT NULL,
HocPhi float NOT NULL DEFAULT 0,
ThoiLuong int NOT NULL DEFAULT 30,
Hinh nvarchar(50) NOT NULL DEFAULT 'chuyen-de.png',
MoTa nvarchar(255) NOT NULL,
PRIMARY KEY(MaCD),
UNIQUE(TenCD),
CHECK(HocPhi >= 0 AND ThoiLuong > 0)
)
GO

CREATE TABLE NguoiHoc(
MaNH nchar(7) NOT NULL,
HoTen nvarchar(50) NOT NULL,
NgaySinh date NOT NULL,
GioiTinh bit NOT NULL DEFAULT 0,
DienThoai nvarchar(50) NOT NULL,
Email nvarchar(50) NOT NULL,
GhiChu nvarchar(max) NULL,
MaNV nvarchar(50) NOT NULL,
NgayDK date NOT NULL DEFAULT getdate(),
PRIMARY KEY(MaNH)
)
GO

CREATE TABLE KhoaHoc(
MaKH int IDENTITY(1,1) NOT NULL,
MaCD nchar(5) NOT NULL,
HocPhi float NOT NULL DEFAULT 0,
ThoiLuong int NOT NULL DEFAULT 0,
NgayKG date NOT NULL,
GhiChu nvarchar(50) NULL,
MaNV nvarchar(50) NOT NULL,
NgayTao date NOT NULL DEFAULT getdate(),
PRIMARY KEY(MaKH),
CHECK(HocPhi >= 0 AND ThoiLuong > 0),
FOREIGN KEY (MaCD) REFERENCES ChuyenDe(MaCD) ON UPDATE CASCADE,
FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV) ON UPDATE CASCADE
)
GO

CREATE TABLE HocVien(
MaHV int IDENTITY(1,1) NOT NULL,
MaKH int NOT NULL,
MaNH nchar(7) NOT NULL,
Diem float NOT NULL,
PRIMARY KEY(MaHV),
UNIQUE(MaKH, MaNH),
FOREIGN KEY (MaKH) REFERENCES KhoaHoc(MaKH) ON DELETE CASCADE,
FOREIGN KEY (MaNH) REFERENCES NguoiHoc(MaNH) ON UPDATE CASCADE
)

------ nhan vien -------------
--Thêm mới
	INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro) VALUES (?, ?, ?, ?)
--Cập nhật theo mã 
	UPDATE NhanVien SET MatKhau=?, HoTen=?, VaiTro=? WHERE MaNV=?
--Xóa theo mã 
	DELETE FROM NhanVien WHERE MaNV=?
--truy vấn tất cả 
	SELECT * FROM NhanVien
--Truy vấn theo mã 
	SELECT * FROM NhanVien WHERE MaNV=?

------ chuyen de -------------
--Thêm mới
	INSERT INTO ChuyenDe (MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa) VALUES (?, ?,?, ?, ?, ?)
--Cập nhật theo mã 
	UPDATE ChuyenDe SET TenCD=?, HocPhi=?, ThoiLuong=?, Hinh=?, MoTa=? WHERE MaCD=?
--Xóa theo mã 
	DELETE FROM ChuyenDe WHERE MaCD=?
--Truy vấn tất cả 
	SELECT * FROM ChuyenDe
--Truy vấn theo mã 
	SELECT * FROM ChuyenDe WHERE MaCD=?	

------ Nguoi hoc -------------
--Thêm mới
	INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, NgayDK) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
--Cập nhật theo mã 
	UPDATE NguoiHoc SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?, MaNV=?, NgayDK=? WHERE MaNH=?
--Xóa theo mã 
	DELETE FROM NguoiHoc WHERE MaNH=?
--Truy vấn tất cả 
	SELECT * FROM NguoiHoc
--Truy vấn theo mã 
	SELECT * FROM NguoiHoc WHERE MaNH=?

------ khoa hoc -------------
--Thêm mới
	INSERT INTO KhoaHoc (MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao) VALUES (?, ?, ?, ?, ?, ?, ?)
--Cập nhật theo mã 
	UPDATE KhoaHoc SET MaCD=?, HocPhi=?, ThoiLuong=?, NgayKG=?, GhiChu=?, MaNV=?, NgayTao=? WHERE MaKH=?
--Xóa theo mã 
	DELETE FROM KhoaHoc WHERE MaKH=?
--Truy vấn tất cả 
	SELECT * FROM KhoaHoc
--Truy vấn theo mã 
	SELECT * FROM KhoaHoc WHERE MaKH=?

------ hoc vien -------------
--Thêm mới
	INSERT INTO HocVien (MaKH, MaNH, Diem) VALUES (?, ?, ?)
--Cập nhật theo mã 
	UPDATE HocVien SET MaKH=?, MaNH=?, Diem=? WHERE MaHV=?
--Xóa theo mã 
	DELETE FROM HocVien WHERE MaHV=?
--Truy vấn tất cả 
	SELECT * FROM HocVien
--Truy vấn theo mã 
	SELECT * FROM HocVien WHERE MaHV=?

---------------Số người học từng năm-----------
	CREATE PROC sp_ThongKeNguoiHoc
	AS BEGIN
	SELECT
	YEAR(NgayDK) Nam,
	COUNT(*) SoLuong,
	MIN(NgayDK) DauTien,
	MAX(NgayDK) CuoiCung
	FROM NguoiHoc
	GROUP BY YEAR(NgayDK)
	END


---------------Doanh thu theo chuyên đề -----------
	CREATE PROC sp_ThongKeDoanhThu(@Year INT)
	AS BEGIN
	SELECT
	TenCD ChuyenDe,
	COUNT(DISTINCT kh.MaKH) SoKH,
	COUNT(hv.MaHV) SoHV,
	SUM(kh.HocPhi) DoanhThu,
	MIN(kh.HocPhi) ThapNhat,
	MAX(kh.HocPhi) CaoNhat,
	AVG(kh.HocPhi) TrungBinh
	FROM KhoaHoc kh
	JOIN HocVien hv ON kh.MaKH=hv.MaKH
	JOIN ChuyenDe cd ON cd.MaCD=kh.MaCD
	WHERE YEAR(NgayKG) = @Year
	GROUP BY TenCD
	END


---------------Học viên theo chuyên đề -----------
	CREATE PROC sp_ThongKeDiem
	AS BEGIN
	SELECT
	TenCD ChuyenDe,
	COUNT(MaHV) SoHV,
	MIN(Diem) ThapNhat,
	MAX(Diem) CaoNhat,
	AVG(Diem) TrungBinh
	FROM KhoaHoc kh
	JOIN HocVien hv ON kh.MaKH=hv.MaKH
	JOIN ChuyenDe cd ON cd.MaCD=kh.MaCD
	GROUP BY TenCD
	END

--------------Học viên theo chuyên đề -----------
	CREATE PROC sp_BangDiem(@MaKH INT)
	AS BEGIN
	SELECT
	nh.MaNH,
	nh.HoTen,
	hv.Diem
	FROM HocVien hv
	JOIN NguoiHoc nh ON nh.MaNH=hv.MaNH
	WHERE hv.MaKH = @MaKH
	ORDER BY hv.Diem DESC
	END