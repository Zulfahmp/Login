<?php 

require_once 'connect.php';

@$key = $_POST['key'];

@$nama       = $_POST['nama'];
@$alamat    = $_POST['alamat'];
@$jenis_kelamin     = $_POST['jenis_kelamin'];
@$tanggal_lahir      = $_POST['tanggal_lahir'];
@$picture    = $_POST['picture'];

if ( $key == "insert" ){

    $birth_newformat = date('Y-m-d', strtotime($tanggal_lahir));

    $query = "INSERT INTO users_table (nama,alamat,jenis_kelamin,tanggal_lahir)
    VALUES ('$nama', '$alamat', '$jenis_kelamin', '$birth_newformat') ";

        if ( mysqli_query($conn, $query) ){

            if ($picture == null) {

                $finalPath = "/login/pet_logo.png"; 
                $result["value"] = "1";
                $result["message"] = "Success";
    
                echo json_encode($result);
                mysqli_close($conn);

            } else {

                $id = mysqli_insert_id($conn);
                $path = "images/$id.jpeg";
                $finalPath = "/login/".$path;

                $insert_picture = "UPDATE users_table SET picture='$finalPath' WHERE id='$id' ";
            
                if (mysqli_query($conn, $insert_picture)) {
            
                    if ( file_put_contents( $path, base64_decode($picture) ) ) {
                        
                        $result["value"] = "1";
                        $result["message"] = "Success!";
            
                        echo json_encode($result);
                        mysqli_close($conn);
            
                    } else {
                        
                        $response["value"] = "0";
                        $response["message"] = "Error! ".mysqli_error($conn);
                        echo json_encode($response);

                        mysqli_close($conn);
                    }

                }
            }

        } 
        else {
            $response["value"] = "0";
            $response["message"] = "Error! ".mysqli_error($conn);
            echo json_encode($response);

            mysqli_close($conn);
        }
}

?>