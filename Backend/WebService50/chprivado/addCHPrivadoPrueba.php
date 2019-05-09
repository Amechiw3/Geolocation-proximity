<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Getting values

		$titulo_chpr = $_POST['titulo_chpr'];
		$form_id_usuf = $_POST['form_id_usuf'];
		$to_id_usuf = $_POST['to_id_usuf'];

		//Creating an sql query
		$sql = "INSERT INTO chprivado (titulo_chpr, form_id_usuf, to_id_usuf) 
				VALUES ('$titulo_chpr', $form_id_usuf, (SELECT id_usu from usuarios where usuario = (
				SELECT usuario from usuarios	where usuario = '$to_id_usuf')))";
		
		//Importing our db connection script
		require_once('dbConnect.php');
		
		//Executing query to database
		if(mysqli_query($con, $sql)) {
			echo 'Chat private Added Successfully';
		} else {
			echo 'Could Not Add Chat privatess';
		}
		
		//Closing the database 
		mysqli_close($con);
	}
?>