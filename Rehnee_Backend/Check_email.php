<?php
	include("Configure.php");

	
	$key1 = isset($_GET["key1"]) ? $_GET["key1"] : "";
	$key2 = isset($_GET["key2"]) ? $_GET["key2"] : "";

	if($key1!="" && $key2!=""){
		$link=mysqli_connect($hostname,$username,$password,$database) or die("Error: Unable to connect to MySQL."); 
		mysqli_set_charset($link,"utf8");
		
		$query = "SELECT `patient`.`P_ID`,`pdata`.`email` FROM `patient`,`pdata` WHERE `pdata`.`P_code` = `patient`.`P_code` AND `patient`.`P_ID`='$key1'";
		$result = mysqli_query($link, $query) or die("Connect DB Table Error!");

		$pass=0;
		while ($row=mysqli_fetch_array($result)){
			if($row["P_ID"]==$key1){
				$pass = 1;
				if($row["email"]==$key2){
					$pass = 2;
					break;
				}
				break;
			}
		}
		echo $pass;

		mysqli_free_result($result);
		mysqli_close($link);

	}
?>	