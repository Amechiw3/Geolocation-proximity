<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Getting values 
		$id_brod = $_POST['id_brod'];
		$fecha_broad = $_POST['fecha_broad'];
		$titulo_broad = $_POST['titulo_broad'];
		$cont_broad = $_POST['cont_broad'];
		$posx = $_POST['posx'];
		$posy = $_POST['posy'];

		
		//importing database connection script 
		require_once('dbConnect.php');
		
		//Creating sql query 
		$sql = "UPDATE broadcast SET fecha_broad = '$fecha_broad', titulo_broad = '$titulo_broad', cont_broad = '$cont_broad', posx = $posx, posy = $posy WHERE id_brod = $id_brod;";
		
		//Updating database table 
		if(mysqli_query($con,$sql)){
			echo 'broadcast Updated Successfully';
		}else{
			echo 'Could Not Update broadcast Try Again';
		}
		
		//closing connection 
		mysqli_close($con);
	}
?>