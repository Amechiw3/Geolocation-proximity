<?php 
	//Getting Id
	$id = $_GET['id_brod'];
	
	//Importing database
	require_once('dbConnect.php');
	
	//Creating sql query
	$sql = "DELETE FROM broadcast WHERE id_brod = $id;";
	
	//Deleting record in database 
	if(mysqli_query($con,$sql)){
		echo 'Broadcast Deleted Successfully';
	}else{
		echo 'Could Not Delete User Try Again';
	}
	
	//closing connection 
	mysqli_close($con);
?>