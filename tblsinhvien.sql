-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 09, 2021 lúc 07:54 AM
-- Phiên bản máy phục vụ: 10.4.19-MariaDB
-- Phiên bản PHP: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `69dcht22`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tblsinhvien`
--

CREATE TABLE `tblsinhvien` (
  `id` int(11) NOT NULL,
  `hoten` varchar(50) NOT NULL,
  `ngaysinh` varchar(50) NOT NULL,
  `diachi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tblsinhvien`
--

INSERT INTO `tblsinhvien` (`id`, `hoten`, `ngaysinh`, `diachi`) VALUES
(1, 'Nguyễn Hữu Thắng', '10/05/2000', 'Hà Đông'),
(2, 'Nguyễn Đức Anh', '11/10/2000', 'Thái Bình'),
(3, 'Ngô Tiến Nam', '20/01/2000', 'Thanh Hóa'),
(4, 'Đỗ Minh Hiếu', '03/09/2000', 'Phú Thọ'),
(5, 'Kiều Bá Việt', '27/04/2000', 'Hà Tây'),
(34, 'Tuan', '2000', 'picture/photo.jpg'),
(37, 'Toan', '2000', 'Duong noi'),
(38, 'Anh', '29/8/1999', 'Hai duong'),
(39, 'Thắng', '2000', 'Dương Nội'),
(40, 'Hang', '10/05/200', ''),
(43, '', '', '');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `tblsinhvien`
--
ALTER TABLE `tblsinhvien`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `tblsinhvien`
--
ALTER TABLE `tblsinhvien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
