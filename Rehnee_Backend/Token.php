<?php
    include("Configure.php");

    
    $key1 = isset($_GET["key1"]) ? $_GET["key1"] : "";//p_ID
    $key2 = isset($_GET["key2"]) ? $_GET["key2"] : "";//token

    if($key1!="" && $key2!=""){
        $link=mysqli_connect($hostname,$username,$password,$database) or die("Error: Unable to connect to MySQL."); 
        mysqli_set_charset($link,"utf8");
        
        /*先搜尋這個token有沒有其他帳號使用過，如果有就把它清除，目的是為了防止，一支手機登入A帳號後資料庫記下token
        ，但是後來登入B帳號，那就會同時有兩個帳號有相同的token，這樣如果A帳號有新訓訊息，程式就會找A帳號的token並發
        送訊息，但是因為A跟B是同一個token,那就會發生B收到A的訊息*/

        //先搜尋這個token有沒有其他帳號使用過，如果有就把它清除
        $Id="";
        $query = "SELECT `P_code` FROM `pdata` WHERE `token`='$key2'";
        $result = mysqli_query($link, $query) or die("Connect DB Table Error!");
        while($row=mysqli_fetch_array($result)){
            $Id=$row["P_code"];
        }
        mysqli_free_result($result);

        if($Id!=""){
            $query = "UPDATE `pdata` SET `token`='' WHERE `pdata`.`P_code` = '$Id'";
            mysqli_query($link, $query);
        }


        //更新帳號的token
        $query = "UPDATE `patient`,`pdata` SET `token`='$key2' WHERE `pdata`.`P_code` = `patient`.`P_code` AND `patient`.`P_ID`='$key1'";
        mysqli_query($link, $query);

        mysqli_close($link);
    }
    
?>  