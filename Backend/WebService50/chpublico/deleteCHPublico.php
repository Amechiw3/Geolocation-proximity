<?php 
	//Getting Id
	$idchpu = $_GET['id_chpu'];
	$idusu = $_GET['id_usuf'];
	
	//Importing database
	require_once('dbConnect.php');
	
	//Creating sql query
	$sql = "DELETE FROM chpublico WHERE id_chpu = $idchpu and id_usuf = $idusu;";
	
	//Deleting record in database 
	if(mysqli_query($con,$sql)){
		echo 'Chat public Deleted Successfully';
	}else{
		echo 'Could Not Delete User Try Again';
	}
	
	//closing connection 
	mysqli_close($con);
?>