<?php 
	//Importing Database Script 
	require_once('dbConnect.php');
	
	//Creating sql query
	//$sql = "SELECT * FROM chpublico";
	$sql = "select chpu.id_chpu, chpu.fecha_chpu, chpu.cont_chpu, usu.usuario from
			chpublico as chpu
			inner join
			usuarios as usu
			on chpu.id_usuf = usu.id_usu";


	//getting result 
	$r = mysqli_query($con, $sql);
	
	//creating a blank array 
	$result = array();
	
	//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array(
			"id_chpu"=>$row['id_chpu'],
			"fecha_chpu"=>$row['fecha_chpu'],
			"cont_chpu"=>$row['cont_chpu'],
			"usuario"=>$row['usuario']
		));
	}
	
	//Displaying the array in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);

?>