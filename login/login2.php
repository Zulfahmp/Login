<?php 

require_once "connect.php";

@$email = $_POST['email'];
@$password = $_POST['password'];

$sql = "SELECT * FROM users_table WHERE email='$email' AND password='$password'";
 //executing query
$result = mysqli_query($conn,$sql);
$check = mysqli_fetch_array($result);
    if(isset($check)){
        echo "success";
    }else{
    //displaying failure
    echo "failure";
    }
    {
        mysqli_close($conn);
    }

?>