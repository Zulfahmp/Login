<?php 

header("Content-Type: application/json; charset=UTF-8");

require_once 'connect.php';

$query = "SELECT * FROM users_table ORDER BY id DESC ";
$result = mysqli_query($conn, $query);
$response = array();

$server_name = $_SERVER['SERVER_ADDR'];

while( $row = mysqli_fetch_assoc($result) ){

    array_push($response, 
    array(
        'id'        =>$row['id'], 
        'nama'      =>$row['nama'], 
        'alamat'   =>$row['alamat'],
        'jenis_kelamin'    =>$row['jenis_kelamin'],
        'tanggal_lahir'     =>date('d M Y', strtotime($row['tanggal_lahir'])),
        'picture'   =>"http://$server_name" . $row['picture'])
    );
}

echo json_encode($response);

mysqli_close($conn);

?>