<?php
    define( 'API_SERVER_ACCESS_KEY', 'AAAAb_upLfg:APA91bF0YrWiniKd2LsgDCQLAq4G5okrbRXAsXLeaprmk3cox_Bqhm2hbpzlAi0ULc7l8Ox1zQL8jE0rhp6uqBeif-VwaChHWTDeGbRWmVRuMirW2mib6ba218pvGB56Nwy4yTW3Ih7x' );

    
    $token = isset($_GET["token"]) ? array($_GET["token"]) : "";
    $Id = isset($_GET["Id"]) ? $_GET["Id"] : "";
    $content =isset($_GET["content"]) ? $_GET["content"] : "";
    $date = isset($_GET["date"]) ? $_GET["date"] : "";
    $time = isset($_GET["time"]) ? $_GET["time"] : "";
    
    
    /*$token   = array("d_SN8UjD9bQ:APA91bF6hbefNDEmka0VH-XIjVlzx8ohUdcad6gYdkeVZ5rK6CmjNml7MsRz06EYi0zcJDRSdS_ToN7bzaKIHh7RGbWY_hsCJb3--RV83LySZd5jeYWYXFmymv-3vOg3ibLWuFa92Fxz");
    $Id = "1";
    $content   = "123";
    $date = "456";
    $time = "789";*/
    
    
    /*
    notification組成格式 官方範例
    {
      "message":{
        "token":"bk3RNwTe3H0:CI2k_HHwgIpoDKCIZvvDMExUdFQ3P1...",
        "notification":{
          "title":"Portugal vs. Denmark",
          "body":"great match!"
        }
      }
    }
    */
    
    $content = array
    (
        'Id' => $Id,
        'content' => $content,
        'date'  => $date,
        'time' => $time
    );
    $fields = array
    (
        'registration_ids'    => $token,
        //'notification' => $content,//通知型預設圖案
        'data'  => $content//資料型可以改變圖案
    );
    
    //firebase認證 與 傳送格式
    $headers = array
    (
        'Authorization: key='.API_SERVER_ACCESS_KEY,
        'Content-Type: application/json'
    );
    
    /*curl至firebase server發送到接收端*/
    $ch = curl_init();//建立CURL連線
    curl_setopt( $ch,CURLOPT_URL, 'https://fcm.googleapis.com/fcm/send' );
    curl_setopt( $ch,CURLOPT_POST, true );
    curl_setopt( $ch,CURLOPT_HTTPHEADER, $headers );
    curl_setopt( $ch,CURLOPT_RETURNTRANSFER, true );
    curl_setopt( $ch,CURLOPT_SSL_VERIFYPEER, false );
    curl_setopt( $ch,CURLOPT_POSTFIELDS, json_encode( $fields ) );
    $result = curl_exec($ch );
    curl_close( $ch );//關閉CURL連線
    //result 是firebase server的結果
    echo $result;
?>