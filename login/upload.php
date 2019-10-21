<?php 

include_once "connect.php";
	
	class emp{}
	
	$image = $_POST['image'];
	
		$random = random_word(20);
		
		$path = "images/".$random.".png";
		
		// sesuiakan ip address laptop/pc atau URL server
		$actualpath = "http://192.168.43.139/login/images/$path";
		
		$query = mysqli_query($con, "INSERT INTO volley_upload (photo,name) VALUES ('$actualpath','$name')");
		
		if ($query){
			file_put_contents($path,base64_decode($image));
			
			$response = new emp();
			$response->success = 1;
			$response->message = "Successfully Uploaded";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error Upload image";
			die(json_encode($response)); 
		}

	mysqli_close($con);

?>