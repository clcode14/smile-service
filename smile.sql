/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.7.23-log : Database - smile_test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`smile_test` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `smile_test`;

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='行善过失-图片关联表';

/*Data for the table `tb_charity_fault_record_image` */

insert  into `tb_charity_fault_record_image`(`id`,`image_id`,`charity_fault_record_id`) values 
(21,160,19),
(22,161,20),
(23,162,21);

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
(1,1,'世界和平1323',NULL,'system','2019-05-15 14:24:35','admin','2019-05-15 14:25:12'),
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

/*Table structure for table `tb_commodity` */

DROP TABLE IF EXISTS `tb_commodity`;

CREATE TABLE `tb_commodity` (
  `commodity_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `sku` varchar(1000) DEFAULT NULL COMMENT '商品SKU',
  `name` varchar(500) DEFAULT NULL COMMENT '商品名称',
  `introduce` text COMMENT '商品介绍',
  `description` text COMMENT '商品详情',
  `price` decimal(9,2) DEFAULT NULL COMMENT '商品价格',
  `freight` decimal(9,2) DEFAULT NULL COMMENT '运费',
  `freight_type` int(11) DEFAULT '1' COMMENT '0:包邮 1:不包邮',
  `memo` varchar(1000) CHARACTER SET latin1 DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(50) CHARACTER SET latin1 DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(50) CHARACTER SET latin1 DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`commodity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tb_commodity` */

insert  into `tb_commodity`(`commodity_id`,`sku`,`name`,`introduce`,`description`,`price`,`freight`,`freight_type`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(2,'001','商品1','商品lksjdklajslkdjaslkdj','商品lksjdklajslkdjaslkdjdsdad\r\nasdasd\r\nasdasdadadasd',10.00,10.00,1,NULL,NULL,NULL,NULL,NULL),
(3,'002','商品2','商品lksjdklajslkdjaslkdj','商品lksjdklajslkdjaslkdjdsdad\r\nasdasd\r\nasdasdadadasd',11.00,22.00,1,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tb_commodity_image` */

DROP TABLE IF EXISTS `tb_commodity_image`;

CREATE TABLE `tb_commodity_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `commodity_id` int(11) NOT NULL COMMENT '商品ID',
  `image_id` int(11) NOT NULL COMMENT '图片ID',
  PRIMARY KEY (`id`),
  KEY `commodity_id` (`commodity_id`),
  KEY `image_id` (`image_id`),
  CONSTRAINT `tb_commodity_image_ibfk_1` FOREIGN KEY (`commodity_id`) REFERENCES `tb_commodity` (`commodity_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_commodity_image_ibfk_2` FOREIGN KEY (`image_id`) REFERENCES `tb_images` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `tb_commodity_image` */

insert  into `tb_commodity_image`(`id`,`commodity_id`,`image_id`) values 
(1,2,110),
(3,2,108),
(4,2,109);

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `tb_course_banner` */

insert  into `tb_course_banner`(`id`,`status`,`course_id`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(9,1,8,NULL,'admin','2019-05-15 21:08:44',NULL,'2019-05-15 21:08:44'),
(10,1,9,NULL,'admin','2019-05-15 21:08:44',NULL,'2019-05-15 21:08:44'),
(11,1,10,NULL,'admin','2019-05-15 21:08:44',NULL,'2019-05-15 21:08:44');

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='课程图片表';

/*Data for the table `tb_course_image` */

insert  into `tb_course_image`(`id`,`course_id`,`image_id`) values 
(12,10,127),
(13,10,128),
(14,11,130),
(15,11,131),
(25,8,121),
(26,8,122),
(27,8,123),
(28,9,125);

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='课程表';

/*Data for the table `tb_course_registration` */

insert  into `tb_course_registration`(`course_id`,`title`,`start_time`,`price`,`members`,`apply_count`,`address`,`cover_image_id`,`description`,`type_id`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(8,'健身课','2019-05-18 07:00:00',150.0000,50,NULL,'北京市东城区安定门东大街28号雍和大厦D座1005室(5号线地铁雍和宫,2号线雍和宫B口往东50米); ',120,'<div class=\"course-light\">\n<h1 class=\"title\"><span style=\"color: #ff6600; font-family: simsun, serif;\"><strong>课程亮点</strong></span></h1>\n<div class=\"cl-preview-section\">\n<ul>\n<li><span style=\"font-size: 18px;\">从浅入深循序渐进，既可以当做手册来看，又能学习到 Webpack 原理实现；</span></li>\n<li><span style=\"font-size: 18px;\">提供大量 Tips 技巧，Webpack 知识点面试再也不怕；</span></li>\n<li><span style=\"font-size: 18px;\">介绍周边知识点，开阔眼界；</span></li>\n<li><span style=\"font-size: 18px;\">介绍作者真实项目的最佳实践，可以直接在自己项目进行实践。</span></li>\n</ul>\n</div>\n</div>\n<div class=\"detailcontent js-lookimg\">\n<p class=\"title\"><span style=\"font-size: 22px;\">课程介绍</span></p>\n<div class=\"cl-preview-section\">\n<p>前端工程正变得越来越复杂，模块越来越多，用好的工具管理我们的前端工程必不可少，Webpack 就是其中的佼佼者，也是市面上的主流。这两年Webpack 的使用率直线上升，Vue、React、Angular 三大框架的脚手架工具都开始使用 Webpack 来做底层代码的构建。</p>\n</div>\n<div class=\"cl-preview-section\">\n<p>关于 Webpack 的内容几乎是前端面试必问，笔试必考，工作必用的内容，甚至可以说，不会 Webpack，你连进大厂的资格都没有。</p>\n</div>\n<div class=\"cl-preview-section\">\n<p>因此很多同学在学 Webpack，但很多人都没学好，甚至没学会，对着官方文档一顿&ldquo;啃&rdquo;，掌握了概念，明白了一些，却依然似懂非懂，不能灵活运用。如果你正是这样，那你真的应该看看这个专栏。</p>\n</div>\n<div class=\"cl-preview-section\">\n<p>我是&ldquo;三水清&rdquo;，曾就职于新浪、腾讯等一线大厂，现在某一线大厂负责&ldquo;前端团队中台技术搭建和工程化建设&rdquo;，在工作中直接带领团队成员使用 Webpack，对 Webpack 在面试中会问的，笔试中会考的，工作中会用的东西非常熟悉，另外，对 Webpack 的配置、使用中会遇到&ldquo;坑&rdquo;，甚至 Webpack 内核原理也都有丰富的实践经验和研究。</p>\n</div>\n<div class=\"cl-preview-section\">\n<p>这个专栏不仅是一个 Webpack 的学习专栏，有大量的Tips，更是一个比官方文档更实用，更贴合实际应用的&ldquo;Webpack手册&rdquo;， 是一个你可以&ldquo;长期使用&ldquo;，&ldquo;随时可查&rdquo;的工具，工作中遇到的实际问题，你甚至能在专栏中找到直接或类似的配置方法、解决方案，因为这其中汇聚了我多年来的实践经验和具体实例！</p>\n</div>\n<div class=\"cl-preview-section\">\n<p>不管你是前端小白还是想在团队工程化建设中有所作的leader，这个专栏都会对你有很大的帮助。</p>\n</div>\n<div class=\"cl-preview-section\">\n<p>前端技术日新月异，要想跟的上技术潮流，就要不断学习，而学习最好的方式是输出。当我写专栏时，我也是在总结和进步，我希望大家和我一起参与这个过程。<br />我会不定期补充新的内容，保持对 Webpack 的关注，有新的动向就会及时分享给你，让这个专栏保持新鲜，让专栏内容始终能够适用。另，本专栏思考问题的方式和工程化思维也一定会让你在编程思想上有所增益，不要忽视这些看不见的内功。</p>\n</div>\n<div class=\"cl-preview-section\">\n<p>最后，给大家一个小彩蛋！专栏每小节末会针对本小节内容补加面试中容易被考到的高频面试题。这样你在学习时会更有针对性，面试时也会更从容，这个算是我送给大家的小惊喜吧！</p>\n</div>\n<div class=\"cl-preview-section\">\n<h3 id=\"专栏模块\"><span style=\"font-size: 22px;\"><strong>专栏模块</strong></span></h3>\n</div>\n<div class=\"cl-preview-section\">\n<p>专栏共 37 讲，分为三大部分和六大模块。按照学习知识循序渐进和解决日常开发的环节来划分为三个大部分，分别为：Webpack 开发配置、Webpack 内核原理和工程化实践。</p>\n</div>\n<div class=\"cl-preview-section\">\n<ul>\n<li>Webpack 开发配置：我会从基础概念、日常开发、上线优化三大模块来讲解；</li>\n<li>Webpack 内核原理：学习 Webpack 的原理实现才能够让我们得心应手的使用它；</li>\n<li>工程化实践：这个部分我会结合自己项目的实践，介绍一些基于 Webpack 打造的前端工程化解决方案。</li>\n</ul>\n</div>\n<div class=\"cl-preview-section\">\n<p>下面是六大模块的详细课程介绍：</p>\n</div>\n<div class=\"cl-preview-section\">\n<p><span style=\"font-size: 22px;\"><strong>入门篇</strong></span></p>\n</div>\n<div class=\"cl-preview-section\">\n<p>介绍 Webpack 的基本概念和解决的问题，从零搭建 Webpack 构建环境，带领大家体验 Webpack-CLI 的零配置打包，介绍 Webpack 的核心概念和基础配置。</p>\n</div>\n<div class=\"cl-preview-section\">\n<p><span style=\"font-size: 22px;\"><strong>配置篇</strong></span><br />从模块化开发、Babel、React/Vue 配置、TypeScript 配置、CSS 样式相关配置和静态资源管理，到 Dev Server 环境配置，带领大家搭建基础开发环境。</p>\n</div>\n<div class=\"cl-preview-section\">\n<p><span style=\"font-size: 22px;\"><strong>优化篇</strong></span><br />在优化篇，会针对生产环境的优化手段，从静态资源体积、缓存管理和 Webpack 打包速度优化进行深入讲解，并且对 Webpack 的代码拆分（SplitChunk）和 Tree-Shaking 代码级别的实践讲解。</p>\n</div>\n<div class=\"cl-preview-section\">\n<p><span style=\"font-size: 22px;\"><strong>原理篇</strong></span><br />介绍 Webpack 的核心机制实现，学习 Webpack 的核心模块 Tapable 的实现和用法，更加深入的理解 Webpack 的工作流程，理解 Webpack 的 Compiler 和 Compilation 两个对象。</p>\n</div>\n<div class=\"cl-preview-section\">\n<p><span style=\"font-size: 22px;\"><strong>实战篇</strong></span></p>\n</div>\n<div class=\"cl-preview-section\">\n<p>学习完 Webpack 的基础知识和内核原理，和大家一起动手从工程化和项目实战角度，通过解决项目中的实际问题，来体验 Webpack 的强大功能。</p>\n</div>\n<div class=\"cl-preview-section\">\n<p><span style=\"font-size: 22px;\"><strong>总结篇</strong></span><br />本章节内容偏总结和手册功能，只要内容包括课程总结、讲解 Webpack 5.0 的新技术和常用 Loader、Plugin 插件列表</p>\n</div>\n</div>',4,NULL,'admin','2019-05-15 20:52:09','admin','2019-05-15 23:12:54'),
(9,'瑜伽课','2019-05-30 20:52:24',500.0000,60,NULL,'苏州市',124,'<h1 class=\"section-title\"><span style=\"font-family: symbol;\">课程简介</span></h1>\n<h4 class=\"section-sub-title\"><span style=\"font-family: arial, helvetica, sans-serif;\">Course</span></h4>\n<div class=\"single-course-detail-wrap\">\n<p><img class=\"alignnone size-full wp-image-5845\" src=\"https://www.shtsn.com/wp-content/uploads/2018/11/2018110511123581.jpg\" alt=\"RPTC私人教练培训课程内容\" width=\"1080\" height=\"15165\" /></p>\n</div>',5,NULL,'admin','2019-05-15 20:54:22','admin','2019-05-15 23:14:05'),
(10,'精选团操套餐F','2019-05-22 20:54:57',500.0000,100,NULL,'北京市',126,'<div class=\"col-xs-12 col-sm-10\">\n<article class=\"single-course-content-wrap\">\n<div class=\"single-course-content\">\n<div id=\"jianjie\" class=\"section-course-item-wrap\">\n<h1 class=\"section-title\">套餐简介</h1>\n<h4 class=\"section-sub-title\">Course</h4>\n<div class=\"single-course-detail-wrap\">\n<p>很多人喜欢团操教练那种激情四射的感觉，相比私人教练来说，团操教练以兼职为主，会在多家场馆授课，教练的收入也是直接由课时多少来决定。一个团操教练如果不想奔波在太多的场馆中，就必须掌握较多团操课程，以有更多的授课机会。</p>\n<p><strong>九门健身教练培训课程任选八门</strong></p>\n<p>&nbsp;</p>\n<p><strong><img class=\"wp-image-5992 size-large aligncenter\" src=\"https://www.shtsn.com/wp-content/uploads/2017/05/2018091903250580-e1537327517447.jpg\" alt=\"上海体适能团操课程培训\" width=\"721\" height=\"1024\" /></strong></p>\n</div>\n</div>\n<div id=\"youshi\" class=\"section-course-item-wrap\">\n<h1 class=\"section-title\">套餐优势</h1>\n<h4 class=\"section-sub-title\">Course</h4>\n<div class=\"single-course-detail-wrap\">\n<p>汇总了9种健身场馆需要的课程，既有健美操、动感单车等健身场馆的经典主流课程，又有杠铃操、搏击操、有氧舞蹈等国际流行的课程。学员通过学习后可以在熟悉经典的基础上，又具有一定的前瞻性，从而保证了学员在工作中具有强大的竞争力。</p>\n<p><img class=\"aligncenter wp-image-2635 size-full\" src=\"https://www.shtsn.com/wp-content/uploads/2017/05/%E6%9C%89%E6%B0%A7%E8%88%9E%E8%B9%881.jpg\" sizes=\"(max-width: 632px) 100vw, 632px\" srcset=\"https://www.shtsn.com/wp-content/uploads/2017/05/有氧舞蹈1.jpg 632w, https://www.shtsn.com/wp-content/uploads/2017/05/有氧舞蹈1-300x210.jpg 300w\" alt=\"上海体适能团操健身教练培训\" width=\"632\" height=\"442\" /></p>\n</div>\n</div>\n<div id=\"kechengrenzheng\" class=\"section-course-item-wrap\">\n<h1 class=\"section-title\">课程认证</h1>\n<h4 class=\"section-sub-title\">Course</h4>\n<div class=\"single-course-detail-wrap\">\n<p><img class=\"alignnone size-full wp-image-4987\" src=\"https://www.shtsn.com/wp-content/uploads/2017/05/2018092908261941.jpg\" alt=\"上海体适能团操证书\" width=\"1000\" height=\"1876\" /></p>\n</div>\n</div>\n</div>\n</article>\n</div>',6,NULL,'admin','2019-05-15 20:57:12',NULL,'2019-05-15 20:57:12'),
(11,'朱利安·格罗365循环计划','2019-05-21 20:57:57',1000.0000,60,NULL,'上海市XXXX——XXXX-SS',129,'<div class=\"box-info-hd\">\n<div class=\"hd-row1\">\n<h1>朱利安&middot;格罗365循环计划</h1>\n</div>\n<div class=\"hd-row2\">课时：1周天/周：5天强度：H3</div>\n</div>\n<div class=\"box-info-bd\">\n<p>朱利安&middot;格罗（Julien Greaux），是补剂大厂BSN的赞助运动员，动作片电影明星，功夫大师，有超过20年的训练和格斗经验，上过超过20本以上杂志的封面，擅长全身性训练，十分强调饮食的重要性。</p>\n<p>他设计的这个365循环训练计划，是一个每周5天的循环计划，帮你一年365天都保持低体脂和好身材。</p>\n</div>',5,NULL,'admin','2019-05-15 20:58:44',NULL,'2019-05-15 20:58:44');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='课程分类表';

/*Data for the table `tb_course_type` */

insert  into `tb_course_type`(`id`,`course_type_name`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(3,'游学课程',NULL,'admin','2019-05-15 20:46:51',NULL,'2019-05-15 20:46:51'),
(4,'在线学习',NULL,'admin','2019-05-15 20:47:01',NULL,'2019-05-15 20:47:01'),
(5,'普通课程',NULL,'admin','2019-05-15 20:47:19',NULL,'2019-05-15 20:47:19'),
(6,'户外课程',NULL,'admin','2019-05-15 20:47:41',NULL,'2019-05-15 20:47:41');

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='客服表';

/*Data for the table `tb_custom` */

insert  into `tb_custom`(`id`,`name`,`phone`,`memo`,`create_time`,`create_user_name`,`update_time`,`update_user_name`) values 
(14,'笑美客服','001-0013456',NULL,'2019-05-15 21:50:30','Admin','2019-05-15 21:50:30',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='疾病大类表';

/*Data for the table `tb_disease_class` */

insert  into `tb_disease_class`(`disease_id`,`number`,`disease_name`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(13,'001','常见病',NULL,'admin','2019-05-15 21:14:13','admin','2019-05-24 23:28:04'),
(14,'002','心理疾病',NULL,'admin','2019-05-15 21:14:31',NULL,'2019-05-15 21:14:31'),
(15,'003','循环系统疾病',NULL,'admin','2019-05-15 21:14:50',NULL,'2019-05-15 21:14:50'),
(16,'004','肿瘤学',NULL,'admin','2019-05-15 21:15:16',NULL,'2019-05-15 21:15:16');

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='疾病小类表';

/*Data for the table `tb_disease_class_detail` */

insert  into `tb_disease_class_detail`(`disease_detail_id`,`disease_id`,`number`,`type`,`disease_detail_name`,`bg_images`,`content`,`icon`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(8,16,'001','0','肺癌',132,'<p>肺癌是发病率和死亡率增长最快，对人群健康和生命威胁最大的恶性肿瘤之一。近50年来许多国家都报道肺癌的发病率和死亡率均明显增高，男性肺癌发病率和死亡率均占所有恶性肿瘤的第一位，女性发病率占第二位，死亡率占第二位。肺癌的病因至今尚不完全明确，大量资料表明，长期大量吸烟与肺癌的发生有非常密切的关系。已有的研究证明：长期大量吸烟者患肺癌的概率是不吸烟者的10～20倍，开始吸烟的年龄越小，患肺癌的几率越高。此外，吸烟不仅直接影响本人的身体健康，还对周围人群的健康产生不良影响，导致被动吸烟者肺癌患病率明显增加。城市居民肺癌的发病率比农村高，这可能与城市大气污染和烟尘中含有致癌物质有关。因此应该提倡不吸烟，并加强城市环境卫生工作。</p>\n<div class=\"para-title level-2\">\n<h2 class=\"title-text\">临床表现</h2>\n</div>\n<div class=\"para\">肺癌的临床表现比较复杂，症状和体征的有无、轻重以及出现的早晚，取决于肿瘤发生部位、病理类型、有无转移及有无并发症，以及患者的反应程度和耐受性的差异。肺癌早期症状常较轻微，甚至可无任何不适。中央型肺癌症状出现早且重，周围型肺癌症状出现晚且较轻，甚至无症状，常在体检时被发现。肺癌的症状大致分为：局部症状、全身症状、肺外症状、浸润和转移症状。</div>\n<div class=\"para\"><strong>（一）局部症状</strong></div>\n<div class=\"para\">局部症状是指由肿瘤本身在局部生长时刺激、阻塞、浸润和压迫组织所引起的症状。</div>\n<div class=\"para\"><strong>1.咳嗽</strong></div>\n<div class=\"para\">咳嗽是最常见的症状，以咳嗽为首发症状者占35%～75%。肺癌所致的咳嗽可能与支气管黏液分泌的改变、阻塞性<a href=\"https://baike.baidu.com/item/%E8%82%BA%E7%82%8E/1083485\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1083485\">肺炎</a>、胸膜侵犯、<a href=\"https://baike.baidu.com/item/%E8%82%BA%E4%B8%8D%E5%BC%A0/2322361\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"2322361\">肺不张</a>及其他胸内合并症有关。肿瘤生长于管径较大、对外来刺激落敏感的段以上支气管黏膜时，可产生类似异物样刺激引起的咳嗽，典型的表现为阵发性刺激性干咳，一般止咳药常不易控制。肿瘤生长在段以下较细小支气管黏膜时，咳嗽多不明显，甚至无咳嗽。对于吸烟或患慢<a href=\"https://baike.baidu.com/item/%E6%94%AF%E6%B0%94%E7%AE%A1%E7%82%8E/982703\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"982703\">支气管炎</a>的病人，如咳嗽程度加重，次数变频，咳嗽性质改变如呈高音调金属音时，尤其在老年人，要高度警惕肺癌的可能性。</div>\n<div class=\"para\"><strong>2.痰中带血或<a href=\"https://baike.baidu.com/item/%E5%92%AF%E8%A1%80/9591481\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"9591481\">咯血</a></strong></div>\n<div class=\"para\">痰中带血或<a href=\"https://baike.baidu.com/item/%E5%92%AF%E8%A1%80/9591481\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"9591481\">咯血</a>亦是肺癌的常见症状，以此为首发症状者约占30%。由于肿瘤组织血供丰富，质地脆，剧咳时血管破裂而致出血，咳血亦可能由肿瘤局部坏死或<a href=\"https://baike.baidu.com/item/%E8%A1%80%E7%AE%A1%E7%82%8E/950528\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"950528\">血管炎</a>引起。肺癌咳血的特征为间断性或持续性、反复少量的痰中带血丝，或少量<a href=\"https://baike.baidu.com/item/%E5%92%AF%E8%A1%80/9591481\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"9591481\">咯血</a>，偶因较大血管破裂、大的空洞形成或肿瘤破溃入支气管与肺血管而导致难以控制的大<a href=\"https://baike.baidu.com/item/%E5%92%AF%E8%A1%80/9591481\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"9591481\">咯血</a>。</div>\n<div class=\"para\"><strong>3.胸痛</strong></div>\n<div class=\"para\">以胸痛为首发症状者约占25%。常表现为胸部不规则的隐痛或钝痛。大多数情况下，周围型肺癌侵犯壁层胸膜或胸壁，可引起尖锐而断续的胸膜性疼痛，若继续发展，则演变为恒定的钻痛。难以定位的轻度的胸部不适有时与中央型肺癌侵犯纵隔或累及血管、支气管周围神经有关，而恶性<a href=\"https://baike.baidu.com/item/%E8%83%B8%E8%85%94%E7%A7%AF%E6%B6%B2/2255567\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"2255567\">胸腔积液</a>患者有25%诉胸部钝痛。持续尖锐剧烈、不易为药物所控制的胸痛，则常提示已有广泛的胸膜或胸壁侵犯。肩部或胸背部持续性疼痛提示肺叶内侧近纵隔部位有肿瘤外侵可能。</div>\n<div class=\"para\"><strong>4.胸闷、气急</strong></div>\n<div class=\"para\">约有10%的患者以此为首发症状，多见于中央型肺癌，特别是肺功能较差的病人。引起呼吸困难的原因主要包括：①肺癌晚期，纵隔淋巴结广泛转移，压迫气管、隆突或主支气管时，可出现气急，甚至窒息症状。②大量<a href=\"https://baike.baidu.com/item/%E8%83%B8%E8%85%94%E7%A7%AF%E6%B6%B2/2255567\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"2255567\">胸腔积液</a>时压迫肺组织并使纵隔严重移位，或有<a href=\"https://baike.baidu.com/item/%E5%BF%83%E5%8C%85%E7%A7%AF%E6%B6%B2/9517803\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"9517803\">心包积液</a>时，也可出现胸闷、气急、呼吸困难，但抽液后症状可缓解。③弥漫性<a href=\"https://baike.baidu.com/item/%E7%BB%86%E6%94%AF%E6%B0%94%E7%AE%A1%E8%82%BA%E6%B3%A1%E7%99%8C/7820872\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"7820872\">细支气管肺泡癌</a>和支气管播散性腺癌，使呼吸面积减少，气体弥散功能障碍，导致严重的通气/血流比值失调，引起呼吸困难逐渐加重，常伴有发绀。④其他：包括阻塞性<a href=\"https://baike.baidu.com/item/%E8%82%BA%E7%82%8E/1083485\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1083485\">肺炎</a>。<a href=\"https://baike.baidu.com/item/%E8%82%BA%E4%B8%8D%E5%BC%A0/2322361\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"2322361\">肺不张</a>、<a href=\"https://baike.baidu.com/item/%E6%B7%8B%E5%B7%B4%E7%AE%A1%E7%82%8E/11050903\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"11050903\">淋巴管炎</a>性肺癌、肿瘤微栓塞、上气道阻塞、自发性<a href=\"https://baike.baidu.com/item/%E6%B0%94%E8%83%B8/111943\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"111943\">气胸</a>以及合并慢性肺疾病如COPD。</div>\n<div class=\"para\"><strong>5.声音嘶哑</strong></div>\n<div class=\"para\">有5%～18%的肺癌患者以声嘶为第一主诉，通常伴随有咳嗽。声嘶一般提示直接的纵隔侵犯或淋巴结长大累及同侧喉返神经而致左侧声带麻痹。声带麻痹亦可引起程度不同的<a href=\"https://baike.baidu.com/item/%E4%B8%8A%E6%B0%94%E9%81%93%E6%A2%97%E9%98%BB/3698454\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"3698454\">上气道梗阻</a>。</div>\n<div class=\"para\"><strong>（二）全身症状</strong></div>\n<div class=\"para\"><strong>1.发热</strong></div>\n<div class=\"para\">以此首发症状者占20%～30%。肺癌所致的发热原因有两种，一为炎性发热，中央型肺癌肿瘤生长时，常先阻塞段或支气管开口，引起相应的肺叶或肺段阻塞性<a href=\"https://baike.baidu.com/item/%E8%82%BA%E7%82%8E/1083485\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1083485\">肺炎</a>或不张而出现发热，但多在38℃左右，很少超过39℃，抗生素治疗可能奏效，阴影可能吸收，但因分泌物引流不畅，常反复发作，约1/3的患者可在短时间内反复在同一部位发生<a href=\"https://baike.baidu.com/item/%E8%82%BA%E7%82%8E/1083485\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1083485\">肺炎</a>。周围型肺癌多在晚期因肿瘤压迫邻近肺组织引起炎症时而发热。二为癌性发热，多由肿瘤坏死组织被机体吸收所致，此种发热抗炎药物治疗无效，激素类或吲哚类药物有一定疗效。</div>\n<div class=\"para\"><strong>2.消瘦和恶病质</strong></div>\n<div class=\"para\">肺癌晚期由于感染、疼痛所致食欲减退，肿瘤生长和毒素引起消耗增加，以及体内TNF、Leptin等细胞因子水平增高，可引起严重的消瘦、<a href=\"https://baike.baidu.com/item/%E8%B4%AB%E8%A1%80/1080\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1080\">贫血</a>、恶病质。</div>\n<div class=\"para\"><strong>（三）肺外症状</strong></div>\n<div class=\"para\">由于肺癌所产生的某些特殊活性物质（包括激素、抗原、酶等），患者可出现一种或多种肺外症状，常可出现在其他症状之前，并且可随肿瘤的消长而消退或出现，临床上以肺源性骨关节增生症较多见。</div>\n<div class=\"para\"><strong>1.肺源性骨关节增生症</strong></div>\n<div class=\"para\">临床上主要表现为柞状指（趾），长骨远端骨膜增生，新骨形成，受累关节肿胀、疼痛和触痛。长骨以胫排骨、肱骨和掌骨，关节以膝、踝、腕等大关节较多见。柞状指、趾发生率约29%，主要见于鳞癌；<a href=\"https://baike.baidu.com/item/%E5%A2%9E%E7%94%9F%E6%80%A7%E9%AA%A8%E5%85%B3%E8%8A%82%E7%97%85/4632626\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"4632626\">增生性骨关节病</a>发生率1%～10%，主要见于腺癌，小细胞癌很少有此种表现。确切的病因尚不完全清楚，可能与雌激素、生长激素或神经功能有关，手术切除癌肿后可获缓解或消退，复发时又可出现。</div>\n<div class=\"para\"><strong>2.与肿瘤有关的异位激素分泌综合征</strong></div>\n<div class=\"para\">约10%患者可出现此类症状，可作为首发症状出现。另有一些患者虽无临床症状，但可检测出一种或几种血浆异位激素增高。此类症状多见于<a href=\"https://baike.baidu.com/item/%E5%B0%8F%E7%BB%86%E8%83%9E%E8%82%BA%E7%99%8C/9987443\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"9987443\">小细胞肺癌</a>。</div>\n<div class=\"para\"><strong>（1）异位促肾上腺皮质激素（ACTH）分泌综合征</strong>&nbsp;由于肿瘤分泌ACTH或类肾上腺皮质激素释放因子活性物质，使血浆皮质醇增高。临床症状与柯兴氏综合征大致相似，可有进行性肌无力、周围性<a href=\"https://baike.baidu.com/item/%E6%B0%B4%E8%82%BF/253990\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"253990\">水肿</a>、<a href=\"https://baike.baidu.com/item/%E9%AB%98%E8%A1%80%E5%8E%8B/195863\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"195863\">高血压</a>、<a href=\"https://baike.baidu.com/item/%E7%B3%96%E5%B0%BF%E7%97%85/100969\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"100969\">糖尿病</a>、低钾性碱中毒等，其特点为病程进展快，可出现严重的精神障碍，伴有皮肤色素沉着，而向心性肥胖、多血质、紫纹多不明显。该综合征多见于<a href=\"https://baike.baidu.com/item/%E8%82%BA%E8%85%BA%E7%99%8C/9211661\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"9211661\">肺腺癌</a>及<a href=\"https://baike.baidu.com/item/%E5%B0%8F%E7%BB%86%E8%83%9E%E8%82%BA%E7%99%8C/9987443\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"9987443\">小细胞肺癌</a>。</div>\n<div class=\"para\"><strong>（2）异位促性腺激素分泌综合征</strong>&nbsp;由于肿瘤自主性分泌LH及HCG而刺激性腺类固醇分泌所致。多表现为男性双侧或单侧乳腺发育，可发生于各种细胞类型的肺癌，以未分化癌和小细胞癌多见。偶可见<a href=\"https://baike.baidu.com/item/%E9%98%B4%E8%8C%8E%E5%BC%82%E5%B8%B8%E5%8B%83%E8%B5%B7/5119967\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"5119967\">阴茎异常勃起</a>，除与激素异常分泌有关外，也可能因阴茎血管栓塞所致。</div>\n<div class=\"para\"><strong>（3）异位甲状旁腺激素分泌综合征</strong>&nbsp;是由于肿瘤分泌甲状旁腺激素或一种溶骨物质（多肽）所致。临床上以高血钙、低血磷为特点，症状有食欲减退、恶心、呕吐、腹痛、烦渴、体重下降、心动过速、心律不齐、烦躁不安和精神错乱等。多见于鳞癌。</div>\n<div class=\"para\"><strong>（4）异位胰岛素分泌综合征&nbsp;</strong>临床表现为亚急性低血糖征候群，如精神错乱、幻觉、<a href=\"https://baike.baidu.com/item/%E5%A4%B4%E7%97%9B/340011\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"340011\">头痛</a>等。其原因可能与肿瘤大量消耗葡萄糖、分泌类似胰岛素活性的体液物质或分泌胰岛素释放多肽等有关。</div>\n<div class=\"para\"><strong>（5）<a href=\"https://baike.baidu.com/item/%E7%B1%BB%E7%99%8C/3305106\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"3305106\">类癌</a>综合征&nbsp;</strong>是由于肿瘤分泌5-羟色胺所致。表现为支气管痉挛性<a href=\"https://baike.baidu.com/item/%E5%93%AE%E5%96%98/467071\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"467071\">哮喘</a>、皮肤潮红、阵发性心动过速和水样<a href=\"https://baike.baidu.com/item/%E8%85%B9%E6%B3%BB/2193261\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"2193261\">腹泻</a>等。多见于腺癌和燕麦细胞癌。</div>\n<div class=\"para\"><strong>（6）神经-肌肉综合征（Eaton-Lambert综合征）&nbsp;</strong>是因肿瘤分泌箭毒性样物质所致。表现为随意肌力减退和极易疲劳。多见于小细胞未分化癌。其他尚有周围性<a href=\"https://baike.baidu.com/item/%E7%A5%9E%E7%BB%8F%E7%97%85/183922\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"183922\">神经病</a>、脊根节细胞与神经退行性变、亚急性小脑变性、皮质变性、<a href=\"https://baike.baidu.com/item/%E5%A4%9A%E5%8F%91%E6%80%A7%E8%82%8C%E7%82%8E/5318281\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"5318281\">多发性肌炎</a>等，可出现肢端疼痛无力、<a href=\"https://baike.baidu.com/item/%E7%9C%A9%E6%99%95/1203975\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1203975\">眩晕</a>、<a href=\"https://baike.baidu.com/item/%E7%9C%BC%E7%90%83%E9%9C%87%E9%A2%A4/619921\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"619921\">眼球震颤</a>、<a href=\"https://baike.baidu.com/item/%E5%85%B1%E6%B5%8E%E5%A4%B1%E8%B0%83/784958\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"784958\">共济失调</a>、步履困难及<a href=\"https://baike.baidu.com/item/%E7%97%B4%E5%91%86/981606\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"981606\">痴呆</a>。</div>\n<div class=\"para\"><strong>（7）异位生长激素综合征&nbsp;</strong>表现为<a href=\"https://baike.baidu.com/item/%E8%82%A5%E5%A4%A7%E6%80%A7%E9%AA%A8%E5%85%B3%E8%8A%82%E7%97%85/784450\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"784450\">肥大性骨关节病</a>&nbsp;多见于腺癌和未分化癌。</div>\n<div class=\"para\"><strong>（8）抗利尿激素分泌异常综合征&nbsp;</strong>是由于癌组织分泌大量的ADH或具有抗利尿作用的多肽物质所致。其主要临床特点为<a href=\"https://baike.baidu.com/item/%E4%BD%8E%E9%92%A0%E8%A1%80%E7%97%87/5417891\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"5417891\">低钠血症</a>，伴有血清和细胞外液低渗透压（&lt;270 mOsm/L）、肾脏持续排纳、尿渗透压大于血浆渗透压（尿比重&gt;1.200）和水中毒。多见于<a href=\"https://baike.baidu.com/item/%E5%B0%8F%E7%BB%86%E8%83%9E%E8%82%BA%E7%99%8C/9987443\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"9987443\">小细胞肺癌</a>。</div>\n<div class=\"para\"><strong>3.其他表现</strong></div>\n<div class=\"para\"><strong>（1）<a href=\"https://baike.baidu.com/item/%E7%9A%AE%E8%82%A4%E7%97%85/9900777\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"9900777\">皮肤病</a>变&nbsp;</strong>黑棘皮病和皮肤炎多见于腺癌，皮肤色素沉着是由于肿瘤分泌黑色素细胞刺激素（MSH）所致，多见于小细胞癌。其他尚有<a href=\"https://baike.baidu.com/item/%E7%A1%AC%E7%9A%AE%E7%97%85/1374470\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1374470\">硬皮病</a>、掌跖皮肤过度角化症等。</div>\n<div class=\"para\"><strong>（2）心血管系统&nbsp;</strong>各种类型的肺癌均可凝血机制异常，出现游走性静脉栓塞、<a href=\"https://baike.baidu.com/item/%E9%9D%99%E8%84%89%E7%82%8E/5185652\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"5185652\">静脉炎</a>和非细菌性栓塞性<a href=\"https://baike.baidu.com/item/%E5%BF%83%E5%86%85%E8%86%9C%E7%82%8E/9604197\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"9604197\">心内膜炎</a>，可在肺癌确诊前数月出现。</div>\n<div class=\"para\"><strong>（3）血液学系统&nbsp;</strong>可有慢性<a href=\"https://baike.baidu.com/item/%E8%B4%AB%E8%A1%80/1080\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1080\">贫血</a>、紫癜、红细胞增多、类<a href=\"https://baike.baidu.com/item/%E7%99%BD%E8%A1%80%E7%97%85/101228\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"101228\">白血病</a>样反应。可能为铁质吸收减少、红细胞生成障碍寿命缩短、毛细血管性渗血性<a href=\"https://baike.baidu.com/item/%E8%B4%AB%E8%A1%80/1080\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1080\">贫血</a>等原因所致。此外，各种细胞类型的肺癌均可出现DIC，可能与肿瘤释放促凝血因子有关。肺鳞癌患者可伴有紫癜。</div>\n<div class=\"para\"><strong>（四）外侵和转移症状</strong></div>\n<div class=\"para\"><strong>1.淋巴结转移</strong></div>\n<div class=\"para\">最常见的是纵隔淋巴结和锁骨上淋巴结，多在病灶同侧，少数可在对侧，多为较坚硬，单个或多个结节，有时可为首发的主诉而就诊。气管旁或隆突下淋巴结肿大可压迫气道，出现胸闷。气急甚至窒息。压迫食管可出现<a href=\"https://baike.baidu.com/item/%E5%90%9E%E5%92%BD%E5%9B%B0%E9%9A%BE/1626920\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1626920\">吞咽困难</a>。</div>\n<div class=\"para\"><strong>2.胸膜受侵和/转移</strong></div>\n<div class=\"para\">胸膜是肺癌常见的侵犯和转移部位，包括直接侵犯和种植性转移。临床表现因有无<a href=\"https://baike.baidu.com/item/%E8%83%B8%E8%85%94%E7%A7%AF%E6%B6%B2/2255567\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"2255567\">胸腔积液</a>及胸水的多寡而异，胸水的成因除直接侵犯和转移外，还包括淋巴结的阻塞以及伴发的阻塞性<a href=\"https://baike.baidu.com/item/%E8%82%BA%E7%82%8E/1083485\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1083485\">肺炎</a>和<a href=\"https://baike.baidu.com/item/%E8%82%BA%E4%B8%8D%E5%BC%A0/2322361\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"2322361\">肺不张</a>。常见的症状有呼吸困难、咳嗽、胸闷与胸痛等，亦可完全无任何症状；查体时可见肋间饱满、肋间增宽、呼吸音减低、语颤减低、叩诊实音、纵隔移位等，胸水可为浆液性、浆液血性或血性，多数为渗出液，恶性胸水的特点为增长速度快，多呈血性。极为罕见的肺癌可发生自发性<a href=\"https://baike.baidu.com/item/%E6%B0%94%E8%83%B8/111943\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"111943\">气胸</a>，其机制为胸膜的直接侵犯和阻塞性<a href=\"https://baike.baidu.com/item/%E8%82%BA%E6%B0%94%E8%82%BF/813652\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"813652\">肺气肿</a>破裂，多见于鳞癌，预后不良。</div>\n<div class=\"para\"><strong>3.<a href=\"https://baike.baidu.com/item/%E4%B8%8A%E8%85%94%E9%9D%99%E8%84%89%E7%BB%BC%E5%90%88%E5%BE%81/1365345\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1365345\">上腔静脉综合征</a>（Superior Vena Cava Syndrome，SVCS）</strong></div>\n<div class=\"para\">肿瘤直接侵犯或纵隔淋巴结转移压迫上腔静脉，或腔内的栓塞，使其狭窄或闭塞，造成血液回流障碍，出现一系列症状和体征，如<a href=\"https://baike.baidu.com/item/%E5%A4%B4%E7%97%9B/340011\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"340011\">头痛</a>、颜面部浮肿、颈胸部<a href=\"https://baike.baidu.com/item/%E9%9D%99%E8%84%89%E6%9B%B2%E5%BC%A0/228549\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"228549\">静脉曲张</a>、压力增高、呼吸困难、咳嗽、胸痛以及<a href=\"https://baike.baidu.com/item/%E5%90%9E%E5%92%BD%E5%9B%B0%E9%9A%BE/1626920\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1626920\">吞咽困难</a>，亦常有弯腰时晕厥或<a href=\"https://baike.baidu.com/item/%E7%9C%A9%E6%99%95/1203975\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1203975\">眩晕</a>等。前胸部和上腹部静脉可代偿性曲张，反映上腔静脉阻塞的时间和阻塞的解剖位置。上腔静脉阻塞的症状和体征与其部位有关。若一侧无名静脉阻塞，头面、颈部的血流可通过对侧无名静脉回流心脏，临床症状较轻。若上腔静脉阻塞发生在奇静脉入口以下部位，除了上述静脉扩张，尚有腹部静脉怒张，血液以此途径流入下腔静脉。若阻塞发展迅速，可出现脑<a href=\"https://baike.baidu.com/item/%E6%B0%B4%E8%82%BF/253990\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"253990\">水肿</a>而有<a href=\"https://baike.baidu.com/item/%E5%A4%B4%E7%97%9B/340011\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"340011\">头痛</a>、嗜睡、激惹和意识状态的改变。</div>\n<div class=\"para\"><strong>4.肾脏转移</strong></div>\n<div class=\"para\">死于肺癌的患者约35%发现有肾脏转移，亦是肺癌手术切除后1月内死亡患者的最常见转移部位。大多数肾脏转移无临床症状，有时可表现为腰痛及肾功能不全。</div>\n<div class=\"para\"><strong>5.消化道转移</strong></div>\n<div class=\"para\">肝转移可表现为食欲减退、肝区疼痛，有时伴有恶心，血清&gamma;-GT常呈阳性，AKP呈进行性增高，查体时可发现肝脏肿大，质硬、结节感。<a href=\"https://baike.baidu.com/item/%E5%B0%8F%E7%BB%86%E8%83%9E%E8%82%BA%E7%99%8C/9987443\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"9987443\">小细胞肺癌</a>好发胰腺转移，可出现<a href=\"https://baike.baidu.com/item/%E8%83%B0%E8%85%BA%E7%82%8E/967699\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"967699\">胰腺炎</a>症状或阻塞性<a href=\"https://baike.baidu.com/item/%E9%BB%84%E7%96%B8/2456805\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"2456805\">黄疸</a>。各种细胞类型的肺癌都可转移到肝脏、胃肠道、肾上腺和腹膜后淋巴结，临床多无症状，常在查体时被发现。</div>\n<div class=\"para\"><strong>6.骨转移</strong></div>\n<div class=\"para\">肺癌骨转移的常见部位有肋骨、椎骨、髂骨、股骨等，但以同侧肋骨和椎骨较多见，表现为局部疼痛并有定点压痛、叩痛。脊柱转移可压迫椎管导致阻塞或压迫症状。关节受累可出现关节腔积液，穿刺可能查到癌细胞。</div>\n<div class=\"para\"><strong>7.中枢神经系统症状</strong></div>\n<div class=\"para\"><strong>（1）脑、脑膜和脊髓转移&nbsp;</strong>发生率约10%，其症状可因转移部位不同而异。常见的症状为<a href=\"https://baike.baidu.com/item/%E9%A2%85%E5%86%85%E5%8E%8B%E5%A2%9E%E9%AB%98/918595\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"918595\">颅内压增高</a>表现，如<a href=\"https://baike.baidu.com/item/%E5%A4%B4%E7%97%9B/340011\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"340011\">头痛</a>、恶心、呕吐以及精神状态的改变等，少见的症状有<a href=\"https://baike.baidu.com/item/%E7%99%AB%E7%97%AB/1613\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1613\">癫痫</a>发作、脑神经受累、偏瘫、<a href=\"https://baike.baidu.com/item/%E5%85%B1%E6%B5%8E%E5%A4%B1%E8%B0%83/784958\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"784958\">共济失调</a>、失语和突然昏厥等。脑膜转移不如脑转移常见，常发生于<a href=\"https://baike.baidu.com/item/%E5%B0%8F%E7%BB%86%E8%83%9E%E8%82%BA%E7%99%8C/9987443\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"9987443\">小细胞肺癌</a>患者中，其症状与脑转移相似。</div>\n<div class=\"para\"><strong>（2）脑病和小脑皮质变性&nbsp;</strong>脑病的主要表现为<a href=\"https://baike.baidu.com/item/%E7%97%B4%E5%91%86/981606\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"981606\">痴呆</a>、精神病和器质<a href=\"https://baike.baidu.com/item/%E6%80%A7%E7%97%85/292397\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"292397\">性病</a>变，小脑皮质变性表现为急性或亚急性肢体功能障碍，四肢行动困难、动作震颤、发音困难、<a href=\"https://baike.baidu.com/item/%E7%9C%A9%E6%99%95/1203975\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1203975\">眩晕</a>等。有报道肿瘤切除后上述症状可获缓解。</div>\n<div class=\"para\"><strong>8.心脏受侵和转移</strong></div>\n<div class=\"para\">肺癌累及心脏并不少见，尤多见于中央型肺癌。肿瘤可通过直接蔓延侵及心脏，亦可以淋巴管逆行播散，阻塞心脏的引流淋巴管引起<a href=\"https://baike.baidu.com/item/%E5%BF%83%E5%8C%85%E7%A7%AF%E6%B6%B2/9517803\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"9517803\">心包积液</a>，发展较慢者可无症状，或仅有心前区、肋弓下或上腹部疼痛。发展较快者可呈典型的心包填塞症状，如心急、心悸、颈面部静脉怒张、心界扩大、心音低远、肝肿大、腹水等。</div>\n<div class=\"para\"><strong>9.周围神经系统症状</strong></div>\n<div class=\"para\">癌肿压迫或侵犯颈交感神经引起Horner氏综合征，其特点为病侧瞳孔缩小，<a href=\"https://baike.baidu.com/item/%E4%B8%8A%E7%9D%91%E4%B8%8B%E5%9E%82/1980536\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1980536\">上睑下垂</a>、<a href=\"https://baike.baidu.com/item/%E7%9C%BC%E7%90%83%E5%86%85%E9%99%B7/5243054\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"5243054\">眼球内陷</a>和颜面部无汗等。压迫或侵犯臂丛神经时引起臂丛神经压迫征，表现为同侧上肢烧灼样放射性疼痛、局部感觉异常和营养性萎缩。肿瘤侵犯膈神经时，可赞成<a href=\"https://baike.baidu.com/item/%E8%86%88%E8%82%8C%E9%BA%BB%E7%97%B9/9638383\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"9638383\">膈肌麻痹</a>，出现胸闷、气急，X线透视下可见有膈肌矛盾运动。压迫或侵犯喉返神经时，可致声带麻痹出现声音嘶哑。肺尖部肿瘤（肺上沟瘤）侵犯颈8和胸1神经、臂丛神经、交感神经节以及邻近的肋骨，引起剧烈肩臂疼痛、感觉异常，一侧臂轻瘫或无力、肌肉萎缩，即所谓<a href=\"https://baike.baidu.com/item/Pancoast%E7%BB%BC%E5%90%88%E5%BE%81/10129038\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"10129038\">Pancoast综合征</a>。</div>\n<div class=\"para-title level-2\">\n<h2 class=\"title-text\">诊断</h2>\n</div>\n<div class=\"para\">根据临床症状、体征、影像学检查和组织病理学检查做出诊断。肺癌的早期诊断具有重要意义，只有在病变早期得到诊断和治疗，才能获得较好的疗效。</div>\n<div class=\"para\">肺癌早期缺乏典型症状，对40岁以上人群，应定期进行胸部X线普查。出现肺癌原发症状或转移症状者及时做胸部X线片检查或胸部CT检查，发现肺部有肿块阴影时，应首先考虑到肺癌的诊断，应作进一步检查，经过组织病理学检查明确诊断。</div>',133,NULL,'admin','2019-05-15 21:18:12',NULL,'2019-05-15 21:18:12'),
(9,16,'002','1','囊肿型淋巴管瘤',134,'<div class=\"para-title level-2\">\n<h2 class=\"title-text\">临床表现</h2>\n</div>\n<div class=\"para\">颈后角区囊性肿块具有向击（锁骨上下口底气管食管旁及纵隔）蔓延生长特点界限常不清楚多见于婴幼儿出生时即呈巨大亦可逐渐长大</div>\n<div class=\"para\">囊瘤柔软般无压缩性能透光表面皮肤正常不粘连</div>\n<div class=\"para\">内容物淡黄透明或乳糜状偶带血性镜下可见大量含有胆固醇结晶的淋巴细胞</div>\n<div class=\"para\">囊瘤较在累及口底舌或咽部时可有语言呼吸或吞咽障碍囊瘤位于锁骨上时可有臂丛受压出现运动障碍或肌肉萎缩有时气管受压移位</div>\n<div class=\"para\">在胚胎期静脉丛中的中胚层裂隙融合形成大的原始淋巴囊引流进入中心静脉系统以后淋巴囊逐渐退化或发展成与静脉平行的淋巴管系统若原始淋巴囊未与静脉系统相连能就产生囊状淋巴管瘤如与淋巴管系统主干不相通可发生海绵状淋巴管瘤如少量淋巴囊在淋巴管系统形成时被分隔则形成单纯性淋巴管瘤因颈静脉囊淋巴形成最早体积最大所致颈部发生囊状淋巴管瘤最常见</div>\n<div class=\"para\">淋巴管瘤是由增生扩张结构紊乱的淋巴管所组成可向周围呈浸润性生长按其形态和分布可分为种类型：</div>\n<div class=\"para\">单纯性淋巴管瘤 由扩张的不规则的毛细淋巴管丛所组成间质较少主要发生在皮肤皮下组织和粘膜层</div>\n<div class=\"para\">海绵状淋巴管瘤 淋巴管扩大呈窦状其内充满淋巴液呈多房性囊腔周围间质较多病变侵及皮肤粘膜皮下组织和深部结构发肌肉后腹膜纵隔等</div>\n<div class=\"para\">囊肿型淋巴管瘤 其囊腔大可单房或多房相互交通覆有内皮细胞间质很少</div>\n<div class=\"para\">实际上临床见到的淋巴管瘤往往是混合型的如果淋巴管瘤中混杂有血管瘤组织则称为淋巴血管瘤</div>\n<div class=\"para\">颈部巨大囊状水瘤的存在可造成胎儿的娩出困难般在出生后即可在颈侧部见到软质的囊性肿块有明显波动感透光试验阳性其界限常不清楚不易被压缩亦无疼痛肿瘤与皮肤无粘连生长缓慢大小无明显变化但易并发感染且较难控制还可发生囊内出知此时瘤体骤然增大张力增高呈青紫色压迫周围器官可产生相应的症状有的可广泛侵及口底咽喉或纵隔压迫气管食管引起呼吸窘迫和咽下困难甚至危及生命</div>\n<div class=\"para\">有部分淋巴管瘤在发展过程中会自行栓塞退化或在感染后由于囊壁内皮细胞被破坏在感染被控制后自行消退</div>',135,NULL,'admin','2019-05-15 21:19:49',NULL,'2019-05-15 21:19:49'),
(10,14,'003','1','恐高症',136,'<div class=\"para-title level-2\">\n<div class=\"poster-top\">\n<div class=\"lemma-summary\">\n<div class=\"para\">恐高症又称畏高症。恐高的基本症状就是眩晕、恶心、食欲不振。国外调查资料显示，现代都市人中有91%的人出现过恐高症状。其中10%属临床性恐高。他们每时每刻都得想方设法避免恐高症&ldquo;突发&rdquo;，他们不敢乘透明电梯，更不敢站在阳台上，他们连4楼的高度也受不了，更不用说坐飞机了。</div>\n<div class=\"para\">恐高症一般分两种恐高。在高处（比如爬山、在悬崖边）恐高，是生理恐高；怕高处的人或事物（比如追求更高更好地东西），见到比自己强的人或是事物自卑退缩，是心理恐高。</div>\n</div>\n</div>\n<div class=\"poster-middle\">\n<div class=\"configModuleBanner\">&nbsp;</div>\n</div>\n<div class=\"poster-bottom\">\n<div class=\"baseBox notDouble hasVideoAndFocus mr-30\">\n<div class=\"dl-baseinfo\">\n<dl class=\"\">\n<dt>别称</dt>\n<dd title=\"畏高症\">畏高症</dd>\n</dl>\n<dl class=\"\">\n<dt>英文名称</dt>\n<dd title=\"acrophobia\">acrophobia</dd>\n</dl>\n<dl class=\"\">\n<dt>就诊科室</dt>\n<dd title=\"精神科\">精神科</dd>\n</dl>\n<dl class=\"bottomLine\">\n<dt>常见症状</dt>\n<dd title=\"眩晕，恶心，食欲不振\">眩晕，恶心，食欲不振</dd>\n</dl>\n</div>\n</div>\n<dl class=\"lemmaWgt-guideCard mr-30\">\n<dt class=\"title\">专家解读</dt>\n<dd class=\"detail\">\n<div class=\"docHeadPic\"><img src=\"https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/h%3D94/sign=805f83cba7cc7cd9e52d38dd3801d1bb/38dbb6fd5266d01625d4060e932bd40734fa35c2.jpg\" alt=\"专家图片\" height=\"94\" /></div>\n<div class=\"re_desTitle\">\n<h4 class=\"desTitleH4\" title=\"认识眩晕症\">认识眩晕症</h4>\n<p class=\"desText\" title=\"眩晕除发作期旋转、呕吐等痛苦外，迷路、前庭、耳蜗器官还可能受到损害。\">眩晕除发作期旋转、呕吐等痛苦外，迷路、前庭、耳蜗器官还可能受到损害。</p>\n</div>\n</dd>\n</dl>\n</div>\n<h2 class=\"title-text\">临床表现</h2>\n</div>\n<div class=\"para\">恐高的基本症状就是眩晕、恶心、食欲不振。眩晕会使身体失去平衡，这时站在高处的人就变得非常危险。</div>',137,NULL,'admin','2019-05-15 21:21:30',NULL,'2019-05-15 21:21:30'),
(11,14,'002','0','分裂型人格障碍',138,'<p>分裂型<a href=\"https://baike.baidu.com/item/%E4%BA%BA%E6%A0%BC%E9%9A%9C%E7%A2%8D/3169525\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"3169525\">人格障碍</a>是人格障碍的一种，有这类异常人格的人敏感多疑，他们总是妄自尊大，而又极易产生羞愧感和耻辱感。此类障碍的患病率可能高于<a href=\"https://baike.baidu.com/item/%E7%B2%BE%E7%A5%9E%E5%88%86%E8%A3%82%E7%97%87/33032\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"33032\">精神分裂症</a>和其他人格障碍。目前主要有两种治疗方法，即<a href=\"https://baike.baidu.com/item/%E5%BF%83%E7%90%86%E6%B2%BB%E7%96%97/7862039\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"7862039\">心理治疗</a>和生物医学治疗。尽管目前在人格障碍的治疗上已取得了一些进步，找到了有效改善症状的方法，但对人格障碍的处理在很大程度上仍然是根据人格障碍者的不同特点，帮助其寻求减少冲突的生活道路。</p>\n<div class=\"para-title level-2\">\n<h2 class=\"title-text\">疾病简介</h2>\n</div>\n<div class=\"para\">分裂型<a href=\"https://baike.baidu.com/item/%E4%BA%BA%E6%A0%BC%E9%9A%9C%E7%A2%8D/3169525\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"3169525\">人格障碍</a>是<a href=\"https://baike.baidu.com/item/%E4%BA%BA%E6%A0%BC%E9%9A%9C%E7%A2%8D\" target=\"_blank\" rel=\"noopener\">人格障碍</a>的一种，<a href=\"https://baike.baidu.com/item/%E4%BA%BA%E6%A0%BC%E9%9A%9C%E7%A2%8D/3169525\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"3169525\">人格障碍</a>（personality disorder）主要涉及一些慢性症状，其中包括判断质量低下、思维混乱、情绪不稳定、社会关系糟糕以及没有控制冲动的能力。关键在于这种障碍来源于长期存在的人格特征，让这类患者无法完成正常的社交和工作<sup class=\"sup--normal\" data-sup=\"1\" data-ctrmap=\":1,\">&nbsp;[1]</sup><a class=\"sup-anchor\" name=\"ref_[1]_17472\"></a>&nbsp;&nbsp;。</div>',139,NULL,'admin','2019-05-15 21:22:46',NULL,'2019-05-15 21:22:46'),
(12,15,'005','1','食源性疾病',140,'<div class=\"para-title level-2\">\n<h2 class=\"title-text\">疾病概述</h2>\n</div>\n<div class=\"para\">世界卫生组织认为，凡是通过摄食进入人体的各种致病因子引起的，通常具有感染性的或中毒性\n<div class=\"lemma-picture text-pic layout-right\"><a class=\"image-link\" title=\"食源性疾病\" href=\"https://baike.baidu.com/pic/%E9%A3%9F%E6%BA%90%E6%80%A7%E7%96%BE%E7%97%85/8592226/0/d0526df04f9c57eda50f5245?fr=lemma&amp;ct=single\" target=\"_blank\" rel=\"noopener\"><img class=\"\" src=\"https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=1cc6c1fd6c81800a6ae58e0c813433d6/dcc451da81cb39db7b635f24d0160924ab183024.jpg\" alt=\"食源性疾病\" /></a><span class=\"description\">食源性疾病</span></div>\n的一类疾病，都称之为食源性疾患。即指通过食物传播的方式和途径致使病原物质进入人体并引发的中毒或感染性疾病。从这个概念 出发当不包括一些与饮食有关的慢性病、代谢病，如糖尿病、高血压等，然而国际上有人把这类疾病也归为食源性疾患的范畴。顾名思义，凡与<a href=\"https://baike.baidu.com/item/%E6%91%84%E9%A3%9F\" target=\"_blank\" rel=\"noopener\">摄食</a>有关的一切疾病(包括传染性和非<a href=\"https://baike.baidu.com/item/%E4%BC%A0%E6%9F%93%E6%80%A7%E7%96%BE%E7%97%85\" target=\"_blank\" rel=\"noopener\">传染性疾病</a>)均属食源性疾患。</div>\n<div class=\"para\">1984年WHO将&ldquo;食源性疾病&rdquo;（foodborne diseases）一词作为正式的专业术语，以代替历史上使用的&ldquo;<a href=\"https://baike.baidu.com/item/%E9%A3%9F%E7%89%A9%E4%B8%AD%E6%AF%92\" target=\"_blank\" rel=\"noopener\">食物中毒</a>&rdquo;一词，并将食源性疾病定义为&ldquo;通过摄食方式进入人体内的各种致病因子引起的通常具有感染或中毒性质的一类疾病。</div>',141,NULL,'admin','2019-05-15 21:24:12',NULL,'2019-05-15 21:24:12'),
(13,15,'006','1','脑血管疾病',142,'<div class=\"para-title level-2\">\n<h2 class=\"title-text\">疾病简介</h2>\n</div>\n<div class=\"para\">脑部血液供应障碍引起的脑部疾病。临床上以急性发病居多，多为中、<a href=\"https://baike.baidu.com/item/%E8%80%81%E5%B9%B4\" target=\"_blank\" rel=\"noopener\">老年</a>患者，表现为<a href=\"https://baike.baidu.com/item/%E5%8D%8A%E8%BA%AB%E4%B8%8D%E9%81%82\" target=\"_blank\" rel=\"noopener\">半身不遂</a>、言语障碍等，俗称中风或<a href=\"https://baike.baidu.com/item/%E5%8D%92%E4%B8%AD\" target=\"_blank\" rel=\"noopener\">卒中</a>。<a href=\"https://baike.baidu.com/item/%E6%80%A5%E6%80%A7%E8%84%91%E8%A1%80%E7%AE%A1%E7%97%85\" target=\"_blank\" rel=\"noopener\">急性脑血管病</a>一般分为缺血性和出血性两类。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"2\"></a><a class=\"lemma-anchor \" name=\"sub68016_2\"></a><a class=\"lemma-anchor \" name=\"疾病分类\"></a></div>\n<div class=\"para-title level-2\">\n<h2 class=\"title-text\">疾病分类</h2>\n</div>\n<div class=\"para\">\n<div class=\"lemma-picture text-pic layout-right\"><a class=\"image-link\" title=\"脑血管疾病--血管畸形\" href=\"https://baike.baidu.com/pic/%E8%84%91%E8%A1%80%E7%AE%A1%E7%96%BE%E7%97%85/1231301/0/9f1011b3c85749e6d9335a91?fr=lemma&amp;ct=single\" target=\"_blank\" rel=\"noopener\"><img class=\"\" src=\"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=8e270262dd54564ee165e33b83df9cde/d53f8794a4c27d1e156805b51bd5ad6eddc43870.jpg\" alt=\"脑血管疾病--血管畸形\" /></a><span class=\"description\">脑血管疾病--血管畸形</span></div>\n<strong>1、</strong><strong>短暂性脑缺血发作：</strong></div>\n<div class=\"para\">（1）颈动脉系统出现障害。</div>\n<div class=\"para\">（2）椎-基底动脉系统出现障害。</div>\n<div class=\"para\"><strong>2、</strong><a href=\"https://baike.baidu.com/item/%E8%9B%9B%E7%BD%91%E8%86%9C\" target=\"_blank\" rel=\"noopener\"><strong>蛛网膜</strong></a><strong>下腔出血：</strong></div>\n<div class=\"para\">以下动脉瘤破裂引起：</div>\n<div class=\"para\">（1）<a href=\"https://baike.baidu.com/item/%E5%85%88%E5%A4%A9%E6%80%A7%E5%8A%A8%E8%84%89%E7%98%A4\" target=\"_blank\" rel=\"noopener\">先天性动脉瘤</a>。　</div>\n<div class=\"para\">（2）动脉硬化性<a href=\"https://baike.baidu.com/item/%E5%8A%A8%E8%84%89%E7%98%A4\" target=\"_blank\" rel=\"noopener\">动脉瘤</a>。　</div>\n<div class=\"para\">（3）感染性动脉瘤。</div>\n<div class=\"para\">（4）<a href=\"https://baike.baidu.com/item/%E8%A1%80%E7%AE%A1%E7%95%B8%E5%BD%A2\" target=\"_blank\" rel=\"noopener\">血管畸形</a>出现障害。</div>\n<div class=\"para\">（5）颅内异常血管网症 出现障害。</div>\n<div class=\"para\"><strong>3、<a href=\"https://baike.baidu.com/item/%E8%84%91%E5%87%BA%E8%A1%80\" target=\"_blank\" rel=\"noopener\">脑出血</a>：</strong></div>\n<div class=\"para\">（1）高血压引起<a href=\"https://baike.baidu.com/item/%E8%84%91%E5%87%BA%E8%A1%80\" target=\"_blank\" rel=\"noopener\">脑出血</a>。</div>\n<div class=\"para\">（2）继发于梗死引起的出血。</div>\n<div class=\"para\">（3）肿瘤性引起出血。</div>\n<div class=\"para\">（4）<a href=\"https://baike.baidu.com/item/%E8%A1%80%E6%B6%B2%E7%97%85\" target=\"_blank\" rel=\"noopener\">血液病</a>引起<a href=\"https://baike.baidu.com/item/%E8%84%91%E5%87%BA%E8%A1%80\" target=\"_blank\" rel=\"noopener\">脑出血</a>。\n<div class=\"lemma-picture text-pic layout-right\"><a class=\"image-link\" title=\"脑血管疾病--血管畸形\" href=\"https://baike.baidu.com/pic/%E8%84%91%E8%A1%80%E7%AE%A1%E7%96%BE%E7%97%85/1231301/0/504ec7f9f85f6b18252df293?fr=lemma&amp;ct=single\" target=\"_blank\" rel=\"noopener\"><img class=\"\" src=\"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=1667b85ab051f819f5250448eab54a76/42166d224f4a20a4695602b690529822720ed072.jpg\" alt=\"脑血管疾病--血管畸形\" /></a><span class=\"description\">脑血管疾病--血管畸形</span></div>\n</div>\n<div class=\"para\">（5）淀粉样<a href=\"https://baike.baidu.com/item/%E8%84%91%E8%A1%80%E7%AE%A1%E7%97%85\" target=\"_blank\" rel=\"noopener\">脑血管病</a>脑出血。</div>\n<div class=\"para\">（6）动脉炎引起<a href=\"https://baike.baidu.com/item/%E8%84%91%E5%87%BA%E8%A1%80\" target=\"_blank\" rel=\"noopener\">脑出血</a>。</div>\n<div class=\"para\">（7）药物引起<a href=\"https://baike.baidu.com/item/%E8%84%91%E5%87%BA%E8%A1%80\" target=\"_blank\" rel=\"noopener\">脑出血</a>。</div>\n<div class=\"para\">（8）<a href=\"https://baike.baidu.com/item/%E8%84%91%E8%A1%80%E7%AE%A1%E7%95%B8%E5%BD%A2\" target=\"_blank\" rel=\"noopener\">脑血管畸形</a>或动脉瘤引起<a href=\"https://baike.baidu.com/item/%E8%84%91%E5%87%BA%E8%A1%80\" target=\"_blank\" rel=\"noopener\">脑出血</a>。</div>\n<div class=\"para\">（9）其他原因引起<a href=\"https://baike.baidu.com/item/%E8%84%91%E5%87%BA%E8%A1%80\" target=\"_blank\" rel=\"noopener\">脑出血</a>。</div>\n<div class=\"para\">（10）原因未明。</div>\n<div class=\"para\"><strong>4、</strong><a href=\"https://baike.baidu.com/item/%E8%84%91%E6%A2%97%E6%AD%BB\" target=\"_blank\" rel=\"noopener\"><strong>脑梗死</strong></a>：</div>\n<div class=\"para\">（）动脉粥样硬化性血栓性脑梗死。</div>\n<div class=\"para\">（2）<a href=\"https://baike.baidu.com/item/%E8%84%91%E6%A0%93%E5%A1%9E\" target=\"_blank\" rel=\"noopener\">脑栓塞</a>引起<a href=\"https://baike.baidu.com/item/%E8%84%91%E6%A2%97%E6%AD%BB\" target=\"_blank\" rel=\"noopener\">脑梗死</a>。</div>\n<div class=\"para\">（3）<a href=\"https://baike.baidu.com/item/%E8%85%94%E9%9A%99%E6%80%A7%E6%A2%97%E6%AD%BB\" target=\"_blank\" rel=\"noopener\">腔隙性梗死</a>引起<a href=\"https://baike.baidu.com/item/%E8%84%91%E6%A2%97%E6%AD%BB\" target=\"_blank\" rel=\"noopener\">脑梗死</a>。</div>\n<div class=\"para\">（4）<a href=\"https://baike.baidu.com/item/%E5%87%BA%E8%A1%80%E6%80%A7%E6%A2%97%E6%AD%BB\" target=\"_blank\" rel=\"noopener\">出血性梗死</a>引起<a href=\"https://baike.baidu.com/item/%E8%84%91%E6%A2%97%E6%AD%BB\" target=\"_blank\" rel=\"noopener\">脑梗死</a>。</div>\n<div class=\"para\">（5）无症状性引起<a href=\"https://baike.baidu.com/item/%E8%84%91%E6%A2%97%E6%AD%BB\" target=\"_blank\" rel=\"noopener\">脑梗死</a>。</div>\n<div class=\"para\">（6）其他引起<a href=\"https://baike.baidu.com/item/%E8%84%91%E6%A2%97%E6%AD%BB\" target=\"_blank\" rel=\"noopener\">脑梗死</a>。</div>\n<div class=\"para\">（7）原因未明。</div>\n<div class=\"para\"><strong>5、</strong><strong><a href=\"https://baike.baidu.com/item/%E9%A2%85%E5%86%85%E5%8A%A8%E8%84%89%E7%98%A4\" target=\"_blank\" rel=\"noopener\">颅内动脉瘤</a>：</strong></div>\n<div class=\"para\">\n<div class=\"lemma-picture text-pic layout-right\"><a class=\"image-link\" title=\"疾病检查\" href=\"https://baike.baidu.com/pic/%E8%84%91%E8%A1%80%E7%AE%A1%E7%96%BE%E7%97%85/1231301/0/b87985502ab4de238535249c?fr=lemma&amp;ct=single\" target=\"_blank\" rel=\"noopener\"><img class=\"\" src=\"https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=fd54794f0cf3d7ca08f63874c21ebe3c/ac345982b2b7d0a269823aeccbef76094b369a73.jpg\" alt=\"疾病检查\" /></a><span class=\"description\">疾病检查</span></div>\n（1）先天性<a href=\"https://baike.baidu.com/item/%E5%8A%A8%E8%84%89%E7%98%A4\" target=\"_blank\" rel=\"noopener\">动脉瘤</a>。</div>\n<div class=\"para\">（2）动脉硬化性动脉瘤。</div>\n<div class=\"para\">（3）感染性动脉瘤。</div>\n<div class=\"para\">（4）外伤性假动脉瘤。</div>\n<div class=\"para\">（5）其他。</div>\n<div class=\"para\"><strong>6、</strong><strong>颅内血管畸形：</strong></div>\n<div class=\"para\">（1）脑动<a href=\"https://baike.baidu.com/item/%E9%9D%99%E8%84%89\" target=\"_blank\" rel=\"noopener\">静脉</a>畸形。</div>\n<div class=\"para\">（2）<a href=\"https://baike.baidu.com/item/%E6%B5%B7%E7%BB%B5%E7%8A%B6%E8%A1%80%E7%AE%A1%E7%98%A4\" target=\"_blank\" rel=\"noopener\">海绵状血管瘤</a>。</div>\n<div class=\"para\">（3）静脉性血管畸形。</div>\n<div class=\"para\">（4）<a href=\"https://baike.baidu.com/item/Galen%E9%9D%99%E8%84%89%E7%98%A4\" target=\"_blank\" rel=\"noopener\">Galen静脉瘤</a>。</div>\n<div class=\"para\">\n<div class=\"lemma-picture text-pic layout-right\"><a class=\"image-link\" title=\"治疗\" href=\"https://baike.baidu.com/pic/%E8%84%91%E8%A1%80%E7%AE%A1%E7%96%BE%E7%97%85/1231301/0/0db2c9ca59b30107f21fe79e?fr=lemma&amp;ct=single\" target=\"_blank\" rel=\"noopener\"><img class=\"\" src=\"https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=9161e085b74543a9f11bfdce2e168a7b/8b13632762d0f703b3f7abad08fa513d2697c57d.jpg\" alt=\"治疗\" /></a><span class=\"description\">治疗</span></div>\n（5）<a href=\"https://baike.baidu.com/item/%E9%A2%88%E5%86%85%E5%8A%A8%E8%84%89%E6%B5%B7%E7%BB%B5%E7%AA%A6%E7%98%98\" target=\"_blank\" rel=\"noopener\">颈内动脉海绵窦瘘</a>。</div>\n<div class=\"para\">（6）<a href=\"https://baike.baidu.com/item/%E6%AF%9B%E7%BB%86%E8%A1%80%E7%AE%A1%E6%89%A9%E5%BC%A0%E7%97%87\" target=\"_blank\" rel=\"noopener\">毛细血管扩张症</a>。</div>\n<div class=\"para\">（7）毛细血管瘤。</div>\n<div class=\"para\">（8）脑-面血管瘤病。</div>\n<div class=\"para\">（9）颅内-颅外血管交通性动静脉畸形。</div>\n<div class=\"para\">（10）其他。</div>\n<div class=\"para\"><strong>7、</strong><strong><a href=\"https://baike.baidu.com/item/%E8%84%91%E5%8A%A8%E8%84%89%E7%82%8E\" target=\"_blank\" rel=\"noopener\">脑动脉炎</a>:</strong></div>\n<div class=\"para\">（1）感染性动脉炎。</div>\n<div class=\"para\">\n<div class=\"lemma-picture text-pic layout-right\"><a class=\"image-link\" title=\"脑血管疾病--多吃水果\" href=\"https://baike.baidu.com/pic/%E8%84%91%E8%A1%80%E7%AE%A1%E7%96%BE%E7%97%85/1231301/0/3bc6f7500a53cd1d1038c29f?fr=lemma&amp;ct=single\" target=\"_blank\" rel=\"noopener\"><img class=\"\" src=\"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=387123791f30e924cba49b337c096e66/f7246b600c338744d481110d510fd9f9d72aa07e.jpg\" alt=\"脑血管疾病--多吃水果\" /></a><span class=\"description\">脑血管疾病--多吃水果</span></div>\n（2）<a href=\"https://baike.baidu.com/item/%E5%A4%A7%E5%8A%A8%E8%84%89%E7%82%8E\" target=\"_blank\" rel=\"noopener\">大动脉炎</a>（<a href=\"https://baike.baidu.com/item/%E4%B8%BB%E5%8A%A8%E8%84%89%E5%BC%93%E7%BB%BC%E5%90%88%E5%BE%81\" target=\"_blank\" rel=\"noopener\">主动脉弓综合征</a>）。</div>\n<div class=\"para\">（3）<a href=\"https://baike.baidu.com/item/%E7%B3%BB%E7%BB%9F%E6%80%A7%E7%BA%A2%E6%96%91%E7%8B%BC%E7%96%AE\" target=\"_blank\" rel=\"noopener\">系统性红斑狼疮</a>。</div>\n<div class=\"para\">（4）<a href=\"https://baike.baidu.com/item/%E7%BB%93%E8%8A%82%E6%80%A7%E5%A4%9A%E5%8A%A8%E8%84%89%E7%82%8E\" target=\"_blank\" rel=\"noopener\">结节性多动脉炎</a>。</div>\n<div class=\"para\">（5）<a href=\"https://baike.baidu.com/item/%E9%A2%9E%E5%8A%A8%E8%84%89%E7%82%8E\" target=\"_blank\" rel=\"noopener\">颞动脉炎</a>。</div>\n<div class=\"para\">（6）闭塞性<a href=\"https://baike.baidu.com/item/%E8%A1%80%E6%A0%93%E6%80%A7%E8%84%89%E7%AE%A1%E7%82%8E\" target=\"_blank\" rel=\"noopener\">血栓性脉管炎</a>。</div>\n<div class=\"para\">（7）其他。</div>\n<div class=\"para\"><strong>8、</strong><strong>其他动脉疾病:</strong></div>\n<div class=\"para\">（1）脑<a href=\"https://baike.baidu.com/item/%E5%8A%A8%E8%84%89\" target=\"_blank\" rel=\"noopener\">动脉</a><a href=\"https://baike.baidu.com/item/%E7%9B%97%E8%A1%80%E7%BB%BC%E5%90%88%E5%BE%81\" target=\"_blank\" rel=\"noopener\">盗血综合征</a>。</div>\n<div class=\"para\">（2）颅内异常血管网症。</div>\n<div class=\"para\">（3）动脉肌纤维发育不良。</div>\n<div class=\"para\">（4）淀粉样血管病。</div>\n<div class=\"para\">（5）动脉壁夹层病变。</div>\n<div class=\"para\">（6）其他。</div>\n<div class=\"para\"><strong>9、</strong><strong>颅内静脉病:</strong></div>\n<div class=\"para\">（1）<a href=\"https://baike.baidu.com/item/%E6%B5%B7%E7%BB%B5%E7%AA%A6%E8%A1%80%E6%A0%93%E5%BD%A2%E6%88%90\" target=\"_blank\" rel=\"noopener\">海绵窦血栓形成</a>。</div>\n<div class=\"para\">（2）<a href=\"https://baike.baidu.com/item/%E4%B8%8A%E7%9F%A2%E7%8A%B6%E7%AA%A6%E8%A1%80%E6%A0%93%E5%BD%A2%E6%88%90\" target=\"_blank\" rel=\"noopener\">上矢状窦血栓形成</a>。</div>\n<div class=\"para\">（3）<a href=\"https://baike.baidu.com/item/%E7%9B%B4%E7%AA%A6%E8%A1%80%E6%A0%93%E5%BD%A2%E6%88%90\" target=\"_blank\" rel=\"noopener\">直窦血栓形成</a>。</div>\n<div class=\"para\">（4）横窦血栓形成。</div>\n<div class=\"para\">（5）其他。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"3\"></a><a class=\"lemma-anchor \" name=\"sub68016_3\"></a><a class=\"lemma-anchor \" name=\"缺血性脑血管病\"></a></div>\n<div class=\"para-title level-2\">&nbsp;</div>',143,NULL,'admin','2019-05-15 21:24:57',NULL,'2019-05-15 21:24:57'),
(14,13,'001','1','糖尿病',144,'<p class=\"p2\">中医认为：</p>\n<p class=\"p2\">消渴者，足厥阴之病也。心移寒于肺，肺消，肺消者，饮一溲二，死不治。</p>\n<p class=\"p2\">上伤燥热，下病湿寒，燥热在肝肺之经，湿寒在脾肾之脏。肝主疏泄，木愈郁而欲泄，泄而不通，则小便不利，泄而失藏，则水泉不止</p>\n<p class=\"p2\">西医认为：</p>\n<p class=\"p2\">糖尿病是一组以高血糖为特征的代谢性疾病。高血糖则是由于胰岛素分泌缺陷或其生物作用受损，或两者兼有引起。糖尿病时长期存在的高血糖，导致各种组织，特别是眼、肾、心脏、血管、神经的慢性损害、功能障碍糖尿病，是由于胰岛素分泌不足导致血糖过高代谢障碍。</p>',145,NULL,'admin','2019-05-24 23:36:25','admin','2019-05-24 23:36:25'),
(15,13,'090','1','乙型流感',146,'<div class=\"para-title level-2\">\n<h2 class=\"title-text\">行性感冒</h2>\n</div>\n<div class=\"para\"><a href=\"https://baike.baidu.com/item/%E6%B5%81%E8%A1%8C%E6%80%A7%E6%84%9F%E5%86%92\" target=\"_blank\" rel=\"noopener\">流行性感冒</a>（influenza，简称流感）是流感病毒引起的急性呼吸道感染，也是一种<a href=\"https://baike.baidu.com/item/%E4%BC%A0%E6%9F%93\" target=\"_blank\" rel=\"noopener\">传染</a>性强、传播速度快的<a href=\"https://baike.baidu.com/item/%E7%96%BE%E7%97%85\" target=\"_blank\" rel=\"noopener\">疾病</a>。其主要通过空气中的飞沫、人与人之间的接触或与被污染物品的接触传播。典型的临床症状是：急起高热、全身疼痛、显著乏力和轻度呼吸道症状。一般秋冬季节是其高发期，所引起的<a href=\"https://baike.baidu.com/item/%E5%B9%B6%E5%8F%91%E7%97%87\" target=\"_blank\" rel=\"noopener\">并发症</a>和<a href=\"https://baike.baidu.com/item/%E6%AD%BB%E4%BA%A1\" target=\"_blank\" rel=\"noopener\">死亡</a>现象非常严重。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"2\"></a><a class=\"lemma-anchor \" name=\"sub2301088_2\"></a><a class=\"lemma-anchor \" name=\"流行性感冒病毒\"></a></div>\n<div class=\"para-title level-2\">\n<h2 class=\"title-text\">流行性感冒病毒</h2>\n</div>\n<div class=\"para\"><a href=\"https://baike.baidu.com/item/%E6%AD%A3%E7%B2%98%E7%97%85%E6%AF%92%E7%A7%91\" target=\"_blank\" rel=\"noopener\">正粘病毒科</a>:一类对<a href=\"https://baike.baidu.com/item/%E7%B2%98%E8%9B%8B%E7%99%BD\" target=\"_blank\" rel=\"noopener\">粘蛋白</a>具有特殊亲和性的RNA病毒</div>\n<div class=\"para\">&middot;分为四属:</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"2_1\"></a><a class=\"lemma-anchor \" name=\"sub2301088_2_1\"></a><a class=\"lemma-anchor \" name=\"甲(A)型流感病毒\"></a><a class=\"lemma-anchor \" name=\"2-1\"></a></div>\n<div class=\"para-title level-3\">\n<h3 class=\"title-text\">甲(A)型流感病毒</h3>\n</div>\n<div class=\"para\">15个H和9个N亚型,仅3个H亚型(H1、H2、H3)和2个N亚型（N1、N2）在人间传播。一些低等动物和禽鸟类是自然宿主。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"2_2\"></a><a class=\"lemma-anchor \" name=\"sub2301088_2_2\"></a><a class=\"lemma-anchor \" name=\"乙(B)型流感病毒\"></a><a class=\"lemma-anchor \" name=\"2-2\"></a></div>\n<div class=\"para-title level-3\">\n<h3 class=\"title-text\">乙(B)型流感病毒</h3>\n</div>\n<div class=\"para\">能否感染人以外的其他动物至今不肯定，有报道可以感染海豹。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"2_3\"></a><a class=\"lemma-anchor \" name=\"sub2301088_2_3\"></a><a class=\"lemma-anchor \" name=\"丙(C)型流感病毒\"></a><a class=\"lemma-anchor \" name=\"2-3\"></a></div>\n<div class=\"para-title level-3\">\n<h3 class=\"title-text\">丙(C)型流感病毒</h3>\n</div>\n<div class=\"para\">已证实可以感染猪．</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"2_4\"></a><a class=\"lemma-anchor \" name=\"sub2301088_2_4\"></a><a class=\"lemma-anchor \" name=\"丁(D)型流感病毒\"></a><a class=\"lemma-anchor \" name=\"2-4\"></a></div>\n<div class=\"para-title level-3\">\n<h3 class=\"title-text\">丁(D)型流感病毒</h3>\n</div>\n<div class=\"para\"><strong>(Thogoto病毒)</strong></div>\n<div class=\"para\"><a href=\"https://baike.baidu.com/item/%E9%9D%9E%E6%B4%B2\" target=\"_blank\" rel=\"noopener\">非洲</a>发现(七条基因)，这个病毒是经由跳蝨<a href=\"https://baike.baidu.com/item/%E4%BC%A0%E6%9F%93\" target=\"_blank\" rel=\"noopener\">传染</a>，会感染牛、羊及人类产生脑膜炎。很少报道．</div>',147,NULL,'admin','2019-05-15 21:27:48',NULL,'2019-05-15 21:27:48');

/*Table structure for table `tb_disease_reason` */

DROP TABLE IF EXISTS `tb_disease_reason`;

CREATE TABLE `tb_disease_reason` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `disease_id` int(11) NOT NULL COMMENT '疾病大类Id',
  `disease_detail_id` int(11) NOT NULL COMMENT '疾病小类Id',
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
  KEY `type` (`type`),
  CONSTRAINT `tb_disease_reason_ibfk_3` FOREIGN KEY (`type`) REFERENCES `tb_disease_reason_type` (`type_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `tb_disease_reason_ibfk_4` FOREIGN KEY (`disease_id`) REFERENCES `tb_disease_class` (`disease_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_disease_reason_ibfk_5` FOREIGN KEY (`disease_detail_id`) REFERENCES `tb_disease_class_detail` (`disease_detail_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='病因分析表(疾病原因)';

/*Data for the table `tb_disease_reason` */

insert  into `tb_disease_reason`(`id`,`disease_id`,`disease_detail_id`,`number`,`title`,`content`,`read_num`,`type`,`memo`,`create_time`,`create_user_name`,`update_time`,`update_user_name`) values 
(12,13,15,'001','一般对症治疗','<div class=\"para-title level-3\">\n<h3 class=\"title-text\">一般对症治疗</h3>\n</div>\n<div class=\"para\">\n<div class=\"lemma-picture text-pic layout-right\"><a class=\"image-link\" title=\"乙型流感预防\" href=\"https://baike.baidu.com/pic/%E4%B9%99%E5%9E%8B%E6%B5%81%E6%84%9F/3089798/0/0e655ca7f6136dacd14358fe?fr=lemma&amp;ct=single\" target=\"_blank\" rel=\"noopener\"><img class=\"\" src=\"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=f274f3779258d109c0e3aeb0e158ccd0/a5c27d1ed21b0ef434f50857ddc451da81cb3e9d.jpg\" alt=\"乙型流感预防\" /></a><span class=\"description\">乙型流感预防</span></div>\n　　卧床休息，多饮水，给予流质或流质饮食，适宜营养，补充维生素，进食后以温开水或温盐水漱口，保持口鼻清洁，全身症状明显时予抗感染治疗。</div>\n<div class=\"para\">&nbsp;</div>\n<div class=\"para\">\n<div class=\"para-title level-3\">\n<h3 class=\"title-text\">早期应用抗病毒治疗</h3>\n</div>\n<div class=\"para\">1、可减少病毒的排毒量，抑制病毒复制，减轻临床症状，并防止病毒向下呼吸道蔓延导致<a href=\"https://baike.baidu.com/item/%E8%82%BA%E7%82%8E\" target=\"_blank\" rel=\"noopener\">肺炎</a>等<a href=\"https://baike.baidu.com/item/%E5%B9%B6%E5%8F%91%E7%97%87\" target=\"_blank\" rel=\"noopener\">并发症</a>。</div>\n<div class=\"para\">2、药物</div>\n<div class=\"para\">1）金刚烷胺为M2离子阻断剂，可阻断病毒吸附于敏感细胞，抑制病毒复制，对甲型流感有效。发病48h内用药效果好。用量：成人200mg/天，老人100mg/天，小孩4-5mg/kg/天；用法：分2次口服，疗程3-4天；副作用：<a href=\"https://baike.baidu.com/item/%E5%8F%A3%E5%B9%B2\" target=\"_blank\" rel=\"noopener\">口干</a>、头晕、嗜睡、共济失调等神经系统症状。</div>\n<div class=\"para\">2）甲基金刚烷胺用量： 100-200mg/天，用法：分2次口服，其抗病毒活性比金刚烷胺高2-4倍，且神经系统副作用少。</div>\n<div class=\"para\">3、注意事项： 孕妇、神经、精神异常、肝肾功能严重受损者禁用，且此两种药物易发生耐药。</div>\n</div>',0,1,NULL,'2019-05-15 21:32:27','admin','2019-05-15 21:35:28','admin'),
(13,13,15,'002','中医医治流感的方法','<div class=\"para-title level-3\">\n<h3 class=\"title-text\">中药治疗</h3>\n</div>\n<div class=\"para\">中医医治流感的方法</div>\n<div class=\"para\">中医学上有句话：&ldquo;正气存内，邪不可干&rdquo;，就是说，若身体强健，便不受外邪（病毒）干扰。因此中医着重治本，一方面会用草本消炎解毒，另一方面会提升<a href=\"https://baike.baidu.com/item/%E8%BA%AB%E4%BD%93%E6%9C%BA%E8%83%BD\" target=\"_blank\" rel=\"noopener\">身体机能</a>，增强免疫力。若只是消除感冒的不适而不提高体质，很容易又会再度受<a href=\"https://baike.baidu.com/item/%E7%97%85%E6%AF%92%E5%85%A5%E4%BE%B5\" target=\"_blank\" rel=\"noopener\">病毒入侵</a>。因此经常感冒的人须加注意，表示他们身体虚弱，抵抗力低下，必须适当作出调理。</div>\n<div class=\"para\">用药方面，变化很大，例如：</div>\n<div class=\"para\">消炎杀菌、提升免疫力：可用板蓝根、连翘、银花</div>\n<div class=\"para\">促进头部血液循环：可用白芷、荆芥、菊花</div>\n<div class=\"para\">清热退烧：可用桑叶</div>\n<div class=\"para\">纾筋活络、解热：可用<a href=\"https://baike.baidu.com/item/%E8%91%9B%E6%A0%B9\" target=\"_blank\" rel=\"noopener\">葛根</a>、桑枝、丝瓜络</div>\n<div class=\"para\">民间食疗：热柠乐煲姜</div>\n<div class=\"para\">感冒初起，很多人习惯到茶餐厅来一杯热柠檬可乐煲姜，纾缓症状。据说，效果颇佳。</div>\n<div class=\"para\">到底此&ldquo;乐&rdquo;何解灵验？按<a href=\"https://baike.baidu.com/item/%E4%B8%AD%E5%8C%BB%E7%90%86%E8%AE%BA\" target=\"_blank\" rel=\"noopener\">中医理论</a>，姜可行气活血，如属<a href=\"https://baike.baidu.com/item/%E9%A3%8E%E5%AF%92%E5%9E%8B%E6%84%9F%E5%86%92\" target=\"_blank\" rel=\"noopener\">风寒型感冒</a>，乍冷乍热，作闷没胃口时，来一杯「劲」姜可乐，确可暖胃驱风寒，算是对证下药。还有一条食疗方，是用陈皮加姜煲粥，代替三餐，对於病毒入胃者，特别适宜。</div>\n<div class=\"para\">至于橙汁，据闻可加速感冒痊愈，是否属实？平时多补充维生素C，的确可以减低感染的机会，但由于大部分水果属性偏凉，容易引起咳嗽，因此有咳时不宜多吃。只有苹果勉强可吃，或者将橙炖热才吃也可，不过食味当然不同。</div>\n<div class=\"para\">反而热柠乐不妨一喝，因为新鲜柠檬含丰富维生素C，开胃提神，蜜糖又可滋润，热呼呼的喝下，喉头的痕痒不适确会减轻。</div>\n<div class=\"para\">另有一条简单配方，对<a href=\"https://baike.baidu.com/item/%E6%94%B6%E9%BC%BB%E6%B0%B4\" target=\"_blank\" rel=\"noopener\">收鼻水</a>甚有功效，惟一缺点是味道欠佳：先将姜煮20分钟，其後加入淡豆鼓和叁条?#93;白，以及1钱(3克)薄荷叶，再煮约2分钟。</div>',0,1,NULL,'2019-05-15 21:32:59','admin','2019-05-15 21:35:36','admin'),
(14,15,12,'003','疾病治疗','<div class=\"para-title level-2\">\n<h2 class=\"title-text\">治疗方案</h2>\n</div>\n<div class=\"para\">1.严格掌握糖皮质激素及广谱抗生素的应用。</div>\n<div class=\"para\">2.积极治疗原发病，加强支持疗法，提高机体免疫力。</div>\n<div class=\"para\">3.病原治疗</div>\n<div class=\"para\">(1)对<a href=\"https://baike.baidu.com/item/%E9%9A%90%E7%90%83%E8%8F%8C%E8%84%91%E8%86%9C%E7%82%8E\" target=\"_blank\" rel=\"noopener\">隐球菌脑膜炎</a>患者：①用二性霉素B加5-氟胞嘧啶联合治疗具有协同作用。二性霉素B lmg/(kg&middot;d)静滴、5-氟胞嘧啶50～150mg/(kg&middot;d)分3次口服或静滴共6周。治疗前、治疗中每2周复查BUN及血象以确定用药的适当时间间隔。治疗前测肾功及血象，对肾功损害及骨髓抑制的患者应慎用(二性霉素B首次静脉滴注剂量为0.1mg/(kZ&middot;d)，以后每日增加0.05～0.10mg/kg直至1.0mg/kg，但每日剂量不超过50mg/d，药物溶解于5%葡萄糖液，最佳浓度为0.1mg/m1)。二性霉素B也可与利福平联合应用，亦有协同作用。②咪康唑(双氯苯咪唑)常用于不能耐受二性霉素B或治疗反应不佳的深部真菌患者。对念珠菌、曲菌、隐球菌、组织胞浆菌或环孢子菌等全身感染有效。剂量：0.6g～1.2g/d分3次静滴，鞘内注射成人20mg每3～7d一次。③氟康唑有广谱抗真菌作用，口服吸收良好，能透过血脑屏障，200mg每日1～2次。④球红霉素口服400～2000U/(kg&middot;d)，静滴40～100U/(kg&middot;d)开始；渐增至600～800U/(kg&middot;d)，成人总量不超过300万～400万U。</div>\n<div class=\"para\">(2)肺部隐球菌病轻型患者可不用抗真菌药物，如无条件随访可口服酮康唑200mg～400mg/d。</div>\n<div class=\"para\">4、护理</div>\n<div class=\"para\">(1)按传染病一般护理常规护理。高热时按高热护理常规护理。</div>\n<div class=\"para\">(2)及时留送痰、血、尿、粪便、脑脊髓液、脓液等，进行常规检查及培养。</div>\n<div class=\"para\">(3)3.密切观察病情，特别注意咳嗽、咯血、呼吸、神志改变等，如有变化应及时与医师联系。</div>\n<div class=\"para\">(4)及时做好隔离、空气消毒、口腔卫生、皮肤护理等。</div>\n<div class=\"para\">5、出院标准</div>\n<div class=\"para\">临床症状与体征消失，精神、食欲恢复，血、脑脊液等常规检查正常，培养阴性。<sup class=\"sup--normal\" data-sup=\"2\" data-ctrmap=\":2,\">&nbsp;[2]</sup><a class=\"sup-anchor\" name=\"ref_[2]_717855\"></a>&nbsp;</div>',0,2,NULL,'2019-05-15 21:38:06','admin','2019-05-15 21:38:06',NULL),
(15,16,9,'005','囊肿型淋巴管瘤成因','<div class=\"para-title level-2\">\n<h2 class=\"title-text\">治疗</h2>\n</div>\n<div class=\"para\">颈部囊状淋巴管瘤的治疗方法对于较小局限的淋巴管瘤不影响功能又无碍美观者可不予治疗因为部分淋巴管瘤有自然消退的趋势对于病变虽较广泛但无呼吸吞嚥困难征象和其它严重并发症者可暂不作处理观察随访一年若未见消退或反而增大者再予手术治疗。</div>\n<div class=\"para\">囊肿型淋巴管瘤注射疗法 以往认为局部注射硬化剂治疗淋巴管瘤的方法无明显效果近年应用抗肿瘤药物博莱霉素（bleomycin）作局部注射疗法取得较为满意的疗效完全消退和显著缩小者可达%可能是通过抑制淋巴管内皮细胞的生长和化学刺激物使间质纤维化的双重作用而达到治疗目的从组织学来看对间质较多的类型如单纯性和海绵状淋巴管瘤的作用较差而对间质和的类型如囊状水瘤的作用较好实际应用亦表明如此</div>\n<div class=\"para\">博莱霉素有水剂和乳剂两种使用水剂量先配成mg/ml的浓度以每次剂量.～.mg/kg注入淋巴管瘤内每周次～次为疗程使用乳剂时每次.mg/kg～周后重复总剂量不宜超过mg/kg据认为乳剂比水剂效果更好副作用较少注射后～周内局部有过性肿胀然后逐渐缩小硬结但有定的副作用当天或次日有发热℃左右偶有腹泻呕吐最严重的并发症是肺纤维化</div>\n<div class=\"para\">最近报道应用OK-（picibanil种溶血性链球菌制剂）进行局部注射疗法先将个临床单位（ke）的OL-溶解在ml生理盐水?穿刺油液后等量的OK-溶液注入瘤腔内次注入量以不超过个临床单位为限观察～周后追加注射～次组例中例经过～次注射后～个月完全消退例显著缩小例无效副作用是局部的炎性反应有～天的肿胀过性发热此法可避免博莱霉素所引起肺纤维化的危险</div>\n<div class=\"para\">由于注射疗法较为简便对组织破坏少可避免因手术而可能发生的严重并发症况且手术往往亦难以完全切除所以可作为囊状淋巴管瘤的首选疗法</div>\n<div class=\"para\">囊肿型淋巴管瘤手术治疗 手术切除虽然仍为淋巴管瘤的主要治疗方法但目前不主张毫无指证地对任何类型的淋巴管瘤进行手术颈部淋巴管瘤有向纵隔胸腔扩张趋势有引起呼吸困难可能影响进食并经注射治疗无效者才是手术适应证淋巴管瘤并发感染时不宜手术须先行控制感染囊内出血并非手术禁忌</div>\n<div class=\"para\">囊肿型淋巴管瘤的实际病变范围往往超出原先的估计手术时常难以彻底切除手术时要求仔细解剖颈部的重要神经血管等结构防止面神经麻痹和舌神经喉返神经膈神经损伤而引起呼吸困难和声音嘶哑对残存的囊壁可涂擦.%碘酊破坏内皮细胞以防复发</div>\n<div class=\"para\">囊肿型淋巴管瘤又称囊性水瘤 主要发生于颈部锁骨之上 亦可发生于颌下区及上颈部 一般为多房性囊腔 彼此间隔 内有透明 淡黄色水样液体 肿瘤大小不一 表面皮肤色泽正常 呈充盈状态 有波动感 与深层血管瘤不同的是体位移动试验为阴性</div>\n<div class=\"para\">淋巴管瘤的诊断除病史及临床表现外 对囊肿型有时还需作穿刺检查 以明确诊断</div>\n<div class=\"para\">淋巴管瘤的治疗 主要是采用外科手术切除 对范围较大的肿瘤可分期切除</div>',0,2,NULL,'2019-05-15 21:38:46','admin','2019-05-15 21:38:46',NULL),
(16,14,11,'006','浸润型肺结核原因','<div class=\"para-title level-2\">\n<h2 class=\"title-text\">检查</h2>\n</div>\n<div class=\"para\">实验室和其他检查：</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"5_1\"></a><a class=\"lemma-anchor \" name=\"sub1219395_5_1\"></a><a class=\"lemma-anchor \" name=\"结核菌检查\"></a><a class=\"lemma-anchor \" name=\"5-1\"></a></div>\n<div class=\"para-title level-3\">\n<h3 class=\"title-text\">结核菌检查</h3>\n</div>\n<div class=\"para\">是确诊肺结核最特异性的方法，痰中找到结核菌是确诊肺结核的主要\n<div class=\"lemma-picture text-pic layout-right\"><a class=\"image-link\" title=\"浸润型肺结核诊断检查\" href=\"https://baike.baidu.com/pic/%E6%B5%B8%E6%B6%A6%E5%9E%8B%E8%82%BA%E7%BB%93%E6%A0%B8/8670438/0/734f12f3bc8dba880a46e0a8?fr=lemma&amp;ct=single\" target=\"_blank\" rel=\"noopener\"><img class=\"\" src=\"https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=6112725aebc4b7453094b014fffc1e78/a8ec8a13632762d09c4450cca0ec08fa513dc64f.jpg\" alt=\"浸润型肺结核诊断检查\" /></a><span class=\"description\">浸润型肺结核诊断检查</span></div>\n依据。涂片抗酸染色镜检快速简便在我国<a href=\"https://baike.baidu.com/item/%E9%9D%9E%E5%85%B8%E5%9E%8B%E5%88%86%E6%9E%9D%E6%9D%86%E8%8F%8C\" target=\"_blank\" rel=\"noopener\">非典型分枝杆菌</a>尚属少见，故抗酸杆菌样应，肺结核诊断基本即可成立。直接厚涂片<a href=\"https://baike.baidu.com/item/%E9%98%B3%E6%80%A7\" target=\"_blank\" rel=\"noopener\">阳性</a>率优于薄涂片，为目前普通采用。荧光显微镜检查适合于大量<a href=\"https://baike.baidu.com/item/%E6%A0%87%E6%9C%AC\" target=\"_blank\" rel=\"noopener\">标本</a>快速检查。无痰或儿童不会咳嗽，可采用清晨的胃洗液找结核菌，承认亦可通过<a href=\"https://baike.baidu.com/item/%E7%BA%A4%E6%94%AF%E9%95%9C\" target=\"_blank\" rel=\"noopener\">纤支镜</a>进差，或从其涮洗液汁<a href=\"https://baike.baidu.com/item/%E4%B8%AD%E5%9B%BD\" target=\"_blank\" rel=\"noopener\">中国</a>查找结核菌。痰菌<a href=\"https://baike.baidu.com/item/%E9%98%B3%E6%80%A7\" target=\"_blank\" rel=\"noopener\">阳性</a>表明其病灶是开放性的，舆论传染性。若排菌量多（每毫升10万个以上），直接涂片易呈阳性，为社会传染源于痰菌量较少（每毫升1万个以下），可用集菌法。培养法更为精确，除能了解结核菌有无生长繁殖能力外，且可作<a href=\"https://baike.baidu.com/item/%E8%8D%AF%E7%89%A9%E6%95%8F%E6%84%9F%E8%AF%95%E9%AA%8C\" target=\"_blank\" rel=\"noopener\">药物敏感试验</a>与菌型鉴定。结核菌生长缓慢，使用改良罗氏培养基，通常需4－8周才能报告。培养虽较费时，但精确可靠，特异性高，若涂片阴性或诊断有疑问时，培养尤其重要，培养菌株进一步作药物敏感性测定，可为治疗特别是复治时提供参考。将<a href=\"https://baike.baidu.com/item/%E6%A0%87%E6%9C%AC\" target=\"_blank\" rel=\"noopener\">标本</a>在体外用<a href=\"https://baike.baidu.com/item/%E8%81%9A%E5%90%88%E9%85%B6%E9%93%BE%E5%8F%8D%E5%BA%94\" target=\"_blank\" rel=\"noopener\">聚合酶链反应</a>（PCR）法，使所含微量结核菌DNA得到扩增，用电泳法检出，1个结核菌约含1fgDNA，40个结核菌即可有<a href=\"https://baike.baidu.com/item/%E9%98%B3%E6%80%A7\" target=\"_blank\" rel=\"noopener\">阳性</a>结果。该法不必体外预培养，特异性强，2天即可出报告，快速、简便，并可鉴定菌型，不足之处是可能出现假阳性或假阴性。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"5_2\"></a><a class=\"lemma-anchor \" name=\"sub1219395_5_2\"></a><a class=\"lemma-anchor \" name=\"影像学检查\"></a><a class=\"lemma-anchor \" name=\"5-2\"></a></div>\n<div class=\"para-title level-3\">\n<h3 class=\"title-text\">影像学检查</h3>\n</div>\n<div class=\"para\">\n<div class=\"lemma-picture text-pic layout-right\"><a class=\"image-link\" title=\"\" href=\"https://baike.baidu.com/pic/%E6%B5%B8%E6%B6%A6%E5%9E%8B%E8%82%BA%E7%BB%93%E6%A0%B8/8670438/0/7a8a14464e37cd306b63e5c7?fr=lemma&amp;ct=single\" target=\"_blank\" rel=\"noopener\"><img class=\"\" src=\"https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=0b6d30908226cffc6d2ab8b089014a7d/63d0f703918fa0ecb4d22204269759ee3d6ddba6.jpg\" alt=\"\" /></a></div>\n胸部X线检查可以发现肺内病变的部位、范围，有无空洞或空洞大小，洞壁厚薄等。X线对各类<a href=\"https://baike.baidu.com/item/%E7%BB%93%E6%A0%B8%E7%97%85\" target=\"_blank\" rel=\"noopener\">结核病</a>变的透过度不同，通过X线检查大致能估计结核病灶的病理性质，并能早期发现肺结核，以及判断病情发展及治疗效果，有助于决定治疗方案。必须指出，不同病因引起的肺内病变，可能呈现相似的X线影像，故亦不能仅凭X线检查轻易确定肺结核的诊断。X线摄片结合透视有助于提高诊断的准确性，可发现肋骨、<a href=\"https://baike.baidu.com/item/%E7%BA%B5%E9%9A%94\" target=\"_blank\" rel=\"noopener\">纵隔</a>、膈肌或被心脏遮盖的细胞病灶，并能观察心、肺、膈肌的动态。肺结核的X线表现包括：纤维钙化的硬结病灶，表现为密度较高、边缘清晰的斑点、条索取或者结节；浸润性病灶，表现为密度较单，边缘模糊的云雾状阴影；干酪样病灶，表现为密度较高，浓淡不一，有环形边界透光区的空洞等。<a href=\"https://baike.baidu.com/item/%E8%82%BA%E7%BB%93%E6%A0%B8%E7%97%85%E7%81%B6\" target=\"_blank\" rel=\"noopener\">肺结核病灶</a>通常在肺上部、单侧或双侧，促成时间较长，且有多种不同性质的病灶混合存在及肺内播散迹象。凡X线胸片上显示渗出性或渗出增殖性病灶、<a href=\"https://baike.baidu.com/item/%E5%B9%B2%E9%85%AA%E6%A0%B7%E8%82%BA%E7%82%8E\" target=\"_blank\" rel=\"noopener\">干酪样肺炎</a>、干酪样病灶、空洞（除净化空洞外），均提示为活动性病变；增殖性病变、纤维包裹紧密的干酪硬结灶火及纤维<a href=\"https://baike.baidu.com/item/%E9%92%99%E5%8C%96%E7%81%B6\" target=\"_blank\" rel=\"noopener\">钙化灶</a>等，均属非活动性病变。活动性病灶的痰中仍可找到结核菌。由于肺<a href=\"https://baike.baidu.com/item/%E7%BB%93%E6%A0%B8%E7%97%85\" target=\"_blank\" rel=\"noopener\">结核病</a>变多为混合性，在未达到完全增殖或纤维钙化时，均仍应考虑为活动性。肺部CT检查对于发现微小或隐蔽性病变，了解病变范围及肺病变鉴别等方面均有帮助。\n<div class=\"lemma-picture text-pic layout-right\"><a class=\"image-link\" title=\"浸润型肺结核CT检查\" href=\"https://baike.baidu.com/pic/%E6%B5%B8%E6%B6%A6%E5%9E%8B%E8%82%BA%E7%BB%93%E6%A0%B8/8670438/0/d0526df0e52f2996a40f52c3?fr=lemma&amp;ct=single\" target=\"_blank\" rel=\"noopener\"><img class=\"\" src=\"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=b675bf866c81800a6ae58e0c813533d6/dcc451da81cb39dbd1d0215fd0160924ab1830a2.jpg\" alt=\"浸润型肺结核CT检查\" /></a><span class=\"description\">浸润型肺结核CT检查</span></div>\n</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"5_3\"></a><a class=\"lemma-anchor \" name=\"sub1219395_5_3\"></a><a class=\"lemma-anchor \" name=\"结核菌素试验\"></a><a class=\"lemma-anchor \" name=\"5-3\"></a></div>\n<div class=\"para-title level-3\">\n<h3 class=\"title-text\">结核菌素试验</h3>\n</div>\n<div class=\"para\">结核菌素试验（简称结素）试验，是诊断结核感染的参考指标。</div>\n<div class=\"para\">旧结素（OT）是结核菌的代谢产物，由液体培养长出的结核菌提炼而成，主要含有结核蛋白。OT抗原不纯，可能引起非特异性反应。在人群中作普查时，可用1：2000的OT稀释液0.1ml（51U），在左前臂屈侧作皮内注射，经48－72小时测量皮肤硬结直径，如小于5mm为阴性，5－9mm为<a href=\"https://baike.baidu.com/item/%E5%BC%B1%E9%98%B3%E6%80%A7\" target=\"_blank\" rel=\"noopener\">弱阳性</a>（提示结核菌或<a href=\"https://baike.baidu.com/item/%E7%BB%93%E6%A0%B8%E5%88%86%E6%9E%9D%E6%9D%86%E8%8F%8C\" target=\"_blank\" rel=\"noopener\">结核分枝杆菌</a>感染），10－19mm为阳性反应，20mm以上或局部出现水泡与坏死者为强阳性反应。</div>\n<div class=\"para\">结素的纯蛋白衍化物（PPD）由旧结素滤液中提取结核蛋白精制而成，为纯结素，不产生非特异性反应。国际上常用的PPD－RT23，已经取代OT。我国从人型结核菌制成PPD（PPD－C）及从<a href=\"https://baike.baidu.com/item/%E5%8D%A1%E4%BB%8B%E8%8B%97\" target=\"_blank\" rel=\"noopener\">卡介苗</a>制成BCG－PPD，纯度均较好，已广泛用于临床诊断，皮内注射0.1ml（5IU）硬结平均直径&ge;5bm为阳性反应。结素试验除引起局部皮肤反应外，偶可引起全身反应。临床诊断通常使用51U，如无反应，可在一<a href=\"https://baike.baidu.com/item/%E5%91%A8%E5%90%8E\" target=\"_blank\" rel=\"noopener\">周后</a>再用5IU（产生结素增强效应），如仍为阴性，大致可除外结核感染。</div>\n<div class=\"para\">结素试验仍是<a href=\"https://baike.baidu.com/item/%E7%BB%93%E6%A0%B8%E7%97%85\" target=\"_blank\" rel=\"noopener\">结核病</a>综合诊断中常用手段之一，有助于判断有无结核菌感染。若呈强阳性反应，常表示为活动性<a href=\"https://baike.baidu.com/item/%E7%BB%93%E6%A0%B8%E7%97%85\" target=\"_blank\" rel=\"noopener\">结核病</a>。结素试验对<a href=\"https://baike.baidu.com/item/%E5%A9%B4%E5%B9%BC%E5%84%BF\" target=\"_blank\" rel=\"noopener\">婴幼儿</a>的诊断价值较成人为大，因年龄越小，自己<a href=\"https://baike.baidu.com/item/%E6%84%9F%E6%9F%93%E7%8E%87\" target=\"_blank\" rel=\"noopener\">感染率</a>越低；3岁以下强阳性反应者，应视为有新近感染的活动性<a href=\"https://baike.baidu.com/item/%E7%BB%93%E6%A0%B8%E7%97%85\" target=\"_blank\" rel=\"noopener\">结核病</a>，有必要进行治疗。如果2年内结素反应从&lt;10mm增加至100mm以上，并增加6mm以上时，可认为有新感染。结素试验阴性反应除表示没有结核菌感染外，尚应考虑以下情况。结核菌感染后需4－8周才建立充分变态反应，在该变态反应产生之前，结素试验可呈阴性。应用糖皮质激素等<a href=\"https://baike.baidu.com/item/%E5%85%8D%E7%96%AB%E6%8A%91%E5%88%B6\" target=\"_blank\" rel=\"noopener\">免疫抑制</a>药物，或营养不良，麻疹、百日咳等患者，结素反应亦可暂时消失。严重<a href=\"https://baike.baidu.com/item/%E7%BB%93%E6%A0%B8%E7%97%85\" target=\"_blank\" rel=\"noopener\">结核病</a>及各种重危患者对结素无反应，或仅出现<a href=\"https://baike.baidu.com/item/%E5%BC%B1%E9%98%B3%E6%80%A7\" target=\"_blank\" rel=\"noopener\">弱阳性</a>，与人体免疫力及变态反应暂时受抑制有关，待病情好转，可转为阳性反应。其他如淋巴细胞免疫系统缺陷（如<a href=\"https://baike.baidu.com/item/%E8%B4%A5%E8%A1%80%E7%97%85\" target=\"_blank\" rel=\"noopener\">败血病</a>、<a href=\"https://baike.baidu.com/item/%E6%B7%8B%E5%B7%B4%E7%98%A4\" target=\"_blank\" rel=\"noopener\">淋巴瘤</a>、<a href=\"https://baike.baidu.com/item/%E7%BB%93%E8%8A%82%E7%97%85\" target=\"_blank\" rel=\"noopener\">结节病</a>、艾滋病等）前者或年老体衰者的结素反应亦常为阴性。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"5_4\"></a><a class=\"lemma-anchor \" name=\"sub1219395_5_4\"></a><a class=\"lemma-anchor \" name=\"其他检查\"></a><a class=\"lemma-anchor \" name=\"5-4\"></a></div>\n<div class=\"para-title level-3\">\n<h3 class=\"title-text\">其他检查</h3>\n</div>\n<div class=\"para\"><a href=\"https://baike.baidu.com/item/%E7%BB%93%E6%A0%B8%E7%97%85\" target=\"_blank\" rel=\"noopener\">结核病</a>患者血像通常无改变，严重病理常有继发性贫血，<a href=\"https://baike.baidu.com/item/%E6%80%A5%E6%80%A7%E7%B2%9F%E7%B2%92%E5%9E%8B%E8%82%BA%E7%BB%93%E6%A0%B8\" target=\"_blank\" rel=\"noopener\">急性粟粒型肺结核</a>对白细胞总数减低或出现<a href=\"https://baike.baidu.com/item/%E7%B1%BB%E7%99%BD%E8%A1%80%E7%97%85%E5%8F%8D%E5%BA%94\" target=\"_blank\" rel=\"noopener\">类白血病反应</a>。血<a href=\"https://baike.baidu.com/item/%E9%99%88%E5%A2%9E\" target=\"_blank\" rel=\"noopener\">陈增</a>快常见于<a href=\"https://baike.baidu.com/item/%E6%B4%BB%E5%8A%A8%E6%80%A7%E8%82%BA%E7%BB%93%E6%A0%B8\" target=\"_blank\" rel=\"noopener\">活动性肺结核</a>，但并无特异性诊断价值，血沉正常亦不能排除活动性肺结核。患者无痰或痰菌阴性而需与其他疾病鉴别时，用酶联免疫吸附试验（<a href=\"https://baike.baidu.com/item/ELISA%E6%B3%95\" target=\"_blank\" rel=\"noopener\">ELISA法</a>）检出患者血清中特异性<a href=\"https://baike.baidu.com/item/%E6%8A%97%E4%BD%93\" target=\"_blank\" rel=\"noopener\">抗体</a>，可能对<a href=\"https://baike.baidu.com/item/%E8%82%BA%E5%A4%96%E7%BB%93%E6%A0%B8\" target=\"_blank\" rel=\"noopener\">肺外结核</a>的诊断提供参考。纤支镜检查对于发现支气管内膜结核、了解有无肿瘤、吸取分泌物、解除阻塞或作病原菌及脱落细胞检查，以及取活组织<a href=\"https://baike.baidu.com/item/%E7%97%85%E7%90%86%E6%A3%80%E6%9F%A5\" target=\"_blank\" rel=\"noopener\">病理检查</a>等，均有重要诊断价值。浅表<a href=\"https://baike.baidu.com/item/%E6%B7%8B%E5%B7%B4%E7%BB%93\" target=\"_blank\" rel=\"noopener\">淋巴结</a>活检，有助于结核的鉴别诊断。</div>\n<div class=\"para\">近年来，应用分子生物学及<a href=\"https://baike.baidu.com/item/%E5%9F%BA%E5%9B%A0%E5%B7%A5%E7%A8%8B%E6%8A%80%E6%9C%AF\" target=\"_blank\" rel=\"noopener\">基因工程技术</a>，以非培养方法来检出与鉴定临床标本中的结核菌，展示其敏感、快速及特异性高等优点，如核算探针、染色体核算<a href=\"https://baike.baidu.com/item/%E6%8C%87%E7%BA%B9\" target=\"_blank\" rel=\"noopener\">指纹</a>等。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"6\"></a><a class=\"lemma-anchor \" name=\"sub1219395_6\"></a><a class=\"lemma-anchor \" name=\"诊断\"></a></div>\n<div class=\"para-title level-2\">\n<h2 class=\"title-text\">诊断</h2>\n</div>\n<div class=\"para\">痰结核菌检查不仅是诊断肺结核的主要依据，亦是考核疗效、随访病情的重\n<div class=\"lemma-picture text-pic layout-right\"><a class=\"image-link\" title=\"浸润型肺结核\" href=\"https://baike.baidu.com/pic/%E6%B5%B8%E6%B6%A6%E5%9E%8B%E8%82%BA%E7%BB%93%E6%A0%B8/8670438/0/7aad4ae79d99ee6db93820a9?fr=lemma&amp;ct=single\" target=\"_blank\" rel=\"noopener\"><img class=\"\" src=\"https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=8bec876009f79052eb1f403c3cf3d738/0dd7912397dda144067c98c1b2b7d0a20cf48648.jpg\" alt=\"浸润型肺结核\" /></a><span class=\"description\">浸润型肺结核</span></div>\n要指标。肺<a href=\"https://baike.baidu.com/item/%E7%BB%93%E6%A0%B8%E7%97%85\" target=\"_blank\" rel=\"noopener\">结核病</a>患者痰液可呈间歇排菌，故应连续多次查产。X线检查是诊断肺结核的必要手段，对早期诊断、确定病变部位、范围、性质、了解其演变及选择治疗等均具有重要价值。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"6_1\"></a><a class=\"lemma-anchor \" name=\"sub1219395_6_1\"></a><a class=\"lemma-anchor \" name=\"分类法\"></a><a class=\"lemma-anchor \" name=\"6-1\"></a></div>\n<div class=\"para-title level-3\">\n<h3 class=\"title-text\">分类法</h3>\n</div>\n<div class=\"para\">在临床诊断中，我国先用的分类法包括四部分，即肺结核类型、病变范围及空洞部位、痰菌检查、活动性及转归。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"6_2\"></a><a class=\"lemma-anchor \" name=\"sub1219395_6_2\"></a><a class=\"lemma-anchor \" name=\"肺结核分为五型\"></a><a class=\"lemma-anchor \" name=\"6-2\"></a></div>\n<div class=\"para-title level-3\">\n<h3 class=\"title-text\">肺结核分为五型</h3>\n</div>\n<div class=\"para\">I型：<a href=\"https://baike.baidu.com/item/%E5%8E%9F%E5%8F%91%E6%80%A7%E8%82%BA%E7%BB%93%E6%A0%B8\" target=\"_blank\" rel=\"noopener\">原发性肺结核</a>；Ⅱ型：<a href=\"https://baike.baidu.com/item/%E8%A1%80%E8%A1%8C%E6%92%AD%E6%95%A3%E5%9E%8B%E8%82%BA%E7%BB%93%E6%A0%B8\" target=\"_blank\" rel=\"noopener\">血行播散型肺结核</a>；Ⅲ型：浸润型肺结核；Ⅳ<a href=\"https://baike.baidu.com/item/%E6%85%A2%E6%80%A7%E7%BA%A4%E7%BB%B4%E7%A9%BA%E6%B4%9E%E5%9E%8B%E8%82%BA%E7%BB%93%E6%A0%B8\" target=\"_blank\" rel=\"noopener\">慢性纤维空洞型肺结核</a>；Ⅴ型：<a href=\"https://baike.baidu.com/item/%E7%BB%93%E6%A0%B8%E6%80%A7%E8%83%B8%E8%86%9C%E7%82%8E\" target=\"_blank\" rel=\"noopener\">结核性胸膜炎</a>（见本篇第十三章）。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"6_3\"></a><a class=\"lemma-anchor \" name=\"sub1219395_6_3\"></a><a class=\"lemma-anchor \" name=\"病变范围及空洞部位\"></a><a class=\"lemma-anchor \" name=\"6-3\"></a></div>\n<div class=\"para-title level-3\">\n<h3 class=\"title-text\">病变范围及空洞部位</h3>\n</div>\n<div class=\"para\">按右、左侧，分上、中、下肺野记述。右侧病变记在横线以上，左侧病变记录\n<div class=\"lemma-picture text-pic layout-right\"><a class=\"image-link\" title=\"浸润型肺结核\" href=\"https://baike.baidu.com/pic/%E6%B5%B8%E6%B6%A6%E5%9E%8B%E8%82%BA%E7%BB%93%E6%A0%B8/8670438/0/4e0b3ea4236ac8b49052eeaa?fr=lemma&amp;ct=single\" target=\"_blank\" rel=\"noopener\"><img class=\"\" src=\"https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=ce2dc20c78f0f736dcfe4b033a55b382/7af40ad162d9f2d33265d09ca9ec8a136327cc49.jpg\" alt=\"浸润型肺结核\" /></a><span class=\"description\">浸润型肺结核</span></div>\n再生横线以下。一侧无病变者，以&ldquo;（一）&rdquo;表示。以第2和第4前肋下缘内端将两肺分为上、中、下肺叶。有空洞者，在相应肺叶部位加&ldquo;0&rdquo;号。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"6_4\"></a><a class=\"lemma-anchor \" name=\"sub1219395_6_4\"></a><a class=\"lemma-anchor \" name=\"痰结核菌检查\"></a><a class=\"lemma-anchor \" name=\"6-4\"></a></div>\n<div class=\"para-title level-3\">\n<h3 class=\"title-text\">痰结核菌检查</h3>\n</div>\n<div class=\"para\">痰菌<a href=\"https://baike.baidu.com/item/%E9%98%B3%E6%80%A7\" target=\"_blank\" rel=\"noopener\">阳性</a>或阴性，分别以（+）或（－）表示，以&ldquo;涂&rdquo;、&ldquo;集&rdquo;或&ldquo;培&rdquo;分别代表涂片、集菌或培养法。患者无痰或未查痰者，注明&ldquo;无痰&rdquo;或未查。</div>',0,2,NULL,'2019-05-15 21:39:34','admin','2019-05-15 21:39:34',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COMMENT='疾病原因-解决方案关联表';

/*Data for the table `tb_disease_reason_to_solution` */

insert  into `tb_disease_reason_to_solution`(`id`,`disease_reason_id`,`solution_id`) values 
(42,12,8),
(43,13,8),
(44,13,9),
(45,13,10),
(46,14,11),
(47,14,10),
(48,15,10),
(49,15,11),
(50,16,11),
(51,16,9);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='动态表';

/*Data for the table `tb_dynamic` */

insert  into `tb_dynamic`(`dynamic_id`,`title`,`content`,`user_id`,`dynamic_detail_num`,`read_num`,`hidden`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,'hhbvv','vbjjvvf',7,3,0,0,NULL,'7','2019-05-25 12:28:55',NULL,'2019-05-25 14:57:07'),
(2,'bjgfh','gmnv',7,0,0,0,NULL,'7','2019-05-25 12:29:50',NULL,'2019-05-25 12:29:50'),
(3,'hdjjddj','bdhdjddj',7,0,0,0,NULL,'7','2019-05-25 13:30:11',NULL,'2019-05-25 13:30:11');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='动态评论信息表';

/*Data for the table `tb_dynamic_detail_message` */

insert  into `tb_dynamic_detail_message`(`id`,`dynamic_detail_id`,`message`,`parent_id`,`from_user_id`,`to_user_id`,`like_num`,`is_read`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,1,'gffffff',NULL,7,NULL,0,0,NULL,'7','2019-05-26 11:28:38',NULL,'2019-05-26 11:28:38');

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
(1,1,'jdjdjddjj',7,0,0,0,0,0,NULL,'7','2019-05-25 14:28:02',NULL,'2019-05-25 14:28:02'),
(2,1,'mffkfk',7,0,0,0,0,0,NULL,'7','2019-05-25 14:54:22',NULL,'2019-05-25 14:54:22'),
(3,1,'jffgjjfjfjf',7,0,0,0,0,0,NULL,'7','2019-05-25 14:57:07',NULL,'2019-05-25 14:57:07');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='动态明细-文件关联表';

/*Data for the table `tb_dynamic_details_files` */

insert  into `tb_dynamic_details_files`(`id`,`dynamic_detail_id`,`file_id`) values 
(1,1,3),
(2,2,4),
(3,2,5),
(4,3,6),
(5,3,7);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='动态-文件关联表';

/*Data for the table `tb_dynamic_files` */

insert  into `tb_dynamic_files`(`id`,`dynamic_id`,`file_id`) values 
(1,1,1),
(2,3,2);

/*Table structure for table `tb_files` */

DROP TABLE IF EXISTS `tb_files`;

CREATE TABLE `tb_files` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `file_name` varchar(50) DEFAULT NULL COMMENT '文件名称',
  `size` int(11) DEFAULT NULL COMMENT '文件大小',
  `path` varchar(100) DEFAULT NULL COMMENT '文件路径',
  `file_type` varchar(100) DEFAULT NULL COMMENT '文件类型',
  `module` varchar(100) DEFAULT NULL COMMENT '所属模块',
  `oss_url` varchar(5000) DEFAULT NULL COMMENT 'OSS URL',
  `oss_key` varchar(100) DEFAULT NULL COMMENT 'OSS KEY',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `FILE_PATH` (`path`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='文件表';

/*Data for the table `tb_files` */

insert  into `tb_files`(`id`,`file_name`,`size`,`path`,`file_type`,`module`,`oss_url`,`oss_key`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(1,NULL,29169,NULL,'multipart/form-data',NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/7/1558758535126.jpg?Expires=1559852539&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=3AM3TNGsfclrVmeFM83VK04I2ps%3D','7/1558758535126.jpg',NULL,'7','2019-05-25 12:28:55',NULL,'2019-05-25 12:28:55'),
(2,NULL,289664,NULL,'multipart/form-data',NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/7/1558762210735.jpg?Expires=1559856215&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=X3Ibla8VpRYeyADrMQaNC3ljOwg%3D','7/1558762210735.jpg',NULL,'7','2019-05-25 13:30:11',NULL,'2019-05-25 13:30:11'),
(3,NULL,2841,NULL,'multipart/form-data',NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/7/1558765682094.jpg?Expires=1559859686&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=dBRNK6ESON%2FaHEx4aB1wy%2FEIT7A%3D','7/1558765682094.jpg',NULL,'7','2019-05-25 14:28:02',NULL,'2019-05-25 14:28:02'),
(4,NULL,29169,NULL,'multipart/form-data',NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/7/1558767261438.jpg?Expires=1559861266&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=nGbD%2FqY6l597bNwdmvirgfzVhPQ%3D','7/1558767261438.jpg',NULL,'7','2019-05-25 14:54:22',NULL,'2019-05-25 14:54:22'),
(5,NULL,289664,NULL,'multipart/form-data',NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/7/1558767261485.jpg?Expires=1559861266&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=aJKHprFwgdEedDKn6qyP%2FS8xN1U%3D','7/1558767261485.jpg',NULL,'7','2019-05-25 14:54:22',NULL,'2019-05-25 14:54:22'),
(6,NULL,29169,NULL,'multipart/form-data',NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/dynamic/7/1558767426641.jpg?Expires=1559861431&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=RIzE7Qt1TufJuhW2rY%2B%2BHzjDktQ%3D','dynamic/7/1558767426641.jpg',NULL,'7','2019-05-25 14:57:07',NULL,'2019-05-25 14:57:07'),
(7,NULL,289664,NULL,'multipart/form-data',NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/dynamic/7/1558767426673.jpg?Expires=1559861431&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=daJ8GOxQp9a%2FO%2F4yC5FVLHQvCZo%3D','dynamic/7/1558767426673.jpg',NULL,'7','2019-05-25 14:57:07',NULL,'2019-05-25 14:57:07');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='养生表';

/*Data for the table `tb_health` */

insert  into `tb_health`(`health_id`,`number`,`health_name`,`bg_image`,`content`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(4,'001','信息辟谷',167,'<p class=\"p1\">在辟谷日趋火爆的当下，市场有多种多样的辟谷方法，如服气辟谷，服药辟谷，开穴辟谷，电疗辟谷等等。信息辟谷则具有下列显著的特色：</p>\n<p class=\"p1\">一、启动辟谷方法神奇</p>\n<p class=\"p1\"><span class=\"Apple-converted-space\">&nbsp;&nbsp; </span>完全不需借用服气、丹药、针灸等外力，只需老师通过发出声波、眼神等信息，就可打开学员的全身穴窍、经脉，快速、轻松启动学员辟谷机能，达到辟谷状态。</p>\n<p class=\"p2\">&nbsp;</p>\n<p class=\"p1\">二、辟谷过程的感受神奇</p>\n<p class=\"p2\">&nbsp;</p>\n<p class=\"p1\"><span class=\"s1\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> 学员在整个辟谷过程中，不吃任何食物（包括水果，丹药或代餐之类），只喝水，也绝不会感到饥饿，因此根本不需要学员忍受饥饿之苦。不仅如此，在辟谷期间，学员还精力充沛，可以正常工作生活，有精神有力量。</p>\n<p class=\"p2\">&nbsp;</p>\n<p class=\"p1\">三、可以长时间持续辟谷很神奇</p>\n<p class=\"p2\">&nbsp;</p>\n<p class=\"p1\"><span class=\"s1\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>每个人的身体状况和病情不同，其对辟谷时间要求是不一样的。社会上很多辟谷班，通常不允许一次辟谷超过七天，信息辟谷则从不限制学员辟谷时长，学员可根据自已情况自行选择辟谷时间。因此学员们辟谷十几天，几十天，上百天，甚至多年持续辟谷也绝不鲜见，实现一次达到全面深度调理效果。</p>\n<p class=\"p2\">&nbsp;</p>\n<p class=\"p1\">四、现场的调理能力神奇</p>\n<p class=\"p2\">&nbsp;</p>\n<p class=\"p1\"><span class=\"s1\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> 刘海荣老师本身就是一个不可思议的能量体，拥有和自如运用宇宙能量的能力，不仅在辟谷期间会帮助学员持续增加能量，让学员受益终生。甚至能调理一些学员多年不治的病疾，令病症当场康复。几十年来，对无数经现场调理康复的病例，只能用神奇来解释。</p>\n<p class=\"p2\">&nbsp;</p>\n<p class=\"p1\">五、辟谷老师本人的神奇</p>\n<p class=\"p2\">&nbsp;</p>\n<p class=\"p1\"><span class=\"s1\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> 刘海荣作为一个文体工作者，她善于学习中华传统儒、释、道、医等养生精华，融合武术、体操、气功等健身诀窍，自创承古接今&ldquo;信息辟谷养生术&rdquo;。之后，历经36年在海内外持续推广，已帮助50万余人获得健康。她是目前辟谷界无可争议的辟谷大师，华夏养生文化国宝级传承人。</p>',NULL,'Admin','2019-05-15 21:46:18','Admin','2019-05-24 23:48:29'),
(5,'002','笑青青',168,'<div class=\"col-left \">\n<div class=\"box\">\n<div id=\"Article\">\n<div class=\"content_z\">\n<p class=\"p1\">它是一种<span class=\"s1\">&nbsp;</span>&ldquo;非接触、非药物&rdquo;的能量疗愈方法。该疗愈方法是通过二十多年实践研究，并结合道家、佛学、瑜伽、中医、卡巴拉等古老智慧和内在本质，进而被研究及推广的一种用能量疗愈来全面提升身心健康的方法。</p>\n</div>\n</div>\n</div>\n<div class=\"bk20\">&nbsp;</div>\n<div class=\"gg_gg\"><!-- 文章内容页广告位2 --></div>\n</div>',NULL,'Admin','2019-05-15 21:48:19','Admin','2019-05-24 23:49:34'),
(6,'003','乳酸菌',171,'<p class=\"p1\">笑河合超级乳酸菌源起于60年代日本的冲绳，历经20余年的研发，于1984年首次上市，它</p>\n<p class=\"p1\">是全世界第一款死菌技术产品，也是&ldquo;全球单支含量最高及来自于人体的乳酸菌产品&rdquo;（每支</p>\n<p class=\"p1\">含量12000亿）。1984年上市以来获得日本国家颁布的抗癌剂、抗动脉硬化等11项专利；获得美国7项专利；2019年获得中国人民解放军总医院（301）的降血糖专利。</p>\n<p class=\"p1\">笑河合超级乳酸菌能调节肠道微生态，大幅提升免疫力，有效减缓动脉硬化，对癌症、脑梗、心脏病、糖尿病、高血压等疾病也有显著改善。</p>',NULL,'Admin','2019-05-24 23:50:24',NULL,'2019-05-24 23:50:24');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='养生成果表';

/*Data for the table `tb_health_result` */

insert  into `tb_health_result`(`id`,`name`,`number`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(4,'效果良好','001',NULL,'admin','2019-05-15 21:48:44',NULL,'2019-05-15 21:48:44'),
(5,'无效','002',NULL,'admin','2019-05-15 21:48:51',NULL,'2019-05-15 21:48:51'),
(6,'效果一般','003',NULL,'admin','2019-05-15 21:49:02',NULL,'2019-05-15 21:49:02');

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='养生-解决方案 关联表';

/*Data for the table `tb_health_to_solution` */

insert  into `tb_health_to_solution`(`id`,`health_id`,`solution_id`) values 
(22,4,12),
(23,4,11),
(24,5,12),
(25,5,11),
(26,5,10);

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='养生方式表';

/*Data for the table `tb_health_way` */

insert  into `tb_health_way`(`health_way_id`,`way_name`,`bg_image`,`number`,`content`,`music_url`,`type`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(5,'大礼拜',153,'001','<div class=\"para-title level-2\">\n<h2 class=\"title-text\">形式</h2>\n</div>\n<div class=\"para\">&ldquo;大礼拜通常与皈依之念诵同时并行。但格鲁派也有以<a href=\"https://baike.baidu.com/item/%E4%B8%89%E5%8D%81%E4%BA%94%E4%BD%9B\" target=\"_blank\" rel=\"noopener\">三十五佛</a>之礼忏包含大礼拜，以代替金刚萨埵法者。若觉同时计大礼拜与皈依次数不易，则宜只计礼拜次数。皈依另外加念，再计其数。</div>\n<div class=\"para\">大礼拜宜置长板，长度为行者身长加手长。近佛之一头垫高四吋。近佛之前半可贴壁纸，使之光滑易于推动。行者立足及着膝处则不可光滑，以致无法着力。可戴<a href=\"https://baike.baidu.com/item/%E6%A3%89%E6%89%8B%E5%A5%97\" target=\"_blank\" rel=\"noopener\">棉手套</a>以代护手之小垫。初修可戴护肘及护膝物。满二万拜后渐觉身轻如燕，届时可取下护肘及护膝。现代之计数器用以计此数，较用念珠方便。可将计数器置于近着膝处，于拜完起身时顺手按一下。</div>\n<div class=\"para\">行礼时行者站于板之低端，一边持大礼拜咒，一边两手各向外画弧形上举而合于额，（上举时念&lsquo;嗡&rsquo;，手至额合掌，念&lsquo;南无曼祖洗尔也&rsquo;。）合掌由额降到喉，（合掌到喉时念&lsquo;南无苏洗尔也&rsquo;。）再降到心，（到心时念&lsquo;南无乌打嘛洗尔也&rsquo;。）然后边念&lsquo;娑哈&rsquo;边俯身，两手分开如肩宽而着地，向前推出。全身伸直着板面，两手在前合掌一拜，立即起身。如此持咒一遍之大礼一拜，要圆满十万遍。故大礼拜之加行中又包含了十万遍的大礼拜咒。若皈依与大礼拜合修，即于念&lsquo;娑哈&rsquo;之后，开始念皈依，直到起身。</div>\n<div class=\"para\">大礼拜咒︰&lsquo;嗡，南无曼祖洗尔也，南无苏洗尔也，南无乌打嘛洗尔也，娑哈。&rsquo;&rdquo;</div>','http://m10.music.126.net/20190515220148/4aaef4097e612886b026a0dc4c0dae14/ymusic/642b/89e4/67bf/4383c261c0aefb9fd9280d73741ded0e.mp3',1,NULL,'admin','2019-05-15 21:42:21','admin','2019-05-15 21:42:28'),
(6,'双心静坐',154,'002','<div class=\"para-title level-2\">\n<h2 class=\"title-text\">基本解释</h2>\n<a class=\"edit-icon j-edit-link\" data-edit-dl=\"1\">编辑</a></div>\n<div class=\"para\">一、排除思虑，闭目安坐，是<a href=\"https://baike.baidu.com/item/%E6%B0%94%E5%8A%9F%E7%96%97%E6%B3%95\" target=\"_blank\" rel=\"noopener\">气功疗法</a>采用的一种方式。</div>\n<div class=\"para\">二、为达到某种要求或表示抗议安静的坐着。<sup class=\"sup--normal\" data-sup=\"1\" data-ctrmap=\":1,\">&nbsp;[1]</sup><a class=\"sup-anchor\" name=\"ref_[1]_811566\"></a>&nbsp;</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"2\"></a><a class=\"lemma-anchor \" name=\"sub811566_2\"></a><a class=\"lemma-anchor \" name=\"通经络法\"></a></div>\n<div class=\"para-title level-2\">\n<h2 class=\"title-text\">通经络法</h2>\n<a class=\"edit-icon j-edit-link\" data-edit-dl=\"2\">编辑</a></div>\n<div class=\"para\">经络是很神奇的东西，习武之人无不重视经络。我们常看到武侠小说里写要练内功必须打通全身经脉，使真气运行。</div>\n<div class=\"para\">经络分为经脉和络脉。人身上有十二条经脉和奇经八脉贯通全身上下，更有无数络脉或附于五脏六腑，或浮于皮肤浅表。经络使人体内外保持协调统一，构成一个有机的整体。经络是气血运行的通道，气血通过经络运输濡养全身。打个比方，人体就像一个大楼，经络好比大楼内部交错纵横的电线，气血好比是电流。电路贯通流畅，大楼就能保持灯火通明；电线有阻塞不通，那么电流不能运行，大楼就漆黑一片，死气沉沉。所以说死人是没有经络的，反之经络一旦长时间不通以致枯萎，人也就活不长了。</div>\n<div class=\"para\">许多疾病都是由经络的不通造成的，什么便秘、脑血栓、心梗、胆结石、肿瘤等等，光看病名就知道了。其实所有的疾病都和经络不通都有着一定联系，经络一旦畅通无阻，病邪根本滞留不下来。</div>\n<div class=\"para\">然而在现代医学中，经络仍是比较虚玄的东西，虽然敏感的人在针灸时会感应得到，但说到经络的实质，人们总是觉得既看不见又摸不着，现代先进的医学仪器也透视不出来。从古代经脉图谱中我们了解到各条经脉的详细走向、交结，那么古人又是怎样看得一清二楚呢?其实方法就两个字：&ldquo;内视。&rdquo;</div>\n<div class=\"para\">经络本就是体内的景象，只有内视才能看到。现代社会高速发展，快节奏的生活，五花八门的仪器让我们逐渐失去了一些本来应该具有的能力。古代社会接近天然，古人显然比我们要聪明得多，身体灵敏度也高，较容易向内觉察到经络的运行。</div>\n<div class=\"para\">内视必须在入静的状态下才可以获得，通过静坐调息，我们可以感知气机在经脉中的循环。静坐到了一定程度，下腹部的丹田位置就会有一股热气循着经脉的走向自然流注循环。因为练习的火候到了，丹田里产生的真气充满流溢，开始向全身运行，这就是打通经脉的过程。此时，你的意识也要随着气机流转，做到&ldquo;神与脉合&rdquo;。如果练习的火候没到，丹田内的真气就仍在酝酿之中，切不可着急。</div>\n<div class=\"para\">静坐是打通经络的最好方法，完全靠精神内守使气机自然而然地融会贯通，一股冲和之气运行经络之间，化解郁积之处。这样地去通经络将是长久有效的，比起推拿、针灸等外界方法不知要高明多少倍。<sup class=\"sup--normal\" data-sup=\"2\" data-ctrmap=\":2,\">&nbsp;[2]</sup><a class=\"sup-anchor\" name=\"ref_[2]_811566\"></a>&nbsp;</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"3\"></a><a class=\"lemma-anchor \" name=\"sub811566_3\"></a><a class=\"lemma-anchor \" name=\"生理的关系\"></a></div>\n<div class=\"para-title level-2\">\n<h2 class=\"title-text\">生理的关系</h2>\n<a class=\"edit-icon j-edit-link\" data-edit-dl=\"3\">编辑</a></div>\n<div class=\"para\">人体的构造，是非常复杂精妙的，它能够成为一个完整的个体，主要是依靠神经系统&mdash;&mdash;尤其是大脑的作用，所以苏联生理学家N．兀．巴甫洛夫(1849&mdash;&mdash;1936)特别强调大脑对于整个生理活动的重要性。同时根据生理学的观点来说，人体机构的最大作用，首先在于生活的维持，一方面摄取体外的养料和氧气供给于体内各器官，一方面使体内的二氧化碳和其他废料排泄于体外。这种物质交换的现象，就是所谓&ldquo;新陈代谢&rdquo;。新陈代谢的作用，是没有一息停止的，主要是依靠心脏、脉管、淋巴管等循环器官，来循环不停地运行血液到全身。心脏包括四个腔，即左心房，左心室、右心房、右心室，是推动血液的器官，脉管有动脉和静脉；淋巴管遍布全身，与静脉并行，一面吸收营养物，输送到动脉管，一面摄取废物，送到静脉管、血液的循环，与呼吸有密切的关系，呼出碳酸气，吸进氧气，使静脉中紫血，变为红血输送到动脉，经体循环一周，约需时二十四秒，一昼夜共计三千六百周，我们呼吸的次数，一昼夜才二万多次，所吸入的氧气，共计三百八十多立方尺，这种可观的工作，我们平时自己并不知觉。假使我们能呼吸正常，血液运行没有阻滞，身体便能健康，一有阻滞，全身各器管便得病。<sup class=\"sup--normal\" data-sup=\"3\" data-ctrmap=\":3,\">&nbsp;[3]</sup><a class=\"sup-anchor\" name=\"ref_[3]_811566\"></a>&nbsp;</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"4\"></a><a class=\"lemma-anchor \" name=\"sub811566_4\"></a><a class=\"lemma-anchor \" name=\"心理的关系\"></a></div>\n<div class=\"para-title level-2\">\n<h2 class=\"title-text\">心理的关系</h2>\n<a class=\"edit-icon j-edit-link\" data-edit-dl=\"4\">编辑</a></div>\n<div class=\"para\"><br />　　人身有生理的和心理的两方面，我们从事修养，固然对身心两方面，都不可偏废，而心理每每更能影响生理，譬如：内心有所惭愧，顿觉面红耳赤；内心有所忧煎，不觉发白貌悴，这是心理的影响于形体；愉快时五官的见闻等感觉优美，而悲哀时，便完全相反，这是心理影响于五官；兴奋时食欲便能增进，而郁闷时便减少，这是心理影响于肠胃；忿怒、嫉妒等不正常的感情起时，能使血液及各部组织中，发生毒素，这是心理影响于血液，可见心理的力量足以支配肉体。从解剖生理的观点来说，中枢神经&mdash;&mdash;脑和脊髓，也是联系、管理和调整神经活动的总机构。巴甫洛夫氏在创立高级神经活动方式是&ldquo;条件反射&rdquo;的学说中，在做实验时，曾使狗一听到铃声，便分泌唾涎，那是经过大脑皮层的活动，转移到延髓部份唾液分泌中心，再到分泌腺，这也是心理影响生理的一个说明。此外，我们发觉往往有些体育家，锻炼筋肉极其强固，但一旦发生不测的疾病便无法抵御，甚至有因此成为废人的，而一般禅师或哲学家，他们往往能借锻炼心意的修养作用，来驱除病魔，或虽体质孱弱，但由于心方强毅，却能获享高寿，更可见心理潜力的不可思议了。<br />　　静坐不但在生理方面可以使血液运行优良．就是在心理方面也能使全身精神归于统一集中，而促使心理现象的健康发展。心理学家捷普洛夫也说过，抱着一种平静的态度，是与注意的分散作斗争的唯&mdash;方法。同时心理既安宁而正常，思想也清明而愉快，自然又能促使体气和平，却病延年。<br />　　此外，一般心理学说所研究到的，都不外乎心的现象、心的作用和心的变化等等，而对心的本体、心的来源以及安心制心的具体方法，却终不能有确当的说明。假使通过静坐，使意识的活动，逐步停止以后，这时心理的静态，清楚地现前，必将有助于心理学说的创造性的发明!<sup class=\"sup--normal\" data-sup=\"3\" data-ctrmap=\":3,\">&nbsp;[3]</sup><a class=\"sup-anchor\" name=\"ref_[3]_811566\"></a>&nbsp;</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"5\"></a><a class=\"lemma-anchor \" name=\"sub811566_5\"></a><a class=\"lemma-anchor \" name=\"静坐的方法\"></a></div>\n<div class=\"para-title level-2\">&nbsp;</div>','https://m801.music.126.net/20190515220808/cf55dd05f0dfba1052a7e6d7ee457237/jdyyaac/045f/0109/045e/359d39a57d6c6dab1f3b340d32dd0ab8.m4a',3,NULL,'admin','2019-05-15 21:44:40',NULL,'2019-05-15 21:44:40'),
(7,'冥想',155,'003','<div class=\"para-title level-2\">\n<h2 class=\"title-text\">三种状态</h2>\n<a class=\"edit-icon j-edit-link\" data-edit-dl=\"1\">编辑</a></div>\n<div class=\"para\">\n<div class=\"lemma-picture text-pic layout-right\"><a class=\"image-link\" title=\"冥想\" href=\"https://baike.baidu.com/pic/%E5%86%A5%E6%83%B3/35985/0/8326cffc1e178a82818df7b3fb03738da977e874?fr=lemma&amp;ct=single\" target=\"_blank\" rel=\"noopener\"><img class=\"\" src=\"https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=bf1819e1701ed21b7dc929e79d6fddae/8326cffc1e178a82818df7b3fb03738da977e874.jpg\" alt=\"冥想\" /></a><span class=\"description\">冥想</span></div>\n根据瑜伽师们的说法，自我，即人，由于受到自然界三种状态的蒙蔽，所以是受控制、受支配的。如果一个人处于愚昧无知状态的影响支配下，就会希望获得某一特定类型的感官享受，例如，如果一个人是处于愚昧无知状态的影响支配下，他可能想<a href=\"https://baike.baidu.com/item/%E7%9D%A1%E7%9C%A0/881242\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"881242\">睡眠</a>或被<a href=\"https://baike.baidu.com/item/%E9%BA%BB%E9%86%89/1768770\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"1768770\">麻醉</a>；如果一个人处于激情状态的影响支配下，他可能要作性的享乐；如果一个人是处于善良状态的影响支配下，他可能要享受在公园或农村度过和平宁静的一天。因此，所有这些情况，是人的欲念的性质由自然界中哪一种影响支配着他的状态来决定的。</div>\n<div class=\"para\">按瑜伽师的看法，只要一个人仍然是受到物质自然界的三种状态即愚昧无知、激情和善良的支配，他就不是自由的&mdash;&mdash;还没有从物质欲念的羁绊中解脱出来。要从物质欲念中解脱出来，他就必须超脱自然界这三种状态的影响。每一个人都被愚昧无知、激情和善良所蒙蔽着。可是，在任何特定时刻这三者中总有一种占着主要地位。</div>\n<div class=\"para\">愚昧无知是最糟糕的状态，这种状态的结果是疯狂、幻觉妄想、怠惰等。</div>\n<div class=\"para\">激情状态优于愚昧无知状态，但它意味着重大执著、不可控制的欲念、追求和紧张的努力等等特征。</div>\n<div class=\"para\">而善良状态比其他状态更纯洁，光辉四射。处于这种状态的人们培育、发展智慧，但是他们却受到幸福这一概念的制约。</div>\n<div class=\"para\">处于善良状态的人仍然只是对自己的幸福感兴趣。换言之，虽然他的欲念，要比处于愚昧无知或激情状态的人的欲念更纯洁些、更微妙些，但他仍然是以私己为中心的。当愚昧无知状态或激情状态开始影响他的身心，从而剥夺了他从善良状态所获得的幸福时，这样的人就会感到非常难过和沮丧。他还没有超脱所有这三者的影响&mdash;&mdash;对于这三种影响的自然活动，他不能够保持作为一个不受影响的、超脱的见证人的地位。他还没有达到对整体作出爱心服务的那个高度上&mdash;&mdash;因此，他并没有真正获得解脱。只有当这个被禁锢在肉体中的生灵能够超越这三种状态时，他才能从出生、死亡、衰老和此三者的痛苦中解脱，而在今生也能享受甘露。</div>\n<div class=\"para\">只有对自身幸福不感兴趣的人才能够真正幸福，这个见解，在愚昧无知的人看来，似乎是怪论，令人糊涂&mdash;&mdash;但是，对于有智慧的人来说，则是合乎逻辑和容易理解的。　实现瑜伽至善境界的人就是这样的人：当愚昧无知、激情或善良状态在他的身心发挥着作用时，他意识到这种情况、但却体会到自己超脱这种影响或不为所动。这样的一个人既不对这些影响唯命是从（即不对自己的身心唯命是从）也不因为这些影响正在发挥作用而感到憎恶。他是作为一个超脱的见证人来体验这一切的。如果说，他既不把注意力放在这些影响的命令要求上，也不对这些影响唯命是从，那么，他又注意什么，听命于什么事物呢？回答是他把注意力放在自己对整体的服务上，因为他受到精神之爱的力量的鼓舞或推动，而这种力量是超越愚昧无知、激情或善良等状态而与它们迥然不同的。这样一个解脱了的瑜伽士可以比喻为一个专心致志地做自己的工作而对天气不太在意的人。可能是阳光普照的晴天（这可以比拟为善良状态〕，或者云霾密布的阴天（这可以比拟为激情状态），或者是雨天（这可以比拟为愚昧无知状态）。虽然他知道：&ldquo;啊，今天有太阳&rdquo;或者&ldquo;啊，今天下雨啊！&rdquo;但他仍然埋头做自己的工作。</div>\n<div class=\"para\">据瑜伽师的看法，从物质自然界三种状态的影响下获得解脱是一个循序渐进的过程。例如，如果一个原先主要地是处于愚昧无知状态的人从事瑜伽练习和采取瑜伽生活方式，他就逐渐愈来愈多地处于激情状态下，还多少处于善良状态下。然后，随着他继续练习下去，他就会逐渐地、愈来愈多地处于善良状态的影响下，而愈少处于激情状态的影响下，甚至更少处于愚昧无知状态的影响下了。然后，他还逐步超越善良状态的影晌。这样一种状态就叫做纯化了的善良状态，或超然状态。或入定（Samadhi）。</div>\n<div class=\"para\">因此，一切习瑜伽者的当务之急是力图从愚昧无知和激情状态的影响下解脱出来。实现这一点的办法是愈来愈多地转而处于善良状态的影响之下。循此以往，习瑜伽者就逐步连善良状态的影响也超越了。</div>\n<div class=\"para\">一个人要超越所有这三种状态，就必须首先基本上坚守住善良状态，而不是依附愚昧无知或激情状态，原因在于善良状态正是摆脱这三种状态的出发点。在获得解脱之后，无论愚昧无知、激情或善良三者哪一种活跃起来，人都始终不为所动。</div>\n<div class=\"para\">因此，所有瑜伽冥想练习的目的都是要使人从物质自然界的这三种状态的统治下解脱出来&mdash;&mdash;先从解脱愚昧无知和激情状态开始，最后，连善良状态也解脱了。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"2\"></a><a class=\"lemma-anchor \" name=\"sub8380961_2\"></a><a class=\"lemma-anchor \" name=\"瑜伽冥想\"></a></div>\n<div class=\"para-title level-2\">\n<h2 class=\"title-text\">瑜伽冥想</h2>\n<a class=\"edit-icon j-edit-link\" data-edit-dl=\"2\">编辑</a></div>\n<div class=\"para\">在所有的<a href=\"https://baike.baidu.com/item/%E7%91%9C%E4%BC%BD%E5%86%A5%E6%83%B3\" target=\"_blank\" rel=\"noopener\">瑜伽冥想</a>体系中，没有哪一种比得上<a href=\"https://baike.baidu.com/item/%E7%91%9C%E4%BC%BD%E8%AF%AD%E9%9F%B3%E5%86%A5%E6%83%B3\" target=\"_blank\" rel=\"noopener\">瑜伽语音冥想</a>的功效那么直接，久经时间考验或广为人们使用。如前所述，瑜伽语音冥想可以和提升生命之气的功法一起配合着练中，也可以终身单项地练习。</div>\n<div class=\"para\">瑜伽语音冥想又称曼特拉（ Mantra）冥想。<a href=\"https://baike.baidu.com/item/%E6%A2%B5%E8%AF%AD\" target=\"_blank\" rel=\"noopener\">梵语</a>词&ldquo;曼特拉&rdquo;可以分为两部分，即&ldquo;曼&rdquo;（ man）和&ldquo;特拉&rdquo;（ tra）。&ldquo;曼&rdquo;的意思是&ldquo;心灵&rdquo;。&ldquo;特拉&rdquo;的意思是&ldquo;引开去&rdquo;。因此，&ldquo;曼特拉&rdquo;的意思是能把人的心灵从其种种世俗的思想、忧虑、欲念、精神负担等等引离开去的一组特殊语音。一个人只要把注意力集中在他的<a href=\"https://baike.baidu.com/item/%E7%91%9C%E4%BC%BD%E8%AF%AD%E9%9F%B3\" target=\"_blank\" rel=\"noopener\">瑜伽语音</a>上，就能逐渐超越愚昧无知和激情等品质，而处身在善良品质的高度上。从这一步，<a href=\"https://baike.baidu.com/item/%E7%91%9C%E4%BC%BD%E5%86%A5%E6%83%B3\" target=\"_blank\" rel=\"noopener\">瑜伽冥想</a>更往深处发展，逐渐演变为完美的禅，而最终地进入入定状态。</div>\n<div class=\"para\">试图凭借注意力使心灵保持专注在某一对象事物上，是违反心灵的能力的。这样做是困难而徒然的。这实际上也是违反瑜伽冥想术的原则的。在瑜伽冥想术中，人要轻柔地设法把他的心灵集中在某一对象事物上，如果心灵要离开这事物，他就设法逮住它，把它带回来。但这是做得极为柔和的。</div>\n<div class=\"para\">瑜伽语音冥想练习极为简便易行。没有什么硬性的严格的规定。有时候是心与口同时反复诵念，有时候只是默念而已。出声念诵时，有时是低声悄语似地反复念，有时用普通语音响度念，有时又是用有节奏的歌唱方式来诵念。有时诵念与呼吸保持同步节奏，有时又不必如此。有时是坐着念，有时站着念，有时走着念，等等。练习者双眼有时是闭合的，有时是部分地闭合的，有时是完全张开的。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"3\"></a><a class=\"lemma-anchor \" name=\"sub8380961_3\"></a><a class=\"lemma-anchor \" name=\"误区\"></a></div>\n<div class=\"para-title level-2\">\n<h2 class=\"title-text\">误区</h2>\n<a class=\"edit-icon j-edit-link\" data-edit-dl=\"3\">编辑</a></div>\n<div class=\"para\"><strong>冥想与调息入静技法可不必同时练习</strong></div>\n<div class=\"para\">冥想前的入静技法，如调息术、收束法、契合法，以及一些生命之气的培养和操控技法。那么，是不是在练瑜伽语音冥想时，得搭配这些技法一块儿练，才能获取最佳的效果呢?</div>\n<div class=\"para\">其实，你不需要练习任何冥想前预备技法，只需练习瑜伽语音冥想，就可以达到瑜伽冥想的最高境界。<br />　　和瑜伽姿势一样，收束法、契合法和调息术(呼吸和生命之气的培养和操控技法）都是为瑜伽语音冥想服务的。如果你觉得任何一种技法，能直接或间接地帮你将心思投人瑜伽语音冥想中，你不妨修炼这些技法。但是如果你不需要靠这些技法就能达到心思专一地沉浸在瑜伽语音冥想上，就别花费宝贵的时间了。</div>\n<div class=\"para\">举例来说，某人练瑜伽姿势可能是因为它有保健和强身的作用，并能释放浅层的紧张与压力，这样，他在做瑜伽语音冥想时，轻易即能达到专心一意的状态。这就是说，他的瑜伽姿势是为瑜伽语音冥想服务的。同样地，一些人练收束法、契合法和调息术，或其他能培养或操控生命之气的技法，是因为感到这些练习可以直接或间接地为瑜伽语音冥想修炼提供帮助。</div>\n<div class=\"para\">瑜伽语音冥想并不需要诸如生命之气的培养和操控等技法的辅助，相反，这些技法需要瑜伽语音冥想的协助，才能实现真正的、深度的精神价值；否则，它们的价值就只能局限在身体和心理的层面了。</div>\n<div class=\"para\">如果你已经习惯于练习冥想前的预备技法，那你可以伴随着瑜伽语音冥想继续练这些技法。但如果你还不熟悉这些入静技法，以冥想的实质与调息入静的主次关系来看，就不必要为此而花费宝贵的时间(但若是有具体的疾病或不适，学练这些冥想前预备技法，可能会获得一定的益处)。</div>\n<div class=\"para\"><strong>瑜伽冥想的最终目的不是让心思空无一物或者达到&ldquo;无我&rdquo;的境界</strong></div>\n<div class=\"para\">而是让心思感应到具有精神性的超然语音，从而达到净化意识的效果。</div>\n<div class=\"para\">一般来说，让思想做到&ldquo;空静空无&rdquo;近似徒劳无功，这种尝试只会带来挫折感。哪怕就在&ldquo;什么念头也没有&rdquo;这个状态出现的那一刻，你也会想：&ldquo;啊，我差不多做到了！我几乎什么念头都没有！&rdquo;但这么一想，不是就有新的念头进入思维了吗？！是你正在想&ldquo;自己什么也没想&rdquo;。根据瑜伽科学，&ldquo;无我&rdquo;的境界也是不可能做到的。按照瑜伽的说法，涅槃并不是终止存在的意思，而是终结无知又痛苦的无意义的存在。真正完美的涅槃就是体悟充满精神之爱、内在智慧和目标的存在。没有任何一件事对我们来说，比自己的存在更真实；也没有任何一件事，比能意识到自己的存在更自然。</div>\n<div class=\"para\">因此，认为瑜伽冥想的目的是叫我们意识不到自己的存在或终止存在，这实质是很难的，事实上是不可能的。任何企图毁灭存在的做法，实际上只是无望地挣扎和浪费时间，根本无法解决痛苦。解决痛苦的办法不是试图终止或忘掉自我的存在，而是让我们的存在更有意义，充满精神之爱及智慧，最终获得美满的生活。</div>\n<div class=\"para\">要是人缺了精神之爱及智慧，就自然难以认识到存在的目的，就会一直处在不快乐的状态中。不过，通过瑜伽冥想的练习，我们认识到存在的意义。因此，我们追求的不再是结束或忘却自我，而是明白人生有更长远的目标，并快乐地生活。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"4\"></a><a class=\"lemma-anchor \" name=\"sub8380961_4\"></a><a class=\"lemma-anchor \" name=\"冥想训练\"></a></div>\n<div class=\"para-title level-2\">\n<h2 class=\"title-text\">冥想训练</h2>\n<a class=\"edit-icon j-edit-link\" data-edit-dl=\"4\">编辑</a></div>\n<div class=\"para\">1、随息法：意念呼吸自然出入，心息相依，意气相随，不加干涉，叫随息。</div>\n<div class=\"para\">2、数息法：默念呼吸次数，从一到十到百，实者数&ldquo;呼&rdquo;，虚者数&ldquo;吸&rdquo;。</div>\n<div class=\"para\">3、听息法：两耳静听自己的呼吸声，排除杂念。</div>\n<div class=\"para\">4、观息法：如观者一样，去观察，体会自己的呼吸。</div>\n<div class=\"para\">5、止息法：通过以上任何一种方法的练习，久炼纯熟，形成一种柔、缓、细、长的呼吸。呼吸细若游丝，若有若无。称止息。也叫胎息。</div>\n<div class=\"para\">6、禅语入定法：（默念数遍）体会联想：&ldquo;独坐小溪任水流&rdquo;的意境。</div>\n<div class=\"para\">7、松静入定法：吸气时默念&ldquo;静&rdquo;字，呼气时默念&ldquo;松&rdquo;字。</div>\n<div class=\"para\">8、观心自静法：用自己的心去观看、体察、分析自己的思绪杂念，任杂念思绪流淌，不加干涉，久则自归于静。</div>','https://m801.music.126.net/20190515220808/cf55dd05f0dfba1052a7e6d7ee457237/jdyyaac/045f/0109/045e/359d39a57d6c6dab1f3b340d32dd0ab8.m4a',5,NULL,'admin','2019-05-15 21:45:34',NULL,'2019-05-15 21:45:34');

/*Table structure for table `tb_home_banner` */

DROP TABLE IF EXISTS `tb_home_banner`;

CREATE TABLE `tb_home_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `title` varchar(30) DEFAULT NULL COMMENT '标题',
  `link` varchar(100) DEFAULT NULL COMMENT '连接',
  `image_id` int(11) DEFAULT NULL COMMENT '图片ID',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `image_id` (`image_id`),
  CONSTRAINT `tb_home_banner_ibfk_1` FOREIGN KEY (`image_id`) REFERENCES `tb_images` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='主页Banner图表';

/*Data for the table `tb_home_banner` */

insert  into `tb_home_banner`(`id`,`title`,`link`,`image_id`,`sort`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(21,'轮播图1','https://www.baidu.com/',163,1,NULL,'Admin','2019-05-15 20:42:56','Admin','2019-05-24 23:36:45'),
(22,'轮播图2','https://www.youku.com/',164,2,NULL,'Admin','2019-05-15 20:44:29','Admin','2019-05-24 23:36:54'),
(23,'轮播图3','https://www.csdn.net/',165,3,NULL,'Admin','2019-05-15 20:45:34','Admin','2019-05-24 23:37:03'),
(24,'轮播图4','https://www.sina.com.cn/',166,4,NULL,'Admin','2019-05-15 20:45:59','Admin','2019-05-24 23:37:11');

/*Table structure for table `tb_images` */

DROP TABLE IF EXISTS `tb_images`;

CREATE TABLE `tb_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `file_name` varchar(50) DEFAULT NULL COMMENT '图片名称',
  `size` int(11) DEFAULT NULL COMMENT '图片大小',
  `path` varchar(100) DEFAULT NULL COMMENT '图片路径',
  `oss_url` varchar(5000) DEFAULT NULL COMMENT 'OSS URL',
  `oss_key` varchar(100) DEFAULT NULL COMMENT 'OSS KEY',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IMAGE_PATH` (`path`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8 COMMENT='图片表';

/*Data for the table `tb_images` */

insert  into `tb_images`(`id`,`file_name`,`size`,`path`,`oss_url`,`oss_key`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(120,'timg.jpg',93542,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1557924548313.jpg?Expires=1559018553&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=iO8iKSh5LauarchZbt8B540lq94%3D','course_image/2_system/1557924548313.jpg',NULL,'admin','2019-05-15 20:49:08',NULL,'2019-05-15 20:49:08'),
(121,'timg.jpg',93542,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1557924553016.jpg?Expires=1559018557&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=Cp2zpw%2B7Pdty1h1vTLA2mtKEBO0%3D','course_image/2_system/1557924553016.jpg',NULL,'admin','2019-05-15 20:49:13',NULL,'2019-05-15 20:49:13'),
(122,'timg01.jpg',61240,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1557924718735.jpg?Expires=1559018723&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=YlxJuxDL0%2FtgWkj5nAMB%2FdFeaxw%3D','course_image/2_system/1557924718735.jpg',NULL,'admin','2019-05-15 20:51:59',NULL,'2019-05-15 20:51:59'),
(123,'image01.jpg',327983,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1557924724798.jpg?Expires=1559018729&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=1%2FHoNMqg5AMj14MKhQZ%2BpKtFIo0%3D','course_image/2_system/1557924724798.jpg',NULL,'admin','2019-05-15 20:52:05',NULL,'2019-05-15 20:52:05'),
(124,'2019021806305142.jpg',90345,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1557924855860.jpg?Expires=1559018860&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=85Wuk3U57CymfU%2FRs7UvCFash28%3D','course_image/2_system/1557924855860.jpg',NULL,'admin','2019-05-15 20:54:16',NULL,'2019-05-15 20:54:16'),
(125,'2019021806305142.jpg',90345,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1557924857579.jpg?Expires=1559018862&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=MxpVME%2FBoHP8jOOzVkHwQyPGQbk%3D','course_image/2_system/1557924857579.jpg',NULL,'admin','2019-05-15 20:54:18',NULL,'2019-05-15 20:54:18'),
(126,'2018112903135314.jpg',127455,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1557924937657.jpg?Expires=1559018942&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=uRFBkXwYT0%2FV6egPcO50fmLu3ro%3D','course_image/2_system/1557924937657.jpg',NULL,'admin','2019-05-15 20:55:38',NULL,'2019-05-15 20:55:38'),
(127,'2019021806590344.jpg',110211,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1557924939110.jpg?Expires=1559018943&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=%2FXgu3xKlkbva48ch%2FWPIurVs2M0%3D','course_image/2_system/1557924939110.jpg',NULL,'admin','2019-05-15 20:55:39',NULL,'2019-05-15 20:55:39'),
(128,'2019021806573520.jpg',98234,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1557924940844.jpg?Expires=1559018945&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=fxUqLbHbRJ%2BvIReqsjVB0cy1yfM%3D','course_image/2_system/1557924940844.jpg',NULL,'admin','2019-05-15 20:55:41',NULL,'2019-05-15 20:55:41'),
(129,'2019021806590344.jpg',110211,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1557925096798.jpg?Expires=1559019101&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=XsOaxMLbCKXoN%2BE1KNv%2Bp5lAYw0%3D','course_image/2_system/1557925096798.jpg',NULL,'admin','2019-05-15 20:58:17',NULL,'2019-05-15 20:58:17'),
(130,'2019021806573520.jpg',98234,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1557925098470.jpg?Expires=1559019103&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=T3oVbWzZsFydPEl1WF3SwY0oPHY%3D','course_image/2_system/1557925098470.jpg',NULL,'admin','2019-05-15 20:58:19',NULL,'2019-05-15 20:58:19'),
(131,'2019021806305142.jpg',90345,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1557925100345.jpg?Expires=1559019105&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=cGkpCoGEP6IUun3MGj4f1Eb1noc%3D','course_image/2_system/1557925100345.jpg',NULL,'admin','2019-05-15 20:58:20',NULL,'2019-05-15 20:58:20'),
(132,'肺癌.jpg',5588,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/disease/2_system/1557926204454.jpg?Expires=1559020209&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=mOMQNU%2BERjaYfXOt%2BnYIQELzUVk%3D','disease/2_system/1557926204454.jpg',NULL,'admin','2019-05-15 21:16:45',NULL,'2019-05-15 21:16:45'),
(133,'肺癌2.jpg',33739,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/disease/2_system/1557926245501.jpg?Expires=1559020250&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=2SapyhFGhC3Z3DEbP2DsuVM6fu8%3D','disease/2_system/1557926245501.jpg',NULL,'admin','2019-05-15 21:17:26',NULL,'2019-05-15 21:17:26'),
(134,'肿瘤.jfif',3549,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/disease/2_system/1557926383548.jfif?Expires=1559020388&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=LuDEgCNitWUtatmQtkMTbHLi3N0%3D','disease/2_system/1557926383548.jfif',NULL,'admin','2019-05-15 21:19:44',NULL,'2019-05-15 21:19:44'),
(135,'肿瘤.jfif',3549,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/disease/2_system/1557926385360.jfif?Expires=1559020390&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=bAdO4KNWh9R3oJmQONTt8G7e5T8%3D','disease/2_system/1557926385360.jfif',NULL,'admin','2019-05-15 21:19:45',NULL,'2019-05-15 21:19:45'),
(136,'002.jfif',3183,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/disease/2_system/1557926486938.jfif?Expires=1559020491&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=ipWyLTS%2FmQE6xW1raBr8TWtHHcI%3D','disease/2_system/1557926486938.jfif',NULL,'admin','2019-05-15 21:21:27',NULL,'2019-05-15 21:21:27'),
(137,'002.jfif',3183,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/disease/2_system/1557926488548.jfif?Expires=1559020493&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=ykIs5uu6py6v3h0Sw%2BtE1lHQRJg%3D','disease/2_system/1557926488548.jfif',NULL,'admin','2019-05-15 21:21:29',NULL,'2019-05-15 21:21:29'),
(138,'960a304e251f95ca7d62ab09c9177f3e660952fd.jpg',9969,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/disease/2_system/1557926562407.jpg?Expires=1559020567&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=RjyvHKOA4E0%2FwdUMf2ljXufdpp0%3D','disease/2_system/1557926562407.jpg',NULL,'admin','2019-05-15 21:22:42',NULL,'2019-05-15 21:22:42'),
(139,'960a304e251f95ca7d62ab09c9177f3e660952fd.jpg',9969,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/disease/2_system/1557926564141.jpg?Expires=1559020568&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=G7R1vBpFJjlc3uAzZgUl0FFBXlM%3D','disease/2_system/1557926564141.jpg',NULL,'admin','2019-05-15 21:22:44',NULL,'2019-05-15 21:22:44'),
(140,'dcc451da81cb39db7b635f24d0160924ab183024.jpg',51183,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/disease/2_system/1557926646610.jpg?Expires=1559020651&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=zmCEE5UNFiDU01yielI9q%2FE7onY%3D','disease/2_system/1557926646610.jpg',NULL,'admin','2019-05-15 21:24:07',NULL,'2019-05-15 21:24:07'),
(141,'dcc451da81cb39db7b635f24d0160924ab183024.jpg',51183,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/disease/2_system/1557926649438.jpg?Expires=1559020654&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=ZWqC4mnAV6jR4ac295A5tcHI2AU%3D','disease/2_system/1557926649438.jpg',NULL,'admin','2019-05-15 21:24:09',NULL,'2019-05-15 21:24:09'),
(142,'c2fdfc039245d688174f8cd5a4c27d1ed21b2475.jpg',10383,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/disease/2_system/1557926692688.jpg?Expires=1559020697&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=aET00lByzwBXHkLg7b9Lttbzvqs%3D','disease/2_system/1557926692688.jpg',NULL,'admin','2019-05-15 21:24:53',NULL,'2019-05-15 21:24:53'),
(143,'c2fdfc039245d688174f8cd5a4c27d1ed21b2475.jpg',10383,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/disease/2_system/1557926696235.jpg?Expires=1559020701&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=r%2FGbRGHVo45QWxDDljtzOjo7EHU%3D','disease/2_system/1557926696235.jpg',NULL,'admin','2019-05-15 21:24:56',NULL,'2019-05-15 21:24:56'),
(144,'ac6eddc451da81cb794320795166d016082431d8.jpg',5154,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/disease/2_system/1557926797298.jpg?Expires=1559020802&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=X6S9GCVPnEcwkfn%2Fs7TnvJZqFsA%3D','disease/2_system/1557926797298.jpg',NULL,'admin','2019-05-15 21:26:37',NULL,'2019-05-15 21:26:37'),
(145,'ac6eddc451da81cb794320795166d016082431d8.jpg',5154,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/disease/2_system/1557926799860.jpg?Expires=1559020804&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=cwGGK9T7Zwl7MDD%2BT6OmrcKiLyA%3D','disease/2_system/1557926799860.jpg',NULL,'admin','2019-05-15 21:26:40',NULL,'2019-05-15 21:26:40'),
(146,'0b55b319ebc4b7451bbc271fcffc1e178a82158a.jpg',7589,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/disease/2_system/1557926857423.jpg?Expires=1559020862&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=pFHczoPszO4pG%2Fk8uD%2B6uQ59a2M%3D','disease/2_system/1557926857423.jpg',NULL,'admin','2019-05-15 21:27:37',NULL,'2019-05-15 21:27:37'),
(147,'0b55b319ebc4b7451bbc271fcffc1e178a82158a.jpg',7589,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/disease/2_system/1557926860454.jpg?Expires=1559020865&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=bJq4ICWWidF8va99jftyt2y%2BIz0%3D','disease/2_system/1557926860454.jpg',NULL,'admin','2019-05-15 21:27:41',NULL,'2019-05-15 21:27:41'),
(148,'c2fdfc039245d688174f8cd5a4c27d1ed21b2475.jpg',10383,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/solution/2_system/1557927202048.jpg?Expires=1559021206&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=q4rbvGU6FIhcePUAvr3e2MPMHNw%3D','solution/2_system/1557927202048.jpg',NULL,'admin','2019-05-15 21:33:22',NULL,'2019-05-15 21:33:22'),
(149,'dcc451da81cb39db7b635f24d0160924ab183024.jpg',51183,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/solution/2_system/1557927250688.jpg?Expires=1559021255&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=KM2LAVOIAyzVamhu8%2BFBE99Wvjs%3D','solution/2_system/1557927250688.jpg',NULL,'admin','2019-05-15 21:34:11',NULL,'2019-05-15 21:34:11'),
(150,'c2fdfc039245d688174f8cd5a4c27d1ed21b2475.jpg',10383,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/solution/2_system/1557927288579.jpg?Expires=1559021293&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=uHlMEQlO4Tz4%2F5DZgUxcAU3%2FlUg%3D','solution/2_system/1557927288579.jpg',NULL,'admin','2019-05-15 21:34:49',NULL,'2019-05-15 21:34:49'),
(151,'c2fdfc039245d688174f8cd5a4c27d1ed21b2475.jpg',10383,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/solution/2_system/1557927375485.jpg?Expires=1559021380&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=8f0QBng37qKCy5Ftj0RrdLB6mZg%3D','solution/2_system/1557927375485.jpg',NULL,'admin','2019-05-15 21:36:16',NULL,'2019-05-15 21:36:16'),
(152,'肿瘤.jfif',3549,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/solution/2_system/1557927440704.jfif?Expires=1559021445&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=Eq7hKKtCjhu5iRI8rDwGobl6cUI%3D','solution/2_system/1557927440704.jfif',NULL,'admin','2019-05-15 21:37:21',NULL,'2019-05-15 21:37:21'),
(153,'微信截图_20190515214052.png',24888,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1557927678048.png?Expires=1559021682&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=K9jVoDGFMfXTgxbSoXPIFqtlCpk%3D','course_image/2_system/1557927678048.png',NULL,'admin','2019-05-15 21:41:18',NULL,'2019-05-15 21:41:18'),
(154,'微信截图_20190515214349.png',28004,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1557927834282.png?Expires=1559021839&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=Qh99MkXr8YJCd9CwRzJm32pLRzw%3D','course_image/2_system/1557927834282.png',NULL,'admin','2019-05-15 21:43:54',NULL,'2019-05-15 21:43:54'),
(155,'微信截图_20190515214455.png',23569,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1557927899563.png?Expires=1559021904&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=eLZWuxoaAMXWRWiEs12BFpcdg3E%3D','course_image/2_system/1557927899563.png',NULL,'admin','2019-05-15 21:45:00',NULL,'2019-05-15 21:45:00'),
(158,'960a304e251f95ca7d62ab09c9177f3e660952fd.jpg',9969,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/office/2_system/1557928264173.jpg?Expires=1559022268&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=bs6jP1v7DgpWPr%2BjIkSIliaof7I%3D','office/2_system/1557928264173.jpg',NULL,'admin','2019-05-15 21:51:04',NULL,'2019-05-15 21:51:04'),
(159,'960a304e251f95ca7d62ab09c9177f3e660952fd.jpg',9969,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/office/2_system/1557928276657.jpg?Expires=1559022281&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=JVsGB73JkDt2zT6Urz9Vym5dIqw%3D','office/2_system/1557928276657.jpg',NULL,'admin','2019-05-15 21:51:17',NULL,'2019-05-15 21:51:17'),
(160,'1B6505C76A57540E.jpg',29169,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/charity/7/1558275436330.jpg?Expires=1559369441&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=%2FyRoOk%2BjebBTgQmwv7YHlvZkhMY%3D','charity/7/1558275436330.jpg',NULL,'7','2019-05-19 22:17:16',NULL,'2019-05-19 22:17:16'),
(161,'7FEA225588B767F8.jpg',22850,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/charity/7/1558275538127.jpg?Expires=1559369542&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=TweeOuLNnAfJvDMnAVtFKYOFsJ4%3D','charity/7/1558275538127.jpg',NULL,'7','2019-05-19 22:18:58',NULL,'2019-05-19 22:18:58'),
(162,'70744695A7566ECD.jpg',22850,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/fault/7/1558275573892.jpg?Expires=1559369578&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=KG8fJILXlk%2BvW03%2BgpoTa0NOzoo%3D','fault/7/1558275573892.jpg',NULL,'7','2019-05-19 22:19:34',NULL,'2019-05-19 22:19:34'),
(163,'广告图1.jpg',210618,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/2_system/1558712202298.jpg?Expires=1559806207&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=HeqwqOjvqHNd8tZ%2FcpLFDeU5adE%3D','2_system/1558712202298.jpg',NULL,'admin','2019-05-24 23:36:42',NULL,'2019-05-24 23:36:42'),
(164,'广告图2.jpg',157599,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/2_system/1558712212235.jpg?Expires=1559806217&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=OAPfnAM0faEVOvXJu5aesbdWEaI%3D','2_system/1558712212235.jpg',NULL,'admin','2019-05-24 23:36:52',NULL,'2019-05-24 23:36:52'),
(165,'广告图3.jpg',189330,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/2_system/1558712221204.jpg?Expires=1559806225&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=Qe0f5ovRh3EtVB6YpnKDWlO1X4I%3D','2_system/1558712221204.jpg',NULL,'admin','2019-05-24 23:37:01',NULL,'2019-05-24 23:37:01'),
(166,'广告图11.jpg',260813,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/2_system/1558712229813.jpg?Expires=1559806234&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=6yiQ4pnE7qZs3Tc9fAD57cOIkaU%3D','2_system/1558712229813.jpg',NULL,'admin','2019-05-24 23:37:10',NULL,'2019-05-24 23:37:10'),
(167,'2.jpg',47904,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1558712827204.jpg?Expires=1559806831&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=fmAGwKsTaVM%2FiBJmUsrojDdMLzc%3D','course_image/2_system/1558712827204.jpg',NULL,'admin','2019-05-24 23:47:07',NULL,'2019-05-24 23:47:07'),
(168,'2.jpg',47904,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1558712944766.jpg?Expires=1559806949&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=msOCywB3XApK7XS2KHciI9aYcDs%3D','course_image/2_system/1558712944766.jpg',NULL,'admin','2019-05-24 23:49:05',NULL,'2019-05-24 23:49:05'),
(169,'3.jpg',44441,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1558712998391.jpg?Expires=1559807003&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=2w4G3B4rIG%2F7FjxJtNiEPWB%2Fiy0%3D','course_image/2_system/1558712998391.jpg',NULL,'admin','2019-05-24 23:49:58',NULL,'2019-05-24 23:49:58'),
(170,'养生相关资料.jpg',61324,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1558713006470.jpg?Expires=1559807011&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=2SAlggUC40ibL7%2B4EOuTwOtE8SQ%3D','course_image/2_system/1558713006470.jpg',NULL,'admin','2019-05-24 23:50:07',NULL,'2019-05-24 23:50:07'),
(171,'3.jpg',44441,NULL,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/course_image/2_system/1558713011860.jpg?Expires=1559807016&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=j7BP7TokP4Hdvsg9AEY5pXl7Hm8%3D','course_image/2_system/1558713011860.jpg',NULL,'admin','2019-05-24 23:50:12',NULL,'2019-05-24 23:50:12');

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
  `recover_case` tinyint(1) DEFAULT NULL COMMENT '是否为康复案例',
  `read_num` int(11) DEFAULT NULL COMMENT '访问量',
  `audit` tinyint(1) NOT NULL DEFAULT '0' COMMENT '审核状态',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`journey_id`),
  KEY `user_id` (`user_id`),
  KEY `cover_image` (`cover_image`),
  CONSTRAINT `tb_journey_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_journey_ibfk_2` FOREIGN KEY (`cover_image`) REFERENCES `tb_images` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='养生旅程表';

/*Data for the table `tb_journey` */

insert  into `tb_journey`(`journey_id`,`journey_name`,`summarize`,`user_id`,`start_time`,`end_time`,`cover_image`,`note_num`,`finished`,`recover_case`,`read_num`,`audit`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(14,'空间里','图来看看',7,'2019-05-15 23:31:44','2019-05-19 17:57:50',NULL,1,1,1,8,1,NULL,'7','2019-05-15 23:31:44','7','2019-05-25 21:04:19'),
(15,NULL,NULL,7,'2019-05-19 18:07:38',NULL,NULL,0,0,0,0,1,NULL,'7','2019-05-19 18:07:38',NULL,'2019-05-19 18:19:58'),
(16,NULL,NULL,9,'2019-05-19 21:43:48',NULL,NULL,0,0,NULL,0,0,NULL,'9','2019-05-19 21:43:48',NULL,'2019-05-19 21:43:48'),
(17,NULL,NULL,9,'2019-05-20 10:11:38',NULL,NULL,0,0,NULL,0,0,NULL,'9','2019-05-20 10:11:38',NULL,'2019-05-20 10:11:38'),
(18,NULL,NULL,11,'2019-05-20 12:12:49',NULL,NULL,0,0,NULL,0,0,NULL,'11','2019-05-20 12:12:49',NULL,'2019-05-20 12:12:49');

/*Table structure for table `tb_journey_disease` */

DROP TABLE IF EXISTS `tb_journey_disease`;

CREATE TABLE `tb_journey_disease` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `journey_id` int(11) NOT NULL COMMENT '养生旅程ID',
  `disease_detail_id` int(11) NOT NULL COMMENT '疾病小类ID',
  `health_result_id` int(11) DEFAULT NULL COMMENT '养生成果ID',
  PRIMARY KEY (`id`),
  KEY `journey_id` (`journey_id`),
  KEY `disease_detail_id` (`disease_detail_id`),
  KEY `health_result_id` (`health_result_id`),
  CONSTRAINT `tb_journey_disease_ibfk_1` FOREIGN KEY (`journey_id`) REFERENCES `tb_journey` (`journey_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_journey_disease_ibfk_2` FOREIGN KEY (`disease_detail_id`) REFERENCES `tb_disease_class_detail` (`disease_detail_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_journey_disease_ibfk_3` FOREIGN KEY (`health_result_id`) REFERENCES `tb_health_result` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='养生旅程-疾病 关联表';

/*Data for the table `tb_journey_disease` */

insert  into `tb_journey_disease`(`id`,`journey_id`,`disease_detail_id`,`health_result_id`) values 
(26,14,10,NULL),
(27,14,11,NULL),
(28,14,12,NULL),
(29,14,13,NULL),
(30,15,9,NULL),
(31,15,11,NULL),
(32,16,9,NULL),
(33,17,8,NULL),
(34,18,13,NULL);

/*Table structure for table `tb_journey_health` */

DROP TABLE IF EXISTS `tb_journey_health`;

CREATE TABLE `tb_journey_health` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `journey_id` int(11) NOT NULL COMMENT '养生旅程ID',
  `health_id` int(11) NOT NULL COMMENT '养生ID',
  PRIMARY KEY (`id`),
  KEY `journey_id` (`journey_id`),
  KEY `health_detail_id` (`health_id`),
  CONSTRAINT `tb_journey_health_ibfk_1` FOREIGN KEY (`journey_id`) REFERENCES `tb_journey` (`journey_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_journey_health_ibfk_2` FOREIGN KEY (`health_id`) REFERENCES `tb_health` (`health_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='养生旅程-养生-成果 关联表';

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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='养生旅程体检指标表';

/*Data for the table `tb_journey_norm` */

insert  into `tb_journey_norm`(`id`,`norm_type_id`,`journey_id`,`start_value1`,`start_value2`,`end_value1`,`end_value2`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(28,2,14,'0.4',NULL,NULL,NULL,NULL,'7','2019-05-15 23:31:44',NULL,'2019-05-15 23:31:44'),
(29,2,15,'0.3',NULL,NULL,NULL,NULL,'7','2019-05-19 18:07:38',NULL,'2019-05-19 18:07:38'),
(30,1,16,'0.4',NULL,NULL,NULL,NULL,'9','2019-05-19 21:43:48',NULL,'2019-05-19 21:43:48'),
(31,2,16,'0.5',NULL,NULL,NULL,NULL,'9','2019-05-19 21:43:48',NULL,'2019-05-19 21:43:48'),
(32,2,17,'0.3',NULL,NULL,NULL,NULL,'9','2019-05-20 10:11:38',NULL,'2019-05-20 10:11:38'),
(33,1,18,'0.4',NULL,NULL,NULL,NULL,'11','2019-05-20 12:12:49',NULL,'2019-05-20 12:12:49'),
(34,2,18,'0.4',NULL,NULL,NULL,NULL,'11','2019-05-20 12:12:49',NULL,'2019-05-20 12:12:49'),
(35,3,18,'104','62',NULL,NULL,NULL,'11','2019-05-20 12:12:49',NULL,'2019-05-20 12:12:49');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='养生旅程日记表';

/*Data for the table `tb_journey_note` */

insert  into `tb_journey_note`(`id`,`journey_id`,`cover_image_id`,`content`,`circle_of_friends`,`note_date`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(6,14,NULL,'测试\n0518',NULL,'2019-05-18 15:45:44',NULL,'7','2019-05-18 15:45:44',NULL,'2019-05-18 15:45:44');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `tb_journey_note_norm` */

insert  into `tb_journey_note_norm`(`id`,`norm_type_id`,`note_id`,`value1`,`value2`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(11,1,6,'0.4',NULL,NULL,'7','2019-05-18 15:45:44',NULL,'2019-05-18 15:45:44');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='养生旅程日记-图片关联表';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='养生旅程-课程关联表';

/*Data for the table `tb_journey_to_course` */

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='养生旅程-体检报告关联表';

/*Data for the table `tb_journey_to_report` */

insert  into `tb_journey_to_report`(`id`,`journey_id`,`report_id`,`start_flag`) values 
(23,14,11,1),
(24,14,16,0),
(25,15,17,1),
(26,16,18,1),
(27,17,22,1),
(28,18,25,1);

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
  `oss_url` varchar(5000) DEFAULT NULL COMMENT 'OSS URL',
  `oss_key` varchar(100) DEFAULT NULL COMMENT 'OSS KEY',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_user_name` varchar(30) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_name` varchar(30) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `path` (`path`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='体检报告文件表';

/*Data for the table `tb_medical_report` */

insert  into `tb_medical_report`(`id`,`file_name`,`size`,`path`,`file_type`,`type`,`oss_url`,`oss_key`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(11,NULL,1330,NULL,'multipart/form-data',0,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/startReport/7/1557934303079.txt?Expires=1559028308&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=TujwuLP%2F%2Bu8jToym%2Fuh%2BCgfB3bs%3D','startReport/7/1557934303079.txt',NULL,'7','2019-05-15 23:31:43',NULL,'2019-05-15 23:31:43'),
(12,NULL,243523,NULL,'multipart/form-data',0,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/startReport/7/1557969423173.txt?Expires=1559063428&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=9B5%2B9InwWKFDCU9netQITGwx%2FaY%3D','startReport/7/1557969423173.txt',NULL,'7','2019-05-16 09:17:04',NULL,'2019-05-16 09:17:04'),
(13,NULL,11606,NULL,'multipart/form-data',1,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/endReport/7/1557969607266.log?Expires=1559063612&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=D5TAppxf1taplNeLShbyKOoxGh0%3D','endReport/7/1557969607266.log',NULL,'7','2019-05-16 09:20:07',NULL,'2019-05-16 09:20:07'),
(14,NULL,243523,NULL,'multipart/form-data',0,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/startReport/7/1558165543642.txt?Expires=1559259548&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=nsIQV3WvzxyHVXXzqK4VVOJ8hTE%3D','startReport/7/1558165543642.txt',NULL,'7','2019-05-18 15:45:44',NULL,'2019-05-18 15:45:44'),
(15,NULL,1330,NULL,'multipart/form-data',1,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/endReport/7/1558259501955.txt?Expires=1559353506&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=huNEYMVh5%2BeJlimfAj4eQ%2BHFIXw%3D','endReport/7/1558259501955.txt',NULL,'7','2019-05-19 17:51:42',NULL,'2019-05-19 17:51:42'),
(16,NULL,39,NULL,'multipart/form-data',1,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/endReport/7/1558259869658.dat?Expires=1559353874&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=IkCszPTDxqRkVn7fYTylcQ%2B3lSc%3D','endReport/7/1558259869658.dat',NULL,'7','2019-05-19 17:57:50',NULL,'2019-05-19 17:57:50'),
(17,NULL,3602,NULL,'multipart/form-data',0,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/startReport/7/1558260457299.log?Expires=1559354462&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=87HVUk6I930LX3j6hY86e%2F1J8wI%3D','startReport/7/1558260457299.log',NULL,'7','2019-05-19 18:07:37',NULL,'2019-05-19 18:07:37'),
(18,NULL,31,NULL,'multipart/form-data',0,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/startReport/9/1558273427736.journal?Expires=1559367432&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=Ztt%2Fzw4t4%2FLm8AzNdTyVJx0vQHE%3D','startReport/9/1558273427736.journal',NULL,'9','2019-05-19 21:43:48',NULL,'2019-05-19 21:43:48'),
(19,NULL,7065,NULL,'multipart/form-data',1,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/endReport/7/1558275637049.log?Expires=1559369641&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=z481zl1TqwS5s04Fi7s6wJqNsx0%3D','endReport/7/1558275637049.log',NULL,'7','2019-05-19 22:20:37',NULL,'2019-05-19 22:20:37'),
(20,NULL,7065,NULL,'multipart/form-data',1,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/endReport/7/1558275644877.log?Expires=1559369649&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=EiZFZHSA0FUSJB0B26FBy8%2FU%2Br4%3D','endReport/7/1558275644877.log',NULL,'7','2019-05-19 22:20:45',NULL,'2019-05-19 22:20:45'),
(21,NULL,7065,NULL,'multipart/form-data',1,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/endReport/7/1558275690986.log?Expires=1559369695&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=QEKJ%2FLbbwYGJuxfP0ack4llIeLI%3D','endReport/7/1558275690986.log',NULL,'7','2019-05-19 22:21:31',NULL,'2019-05-19 22:21:31'),
(22,NULL,94,NULL,'multipart/form-data',0,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/startReport/9/1558318297986.uuid?Expires=1559412302&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=r33jOk138WejE9MTjYtOuOqoSe8%3D','startReport/9/1558318297986.uuid',NULL,'9','2019-05-20 10:11:38',NULL,'2019-05-20 10:11:38'),
(23,NULL,128,NULL,'multipart/form-data',1,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/endReport/9/1558318377689.so?Expires=1559412382&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=f%2BiFS6myoAU2dAgjlgBcPxVSN4M%3D','endReport/9/1558318377689.so',NULL,'9','2019-05-20 10:12:58',NULL,'2019-05-20 10:12:58'),
(24,NULL,128,NULL,'multipart/form-data',1,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/endReport/9/1558318385064.so?Expires=1559412389&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=bhqNojG%2B7qGQCcWgW%2BsmzJ2QkJE%3D','endReport/9/1558318385064.so',NULL,'9','2019-05-20 10:13:05',NULL,'2019-05-20 10:13:05'),
(25,NULL,341,NULL,'multipart/form-data',0,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/startReport/11/1558325569330.142cc014a0513679a23c0acc0afcb936?Expires=1559419574&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=RVLLisPNiUXJyZ1BEhHInSs06rU%3D','startReport/11/1558325569330.142cc014a0513679a23c0acc0afcb936',NULL,'11','2019-05-20 12:12:49',NULL,'2019-05-20 12:12:49'),
(26,NULL,93542,NULL,'image/jpeg',1,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/endReport/8/1558442228578.jpg?Expires=1559536233&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=1kQOznY23bZKQucO2M%2Bj4oZH5N8%3D','endReport/8/1558442228578.jpg',NULL,'8','2019-05-21 20:37:09',NULL,'2019-05-21 20:37:09'),
(27,'(Calc Info)2019-05-14_07-48-24.txt',1330,NULL,'multipart/form-data',1,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/endReport/7/1558572791266.txt?Expires=1559666796&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=f4RiUu939YAAgJzhJacYMV26qr4%3D','endReport/7/1558572791266.txt',NULL,'7','2019-05-23 08:53:12',NULL,'2019-05-23 08:53:12'),
(28,'(Calc Info)2019-05-14_07-48-24.txt',1330,NULL,'multipart/form-data',1,'http://flighty-thought-oss-demo.oss-cn-shanghai.aliyuncs.com/endReport/7/1558572886891.txt?Expires=1559666891&OSSAccessKeyId=LTAICSkNdBY7Z0KK&Signature=ByHm6ukQgPBN2bidhPM5R0PjOcM%3D','endReport/7/1558572886891.txt',NULL,'7','2019-05-23 08:54:47',NULL,'2019-05-23 08:54:47');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='相关机构表';

/*Data for the table `tb_office` */

insert  into `tb_office`(`office_id`,`name`,`address`,`description`,`number`,`contact_name`,`phone`,`memo`,`create_time`,`create_user_name`,`update_time`,`update_user_name`) values 
(6,'相关机构1','北京市',NULL,'001','张三','001-1234567',NULL,'2019-05-15 21:51:08','Admin','2019-05-15 21:51:18','Admin');

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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='机构图片关联表';

/*Data for the table `tb_office_image` */

insert  into `tb_office_image`(`id`,`office_id`,`image_id`) values 
(21,6,159);

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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='康复案例表';

/*Data for the table `tb_recover_case` */

insert  into `tb_recover_case`(`id`,`journey_id`,`solution_id`,`cover_image`,`title`,`case_start_time`,`case_end_time`,`user_id`,`read_num`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(19,14,9,NULL,'空间里','2019-05-15 23:31:44','2019-05-19 17:57:50',7,0,NULL,'admin','2019-05-19 18:13:37',NULL,'2019-05-19 18:13:37'),
(20,14,10,NULL,'空间里','2019-05-15 23:31:44','2019-05-19 17:57:50',7,0,NULL,'admin','2019-05-19 18:13:37',NULL,'2019-05-19 18:13:37'),
(21,14,11,NULL,'空间里','2019-05-15 23:31:44','2019-05-19 17:57:50',7,0,NULL,'admin','2019-05-19 18:13:37',NULL,'2019-05-19 18:13:37');

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='解决方案表';

/*Data for the table `tb_solution` */

insert  into `tb_solution`(`id`,`number`,`title`,`content`,`agency_id`,`recover_number`,`read_num`,`memo`,`create_time`,`create_user_name`,`update_time`,`update_user_name`) values 
(8,'001','解决方案1','<div class=\"para-title level-2\">\n<h2 class=\"title-text\">流行性感冒预防措施</h2>\n</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"6_1\"></a><a class=\"lemma-anchor \" name=\"sub2301088_6_1\"></a><a class=\"lemma-anchor \" name=\"控制治疗传染源\"></a><a class=\"lemma-anchor \" name=\"6-1\"></a></div>\n<div class=\"para-title level-3\">\n<h3 class=\"title-text\">控制治疗传染源</h3>\n</div>\n<div class=\"para\">早发现，早报告，早隔离，早治疗</div>\n<div class=\"para\">呼吸道隔离1周或至主要症状消失</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"6_2\"></a><a class=\"lemma-anchor \" name=\"sub2301088_6_2\"></a><a class=\"lemma-anchor \" name=\"切断传播途径\"></a><a class=\"lemma-anchor \" name=\"6-2\"></a></div>\n<div class=\"para-title level-3\">\n<h3 class=\"title-text\">切断传播途径</h3>\n</div>\n<div class=\"para\">1. 流行期间，避免集会或集体娱乐活动，老幼病残易感者少去公共场所，注意通风，必要时对公共场所进行消毒</div>\n<div class=\"para\">2. 医护人员戴口罩、洗手、防交叉感染</div>\n<div class=\"para\">3. 患者用具及分泌物要彻底消毒</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"6_3\"></a><a class=\"lemma-anchor \" name=\"sub2301088_6_3\"></a><a class=\"lemma-anchor \" name=\"疫苗预防\"></a><a class=\"lemma-anchor \" name=\"6-3\"></a></div>\n<div class=\"para-title level-3\">\n<h3 class=\"title-text\">疫苗预防</h3>\n</div>\n<div class=\"para\">灭活疫苗：效果较好，接种对象为老人、儿童、严重慢性病患者、免疫力低下及可能密切接触患者的人员；接种时间为每年10-11月中旬，每年接种1次，2<a href=\"https://baike.baidu.com/item/%E5%91%A8%E5%8F%AF\" target=\"_blank\" rel=\"noopener\">周可</a>产生有效抗体。下列情况禁用：对鸡蛋过敏者；急性<a href=\"https://baike.baidu.com/item/%E4%BC%A0%E6%9F%93\" target=\"_blank\" rel=\"noopener\">传染</a>病患者，精神病患者，妊娠早期，6个月以下婴儿。&nbsp;\n<div class=\"lemma-picture text-pic layout-right\"><a class=\"image-link\" title=\"对待乙型流感态度\" href=\"https://baike.baidu.com/pic/%E4%B9%99%E5%9E%8B%E6%B5%81%E6%84%9F/3089798/0/1e71f724ee0818384d088d68?fr=lemma&amp;ct=single\" target=\"_blank\" rel=\"noopener\"><img class=\"\" src=\"https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D250/sign=4d441c57ddc451daf2f60bee86fc52a5/4b90f603738da9776f830222b051f8198618e30f.jpg\" alt=\"对待乙型流感态度\" /></a><span class=\"description\">对待乙型流感态度</span></div>\n　　减毒活疫苗：采用喷鼻法接种</div>',NULL,NULL,NULL,NULL,'2019-05-15 21:33:29','admin','2019-05-15 21:33:29',NULL),
(9,'002','解决方案2','<div class=\"para-title level-2\">\n<h2 class=\"title-text\">治疗</h2>\n</div>\n<div class=\"para\">在适当隔离的条件下，给予对症维持、抗感染、保证组织供氧、维持脏器功能等方面的治疗。</div>\n<div class=\"para\">对症维持主要包括卧床休息、动态监测生命体征、物理或药物降温。抗感染治疗包括抗病毒（如奥司他韦、扎那米韦、帕拉米韦等）治疗，但强调临床的治疗时机要&ldquo;早、快、准&rdquo;。尤其是，抗病毒药物在使用之前应留取呼吸道标本，并应尽量在发病48小时内使用，对于临床认为需要使用抗病毒药物的病例，发病超过48小时也可使用。</div>\n<div class=\"para\">保证组织氧合是维持重症和危重症病人重要器官正常功能的核心，可通过选择鼻管、口/鼻面罩、无创通气和有创通气等序贯方式进行。</div>\n<div class=\"para\">具体治疗方法应当在专业医生的指导下进行，以避免滥用药物和不当操作，造成耐药和贻误病情。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"6\"></a><a class=\"lemma-anchor \" name=\"sub10789098_6\"></a><a class=\"lemma-anchor \" name=\"预防\"></a></div>\n<div class=\"para-title level-2\">\n<h2 class=\"title-text\">预防</h2>\n</div>\n<div class=\"para\">结合禽流感病毒的特点和现有研究发现，目前认为，携带病毒的禽类是人感染禽流感的主要传染源。减少和控制禽类，尤其是家禽间的禽流感病毒的传播尤为重要。随着我国社会、经济发展水平的提高，急需加快推动传统家禽养殖和流通向现代生产方式转型升级，从散养方式向集中规模化养殖、宰杀处理和科学运输的转变，提高家禽和家畜的养殖、流通生物安全水平，从而减少人群的活禽或病死禽暴露机会。同时，要持续开展健康教育，倡导和培养个人呼吸道卫生和预防习惯，做到勤洗手、保持环境清洁、合理加工烹饪食物等。需特别加强人感染禽流感高危人群和医护人员的健康教育和卫生防护。</div>\n<div class=\"para\">同时，要做好动物和人的流感的监测。及时发现动物感染或发病疫情，以及环境中病毒循环的状态，尽早的采取动物免疫、扑杀、休市等消灭传染源、阻断病毒禽间传播的措施。早发现、早诊断禽流感病人，及时、有效、合理地实施病例隔离和诊治。做好疾病的流行病调查和病毒学监测，不断增进对禽流感的科学认识，及时发现聚集<a href=\"https://baike.baidu.com/item/%E6%80%A7%E7%97%85/292397\" target=\"_blank\" rel=\"noopener\" data-lemmaid=\"292397\">性病</a>例和病毒变异，进而采取相应的干预和应对措施。</div>\n<div class=\"para\">在做好科学防控的同时，还要认真开展流感大流行的应对准备。</div>',NULL,1,NULL,NULL,'2019-05-15 21:34:17','admin','2019-05-19 18:13:37',NULL),
(10,'003','解决方案3','<p>现在随着生活条件的上升，有的疾病已经年强化了。有的人生活上没有规律再加上上班压力大，晚上出去在撸个串喝个啤酒这样就会发胖，人胖了以后就懒不喜欢运动，随着疾病就找上了，就像高血压这个疾病现在已经年轻化，今天小编就给大家讲解一下高血压。</p>\n<p><img src=\"https://imgsa.baidu.com/baike/pic/item/b03533fa828ba61e2bc0d8814834970a304e5975.jpg\" /></p>\n<p>中国高血压流行情况 1</p>\n<p>中国高血压患病情况不容乐观，患病率近30年来逐年增长，而且高血压在我国出现三&ldquo;高&rdquo;、三&ldquo;低&rdquo;和四个&ldquo;最&rdquo;。</p>\n<p>（一）高血压的三&ldquo;高&rdquo;</p>\n<p>患病率高；根据2010年统计我国高血压的患病人口已达2亿, 每年新增患者约600万。</p>\n<p>致残率高；现有脑卒中患者600万，其中75%不同程度丧失劳动力每年有近200万人新发脑卒中。</p>\n<p>死亡率高；并发冠心病、心力衰竭、中风、肾衰等而引起的死亡逐年上升。</p>\n<p>（二）高血压的三&ldquo;低&rdquo;</p>\n<p>知晓率低</p>\n<p>治疗率低</p>\n<p>控制率低</p>\n<p>（三）高血压的四个&ldquo;最&rdquo;</p>\n<p>历史最久：埃及木乃伊和5100前&ldquo;冰人&rdquo;已有动脉硬化征象；</p>\n<p>流行最广：全球患者高达六亿；</p>\n<p>隐蔽最深：半数以上无症状，隐匿发病，猝然发病；</p>\n<p>危害最烈：患病、致残、致死均居首位。</p>\n<p>（四）高血压的发病情况</p>\n<p>随着年龄增高而发病率增高，女性在绝经前低于男性，绝经后高于男性； 城市高于农村；北方高于南方；根据90～91年我国高血压普查结果，15岁以上成人发病率为11.26%，据2010年统计全国成人发病率为20 %，20年翻一翻，目前我国已有2亿高血压患者。</p>\n<p>高血压的基本概念 2 什么是血压？</p>\n<p>血压是血液被心脏输向全身时对血管壁产生的压力。</p>\n<p>收缩压是心脏收缩时，血液给动脉壁的压力；</p>\n<p>舒张压是心脏舒张时，动脉血管回弹时测出的血压；</p>\n<p>什么是高血压？</p>\n<p>高血压，又称高血压病或原发性高血压。是指查不出原因、以非特异性血压持续升高为主要表现的一类临床征象。</p>\n<p>继发性高血压：由肾脏病、肾上腺肿瘤等引起。</p>\n<p><img src=\"https://imgsa.baidu.com/baike/pic/item/838ba61ea8d3fd1fc427b294394e251f95ca5f75.jpg\" /></p>\n<p>高血压定义</p>\n<p>只要在三个不同时间测得的血压平均值 &gt; 140/90 mmHg就可诊断高血压，如果医生测量血压后，发现您的高压高于140，而低压低于90，则会 告诉您患的是单纯性收缩期高血压。还有一种情况，如果患者曾经患有高血压，而且正在吃药，那么即使医生测量的血压正常，这名患者也仍是高血压患者。</p>\n<p>高血压的危害</p>\n<p>高血压的危害在于对心、脑、肾（靶器官）损害，这些靶器官的损害程度一般与血压水平密切相关，中度甚至轻度高血压也可能出现靶器官损害，与没有高血压相比高血压患者的心力衰竭危险增加6倍，中风危险增加4倍，舒张压每降低5mmHg终末期肾病的危险至少降低25%。</p>\n<p>高血压的临床后果</p>\n<p>视网膜出血、中风、心肌梗死、左心室肥厚、心力衰竭、终末期肾病。</p>\n<p>得了高血压怎么办？该怎么做？</p>\n<p>不要害怕！早期发现和治疗可以避免很多并发症和死亡；</p>\n<p>了解相关知识，坚持长期治疗；</p>\n<p>高血压的高危因素</p>\n<p>国际上公认高血压有三大高危因素 ：</p>\n<p>高盐膳食，肥胖，中度以上饮酒，这三种人很容易患高血压！</p>\n<p>盐： 吃盐越多，血压越高，吃盐越多，高血压的患病率也越高。</p>\n<p>肥胖：体重越重，血压越高，肥胖者患高血压的可能性是体重正常者的3-4倍，腹型肥胖者更容易发生高血压。</p>\n<p>饮酒：饮酒量越多，高压和低压也越高，尤其是高压的升高值更明显。</p>\n<p>遗传：如果父母都患有高血压，那么他们的子女得高血压的可能性就增加一倍。</p>\n<p><img src=\"https://imgsa.baidu.com/baike/pic/item/2934349b033b5bb5e1b230503fd3d539b600bc32.jpg\" /></p>\n<p>高血压对身体的危害非常大，高血压是动脉血管内的压力异常升高， 动脉血管如同流水的管道，心脏如同水泵，管道内的压力异常升高，泵就要用更大的力量将水泵到管道内，久而久之，泵就会因劳累而损害。</p>\n<p>高血压的症状和诊断 3</p>\n<p>高血压的症状</p>\n<p>高血压起病缓慢，早期多无症状，多在体检时发现， 有时有头晕、头痛、耳鸣、失眠、烦躁、心悸、胸闷、乏力等症状。</p>\n<p><img src=\"https://imgsa.baidu.com/baike/pic/item/574e9258d109b3dee4fef4c6c5bf6c81810a4cde.jpg\" /></p>\n<p>症状与血压的高低未必一致，如长期的高血压不及时有效的降压，心脏就会因过度劳累而代偿性肥厚和扩大，进而出现功能衰竭这就是高血压性心脏病心力衰竭。 长期下去除心脏外，脑、肾的破坏也同时发生严重的病变，大部分高血压的患者死亡原因是中风、心衰和肾功能衰竭。</p>',NULL,1,NULL,NULL,'2019-05-15 21:34:53','admin','2019-05-19 18:13:37',NULL),
(11,'006','原发性高血压的治疗','<div class=\"para-title level-2\">\n<h2 class=\"title-text\">治疗</h2>\n</div>\n<div class=\"para\"><strong>（一）原发性高血压的治疗</strong></div>\n<div class=\"para\"><strong>1.治疗目的及原则</strong></div>\n<div class=\"para\"><a href=\"https://baike.baidu.com/item/%E9%AB%98%E8%A1%80%E5%8E%8B\" target=\"_blank\" rel=\"noopener\">高血压</a>治疗的主要目标是血压达标，降压治疗的最终目的是最大限度地减少高血压患者心、脑血管病的发生率和死亡率。降压治疗应该确立血压控制目标值。另一方面，高血压常常与其他心、脑血管病的危险因素合并存在，例如高胆固醇血症、肥胖、糖尿病等，协同加重心血管疾病危险，治疗措施应该是综合性的。不同人群的降压目标不同，一般患者的降压目标为140/90mmHg以下，对合并糖尿病或肾病等高危患者，应酌情降至更低。对所有患者，不管其他时段的血压是否高于正常值，均应注意清晨血压的监测，有研究显示半数以上诊室血压达标的患者，其清晨血压并未达标。</div>\n<div class=\"para\"><strong>（1）改善生活行为</strong>&nbsp;①减轻并控制体重。②减少钠盐摄入。③补充钙和钾盐。④减少脂肪摄入。⑤增加运动。⑥戒烟、限制饮酒。⑦减轻精神压力，保持心理平衡。</div>\n<div class=\"para\"><strong>（2）血压控制标准个体化</strong>&nbsp;由于病因不同，高血压发病机制不尽相同，临床用药分别对待，选择最合适药物和剂量，以获得最佳疗效。</div>\n<div class=\"para\"><strong>（3）多重心血管危险因素协同控制</strong>&nbsp;降压治疗后尽管血压控制在正常范围，血压升高以外的多种危险因素依然对预后产生重要影响。</div>\n<div class=\"para\"><strong>2.降压药物治疗</strong></div>\n<div class=\"para\">对检出的高血压患者，应使用推荐的起始与维持治疗的降压药物，特别是每日给药1次能控制24小时并达标的药物，具体应遵循4项原则，即小剂量开始，优先选择长效制剂，联合用药及个体化。</div>\n<div class=\"para\"><strong>（1）降压药物种类</strong>&nbsp;①利尿药。②&beta;受体阻滞剂。③钙通道阻滞剂。④血管紧张素转换酶抑制剂。⑤血管紧张素Ⅱ受体阻滞剂。</div>\n<div class=\"para\">应根据患者的危险因素、靶器官损害及合并临床疾病的情况，选择单一用药或联合用药。选择降压药物的原则如下：</div>\n<div class=\"para\">1）使用半衰期24小时以及以上、每日一次服药能够控制24小时的血压药物，如氨氯地平等，避免因治疗方案选择不当导致的医源性清晨血压控制不佳；</div>\n<div class=\"para\">2）使用安全、可长期坚持并能够控制每一个24小时血压的药物，提高患者的治疗依从性；</div>\n<div class=\"para\">3）使用心脑获益临床试验证据充分并可真正降低长期心脑血管事件的药物，减少心脑血管事件，改善高血压患者的生存质量。</div>\n<div class=\"para\"><strong>（2）治疗方案</strong>&nbsp;大多数无并发症或合并症患者可以单独或者联合使用噻嗪类利尿剂、&beta;受体阻滞剂等。治疗应从小剂量开始，逐步递增剂量。临床实际使用时，患者心血管危险因素状况、靶器官损害、并发症、合并症、降压疗效、不良反应等，都会影响降压药的选择。2级高血压患者在开始时就可以采用两种降压药物联合治疗。</div>\n<div class=\"para\"><strong>（二）继发性高血压的治疗</strong></div>\n<div class=\"para\">主要是针对原发病的治疗，如嗜铬细胞瘤引起的高血压，肿瘤切除后血压可降至正常；肾血管性高血压可通过介入治疗扩张肾动脉。对原发病不能手术根治或术后血压仍高者，除采用其他针对病因的治疗外，还应选用适当的降压药物进行降压治疗。</div>\n<div class=\"anchor-list\"><a class=\"lemma-anchor para-title\" name=\"8\"></a><a class=\"lemma-anchor \" name=\"sub6632_8\"></a><a class=\"lemma-anchor \" name=\"预防\"></a></div>\n<div class=\"para-title level-2\">\n<h2 class=\"title-text\">预防</h2>\n</div>\n<div class=\"para\"><a href=\"https://baike.baidu.com/item/%E9%AB%98%E8%A1%80%E5%8E%8B\" target=\"_blank\" rel=\"noopener\">高血压</a>是一种可防可控的疾病，对血压130～139/85～89mmHg正常高值阶段、超重/肥胖、长期高盐饮食、过量饮酒者应进行重点干预，定期健康体检，积极控制危险因素。</div>\n<div class=\"para\">针对高血压患者，应定期随访和测量血压，尤其注意清晨血压的管理，积极治疗高血压（药物治疗与生活方式干预并举），减缓靶器官损害，预防心脑肾并发症的发生，降低致残率及死亡率。<sup class=\"sup--normal\" data-sup=\"1-4\" data-ctrmap=\":1,:2,:3,:4,\">&nbsp;[1-4]</sup></div>',NULL,1,NULL,NULL,'2019-05-15 21:36:20','admin','2019-05-19 18:13:37',NULL),
(12,'007','曲菌病治疗','<div class=\"para-title level-2\">\n<h2 class=\"title-text\">治疗方案</h2>\n</div>\n<div class=\"para\">1.严格掌握糖皮质激素及广谱抗生素的应用。</div>\n<div class=\"para\">2.积极治疗原发病，加强支持疗法，提高机体免疫力。</div>\n<div class=\"para\">3.病原治疗</div>\n<div class=\"para\">(1)对<a href=\"https://baike.baidu.com/item/%E9%9A%90%E7%90%83%E8%8F%8C%E8%84%91%E8%86%9C%E7%82%8E\" target=\"_blank\" rel=\"noopener\">隐球菌脑膜炎</a>患者：①用二性霉素B加5-氟胞嘧啶联合治疗具有协同作用。二性霉素B lmg/(kg&middot;d)静滴、5-氟胞嘧啶50～150mg/(kg&middot;d)分3次口服或静滴共6周。治疗前、治疗中每2周复查BUN及血象以确定用药的适当时间间隔。治疗前测肾功及血象，对肾功损害及骨髓抑制的患者应慎用(二性霉素B首次静脉滴注剂量为0.1mg/(kZ&middot;d)，以后每日增加0.05～0.10mg/kg直至1.0mg/kg，但每日剂量不超过50mg/d，药物溶解于5%葡萄糖液，最佳浓度为0.1mg/m1)。二性霉素B也可与利福平联合应用，亦有协同作用。②咪康唑(双氯苯咪唑)常用于不能耐受二性霉素B或治疗反应不佳的深部真菌患者。对念珠菌、曲菌、隐球菌、组织胞浆菌或环孢子菌等全身感染有效。剂量：0.6g～1.2g/d分3次静滴，鞘内注射成人20mg每3～7d一次。③氟康唑有广谱抗真菌作用，口服吸收良好，能透过血脑屏障，200mg每日1～2次。④球红霉素口服400～2000U/(kg&middot;d)，静滴40～100U/(kg&middot;d)开始；渐增至600～800U/(kg&middot;d)，成人总量不超过300万～400万U。</div>\n<div class=\"para\">(2)肺部隐球菌病轻型患者可不用抗真菌药物，如无条件随访可口服酮康唑200mg～400mg/d。</div>\n<div class=\"para\">4、护理</div>\n<div class=\"para\">(1)按传染病一般护理常规护理。高热时按高热护理常规护理。</div>\n<div class=\"para\">(2)及时留送痰、血、尿、粪便、脑脊髓液、脓液等，进行常规检查及培养。</div>\n<div class=\"para\">(3)3.密切观察病情，特别注意咳嗽、咯血、呼吸、神志改变等，如有变化应及时与医师联系。</div>\n<div class=\"para\">(4)及时做好隔离、空气消毒、口腔卫生、皮肤护理等。</div>\n<div class=\"para\">5、出院标准</div>\n<div class=\"para\">临床症状与体征消失，精神、食欲恢复，血、脑脊液等常规检查正常，培养阴性。<sup class=\"sup--normal\" data-sup=\"2\" data-ctrmap=\":2,\">&nbsp;[2]</sup><a class=\"sup-anchor\" name=\"ref_[2]_717855\"></a>&nbsp;</div>',NULL,NULL,NULL,NULL,'2019-05-15 21:37:25','admin','2019-05-15 21:37:25',NULL);

/*Table structure for table `tb_solution_commodity` */

DROP TABLE IF EXISTS `tb_solution_commodity`;

CREATE TABLE `tb_solution_commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `solution_id` int(11) NOT NULL COMMENT '解决方案ID',
  `commodity_id` int(11) NOT NULL COMMENT '商品ID',
  PRIMARY KEY (`id`),
  KEY `solution_id` (`solution_id`),
  KEY `tb_solution_commodity_ibfk_2` (`commodity_id`),
  CONSTRAINT `tb_solution_commodity_ibfk_1` FOREIGN KEY (`solution_id`) REFERENCES `tb_solution` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_solution_commodity_ibfk_2` FOREIGN KEY (`commodity_id`) REFERENCES `tb_commodity` (`commodity_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_solution_commodity` */

insert  into `tb_solution_commodity`(`id`,`solution_id`,`commodity_id`) values 
(1,1,2),
(2,2,2);

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='解决方案-课程关联表';

/*Data for the table `tb_solution_course` */

insert  into `tb_solution_course`(`id`,`course_id`,`solution_id`) values 
(13,10,8),
(14,11,8),
(15,9,9),
(16,8,9),
(17,11,10),
(18,9,11),
(19,10,11),
(20,10,12),
(21,9,12),
(22,11,12);

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='解决方案-图片 关联表';

/*Data for the table `tb_solution_image` */

insert  into `tb_solution_image`(`id`,`image_id`,`solution_id`) values 
(7,148,8),
(8,149,9),
(9,150,10),
(10,151,11),
(11,152,12);

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
(2,'domain_port','http://www.flighty-thought.cn:8087','项目域名端口',NULL,'system','2019-03-27 17:29:34',NULL,NULL),
(3,'domain_port_app','http://www.flighty-thought.cn:8086','APP接口域名端口',NULL,'system','2019-04-04 23:57:33',NULL,NULL),
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
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(30) DEFAULT NULL COMMENT '昵称',
  `auth_id` varchar(500) DEFAULT NULL COMMENT 'AuthId',
  `third_type` varchar(30) DEFAULT NULL COMMENT 'Login Type',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证',
  `login_time` datetime DEFAULT NULL COMMENT '登陆时间',
  `login_count` int(11) DEFAULT NULL COMMENT '登陆次数',
  `photo` int(11) DEFAULT NULL COMMENT '头像',
  `height` double DEFAULT NULL COMMENT '身高',
  `body_weight` double DEFAULT NULL COMMENT '体重',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` int(1) DEFAULT NULL COMMENT '性别，0：男，1：女',
  `ip` varchar(32) DEFAULT NULL COMMENT 'IP地址',
  `token` varchar(500) DEFAULT NULL COMMENT 'Token',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `mobile` (`mobile`),
  UNIQUE KEY `auth_id` (`auth_id`,`third_type`),
  UNIQUE KEY `user_name` (`user_name`),
  KEY `photo` (`photo`),
  CONSTRAINT `tb_user_ibfk_1` FOREIGN KEY (`photo`) REFERENCES `tb_images` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='APP用户表';

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`mobile`,`user_name`,`password`,`nick_name`,`auth_id`,`third_type`,`id_card`,`login_time`,`login_count`,`photo`,`height`,`body_weight`,`birthday`,`sex`,`ip`,`token`,`memo`,`create_time`,`create_user_name`,`update_time`,`update_user_name`) values 
(7,'18914050263','48976d14b9e36de8ca26e9bbdcba8f64',NULL,'手机用户55383',NULL,NULL,NULL,'2019-05-15 20:40:19',1,NULL,180,65,'1991-05-05',NULL,'112.65.12.38','eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6IjQ4OTc2ZDE0YjllMzZkZThjYTI2ZTliYmRjYmE4ZjY0XzE1NTc5MjQwMTkwNzkiLCJpYXQiOjE1NTc5MjQwMTksImV4cCI6MTU2NTcwMDAxOX0.UbZ2Q7-jfgvybrX2ELnswuoHqd2GvvSUTPe_-2GVdWtD-A5FGxBg5Sh7W4UBevNSiZElBsKAzvdYzAH7UN0wOQ',NULL,'2019-05-15 20:40:19','system','2019-05-15 20:40:49',NULL),
(8,'15850374663','564ca4b98598f3c3461ea82d16927267',NULL,'手机用户97622',NULL,NULL,NULL,'2019-05-21 20:35:29',3,NULL,160,60,'1981-01-01',NULL,'0:0:0:0:0:0:0:1','eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6IjU2NGNhNGI5ODU5OGYzYzM0NjFlYTgyZDE2OTI3MjY3XzE1NTg0NDIxMjg2NzciLCJpYXQiOjE1NTg0NDIxMjgsImV4cCI6MTU2NjIxODEyOH0.tvG-8RHlHF_11QUEZPOdlegCW__oeiNIsn_97UWMZQYIRXZeJG0eVbqT2JTNWrBGauFV2kkd8zQRcXdoDoAF3g',NULL,'2019-05-15 20:43:48','system','2019-05-21 20:35:29',NULL),
(9,'18550133770','c3a890b282864893144461536baa2770',NULL,'手机用户46391',NULL,NULL,NULL,'2019-05-20 10:10:34',2,NULL,170,70,'1970-01-01',NULL,'122.96.46.202','eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6ImMzYTg5MGIyODI4NjQ4OTMxNDQ0NjE1MzZiYWEyNzcwXzE1NTgzMTgyMzQzMTQiLCJpYXQiOjE1NTgzMTgyMzQsImV4cCI6MTU2NjA5NDIzNH0.73PuMxzR1ZDwDjsyCSK_Tb_C7SO9KCRHj_SWDXyqbbB2p9yIllCfZnEHbmQKqGuz4Bwr2SGXKTQYxbn0GcS3wA',NULL,'2019-05-19 21:32:44','system','2019-05-20 10:10:34',NULL),
(10,'15508710313','61042eb36b3f27880641d6787ac314f3',NULL,'手机用户68258',NULL,NULL,NULL,'2019-05-20 11:35:06',1,NULL,178.1,60,'1976-01-01',NULL,'27.195.56.143','eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6IjYxMDQyZWIzNmIzZjI3ODgwNjQxZDY3ODdhYzMxNGYzXzE1NTgzMjMzMDYwMDIiLCJpYXQiOjE1NTgzMjMzMDYsImV4cCI6MTU2NjA5OTMwNn0.JnzyKcdbV1HXIB3ByGG6C1dmxM87fshcTx8gthuNXEIfcUyZS9S3i0nMw9sMGCbHP_Mv3U5Iv-gz6DV9xMcjSQ',NULL,'2019-05-20 11:35:06','system','2019-05-20 11:35:42',NULL),
(11,'15821765358','f21594e272929c8c41ee257a9b5cc507',NULL,'手机用户98957',NULL,NULL,NULL,'2019-05-20 12:00:22',1,NULL,160,60,'1990-01-01',NULL,'101.84.158.25','eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6ImYyMTU5NGUyNzI5MjljOGM0MWVlMjU3YTliNWNjNTA3XzE1NTgzMjQ4MjIxMjciLCJpYXQiOjE1NTgzMjQ4MjIsImV4cCI6MTU2NjEwMDgyMn0.Fdg3s5k6mUpolinDvFg1KS1l3Z8q5o6WrzfRIbU2yLF7tfhXEV6rYgTIrleKL3_tDp7h2RJg2NViYXlmpFwpnA',NULL,'2019-05-20 12:00:22','system','2019-05-20 12:00:28',NULL),
(12,'15093351537','271933768f37c8a8549d60113998c41b',NULL,'手机用户58787',NULL,NULL,NULL,'2019-05-20 12:01:39',1,NULL,160,60,'1990-01-01',NULL,'180.154.202.232','eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6IjI3MTkzMzc2OGYzN2M4YTg1NDlkNjAxMTM5OThjNDFiXzE1NTgzMjQ4OTg4NjEiLCJpYXQiOjE1NTgzMjQ4OTgsImV4cCI6MTU2NjEwMDg5OH0.pLbCNyjw7CutjHx9QJazIKcCD5LXryvB3qCdM8euMPEfMvZ1SVWPZPpFxLLI9s2T-GKOlgpP911krv3w7MCGXA',NULL,'2019-05-20 12:01:39','system','2019-05-20 12:01:43',NULL),
(13,'17620397725','26e25e8ae4d315ca6539d2c821c0425c',NULL,'手机用户84727',NULL,NULL,NULL,'2019-05-24 23:34:12',1,NULL,160,60,'1990-01-01',NULL,'223.106.59.148','eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6IjI2ZTI1ZThhZTRkMzE1Y2E2NTM5ZDJjODIxYzA0MjVjXzE1NTg3MTIwNTE3ODIiLCJpYXQiOjE1NTg3MTIwNTEsImV4cCI6MTU2NjQ4ODA1MX0.21ZQeI30w9cdlbOHR1qxlXa9_Bq3xcpcHagiBdhA_VP4ngDBZeNGz8q-cmPgGC0Ff6XgDN4vqPv4CZMfvUkTmA',NULL,'2019-05-24 23:34:12','system','2019-05-24 23:34:16',NULL),
(14,'18912798439','aa693f69358d7b84b65e1b790e505d9d',NULL,'手机用户66060',NULL,NULL,NULL,'2019-05-25 15:42:49',1,NULL,NULL,NULL,NULL,NULL,'49.84.170.169','eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6ImFhNjkzZjY5MzU4ZDdiODRiNjVlMWI3OTBlNTA1ZDlkXzE1NTg3NzAxNjg5NjkiLCJpYXQiOjE1NTg3NzAxNjgsImV4cCI6MTU2NjU0NjE2OH0.SUL2E35GcmzKHoh2QnirjjGtb-i_q3TTDaYj32l3R64bhKkreCRwElWKTzhJAV3h19hGeB8hmQB2UXaVwqIf-g',NULL,'2019-05-25 15:42:49','system','2019-05-25 15:42:49',NULL),
(15,'18913198796','ba8372d63e5cab01cf16f668da5880c6',NULL,'手机用户87449',NULL,NULL,NULL,'2019-05-25 17:58:30',3,NULL,NULL,NULL,NULL,NULL,'49.84.170.169','eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6ImJhODM3MmQ2M2U1Y2FiMDFjZjE2ZjY2OGRhNTg4MGM2XzE1NTg3NzgzMDk1NzkiLCJpYXQiOjE1NTg3NzgzMDksImV4cCI6MTU2NjU1NDMwOX0.KVzDT9JXNa9ok9Upk52BQ5O2FvE3qWDuVFjCEa2dvKJ4zgBl_6ZUp_errZW-f3SSZdjS1l_jYMO83NYyrB6yYw',NULL,'2019-05-25 17:10:09','system','2019-05-25 17:58:30',NULL),
(16,'15962136555','b7eadeb093c3e8e9309629f90fb286a6',NULL,'手机用户48594',NULL,NULL,NULL,'2019-05-25 21:17:33',2,NULL,160,60,'1990-01-01',NULL,'49.84.170.169','eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6ImI3ZWFkZWIwOTNjM2U4ZTkzMDk2MjlmOTBmYjI4NmE2XzE1NTg3OTAyNTMzNzYiLCJpYXQiOjE1NTg3OTAyNTMsImV4cCI6MTU2NjU2NjI1M30.BOBF1_frFuWocl_sSdkPeoFZ9csghUhb7WlAUFeDYfYiIjZNa7hvEl77tNGioxmB0uvr0ORuVAGYf7Xz1hlVPg',NULL,'2019-05-25 21:02:01','system','2019-05-25 21:17:33',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COMMENT='用户行善过失积分表\r\n';

/*Data for the table `tb_user_charity_fault_integral` */

insert  into `tb_user_charity_fault_integral`(`id`,`score`,`charity_count`,`fault_count`,`user_id`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(4,3,2,1,7,NULL,'7','2019-05-19 22:17:17','7','2019-05-19 22:19:34'),
(5,1,1,0,9,NULL,'9','2019-05-20 10:13:48',NULL,'2019-05-20 10:13:48'),
(6,1,1,0,11,NULL,'11','2019-05-20 12:14:21',NULL,'2019-05-20 12:14:21');

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户行善过失记录表';

/*Data for the table `tb_user_charity_fault_record` */

insert  into `tb_user_charity_fault_record`(`id`,`user_id`,`type`,`content`,`charity_time_start`,`charity_time_end`,`cf_type_id`,`type_content_id`,`donate_amount`,`material_details`,`longitude`,`latitude`,`address`,`memo`,`create_user_name`,`create_time`,`update_user_name`,`update_time`) values 
(19,7,0,NULL,NULL,NULL,5,NULL,100.00,NULL,NULL,NULL,NULL,NULL,'7','2019-05-19 22:17:17',NULL,'2019-05-19 22:17:17'),
(20,7,0,NULL,NULL,NULL,5,NULL,1000.00,NULL,NULL,NULL,NULL,NULL,'7','2019-05-19 22:18:58',NULL,'2019-05-19 22:18:58'),
(21,7,1,'自言自语',NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'7','2019-05-19 22:19:34',NULL,'2019-05-19 22:19:34'),
(22,9,0,NULL,NULL,NULL,1,3,NULL,NULL,NULL,NULL,NULL,NULL,'9','2019-05-20 10:13:48',NULL,'2019-05-20 10:13:48'),
(23,11,0,NULL,NULL,NULL,1,3,NULL,NULL,NULL,NULL,NULL,NULL,'11','2019-05-20 12:14:21',NULL,'2019-05-20 12:14:21');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户预约课程表';

/*Data for the table `tb_user_follow_course` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户关注疾病表';

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
