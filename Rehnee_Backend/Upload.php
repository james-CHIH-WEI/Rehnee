<?php
    include("Configure.php");

    $key1 = isset($_GET["key1"]) ? $_GET["key1"] : "";
    $key2 = isset($_GET["key2"]) ? $_GET["key2"] : "";
    $key3 = isset($_GET["key3"]) ? $_GET["key3"] : "";
    $key4 = isset($_GET["key4"]) ? $_GET["key4"] : "";
    $key5 = isset($_GET["key5"]) ? $_GET["key5"] : "";
    $key6 = isset($_GET["key6"]) ? $_GET["key6"] : "";
    $key7 = isset($_GET["key7"]) ? $_GET["key7"] : "";
    $key8 = isset($_GET["key8"]) ? $_GET["key8"] : "";
    

    if($key1!="" && $key2!="" && $key3!="" && $key4!=""&& $key5!=""&& $key6!=""&& $key7!=""&& $key8!=""){
        $link=mysqli_connect($hostname,$username,$password,$database) or die("Error: Unable to connect to MySQL."); 
	    mysqli_set_charset($link,"utf8");

        $query = "INSERT INTO `record` (`P_ID`,`medical_date`,`medical_type`,`medical_angle`,`medical_frequency`,`finish_date`,`finish_time`,`spend_time` ) VALUES ('$key1','$key2','$key3','$key4','$key5','$key6','$key7','$key8')";
        $result = mysqli_query($link, $query);  
        
        mysqli_close($link);
    }
    

?>  