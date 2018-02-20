

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

CREATE TABLE `power_plant_base_info_upload`(
	`plant_id` VARCHAR(32) NOT NULL,
	`plant_code` VARCHAR(32) COMMENT '电站编码',
	`plant_name` VARCHAR(64) NOT NULL COMMENT '电站名称',
	`administrative_area` VARCHAR(32) COMMENT '行政区',
	`plant_river_location` VARCHAR(32) COMMENT '所在河流',
	`longitude` DECIMAL(12,6) COMMENT '经度',
	`latitude` DECIMAL(12,6) COMMENT '纬度',
	`location` VARCHAR(32) COMMENT '建站地点',
	`development_mode` VARCHAR(32) COMMENT '开发方式',
	`installed_capacity` VARCHAR(32) COMMENT '装机规模',
	`designed_hydraulic_head` VARCHAR(32) COMMENT '设计水头',
	`designed_power_per_year` DECIMAL(10,2) COMMENT '设计全年发电量(万千瓦时)',
	`guaranteed_output` DECIMAL(10,2) COMMENT '保证出力',
	`eco_flow` DECIMAL(10,2) COMMENT '生态流量',
	`flow_for_power` DECIMAL(10,2) COMMENT '发电引用流量',
	`regulation_performance` VARCHAR(32) COMMENT '调节性能',
	`dam_interval_length` DECIMAL(10,2) COMMENT '坝间河道长度',
	`average_flow_for_years` DECIMAL(10,2) COMMENT '多年平均流量',
	`dam_contral_watershed_area` DECIMAL(10,2) COMMENT '堤坝控制流域面积',
	`normal_storage_level` DECIMAL(10,2) COMMENT '正常蓄水位',
	`dead_water_level` DECIMAL(10,2) COMMENT '死水位',
	`limiting_level_during_flood_season` DECIMAL(10,2) COMMENT '防洪限制水位',
	`total_storage` DECIMAL(10,2) COMMENT '总库容',
	`normal_water_level_storage` DECIMAL(10,2) COMMENT '正常蓄水位相应库容',
	`plant_status` VARCHAR(32) COMMENT '建站状态',
	`dead_storage` DECIMAL(10,2) COMMENT '死库容',
	`adjust_storage` DECIMAL(10,2) COMMENT '调节库容',
	`power_factory_form` VARCHAR(32) COMMENT '发电厂房形式',
	`integrated_utilization` VARCHAR(32) COMMENT '综合利用',
	`submerged_cultivated_land` DECIMAL(10,2) COMMENT '水库淹没耕地',
	`submerged_forest` DECIMAL(10,2) COMMENT '水库淹没林地',
	`dam_hight` DECIMAL(10,2) COMMENT '坝高',
	`main_dam_type` VARCHAR(32) COMMENT '主坝类型',
	`migranted_population` DECIMAL(10,2) COMMENT '迁移人口',
	`static_total_investment` DECIMAL(10,2) COMMENT '静态总投资',
	`construction_time` TIMESTAMP DEFAULT '0000-00-00 00:00:00' COMMENT '建设开始时间',
	`construction_end_time` TIMESTAMP DEFAULT '0000-00-00 00:00:00' COMMENT '建设结束时间',
	`production_time` TIMESTAMP DEFAULT '0000-00-00 00:00:00' COMMENT '投产时间',
	`completion_acceptance_time` TIMESTAMP DEFAULT '0000-00-00 00:00:00' COMMENT '竣工验收时间',
	`compile_time` TIMESTAMP DEFAULT '0000-00-00 00:00:00' COMMENT '编制时间',
	`compile_unit` VARCHAR(32) COMMENT '编制单位',
	`compile_legal_person` VARCHAR(32) COMMENT '项目法人',
	`contact` VARCHAR(32) COMMENT '联系人',
	`tel` VARCHAR(32) COMMENT '联系电话',
	`use_warrant_number` VARCHAR(32) COMMENT '使用权证编号',
	`certificate_number` VARCHAR(32) COMMENT '证书编号',
	`registration_number` VARCHAR(32) COMMENT '水库大坝登记注册号码',
	`safety_appraisal_type` VARCHAR(32)COMMENT '安全鉴定情况(类别)',
	`safety_appraisal_time` VARCHAR(32) COMMENT '安全鉴定情况(时间)',
	`safety_production_level` VARCHAR(32) COMMENT '安全生产建设(等级)',
	`safety_production_time` VARCHAR(32) COMMENT '安全生产建设(时间)',
	`safety_production_production_subject` VARCHAR(32) COMMENT '安全生产生产主体',
	`safety_production_supervising_subject` VARCHAR(32) COMMENT '安全生产监管主体',
	`green_small_hydropower_construction` VARCHAR(32) COMMENT '绿色小水电建设',
	`data_source` VARCHAR(32) COMMENT '资料来源',
	`fill_form_unit` VARCHAR(32) COMMENT '填报单位',
	`fill_form_person` VARCHAR(32) COMMENT '填报人员',
	`fill_form_person_tel` VARCHAR(32) COMMENT '填报人员电话',
	`fill_form_time` TIMESTAMP DEFAULT '0000-00-00 00:00:00' COMMENT '填报时间',
	`marks` VARCHAR(255) COMMENT '备注',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`plant_id`),
	KEY `index_plant_code` (`plant_code`)
	) ENGINE=INNODB DEFAULT CHARSET=utf8
SHOW VARIABLES LIKE 'sql_mode';
SET SESSION sql_mode='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';