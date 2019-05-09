<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Getting values 
		$id_usu = $_POST['id_usu'];
		$usuario = $_POST['usuario'];
		$cuenta = $_POST['cuenta'];
		$privacidad = $_POST['privacidad'];
		$password = $_POST['password'];
		$email = $_POST['email'];
		
		//importing database connection script 
		require_once('dbConnect.php');
		
		//Creating sql query 
		$sql = "UPDATE usuarios SET usuario = '$usuario', cuenta = '$cuenta', privacidad = '$privacidad', password = MD5('$password'), email = '$email' WHERE id_usu = $id_usu;";
		
		//Updating database table 
		if(mysqli_query($con,$sql)){
			echo 'User Updated Successfully';
		}else{
			echo 'Could Not Update User Try Again';
		}
		
		//closing connection 
		mysqli_close($con);
	}
?>