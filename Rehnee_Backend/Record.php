<?php
    include("Configure.php");

    
    $key1 = isset($_GET["key1"]) ? $_GET["key1"] : "";

    if($key1!=""){
        $link=mysqli_connect($hostname,$username,$password,$database) or die("Error: Unable to connect to MySQL."); 
        mysqli_set_charset($link,"utf8");
        
        $query = "SELECT `P_ID`,`medical_date`,`medical_type`,`medical_angle`,`medical_frequency`,`finish_date`,`finish_time`,`spend_time` FROM `record` WHERE `record`.`P_ID`='$key1'";
        $result = mysqli_query($link, $query) or die("Connect DB Table Error!");
        $x=0;
        while ($row=mysqli_fetch_array($result)){
            
                $x++;
                echo 
                $row["medical_date"]." ".
                $row["medical_type"]." ".
                $row["medical_angle"]." ".
                $row["medical_frequency"]." ".
                $row["finish_date"]." ".
                $row["finish_time"]." ".
                $row["spend_time"]."/";
            
        }
        
        mysqli_free_result($result);
        mysqli_close($link);
    }
?>  