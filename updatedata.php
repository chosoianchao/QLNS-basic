<?php  
require 'connectDB.php';
$id =$_POST['iD'];
$hoten = $_POST['hoTen'];
$namsinh =$_POST['namSinh'];
$diachi = $_POST['diaChi'];

$sql = "UPDATE tblsinhvien SET hoten = '$hoten', ngaysinh = '$namsinh', diachi = '$diachi' WHERE id=$id";

if ($conn->query($sql) === TRUE) {
  echo "Success";
} else {
  echo "Error" ;
}

$conn->close();
?>