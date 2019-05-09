<?php 
	//Importing Database Script 
	require_once('dbConnect.php');
	
	//Creating sql query
	//$sql = "SELECT * FROM chpublico";
	$sql = "select msg.id_msg, msg.fecha_msg, msg.cont_msg, msg.id_chprf, usu.usuario from
			mensajes as msg
			inner join
			usuarios as usu
			on msg.id_usuf = usu.id_usu";


	//getting result 
	$r = mysqli_query($con, $sql);
	
	//creating a blank array 
	$result = array();
	
	//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array(
			"id_msg"=>$row['id_msg'],
			"fecha_msg"=>$row['fecha_msg'],
			"cont_msg"=>$row['cont_msg'],
			"id_chprf"=>$row['id_chprf'],
			"usuario"=>$row['usuario']
		));
	}
	
	//Displaying the array in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);

?>