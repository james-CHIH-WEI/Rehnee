先將setting.txt裡的文字設為 1  (1代表執行，其餘為不執行)

再在執行 NotificationServer.php  這一支PHP

要關閉時將setting.txt裡的文字設為 1 以外的字
----------------------------------------------------------------

住家
http://192.168.0.101/AndroidStudio_php/Rehnee/NotificationServer/NotificationServer.php

Lab
http://10.20.18.121/AndroidStudio_php/Rehnee/NotificationServer/NotificationServer.php

手機
http://192.168.43.33/AndroidStudio_php/Rehnee/NotificationServer/NotificationServer.php

家
http://192.168.0.13/AndroidStudio_php/Rehnee/NotificationServer/NotificationServer.php

setting.txt-> 1代表執行

`sender`=1 代表醫師發送訊息
測試:
INSERT INTO `chat`(`p_ID`, `sender`, `content`, `date`, `time`) VALUES (1,1,"測試內容","2019-11-07","10:37")


