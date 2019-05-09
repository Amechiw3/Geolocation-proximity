<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Getting values
		$fecha_brod = $_POST['fecha_brod'];
		$titulo_brod = $_POST['titulo_brod'];
		$cont_brod = $_POST['cont_brod'];
		$posx = $_POST['posx'];
		$posy = $_POST['posy'];

		
		//Creating an sql query
		$sql = "INSERT INTO broadcast (fecha_brod, titulo_brod, cont_brod, posx, posy) VALUES ('$fecha_brod', '$titulo_brod', '$cont_brod', '$posx', '$posy')";
		
		//Importing our db connection script
		require_once('dbConnect.php');
		
		//Executing query to database
		if(mysqli_query($con,$sql)){
			echo 'Broadcast Added Successfully';
		}else{
			echo 'Could Not Add broadcast';
		}
		
		//Closing the database 
		mysqli_close($con);
	}