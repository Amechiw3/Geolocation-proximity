<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Getting values 
		$id_msg = $_POST['id_msg'];
		$fecha_msg = $_POST['fecha_msg'];
		$cont_msg = $_POST['cont_msg'];


		
		//importing database connection script 
		require_once('dbConnect.php');
		
		//Creating sql query 
		$sql = "UPDATE mensajes SET fecha_msg = '$fecha_msg', cont_msg = '$cont_msg' WHERE id_msg = $id_msg";
		
		//Updating database table 
		if(mysqli_query($con,$sql)){
			echo 'Mensaje Updated Successfully';
		}else{
			echo 'Could Not Update Mensaje Try Again';
		}
		
		//closing connection 
		mysqli_close($con);
	}
?>