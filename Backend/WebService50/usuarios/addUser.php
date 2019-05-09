<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Getting values
		$usuario = $_POST['usuario'];
		$cuenta = $_POST['cuenta'];
		$privacidad = $_POST['privacidad'];
		$password = $_POST['password'];
		$email = $_POST['email'];

		
		//Creating an sql query
		$sql = "INSERT INTO usuarios (usuario, cuenta, privacidad, password, email) VALUES ('$usuario','$cuenta','$privacidad',MD5('$password'),'$email')";
		
		//Importing our db connection script
		require_once('dbConnect.php');
		
		//Executing query to database
		if(mysqli_query($con,$sql)){
			echo 'User Added Successfully';
		}else{
			echo 'Could Not Add User';
		}
		
		//Closing the database 
		mysqli_close($con);
	}