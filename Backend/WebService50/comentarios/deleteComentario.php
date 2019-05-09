<?php 
	//Getting Id
	$id_com = $_GET['id_com'];
	
	//Importing database
	require_once('dbConnect.php');
	
	//Creating sql query
	$sql = "DELETE FROM comentarios WHERE id_com = $id_com";
	
	//Deleting record in database 
	if(mysqli_query($con,$sql)){
		echo 'Comment Deleted Successfully';
	}else{
		echo 'Could Not Delete Comment Try Again';
	}
	
	//closing connection 
	mysqli_close($con);
?>