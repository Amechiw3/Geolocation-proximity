<?php 
	
	//Getting the requested id
	$username = $_GET['email'];
	
	//Importing database
	require_once('dbConnect.php');
	
	//Creating sql query with where clause to get an specific employee
	$sql = "SELECT * FROM usuarios WHERE email='$username'";
	
	//getting result 
	$r = mysqli_query($con,$sql);
	
	//pushing result to an array 
	$result = array();
	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"id_usu"=>$row['id_usu'],
			"usuario"=>$row['usuario'],
			"cuenta"=>$row['cuenta'],
			"privacidad"=>$row['privacidad'],
			"password"=>$row['password'],
			"email"=>$row['email']
		));

	//displaying in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>