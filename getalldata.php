<?php

class SinhVien 
{
  
 function SinhVien($id,$hoten,$ngaysinh,$diachi)
 {
   $this->iD = $id;
   $this->hoTen = $hoten;
   $this->ngaySinh = $ngaysinh;
   $this->diaChi = $diachi;
 }
}
//include 'connectDB.php'
require 'connectDB.php';

 $sql = "SELECT * FROM tblsinhvien";
 $result = mysqli_query($conn, $sql);
 $arrSV=[];
if (mysqli_num_rows($result) > 0) {
   // output data of each row
  while($row = mysqli_fetch_assoc($result)) {


     $sv = new SinhVien($row["id"],$row["hoten"],$row["ngaysinh"],$row["diachi"]);
     array_push($arrSV, $sv);
   }
     echo json_encode($arrSV);
 } else {
   echo "0 results";
 }


mysqli_close($conn);
?>