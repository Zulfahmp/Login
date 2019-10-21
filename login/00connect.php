<?php 

$conn = mysqli_connect("localhost","id8443019_user","zulfa123","id8443019_login");

if(!$conn){
    echo "Tidak Konek";
}else{
    echo "Koneksi Berhasil";
}
?>