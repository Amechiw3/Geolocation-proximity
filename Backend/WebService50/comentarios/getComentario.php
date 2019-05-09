<?php 
	
	//Getting the requested id
	$id = $_GET['id_com'];
	
	//Importing database
	require_once('dbConnect.php');
	
	//Creating sql query with where clause to get an specific employee
	$sql = "select comm.id_com, comm.fecha_com,comm.cont_com, comm.id_chpu, usu.usuario from
			comentarios as comm
			inner join
			usuarios as usu
			on comm.id_usuf = usu.id_usu
			where comm.id_chpu = $id";

    //compile 'com.mcxiaoke.volley:library:1.0.+'

	//getting result 
	$r = mysqli_query($con,$sql);
	
	//pushing result to an array 
	$result = array();

	while($row = mysqli_fetch_array($r)) {

		array_push($result,array(
			"id_com"=>$row['id_com'],
			"fecha_com"=>$row['fecha_com'],
			"cont_com"=>$row['cont_com'],
			"id_chpu"=>$row['id_chpu'],
			"usuario"=>$row['usuario']
		));
	}

	//displaying in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>