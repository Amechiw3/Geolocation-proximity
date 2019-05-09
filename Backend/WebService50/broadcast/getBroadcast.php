<?php 
	
	//Getting the requested id
	$id = $_GET['id_brod'];
	
	//Importing database
	require_once('dbConnect.php');
	
	//Creating sql query with where clause to get an specific employee
	$sql = "SELECT * FROM broadcast WHERE id_usu = $id";
	
	//getting result 
	$r = mysqli_query($con,$sql);
	
	//pushing result to an array 
	$result = array();
	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"id_broad"=>$row['id_broad'],
			"fecha_broad"=>$row['fecha_broad'],
			"titulo_broad"=>$row['titulo_broad'],
			"cont_broad"=>$row['cont_broad'],
			"posx"=>$row['posx'],
			"posy"=>$row['posy']
		));

	//displaying in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>