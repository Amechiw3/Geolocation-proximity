<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Getting values 
		$id_chpr = $_POST['id_chpr'];
		$titulo_chpr = $_POST['titulo_chpr'];
		$form_id_usuf = $_POST['form_id_usuf'];
		$to_id_usuf = $_POST['to_id_usuf'];


		
		//importing database connection script 
		require_once('dbConnect.php');
		
		//Creating sql query 
		$sql = "UPDATE chpublico SET titulo_chpr = '$titulo_chpr', form_id_usuf = $form_id_usuf, to_id_usuf = $to_id_usuf, id_usuf = $id_usuf WHERE id_chpr = $id_chpr and id_usuf = $id_usuf;";
		
		//Updating database table 
		if(mysqli_query($con,$sql)){
			echo 'Chat private Updated Successfully';
		}else{
			echo 'Could Not Update Chat private Try Again';
		}
		
		//closing connection 
		mysqli_close($con);
	}
?>