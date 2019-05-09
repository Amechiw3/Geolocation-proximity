<?php 
	//Importing Database Script 
	require_once('dbConnect.php');
	
	//Creating sql query
	//$sql = "SELECT * FROM chpublico";
	$sql = "select chpr.id_chpr, chpr.titulo_chpr, chpr.form_id_usuf as 'De', uss.usuario as 'Para' from
			chprivado as chpr
			inner join
			usuarios as usu
			on chpr.form_id_usuf = usu.id_usu
			inner join
			usuarios as uss
			on chpr.to_id_usuf = uss.id_usu";


	//getting result 
	$r = mysqli_query($con, $sql);
	
	//creating a blank array 
	$result = array();
	
	//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array(
			"id_chpr"=>$row['id_chpr'],
			"form_id_usuf"=>$row['form_id_usuf'],
			"usuario"=>$row['usuario']
		));
	}
	
	//Displaying the array in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);

?>