<?php 
	
	//Getting the requested id
	$id = $_GET['id_chpr'];
	
	//Importing database
	require_once('dbConnect.php');
	
	//Creating sql query with where clause to get an specific employee
	$sql = "select chpr.id_chpr, chpr.titulo_chpr, chpr.form_id_usuf, chpr.to_id_usuf, usu.usuario as 'DE', uss.usuario 'PARA' from
			chprivado as chpr
			inner join
			usuarios as usu
			on chpr.form_id_usuf = usu.id_usu
			inner join
			usuarios as uss
			on chpr.to_id_usuf = uss.id_usu
			where chpr.id_chpr = $id";
	
	//getting result 
	$r = mysqli_query($con,$sql);
	
	//pushing result to an array 
	$result = array();
	$row = mysqli_fetch_array($r);
		array_push($result,array(
			"id_chpr"=>$row['id_chpr'],
			"form_id_usuf"=>$row['form_id_usuf'],
			"titulo_chpr"=>$row['titulo_chpr'],
			"to_id_usuf"=>$row['to_id_usuf'],
			"DE"=>$row['DE'],
			"PARA"=>$row['PARA']
		));
	

	//displaying in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>