<?php 
	//Importing Database Script 
	require_once('dbConnect.php');
	
	//Creating sql query
	//$sql = "SELECT * FROM chpublico";
	$sql = "select comm.id_com, comm.fecha_com,comm.cont_com, comm.id_chpu, usu.usuario from
			comentarios as comm
			inner join
			usuarios as usu
			on comm.id_usuf = usu.id_usu";


	//getting result 
	$r = mysqli_query($con, $sql);
	
	//creating a blank array 
	$result = array();
	
	//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array(
			"id_com"=>$row['id_com'],
			"fecha_com"=>$row['fecha_com'],
			"cont_com"=>$row['cont_com'],
			"id_chpu"=>$row['id_chpu'],
			"usuario"=>$row['usuario']
		));
	}
	
	//Displaying the array in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);

?>