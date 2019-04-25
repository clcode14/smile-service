/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.7.23 : Database - smile
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`smile` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `smile`;

/*Table structure for table `r_role_resource` */

DROP TABLE IF EXISTS `r_role_resource`;

CREATE TABLE `r_role_resource` (
  `role_id` int(11) NOT NULL COMMENT '权限ID',
  `resource_id` int(11) NOT NULL COMMENT '资源ID',
  PRIMARY KEY (`role_id`,`resource_id`),
  KEY `FK26b7o6ngv44hnl5lg1nwfu0rl` (`resource_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='权限资源表';

/*Data for the table `r_role_resource` */

/*Table structure for table `r_user_role` */

DROP TABLE IF EXISTS `r_user_role`;

CREATE TABLE `r_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKsxcrrh6mn91vpv2r4kke5bt3d` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户权限表';

/*Data for the table `r_user_role` */

/*Table structure for table `tb_charity_fault_record_image` */

DROP TABLE IF EXISTS `tb_charity_fault_record_image`;

CREATE TABLE `tb_charity_fault_record_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `image_id` int(11) NOT NULL COMMENT '图片ID',
  `charity_fault_record_id` int(11) NOT NULL COMMENT '行善忏悔记录ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `image_id` (`image_id`,`charity_fault_record_id`),
  KEY `charity_fault_record` (`charity_fault_record_id`),
  CONSTRAINT `tb_charity_fault_record_image_ibfk_1` FOREIGN KEY (`image_id`) REFERENCES `tb_images` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_charity_fault_record_image_ibfk_2` FOREIGN KEY (`charity_fault_record_id`) REFERENCES `tb_user_charity_fault_record` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='行善过失-图片关联表';

/*Data for the table `tb_charity_fault_record_image` */

insert  into `tb_charity_fault_record_image`(`id`,`image_id`,`charity_fault_record_id`) values 
(11,1,14),
(13,1,15),
(15,1,16),
(17,1,17),
(19,1,18),
(12,2,14),
(14,2,15),
(16,2,16),
(18,2,17),
(20,2,18);

/*Table structure for table `tb_charity_fault_type` */

DROP TABLE IF EXISTS `tb_charity_fault_type`;

CREATE TABLE `tb_charity_fault_type` (
  `cf_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type_name` varchar(30) NOT NULL COMMENT '类型名称',
  `type` int(11) NOT NULL COMMENT '0:行善，1:过失',
  `integral` int(10) NOT NULL DEFAULT '0' COMMENT '积分计算',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cf_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='行善过失类型表';

/*Data for the table `tb_charity_fault_type` */

insert  into `tb_charity_fault_type`(`cf_type_id`,`type_name`,`type`,`integral`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'祝福',0,1,NULL,'system','2019-04-07 16:11:43',NULL,NULL),
(2,'道歉',1,1,NULL,'system','2019-04-07 16:11:43',NULL,NULL),
(3,'微笑',0,1,NULL,'system','2019-04-07 16:11:43',NULL,NULL),
(4,'鼓励他人',0,1,NULL,'system','2019-04-07 16:11:43',NULL,NULL),
(5,'捐款',0,1,NULL,'system','2019-04-07 16:11:43',NULL,NULL),
(6,'捐物',0,1,NULL,'system','2019-04-07 16:11:43',NULL,NULL),
(7,'做义工',0,1,NULL,'system','2019-04-07 16:11:43',NULL,NULL),
(8,'其他',0,0,NULL,'system','2019-04-16 23:09:10',NULL,NULL);

/*Table structure for table `tb_charity_fault_type_content` */

DROP TABLE IF EXISTS `tb_charity_fault_type_content`;

CREATE TABLE `tb_charity_fault_type_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cf_type_id` int(11) NOT NULL COMMENT '类型ID',
  `content` varchar(30) DEFAULT NULL COMMENT '内容',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `charity_type_id` (`cf_type_id`),
  CONSTRAINT `tb_charity_fault_type_content_ibfk_1` FOREIGN KEY (`cf_type_id`) REFERENCES `tb_charity_fault_type` (`cf_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;

/*Data for the table `tb_charity_fault_type_content` */

insert  into `tb_charity_fault_type_content`(`id`,`cf_type_id`,`content`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,1,'世界和平',NULL,'system','2019-04-07 16:13:33',NULL,NULL),
(2,1,'国泰民安',NULL,'system','2019-04-07 16:13:33',NULL,NULL),
(3,1,'让世界充满爱',NULL,'system','2019-04-07 16:13:33',NULL,NULL),
(4,1,'自检',NULL,'system','2019-04-07 16:13:33',NULL,NULL),
(58,2,'世界和平',NULL,NULL,NULL,NULL,NULL),
(59,2,'国泰民安',NULL,NULL,NULL,NULL,NULL),
(60,2,'让世界充满爱',NULL,NULL,NULL,NULL,NULL),
(61,2,'自检',NULL,NULL,NULL,NULL,NULL),
(65,3,'世界和平',NULL,NULL,NULL,NULL,NULL),
(66,3,'国泰民安',NULL,NULL,NULL,NULL,NULL),
(67,3,'让世界充满爱',NULL,NULL,NULL,NULL,NULL),
(68,3,'自检',NULL,NULL,NULL,NULL,NULL),
(72,4,'世界和平',NULL,NULL,NULL,NULL,NULL),
(73,4,'国泰民安',NULL,NULL,NULL,NULL,NULL),
(74,4,'让世界充满爱',NULL,NULL,NULL,NULL,NULL),
(75,4,'自检',NULL,NULL,NULL,NULL,NULL),
(79,5,'世界和平',NULL,NULL,NULL,NULL,NULL),
(80,5,'国泰民安',NULL,NULL,NULL,NULL,NULL),
(81,5,'让世界充满爱',NULL,NULL,NULL,NULL,NULL),
(82,5,'自检',NULL,NULL,NULL,NULL,NULL),
(86,6,'世界和平',NULL,NULL,NULL,NULL,NULL),
(87,6,'国泰民安',NULL,NULL,NULL,NULL,NULL),
(88,6,'让世界充满爱',NULL,NULL,NULL,NULL,NULL),
(89,6,'自检',NULL,NULL,NULL,NULL,NULL),
(93,7,'世界和平',NULL,NULL,NULL,NULL,NULL),
(94,7,'国泰民安',NULL,NULL,NULL,NULL,NULL),
(95,7,'让世界充满爱',NULL,NULL,NULL,NULL,NULL),
(96,7,'自检',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tb_course_banner` */

DROP TABLE IF EXISTS `tb_course_banner`;

CREATE TABLE `tb_course_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态是否可用',
  `course_id` int(11) NOT NULL COMMENT '课程ID',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_id` (`course_id`),
  CONSTRAINT `tb_course_banner_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `tb_course_registration` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `tb_course_banner` */

insert  into `tb_course_banner`(`id`,`status`,`course_id`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(2,1,3,NULL,NULL,NULL,NULL,NULL),
(3,1,6,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tb_course_image` */

DROP TABLE IF EXISTS `tb_course_image`;

CREATE TABLE `tb_course_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `course_id` int(11) NOT NULL COMMENT '课程ID',
  `image_id` int(11) NOT NULL COMMENT '图片ID',
  PRIMARY KEY (`id`),
  KEY `FKdx13ipxcpeq2fvx3chk2ko3iy` (`image_id`),
  KEY `FKc9uphm5bdgn48wj4i8e6effbp` (`course_id`),
  CONSTRAINT `tb_course_image_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `tb_course_registration` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_course_image_ibfk_2` FOREIGN KEY (`image_id`) REFERENCES `tb_images` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='课程图片表';

/*Data for the table `tb_course_image` */

insert  into `tb_course_image`(`id`,`course_id`,`image_id`) values 
(1,5,4),
(2,5,6);

/*Table structure for table `tb_course_registration` */

DROP TABLE IF EXISTS `tb_course_registration`;

CREATE TABLE `tb_course_registration` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `title` varchar(60) NOT NULL COMMENT '标题',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `price` decimal(9,4) DEFAULT NULL COMMENT '价格',
  `members` int(11) NOT NULL COMMENT '报名人数',
  `apply_count` int(11) DEFAULT NULL COMMENT '已报名人数',
  `address` varchar(1000) NOT NULL COMMENT '活动地址',
  `cover_image_id` int(11) DEFAULT NULL COMMENT '封面图片ID',
  `description` text COMMENT '详情描述',
  `type_id` int(11) DEFAULT NULL COMMENT '课程类型ID',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) NOT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`course_id`) USING BTREE,
  KEY `FK25aoiumifkadlcuuaqagbpm8r` (`cover_image_id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `COVER_IMAGE` FOREIGN KEY (`cover_image_id`) REFERENCES `tb_images` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `tb_course_registration_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `tb_course_type` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='课程表';

/*Data for the table `tb_course_registration` */

insert  into `tb_course_registration`(`course_id`,`title`,`start_time`,`price`,`members`,`apply_count`,`address`,`cover_image_id`,`description`,`type_id`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(2,'美容课','2019-05-20 12:00:00',600.0000,50,NULL,'江苏省苏州市园区',2,'你想减肥吗，来加入五门吧',2,NULL,'admin','2019-04-01 22:52:31','admin','2019-04-10 21:49:33'),
(3,'课程3','2019-04-01 15:00:00',250.0000,250,NULL,'地址3',2,'课程3',1,NULL,'admin','2019-04-01 22:52:44',NULL,'2019-04-01 22:52:44'),
(4,'课程3','2019-04-10 09:00:00',120.0000,20,NULL,'地址1',1,'<p><img class=\"wscnph\" src=\"http://192.168.0.216:8087/smile-admin/image/course_image/2_system/1554627635069.jpg\" /></p>\n<p>1、1111111111111111</p>\n<p>2、2222222222222222222222</p>\n<p>3、333333333333333333333</p>\n<p>4、44444444444444</p>',NULL,NULL,'admin','2019-04-07 17:01:20','admin','2019-04-07 17:05:08'),
(5,'课程4','2019-04-01 00:00:00',125.0000,120,NULL,'地址4',1,'<p><img class=\"wscnph\" src=\"http://192.168.0.216:8087/smile-admin/image/course_image/2_system/1554629008622.jpg\" /><img class=\"wscnph\" src=\"http://192.168.0.216:8087/smile-admin/image/course_image/2_system/1554629008810.jpg\" /></p>',NULL,NULL,'admin','2019-04-07 17:23:33','admin','2019-04-07 17:23:43'),
(6,'减肥课','2019-06-30 12:00:00',600.0000,50,NULL,'江苏省苏州市园区',2,'你想减肥吗，来加入五门吧',NULL,NULL,'admin','2019-04-10 21:32:07',NULL,'2019-04-10 21:32:07');

/*Table structure for table `tb_course_type` */

DROP TABLE IF EXISTS `tb_course_type`;

CREATE TABLE `tb_course_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `course_type_name` varchar(100) DEFAULT NULL COMMENT '课程类型名称',
  `memo` varchar(1000) CHARACTER SET latin1 DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) CHARACTER SET latin1 DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) CHARACTER SET latin1 DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='课程分类表';

/*Data for the table `tb_course_type` */

insert  into `tb_course_type`(`id`,`course_type_name`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'游学课程',NULL,'system','2019-04-17 15:39:53',NULL,NULL),
(2,'课程类型2',NULL,'system','2019-04-17 15:40:08',NULL,NULL);

/*Table structure for table `tb_custom` */

DROP TABLE IF EXISTS `tb_custom`;

CREATE TABLE `tb_custom` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(255) DEFAULT NULL COMMENT '客服名',
  `phone` varchar(255) DEFAULT NULL COMMENT '客服手机',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='客服表';

/*Data for the table `tb_custom` */

insert  into `tb_custom`(`id`,`name`,`phone`,`memo`,`create_time`,`create_user_name`,`update_time`,`update_user_name`) values 
(1,'减肥课','18994314562',NULL,NULL,NULL,'2019-04-24 23:19:17','Admin'),
(2,'李白','18994314562',NULL,'2019-04-14 21:06:56','Admin','2019-04-14 21:06:56',NULL),
(3,'杜甫','18994314562',NULL,'2019-04-14 21:07:02','Admin','2019-04-14 21:07:02',NULL),
(4,'程咬金','18994314562',NULL,'2019-04-14 21:07:10','Admin','2019-04-14 21:07:10',NULL),
(5,'悟空','18994314562',NULL,'2019-04-14 21:08:04',NULL,'2019-04-14 21:08:04','Admin'),
(6,'客服一','1548545251',NULL,'2019-04-24 23:13:24','Admin','2019-04-24 23:13:24',NULL),
(7,'客服2','41545245',NULL,'2019-04-24 23:13:46','Admin','2019-04-24 23:13:46',NULL),
(8,'客服3','11111111',NULL,'2019-04-24 23:13:58','Admin','2019-04-24 23:13:58',NULL),
(9,'2111','2212',NULL,'2019-04-24 23:14:07','Admin','2019-04-24 23:14:07',NULL),
(11,'客服12','12',NULL,'2019-04-24 23:14:24','Admin','2019-04-24 23:14:24',NULL),
(12,'1111','111',NULL,'2019-04-24 23:16:40','Admin','2019-04-24 23:16:40',NULL);

/*Table structure for table `tb_dictionary` */

DROP TABLE IF EXISTS `tb_dictionary`;

CREATE TABLE `tb_dictionary` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `dic_category` varchar(64) NOT NULL COMMENT '字典种类',
  `dic_name` varchar(64) NOT NULL COMMENT '字典名称',
  `dic_key` varchar(64) NOT NULL COMMENT '字典Key',
  `dic_value` varchar(64) NOT NULL COMMENT '字典Value',
  `description` varchar(64) DEFAULT NULL COMMENT '描述',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='字典表';

/*Data for the table `tb_dictionary` */

insert  into `tb_dictionary`(`id`,`dic_category`,`dic_name`,`dic_key`,`dic_value`,`description`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'HEALTH_WAY_TYPE','类型1','1','类型1',NULL,NULL,NULL,NULL,NULL,NULL),
(2,'HEALTH_WAY_TYPE','类型2','2','类型2',NULL,NULL,NULL,NULL,NULL,NULL),
(3,'HEALTH_WAY_TYPE','类型3','3','类型3',NULL,NULL,NULL,NULL,NULL,NULL),
(4,'HEALTH_WAY_TYPE','类型4','4','类型4',NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tb_disease_class` */

DROP TABLE IF EXISTS `tb_disease_class`;

CREATE TABLE `tb_disease_class` (
  `disease_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '疾病大类ID',
  `number` varchar(50) DEFAULT NULL COMMENT '编码',
  `disease_name` varchar(100) DEFAULT NULL COMMENT '疾病类目名称',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`disease_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='疾病大类表';

/*Data for the table `tb_disease_class` */

insert  into `tb_disease_class`(`disease_id`,`number`,`disease_name`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'001','疾病大类1',NULL,'admin','2019-04-24 23:01:25',NULL,'2019-04-24 23:01:25'),
(2,'002','疾病大类2',NULL,'admin','2019-04-24 23:01:36',NULL,'2019-04-24 23:01:36'),
(3,'003','疾病大类3',NULL,'admin','2019-04-24 23:02:09',NULL,'2019-04-24 23:02:09');

/*Table structure for table `tb_disease_class_detail` */

DROP TABLE IF EXISTS `tb_disease_class_detail`;

CREATE TABLE `tb_disease_class_detail` (
  `disease_detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '疾病小类ID',
  `disease_id` int(11) NOT NULL COMMENT '疾病大类ID',
  `number` varchar(30) DEFAULT NULL COMMENT '编码',
  `type` varchar(20) DEFAULT NULL COMMENT '疾病小类类型 0：普通疾病1，常见疾病',
  `disease_detail_name` varchar(100) NOT NULL COMMENT '疾病小类名称',
  `bg_images` int(11) DEFAULT NULL COMMENT '疾病小类背景图（应用在APP疾病小类展示上）',
  `content` text COMMENT '疾病小类描述（简介）',
  `icon` int(11) DEFAULT NULL COMMENT '疾病小类图标',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`disease_detail_id`) USING BTREE,
  KEY `FK9balwu1bp2ksvlpcuje4t3het` (`disease_id`),
  CONSTRAINT `DISEASE_KEY` FOREIGN KEY (`disease_id`) REFERENCES `tb_disease_class` (`disease_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='疾病小类表';

/*Data for the table `tb_disease_class_detail` */

insert  into `tb_disease_class_detail`(`disease_detail_id`,`disease_id`,`number`,`type`,`disease_detail_name`,`bg_images`,`content`,`icon`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,1,'111','0','111',NULL,NULL,68,NULL,'admin','2019-04-25 00:12:59',NULL,'2019-04-25 00:12:59');

/*Table structure for table `tb_disease_reason` */

DROP TABLE IF EXISTS `tb_disease_reason`;

CREATE TABLE `tb_disease_reason` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `disease_id` int(11) DEFAULT NULL COMMENT '疾病大类Id',
  `disease_detail_id` int(11) DEFAULT NULL COMMENT '疾病小类Id',
  `number` varchar(30) DEFAULT NULL COMMENT '编码',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '病因分析内容',
  `read_num` int(11) DEFAULT NULL COMMENT '阅读数',
  `type` int(11) DEFAULT NULL COMMENT '病因类型（1：中医，2：西医）',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKi6shv04k3kjopcfvqdnmgquy9` (`disease_detail_id`) USING BTREE,
  KEY `disease_id` (`disease_id`),
  KEY `tb_disease_reason_ibfk_3` (`type`),
  CONSTRAINT `tb_disease_reason_ibfk_1` FOREIGN KEY (`disease_id`) REFERENCES `tb_disease_class` (`disease_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_disease_reason_ibfk_2` FOREIGN KEY (`disease_detail_id`) REFERENCES `tb_disease_class_detail` (`disease_detail_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_disease_reason_ibfk_3` FOREIGN KEY (`type`) REFERENCES `tb_disease_reason_type` (`type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='病因分析表(疾病原因)';

/*Data for the table `tb_disease_reason` */

/*Table structure for table `tb_disease_reason_to_solution` */

DROP TABLE IF EXISTS `tb_disease_reason_to_solution`;

CREATE TABLE `tb_disease_reason_to_solution` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `disease_reason_id` int(11) NOT NULL COMMENT '疾病原因ID',
  `solution_id` int(11) NOT NULL COMMENT '解决方案ID',
  PRIMARY KEY (`id`),
  KEY `FKoi2ew0n7no7tpqky5o8v8asdm` (`solution_id`),
  KEY `FKkklr1b6n2y082jppdepssdcft` (`disease_reason_id`),
  CONSTRAINT `tb_disease_reason_to_solution_ibfk_1` FOREIGN KEY (`disease_reason_id`) REFERENCES `tb_disease_reason` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_disease_reason_to_solution_ibfk_2` FOREIGN KEY (`solution_id`) REFERENCES `tb_solution` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='疾病原因-解决方案关联表';

/*Data for the table `tb_disease_reason_to_solution` */

/*Table structure for table `tb_disease_reason_type` */

DROP TABLE IF EXISTS `tb_disease_reason_type`;

CREATE TABLE `tb_disease_reason_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type_name` varchar(30) NOT NULL COMMENT '类型名称',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='疾病原因类型表';

/*Data for the table `tb_disease_reason_type` */

insert  into `tb_disease_reason_type`(`type_id`,`type_name`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'中医',NULL,'system','2019-04-04 23:21:26',NULL,NULL),
(2,'西医',NULL,'system','2019-04-04 23:21:36',NULL,NULL);

/*Table structure for table `tb_dynamic` */

DROP TABLE IF EXISTS `tb_dynamic`;

CREATE TABLE `tb_dynamic` (
  `dynamic_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '动态ID',
  `title` varchar(100) DEFAULT NULL COMMENT '动态标题',
  `content` text COMMENT '动态内容',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `dynamic_detail_num` int(11) NOT NULL COMMENT '动态个数',
  `read_num` int(11) NOT NULL COMMENT '浏览数',
  `hidden` tinyint(1) NOT NULL COMMENT '是否公开',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`dynamic_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_dynamic_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='动态表';

/*Data for the table `tb_dynamic` */

insert  into `tb_dynamic`(`dynamic_id`,`title`,`content`,`user_id`,`dynamic_detail_num`,`read_num`,`hidden`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(6,'动态1','动态内容',1,2,0,0,NULL,NULL,'2019-04-23 19:42:14',NULL,'2019-04-23 23:59:24');

/*Table structure for table `tb_dynamic_detail_message` */

DROP TABLE IF EXISTS `tb_dynamic_detail_message`;

CREATE TABLE `tb_dynamic_detail_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `dynamic_detail_id` int(11) NOT NULL COMMENT '动态明细ID',
  `message` varchar(5000) DEFAULT NULL COMMENT '评论内容',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级ID（回复的评论ID）',
  `from_user_id` bigint(20) NOT NULL COMMENT '创建者用户ID',
  `to_user_id` bigint(20) DEFAULT NULL COMMENT '发送给用户ID',
  `like_num` int(11) DEFAULT NULL COMMENT '点赞个数',
  `is_read` tinyint(1) DEFAULT NULL COMMENT '接收者用户是否查看',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `dynamic_detail_id` (`dynamic_detail_id`),
  KEY `from_user_id` (`from_user_id`),
  KEY `to_user_id` (`to_user_id`),
  KEY `tb_dynamic_detail_message_ibfk_2` (`parent_id`),
  CONSTRAINT `tb_dynamic_detail_message_ibfk_1` FOREIGN KEY (`dynamic_detail_id`) REFERENCES `tb_dynamic_details` (`dynamic_detail_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_dynamic_detail_message_ibfk_2` FOREIGN KEY (`parent_id`) REFERENCES `tb_dynamic_detail_message` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_dynamic_detail_message_ibfk_3` FOREIGN KEY (`from_user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_dynamic_detail_message_ibfk_4` FOREIGN KEY (`to_user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='动态评论信息表';

/*Data for the table `tb_dynamic_detail_message` */

insert  into `tb_dynamic_detail_message`(`id`,`dynamic_detail_id`,`message`,`parent_id`,`from_user_id`,`to_user_id`,`like_num`,`is_read`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,2,'2222',NULL,2,1,0,0,NULL,NULL,NULL,NULL,NULL),
(2,2,'评论1',NULL,2,1,0,0,NULL,'1','2019-04-24 01:17:03',NULL,'2019-04-24 01:17:03'),
(3,2,'评论回复1',2,1,2,0,0,NULL,'1','2019-04-24 01:17:27',NULL,'2019-04-24 01:17:27'),
(4,2,'评论回复2',3,2,1,0,0,NULL,'1','2019-04-24 01:18:01',NULL,'2019-04-24 01:18:01'),
(5,2,'评论回复3',4,1,2,0,0,NULL,'1','2019-04-24 01:18:44',NULL,'2019-04-24 01:18:44');

/*Table structure for table `tb_dynamic_details` */

DROP TABLE IF EXISTS `tb_dynamic_details`;

CREATE TABLE `tb_dynamic_details` (
  `dynamic_detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '发布动态明细ID',
  `dynamic_id` int(11) NOT NULL COMMENT '动态ID',
  `content` text COMMENT '动态内容',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `forward_num` int(11) DEFAULT NULL COMMENT '转发个数',
  `message_num` int(11) DEFAULT NULL COMMENT '评论个数',
  `like_num` int(11) DEFAULT NULL COMMENT '点赞个数',
  `read_num` int(11) DEFAULT NULL COMMENT '浏览数',
  `hidden` tinyint(1) DEFAULT NULL COMMENT '是否公开',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`dynamic_detail_id`),
  KEY `dynamic_id` (`dynamic_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_dynamic_details_ibfk_1` FOREIGN KEY (`dynamic_id`) REFERENCES `tb_dynamic` (`dynamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_dynamic_details_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='动态明细表';

/*Data for the table `tb_dynamic_details` */

insert  into `tb_dynamic_details`(`dynamic_detail_id`,`dynamic_id`,`content`,`user_id`,`forward_num`,`message_num`,`like_num`,`read_num`,`hidden`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(2,6,'动态明细内容1',1,0,0,0,0,0,NULL,NULL,'2019-04-23 23:59:22',NULL,'2019-04-23 23:59:22'),
(3,6,'动态明细内容1',1,0,0,0,0,0,NULL,NULL,'2019-04-23 23:59:24',NULL,'2019-04-23 23:59:24');

/*Table structure for table `tb_dynamic_details_files` */

DROP TABLE IF EXISTS `tb_dynamic_details_files`;

CREATE TABLE `tb_dynamic_details_files` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `dynamic_detail_id` int(11) NOT NULL COMMENT '发布动态明细ID',
  `file_id` int(11) NOT NULL COMMENT '文件ID',
  PRIMARY KEY (`id`),
  KEY `dynamic_details_id` (`dynamic_detail_id`),
  KEY `file_id` (`file_id`),
  CONSTRAINT `tb_dynamic_details_files_ibfk_1` FOREIGN KEY (`dynamic_detail_id`) REFERENCES `tb_dynamic_details` (`dynamic_detail_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_dynamic_details_files_ibfk_2` FOREIGN KEY (`file_id`) REFERENCES `tb_files` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='动态明细-文件关联表';

/*Data for the table `tb_dynamic_details_files` */

insert  into `tb_dynamic_details_files`(`id`,`dynamic_detail_id`,`file_id`) values 
(4,2,1),
(5,2,2),
(6,2,3),
(7,3,1),
(8,3,2),
(9,3,3);

/*Table structure for table `tb_dynamic_files` */

DROP TABLE IF EXISTS `tb_dynamic_files`;

CREATE TABLE `tb_dynamic_files` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `dynamic_id` int(11) NOT NULL COMMENT '动态ID',
  `file_id` int(11) NOT NULL COMMENT '动态文件（图片、视频）ID',
  PRIMARY KEY (`id`),
  KEY `dynamic_id` (`dynamic_id`),
  KEY `file_id` (`file_id`),
  CONSTRAINT `tb_dynamic_files_ibfk_1` FOREIGN KEY (`dynamic_id`) REFERENCES `tb_dynamic` (`dynamic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_dynamic_files_ibfk_2` FOREIGN KEY (`file_id`) REFERENCES `tb_files` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='动态-文件关联表';

/*Data for the table `tb_dynamic_files` */

insert  into `tb_dynamic_files`(`id`,`dynamic_id`,`file_id`) values 
(21,6,1),
(22,6,2),
(23,6,3),
(24,6,4);

/*Table structure for table `tb_files` */

DROP TABLE IF EXISTS `tb_files`;

CREATE TABLE `tb_files` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `file_name` varchar(50) DEFAULT NULL COMMENT '文件名称',
  `size` int(11) DEFAULT NULL COMMENT '文件大小',
  `path` varchar(100) DEFAULT NULL COMMENT '文件路径',
  `file_type` varchar(100) DEFAULT NULL COMMENT '文件类型',
  `module` varchar(100) DEFAULT NULL COMMENT '所属模块',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `FILE_PATH` (`path`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='文件表';

/*Data for the table `tb_files` */

insert  into `tb_files`(`id`,`file_name`,`size`,`path`,`file_type`,`module`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'002 - 副本 (5).jpg',24482,'\\1\\1555771257830.jpg','image/jpeg',NULL,NULL,'1','2019-04-20 22:40:58',NULL,'2019-04-20 22:40:58'),
(2,'002 - 副本 (5).jpg',24482,'\\1\\1555771372702.jpg','image/jpeg',NULL,NULL,'1','2019-04-20 22:42:53',NULL,'2019-04-20 22:42:53'),
(3,'001.jpg',39125,'\\1\\1555771409022.jpg','image/jpeg',NULL,NULL,'1','2019-04-20 22:43:29',NULL,'2019-04-20 22:43:29'),
(4,'002 - 副本 (2).jpg',24482,'\\1\\1555771409029.jpg','image/jpeg',NULL,NULL,'1','2019-04-20 22:43:29',NULL,'2019-04-20 22:43:29'),
(5,'003.jpg',202398,'\\1\\1555771409034.jpg','image/jpeg',NULL,NULL,'1','2019-04-20 22:43:29',NULL,'2019-04-20 22:43:29'),
(6,'004 - 副本 (2).jpg',41958,'\\1\\1555771409042.jpg','image/jpeg',NULL,NULL,'1','2019-04-20 22:43:29',NULL,'2019-04-20 22:43:29'),
(7,'001.jpg',39125,'\\1\\1555999646862.jpg','image/jpeg',NULL,NULL,'1','2019-04-23 14:07:27',NULL,'2019-04-23 14:07:27'),
(8,'002 - 副本 (2).jpg',24482,'\\1\\1555999646875.jpg','image/jpeg',NULL,NULL,'1','2019-04-23 14:07:27',NULL,'2019-04-23 14:07:27'),
(9,'003.jpg',202398,'\\1\\1555999646886.jpg','image/jpeg',NULL,NULL,'1','2019-04-23 14:07:27',NULL,'2019-04-23 14:07:27'),
(10,'004 - 副本 (2).jpg',41958,'\\1\\1555999646899.jpg','image/jpeg',NULL,NULL,'1','2019-04-23 14:07:27',NULL,'2019-04-23 14:07:27'),
(11,'002 - 副本 (2).jpg',24482,'\\dynamic\\1\\1555999694293.jpg','image/jpeg',NULL,NULL,'1','2019-04-23 14:08:14',NULL,'2019-04-23 14:08:14');

/*Table structure for table `tb_health` */

DROP TABLE IF EXISTS `tb_health`;

CREATE TABLE `tb_health` (
  `health_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `number` varchar(10) DEFAULT NULL COMMENT '编码',
  `health_name` varchar(30) DEFAULT NULL COMMENT '养生名称',
  `bg_image` int(11) DEFAULT NULL COMMENT '背景图',
  `content` text COMMENT '内容介紹',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`health_id`),
  KEY `bg_image` (`bg_image`),
  CONSTRAINT `tb_health_ibfk_1` FOREIGN KEY (`bg_image`) REFERENCES `tb_images` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='养生表';

/*Data for the table `tb_health` */

insert  into `tb_health`(`health_id`,`number`,`health_name`,`bg_image`,`content`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'001','养生大类1',1,'<h1>养生大类1</h1><p>123123ajaskljdaisdaosdoadsjojdoiasd</p>',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tb_health_norm_type` */

DROP TABLE IF EXISTS `tb_health_norm_type`;

CREATE TABLE `tb_health_norm_type` (
  `norm_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `norm_number` varchar(30) DEFAULT NULL COMMENT '编码',
  `norm_name` varchar(100) DEFAULT NULL COMMENT '指标名称',
  `unit` varchar(100) DEFAULT NULL COMMENT '单位',
  `max` double DEFAULT NULL COMMENT '最大值',
  `min` double DEFAULT NULL COMMENT '最小值',
  `step` double DEFAULT NULL COMMENT '步进',
  `input_type` int(11) DEFAULT NULL COMMENT '输入类型（参数个数）1：普通输入类型 2：血压两个输入框',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`norm_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='体检指标表';

/*Data for the table `tb_health_norm_type` */

insert  into `tb_health_norm_type`(`norm_type_id`,`norm_number`,`norm_name`,`unit`,`max`,`min`,`step`,`input_type`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'001','血脂','mmol/L',15,0,0.1,1,NULL,'system','2019-04-09 15:50:48',NULL,NULL),
(2,'002','血糖','mmol/L',15,0,0.1,1,NULL,'system','2019-04-09 15:51:09',NULL,NULL),
(3,'003','血压','mmHg',300,0,1,2,NULL,'system','2019-04-10 01:30:13',NULL,NULL);

/*Table structure for table `tb_health_result` */

DROP TABLE IF EXISTS `tb_health_result`;

CREATE TABLE `tb_health_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) NOT NULL COMMENT '养生成果名称',
  `number` varchar(30) DEFAULT NULL COMMENT '编码',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='养生成果表';

/*Data for the table `tb_health_result` */

insert  into `tb_health_result`(`id`,`name`,`number`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'效果一般','001',NULL,'system','2019-04-18 00:38:00',NULL,NULL),
(2,'效果很好','002',NULL,'system','2019-04-18 00:38:14',NULL,NULL),
(3,'没用','003',NULL,'system','2019-04-18 00:38:27',NULL,NULL);

/*Table structure for table `tb_health_to_solution` */

DROP TABLE IF EXISTS `tb_health_to_solution`;

CREATE TABLE `tb_health_to_solution` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `health_id` int(11) NOT NULL COMMENT '养生ID',
  `solution_id` int(11) NOT NULL COMMENT '解决方案ID',
  PRIMARY KEY (`id`),
  KEY `health_detail_id` (`health_id`),
  KEY `solution_id` (`solution_id`),
  CONSTRAINT `tb_health_to_solution_ibfk_1` FOREIGN KEY (`health_id`) REFERENCES `tb_health` (`health_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_health_to_solution_ibfk_2` FOREIGN KEY (`solution_id`) REFERENCES `tb_solution` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='养生-解决方案 关联表';

/*Data for the table `tb_health_to_solution` */

insert  into `tb_health_to_solution`(`id`,`health_id`,`solution_id`) values 
(4,1,1),
(5,1,3);

/*Table structure for table `tb_health_way` */

DROP TABLE IF EXISTS `tb_health_way`;

CREATE TABLE `tb_health_way` (
  `health_way_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '养生方式ID',
  `way_name` varchar(100) DEFAULT NULL COMMENT '养生方式名称',
  `bg_image` int(11) DEFAULT NULL COMMENT '背景图',
  `number` varchar(30) DEFAULT NULL COMMENT '编码',
  `content` text COMMENT '介绍描述',
  `music_url` varchar(600) DEFAULT NULL COMMENT '音乐链接',
  `type` int(11) DEFAULT NULL COMMENT '养生方式类型',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`health_way_id`),
  KEY `bg_image` (`bg_image`),
  CONSTRAINT `tb_health_way_ibfk_2` FOREIGN KEY (`bg_image`) REFERENCES `tb_images` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='养生方式表';

/*Data for the table `tb_health_way` */

insert  into `tb_health_way`(`health_way_id`,`way_name`,`bg_image`,`number`,`content`,`music_url`,`type`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'养生方式1',2,'001','养生小类1 <h1>养生小类1223</h1>',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(2,'养生方式2',2,'002','养生小类1 <h1>养生小类1223</h1>',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tb_home_banner` */

DROP TABLE IF EXISTS `tb_home_banner`;

CREATE TABLE `tb_home_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `title` varchar(30) DEFAULT NULL COMMENT '标题',
  `link` varchar(100) DEFAULT NULL COMMENT '连接',
  `image_id` int(11) NOT NULL COMMENT '图片ID',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `image_id` (`image_id`),
  CONSTRAINT `tb_home_banner_ibfk_1` FOREIGN KEY (`image_id`) REFERENCES `tb_images` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='主页Banner图表';

/*Data for the table `tb_home_banner` */

insert  into `tb_home_banner`(`id`,`title`,`link`,`image_id`,`sort`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'胃病','www.baidu.com',1,1,NULL,'Admin','2019-04-14 22:57:13',NULL,'2019-04-14 22:57:13'),
(2,'眼病','www.baidu.com',2,2,NULL,'Admin','2019-04-14 23:32:17',NULL,'2019-04-14 23:32:17'),
(3,'手','www.baidu.com',3,0,NULL,'Admin','2019-04-14 23:33:08',NULL,'2019-04-14 23:33:08');

/*Table structure for table `tb_images` */

DROP TABLE IF EXISTS `tb_images`;

CREATE TABLE `tb_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `file_name` varchar(50) DEFAULT NULL COMMENT '图片名称',
  `size` int(11) DEFAULT NULL COMMENT '图片大小',
  `path` varchar(100) DEFAULT NULL COMMENT '图片路径',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IMAGE_PATH` (`path`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8 COMMENT='图片表';

/*Data for the table `tb_images` */

insert  into `tb_images`(`id`,`file_name`,`size`,`path`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'image01.jpg',327983,'\\2\\1554129604141.jpg',NULL,'2','2019-04-01 22:40:04',NULL,'2019-04-01 22:40:04'),
(2,'image01.jpg',327983,'\\2\\1554129978583.jpg',NULL,'2','2019-04-01 22:46:19',NULL,'2019-04-01 22:46:19'),
(3,'timg.jpg',93542,'\\2\\1554129978688.jpg',NULL,'2','2019-04-01 22:46:19',NULL,'2019-04-01 22:46:19'),
(4,'timg01.jpg',61240,'\\2\\1554129978774.jpg',NULL,'2','2019-04-01 22:46:19',NULL,'2019-04-01 22:46:19'),
(5,'timg02.jpg',109143,'\\2\\1554129978857.jpg',NULL,'2','2019-04-01 22:46:19',NULL,'2019-04-01 22:46:19'),
(6,'timg (1).jpg',30940,'\\course_image\\2_system\\1554625858970.jpg',NULL,'admin','2019-04-07 16:30:59',NULL,'2019-04-07 16:30:59'),
(7,'timg (1).jpg',30940,'\\course_image\\2_system\\1554626085869.jpg',NULL,'admin','2019-04-07 16:34:46',NULL,'2019-04-07 16:34:46'),
(8,'timg (1).jpg',30940,'\\course_image\\2_system\\1554626152221.jpg',NULL,'admin','2019-04-07 16:35:52',NULL,'2019-04-07 16:35:52'),
(9,'timg (1).jpg',30940,'\\course_image\\2_system\\1554626234900.jpg',NULL,'admin','2019-04-07 16:37:15',NULL,'2019-04-07 16:37:15'),
(10,'timg (1).jpg',30940,'\\course_image\\2_system\\1554626316812.jpg',NULL,'admin','2019-04-07 16:38:37',NULL,'2019-04-07 16:38:37'),
(11,'timg (1).jpg',30940,'\\course_image\\2_system\\1554626357015.jpg',NULL,'admin','2019-04-07 16:39:17',NULL,'2019-04-07 16:39:17'),
(12,'timg (1).jpg',30940,'\\course_image\\2_system\\1554626382071.jpg',NULL,'admin','2019-04-07 16:39:42',NULL,'2019-04-07 16:39:42'),
(13,'timg (3).jpg',24575,'\\course_image\\2_system\\1554626562196.jpg',NULL,'admin','2019-04-07 16:42:42',NULL,'2019-04-07 16:42:42'),
(14,'timg (4).jpg',119676,'\\course_image\\2_system\\1554626621393.jpg',NULL,'admin','2019-04-07 16:43:41',NULL,'2019-04-07 16:43:41'),
(15,'timg (1).jpg',30940,'\\course_image\\2_system\\1554627283233.jpg',NULL,'admin','2019-04-07 16:54:43',NULL,'2019-04-07 16:54:43'),
(16,'timg (3).jpg',24575,'\\course_image\\2_system\\1554627288047.jpg',NULL,'admin','2019-04-07 16:54:48',NULL,'2019-04-07 16:54:48'),
(17,'timg (4).jpg',119676,'\\course_image\\2_system\\1554627291344.jpg',NULL,'admin','2019-04-07 16:54:51',NULL,'2019-04-07 16:54:51'),
(18,'timg (1).jpg',30940,'\\course_image\\2_system\\1554627323867.jpg',NULL,'admin','2019-04-07 16:55:24',NULL,'2019-04-07 16:55:24'),
(19,'timg (4).jpg',119676,'\\course_image\\2_system\\1554627547600.jpg',NULL,'admin','2019-04-07 16:59:08',NULL,'2019-04-07 16:59:08'),
(20,'timg (3).jpg',24575,'\\course_image\\2_system\\1554627614787.jpg',NULL,'admin','2019-04-07 17:00:15',NULL,'2019-04-07 17:00:15'),
(21,'timg (2).jpg',35166,'\\course_image\\2_system\\1554627624857.jpg',NULL,'admin','2019-04-07 17:00:25',NULL,'2019-04-07 17:00:25'),
(22,'timg (3).jpg',24575,'\\course_image\\2_system\\1554627625008.jpg',NULL,'admin','2019-04-07 17:00:25',NULL,'2019-04-07 17:00:25'),
(23,'timg (4).jpg',119676,'\\course_image\\2_system\\1554627625266.jpg',NULL,'admin','2019-04-07 17:00:26',NULL,'2019-04-07 17:00:26'),
(24,'timg (3).jpg',24575,'\\course_image\\2_system\\1554627635069.jpg',NULL,'admin','2019-04-07 17:00:35',NULL,'2019-04-07 17:00:35'),
(25,'timg (4).jpg',119676,'\\course_image\\2_system\\1554628728302.jpg',NULL,'admin','2019-04-07 17:18:48',NULL,'2019-04-07 17:18:48'),
(26,'timg (2).jpg',35166,'\\course_image\\2_system\\1554628828916.jpg',NULL,'admin','2019-04-07 17:20:29',NULL,'2019-04-07 17:20:29'),
(27,'timg (4).jpg',119676,'\\course_image\\2_system\\1554628832553.jpg',NULL,'admin','2019-04-07 17:20:33',NULL,'2019-04-07 17:20:33'),
(28,'timg (4).jpg',119676,'\\course_image\\2_system\\1554628840935.jpg',NULL,'admin','2019-04-07 17:20:41',NULL,'2019-04-07 17:20:41'),
(29,'timg (5).jpg',20047,'\\course_image\\2_system\\1554628843780.jpg',NULL,'admin','2019-04-07 17:20:44',NULL,'2019-04-07 17:20:44'),
(30,'timg (2).jpg',35166,'\\course_image\\2_system\\1554628854627.jpg',NULL,'admin','2019-04-07 17:20:55',NULL,'2019-04-07 17:20:55'),
(31,'timg (3).jpg',24575,'\\course_image\\2_system\\1554628854670.jpg',NULL,'admin','2019-04-07 17:20:55',NULL,'2019-04-07 17:20:55'),
(32,'timg (3).jpg',24575,'\\course_image\\2_system\\1554628997250.jpg',NULL,'admin','2019-04-07 17:23:17',NULL,'2019-04-07 17:23:17'),
(33,'timg (4).jpg',119676,'\\course_image\\2_system\\1554629001643.jpg',NULL,'admin','2019-04-07 17:23:22',NULL,'2019-04-07 17:23:22'),
(34,'timg (5).jpg',20047,'\\course_image\\2_system\\1554629001694.jpg',NULL,'admin','2019-04-07 17:23:22',NULL,'2019-04-07 17:23:22'),
(35,'timg (6).jpg',13537,'\\course_image\\2_system\\1554629008622.jpg',NULL,'admin','2019-04-07 17:23:29',NULL,'2019-04-07 17:23:29'),
(36,'timg.jpg',165396,'\\course_image\\2_system\\1554629008810.jpg',NULL,'admin','2019-04-07 17:23:29',NULL,'2019-04-07 17:23:29'),
(37,'timg (1).jpg',30940,'\\course_image\\2_system\\1554631055359.jpg',NULL,'admin','2019-04-07 17:57:35',NULL,'2019-04-07 17:57:35'),
(38,'timg (3).jpg',24575,'\\course_image\\2_system\\1554631155167.jpg',NULL,'admin','2019-04-07 17:59:15',NULL,'2019-04-07 17:59:15'),
(39,'timg (4).jpg',119676,'\\course_image\\2_system\\1554631155434.jpg',NULL,'admin','2019-04-07 17:59:15',NULL,'2019-04-07 17:59:15'),
(40,'timg (2).jpg',35166,'\\course_image\\2_system\\1554631445999.jpg',NULL,'admin','2019-04-07 18:04:06',NULL,'2019-04-07 18:04:06'),
(41,'timg (2).jpg',35166,'\\solution\\2_system\\1554631541771.jpg',NULL,'admin','2019-04-07 18:05:42',NULL,'2019-04-07 18:05:42'),
(42,'timg (3).jpg',24575,'\\solution\\2_system\\1554631541924.jpg',NULL,'admin','2019-04-07 18:05:42',NULL,'2019-04-07 18:05:42'),
(43,'timg (5).jpg',20047,'\\course_image\\2_system\\1554632334240.jpg',NULL,'admin','2019-04-07 18:18:54',NULL,'2019-04-07 18:18:54'),
(44,'timg.jpg',165396,'\\solution\\2_system\\1554632345924.jpg',NULL,'admin','2019-04-07 18:19:06',NULL,'2019-04-07 18:19:06'),
(45,'timg (3).jpg',24575,'\\course_image\\2_system\\1554632585629.jpg',NULL,'admin','2019-04-07 18:23:06',NULL,'2019-04-07 18:23:06'),
(46,'timg (5).jpg',20047,'\\solution\\2_system\\1554632591453.jpg',NULL,'admin','2019-04-07 18:23:11',NULL,'2019-04-07 18:23:11'),
(47,'timg.jpg',93542,'\\1\\1554664842631.jpg',NULL,'1','2019-04-08 03:20:43',NULL,'2019-04-08 03:20:43'),
(48,'image01.jpg',327983,'\\charity\\1\\1554781969262.jpg',NULL,'1','2019-04-09 11:52:49',NULL,'2019-04-09 11:52:49'),
(49,'timg.jpg',93542,'\\charity\\1\\1554781969415.jpg',NULL,'1','2019-04-09 11:52:49',NULL,'2019-04-09 11:52:49'),
(50,'timg01.jpg',61240,'\\charity\\1\\1554781969517.jpg',NULL,'1','2019-04-09 11:52:50',NULL,'2019-04-09 11:52:50'),
(51,'timg02.jpg',109143,'\\charity\\1\\1554781969639.jpg',NULL,'1','2019-04-09 11:52:50',NULL,'2019-04-09 11:52:50'),
(52,'timg (5).jpg',20047,'\\solution\\2_system\\1555176838922.jpg',NULL,'admin','2019-04-14 01:33:59',NULL,'2019-04-14 01:33:59'),
(53,'timg (5).jpg',20047,'\\course_image\\2_system\\1555176931703.jpg',NULL,'admin','2019-04-14 01:35:32',NULL,'2019-04-14 01:35:32'),
(54,'timg (1).jpg',30940,'\\course_image\\2_system\\1555176968422.jpg',NULL,'admin','2019-04-14 01:36:08',NULL,'2019-04-14 01:36:08'),
(55,'timg (1).jpg',30940,'\\2_system\\1556120142176.jpg',NULL,'admin','2019-04-24 23:35:42',NULL,'2019-04-24 23:35:42'),
(56,'timg (2).jpg',35166,'\\office\\2_system\\1556120721161.jpg',NULL,'admin','2019-04-24 23:45:21',NULL,'2019-04-24 23:45:21'),
(57,'timg (6).jpg',13537,'\\office\\2_system\\1556120792194.jpg',NULL,'admin','2019-04-24 23:46:32',NULL,'2019-04-24 23:46:32'),
(58,'timg(7).jpg',129097,'\\office\\2_system\\1556120835272.jpg',NULL,'admin','2019-04-24 23:47:15',NULL,'2019-04-24 23:47:15'),
(59,'timg (3).jpg',24575,'\\office\\2_system\\1556121128027.jpg',NULL,'admin','2019-04-24 23:52:08',NULL,'2019-04-24 23:52:08'),
(60,'timg(9).jpg',503901,'\\office\\2_system\\1556121313284.jpg',NULL,'admin','2019-04-24 23:55:13',NULL,'2019-04-24 23:55:13'),
(61,'timg(8).jpg',54708,'\\office\\2_system\\1556121316546.jpg',NULL,'admin','2019-04-24 23:55:17',NULL,'2019-04-24 23:55:17'),
(62,'timg(7).jpg',129097,'\\course_image\\2_system\\1556122185977.jpg',NULL,'admin','2019-04-25 00:09:46',NULL,'2019-04-25 00:09:46'),
(63,'timg(9).jpg',503901,'\\disease\\2_system\\1556122214266.jpg',NULL,'admin','2019-04-25 00:10:14',NULL,'2019-04-25 00:10:14'),
(64,'timg (6).jpg',13537,'\\disease\\2_system\\1556122218584.jpg',NULL,'admin','2019-04-25 00:10:19',NULL,'2019-04-25 00:10:19'),
(65,'timg (1).jpg',30940,'\\disease\\2_system\\1556122294863.jpg',NULL,'admin','2019-04-25 00:11:35',NULL,'2019-04-25 00:11:35'),
(66,'timg(7).jpg',129097,'\\disease\\2_system\\1556122298873.jpg',NULL,'admin','2019-04-25 00:11:39',NULL,'2019-04-25 00:11:39'),
(67,'timg(8).jpg',54708,'\\disease\\2_system\\1556122365102.jpg',NULL,'admin','2019-04-25 00:12:45',NULL,'2019-04-25 00:12:45'),
(68,'timg(8).jpg',54708,'\\disease\\2_system\\1556122368657.jpg',NULL,'admin','2019-04-25 00:12:49',NULL,'2019-04-25 00:12:49'),
(69,'timg(9).jpg',503901,'\\office\\2_system\\1556125281263.jpg',NULL,'admin','2019-04-25 01:01:21',NULL,'2019-04-25 01:01:21'),
(70,'timg (4).jpg',119676,'\\office\\2_system\\1556126374638.jpg',NULL,'admin','2019-04-25 01:19:35',NULL,'2019-04-25 01:19:35'),
(71,'timg (6).jpg',13537,'\\office\\2_system\\1556126428973.jpg',NULL,'admin','2019-04-25 01:20:29',NULL,'2019-04-25 01:20:29'),
(72,'timg(9).jpg',503901,'\\office\\2_system\\1556126805368.jpg',NULL,'admin','2019-04-25 01:26:45',NULL,'2019-04-25 01:26:45');

/*Table structure for table `tb_journey` */

DROP TABLE IF EXISTS `tb_journey`;

CREATE TABLE `tb_journey` (
  `journey_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `journey_name` varchar(100) DEFAULT NULL COMMENT '养生旅程名称',
  `summarize` varchar(5000) DEFAULT NULL COMMENT '概述',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户ID',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `cover_image` int(11) DEFAULT NULL COMMENT '封面图片',
  `note_num` int(11) DEFAULT '0' COMMENT '日记数量',
  `finished` tinyint(1) DEFAULT NULL COMMENT '是否结束',
  `read_num` int(11) DEFAULT NULL COMMENT '访问量',
  `audit` tinyint(1) NOT NULL DEFAULT '0' COMMENT '审核状态',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `recover_case` tinyint(1) DEFAULT '0' COMMENT '是否评为康复案例',
  PRIMARY KEY (`journey_id`),
  KEY `user_id` (`user_id`),
  KEY `cover_image` (`cover_image`),
  CONSTRAINT `tb_journey_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_journey_ibfk_2` FOREIGN KEY (`cover_image`) REFERENCES `tb_images` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='养生旅程表';

/*Data for the table `tb_journey` */

insert  into `tb_journey`(`journey_id`,`journey_name`,`summarize`,`user_id`,`start_time`,`end_time`,`cover_image`,`note_num`,`finished`,`read_num`,`audit`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`,`recover_case`) values 
(2,'养生旅程112',NULL,1,'2019-04-17 19:45:13',NULL,NULL,0,NULL,NULL,1,NULL,'1','2019-04-17 19:45:13',NULL,'2019-04-17 19:45:13',0),
(3,'养生旅程12','养生旅程1简要概述',1,'2019-04-17 19:53:51',NULL,NULL,0,NULL,NULL,0,NULL,'1','2019-04-17 19:53:51',NULL,'2019-04-17 19:53:51',0),
(8,'养生旅程1333332','养生旅程333331简要概述',2,'2019-04-17 23:38:40',NULL,NULL,0,NULL,3,0,NULL,'1','2019-04-17 23:38:40',NULL,'2019-04-20 19:10:30',0),
(11,'旅程名称1','概述11112',1,'2019-04-17 23:40:06',NULL,NULL,0,NULL,NULL,0,NULL,'1','2019-04-17 23:40:06','1','2019-04-17 23:42:17',0);

/*Table structure for table `tb_journey_disease` */

DROP TABLE IF EXISTS `tb_journey_disease`;

CREATE TABLE `tb_journey_disease` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `journey_id` int(11) NOT NULL COMMENT '养生旅程ID',
  `disease_detail_id` int(11) NOT NULL COMMENT '疾病小类ID',
  PRIMARY KEY (`id`),
  KEY `journey_id` (`journey_id`),
  KEY `disease_detail_id` (`disease_detail_id`),
  CONSTRAINT `tb_journey_disease_ibfk_1` FOREIGN KEY (`journey_id`) REFERENCES `tb_journey` (`journey_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_journey_disease_ibfk_2` FOREIGN KEY (`disease_detail_id`) REFERENCES `tb_disease_class_detail` (`disease_detail_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='养生旅程-疾病 关联表';

/*Data for the table `tb_journey_disease` */

/*Table structure for table `tb_journey_health` */

DROP TABLE IF EXISTS `tb_journey_health`;

CREATE TABLE `tb_journey_health` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `journey_id` int(11) NOT NULL COMMENT '养生旅程ID',
  `health_id` int(11) NOT NULL COMMENT '养生ID',
  `health_result_id` int(11) DEFAULT NULL COMMENT '养生成果ID',
  PRIMARY KEY (`id`),
  KEY `journey_id` (`journey_id`),
  KEY `health_detail_id` (`health_id`),
  KEY `health_result_id` (`health_result_id`),
  CONSTRAINT `tb_journey_health_ibfk_1` FOREIGN KEY (`journey_id`) REFERENCES `tb_journey` (`journey_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_journey_health_ibfk_2` FOREIGN KEY (`health_id`) REFERENCES `tb_health` (`health_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_journey_health_ibfk_3` FOREIGN KEY (`health_result_id`) REFERENCES `tb_health_result` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='养生旅程-养生-成果 关联表';

/*Data for the table `tb_journey_health` */

/*Table structure for table `tb_journey_norm` */

DROP TABLE IF EXISTS `tb_journey_norm`;

CREATE TABLE `tb_journey_norm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `norm_type_id` int(11) DEFAULT NULL COMMENT '体检指标类型',
  `journey_id` int(11) DEFAULT NULL COMMENT '养生旅程ID',
  `start_value1` varchar(1000) DEFAULT NULL COMMENT '开始数值1',
  `start_value2` varchar(1000) DEFAULT NULL COMMENT '开始数值2',
  `end_value1` varchar(1000) DEFAULT NULL COMMENT '结束数值1',
  `end_value2` varchar(1000) DEFAULT NULL COMMENT '结束数值2',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `journey_id` (`journey_id`),
  CONSTRAINT `tb_journey_norm_ibfk_1` FOREIGN KEY (`journey_id`) REFERENCES `tb_journey` (`journey_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='养生旅程体检指标表';

/*Data for the table `tb_journey_norm` */

insert  into `tb_journey_norm`(`id`,`norm_type_id`,`journey_id`,`start_value1`,`start_value2`,`end_value1`,`end_value2`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(3,1,2,'20',NULL,NULL,NULL,NULL,'1','2019-04-17 19:45:13',NULL,'2019-04-17 19:45:13'),
(4,3,2,'158','120',NULL,NULL,NULL,'1','2019-04-17 19:45:13',NULL,'2019-04-17 19:45:13'),
(5,1,3,'20',NULL,NULL,NULL,NULL,'1','2019-04-17 19:53:51',NULL,'2019-04-17 19:53:51'),
(6,3,3,'158','120',NULL,NULL,NULL,'1','2019-04-17 19:53:51',NULL,'2019-04-17 19:53:51'),
(15,1,8,'20',NULL,NULL,NULL,NULL,'1','2019-04-17 23:38:40',NULL,'2019-04-17 23:38:40'),
(16,3,8,'158','122',NULL,NULL,NULL,'1','2019-04-17 23:38:40',NULL,'2019-04-17 23:38:40'),
(21,1,11,'20',NULL,NULL,NULL,NULL,'1','2019-04-17 23:40:06',NULL,'2019-04-17 23:40:06'),
(22,3,11,'158','122',NULL,NULL,NULL,'1','2019-04-17 23:40:06',NULL,'2019-04-17 23:40:06');

/*Table structure for table `tb_journey_note` */

DROP TABLE IF EXISTS `tb_journey_note`;

CREATE TABLE `tb_journey_note` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `journey_id` int(11) DEFAULT NULL COMMENT '养生旅程ID',
  `cover_image_id` int(11) DEFAULT NULL COMMENT '封面图片ID',
  `content` text COMMENT '日记内容',
  `circle_of_friends` tinyint(1) DEFAULT NULL COMMENT '是否发布到朋友圈',
  `note_date` datetime DEFAULT NULL COMMENT '日记时间',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `journey_id` (`journey_id`),
  KEY `cover_image_id` (`cover_image_id`),
  CONSTRAINT `tb_journey_note_ibfk_1` FOREIGN KEY (`journey_id`) REFERENCES `tb_journey` (`journey_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_journey_note_ibfk_2` FOREIGN KEY (`cover_image_id`) REFERENCES `tb_images` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='养生旅程日记表';

/*Data for the table `tb_journey_note` */

insert  into `tb_journey_note`(`id`,`journey_id`,`cover_image_id`,`content`,`circle_of_friends`,`note_date`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,2,2,'今天喝了一瓶酒',1,'2019-04-20 18:33:43',NULL,'admin','2019-04-20 18:33:54',NULL,NULL);

/*Table structure for table `tb_journey_note_norm` */

DROP TABLE IF EXISTS `tb_journey_note_norm`;

CREATE TABLE `tb_journey_note_norm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `norm_type_id` int(11) DEFAULT NULL COMMENT '体检指标类型',
  `note_id` int(11) DEFAULT NULL COMMENT '日记ID',
  `value1` varchar(1000) DEFAULT NULL COMMENT '数值1',
  `value2` varchar(1000) DEFAULT NULL COMMENT '数值2',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `note_id` (`note_id`),
  CONSTRAINT `tb_journey_note_norm_ibfk_1` FOREIGN KEY (`note_id`) REFERENCES `tb_journey_note` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tb_journey_note_norm` */

/*Table structure for table `tb_journey_note_to_image` */

DROP TABLE IF EXISTS `tb_journey_note_to_image`;

CREATE TABLE `tb_journey_note_to_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `note_id` int(11) NOT NULL COMMENT '日记ID',
  `image_id` int(11) NOT NULL COMMENT '图片ID',
  PRIMARY KEY (`id`),
  KEY `note_id` (`note_id`),
  KEY `image_id` (`image_id`),
  CONSTRAINT `tb_journey_note_to_image_ibfk_1` FOREIGN KEY (`note_id`) REFERENCES `tb_journey_note` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_journey_note_to_image_ibfk_2` FOREIGN KEY (`image_id`) REFERENCES `tb_images` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='养生旅程日记-图片关联表';

/*Data for the table `tb_journey_note_to_image` */

/*Table structure for table `tb_journey_to_course` */

DROP TABLE IF EXISTS `tb_journey_to_course`;

CREATE TABLE `tb_journey_to_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `journey_id` int(11) NOT NULL COMMENT '养生旅程ID',
  `course_id` int(11) NOT NULL COMMENT '课程ID',
  PRIMARY KEY (`id`),
  KEY `journey_id` (`journey_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `tb_journey_to_course_ibfk_1` FOREIGN KEY (`journey_id`) REFERENCES `tb_journey` (`journey_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_journey_to_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `tb_course_registration` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='养生旅程-课程关联表';

/*Data for the table `tb_journey_to_course` */

insert  into `tb_journey_to_course`(`id`,`journey_id`,`course_id`) values 
(2,3,3),
(3,11,5);

/*Table structure for table `tb_journey_to_report` */

DROP TABLE IF EXISTS `tb_journey_to_report`;

CREATE TABLE `tb_journey_to_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `journey_id` int(11) NOT NULL COMMENT '养生旅程Id',
  `report_id` int(11) NOT NULL COMMENT '体检报告Id',
  `start_flag` tinyint(1) DEFAULT NULL COMMENT '是否是开始旅程的体检报告',
  PRIMARY KEY (`id`),
  UNIQUE KEY `journey_id` (`journey_id`,`report_id`),
  KEY `report_id` (`report_id`),
  CONSTRAINT `tb_journey_to_report_ibfk_1` FOREIGN KEY (`journey_id`) REFERENCES `tb_journey` (`journey_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_journey_to_report_ibfk_2` FOREIGN KEY (`report_id`) REFERENCES `tb_medical_report` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='养生旅程-体检报告关联表';

/*Data for the table `tb_journey_to_report` */

insert  into `tb_journey_to_report`(`id`,`journey_id`,`report_id`,`start_flag`) values 
(4,2,1,1),
(5,2,3,1),
(6,2,4,1),
(7,3,1,1),
(8,3,3,1),
(9,3,4,1),
(10,8,1,1),
(11,8,3,1),
(12,8,4,1),
(13,11,1,1),
(14,11,3,1),
(15,11,4,1);

/*Table structure for table `tb_journey_to_solution` */

DROP TABLE IF EXISTS `tb_journey_to_solution`;

CREATE TABLE `tb_journey_to_solution` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `journey_id` int(11) NOT NULL COMMENT '旅程ID',
  `solution_id` int(11) NOT NULL COMMENT '解决方案ID',
  PRIMARY KEY (`id`),
  KEY `journey_id` (`journey_id`),
  KEY `solution_id` (`solution_id`),
  CONSTRAINT `tb_journey_to_solution_ibfk_1` FOREIGN KEY (`journey_id`) REFERENCES `tb_journey` (`journey_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_journey_to_solution_ibfk_2` FOREIGN KEY (`solution_id`) REFERENCES `tb_solution` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='养生旅程-解决方案关联表';

/*Data for the table `tb_journey_to_solution` */

/*Table structure for table `tb_medical_report` */

DROP TABLE IF EXISTS `tb_medical_report`;

CREATE TABLE `tb_medical_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `file_name` varchar(100) DEFAULT NULL COMMENT '文件名称',
  `size` int(11) DEFAULT NULL COMMENT '文件大小',
  `path` varchar(100) DEFAULT NULL COMMENT '文件地址',
  `file_type` varchar(100) DEFAULT NULL COMMENT '文件类型',
  `type` int(11) DEFAULT NULL COMMENT '上传类型：0：开启养生旅程上传体检报告，1：结束养生旅程上传的体检报告',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `path` (`path`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='体检报告文件表';

/*Data for the table `tb_medical_report` */

insert  into `tb_medical_report`(`id`,`file_name`,`size`,`path`,`file_type`,`type`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'进度说明.xlsx',14444,'\\startReport\\1\\1554796125516.xlsx','application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',0,NULL,'1','2019-04-09 15:48:46',NULL,'2019-04-09 15:48:46'),
(2,'demo - 副本 (2).txt',4,'\\startReport\\1\\1555500233053.txt','text/plain',0,NULL,'1','2019-04-17 19:23:53',NULL,'2019-04-17 19:23:53'),
(3,'demo - 副本 (3).txt',4,'\\startReport\\1\\1555500233055.txt','text/plain',0,NULL,'1','2019-04-17 19:23:53',NULL,'2019-04-17 19:23:53'),
(4,'demo - 副本.txt',4,'\\startReport\\1\\1555500233056.txt','text/plain',0,NULL,'1','2019-04-17 19:23:53',NULL,'2019-04-17 19:23:53'),
(5,'demo.txt',4,'\\startReport\\1\\1555500233057.txt','text/plain',0,NULL,'1','2019-04-17 19:23:53',NULL,'2019-04-17 19:23:53'),
(6,'demo - 副本 (2).txt',4,'\\endReport\\1\\1555500349554.txt','text/plain',1,NULL,'1','2019-04-17 19:25:50',NULL,'2019-04-17 19:25:50'),
(7,'demo - 副本 (3).txt',4,'\\endReport\\1\\1555500349555.txt','text/plain',1,NULL,'1','2019-04-17 19:25:50',NULL,'2019-04-17 19:25:50'),
(8,'demo - 副本.txt',4,'\\endReport\\1\\1555500349556.txt','text/plain',1,NULL,'1','2019-04-17 19:25:50',NULL,'2019-04-17 19:25:50'),
(9,'demo.txt',4,'\\endReport\\1\\1555500349557.txt','text/plain',1,NULL,'1','2019-04-17 19:25:50',NULL,'2019-04-17 19:25:50'),
(10,'demo.txt',4,'\\endReport\\1\\1555500414011.txt','text/plain',1,NULL,'1','2019-04-17 19:26:54',NULL,'2019-04-17 19:26:54');

/*Table structure for table `tb_office` */

DROP TABLE IF EXISTS `tb_office`;

CREATE TABLE `tb_office` (
  `office_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公司ID（机构ID）',
  `name` varchar(255) NOT NULL COMMENT '公司名称',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `number` varchar(255) DEFAULT NULL COMMENT '编号',
  `contact_name` varchar(50) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(255) NOT NULL COMMENT '电话',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`office_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='相关机构表';

/*Data for the table `tb_office` */

insert  into `tb_office`(`office_id`,`name`,`address`,`description`,`number`,`contact_name`,`phone`,`memo`,`create_time`,`create_user_name`,`update_time`,`update_user_name`) values 
(3,'机构2','江苏省苏州市工业园区	',NULL,'002','carrie','1542512',NULL,'2019-04-24 23:55:19','Admin','2019-04-24 23:55:19',NULL),
(4,'机构2','江苏省苏州市工业园区',NULL,'002','andy','123466',NULL,'2019-04-25 01:01:22','Admin','2019-04-25 01:01:22',NULL),
(5,'机构2','江苏省苏州市工业园区',NULL,'002','andy','123466',NULL,'2019-04-25 01:19:36','Admin','2019-04-25 01:30:10','Admin');

/*Table structure for table `tb_office_image` */

DROP TABLE IF EXISTS `tb_office_image`;

CREATE TABLE `tb_office_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `office_id` bigint(20) NOT NULL COMMENT '公司ID',
  `image_id` int(11) NOT NULL COMMENT '图片ID',
  PRIMARY KEY (`id`),
  KEY `office_id` (`office_id`),
  KEY `image_id` (`image_id`),
  CONSTRAINT `tb_office_image_ibfk_1` FOREIGN KEY (`office_id`) REFERENCES `tb_office` (`office_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_office_image_ibfk_2` FOREIGN KEY (`image_id`) REFERENCES `tb_images` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='机构图片关联表';

/*Data for the table `tb_office_image` */

insert  into `tb_office_image`(`id`,`office_id`,`image_id`) values 
(2,3,60),
(3,3,61),
(4,4,69),
(16,5,69),
(17,5,70),
(18,5,72);

/*Table structure for table `tb_recover_case` */

DROP TABLE IF EXISTS `tb_recover_case`;

CREATE TABLE `tb_recover_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `journey_id` int(11) DEFAULT NULL COMMENT '养生旅程ID',
  `solution_id` int(11) DEFAULT NULL COMMENT '解决方案ID',
  `cover_image` int(11) DEFAULT NULL COMMENT '养生旅程对应的封面图',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `case_start_time` datetime DEFAULT NULL COMMENT '案例开始时间',
  `case_end_time` datetime DEFAULT NULL COMMENT '案例结束时间',
  `user_id` bigint(20) NOT NULL COMMENT '作者',
  `read_num` bigint(11) DEFAULT NULL COMMENT '阅读数',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `journey_id` (`journey_id`,`solution_id`),
  KEY `RECOVER_SOLUTION` (`solution_id`),
  KEY `RECOVER_USERID` (`user_id`),
  CONSTRAINT `RECOVER_SOLUTION` FOREIGN KEY (`solution_id`) REFERENCES `tb_solution` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `RECOVER_USERID` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_recover_case_ibfk_1` FOREIGN KEY (`journey_id`) REFERENCES `tb_journey` (`journey_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='康复案例表';

/*Data for the table `tb_recover_case` */

insert  into `tb_recover_case`(`id`,`journey_id`,`solution_id`,`cover_image`,`title`,`case_start_time`,`case_end_time`,`user_id`,`read_num`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,2,1,1,'康复案例1','2019-04-20 15:55:56','2019-04-20 15:55:58',1,0,NULL,NULL,NULL,NULL,NULL),
(2,3,2,1,'康复案例2','2019-04-20 15:56:21','2019-04-20 15:56:23',1,0,NULL,NULL,NULL,NULL,NULL),
(3,3,1,2,'康复案例3','2019-04-20 15:56:49','2019-04-20 15:56:51',1,0,NULL,NULL,NULL,NULL,NULL),
(5,11,1,3,'康复案例5','2019-04-20 15:57:40','2019-04-20 15:57:42',2,0,NULL,NULL,NULL,NULL,NULL),
(6,11,2,2,'康复案例6','2019-04-20 15:58:15','2019-04-20 15:58:16',2,0,NULL,NULL,NULL,NULL,NULL),
(7,8,3,1,'康复案例7','2019-04-20 18:25:03','2019-04-20 18:25:04',2,1,NULL,NULL,NULL,NULL,'2019-04-20 19:10:30'),
(8,2,2,3,'养生旅程112',NULL,NULL,1,NULL,NULL,'Admin','2019-04-20 21:16:14',NULL,'2019-04-20 21:16:14');

/*Table structure for table `tb_solution` */

DROP TABLE IF EXISTS `tb_solution`;

CREATE TABLE `tb_solution` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `number` varchar(30) DEFAULT NULL COMMENT '编码',
  `title` varchar(255) DEFAULT NULL COMMENT '解决方案名称',
  `content` text COMMENT '解决方案',
  `agency_id` int(11) DEFAULT NULL COMMENT '机构ID',
  `recover_number` int(11) DEFAULT NULL COMMENT '康复人数',
  `read_num` int(11) DEFAULT NULL COMMENT '阅读数',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='解决方案表';

/*Data for the table `tb_solution` */

insert  into `tb_solution`(`id`,`number`,`title`,`content`,`agency_id`,`recover_number`,`read_num`,`memo`,`create_time`,`create_user_name`,`update_time`,`update_user_name`) values 
(1,'001','解决方案1','内容1',NULL,NULL,NULL,NULL,'2019-04-01 23:00:08','admin','2019-04-01 23:00:08',NULL),
(2,'002','解决方案2','内容2',NULL,1,NULL,NULL,'2019-04-01 23:01:19','admin','2019-04-20 21:16:14',NULL),
(3,'001','方案一','<p style=\"color: red;\"><strong>1、111111111111111</strong></p>\n<p><span style=\"text-decoration: underline; color: #33cccc;\">2、22222222222222222222</span></p>\n<p><span style=\"text-decoration: underline; color: #33cccc;\">3、333333333333333333</span></p>\n<p><em>4、44444444444444444</em></p>\n<p><em>5、55555555555555555</em></p>\n<p>&nbsp;</p>\n<p><em><img class=\"wscnph\" src=\"http://192.168.0.216:8087/smile-admin/image/course_image/2_system/1554632585629.jpg\" /></em></p>',NULL,NULL,NULL,NULL,'2019-04-07 18:23:17','admin','2019-04-07 18:23:17',NULL);

/*Table structure for table `tb_solution_course` */

DROP TABLE IF EXISTS `tb_solution_course`;

CREATE TABLE `tb_solution_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `course_id` int(11) NOT NULL COMMENT '课程ID',
  `solution_id` int(11) NOT NULL COMMENT '解决方案ID',
  PRIMARY KEY (`id`),
  KEY `FK4flp4y3r6f347dmkscxnvo1vm` (`course_id`),
  KEY `solution_id` (`solution_id`),
  CONSTRAINT `tb_solution_course_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `tb_course_registration` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_solution_course_ibfk_2` FOREIGN KEY (`solution_id`) REFERENCES `tb_solution` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='解决方案-课程关联表';

/*Data for the table `tb_solution_course` */

insert  into `tb_solution_course`(`id`,`course_id`,`solution_id`) values 
(2,2,1),
(5,3,3);

/*Table structure for table `tb_solution_image` */

DROP TABLE IF EXISTS `tb_solution_image`;

CREATE TABLE `tb_solution_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `image_id` int(11) NOT NULL COMMENT '图片ID',
  `solution_id` int(11) NOT NULL COMMENT '解决方案ID',
  PRIMARY KEY (`id`),
  KEY `FKcyvwu4jagqp5edfjyx4ksfcbe` (`image_id`),
  KEY `FKctuum5udt0f1b2s6m6wf36h31` (`solution_id`),
  CONSTRAINT `tb_solution_image_ibfk_1` FOREIGN KEY (`image_id`) REFERENCES `tb_images` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_solution_image_ibfk_2` FOREIGN KEY (`solution_id`) REFERENCES `tb_solution` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='解决方案-图片 关联表';

/*Data for the table `tb_solution_image` */

insert  into `tb_solution_image`(`id`,`image_id`,`solution_id`) values 
(1,3,1),
(2,2,2),
(3,2,3);

/*Table structure for table `tb_solution_office` */

DROP TABLE IF EXISTS `tb_solution_office`;

CREATE TABLE `tb_solution_office` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `office_id` bigint(11) NOT NULL COMMENT '相关机构ID',
  `solution_id` int(11) NOT NULL COMMENT '解决方案ID',
  PRIMARY KEY (`id`),
  KEY `FKl6pkfwh5hukp5ceild4a68orc` (`office_id`),
  KEY `solution_id` (`solution_id`),
  CONSTRAINT `tb_solution_office_ibfk_1` FOREIGN KEY (`office_id`) REFERENCES `tb_office` (`office_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_solution_office_ibfk_2` FOREIGN KEY (`solution_id`) REFERENCES `tb_solution` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_solution_office` */

/*Table structure for table `tb_sys_department` */

DROP TABLE IF EXISTS `tb_sys_department`;

CREATE TABLE `tb_sys_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `department_name` varchar(50) NOT NULL COMMENT '部门名称',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user_name` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKmxve8thp9w83v4jef4w1hpqxy` (`parent_id`) USING BTREE,
  CONSTRAINT `FKmxve8thp9w83v4jef4w1hpqxy` FOREIGN KEY (`parent_id`) REFERENCES `tb_sys_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='组织机构';

/*Data for the table `tb_sys_department` */

insert  into `tb_sys_department`(`id`,`department_name`,`address`,`icon`,`parent_id`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'IT部',NULL,NULL,NULL,NULL,'lilei','2018-08-21 14:30:00',NULL,NULL);

/*Table structure for table `tb_sys_parameter` */

DROP TABLE IF EXISTS `tb_sys_parameter`;

CREATE TABLE `tb_sys_parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `parameter_key` varchar(50) NOT NULL COMMENT '参数Key',
  `parameter_value` varchar(200) NOT NULL COMMENT '参数值',
  `parameter_desc` varchar(500) DEFAULT NULL COMMENT '描述',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) NOT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者ID',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UN_SYS_PARAMETER` (`parameter_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='平台参数';

/*Data for the table `tb_sys_parameter` */

insert  into `tb_sys_parameter`(`id`,`parameter_key`,`parameter_value`,`parameter_desc`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'file_path','D:\\cache\\smile\\','上传文件存放路径',NULL,'system','2019-02-23 19:12:24',NULL,NULL),
(2,'domain_port','http://192.168.0.104:8087','项目域名端口',NULL,'system','2019-03-27 17:29:34',NULL,NULL),
(3,'domain_port_app','http://192.168.0.104:8086','APP接口域名端口',NULL,'system','2019-04-04 23:57:33',NULL,NULL),
(4,'recent_course','30','近期课程时间区间（天）',NULL,'system','2019-04-20 13:26:05',NULL,NULL);

/*Table structure for table `tb_sys_resource` */

DROP TABLE IF EXISTS `tb_sys_resource`;

CREATE TABLE `tb_sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `description` varchar(255) DEFAULT NULL COMMENT '资源描述',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `resource_type` int(11) DEFAULT NULL COMMENT '资源类型',
  `seq` int(11) DEFAULT NULL COMMENT '序列',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `url` varchar(255) DEFAULT NULL COMMENT 'URL',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`),
  KEY `FK4lpaa8jnibgih8ygmnh5n0gcg` (`parent_id`),
  CONSTRAINT `tb_sys_resource_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `tb_sys_resource` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

/*Data for the table `tb_sys_resource` */

/*Table structure for table `tb_sys_role` */

DROP TABLE IF EXISTS `tb_sys_role`;

CREATE TABLE `tb_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `is_admin` bit(1) DEFAULT NULL COMMENT '是否是管理员',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `role` varchar(255) NOT NULL COMMENT '权限',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系统权限表';

/*Data for the table `tb_sys_role` */

/*Table structure for table `tb_sys_user` */

DROP TABLE IF EXISTS `tb_sys_user`;

CREATE TABLE `tb_sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `login_name` varchar(50) NOT NULL COMMENT '登录名',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `department_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '帐号状态',
  `login_limit` int(11) DEFAULT '1' COMMENT '登录限制（1:允许登录;0:限制登录）',
  `expiration_time` datetime DEFAULT NULL COMMENT '过期时间（为NULL表示永不过期）',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `mobile_phone` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `tel` varchar(100) DEFAULT NULL COMMENT '电话号码',
  `memo` varchar(2000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK2o8qjcjkx6b7fo2utcyn7jper` (`department_id`) USING BTREE,
  CONSTRAINT `FK2o8qjcjkx6b7fo2utcyn7jper` FOREIGN KEY (`department_id`) REFERENCES `tb_sys_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户表';

/*Data for the table `tb_sys_user` */

insert  into `tb_sys_user`(`id`,`login_name`,`user_name`,`password`,`department_id`,`status`,`login_limit`,`expiration_time`,`email`,`mobile_phone`,`tel`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'lilei','李磊','e10adc3949ba59abbe56e057f20f883e',1,1,1,NULL,'lilei@oristand.com','15850374663',NULL,NULL,'lilei','2018-08-21 13:29:15',NULL,NULL),
(2,'admin','Admin','$2a$10$P9PQ3beKXePgTigCNnzw.eFKZnDshgd0ZDhtjRRAH5GMrykYVde.W',1,1,1,NULL,NULL,NULL,NULL,NULL,'lilei','2018-08-21 13:29:53',NULL,NULL);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `mobile` varchar(11) NOT NULL COMMENT '手机号',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(30) DEFAULT NULL COMMENT '昵称',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证',
  `login_time` datetime DEFAULT NULL COMMENT '登陆时间',
  `login_count` int(11) DEFAULT NULL COMMENT '登陆次数',
  `photo` int(11) DEFAULT NULL COMMENT '头像',
  `height` double DEFAULT NULL COMMENT '身高',
  `body_weight` double DEFAULT NULL COMMENT '体重',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `ip` varchar(32) DEFAULT NULL COMMENT 'IP地址',
  `token` varchar(500) DEFAULT NULL COMMENT 'Token',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `photo` (`photo`),
  CONSTRAINT `tb_user_ibfk_1` FOREIGN KEY (`photo`) REFERENCES `tb_images` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='APP用户表';

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`mobile`,`user_name`,`password`,`nick_name`,`id_card`,`login_time`,`login_count`,`photo`,`height`,`body_weight`,`birthday`,`ip`,`token`,`memo`,`create_time`,`create_user_name`,`update_time`,`update_user_name`) values 
(1,'15850374663','15850374663',NULL,NULL,NULL,'2019-04-23 23:53:33',41,1,176,60,'1993-07-28','0:0:0:0:0:0:0:1','eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6IjE1ODUwMzc0NjYzXzE1NTYwMzQ4MTI1NTEiLCJpYXQiOjE1NTYwMzQ4MTIsImV4cCI6MTU2MzgxMDgxMn0.QDJTKeglK2b77RrJg-OfNT2bAot81zRitMEbm2zrCkNFsWEnj8MEkc-3MM5ktvcGVCAdLggCU-41K45TfZ0Gjg',NULL,NULL,'system','2019-04-23 23:53:33',NULL),
(2,'18914050263','18914050263',NULL,NULL,NULL,'2019-04-04 01:13:49',2,1,NULL,NULL,NULL,'101.84.2.198','eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6IjE4OTE0MDUwMjYzXzE1NTQzMTE2Mjg5NjciLCJpYXQiOjE1NTQzMTE2MjgsImV4cCI6MTU2MjA4NzYyOH0.zRP7AaxhQTj0cSa_zBk_ryc83K4mDsF7w3y32NdogipKL_PrUnPa0zw-hb8kbfSvlsh5dub0SzytUtuoaungvw',NULL,NULL,'system',NULL,NULL);

/*Table structure for table `tb_user_charity_fault_integral` */

DROP TABLE IF EXISTS `tb_user_charity_fault_integral`;

CREATE TABLE `tb_user_charity_fault_integral` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `score` int(11) NOT NULL DEFAULT '0' COMMENT '积分总数',
  `charity_count` int(11) NOT NULL DEFAULT '0' COMMENT '行善个数',
  `fault_count` int(11) NOT NULL DEFAULT '0' COMMENT '过失个数',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COMMENT='用户行善过失积分表\r\n';

/*Data for the table `tb_user_charity_fault_integral` */

insert  into `tb_user_charity_fault_integral`(`id`,`score`,`charity_count`,`fault_count`,`user_id`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,3,4,1,1,NULL,'1','2019-04-20 14:34:53','1','2019-04-20 14:37:31'),
(2,10,5,4,2,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tb_user_charity_fault_record` */

DROP TABLE IF EXISTS `tb_user_charity_fault_record`;

CREATE TABLE `tb_user_charity_fault_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `type` int(20) DEFAULT NULL COMMENT '0:善行1，过失',
  `content` text COMMENT '内容记录',
  `charity_time_start` datetime DEFAULT NULL COMMENT '行善日期,开始时间',
  `charity_time_end` datetime DEFAULT NULL COMMENT '行善日期,结束时间',
  `cf_type_id` int(11) DEFAULT NULL COMMENT '行善类型ID',
  `type_content_id` int(11) DEFAULT NULL COMMENT '行善类型对应的内容ID',
  `donate_amount` decimal(11,2) DEFAULT NULL COMMENT '捐款金额',
  `material_details` varchar(500) DEFAULT NULL COMMENT '物资详情',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户行善过失记录表';

/*Data for the table `tb_user_charity_fault_record` */

insert  into `tb_user_charity_fault_record`(`id`,`user_id`,`type`,`content`,`charity_time_start`,`charity_time_end`,`cf_type_id`,`type_content_id`,`donate_amount`,`material_details`,`longitude`,`latitude`,`address`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(14,1,0,'demo','2019-04-20 12:00:00','2019-04-20 12:00:00',1,NULL,15.80,NULL,NULL,NULL,NULL,NULL,'1','2019-04-20 14:34:53',NULL,'2019-04-20 14:34:53'),
(15,1,0,'demo','2019-04-20 12:00:00','2019-04-20 12:00:00',1,NULL,15.80,NULL,NULL,NULL,NULL,NULL,'1','2019-04-20 14:35:11',NULL,'2019-04-20 14:35:11'),
(16,1,1,'demo','2019-04-20 12:00:00','2019-04-20 12:00:00',2,NULL,15.80,NULL,NULL,NULL,NULL,NULL,'1','2019-04-20 14:36:00',NULL,'2019-04-20 14:36:00'),
(17,1,0,'demo','2019-04-20 12:00:00','2019-04-20 12:00:00',8,NULL,15.80,NULL,NULL,NULL,NULL,NULL,'1','2019-04-20 14:36:51',NULL,'2019-04-20 14:36:51'),
(18,1,0,'demo','2019-04-20 12:00:00','2019-04-20 12:00:00',8,2,15.80,NULL,NULL,NULL,NULL,NULL,'1','2019-04-20 14:37:31',NULL,'2019-04-20 14:37:31');

/*Table structure for table `tb_user_follow_course` */

DROP TABLE IF EXISTS `tb_user_follow_course`;

CREATE TABLE `tb_user_follow_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `course_id` int(11) NOT NULL COMMENT '课程ID',
  `name` varchar(100) NOT NULL COMMENT '预约报名姓名',
  `phone` varchar(100) NOT NULL COMMENT '手机号码',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `tb_user_follow_course_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_user_follow_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `tb_course_registration` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户预约课程表';

/*Data for the table `tb_user_follow_course` */

insert  into `tb_user_follow_course`(`id`,`user_id`,`course_id`,`name`,`phone`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(2,1,3,'李四','15850374663',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tb_user_follow_disease` */

DROP TABLE IF EXISTS `tb_user_follow_disease`;

CREATE TABLE `tb_user_follow_disease` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `disease_detail_id` int(11) DEFAULT NULL COMMENT '疾病小类ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `disease_detail_id` (`disease_detail_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `USER_FOLLOW_DISEASE` FOREIGN KEY (`disease_detail_id`) REFERENCES `tb_disease_class_detail` (`disease_detail_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_user_follow_disease_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户关注疾病表';

/*Data for the table `tb_user_follow_disease` */

/*Table structure for table `tb_user_follow_solution` */

DROP TABLE IF EXISTS `tb_user_follow_solution`;

CREATE TABLE `tb_user_follow_solution` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `solution_id` int(11) NOT NULL COMMENT '解决方案ID',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `solution_id` (`solution_id`),
  CONSTRAINT `tb_user_follow_solution_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_user_follow_solution_ibfk_2` FOREIGN KEY (`solution_id`) REFERENCES `tb_solution` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户收藏解决方案表';

/*Data for the table `tb_user_follow_solution` */

/*Table structure for table `tb_user_to_dynamic_detail_like` */

DROP TABLE IF EXISTS `tb_user_to_dynamic_detail_like`;

CREATE TABLE `tb_user_to_dynamic_detail_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `dynamic_detail_id` int(20) NOT NULL COMMENT '动态明细ID',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `dynamic_detail_id` (`dynamic_detail_id`),
  CONSTRAINT `tb_user_to_dynamic_detail_like_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_user_to_dynamic_detail_like_ibfk_2` FOREIGN KEY (`dynamic_detail_id`) REFERENCES `tb_dynamic_details` (`dynamic_detail_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户点赞动态明细表';

/*Data for the table `tb_user_to_dynamic_detail_like` */

/*Table structure for table `tb_user_to_message_like` */

DROP TABLE IF EXISTS `tb_user_to_message_like`;

CREATE TABLE `tb_user_to_message_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `message_id` int(11) NOT NULL COMMENT '评论ID',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `message_id` (`message_id`),
  CONSTRAINT `tb_user_to_message_like_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_user_to_message_like_ibfk_2` FOREIGN KEY (`message_id`) REFERENCES `tb_dynamic_detail_message` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户点赞动态评论表';

/*Data for the table `tb_user_to_message_like` */

/*Table structure for table `vw_disease_health_recover_case` */

DROP TABLE IF EXISTS `vw_disease_health_recover_case`;

/*!50001 DROP VIEW IF EXISTS `vw_disease_health_recover_case` */;
/*!50001 DROP TABLE IF EXISTS `vw_disease_health_recover_case` */;

/*!50001 CREATE TABLE  `vw_disease_health_recover_case`(
 `id` int(11) ,
 `journey_id` int(11) ,
 `solution_id` int(11) ,
 `title` varchar(255) ,
 `case_start_time` datetime ,
 `case_end_time` datetime ,
 `user_id` bigint(20) ,
 `read_num` bigint(20) ,
 `memo` text ,
 `create_user_name` varchar(30) ,
 `create_time` datetime ,
 `update_user_name` varchar(30) ,
 `update_time` datetime ,
 `disease_detail_id` int(11) ,
 `health_id` int(11) 
)*/;

/*Table structure for table `vw_disease_health_solution` */

DROP TABLE IF EXISTS `vw_disease_health_solution`;

/*!50001 DROP VIEW IF EXISTS `vw_disease_health_solution` */;
/*!50001 DROP TABLE IF EXISTS `vw_disease_health_solution` */;

/*!50001 CREATE TABLE  `vw_disease_health_solution`(
 `id` int(11) ,
 `number` varchar(30) ,
 `title` varchar(255) ,
 `content` mediumtext ,
 `agency_id` int(11) ,
 `recover_number` int(11) ,
 `read_num` int(11) ,
 `memo` text ,
 `create_time` datetime ,
 `create_user_name` varchar(30) ,
 `update_time` datetime ,
 `update_user_name` varchar(30) ,
 `disease_detail_id` int(11) ,
 `health_id` int(11) 
)*/;

/*View structure for view vw_disease_health_recover_case */

/*!50001 DROP TABLE IF EXISTS `vw_disease_health_recover_case` */;
/*!50001 DROP VIEW IF EXISTS `vw_disease_health_recover_case` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `vw_disease_health_recover_case` AS select `rc`.`id` AS `id`,`rc`.`journey_id` AS `journey_id`,`rc`.`solution_id` AS `solution_id`,`rc`.`title` AS `title`,`rc`.`case_start_time` AS `case_start_time`,`rc`.`case_end_time` AS `case_end_time`,`rc`.`user_id` AS `user_id`,`rc`.`read_num` AS `read_num`,`rc`.`memo` AS `memo`,`rc`.`create_user_name` AS `create_user_name`,`rc`.`create_time` AS `create_time`,`rc`.`update_user_name` AS `update_user_name`,`rc`.`update_time` AS `update_time`,`dcd`.`disease_detail_id` AS `disease_detail_id`,NULL AS `health_id` from ((((`tb_disease_class_detail` `dcd` left join `tb_disease_reason` `dr` on((`dcd`.`disease_detail_id` = `dr`.`disease_detail_id`))) left join `tb_disease_reason_to_solution` `drts` on((`dr`.`id` = `drts`.`disease_reason_id`))) join `tb_solution` `s` on((`drts`.`solution_id` = `s`.`id`))) join `tb_recover_case` `rc` on((`rc`.`solution_id` = `s`.`id`))) union select `rc`.`id` AS `id`,`rc`.`journey_id` AS `journey_id`,`rc`.`solution_id` AS `solution_id`,`rc`.`title` AS `title`,`rc`.`case_start_time` AS `case_start_time`,`rc`.`case_end_time` AS `case_end_time`,`rc`.`user_id` AS `user_id`,`rc`.`read_num` AS `read_num`,`rc`.`memo` AS `memo`,`rc`.`create_user_name` AS `create_user_name`,`rc`.`create_time` AS `create_time`,`rc`.`update_user_name` AS `update_user_name`,`rc`.`update_time` AS `update_time`,NULL AS `disease_detail_id`,`h`.`health_id` AS `health_id` from (((`tb_health` `h` left join `tb_health_to_solution` `hts` on((`h`.`health_id` = `hts`.`health_id`))) join `tb_solution` `s` on((`hts`.`solution_id` = `s`.`id`))) join `tb_recover_case` `rc` on((`s`.`id` = `rc`.`solution_id`))) */;

/*View structure for view vw_disease_health_solution */

/*!50001 DROP TABLE IF EXISTS `vw_disease_health_solution` */;
/*!50001 DROP VIEW IF EXISTS `vw_disease_health_solution` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `vw_disease_health_solution` AS select `s`.`id` AS `id`,`s`.`number` AS `number`,`s`.`title` AS `title`,`s`.`content` AS `content`,`s`.`agency_id` AS `agency_id`,`s`.`recover_number` AS `recover_number`,`s`.`read_num` AS `read_num`,`s`.`memo` AS `memo`,`s`.`create_time` AS `create_time`,`s`.`create_user_name` AS `create_user_name`,`s`.`update_time` AS `update_time`,`s`.`update_user_name` AS `update_user_name`,`dcd`.`disease_detail_id` AS `disease_detail_id`,NULL AS `health_id` from (((`tb_disease_class_detail` `dcd` left join `tb_disease_reason` `dr` on((`dcd`.`disease_detail_id` = `dr`.`disease_detail_id`))) left join `tb_disease_reason_to_solution` `drts` on((`dr`.`id` = `drts`.`disease_reason_id`))) join `tb_solution` `s` on((`drts`.`solution_id` = `s`.`id`))) union select `s`.`id` AS `id`,`s`.`number` AS `number`,`s`.`title` AS `title`,`s`.`content` AS `content`,`s`.`agency_id` AS `agency_id`,`s`.`recover_number` AS `recover_number`,`s`.`read_num` AS `read_num`,`s`.`memo` AS `memo`,`s`.`create_time` AS `create_time`,`s`.`create_user_name` AS `create_user_name`,`s`.`update_time` AS `update_time`,`s`.`update_user_name` AS `update_user_name`,NULL AS `disease_detail_id`,`h`.`health_id` AS `health_id` from ((`tb_health` `h` left join `tb_health_to_solution` `hts` on((`h`.`health_id` = `hts`.`health_id`))) join `tb_solution` `s` on((`hts`.`solution_id` = `s`.`id`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
