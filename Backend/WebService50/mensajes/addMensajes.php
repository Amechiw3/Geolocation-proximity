<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Getting values
		$fecha_msg = $_POST['fecha_msg'];
		$cont_msg = $_POST['cont_msg'];
		$id_chprf = $_POST['id_chprf'];
		$id_usuf = $_POST['id_usuf'];

		
		//Creating an sql query
		$sql = "INSERT INTO mensajes (fecha_msg, cont_msg, id_chprf, id_usuf) VALUES ('$fecha_msg', '$cont_msg', $id_chprf, $id_usuf)";
		
		//Importing our db connection script
		require_once('dbConnect.php');
		
		//Executing query to database
		if(mysqli_query($con, $sql)) {
			echo 'Comentario Added Successfully';
		} else {
			echo 'Could Not Add Comentario';
		}
		
		//Closing the database 
		mysqli_close($con);
	}
?>