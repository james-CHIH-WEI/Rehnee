<?php
    include("../Configure.php");

    ignore_user_abort(true);
    set_time_limit(0);

    $start_length=0;
    $status=0;

    $link=mysqli_connect($hostname,$username,$password,$database) or die("Error: Unable to connect to MySQL."); 
	mysqli_set_charset($link,"utf8");
    message();

    while(file_get_contents("setting.txt")=="1"){//讀取setting檔案如果讀取的內容是 1 
        
        if(connection_status() != CONNECTION_NORMAL){
            break;
        }

        message();
        visit_back();

        sleep(1); // sleep 1秒 (以秒為單位)
    }

    mysqli_close($link);
    /*------------------------------------------------------------------------------------------*/

    function message(){
        global $start_length,$link;
        $now_length=0;

        $query = "SELECT `P_ID`,`content`,`date`,`time` FROM `chat` WHERE `sender`=1";//`sender`=1代表是醫生傳的
        $result = mysqli_query($link, $query) or die("Connect DB Table Error!");
        if($start_length==0){
            while($row=mysqli_fetch_array($result)){
                $start_length=$start_length+1;
            }
        }else{
            while($row=mysqli_fetch_array($result)){
                $now_length=$now_length+1;
                if($now_length>$start_length){
                    for($x=$start_length;$x<$now_length;$x++){
                        echo $row["P_ID"].$row["content"].$row["date"].$row["time"];
                        notification("message",$row["P_ID"],$row["content"],$row["date"],$row["time"]);
                    }
                    $start_length=$now_length;
                }
            }
        }
        mysqli_free_result($result);
        
    }


    function visit_back()
    {
        global $status;
        
        date_default_timezone_set('Asia/Taipei');//設定地點為台北時區

        $now_time= date("Y-m-d H:i:s");//取得 時:分:秒
      

        $notice_time= date("20:00:00");//要推播的時間

        $gap=(strtotime($now_time) - strtotime($notice_time))/(60);//分
        

        if($gap>0&$gap<=1){
            if($status==0){
        
                $link=mysqli_connect("localhost","root","","rehnee") or die("Error: Unable to connect to MySQL."); 
                mysqli_set_charset($link,"utf8");

                $query = "SELECT `P_ID`,`visit_back_date` FROM `medical_order`";
                $result = mysqli_query($link, $query) or die("Connect DB Table Error!");

                while ($row=mysqli_fetch_array($result)){
                    
                    $gap=(strtotime($row["visit_back_date"]) - strtotime($now_time))/ (60*60*24);
                    
                    if($gap>0&$gap<=1){//如果是明天
                        echo $row["P_ID"];
                        notification("visit_back",$row["P_ID"],"明天記得到診所回診+謝謝~","","");//加號代表空白
                    }
                }
                mysqli_free_result($result);
                mysqli_close($link);
                $status=1;
            }
        }else{
            $status=0;
        }
    }

    function notification($type,$P_ID,$content,$date,$time){

        $token=get_token($P_ID);

        if($type=="message"){
            $url="http://".ip."/AndroidStudio_php/Rehnee/Notification.php?token=".$token."&Id=".$P_ID."&content=".$content."&date=".$date."&time=".$time;
        }elseif ($type=="visit_back") {
            $url="http://".ip."/AndroidStudio_php/Rehnee/Notification.php?token=".$token."&Id=".$P_ID."&content=".$content;
            echo "<br>".$url;
        }

        $ch = curl_init();
        $options = array( 
          CURLOPT_URL => $url, 
          CURLOPT_HEADER => false, 
          CURLOPT_RETURNTRANSFER => true, 
          CURLOPT_TIMEOUT=>1,
          CURLOPT_USERAGENT => "Google Bot", 
          CURLOPT_FOLLOWLOCATION => true
        );

        curl_setopt_array($ch, $options);
        curl_exec($ch);
    }

    function get_token($P_ID){
        global $link;

        $query = "SELECT `pdata`.`token` FROM `patient`,`pdata` WHERE `pdata`.`P_code` = `patient`.`P_code` AND `patient`.`P_ID`='$P_ID'";
        $result = mysqli_query($link, $query) or die("Connect DB Table Error!");
        
        while($row=mysqli_fetch_array($result)){
            $token=$row["token"];
        }      
        mysqli_free_result($result);
        return $token;
    }

?>