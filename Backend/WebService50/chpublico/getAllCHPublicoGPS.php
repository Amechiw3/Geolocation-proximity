<?php 
	//Importing Database Script 
	require_once('dbConnect.php');

	$LATX = $_GET['latx'];
	$LONY = $_GET['lony'];
	
	//Creating sql query
	//$sql = "SELECT * FROM broadcast";
	//$sql = "SELECT * FROM broadcast WHERE fecha_brod = DATE(NOW())";
	$sql = "SELECT id_chpu, fecha_chpu, cont_chpu, usuario, (6371 * ACOS( 
                                SIN(RADIANS(latx_chpu)) * SIN(RADIANS($LATX)) 
                                + COS(RADIANS(lony_chpu - $LONY)) * COS(RADIANS(latx_chpu)) 
                                * COS(RADIANS($LATX))
                                )
			                   ) AS distance
			FROM chpublico
			inner join usuarios
			on id_usuf = id_usu
			HAVING distance < 1 /* 1 KM  a la redonda */
			ORDER BY distance ASC";


	//getting result 
	$r = mysqli_query($con,$sql);
	
	//creating a blank array 
	$result = array();
	
	//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
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