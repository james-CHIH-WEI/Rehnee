<?php
    include("Configure.php");

    $key1 = isset($_GET["key1"]) ? $_GET["key1"] : "";//p_ID
    $key2 = isset($_GET["key2"]) ? $_GET["key2"] : "";//content
    $key3 = isset($_GET["key3"]) ? $_GET["key3"] : "";//date
    $key4 = isset($_GET["key4"]) ? $_GET["key4"] : "";//time


    if($key1!="" && $key2!=""&& $key3!=""&& $key4!=""){
        $link=mysqli_connect($hostname,$username,$password,$database) or die("Error: Unable to connect to MySQL."); 
        mysqli_set_charset($link,"utf8");
        
        //sender=2 代表病人所發送的對話
        $query = "INSERT INTO `chat` (`P_ID`,`sender`,`content`,`date`,`time`) VALUES ('$key1','2','$key2','$key3','$key4')";
        $result = mysqli_query($link, $query);  
        
        mysqli_close($link);
    }
?>  