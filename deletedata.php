<?php

require 'connectDB.php';
$id =$_POST['iD'];

// sql to delete a record
$sql = "DELETE FROM tblsinhvien WHERE id=$id";

if ($conn->query($sql) === TRUE) {

  echo "Success";

} else {

  echo "Error";

}

$conn->close();
?>