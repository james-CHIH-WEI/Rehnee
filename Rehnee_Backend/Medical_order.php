<?php
    include("Configure.php");

    
    $key1 = isset($_GET["key1"]) ? $_GET["key1"] : "";

    if($key1!=""){
        $link=mysqli_connect($hostname,$username,$password,$database) or die("Error: Unable to connect to MySQL."); 
        mysqli_set_charset($link,"utf8");
        
        $query = "SELECT `patient`.`P_ID`,`medical_order`.`date`,`medical_order`.`content` FROM `patient`,`pdata`,`medical_order` WHERE `pdata`.`P_code` = `medical_order`.`P_code` AND `pdata`.`P_code` = `patient`.`P_code` AND `patient`.`P_ID`='$key1'";
        $result = mysqli_query($link, $query) or die("Connect DB Table Error!");

        while ($row=mysqli_fetch_array($result)){
            if($row["P_ID"]==$key1 ){
                
                echo 
                $row["date"]." ".
                $row["content"]."/";
            }
        }

        mysqli_free_result($result);
        mysqli_close($link);
    }
?>  