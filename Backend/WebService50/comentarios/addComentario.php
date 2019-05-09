<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Getting values
		$fecha_com = $_POST['fecha_com'];
		$cont_com = $_POST['cont_com'];
		$id_chpu = $_POST['id_chpu'];
		$id_usuf = $_POST['id_usuf'];


		
		//Creating an sql query
		$sql = "INSERT INTO comentarios (fecha_com, cont_com, id_chpu, id_usuf) VALUES ('$fecha_com', '$cont_com', $id_chpu, $id_usuf)";
		
		//Importing our db connection script
		require_once('dbConnect.php');
		
		//Executing query to database
		if(mysqli_query($con, $sql)) {
			echo 'Comment Added Successfully';
		} else {
			echo 'Could Not Add Comments';
		}
		
		//Closing the database 
		mysqli_close($con);
	}
?>