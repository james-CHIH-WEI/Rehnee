-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 
-- 伺服器版本： 10.4.6-MariaDB
-- PHP 版本： 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `rehnee`
--

-- --------------------------------------------------------

--
-- 資料表結構 `chat`
--

CREATE TABLE `chat` (
  `id` int(11) NOT NULL,
  `P_ID` varchar(18) NOT NULL,
  `sender` int(10) NOT NULL,
  `content` text NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `chat`
--

INSERT INTO `chat` (`id`, `P_ID`, `sender`, `content`, `date`, `time`) VALUES
(3, '1', 2, '245', '2019-10-27', '13:38:00'),
(4, '1', 1, '測試內容', '2019-09-17', '01:44:00'),
(5, '1', 1, '測試內容', '2019-09-17', '01:44:00'),
(6, '1', 1, '測試內容', '2019-09-17', '01:44:00'),
(7, '1', 1, '測試內容', '2019-09-17', '01:44:00'),
(8, '1', 1, '測試內容', '2019-09-17', '01:44:00'),
(9, '1', 1, '測試內容', '2019-09-17', '01:44:00'),
(10, '1', 1, '測試內容', '2019-09-17', '01:44:00'),
(11, '1', 1, '測試內容', '2019-11-07', '10:37:00'),
(12, '1', 1, '測試內容', '2019-11-07', '10:37:00'),
(13, '1', 1, '測試內容', '2019-11-07', '11:06:00'),
(14, '1', 1, '測試內容', '2019-11-13', '16:19:00'),
(15, '1', 1, '測試內容', '2019-11-13', '16:19:00');

-- --------------------------------------------------------

--
-- 資料表結構 `disease`
--

CREATE TABLE `disease` (
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `disease`
--

INSERT INTO `disease` (`name`) VALUES
('Abrasion wound'),
('Acetabular Dysplasia'),
('Acetabular Fracture'),
('Acetabular vascular necrosis'),
('Achilles Tendon Rupture'),
('Acromio-Clavical Joint Dislocation'),
('Aneurysmal Bone Cyst'),
('Ankle Fracture'),
('Ankle Instability'),
('Ankle Ligament Injury'),
('Ankylosing Spondylitis'),
('Anteversion of Femoral Neck'),
('Arthritis'),
('Arthrogryposis Multiple Congenita'),
('Baker\'s Cyst'),
('Bankart Lesion'),
('Barton\'s Fracture'),
('Benign prostatic hypertrophy'),
('Benign Tumor of Lower Limb'),
('Bennett\'s Fracture'),
('Bimalleolus Fracture'),
('Blount\'s Diease'),
('Bone Cyst'),
('Bone Neoplasm'),
('Bone Tumor'),
('Brachial Plexus Injury'),
('Burn injury'),
('Bursitis'),
('Burst Fracture	'),
('Calcaneus Spur'),
('Carpal bone fracture'),
('Carpal Tunnel Syndrome'),
('Cavovarus Deformity'),
('Cellulitis'),
('Cerebral Palsy	'),
('Cervical Vertebrae Fracture'),
('Chondro-Blastoma'),
('Chondroma'),
('Chondromalacia Both Knee'),
('Chondromatosis'),
('Chondromyxoid Fibroma'),
('Chondrosarcoma'),
('Chronic Hypertrophic Rhnitis'),
('Chronic Osteomyelitis'),
('Clavicle fracture'),
('Claw Toe'),
('Clubfoot'),
('Coccyx Fracture'),
('Colle\'s Fracture'),
('Compartment Syndrome	'),
('Compression Fracture'),
('Congenital Club Foot'),
('Congenital Hemihypertrophy'),
('Congenital Muscular Torticollis'),
('Congenital Syndactyly'),
('Congenital Trigger Thumb'),
('Congenutal Knee Dislocation'),
('Contracture'),
('Contussion'),
('Coxa Valgus'),
('Coxa Varus'),
('Crush Injury'),
('Crushing injury'),
('Cubital Tunnel Syndrome'),
('Cubitus Valgus'),
('Cubitus Varus'),
('Cutting Injury'),
('Cutting wound'),
('Deep Vein Thrombosis'),
('Degeneration Ssoliosis'),
('Degenerative Joint Disease'),
('Dehydration'),
('Delay Union'),
('Developmetal Dysplasia of Hip'),
('Deviation of nasal septum'),
('Diabetes mellitus'),
('Diabetes Mellitus Foot'),
('Diarrhea'),
('Digital Nerve Injury'),
('Dislocation'),
('Distal Inter-Phalangeal Osteoarthrosis'),
('Distal Radius Fracture'),
('Duodenal ulcer'),
('Duplication of Thumb'),
('Dupuytren\'s Contracture	'),
('Elbow Fracture'),
('Empyema'),
('Epidermoid Cancer'),
('Equinovarus Deformity'),
('Esophageal cancer'),
('Ewig\'s Sarcoma'),
('Extensor Policis Brevis Tear'),
('Felon'),
('Femeral Fracture'),
('Femeral Neck Fracture'),
('Femeral Shaft Fracture'),
('Femeral Supracondylar Fracture'),
('Fibrosarcoma'),
('Fibrous Dyslasia'),
('Fibula fracture'),
('Fibular Farcture'),
('Flexion Contracture of Finger	'),
('Flexor Policis Longus Tear'),
('Forearm Fracture'),
('Foreign Body in Joint'),
('Forzen Shoulder'),
('fracture'),
('Full thickness skin graft '),
('Galeazzi\'s Fracture'),
('Gangrene'),
('Genu Valgus'),
('Genu Varus'),
('Giant Cell Tumor'),
('Gout'),
('Gouty Arthritis'),
('Growth Plate Injury'),
('Guyon Canal Syndrome'),
('Hallux'),
('Head injury'),
('Hemangioma'),
('Hemarthrosis'),
('Hematuria'),
('Hemimelia'),
('Herniation of inter vertebral Disc'),
('Hip Fracture Dislocation'),
('Humeral Neck Fracture'),
('Humeral Shaft Fracture'),
('Humerus'),
('Hydronephrosis'),
('Hypermobile Syndrome'),
('Impingement Syndrome'),
('Implant Loosening'),
('Interal Derangement of Knee'),
('Intertrochanteric Fracture'),
('Ischemic Necrosis Femoral Herd'),
('Kyphosis'),
('Laceration wound'),
('Lateral Collateral Ligament Tear/Rupture'),
('Leg Length Discrepancy'),
('Lesion of Ulnar Nerve'),
('Leukemia of Bone'),
('Lipoma'),
('Low back pain'),
('Lumber Vertebrae Fracture'),
('Lymphoma of Bone'),
('Mal-union'),
('Metacarpal bones fracture'),
('Metatarsal bones fracture'),
('Necrotizing fascitis'),
('Osteoarthrosis'),
('Osteomyelitis'),
('Osteoporosis'),
('Patella fracture'),
('Phalanges fracture'),
('posterior cruciate ligament'),
('Pressure sore'),
('Radial fracture'),
('Rib frcature'),
('Scapula fracture'),
('Sciatica'),
('Shoulder dislocation'),
('Sleep Apnea Syndrome'),
('Snake bite'),
('Spinal cord injury'),
('Splint thickness skin graft'),
('Spondylitis'),
('Spondylolisthesis'),
('Spondylosis'),
('Sprain'),
('Stenosis'),
('Tibia fracture'),
('Ulna fracture'),
('Valgus'),
('zzz');

-- --------------------------------------------------------

--
-- 資料表結構 `doctor`
--

CREATE TABLE `doctor` (
  `id` int(11) NOT NULL,
  `dr_ID` varchar(18) NOT NULL,
  `dr_password` varchar(18) NOT NULL,
  `role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `doctor`
--

INSERT INTO `doctor` (`id`, `dr_ID`, `dr_password`, `role`) VALUES
(1, 'aaaa', 'aaa', 0),
(2, 'bbbb', 'bbb', 1);

-- --------------------------------------------------------

--
-- 資料表結構 `drdata`
--

CREATE TABLE `drdata` (
  `id` int(11) NOT NULL,
  `dr_name` varchar(18) NOT NULL,
  `dr_ID` varchar(18) NOT NULL,
  `profi` varchar(18) NOT NULL DEFAULT 'default.png'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `drdata`
--

INSERT INTO `drdata` (`id`, `dr_name`, `dr_ID`, `profi`) VALUES
(1, '柯文哲', 'aaaa', 'default.png'),
(2, '個案師', 'bbbb', 'default.png');

-- --------------------------------------------------------

--
-- 資料表結構 `faq`
--

CREATE TABLE `faq` (
  `type` varchar(10) NOT NULL,
  `title` text NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `faq`
--

INSERT INTO `faq` (`type`, `title`, `content`) VALUES
('1', '護膝PART-認識護膝', '關於護膝，它的價值在於提升膝蓋關節的穩定度以避免運動傷害，護膝的限制性可以限制關節的活動度，讓你膝蓋彎曲角度不會太大，造成你膝關節過多的摩擦。'),
('1', '關於膝關節常發生的疾病-PART1', '1.關節疼痛，或是受壓迫時疼痛@#2.手指、腳趾及腕關節水腫@#3.關節僵硬及活動不便@#4.類風濕關節炎的早期症狀包括類似感冒的全身不適。'),
('1', '關於膝關節常發生的疾病-PART2', '說到內側摩擦症候群要先提到內側皺襞(ㄅㄧˋ)，內側皺襞是在胚胎發育過程中，殘留在膝關節滑膜腔中的皺襞，外型像是衣服皺褶，正常人膝關節平均每年彎曲多達百萬次，每次膝蓋彎曲、伸直時，內側皺襞與關節股骨內髁會互相摩擦而產生物理及化學破壞，隨著年齡增長，內側皺襞與股骨內髁摩擦達數千萬次，開始慢慢地造成纖維化，漸漸失去彈性而變硬，最後變成內側摩擦症候群。'),
('1', '關於膝關節常發生的疾病-PART3', '類風濕性關節炎屬於免疫系統疾病，病因可能與荷爾蒙、遺傳或環境因素有關。類風濕性關節炎可能發生在任何年齡層，由於免疫系統失調，導致關節軟骨受侵蝕與破壞，患者會在一段時間後感受到症狀好轉，但又可能在無預警狀況下突然發作。'),
('1', '關於膝關節常發生的疾病-PART4', '主要好發於中老年齡層，病因來自長期的關節磨損及發炎，或是早年的關節傷害。退化性膝關節炎通常發作於四、五十歲，而隨著年齡成長，症狀會逐漸加劇。六十五歲以上的老年人，開始出現退化性關節炎初期症狀者，約超過總人數的一半。提醒:醫生也常常會把退化性膝關節炎跟內側摩擦症候群搞混，因此就診時，要請醫生特別判斷是否是哪一症狀。'),
('1', '膝蓋保養', '1.避免提抬或背重物。@#2.盡量少蹲著做事。@#3.避免過度爬上爬下，容易造成嚴重摩擦膝蓋。@#4.避免長時間維持一固定姿勢，容易造成膝蓋僵硬或腫脹，應常常稍微動動做柔軟操。@#5.穿戴護具可以保護關節，降低關節受力及保暖。@#6.肌力訓練。@#7.規律的休閒運動習慣，避免跑跳的劇烈運動，以有氧運動最佳。建議每週做3～5次，每次30～60分鐘。散步、土風舞、交際舞、游泳、騎腳踏車、太極拳等，都是對有退化性關節炎患者不錯的運動。慢跑對關節受力較大比較不適合。');

-- --------------------------------------------------------

--
-- 資料表結構 `medical_order`
--

CREATE TABLE `medical_order` (
  `id` int(11) NOT NULL,
  `P_code` varchar(18) NOT NULL,
  `dr_ID` varchar(18) NOT NULL,
  `date` date NOT NULL,
  `content` text NOT NULL,
  `m_order` text NOT NULL,
  `remark` varchar(18) NOT NULL,
  `visit_back_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `medical_order`
--

INSERT INTO `medical_order` (`id`, `P_code`, `dr_ID`, `date`, `content`, `m_order`, `remark`, `visit_back_date`) VALUES
(4, 'tes001', 'aaaa', '2019-09-07', '1,60,5-3,20,5', '', '', '2019-09-08'),
(5, 'tes001', 'aaaa', '2019-09-09', '2,120,3', '', '', '2019-09-10'),
(8, 'YWFh', 'aaaa', '2019-09-18', '1,20,5', 'aaaaa', 'ssssss', '2019-09-01'),
(12, 'YWFh', 'aaaa', '2019-09-18', '1,20,5', 'aaaaa', 'ffff', '2019-09-16'),
(13, 'YWFh', 'aaaa', '2019-09-18', '1,20,5', 'aaaaa', 'Sam268', '2019-09-30'),
(14, 'YWFh', 'aaaa', '2019-09-18', '1,20,5', 'aaaaa', 'aaaa', '2019-09-10'),
(15, 'YWFh', 'aaaa', '2019-09-18', '1,20,5', 'aaaaa', 'aaaa', '2019-09-10'),
(16, 'YWFh', 'aaaa', '2019-09-18', '1,20,5', 'aaaaa', '55', '2019-09-21'),
(17, 'YWFh', 'aaaa', '2019-09-20', '1,20,5', 'aaaaa', '0920 aaaa', '2019-09-01'),
(18, 'YWFh', 'aaaa', '2019-09-20', '1,20,5', 'Acetabular Fractur', 'frq', '2019-09-21'),
(19, 'YWFh', 'aaaa', '2019-09-20', '1,20,5', 'Acetabular Fractur', 'gq', '2019-09-15'),
(20, 'YWFh', 'aaaa', '2019-09-20', '1,20,5', 'Acetabular Dysplas', 'ddd', '2019-09-13'),
(21, 'YWFh', 'aaaa', '2019-09-20', '1,20,5', 'Acetabular vascula', 'gfe', '2019-09-01'),
(22, 'YWFh', 'aaaa', '2019-09-20', '1,20,5', 'Acetabular Dysplasia!Acetabular Fracture!Achilles Tendon Rupture!Acromio-Clavical Joint Dislocation', 'rfe', '2019-09-09'),
(23, 'YWFh', 'aaaa', '2019-09-20', '1,20,5', 'Barton\'s Fracture,Benign prostatic hypertrophy,Bone Cyst,Bone Tumor', 'fad', '2019-09-01'),
(24, 'YWFh', 'aaaa', '2019-09-23', '2,4,1-4,2,1', 'Abrasion wound,Acetabular Dysplasia', 'aaaa', '2019-09-08'),
(27, 'tes002', 'aaaa', '2019-09-23', '2,4,1-4,2,1', 'Abrasion wound,Acetabular Dysplasia', 'aaaa', '2019-09-08'),
(28, 'tes003', 'aaaa', '2019-09-23', '2,4,1-4,2,1', 'Abrasion wound,Acetabular Dysplasia', 'aaaa', '2019-09-08'),
(29, 'tes004', 'aaaa', '2019-09-23', '2,4,1-4,2,1', 'Abrasion wound,Acetabular Dysplasia', 'aaaa', '2019-09-08'),
(30, 'tes005', 'aaaa', '2019-09-23', '2,4,1-4,2,1', 'Abrasion wound,Acetabular Dysplasia', 'aaaa', '2019-09-08'),
(31, 'tes002', 'aaaa', '2019-09-25', '1,60,5-3,20,5', 'Abrasion wound,Acetabular Dysplasia', '1232', '2019-09-30'),
(34, 'tes001', 'aaaa', '2019-09-13', '1,60,5-3,20,5', '', '', '2019-09-14'),
(35, 'tes001', 'aaaa', '2019-09-15', '1,60,5-2,30,10-3,60,10', '', '', '2019-09-16'),
(36, 'tes001', 'aaaa', '2019-09-25', '1,60,5-3,20,5', 'Acetabular Dysplasia,Acetabular Fracture,Acetabular vascular necrosis', '3', '2019-09-25'),
(37, 'tes001', 'aaaa', '2019-09-25', '1,60,5-2,30,10-3,60,10', 'Acetabular Dysplasia,Acetabular Fracture,Acromio-Clavical Joint Dislocation', '41213123', '2019-09-08'),
(38, 'tes001', 'aaaa', '2019-09-25', '1,60,5-2,30,10', '', '41213123', '2019-09-30'),
(39, 'tes001', 'aaaa', '2019-09-25', '1,60,5-3,60,10', 'Acetabular Dysplasia,Acetabular Fracture,Acromio-Clavical Joint Dislocation', '41213123', '2019-09-08'),
(43, 'tes001', 'aaaa', '2019-10-31', '1,60,5', '', '', '2019-11-01');

-- --------------------------------------------------------

--
-- 資料表結構 `patient`
--

CREATE TABLE `patient` (
  `id` int(11) NOT NULL,
  `P_ID` varchar(18) NOT NULL,
  `P_code` varchar(18) NOT NULL,
  `P_password` varchar(18) NOT NULL,
  `dr_ID` varchar(18) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `patient`
--

INSERT INTO `patient` (`id`, `P_ID`, `P_code`, `P_password`, `dr_ID`) VALUES
(1, '1', 'tes001', '1', 'aaaa'),
(2, 'test002', 'tes002', 'test002', 'aaaa'),
(3, 'test003', 'tes003', 'test003', 'aaaa'),
(4, 'test004', 'tes004', 'test004', 'aaaa'),
(5, 'test005', 'tes005', 'test005', 'aaaa'),
(6, 'test006', 'tes006', 'test006', 'aaaa'),
(7, 'test007', 'tes007', 'test007', 'aaaa'),
(8, 'test008', 'tes008', 'test008', 'aaaa'),
(9, 'test009', 'tes009', 'test009', 'aaaa'),
(10, 'test010', 'tes010', 'test010', 'aaaa'),
(11, 'test011', 'tes011', 'test011', 'aaaa'),
(12, 'test012', 'tes012', 'test012', 'aaaa'),
(13, 'test013', 'tes013', 'test013', 'aaaa'),
(14, 'test014', 'tes014', 'test014', 'aaaa'),
(15, 'test015', 'tes015', 'test015', 'aaaa'),
(16, 'test016', 'tes016', 'test016', 'aaaa'),
(17, 'test017', 'tes017', 'test017', 'aaaa'),
(18, 'test018', 'tes018', 'test018', 'aaaa'),
(19, 'test019', 'tes019', 'test019', 'aaaa'),
(20, 'test020', 'tes020', 'test020', 'aaaa'),
(21, 'test021', 'tes021', 'test021', 'aaaa'),
(22, 'test022', 'tes022', 'test022', 'aaaa'),
(23, 'test023', 'tes023', 'test023', 'aaaa'),
(24, 'test024', 'tes024', 'test024', 'aaaa'),
(25, 'test025', 'tes025', 'test025', 'aaaa'),
(26, 'test026', 'tes026', 'test026', 'aaaa'),
(27, 'test027', 'tes027', 'test027', 'aaaa'),
(28, 'test028', 'tes028', 'test028', 'aaaa'),
(29, 'test029', 'tes029', 'test029', 'aaaa'),
(30, 'test030', 'tes030', 'test030', 'aaaa'),
(31, 'test031', 'tes031', 'test031', 'aaaa'),
(32, 'test032', 'tes032', 'test032', 'aaaa'),
(33, 'test033', 'tes033', 'test033', 'aaaa'),
(34, 'test034', 'tes034', 'test034', 'aaaa'),
(35, 'test035', 'tes035', 'test035', 'aaaa'),
(36, 'test036', 'tes036', 'test036', 'aaaa'),
(37, 'test037', 'tes037', 'test037', 'aaaa'),
(38, 'test038', 'tes038', 'test038', 'aaaa'),
(39, 'test039', 'tes039', 'test039', 'aaaa'),
(40, 'test040', 'tes040', 'test040', 'aaaa'),
(41, 'test041', 'tes041', 'test041', 'aaaa'),
(42, 'test042', 'tes042', 'test042', 'aaaa'),
(43, 'test043', 'tes043', 'test043', 'aaaa'),
(44, 'test044', 'tes044', 'test044', 'aaaa'),
(45, 'test045', 'tes045', 'test045', 'aaaa'),
(46, 'test046', 'tes046', 'test046', 'aaaa'),
(47, 'test047', 'tes047', 'test047', 'aaaa'),
(48, 'test048', 'tes048', 'test048', 'aaaa'),
(49, 'test049', 'tes049', 'test049', 'aaaa'),
(50, 'test050', 'tes050', 'test050', 'aaaa'),
(51, 'test051', 'tes051', 'test051', 'aaaa'),
(52, 'test052', 'tes052', 'test052', 'aaaa'),
(53, 'test053', 'tes053', 'test053', 'aaaa'),
(54, 'test054', 'tes054', 'test054', 'aaaa'),
(55, 'test055', 'tes055', 'test055', 'aaaa'),
(56, 'test056', 'tes056', 'test056', 'aaaa'),
(57, 'test057', 'tes057', 'test057', 'aaaa'),
(58, 'test058', 'tes058', 'test058', 'aaaa'),
(59, 'test059', 'tes059', 'test059', 'aaaa'),
(60, 'test060', 'tes060', 'test060', 'aaaa'),
(61, 'test061', 'tes061', 'test061', 'aaaa'),
(62, 'test062', 'tes062', 'test062', 'aaaa'),
(63, 'test063', 'tes063', 'test063', 'aaaa'),
(64, 'test064', 'tes064', 'test064', 'aaaa'),
(65, 'test065', 'tes065', 'test065', 'aaaa'),
(66, 'test066', 'tes066', 'test066', 'aaaa'),
(67, 'test067', 'tes067', 'test067', 'aaaa'),
(68, 'test068', 'tes068', 'test068', 'aaaa'),
(69, 'test069', 'tes069', 'test069', 'aaaa'),
(70, 'test070', 'tes070', 'test070', 'aaaa'),
(71, 'test071', 'tes071', 'test071', 'aaaa'),
(72, 'test072', 'tes072', 'test072', 'aaaa'),
(73, 'test073', 'tes073', 'test073', 'aaaa'),
(74, 'test074', 'tes074', 'test074', 'aaaa'),
(75, 'test075', 'tes075', 'test075', 'aaaa'),
(76, 'test076', 'tes076', 'test076', 'aaaa'),
(77, 'test077', 'tes077', 'test077', 'aaaa'),
(78, 'test078', 'tes078', 'test078', 'aaaa'),
(79, 'test079', 'tes079', 'test079', 'aaaa'),
(80, 'test080', 'tes080', 'test080', 'aaaa'),
(81, 'test081', 'tes081', 'test081', 'aaaa'),
(82, 'test082', 'tes082', 'test082', 'aaaa'),
(83, 'test083', 'tes083', 'test083', 'aaaa'),
(84, 'test084', 'tes084', 'test084', 'aaaa'),
(85, 'test085', 'tes085', 'test085', 'aaaa'),
(86, 'test086', 'tes086', 'test086', 'aaaa'),
(87, 'test087', 'tes087', 'test087', 'aaaa'),
(88, 'test088', 'tes088', 'test088', 'aaaa'),
(89, 'test089', 'tes089', 'test089', 'aaaa'),
(90, 'test090', 'tes090', 'test090', 'aaaa'),
(91, 'test091', 'tes091', 'test091', 'aaaa'),
(92, 'test092', 'tes092', 'test092', 'aaaa'),
(93, 'test093', 'tes093', 'test093', 'aaaa'),
(94, 'test094', 'tes094', 'test094', 'aaaa'),
(95, 'test095', 'tes095', 'test095', 'aaaa'),
(96, 'test096', 'tes096', 'test096', 'aaaa'),
(97, 'test097', 'tes097', 'test097', 'aaaa'),
(98, 'test098', 'tes098', 'test098', 'aaaa'),
(99, 'test099', 'tes099', 'test099', 'aaaa'),
(100, 'test100', 'tes100', 'test100', 'aaaa'),
(101, 'test101', 'tes101', 'test101', 'aaaa'),
(102, 'test102', 'tes102', 'test102', 'aaaa'),
(103, 'test103', 'tes103', 'test103', 'aaaa'),
(104, 'test104', 'tes104', 'test104', 'aaaa'),
(105, 'test105', 'tes105', 'test105', 'aaaa'),
(106, 'test106', 'tes106', 'test106', 'aaaa'),
(107, 'test107', 'tes107', 'test107', 'aaaa'),
(108, 'test108', 'tes108', 'test108', 'aaaa'),
(109, 'test109', 'tes109', 'test109', 'aaaa'),
(110, 'test110', 'tes110', 'test110', 'aaaa'),
(111, 'test111', 'tes111', 'test111', 'aaaa'),
(112, 'test112', 'tes112', 'test112', 'aaaa'),
(113, 'test113', 'tes113', 'test113', 'aaaa'),
(114, 'test114', 'tes114', 'test114', 'aaaa'),
(115, 'test115', 'tes115', 'test115', 'aaaa'),
(116, 'test116', 'tes116', 'test116', 'aaaa'),
(117, 'test117', 'tes117', 'test117', 'aaaa'),
(118, 'test118', 'tes118', 'test118', 'aaaa'),
(119, 'test119', 'tes119', 'test119', 'aaaa'),
(120, 'test120', 'tes120', 'test120', 'aaaa'),
(121, 'test121', 'tes121', 'test121', 'aaaa'),
(122, 'test122', 'tes122', 'test122', 'aaaa'),
(123, 'test123', 'tes123', 'test123', 'aaaa'),
(124, 'test124', 'tes124', 'test124', 'aaaa'),
(125, 'test125', 'tes125', 'test125', 'aaaa'),
(126, 'test126', 'tes126', 'test126', 'aaaa'),
(127, 'test127', 'tes127', 'test127', 'aaaa'),
(128, 'test128', 'tes128', 'test128', 'aaaa'),
(129, 'test129', 'tes129', 'test129', 'aaaa'),
(130, 'test130', 'tes130', 'test130', 'aaaa'),
(131, 'test131', 'tes131', 'test131', 'aaaa'),
(132, 'test132', 'tes132', 'test132', 'aaaa'),
(133, 'test133', 'tes133', 'test133', 'aaaa'),
(134, 'test134', 'tes134', 'test134', 'aaaa'),
(135, 'test135', 'tes135', 'test135', 'aaaa'),
(136, 'test136', 'tes136', 'test136', 'aaaa'),
(137, 'test137', 'tes137', 'test137', 'aaaa'),
(138, 'test138', 'tes138', 'test138', 'aaaa'),
(139, 'test139', 'tes139', 'test139', 'aaaa'),
(140, 'test140', 'tes140', 'test140', 'aaaa'),
(141, 'test141', 'tes141', 'test141', 'aaaa'),
(142, 'test142', 'tes142', 'test142', 'aaaa'),
(143, 'test143', 'tes143', 'test143', 'aaaa'),
(144, 'test144', 'tes144', 'test144', 'aaaa'),
(145, 'test145', 'tes145', 'test145', 'aaaa'),
(146, 'test146', 'tes146', 'test146', 'aaaa'),
(147, 'test147', 'tes147', 'test147', 'aaaa'),
(148, 'test148', 'tes148', 'test148', 'aaaa'),
(149, 'test149', 'tes149', 'test149', 'aaaa'),
(150, 'test150', 'tes150', 'test150', 'aaaa'),
(151, 'test151', 'tes151', 'test151', 'aaaa'),
(152, 'test152', 'tes152', 'test152', 'aaaa'),
(153, 'test153', 'tes153', 'test153', 'aaaa'),
(154, 'test154', 'tes154', 'test154', 'aaaa'),
(155, 'test155', 'tes155', 'test155', 'aaaa'),
(156, 'test156', 'tes156', 'test156', 'aaaa'),
(157, 'test157', 'tes157', 'test157', 'aaaa'),
(158, 'test158', 'tes158', 'test158', 'aaaa'),
(159, 'test159', 'tes159', 'test159', 'aaaa'),
(160, 'test160', 'tes160', 'test160', 'aaaa'),
(161, 'test161', 'tes161', 'test161', 'aaaa'),
(162, 'test162', 'tes162', 'test162', 'aaaa'),
(163, 'test163', 'tes163', 'test163', 'aaaa'),
(164, 'test164', 'tes164', 'test164', 'aaaa'),
(165, 'test165', 'tes165', 'test165', 'aaaa'),
(166, 'test166', 'tes166', 'test166', 'aaaa'),
(167, 'test167', 'tes167', 'test167', 'aaaa'),
(168, 'test168', 'tes168', 'test168', 'aaaa'),
(169, 'test169', 'tes169', 'test169', 'aaaa'),
(170, 'test170', 'tes170', 'test170', 'aaaa'),
(171, 'test171', 'tes171', 'test171', 'aaaa'),
(172, 'test172', 'tes172', 'test172', 'aaaa'),
(173, 'test173', 'tes173', 'test173', 'aaaa'),
(174, 'test174', 'tes174', 'test174', 'aaaa'),
(175, 'test175', 'tes175', 'test175', 'aaaa'),
(176, 'test176', 'tes176', 'test176', 'aaaa'),
(177, 'test177', 'tes177', 'test177', 'aaaa'),
(178, 'test178', 'tes178', 'test178', 'aaaa'),
(179, 'test179', 'tes179', 'test179', 'aaaa'),
(180, 'test180', 'tes180', 'test180', 'aaaa'),
(181, 'test181', 'tes181', 'test181', 'aaaa'),
(182, 'test182', 'tes182', 'test182', 'aaaa'),
(183, 'test183', 'tes183', 'test183', 'aaaa'),
(184, 'test184', 'tes184', 'test184', 'aaaa'),
(185, 'test185', 'tes185', 'test185', 'aaaa'),
(186, 'test186', 'tes186', 'test186', 'aaaa'),
(187, 'test187', 'tes187', 'test187', 'aaaa'),
(188, 'test188', 'tes188', 'test188', 'aaaa'),
(189, 'test189', 'tes189', 'test189', 'aaaa'),
(190, 'test190', 'tes190', 'test190', 'aaaa'),
(191, 'test191', 'tes191', 'test191', 'aaaa'),
(192, 'test192', 'tes192', 'test192', 'aaaa'),
(193, 'test193', 'tes193', 'test193', 'aaaa'),
(194, 'test194', 'tes194', 'test194', 'aaaa'),
(195, 'test195', 'tes195', 'test195', 'aaaa'),
(196, 'test196', 'tes196', 'test196', 'aaaa'),
(197, 'test197', 'tes197', 'test197', 'aaaa'),
(198, 'test198', 'tes198', 'test198', 'aaaa'),
(199, 'test199', 'tes199', 'test199', 'aaaa'),
(200, 'test200', 'tes200', 'test200', 'aaaa'),
(201, 'test201', 'tes201', 'test201', 'aaaa'),
(202, 'test202', 'tes202', 'test202', 'aaaa'),
(203, 'test203', 'tes203', 'test203', 'aaaa'),
(204, 'test204', 'tes204', 'test204', 'aaaa'),
(205, 'test205', 'tes205', 'test205', 'aaaa'),
(206, 'test206', 'tes206', 'test206', 'aaaa'),
(207, 'test207', 'tes207', 'test207', 'aaaa'),
(208, 'test208', 'tes208', 'test208', 'aaaa'),
(209, 'test209', 'tes209', 'test209', 'aaaa'),
(210, 'test210', 'tes210', 'test210', 'aaaa'),
(211, 'test211', 'tes211', 'test211', 'aaaa'),
(212, 'test212', 'tes212', 'test212', 'aaaa'),
(213, 'test213', 'tes213', 'test213', 'aaaa'),
(214, 'test214', 'tes214', 'test214', 'aaaa'),
(215, 'test215', 'tes215', 'test215', 'aaaa'),
(216, 'test216', 'tes216', 'test216', 'aaaa'),
(217, 'test217', 'tes217', 'test217', 'aaaa'),
(218, 'test218', 'tes218', 'test218', 'aaaa'),
(219, 'test219', 'tes219', 'test219', 'aaaa'),
(220, 'test220', 'tes220', 'test220', 'aaaa'),
(221, 'test221', 'tes221', 'test221', 'aaaa'),
(222, 'test222', 'tes222', 'test222', 'aaaa'),
(223, 'test223', 'tes223', 'test223', 'aaaa'),
(224, 'test224', 'tes224', 'test224', 'aaaa'),
(225, 'test225', 'tes225', 'test225', 'aaaa'),
(226, 'test226', 'tes226', 'test226', 'aaaa'),
(227, 'test227', 'tes227', 'test227', 'aaaa'),
(228, 'test228', 'tes228', 'test228', 'aaaa'),
(229, 'test229', 'tes229', 'test229', 'aaaa'),
(230, 'test230', 'tes230', 'test230', 'aaaa'),
(231, 'test231', 'tes231', 'test231', 'aaaa'),
(232, 'test232', 'tes232', 'test232', 'aaaa'),
(233, 'test233', 'tes233', 'test233', 'aaaa'),
(234, 'test234', 'tes234', 'test234', 'aaaa'),
(235, 'test235', 'tes235', 'test235', 'aaaa'),
(236, 'test236', 'tes236', 'test236', 'aaaa'),
(237, 'test237', 'tes237', 'test237', 'aaaa'),
(238, 'test238', 'tes238', 'test238', 'aaaa'),
(239, 'test239', 'tes239', 'test239', 'aaaa'),
(240, 'test240', 'tes240', 'test240', 'aaaa'),
(241, 'test241', 'tes241', 'test241', 'aaaa'),
(242, 'test242', 'tes242', 'test242', 'aaaa'),
(243, 'test243', 'tes243', 'test243', 'aaaa'),
(244, 'test244', 'tes244', 'test244', 'aaaa'),
(245, 'test245', 'tes245', 'test245', 'aaaa'),
(246, 'test246', 'tes246', 'test246', 'aaaa'),
(247, 'test247', 'tes247', 'test247', 'aaaa'),
(248, 'test248', 'tes248', 'test248', 'aaaa'),
(249, 'test249', 'tes249', 'test249', 'aaaa'),
(250, 'test250', 'tes250', 'test250', 'aaaa'),
(251, 'test251', 'tes251', 'test251', 'aaaa'),
(252, 'test252', 'tes252', 'test252', 'aaaa'),
(253, 'test253', 'tes253', 'test253', 'aaaa'),
(254, 'test254', 'tes254', 'test254', 'aaaa'),
(255, 'test255', 'tes255', 'test255', 'aaaa'),
(256, 'test256', 'tes256', 'test256', 'aaaa'),
(257, 'test257', 'tes257', 'test257', 'aaaa'),
(258, 'test258', 'tes258', 'test258', 'aaaa'),
(259, 'test259', 'tes259', 'test259', 'aaaa'),
(260, 'test260', 'tes260', 'test260', 'aaaa'),
(261, 'test261', 'tes261', 'test261', 'aaaa'),
(262, 'test262', 'tes262', 'test262', 'aaaa'),
(263, 'test263', 'tes263', 'test263', 'aaaa'),
(264, 'test264', 'tes264', 'test264', 'aaaa'),
(265, 'test265', 'tes265', 'test265', 'aaaa'),
(266, 'test266', 'tes266', 'test266', 'aaaa'),
(267, 'test267', 'tes267', 'test267', 'aaaa'),
(268, 'test268', 'tes268', 'test268', 'aaaa'),
(269, 'test269', 'tes269', 'test269', 'aaaa'),
(270, 'test270', 'tes270', 'test270', 'aaaa'),
(271, 'test271', 'tes271', 'test271', 'aaaa'),
(272, 'test272', 'tes272', 'test272', 'aaaa'),
(273, 'test273', 'tes273', 'test273', 'aaaa'),
(274, 'test274', 'tes274', 'test274', 'aaaa'),
(275, 'test275', 'tes275', 'test275', 'aaaa'),
(276, 'test276', 'tes276', 'test276', 'aaaa'),
(277, 'test277', 'tes277', 'test277', 'aaaa'),
(278, 'test278', 'tes278', 'test278', 'aaaa'),
(279, 'test279', 'tes279', 'test279', 'aaaa'),
(280, 'test280', 'tes280', 'test280', 'aaaa'),
(281, 'test281', 'tes281', 'test281', 'aaaa'),
(282, 'test282', 'tes282', 'test282', 'aaaa'),
(283, 'test283', 'tes283', 'test283', 'aaaa'),
(284, 'test284', 'tes284', 'test284', 'aaaa'),
(285, 'test285', 'tes285', 'test285', 'aaaa'),
(286, 'test286', 'tes286', 'test286', 'aaaa'),
(287, 'test287', 'tes287', 'test287', 'aaaa'),
(288, 'test288', 'tes288', 'test288', 'aaaa'),
(289, 'test289', 'tes289', 'test289', 'aaaa'),
(290, 'test290', 'tes290', 'test290', 'aaaa'),
(291, 'test291', 'tes291', 'test291', 'aaaa'),
(292, 'test292', 'tes292', 'test292', 'aaaa'),
(293, 'test293', 'tes293', 'test293', 'aaaa'),
(294, 'test294', 'tes294', 'test294', 'aaaa'),
(295, 'test295', 'tes295', 'test295', 'aaaa'),
(296, 'test296', 'tes296', 'test296', 'aaaa'),
(297, 'test297', 'tes297', 'test297', 'aaaa'),
(298, 'test298', 'tes298', 'test298', 'aaaa'),
(299, 'test299', 'tes299', 'test299', 'aaaa'),
(300, 'test300', 'tes300', 'test300', 'aaaa');

-- --------------------------------------------------------

--
-- 資料表結構 `pdata`
--

CREATE TABLE `pdata` (
  `id` int(11) NOT NULL,
  `P_name` varchar(18) NOT NULL,
  `P_birthday` date NOT NULL,
  `P_code` varchar(18) NOT NULL,
  `ID_code` varchar(18) NOT NULL,
  `profi` varchar(18) NOT NULL DEFAULT 'default.jpg',
  `email` varchar(50) NOT NULL,
  `verification_code` varchar(6) NOT NULL,
  `token` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `pdata`
--

INSERT INTO `pdata` (`id`, `P_name`, `P_birthday`, `P_code`, `ID_code`, `profi`, `email`, `verification_code`, `token`) VALUES
(1, '測試員001', '2019-06-03', 'tes001', 'A123456001', 'default.jpg', 'james0159@kimo.com', '857284', 'fPb_L54BUnM:APA91bGY-XULFXPQC60OqcaTxOqissMhGm1WPS7SP30plANOB3_pXkp_-5gcr2dd1dlLUPCq13Acl3QiCh4dSiYINGs6MkXqLNG_qOpLjKx7g8tNMR0NPL5J6D3OP0tU5TEsyGc1RxFh'),
(2, '測試員002', '2019-06-26', 'tes002', 'A123456002', 'default.jpg', '', '', ''),
(3, '測試員003', '2019-06-26', 'tes003', 'A123456003', 'default.jpg', '', '', ''),
(4, '測試員004', '2019-06-26', 'tes004', 'A123456004', 'default.jpg', '', '', ''),
(5, '測試員005', '2019-06-26', 'tes005', 'A123456005', 'default.jpg', '', '', ''),
(6, '測試員006', '2019-06-26', 'tes006', 'A123456006', 'default.jpg', '', '', ''),
(7, '測試員007', '2019-06-26', 'tes007', 'A123456007', 'default.jpg', '', '', ''),
(8, '測試員008', '2019-06-26', 'tes008', 'A123456008', 'default.jpg', '', '', ''),
(9, '測試員009', '2019-06-26', 'tes009', 'A123456009', 'default.jpg', '', '', ''),
(10, '測試員010', '2019-06-26', 'tes010', 'A123456010', 'default.jpg', '', '', ''),
(11, '測試員011', '2019-06-26', 'tes011', 'A123456011', 'default.jpg', '', '', ''),
(12, '測試員012', '2019-06-26', 'tes012', 'A123456012', 'default.jpg', '', '', ''),
(13, '測試員013', '2019-06-26', 'tes013', 'A123456013', 'default.jpg', '', '', ''),
(14, '測試員014', '2019-06-26', 'tes014', 'A123456014', 'default.jpg', '', '', ''),
(15, '測試員015', '2019-06-26', 'tes015', 'A123456015', 'default.jpg', '', '', ''),
(16, '測試員016', '2019-06-26', 'tes016', 'A123456016', 'default.jpg', '', '', ''),
(17, '測試員017', '2019-06-26', 'tes017', 'A123456017', 'default.jpg', '', '', ''),
(18, '測試員018', '2019-06-26', 'tes018', 'A123456018', 'default.jpg', '', '', ''),
(19, '測試員019', '2019-06-26', 'tes019', 'A123456019', 'default.jpg', '', '', ''),
(20, '測試員020', '2019-06-26', 'tes020', 'A123456020', 'default.jpg', '', '', ''),
(21, '測試員021', '2019-06-26', 'tes021', 'A123456021', 'default.jpg', '', '', ''),
(22, ' 022a', '2019-06-26', 'tes022', 'A123456022', 'default.jpg', '', '', ''),
(23, '測試員023', '2019-06-26', 'tes023', 'A123456023', 'default.jpg', '', '', ''),
(24, '測試員024', '2019-06-26', 'tes024', 'A123456024', 'default.jpg', '', '', ''),
(25, '測試員025', '2019-06-26', 'tes025', 'A123456025', 'default.jpg', '', '', ''),
(26, '測試員026', '2019-06-26', 'tes026', 'A123456026', 'default.jpg', '', '', ''),
(27, '測試員027', '2019-06-26', 'tes027', 'A123456027', 'default.jpg', '', '', ''),
(28, '測試員028', '2019-06-26', 'tes028', 'A123456028', 'default.jpg', '', '', ''),
(29, '測試員029', '2019-06-26', 'tes029', 'A123456029', 'default.jpg', '', '', ''),
(30, '測試員030', '2019-06-26', 'tes030', 'A123456030', 'default.jpg', '', '', ''),
(31, '測試員031', '2019-06-26', 'tes031', 'A123456031', 'default.jpg', '', '', ''),
(32, '測試員032', '2019-06-26', 'tes032', 'A123456032', 'default.jpg', '', '', ''),
(33, '測試員033', '2019-06-26', 'tes033', 'A123456033', 'default.jpg', '', '', ''),
(34, '測試員034', '2019-06-26', 'tes034', 'A123456034', 'default.jpg', '', '', ''),
(35, '測試員035', '2019-06-26', 'tes035', 'A123456035', 'default.jpg', '', '', ''),
(36, '測試員036', '2019-06-26', 'tes036', 'A123456036', 'default.jpg', '', '', ''),
(37, '測試員037', '2019-06-26', 'tes037', 'A123456037', 'default.jpg', '', '', ''),
(38, '測試員038', '2019-06-26', 'tes038', 'A123456038', 'default.jpg', '', '', ''),
(39, '測試員039', '2019-06-26', 'tes039', 'A123456039', 'default.jpg', '', '', ''),
(40, '測試員040', '2019-06-26', 'tes040', 'A123456040', 'default.jpg', '', '', ''),
(41, '測試員041', '2019-06-26', 'tes041', 'A123456041', 'default.jpg', '', '', ''),
(42, '測試員042', '2019-06-26', 'tes042', 'A123456042', 'default.jpg', '', '', ''),
(43, '測試員043', '2019-06-26', 'tes043', 'A123456043', 'default.jpg', '', '', ''),
(44, '測試員044', '2019-06-26', 'tes044', 'A123456044', 'default.jpg', '', '', ''),
(45, '測試員045', '2019-06-26', 'tes045', 'A123456045', 'default.jpg', '', '', ''),
(46, '測試員046', '2019-06-26', 'tes046', 'A123456046', 'default.jpg', '', '', ''),
(47, '測試員047', '2019-06-26', 'tes047', 'A123456047', 'default.jpg', '', '', ''),
(48, '測試員048', '2019-06-26', 'tes048', 'A123456048', 'default.jpg', '', '', ''),
(49, '測試員049', '2019-06-26', 'tes049', 'A123456049', 'default.jpg', '', '', ''),
(50, '測試員050', '2019-06-26', 'tes050', 'A123456050', 'default.jpg', '', '', ''),
(51, '測試員051', '2019-06-26', 'tes051', 'A123456051', 'default.jpg', '', '', ''),
(52, '測試員052', '2019-06-26', 'tes052', 'A123456052', 'default.jpg', '', '', ''),
(53, '測試員053', '2019-06-26', 'tes053', 'A123456053', 'default.jpg', '', '', ''),
(54, '測試員054', '2019-06-26', 'tes054', 'A123456054', 'default.jpg', '', '', ''),
(55, '測試員055', '2019-06-26', 'tes055', 'A123456055', 'default.jpg', '', '', ''),
(56, '測試員056', '2019-06-26', 'tes056', 'A123456056', 'default.jpg', '', '', ''),
(57, '測試員057', '2019-06-26', 'tes057', 'A123456057', 'default.jpg', '', '', ''),
(58, '測試員058', '2019-06-26', 'tes058', 'A123456058', 'default.jpg', '', '', ''),
(59, '測試員059', '2019-06-26', 'tes059', 'A123456059', 'default.jpg', '', '', ''),
(60, '測試員060', '2019-06-26', 'tes060', 'A123456060', 'default.jpg', '', '', ''),
(61, '測試員061', '2019-06-26', 'tes061', 'A123456061', 'default.jpg', '', '', ''),
(62, '測試員062', '2019-06-26', 'tes062', 'A123456062', 'default.jpg', '', '', ''),
(63, '測試員063', '2019-06-26', 'tes063', 'A123456063', 'default.jpg', '', '', ''),
(64, '測試員064', '2019-06-26', 'tes064', 'A123456064', 'default.jpg', '', '', ''),
(65, '測試員065', '2019-06-26', 'tes065', 'A123456065', 'default.jpg', '', '', ''),
(66, '測試員066', '2019-06-26', 'tes066', 'A123456066', 'default.jpg', '', '', ''),
(67, '測試員067', '2019-06-26', 'tes067', 'A123456067', 'default.jpg', '', '', ''),
(68, '測試員068', '2019-06-26', 'tes068', 'A123456068', 'default.jpg', '', '', ''),
(69, '測試員069', '2019-06-26', 'tes069', 'A123456069', 'default.jpg', '', '', ''),
(70, '測試員070', '2019-06-26', 'tes070', 'A123456070', 'default.jpg', '', '', ''),
(71, '測試員071', '2019-06-26', 'tes071', 'A123456071', 'default.jpg', '', '', ''),
(72, '測試員072', '2019-06-26', 'tes072', 'A123456072', 'default.jpg', '', '', ''),
(73, '測試員073', '2019-06-26', 'tes073', 'A123456073', 'default.jpg', '', '', ''),
(74, '測試員074', '2019-06-26', 'tes074', 'A123456074', 'default.jpg', '', '', ''),
(75, '測試員075', '2019-06-26', 'tes075', 'A123456075', 'default.jpg', '', '', ''),
(76, '測試員076', '2019-06-26', 'tes076', 'A123456076', 'default.jpg', '', '', ''),
(77, '測試員077', '2019-06-26', 'tes077', 'A123456077', 'default.jpg', '', '', ''),
(78, '測試員078', '2019-06-26', 'tes078', 'A123456078', 'default.jpg', '', '', ''),
(79, '測試員079', '2019-06-26', 'tes079', 'A123456079', 'default.jpg', '', '', ''),
(80, '測試員080', '2019-06-26', 'tes080', 'A123456080', 'default.jpg', '', '', ''),
(81, '測試員081', '2019-06-26', 'tes081', 'A123456081', 'default.jpg', '', '', ''),
(82, '測試員082', '2019-06-26', 'tes082', 'A123456082', 'default.jpg', '', '', ''),
(83, '測試員083', '2019-06-26', 'tes083', 'A123456083', 'default.jpg', '', '', ''),
(84, '測試員084', '2019-06-26', 'tes084', 'A123456084', 'default.jpg', '', '', ''),
(85, '測試員085', '2019-06-26', 'tes085', 'A123456085', 'default.jpg', '', '', ''),
(86, '測試員086', '2019-06-26', 'tes086', 'A123456086', 'default.jpg', '', '', ''),
(87, '測試員087', '2019-06-26', 'tes087', 'A123456087', 'default.jpg', '', '', ''),
(88, '測試員088', '2019-06-26', 'tes088', 'A123456088', 'default.jpg', '', '', ''),
(89, '測試員089', '2019-06-26', 'tes089', 'A123456089', 'default.jpg', '', '', ''),
(90, '測試員090', '2019-06-26', 'tes090', 'A123456090', 'default.jpg', '', '', ''),
(91, '測試員091', '2019-06-26', 'tes091', 'A123456091', 'default.jpg', '', '', ''),
(92, '測試員092', '2019-06-26', 'tes092', 'A123456092', 'default.jpg', '', '', ''),
(93, '測試員093', '2019-06-26', 'tes093', 'A123456093', 'default.jpg', '', '', ''),
(94, '測試員094', '2019-06-26', 'tes094', 'A123456094', 'default.jpg', '', '', ''),
(95, '測試員095', '2019-06-26', 'tes095', 'A123456095', 'default.jpg', '', '', ''),
(96, '測試員096', '2019-06-26', 'tes096', 'A123456096', 'default.jpg', '', '', ''),
(97, '測試員097', '2019-06-26', 'tes097', 'A123456097', 'default.jpg', '', '', ''),
(98, '測試員098', '2019-06-26', 'tes098', 'A123456098', 'default.jpg', '', '', ''),
(99, '測試員099', '2019-06-26', 'tes099', 'A123456099', 'default.jpg', '', '', ''),
(100, '測試員100', '2019-06-26', 'tes100', 'A123456100', 'default.jpg', '', '', ''),
(101, '測試員101', '2019-06-26', 'tes101', 'A123456101', 'default.jpg', '', '', ''),
(102, '測試員102', '2019-06-26', 'tes102', 'A123456102', 'default.jpg', '', '', ''),
(103, '測試員103', '2019-06-26', 'tes103', 'A123456103', 'default.jpg', '', '', ''),
(104, '測試員104', '2019-06-26', 'tes104', 'A123456104', 'default.jpg', '', '', ''),
(105, '測試員105', '2019-06-26', 'tes105', 'A123456105', 'default.jpg', '', '', ''),
(106, '測試員106', '2019-06-26', 'tes106', 'A123456106', 'default.jpg', '', '', ''),
(107, '測試員107', '2019-06-26', 'tes107', 'A123456107', 'default.jpg', '', '', ''),
(108, '測試員108', '2019-06-26', 'tes108', 'A123456108', 'default.jpg', '', '', ''),
(109, '測試員109', '2019-06-26', 'tes109', 'A123456109', 'default.jpg', '', '', ''),
(110, '測試員110', '2019-06-26', 'tes110', 'A123456110', 'default.jpg', '', '', ''),
(111, '測試員111', '2019-06-26', 'tes111', 'A123456111', 'default.jpg', '', '', ''),
(112, '測試員112', '2019-06-26', 'tes112', 'A123456112', 'default.jpg', '', '', ''),
(113, '測試員113', '2019-06-26', 'tes113', 'A123456113', 'default.jpg', '', '', ''),
(114, '測試員114', '2019-06-26', 'tes114', 'A123456114', 'default.jpg', '', '', ''),
(115, '測試員115', '2019-06-26', 'tes115', 'A123456115', 'default.jpg', '', '', ''),
(116, '測試員116', '2019-06-26', 'tes116', 'A123456116', 'default.jpg', '', '', ''),
(117, '測試員117', '2019-06-26', 'tes117', 'A123456117', 'default.jpg', '', '', ''),
(118, '測試員118', '2019-06-26', 'tes118', 'A123456118', 'default.jpg', '', '', ''),
(119, '測試員119', '2019-06-26', 'tes119', 'A123456119', 'default.jpg', '', '', ''),
(120, '測試員120', '2019-06-26', 'tes120', 'A123456120', 'default.jpg', '', '', ''),
(121, '測試員121', '2019-06-26', 'tes121', 'A123456121', 'default.jpg', '', '', ''),
(122, '測試員122', '2019-06-26', 'tes122', 'A123456122', 'default.jpg', '', '', ''),
(123, '測試員123', '2019-06-26', 'tes123', 'A123456123', 'default.jpg', '', '', ''),
(124, '測試員124', '2019-06-26', 'tes124', 'A123456124', 'default.jpg', '', '', ''),
(125, '測試員125', '2019-06-26', 'tes125', 'A123456125', 'default.jpg', '', '', ''),
(126, '測試員126', '2019-06-26', 'tes126', 'A123456126', 'default.jpg', '', '', ''),
(127, '測試員127', '2019-06-26', 'tes127', 'A123456127', 'default.jpg', '', '', ''),
(128, '測試員128', '2019-06-26', 'tes128', 'A123456128', 'default.jpg', '', '', ''),
(129, '測試員129', '2019-06-26', 'tes129', 'A123456129', 'default.jpg', '', '', ''),
(130, '測試員130', '2019-06-26', 'tes130', 'A123456130', 'default.jpg', '', '', ''),
(131, '測試員131', '2019-06-26', 'tes131', 'A123456131', 'default.jpg', '', '', ''),
(132, '測試員132', '2019-06-26', 'tes132', 'A123456132', 'default.jpg', '', '', ''),
(133, '測試員133', '2019-06-26', 'tes133', 'A123456133', 'default.jpg', '', '', ''),
(134, '測試員134', '2019-06-26', 'tes134', 'A123456134', 'default.jpg', '', '', ''),
(135, '測試員135', '2019-06-26', 'tes135', 'A123456135', 'default.jpg', '', '', ''),
(136, '測試員136', '2019-06-26', 'tes136', 'A123456136', 'default.jpg', '', '', ''),
(137, '測試員137', '2019-06-26', 'tes137', 'A123456137', 'default.jpg', '', '', ''),
(138, '測試員138', '2019-06-26', 'tes138', 'A123456138', 'default.jpg', '', '', ''),
(139, '測試員139', '2019-06-26', 'tes139', 'A123456139', 'default.jpg', '', '', ''),
(140, '測試員140', '2019-06-26', 'tes140', 'A123456140', 'default.jpg', '', '', ''),
(141, '測試員141', '2019-06-26', 'tes141', 'A123456141', 'default.jpg', '', '', ''),
(142, '測試員142', '2019-06-26', 'tes142', 'A123456142', 'default.jpg', '', '', ''),
(143, '測試員143', '2019-06-26', 'tes143', 'A123456143', 'default.jpg', '', '', ''),
(144, '測試員144', '2019-06-26', 'tes144', 'A123456144', 'default.jpg', '', '', ''),
(145, '測試員145', '2019-06-26', 'tes145', 'A123456145', 'default.jpg', '', '', ''),
(146, '測試員146', '2019-06-26', 'tes146', 'A123456146', 'default.jpg', '', '', ''),
(147, '測試員147', '2019-06-26', 'tes147', 'A123456147', 'default.jpg', '', '', ''),
(148, '測試員148', '2019-06-26', 'tes148', 'A123456148', 'default.jpg', '', '', ''),
(149, '測試員149', '2019-06-26', 'tes149', 'A123456149', 'default.jpg', '', '', ''),
(150, '測試員150', '2019-06-26', 'tes150', 'A123456150', 'default.jpg', '', '', ''),
(151, '測試員151', '2019-06-26', 'tes151', 'A123456151', 'default.jpg', '', '', ''),
(152, '測試員152', '2019-06-26', 'tes152', 'A123456152', 'default.jpg', '', '', ''),
(153, '測試員153', '2019-06-26', 'tes153', 'A123456153', 'default.jpg', '', '', ''),
(154, '測試員154', '2019-06-26', 'tes154', 'A123456154', 'default.jpg', '', '', ''),
(155, '測試員155', '2019-06-26', 'tes155', 'A123456155', 'default.jpg', '', '', ''),
(156, '測試員156', '2019-06-26', 'tes156', 'A123456156', 'default.jpg', '', '', ''),
(157, '測試員157', '2019-06-26', 'tes157', 'A123456157', 'default.jpg', '', '', ''),
(158, '測試員158', '2019-06-26', 'tes158', 'A123456158', 'default.jpg', '', '', ''),
(159, '測試員159', '2019-06-26', 'tes159', 'A123456159', 'default.jpg', '', '', ''),
(160, '測試員160', '2019-06-26', 'tes160', 'A123456160', 'default.jpg', '', '', ''),
(161, '測試員161', '2019-06-26', 'tes161', 'A123456161', 'default.jpg', '', '', ''),
(162, '測試員162', '2019-06-26', 'tes162', 'A123456162', 'default.jpg', '', '', ''),
(163, '測試員163', '2019-06-26', 'tes163', 'A123456163', 'default.jpg', '', '', ''),
(164, '測試員164', '2019-06-26', 'tes164', 'A123456164', 'default.jpg', '', '', ''),
(165, '測試員165', '2019-06-26', 'tes165', 'A123456165', 'default.jpg', '', '', ''),
(166, '測試員166', '2019-06-26', 'tes166', 'A123456166', 'default.jpg', '', '', ''),
(167, '測試員167', '2019-06-26', 'tes167', 'A123456167', 'default.jpg', '', '', ''),
(168, '測試員168', '2019-06-26', 'tes168', 'A123456168', 'default.jpg', '', '', ''),
(169, '測試員169', '2019-06-26', 'tes169', 'A123456169', 'default.jpg', '', '', ''),
(170, '測試員170', '2019-06-26', 'tes170', 'A123456170', 'default.jpg', '', '', ''),
(171, '測試員171', '2019-06-26', 'tes171', 'A123456171', 'default.jpg', '', '', ''),
(172, '測試員172', '2019-06-26', 'tes172', 'A123456172', 'default.jpg', '', '', ''),
(173, '測試員173', '2019-06-26', 'tes173', 'A123456173', 'default.jpg', '', '', ''),
(174, '測試員174', '2019-06-26', 'tes174', 'A123456174', 'default.jpg', '', '', ''),
(175, '測試員175', '2019-06-26', 'tes175', 'A123456175', 'default.jpg', '', '', ''),
(176, '測試員176', '2019-06-26', 'tes176', 'A123456176', 'default.jpg', '', '', ''),
(177, '測試員177', '2019-06-26', 'tes177', 'A123456177', 'default.jpg', '', '', ''),
(178, '測試員178', '2019-06-26', 'tes178', 'A123456178', 'default.jpg', '', '', ''),
(179, '測試員179', '2019-06-26', 'tes179', 'A123456179', 'default.jpg', '', '', ''),
(180, '測試員180', '2019-06-26', 'tes180', 'A123456180', 'default.jpg', '', '', ''),
(181, '測試員181', '2019-06-26', 'tes181', 'A123456181', 'default.jpg', '', '', ''),
(182, '測試員182', '2019-06-26', 'tes182', 'A123456182', 'default.jpg', '', '', ''),
(183, '測試員183', '2019-06-26', 'tes183', 'A123456183', 'default.jpg', '', '', ''),
(184, '測試員184', '2019-06-26', 'tes184', 'A123456184', 'default.jpg', '', '', ''),
(185, '測試員185', '2019-06-26', 'tes185', 'A123456185', 'default.jpg', '', '', ''),
(186, '測試員186', '2019-06-26', 'tes186', 'A123456186', 'default.jpg', '', '', ''),
(187, '測試員187', '2019-06-26', 'tes187', 'A123456187', 'default.jpg', '', '', ''),
(188, '測試員188', '2019-06-26', 'tes188', 'A123456188', 'default.jpg', '', '', ''),
(189, '測試員189', '2019-06-26', 'tes189', 'A123456189', 'default.jpg', '', '', ''),
(190, '測試員190', '2019-06-26', 'tes190', 'A123456190', 'default.jpg', '', '', ''),
(191, '測試員191', '2019-06-26', 'tes191', 'A123456191', 'default.jpg', '', '', ''),
(192, '測試員192', '2019-06-26', 'tes192', 'A123456192', 'default.jpg', '', '', ''),
(193, '測試員193', '2019-06-26', 'tes193', 'A123456193', 'default.jpg', '', '', ''),
(194, '測試員194', '2019-06-26', 'tes194', 'A123456194', 'default.jpg', '', '', ''),
(195, '測試員195', '2019-06-26', 'tes195', 'A123456195', 'default.jpg', '', '', ''),
(196, '測試員196', '2019-06-26', 'tes196', 'A123456196', 'default.jpg', '', '', ''),
(197, '測試員197', '2019-06-26', 'tes197', 'A123456197', 'default.jpg', '', '', ''),
(198, '測試員198', '2019-06-26', 'tes198', 'A123456198', 'default.jpg', '', '', ''),
(199, '測試員199', '2019-06-26', 'tes199', 'A123456199', 'default.jpg', '', '', ''),
(200, '測試員200', '2019-06-26', 'tes200', 'A123456200', 'default.jpg', '', '', ''),
(201, '測試員201', '2019-06-26', 'tes201', 'A123456201', 'default.jpg', '', '', ''),
(202, '測試員202', '2019-06-26', 'tes202', 'A123456202', 'default.jpg', '', '', ''),
(203, '測試員203', '2019-06-26', 'tes203', 'A123456203', 'default.jpg', '', '', ''),
(204, '測試員204', '2019-06-26', 'tes204', 'A123456204', 'default.jpg', '', '', ''),
(205, '測試員205', '2019-06-26', 'tes205', 'A123456205', 'default.jpg', '', '', ''),
(206, '測試員206', '2019-06-26', 'tes206', 'A123456206', 'default.jpg', '', '', ''),
(207, '測試員207', '2019-06-26', 'tes207', 'A123456207', 'default.jpg', '', '', ''),
(208, '測試員208', '2019-06-26', 'tes208', 'A123456208', 'default.jpg', '', '', ''),
(209, '測試員209', '2019-06-26', 'tes209', 'A123456209', 'default.jpg', '', '', ''),
(210, '測試員210', '2019-06-26', 'tes210', 'A123456210', 'default.jpg', '', '', ''),
(211, '測試員211', '2019-06-26', 'tes211', 'A123456211', 'default.jpg', '', '', ''),
(212, '測試員212', '2019-06-26', 'tes212', 'A123456212', 'default.jpg', '', '', ''),
(213, '測試員213', '2019-06-26', 'tes213', 'A123456213', 'default.jpg', '', '', ''),
(214, '測試員214', '2019-06-26', 'tes214', 'A123456214', 'default.jpg', '', '', ''),
(215, '測試員215', '2019-06-26', 'tes215', 'A123456215', 'default.jpg', '', '', ''),
(216, '測試員216', '2019-06-26', 'tes216', 'A123456216', 'default.jpg', '', '', ''),
(217, '測試員217', '2019-06-26', 'tes217', 'A123456217', 'default.jpg', '', '', ''),
(218, '測試員218', '2019-06-26', 'tes218', 'A123456218', 'default.jpg', '', '', ''),
(219, '測試員219', '2019-06-26', 'tes219', 'A123456219', 'default.jpg', '', '', ''),
(220, '測試員220', '2019-06-26', 'tes220', 'A123456220', 'default.jpg', '', '', ''),
(221, '測試員221', '2019-06-26', 'tes221', 'A123456221', 'default.jpg', '', '', ''),
(222, '測試員222', '2019-06-26', 'tes222', 'A123456222', 'default.jpg', '', '', ''),
(223, '測試員223', '2019-06-26', 'tes223', 'A123456223', 'default.jpg', '', '', ''),
(224, '測試員224', '2019-06-26', 'tes224', 'A123456224', 'default.jpg', '', '', ''),
(225, '測試員225', '2019-06-26', 'tes225', 'A123456225', 'default.jpg', '', '', ''),
(226, '測試員226', '2019-06-26', 'tes226', 'A123456226', 'default.jpg', '', '', ''),
(227, '測試員227', '2019-06-26', 'tes227', 'A123456227', 'default.jpg', '', '', ''),
(228, '測試員228', '2019-06-26', 'tes228', 'A123456228', 'default.jpg', '', '', ''),
(229, '測試員229', '2019-06-26', 'tes229', 'A123456229', 'default.jpg', '', '', ''),
(230, '測試員230', '2019-06-26', 'tes230', 'A123456230', 'default.jpg', '', '', ''),
(231, '測試員231', '2019-06-26', 'tes231', 'A123456231', 'default.jpg', '', '', ''),
(232, '測試員232', '2019-06-26', 'tes232', 'A123456232', 'default.jpg', '', '', ''),
(233, '測試員233', '2019-06-26', 'tes233', 'A123456233', 'default.jpg', '', '', ''),
(234, '測試員234', '2019-06-26', 'tes234', 'A123456234', 'default.jpg', '', '', ''),
(235, '測試員235', '2019-06-26', 'tes235', 'A123456235', 'default.jpg', '', '', ''),
(236, '測試員236', '2019-06-26', 'tes236', 'A123456236', 'default.jpg', '', '', ''),
(237, '測試員237', '2019-06-26', 'tes237', 'A123456237', 'default.jpg', '', '', ''),
(238, '測試員238', '2019-06-26', 'tes238', 'A123456238', 'default.jpg', '', '', ''),
(239, '測試員239', '2019-06-26', 'tes239', 'A123456239', 'default.jpg', '', '', ''),
(240, '測試員240', '2019-06-26', 'tes240', 'A123456240', 'default.jpg', '', '', ''),
(241, '測試員241', '2019-06-26', 'tes241', 'A123456241', 'default.jpg', '', '', ''),
(242, '測試員242', '2019-06-26', 'tes242', 'A123456242', 'default.jpg', '', '', ''),
(243, '測試員243', '2019-06-26', 'tes243', 'A123456243', 'default.jpg', '', '', ''),
(244, '測試員244', '2019-06-26', 'tes244', 'A123456244', 'default.jpg', '', '', ''),
(245, '測試員245', '2019-06-26', 'tes245', 'A123456245', 'default.jpg', '', '', ''),
(246, '測試員246', '2019-06-26', 'tes246', 'A123456246', 'default.jpg', '', '', ''),
(247, '測試員247', '2019-06-26', 'tes247', 'A123456247', 'default.jpg', '', '', ''),
(248, '測試員248', '2019-06-26', 'tes248', 'A123456248', 'default.jpg', '', '', ''),
(249, '測試員249', '2019-06-26', 'tes249', 'A123456249', 'default.jpg', '', '', ''),
(250, '測試員250', '2019-06-26', 'tes250', 'A123456250', 'default.jpg', '', '', ''),
(251, '測試員251', '2019-06-26', 'tes251', 'A123456251', 'default.jpg', '', '', ''),
(252, '測試員252', '2019-06-26', 'tes252', 'A123456252', 'default.jpg', '', '', ''),
(253, '測試員253', '2019-06-26', 'tes253', 'A123456253', 'default.jpg', '', '', ''),
(254, '測試員254', '2019-06-26', 'tes254', 'A123456254', 'default.jpg', '', '', ''),
(255, '測試員255', '2019-06-26', 'tes255', 'A123456255', 'default.jpg', '', '', ''),
(256, '測試員256', '2019-06-26', 'tes256', 'A123456256', 'default.jpg', '', '', ''),
(257, '測試員257', '2019-06-26', 'tes257', 'A123456257', 'default.jpg', '', '', ''),
(258, '測試員258', '2019-06-26', 'tes258', 'A123456258', 'default.jpg', '', '', ''),
(259, '測試員259', '2019-06-26', 'tes259', 'A123456259', 'default.jpg', '', '', ''),
(260, '測試員260', '2019-06-26', 'tes260', 'A123456260', 'default.jpg', '', '', ''),
(261, '測試員261', '2019-06-26', 'tes261', 'A123456261', 'default.jpg', '', '', ''),
(262, '測試員262', '2019-06-26', 'tes262', 'A123456262', 'default.jpg', '', '', ''),
(263, '測試員263', '2019-06-26', 'tes263', 'A123456263', 'default.jpg', '', '', ''),
(264, '測試員264', '2019-06-26', 'tes264', 'A123456264', 'default.jpg', '', '', ''),
(265, '測試員265', '2019-06-26', 'tes265', 'A123456265', 'default.jpg', '', '', ''),
(266, '測試員266', '2019-06-26', 'tes266', 'A123456266', 'default.jpg', '', '', ''),
(267, '測試員267', '2019-06-26', 'tes267', 'A123456267', 'default.jpg', '', '', ''),
(268, '測試員268', '2019-06-26', 'tes268', 'A123456268', 'default.jpg', '', '', ''),
(269, '測試員269', '2019-06-26', 'tes269', 'A123456269', 'default.jpg', '', '', ''),
(270, '測試員270', '2019-06-26', 'tes270', 'A123456270', 'default.jpg', '', '', ''),
(271, '測試員271', '2019-06-26', 'tes271', 'A123456271', 'default.jpg', '', '', ''),
(272, '測試員272', '2019-06-26', 'tes272', 'A123456272', 'default.jpg', '', '', ''),
(273, '測試員273', '2019-06-26', 'tes273', 'A123456273', 'default.jpg', '', '', ''),
(274, '測試員274', '2019-06-26', 'tes274', 'A123456274', 'default.jpg', '', '', ''),
(275, '測試員275', '2019-06-26', 'tes275', 'A123456275', 'default.jpg', '', '', ''),
(276, '測試員276', '2019-06-26', 'tes276', 'A123456276', 'default.jpg', '', '', ''),
(277, '測試員277', '2019-06-26', 'tes277', 'A123456277', 'default.jpg', '', '', ''),
(278, '測試員278', '2019-06-26', 'tes278', 'A123456278', 'default.jpg', '', '', ''),
(279, '測試員279', '2019-06-26', 'tes279', 'A123456279', 'default.jpg', '', '', ''),
(280, '測試員280', '2019-06-26', 'tes280', 'A123456280', 'default.jpg', '', '', ''),
(281, '測試員281', '2019-06-26', 'tes281', 'A123456281', 'default.jpg', '', '', ''),
(282, '測試員282', '2019-06-26', 'tes282', 'A123456282', 'default.jpg', '', '', ''),
(283, '測試員283', '2019-06-26', 'tes283', 'A123456283', 'default.jpg', '', '', ''),
(284, '測試員284', '2019-06-26', 'tes284', 'A123456284', 'default.jpg', '', '', ''),
(285, '測試員285', '2019-06-26', 'tes285', 'A123456285', 'default.jpg', '', '', ''),
(286, '測試員286', '2019-06-26', 'tes286', 'A123456286', 'default.jpg', '', '', ''),
(287, '測試員287', '2019-06-26', 'tes287', 'A123456287', 'default.jpg', '', '', ''),
(288, '測試員288', '2019-06-26', 'tes288', 'A123456288', 'default.jpg', '', '', ''),
(289, '測試員289', '2019-06-26', 'tes289', 'A123456289', 'default.jpg', '', '', ''),
(290, '測試員290', '2019-06-26', 'tes290', 'A123456290', 'default.jpg', '', '', ''),
(291, '測試員291', '2019-06-26', 'tes291', 'A123456291', 'default.jpg', '', '', ''),
(292, '測試員292', '2019-06-26', 'tes292', 'A123456292', 'default.jpg', '', '', ''),
(293, '測試員293', '2019-06-26', 'tes293', 'A123456293', 'default.jpg', '', '', ''),
(294, '測試員294', '2019-06-26', 'tes294', 'A123456294', 'default.jpg', '', '', ''),
(295, '測試員295', '2019-06-26', 'tes295', 'A123456295', 'default.jpg', '', '', ''),
(296, '測試員296', '2019-06-26', 'tes296', 'A123456296', 'default.jpg', '', '', ''),
(297, '測試員297', '2019-06-26', 'tes297', 'A123456297', 'default.jpg', '', '', ''),
(298, '測試員298', '2019-06-26', 'tes298', 'A123456298', 'default.jpg', '', '', ''),
(299, '測試員299', '2019-06-26', 'tes299', 'A123456299', 'default.jpg', '', '', ''),
(300, '測試員300', '2019-06-26', 'tes300', 'A123456300', 'default.jpg', '', '', ''),
(310, 'sam1268', '1998-05-15', 'F21i3v9', 'F123456789', 'default.jpg', '', '', ''),
(311, 'sadasd', '1998-05-05', 'F21i3v9', 'F123456789', 'default.jpg', '', '', ''),
(312, 'asd', '1998-05-05', 'F21i3v9', 'F123456789', 'default.jpg', '', '', ''),
(313, 'asd', '1954-05-05', 'F21i3v9', 'F123456789', 'default.jpg', '', '', ''),
(314, 'as', '1998-05-05', 'F21i3v9', 'F123456789', 'default.jpg', '', '', ''),
(315, 'sam', '1998-05-05', 'F21i3v9', 'F123456789', 'default.jpg', '', '', ''),
(316, 'tttt', '1998-05-14', 'q21i3v9', 'q123456789', 'default.jpg', '', '', ''),
(317, 'qwg', '1887-05-14', 'z21i3vi', 'z123456798', 'default.jpg', '', '', ''),
(318, 'qwefha', '1854-05-14', 'e21i3v9', 'e123456789', 'default.jpg', '', '', ''),
(319, 'agsd', '1888-05-14', 'w21i3v9', 'w123456789', 'default.jpg', '', '', ''),
(320, 'gqdg', '2015-06-06', 'g21i3v9', 'g123456789', 'default.jpg', '', '', ''),
(321, 'asdadsad', '1587-05-05', 'g21i3v9', 'g123456789', 'default.jpg', '', '', ''),
(322, 'sss', '1857-05-31', 'h21i3v9', 'h123456789', 'default.jpg', '', '', ''),
(323, 'azdss', '1854-05-05', 'y21i3v9', 'y123456789', 'default.jpg', '', '', ''),
(324, 'jjj', '1854-05-05', 'n21i3v9', 'n123456789', 'default.jpg', '', '', ''),
(325, 'tywfdg', '1857-05-05', 'v21i3v9', 'v123456789', 'default.jpg', '', '', ''),
(326, 'gfredh', '1896-05-05', 'l21i3v9', 'l123456789', 'default.jpg', '', '', ''),
(327, 'jrj', '1855-05-05', 'z21i3v9', 'z123456789', 'default.jpg', '', '', ''),
(328, 'ggg', '1865-05-05', 'x21i3v9', 'x123456789', 'default.jpg', '', '', ''),
(329, 'a', '1958-05-07', 's21i3v9', 's123456789', 'default.jpg', '', '', ''),
(330, 'qasf', '1958-05-05', 'd21i3v9', 'd123456789', 'default.jpg', '', '', ''),
(331, '', '0000-00-00', '0', '', 'default.jpg', '', '', ''),
(332, '', '0000-00-00', '0', '', 'default.jpg', '', '', ''),
(333, '', '0000-00-00', '0', '', 'default.jpg', '', '', ''),
(334, '', '0000-00-00', '0', '', 'default.jpg', '', '', ''),
(335, '', '0000-00-00', '0', '', 'default.jpg', '', '', ''),
(336, '#name', '0000-00-00', '#0', '#id_code', 'default.jpg', '', '', ''),
(337, '#name', '0000-00-00', '#0', '#id_code', 'default.jpg', '', '', ''),
(338, 'hasf', '1854-05-05', 'H21i39d', 'H123456001', 'default.jpg', '', '', ''),
(339, 'test', '1111-11-11', 'r21i3v9', 'r123456789', 'default.jpg', '', '', ''),
(340, 'test', '1122-02-02', 'l21i3v9', 'l123456789', 'default.jpg', '', '', ''),
(341, 'sss', '2019-09-05', 'YWFhYQ==', 'aaaa', 'default.jpg', '', '', ''),
(342, 'fffff', '2019-09-11', 'MTExMTExMTExMTEx', '111111111111', 'default.jpg', '', '', ''),
(343, '', '2019-09-17', '', '', 'default.jpg', '', '', ''),
(344, 'sssam', '2019-09-01', 'ZzEyMzQ1NjAwMQ==', 'g123456001', 'default.jpg', '', '', ''),
(345, 'jjjjjjjjjj', '2019-09-01', 'cXFxcXFxcXFxcXFxcX', 'qqqqqqqqqqqqqq', 'default.jpg', '', '', ''),
(346, '0945', '2019-09-01', 'MDk0NQ==', '0945', 'default.jpg', '', '', ''),
(347, '0945', '2019-09-09', 'c3Nkc2RzZA==', 'ssdsdsd', 'default.jpg', '', '', ''),
(348, '0918', '2019-09-01', 'MDkxOA==', '0918', 'default.jpg', '', '', ''),
(349, '0918', '2019-09-01', 'MDkxOA==', '0918', 'default.jpg', '', '', ''),
(350, 'sam1268', '2019-09-11', 'c2FtMTI2OA==', 'sam1268', 'default.jpg', '', '', ''),
(351, 'sam1269', '2019-09-02', 'c2FtMTI2OQ==', 'sam1269', 'default.jpg', '', '', ''),
(352, 'sam1270', '2019-09-04', 'c2FtMTI3MQ==', 'sam1271', 'default.jpg', '', '', ''),
(353, 'sam1271', '2019-09-10', 'c2FtMTI3Mg==', 'sam1272', 'default.jpg', '', '', ''),
(354, 'sam1272', '2019-09-11', 'c2FtMTI3Mw==', 'sam1273', 'default.jpg', '', '', ''),
(355, 'aaaa', '2019-09-02', 'YWFh', 'aaa', 'default.jpg', '', '', ''),
(356, 'sam', '2019-09-02', 'MTExMQ==', '1111', 'default.jpg', '', '', '');

-- --------------------------------------------------------

--
-- 資料表結構 `record`
--

CREATE TABLE `record` (
  `P_ID` varchar(20) NOT NULL,
  `medical_date` date NOT NULL,
  `medical_type` int(11) NOT NULL,
  `medical_angle` int(11) NOT NULL,
  `medical_frequency` int(11) NOT NULL,
  `finish_date` date NOT NULL,
  `finish_time` time NOT NULL,
  `spend_time` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 傾印資料表的資料 `record`
--

INSERT INTO `record` (`P_ID`, `medical_date`, `medical_type`, `medical_angle`, `medical_frequency`, `finish_date`, `finish_time`, `spend_time`) VALUES
('1', '2019-09-07', 1, 60, 5, '2019-09-08', '14:53:00', '125'),
('1', '2019-09-07', 1, 60, 5, '2019-09-08', '15:21:00', '17'),
('1', '2019-09-07', 1, 60, 5, '2019-09-08', '16:04:00', '15'),
('1', '2019-09-07', 3, 20, 5, '2019-09-08', '16:05:00', '19'),
('1', '2019-09-07', 1, 60, 5, '2019-09-08', '16:07:00', '13'),
('1', '2019-09-07', 1, 60, 5, '2019-09-08', '16:12:00', '26'),
('1', '2019-09-07', 1, 60, 5, '2019-09-08', '16:29:00', '79'),
('1', '2019-09-07', 3, 20, 5, '2019-09-08', '16:31:00', '73'),
('1', '2019-09-13', 1, 60, 5, '2019-09-13', '14:45:00', '38'),
('1', '2019-09-13', 1, 60, 5, '2019-09-13', '14:52:00', '31'),
('1', '2019-09-13', 1, 60, 5, '2019-09-13', '15:04:00', '41'),
('1', '2019-09-13', 3, 20, 5, '2019-09-13', '15:08:00', '89'),
('1', '2019-09-13', 1, 60, 5, '2019-09-13', '15:26:00', '31'),
('1', '2019-09-13', 3, 20, 5, '2019-09-13', '15:28:00', '41'),
('1', '2019-09-13', 1, 60, 5, '2019-09-13', '15:40:00', '19'),
('1', '2019-09-13', 1, 60, 5, '2019-09-13', '15:52:00', '27'),
('1', '2019-09-13', 3, 20, 5, '2019-09-13', '16:06:00', '52'),
('1', '2019-09-15', 1, 60, 5, '2019-09-15', '15:43:00', '40'),
('1', '2019-09-15', 2, 60, 5, '2019-09-15', '15:45:00', '71'),
('1', '2019-09-15', 2, 60, 5, '2019-09-15', '16:18:00', '327'),
('1', '2019-09-15', 2, 20, 5, '2019-09-15', '16:23:00', '168'),
('1', '2019-09-15', 2, 20, 5, '2019-09-15', '16:27:00', '49'),
('1', '2019-09-15', 2, 20, 5, '2019-09-15', '16:31:00', '45'),
('1', '2019-09-15', 1, 60, 5, '2019-09-15', '23:42:00', '55'),
('1', '2019-09-15', 1, 60, 5, '2019-09-15', '23:46:00', '48'),
('1', '2019-09-15', 2, 20, 5, '2019-09-16', '00:05:00', '33'),
('1', '2019-09-15', 2, 20, 5, '2019-09-16', '00:08:00', '75'),
('1', '2019-09-15', 2, 20, 5, '2019-09-16', '00:10:00', '103'),
('1', '2019-09-15', 2, 20, 5, '2019-09-16', '00:11:00', '57'),
('1', '2019-09-15', 1, 60, 5, '2019-09-17', '15:09:00', '98'),
('1', '2019-09-15', 2, 30, 10, '2019-09-17', '16:06:00', '119'),
('1', '2019-09-15', 1, 60, 5, '2019-09-17', '21:13:00', '36'),
('1', '2019-09-15', 1, 60, 5, '2019-09-18', '12:01:00', '641'),
('1', '2019-09-15', 1, 60, 5, '2019-09-18', '13:23:00', '39'),
('1', '2019-09-15', 1, 60, 5, '2019-09-23', '22:44:00', '78'),
('1', '2019-09-15', 1, 60, 5, '2019-09-25', '15:55:00', '28'),
('1', '2019-09-15', 1, 60, 5, '2019-09-25', '15:58:00', '27'),
('1', '2019-10-31', 1, 60, 5, '2019-11-05', '12:25:00', '45'),
('1', '2019-10-31', 1, 60, 5, '2019-11-07', '10:43:00', '30'),
('1', '2019-10-31', 1, 60, 5, '2019-11-07', '11:05:00', '27'),
('1', '2019-10-31', 1, 60, 5, '2019-11-07', '11:37:00', '45'),
('1', '2019-10-31', 1, 60, 5, '2019-11-11', '14:00:00', '29'),
('1', '2019-10-31', 1, 60, 5, '2019-11-13', '16:16:00', '26'),
('1', '2019-10-31', 1, 60, 5, '2019-11-13', '16:50:00', '117'),
('1', '2019-10-31', 1, 60, 5, '2019-11-18', '14:42:00', '450'),
('1', '2019-10-31', 1, 60, 5, '2019-11-19', '12:38:00', '46'),
('1', '2019-10-31', 1, 60, 5, '2019-11-19', '14:01:00', '27');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `chat`
--
ALTER TABLE `chat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `a` (`P_ID`);

--
-- 資料表索引 `disease`
--
ALTER TABLE `disease`
  ADD UNIQUE KEY `name` (`name`);

--
-- 資料表索引 `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `dr_ID` (`dr_ID`) USING BTREE;

--
-- 資料表索引 `drdata`
--
ALTER TABLE `drdata`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dr_ID` (`dr_ID`);

--
-- 資料表索引 `medical_order`
--
ALTER TABLE `medical_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dr_ID` (`dr_ID`) USING BTREE,
  ADD KEY `b` (`P_code`);

--
-- 資料表索引 `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `P_ID` (`P_ID`) USING BTREE,
  ADD KEY `P_code` (`P_code`),
  ADD KEY `dr_ID` (`dr_ID`) USING BTREE;

--
-- 資料表索引 `pdata`
--
ALTER TABLE `pdata`
  ADD PRIMARY KEY (`id`),
  ADD KEY `P_code` (`P_code`);

--
-- 資料表索引 `record`
--
ALTER TABLE `record`
  ADD KEY `P_ID` (`P_ID`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `chat`
--
ALTER TABLE `chat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `doctor`
--
ALTER TABLE `doctor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `drdata`
--
ALTER TABLE `drdata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `medical_order`
--
ALTER TABLE `medical_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=301;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `pdata`
--
ALTER TABLE `pdata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=357;

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `chat`
--
ALTER TABLE `chat`
  ADD CONSTRAINT `a` FOREIGN KEY (`P_ID`) REFERENCES `patient` (`P_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 資料表的限制式 `drdata`
--
ALTER TABLE `drdata`
  ADD CONSTRAINT `aa` FOREIGN KEY (`dr_ID`) REFERENCES `doctor` (`dr_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 資料表的限制式 `medical_order`
--
ALTER TABLE `medical_order`
  ADD CONSTRAINT `b` FOREIGN KEY (`P_code`) REFERENCES `pdata` (`P_code`),
  ADD CONSTRAINT `id_ID` FOREIGN KEY (`dr_ID`) REFERENCES `doctor` (`dr_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 資料表的限制式 `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `P_code` FOREIGN KEY (`P_code`) REFERENCES `pdata` (`P_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `dr_ID` FOREIGN KEY (`dr_ID`) REFERENCES `doctor` (`dr_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 資料表的限制式 `record`
--
ALTER TABLE `record`
  ADD CONSTRAINT `P_ID` FOREIGN KEY (`P_ID`) REFERENCES `patient` (`P_ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
