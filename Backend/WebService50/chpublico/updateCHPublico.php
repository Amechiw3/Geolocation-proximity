<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Getting values 
		$id_chpu = $_POST['id_chpu'];
		$fecha_chpu = $_POST['fecha_chpu'];
		$cont_chpu = $_POST['cont_chpu'];
		$id_usuf = $_POST['id_usuf'];


		
		//importing database connection script 
		require_once('dbConnect.php');
		
		//Creating sql query 
		$sql = "UPDATE chpublico SET fecha_chpu = '$fecha_chpu', cont_chpu = '$cont_chpu', id_usuf = $id_usuf WHERE id_chpu = $id_chpu and id_usuf = $id_usuf;";
		
		//Updating database table 
		if(mysqli_query($con,$sql)){
			echo 'Chat public Updated Successfully';
		}else{
			echo 'Could Not Update broadcast Try Again';
		}
		
		//closing connection 
		mysqli_close($con);
	}
?>