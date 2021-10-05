create database EduSys
go
use EduSys
go

create table NhanVien
(
	MaNV NVARCHAR(50) NOT NULL PRIMARY KEY,
	MatKhau NVARCHAR(50) NOT NULL,
	HoTen NVARCHAR(50) NOT NULL,
	VaiTro bit not null,
)
go

create table ChuyenDe
(
	MaCD NCHAR(5) NOT NULL PRIMARY KEY,
	TenCD NVARCHAR(50) NOT NULL,
	HocPhi FLOAT NOT NULL,
	ThoiLuong INT not null,
	Hinh NVARCHAR(50) NOT NULL,
	MoTa NVARCHAR(50) NOT NULL,
)
go

create table KhoaHoc
(
	MaKH int identity(1,1) NOT NULL PRIMARY KEY,
	MaCD NCHAR(5) NOT NULL,
	HocPhi FLOAT NOT NULL,
	ThoiLuong INT not null,
	NgayKG date not null,
	GhiChu NVARCHAR(50) NOT NULL,
	MaNV NVARCHAR(50) NOT NULL,
	NgayTao date not null,
	foreign key (MaCD) references ChuyenDe(MaCD) on delete no action on update cascade,
	foreign key (MaNV) references NhanVien(MaNV) on delete no action on update cascade
)
go

create table NguoiHoc
(
	MaNH NCHAR(7) NOT NULL PRIMARY KEY,
	HoTen NVARCHAR(50) NOT NULL,
	NgaySinh date not null,
	GioiTinh bit NOT NULL,
	DienThoai NVARCHAR(15) NOT NULL,
	Email NVARCHAR(50) NOT NULL,
	GhiChu NVARCHAR(max) NULL,
	MaNV NVARCHAR(50) NOT NULL,
	NgayDK date not null,
	foreign key (MaNV) references NhanVien(MaNV) on delete no action on update cascade
)
go

create table HocVien
(
	MaHV int identity(1,1) NOT NULL PRIMARY KEY,
	MaKH int not null,
	MaNH NCHAR(7) NOT NULL,
	Diem float not null,
	foreign key (MaNH) references NguoiHoc(MaNH) on delete no action on update cascade,
	foreign key (MaKH) references KhoaHoc(MaKH) on delete cascade on update cascade
)
go

select * from HocVien

INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro) VALUES ('NV01','123' , N'Nguyễn Văn A', 0)
select * from NhanVien

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