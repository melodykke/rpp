

CREATE TABLE `power_plant_location_info` (
   `plant_id` varchar(32) NOT NULL,
   `plant_code` varchar(32) COMMENT '电站编码',
   `plant_name` varchar(64) NOT NULL COMMENT '电站名称',
   `plant_nature` varchar(32) DEFAULT NULL COMMENT '电站属性',
   `plant_village_address` varchar(64) DEFAULT NULL COMMENT '电站所在村',
   `plant_town_address` varchar(64) DEFAULT NULL COMMENT '电站所在乡',
   `plant_affiliation` varchar(64) DEFAULT NULL COMMENT '隶属关系',
   `general_income` decimal(10,2) DEFAULT NULL COMMENT '发（售）电总收入（万元）',
   `taxes` decimal(8,2) DEFAULT NULL COMMENT '上缴税金（万元）',
   `fixed_assets` decimal(10,2) DEFAULT NULL COMMENT '固定资产',
   `storage` decimal(8,2) DEFAULT NULL COMMENT '电站水库库容',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   PRIMARY KEY (`plant_id`),
   KEY `idx_plant_code` (`plant_code`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `power_plant_generating_equipment` (
	`equipment_id` INT(100) NOT NULL AUTO_INCREMENT,
	`plant_code` VARCHAR(32) NOT NULL COMMENT '电站编码',
	`equipment_quantity` INT DEFAULT NULL COMMENT '发电机组数量（台）',
	`equipment_power` INT DEFAULT NULL COMMENT '发电功率（千瓦）',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`equipment_id`),
	KEY `idx_plant_code` (`plant_code`)
	)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `power_plant_power_info` (
	`plant_id` VARCHAR(32) NOT NULL,
	`plant_code` VARCHAR(32) COMMENT '电站编码',
	`plant_name` VARCHAR(64) NOT NULL COMMENT '电站名称',
	`administrative_division` VARCHAR(32) COMMENT '行政区划代码',
	`equipment_total_power` DECIMAL(10,2) COMMENT '发电设备容量(千瓦)',
	`total_power_per_year` DECIMAL(10,2) COMMENT '全年发电量(万千瓦时)',
	`plant_power_consumption` DECIMAL(8,2)  COMMENT '厂用电量(万千瓦时)',
	`plant_power_consumption_rate` DECIMAL(8,2) COMMENT '厂用电率',
	`quantity_to_national_grid` DECIMAL(10,2) COMMENT '上国家电网电量(万千瓦时)',
	`price_to_national_grid` DECIMAL(8,3) COMMENT '上国家电网电价(元/千瓦时)',
	`quantity_to_local_grid` DECIMAL(10,2) COMMENT '上地方电网电量(万千瓦时)',
	`price_to_local_grid` DECIMAL(8,3) COMMENT '上地方电网电价(元/千瓦时)',
	`quantity_to_rural_grid` DECIMAL(10,2) COMMENT '上农村电网电量(万千瓦时)',
	`price_to_rural_grid` DECIMAL(8,3) COMMENT '上农村电网电价(元/千瓦时)',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`plant_id`),
	KEY `idx_plant_code` (`plant_code`)
	)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `power_plant_base_info`(
	`plant_id` VARCHAR(32) NOT NULL,
	`plant_code` VARCHAR(32) COMMENT '电站编码',
	`plant_name` VARCHAR(64) NOT NULL COMMENT '电站名称',
	`plant_nature` VARCHAR(32) COMMENT '电站属性',
	`plant_status` VARCHAR(32) COMMENT '电站建设属性',
	`plant_river_location` VARCHAR(32) COMMENT '所在河流',
	`plant_affiliation` VARCHAR(64) COMMENT '隶属关系',
	`water_department_stock_share` DECIMAL(8,2) COMMENT '水利系统所占股份（百分比）',
	`plant_stock_character` VARCHAR(32) COMMENT '电站股份情况',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`plant_id`),
	KEY `index_plant_code` (`plant_code`)
	) ENGINE=INNODB DEFAULT CHARSET=utf8


INSERT INTO `user_info` (`user_id`,`username`,`name`,`password`,`salt`,`state`) VALUES ('1', 'admin', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', 0);
INSERT INTO `sys_permission` (`permission_id`,`available`,`name`,`parent_id`,`parent_ids`,`permission`,`resource_type`,`url`) VALUES (1,0,'用户管理',0,'0/','userInfo:view','menu','userInfo/userList');
INSERT INTO `sys_permission` (`permission_id`,`available`,`name`,`parent_id`,`parent_ids`,`permission`,`resource_type`,`url`) VALUES (2,0,'用户添加',1,'0/1','userInfo:add','button','userInfo/userAdd');
INSERT INTO `sys_permission` (`permission_id`,`available`,`name`,`parent_id`,`parent_ids`,`permission`,`resource_type`,`url`) VALUES (3,0,'用户删除',1,'0/1','userInfo:del','button','userInfo/userDel');
INSERT INTO `sys_role` (`role_id`,`available`,`description`,`mark`) VALUES (1,0,'管理员','admin');
INSERT INTO `sys_role` (`role_id`,`available`,`description`,`mark`) VALUES (2,0,'VIP会员','vip');
INSERT INTO `sys_role` (`role_id`,`available`,`description`,`mark`) VALUES (3,1,'test','test');
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` (`permission_id`,`role_id`) VALUES (1,1);
INSERT INTO `sys_role_permission` (`permission_id`,`role_id`) VALUES (2,1);
INSERT INTO `sys_role_permission` (`permission_id`,`role_id`) VALUES (3,2);
INSERT INTO `sys_user_role` (`role_id`,`user_id`) VALUES (1,1);