<?php 
	//Getting Id
	$id = $_GET['id_usu'];
	
	//Importing database
	require_once('dbConnect.php');
	
	//Creating sql query
	$sql = "DELETE FROM id_usu WHERE id_usu = $id;";
	
	//Deleting record in database 
	if(mysqli_query($con,$sql)){
		echo 'User Deleted Successfully';
	}else{
		echo 'Could Not Delete User Try Again';
	}
	
	//closing connection 
	mysqli_close($con);
?>