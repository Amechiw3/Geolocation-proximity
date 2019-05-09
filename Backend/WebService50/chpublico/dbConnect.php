<?php
	/*
		author: Belal Khan 
		website: https://www.simplifiedcoding.net
		
		My Database is androiddb 
		you need to change the database name rest the things are default if you are using wamp or xampp server
		You may need to change the host user name or password if you have changed the defaults in your server
	*/
	
	//Defining Constants
	//define('HOST','localhost');
	//define('USER','root');
	//define('PASS','');
	//define('DB','webservice');

	define('HOST','50.62.209.162');
	define('USER','android');
	define('PASS','Mart!n12$$');
	define('DB','sqlWebService');
	
	//Connecting to Database
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
?>