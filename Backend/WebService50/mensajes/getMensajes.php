<?php 
	
	//Getting the requested id
	$id = $_GET['id_msg'];
	
	//Importing database
	require_once('dbConnect.php');
	
	//Creating sql query with where clause to get an specific employee
	$sql = "select msg.id_msg, msg.fecha_msg, msg.cont_msg, msg.id_chprf, usu.usuario from
			mensajes as msg
			inner join
			usuarios as usu
			on msg.id_usuf = usu.id_usu
			where msg.id_chprf = $id";
	
	//getting result 
	$r = mysqli_query($con,$sql);
	
	//pushing result to an array 
	$result = array();
	while($row = mysqli_fetch_array($r)) {
		array_push($result,array(
			"id_msg"=>$row['id_msg'],
			"fecha_msg"=>$row['fecha_msg'],
			"cont_msg"=>$row['cont_msg'],
			"id_chprf"=>$row['id_chprf'],
			"usuario"=>$row['usuario']
		));
	}

	//displaying in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>