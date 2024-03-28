<?php

    //設定地點為台北時區
    date_default_timezone_set('Asia/Taipei');

    //取得 時:分:秒
    $now_time= date("2019-09-03 21:00:10");
    echo $now_time."<br/>";

    //要推播的時間
    $notice_time= date("21:01:00");
    echo $notice_time."<br/>";



    $gap=strtotime($notice_time) - strtotime($now_time);
    echo $gap/(60);



    
    /*$time1="2019-09-04";

    echo $time1."<br/>";

    echo (strtotime($time1) - strtotime($now_time))/ (60*60*24); //計算相差之天數*/

?>