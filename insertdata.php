<?php 
require 'connectDB.php';

$hoTen = $_POST['hoTen'];
$namSinh = $_POST['namSinh'];
$diaChi = $_POST['diaChi'];

$sql = "INSERT INTO tblsinhvien VALUES (null,'$hoTen','$namSinh','$diaChi')";

if($conn->query($sql) == TRUE) {
	echo "Success";

}else{
	echo"Error";
}

$conn->close();
 ?>