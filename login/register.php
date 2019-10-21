<?php 

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $nama = $_POST['nama'];
    $email = $_POST['email'];
    $password = $_POST['password'];

    // $password = password_hash($password, PASSWORD_DEFAULT);

    require_once 'connect.php';

    $sql = "INSERT INTO users_table (nama,email,password) VALUES ('$nama','$email','$password')";

    if(mysqli_query($conn, $sql)){
        $result["succes"] = "1";
        $result["message"] = "Succes";

        echo json_encode($result);
        mysqli_close($conn);
    }else{
        $result["succes"] = "0";
        $result["message"] = "Error";

        echo json_encode($result);
        mysqli_close($conn);
    }
}

?>