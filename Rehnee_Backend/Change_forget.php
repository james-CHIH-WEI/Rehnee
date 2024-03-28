<?php
    include("Configure.php");

    $key1 = isset($_GET["key1"]) ? $_GET["key1"] : "";
    $key2 = isset($_GET["key2"]) ? $_GET["key2"] : "";

    if($key1!="" && $key2!=""){
        $link=mysqli_connect($hostname,$username,$password,$database) or die("Error: Unable to connect to MySQL."); 
        mysqli_set_charset($link,"utf8");
        
        $query = "SELECT `P_ID` FROM `patient`";
        $result = mysqli_query($link, $query) or die("Connect DB Table Error!");
        
        while ($row=mysqli_fetch_array($result)){
            if($row["P_ID"]==$key1 ){
                $pass= "1";
                break;
            }else{
                $pass="0";
            }
        }
        echo $pass;
        
        mysqli_free_result($result);

        $query = "UPDATE `patient` SET `P_password`=$key2 WHERE `P_ID`=$key1";
        $result = mysqli_query($link, $query);  

        mysqli_close($link);

    }
?>