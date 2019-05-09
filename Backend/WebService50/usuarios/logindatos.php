<?php 
	
	if($_SERVER['REQUEST_METHOD']=='GET'){
		//Getting values 
		$username = $_GET['email'];
		//$password = $_GET['password'];
		
		//Creating sql query
		$sql = "SELECT * FROM usuarios WHERE email='$username'";
		//"AND password=md5('$password')";
		
		//importing dbConnect.php script 
		require_once('dbConnect.php');
		
		//executing query
		$r = mysqli_query($con,$sql);
		
		//fetching result
		$result = array();
		$row = mysqli_fetch_array($r);

		array_push($result, array(
			"id_usu"=>$row['id_usu']/*,
			"usuario"=>$row['usuario'],
			"cuenta"=>$row['cuenta'],
			"privacidad"=>$row['privacidad'],
			"password"=>$row['password'],
			"email"=>$row['email']*/
			));
		
		//if we got some result 
		echo json_encode(array("result"=>$result));
		mysqli_close($con);
	}
?>