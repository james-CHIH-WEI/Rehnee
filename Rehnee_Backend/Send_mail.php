<?php
	include("Configure.php");


    $key1 = isset($_GET["key1"]) ? $_GET["key1"] : "";//P_ID
    $key2 = isset($_GET["key2"]) ? $_GET["key2"] : "";//收件人信箱


    if($key1!="" && $key2!=""){
		$link=mysqli_connect($hostname,$username,$password,$database) or die("Error: Unable to connect to MySQL."); 
		mysqli_set_charset($link,"utf8");
		
        $verification_code=rand(100000,999999);
        $query = "UPDATE `patient`,`pdata` SET `pdata`.`verification_code`=$verification_code WHERE `pdata`.`P_code` = `patient`.`P_code` AND `patient`.`P_ID`='$key1'";
        $result = mysqli_query($link, $query); 

        send($verification_code);
        
        mysqli_close($link);
    }
    
    

    function send($verification_code){
		require_once('./PHPMailer/PHPMailerAutoload.php');

	    //$C_name="測試";//收件人名字
	    $C_email=$_GET["key2"];//收件人信箱
	    $C_message="Rehnee用戶您好，我們收到您所提出的密碼救援要求，請在手機輸入下方驗證碼，謝謝!<br/><br/>驗證碼 : ".$verification_code."";//內容
	    $mail= new PHPMailer();                             //建立新物件
	    $mail->SMTPDebug = 2;                        
	    $mail->IsSMTP();                                    //設定使用SMTP方式寄信
	    $mail->SMTPAuth = true;                        //設定SMTP需要驗證
	    $mail->SMTPSecure = "ssl";                    // Gmail的SMTP主機需要使用SSL連線
	    $mail->Host = "smtp.gmail.com";             //Gamil的SMTP主機
	    $mail->Port = 465;                                 //Gamil的SMTP主機的埠號(Gmail為465)。
	    $mail->CharSet = "utf-8";                       //郵件編碼
	    $mail->Username = "@gmail.com";       //Gamil帳號
	    $mail->Password = "";                 //Gmail密碼
	    $mail->From = "@gmail.com";        //寄件者信箱
	    $mail->FromName = "Rehnee膝蓋復健系統";                  //寄件者姓名
	    $mail->Subject ="密碼變更"; //郵件標題
	    $mail->Body = $C_message; //郵件內容
	    //$mail->addAttachment('../uploadfile/file/dirname.png','new.jpg'); //附件，改以新的檔名寄出
	    $mail->IsHTML(true);                             //郵件內容為html
	    $mail->AddAddress("$C_email");            //收件者郵件及名稱
	    $mail->Send();
    }

?>  