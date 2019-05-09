<?php 
	//Getting Id
	$id_msg = $_GET['id_msg'];
	
	//Importing database
	require_once('dbConnect.php');
	
	//Creating sql query
	$sql = "DELETE FROM mensajes WHERE id_msg = $id_msg";
	
	//Deleting record in database 
	if(mysqli_query($con,$sql)){
		echo 'Mensaje Deleted Successfully';
	}else{
		echo 'Could Not Delete Mensaje Try Again';
	}
	
	//closing connection 
	mysqli_close($con);
?>