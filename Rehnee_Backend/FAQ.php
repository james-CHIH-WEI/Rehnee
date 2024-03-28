<?php
    include("Configure.php");

    $link=mysqli_connect($hostname,$username,$password,$database) or die("Error: Unable to connect to MySQL."); 
    mysqli_set_charset($link,"utf8");
    
    $query = "SELECT `type`,`title`,`content` FROM `faq`";
    $result = mysqli_query($link, $query) or die("Connect DB Table Error!");
    while ($row=mysqli_fetch_array($result)){
        echo $row["type"]." ".$row["title"]." ".$row["content"]."/";
    }
    mysqli_free_result($result);
    mysqli_close($link);

?>  