<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Getting values 
		$id_com = $_POST['id_com'];
		$fecha_com = $_POST['fecha_com'];
		$cont_com = $_POST['cont_com'];
		$id_usuf = $_POST['id_usuf'];


		
		//importing database connection script 
		require_once('dbConnect.php');
		
		//Creating sql query 
		$sql = "UPDATE chpublico SET fecha_com = '$fecha_com', cont_com = '$cont_com', id_usuf = $id_usuf WHERE id_com = $id_com and id_usuf = $id_usuf;";
		
		//Updating database table 
		if(mysqli_query($con,$sql)){
			echo 'Comment Updated Successfully';
		}else{
			echo 'Could Not Update Comment Try Again';
		}
		
		//closing connection 
		mysqli_close($con);
	}
?>