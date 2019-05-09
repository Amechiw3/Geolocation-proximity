<?php 
	//Importing Database Script 
	require_once('dbConnect.php');

	$POSX = $_GET['posx'];
	$POSY = $_GET['posy'];
	$Rango = $_GET['rango'];
	
	//Creating sql query
	//$sql = "SELECT * FROM broadcast";
	//$sql = "SELECT * FROM broadcast WHERE fecha_brod = DATE(NOW())";
	$sql = "SELECT id_brod, fecha_brod, titulo_brod, cont_brod, (6371 * ACOS( 
                                SIN(RADIANS(posx)) * SIN(RADIANS($POSX)) 
                                + COS(RADIANS(posy - $POSY)) * COS(RADIANS(posx)) 
                                * COS(RADIANS($POSX))
                                )
			                   ) AS distance
			FROM broadcast
			WHERE fecha_brod = DATE(NOW())
			HAVING distance < $Rango
			ORDER BY distance ASC";


	//getting result 
	$r = mysqli_query($con,$sql);
	
	//creating a blank array 
	$result = array();
	
	//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array(
			//"id_brod"=>$row['id_brod'],
			//"fecha_brod"=>$row['fecha_brod'],
			"titulo_brod"=>$row['titulo_brod'],
			"cont_brod"=>$row['cont_brod']
			//"posx"=>$row['posx'],
			//"posy"=>$row['posy']
		));
	}
	
	//Displaying the array in json format 
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);

?>