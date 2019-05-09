<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Getting values
		$fecha_chpu = $_POST['fecha_chpu'];
		$cont_chpu = $_POST['cont_chpu'];
		$id_usuf = $_POST['id_usuf'];
		$latx_chpu = $_POST['latx_chpu'];
		$lony_chpu = $_POST['lony_chpu'];

		
		//Creating an sql query
		$sql = "INSERT INTO chpublico (fecha_chpu, cont_chpu, id_usuf, lony_chpu, latx_chpu) VALUES ('$fecha_chpu', '$cont_chpu', $id_usuf, '$lony_chpu', '$latx_chpu')";
		
		//Importing our db connection script
		require_once('dbConnect.php');
		
		//Executing query to database
		if(mysqli_query($con, $sql)) {
			echo 'Chat public Added Successfully';
		} else {
			echo 'Could Not Add Chat public';
		}
		
		//Closing the database 
		mysqli_close($con);
	}
?>