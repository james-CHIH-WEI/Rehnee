#include <SoftwareSerial.h>
SoftwareSerial BT(11, 12);  // 傳送腳(TX 11),接收腳(RX 12) ；接HC-06之TXD、RXD

const int xpin = A0;                  // x-axis
const int ypin = A1;                  // y-axis
const int zpin = A2;                  // z-axis
const int x2pin = A3;                  // x-axis
const int y2pin = A4;                  // y-axis
const int z2pin = A5;                  // z-axis

const String XHEADER = "X: ";
const String YHEADER = "Y: ";
const String ZHEADER = "Z: ";
const String TAB = "\t";

char receive;
int delay_time = 100, angle, x;

long z_value, z2_value, z_z2_gap;

long gap_z_sum = 0, gap_z_length = 0, gap_z;
long correction_z_sum = 0, correction_z_length = 0, correction_z;

long gap_y_sum = 0, gap_y_length = 0, gap_y;
long correction_y_sum = 0, correction_y_length = 0, correction_y;
long correction_y2_sum = 0, correction_y2_length = 0;

void setup()
{
  Serial.begin(9600);
  BT.begin(9600); //HC-06 預設 baud
}

void loop()
{

  z_value = 0;
  z2_value = 0;
  for (x = 0; x < 100; x++) {
    z_value = z_value + analogRead(zpin);
    z2_value = z2_value + analogRead(z2pin);
  }
  z_value = z_value / 100;
  z2_value = z2_value / 100;
  z_z2_gap = abs(z_value - z2_value);
  Serial.println(z_value+TAB+z2_value+TAB+z_z2_gap);

  //delay(500);

  if (BT.available()) {
    receive = BT.read();
    //Serial.println(TAB + x + TAB);
  }

  if (receive == '1') {//校正
    Serial.println(receive);
    delay(500);
    /*--------------z軸的比例--------------*/
    gap_z_sum = gap_z_sum + (abs(analogRead(zpin) - analogRead(z2pin)));
    gap_z_length = gap_z_length + 1;
    gap_z = gap_z_sum / gap_z_length;

    /*--------------z軸的值--------------*/
    correction_z_sum = correction_z_sum + analogRead(zpin);
    correction_z_length = correction_z_length + 1;
    correction_z = correction_z_sum / correction_z_length;

    /*--------------y軸的比例--------------*/
    gap_y_sum = gap_y_sum + (abs(analogRead(ypin) - analogRead(y2pin)));
    gap_y_length = gap_y_length + 1;
    gap_y = gap_y_sum / gap_y_length;

    /*--------------y軸的值--------------*/
    correction_y_sum = correction_y_sum + analogRead(ypin);
    correction_y_length = correction_y_length + 1;
    correction_y = correction_y_sum / correction_y_length;

    /*--------------y2軸的值--------------*/
    correction_y2_sum = correction_y2_sum + analogRead(y2pin);
    correction_y2_length = correction_y2_length + 1;

  } else if (receive == '2') {//動作一 屈膝抬腿
    angle = z_z2_gap * 90 / gap_z;
    Serial.println(z_z2_gap + TAB + angle);
    //Serial.println(x + TAB + analogRead(zpin) + TAB + analogRead(z2pin) + TAB + gap_z + TAB + angle + TAB);
    write(angle);

  } else if (receive == '3') {//動作二 直膝抬腿
    //angle = abs( analogRead(zpin) - correction_z) * 90 / gap_z;
    //Serial.println(x + TAB + correction_z + TAB + analogRead(zpin) + TAB + gap_z + TAB + angle + TAB+abs( analogRead(zpin) - correction_z));
    angle = abs( correction_y - analogRead(ypin)) * 90 / gap_y;
    Serial.println(x + TAB + correction_y + TAB + analogRead(ypin) + TAB + gap_y + TAB + angle + TAB + abs( analogRead(ypin) - analogRead(y2pin)));
    write(angle);

  } else if (receive == '4') { //動作三 靠牆半蹲
    //angle =  (z_value-) * 90 / (gap_y / gap_y_length);
    //Serial.println(x + TAB + analogRead(ypin) + TAB + analogRead(y2pin) + TAB + (correction_y2 / correction_y2_length) + TAB + (gap_y / gap_y_length) + TAB + angle + TAB);
    write(angle);
  }
}

void write(int angle)
{
  int analog_length, i = 1;
  int output;

  //angle = floor(angle / 5 * 5);
  analog_length = floor(log10(abs(angle))) + 1;

  BT.write(byte(40));//傳送左括號
  delay(delay_time);
  /*------------------------------------------------------------------------------*/
  while (i <= analog_length)
  {
    output = ((int)(angle / pow(10, analog_length - i))) % 10;
    output = int2asc(output);
    BT.write(byte(output)); //把每次收到的字元轉成byte封包傳至手機端
    delay(delay_time);  //每次傳輸間隔，如果太短會造成資料遺失或亂碼
    i++;
  }
  /*------------------------------------------------------------------------------*/
  BT.write(byte(41));//傳送右括號
  delay(delay_time);
}

int int2asc(int input)
{
  if (input == 0) {
    return 48;
  } else if (input == 1) {
    return 49;
  } else if (input == 2) {
    return 50;
  } else if (input == 3) {
    return 51;
  } else if (input == 4) {
    return 52;
  } else if (input == 5) {
    return 53;
  } else if (input == 6) {
    return 54;
  } else if (input == 7) {
    return 55;
  } else if (input == 8) {
    return 56;
  } else {
    return 57;
  }
}
