<?php 
	//Getting Id
	$id_chpr = $_GET['id_chpr'];
	
	//Importing database
	require_once('dbConnect.php');
	
	//Creating sql query
	$sql = "DELETE FROM chprivado WHERE id_chpr = $id_chpr";
	
	//Deleting record in database 
	if(mysqli_query($con,$sql)){
		echo 'Chat private Deleted Successfully';
	}else{
		echo 'Could Not Delete Chat private Try Again';
	}
	
	//closing connection 
	mysqli_close($con);
?>