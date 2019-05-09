<?php 
	
	//Getting the requested id
	$id = $_GET['id_chpu'];
	
	//Importing database
	require_once('dbConnect.php');
	
	//Creating sql query with where clause to get an specific employee
	$sql = "select chpu.id_chpu, chpu.fecha_chpu, chpu.cont_chpu, usu.usuario from
			chpublico as chpu
			inner join
			usuarios as usu
			on chpu.id_usuf = usu.id_usu
			where chpu.id_chpu = $id";
	
	//getting result 
	$r = mysqli_query($con,$sql);
	
	//pushing result to an array 
	$result = array();
	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"id_chpu"=>$row['id_chpu'],
			"fecha_chpu"=>$row['fecha_chpu'],
			"cont_chpu"=>$row['cont_chpu'],
			"usuario"=>$row['usuario']
		));

	//displaying in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>