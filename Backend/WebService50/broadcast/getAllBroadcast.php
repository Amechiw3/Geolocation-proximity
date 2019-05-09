<?php 
	//Importing Database Script 
	require_once('dbConnect.php');
	
	//Creating sql query
	//$sql = "SELECT * FROM broadcast";
	$sql = "SELECT * FROM broadcast WHERE fecha_brod = DATE(NOW())";
	
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