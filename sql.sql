

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
	`construction_time` DATETIME COMMENT '建设开始时间',
	`construction_end_time` DATETIME COMMENT '建设结束时间',
	`production_time` DATETIME COMMENT '投产时间',
	`completion_acceptance_time` DATETIME COMMENT '竣工验收时间',
	`compile_time` DATETIME COMMENT '编制时间',
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
	`fill_form_time` DATETIME COMMENT '填报时间',
	`marks` VARCHAR(255) COMMENT '备注',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`plant_id`),
	KEY `index_plant_code` (`plant_code`)
	) ENGINE=INNODB DEFAULT CHARSET=utf8
SHOW VARIABLES LIKE 'sql_mode';
SET SESSION sql_mode='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

CREATE TABLE `region`(
  `region_id` INT NOT NULL AUTO_INCREMENT,
	`region_code` VARCHAR(32) NOT NULL COMMENT '行政区代码',
	`region_name` VARCHAR(32) NOT NULL COMMENT '行政区名',
	PRIMARY KEY (`region_id`)
	) ENGINE=INNODB DEFAULT CHARSET=utf8
INSERT INTO `region` VALUES (NULL, '贵阳市', '520100000000');
INSERT INTO `region` VALUES (NULL, '南明区', '520102000000');
INSERT INTO `region` VALUES (NULL, '云岩区', '520103000000');
INSERT INTO `region` VALUES (NULL, '花溪区', '520111000000');
INSERT INTO `region` VALUES (NULL, '乌当区', '520112000000');
INSERT INTO `region` VALUES (NULL, '白云区', '520113000000');
INSERT INTO `region` VALUES (NULL, '观山湖区', '520115000000');
INSERT INTO `region` VALUES (NULL, '开阳县', '520121000000');
INSERT INTO `region` VALUES (NULL, '息烽县', '520122000000');
INSERT INTO `region` VALUES (NULL, '修文县', '520123000000');
INSERT INTO `region` VALUES (NULL, '清镇市', '520181000000');
INSERT INTO `region` VALUES (NULL, '六盘水市', '520200000000');
INSERT INTO `region` VALUES (NULL, '钟山区', '520201000000');
INSERT INTO `region` VALUES (NULL, '六枝特区', '520203000000');
INSERT INTO `region` VALUES (NULL, '水城县', '520221000000');
INSERT INTO `region` VALUES (NULL, '盘县', '520222000000');
INSERT INTO `region` VALUES (NULL, '遵义市', '520300000000');
INSERT INTO `region` VALUES (NULL, '红花岗区', '520302000000');
INSERT INTO `region` VALUES (NULL, '汇川区', '520303000000');
INSERT INTO `region` VALUES (NULL, '播州区', '520321000000');
INSERT INTO `region` VALUES (NULL, '赤水市', '520381000000');
INSERT INTO `region` VALUES (NULL, '仁怀市', '520382000000');
INSERT INTO `region` VALUES (NULL, '桐梓县', '520322000000');
INSERT INTO `region` VALUES (NULL, '绥阳县', '520323000000');
INSERT INTO `region` VALUES (NULL, '正安县', '520324000000');
INSERT INTO `region` VALUES (NULL, '道真仡佬族苗族自治县', '520325000000');
INSERT INTO `region` VALUES (NULL, '务川仡佬族苗族自治县', '520326000000');
INSERT INTO `region` VALUES (NULL, '凤冈县', '520327000000');
INSERT INTO `region` VALUES (NULL, '湄潭县', '520328000000');
INSERT INTO `region` VALUES (NULL, '余庆县', '520329000000');
INSERT INTO `region` VALUES (NULL, '习水县', '520330000000');

INSERT INTO `region` VALUES (NULL, '安顺市', '520400000000');
INSERT INTO `region` VALUES (NULL, '西秀区', '520402000000');
INSERT INTO `region` VALUES (NULL, '平坝区', '520403000000');
INSERT INTO `region` VALUES (NULL, '普定县', '520422000000');
INSERT INTO `region` VALUES (NULL, '镇宁布依族苗族自治县', '520423000000');
INSERT INTO `region` VALUES (NULL, '关岭布依族苗族自治县', '520424000000');
INSERT INTO `region` VALUES (NULL, '紫云苗族布依族自治县', '520425000000');

INSERT INTO `region` VALUES (NULL, '毕节市', '520500000000');
INSERT INTO `region` VALUES (NULL, '七星关区', '520502000000');
INSERT INTO `region` VALUES (NULL, '大方县', '520521000000');
INSERT INTO `region` VALUES (NULL, '黔西县', '520522000000');
INSERT INTO `region` VALUES (NULL, '金沙县', '520523000000');
INSERT INTO `region` VALUES (NULL, '织金县', '520524000000');
INSERT INTO `region` VALUES (NULL, '纳雍县', '520525000000');
INSERT INTO `region` VALUES (NULL, '威宁彝族回族苗族自治县', '520526000000');
INSERT INTO `region` VALUES (NULL, '赫章县', '520527000000');

INSERT INTO `region` VALUES (NULL, '铜仁市', '520600000000');
INSERT INTO `region` VALUES (NULL, '碧江区', '520602000000');
INSERT INTO `region` VALUES (NULL, '万山区', '520603000000');
INSERT INTO `region` VALUES (NULL, '江口县', '520621000000');
INSERT INTO `region` VALUES (NULL, '玉屏侗族自治县', '520622000000');
INSERT INTO `region` VALUES (NULL, '石阡县', '520623000000');
INSERT INTO `region` VALUES (NULL, '思南县', '520624000000');
INSERT INTO `region` VALUES (NULL, '印江土家族苗族自治县', '520625000000');
INSERT INTO `region` VALUES (NULL, '德江县', '520626000000');
INSERT INTO `region` VALUES (NULL, '沿河土家族自治县', '520627000000');
INSERT INTO `region` VALUES (NULL, '松桃苗族自治县', '520628000000');

INSERT INTO `region` VALUES (NULL, '黔西南布依族苗族自治州', '522300000000');
INSERT INTO `region` VALUES (NULL, '兴义市', '522301000000');
INSERT INTO `region` VALUES (NULL, '兴仁县', '522322000000');
INSERT INTO `region` VALUES (NULL, '普安县', '522323000000');
INSERT INTO `region` VALUES (NULL, '晴隆县', '522324000000');
INSERT INTO `region` VALUES (NULL, '贞丰县', '522325000000');
INSERT INTO `region` VALUES (NULL, '望谟县', '522326000000');
INSERT INTO `region` VALUES (NULL, '册亨县', '522327000000');
INSERT INTO `region` VALUES (NULL, '安龙县', '522328000000');

INSERT INTO `region` VALUES (NULL, '黔东南苗族侗族自治州', '522600000000');
INSERT INTO `region` VALUES (NULL, '凯里市', '522601000000');
INSERT INTO `region` VALUES (NULL, '黄平县', '522622000000');
INSERT INTO `region` VALUES (NULL, '施秉县', '522623000000');
INSERT INTO `region` VALUES (NULL, '三穗县', '522624000000');
INSERT INTO `region` VALUES (NULL, '镇远县', '522625000000');
INSERT INTO `region` VALUES (NULL, '岑巩县', '522626000000');
INSERT INTO `region` VALUES (NULL, '天柱县', '522627000000');
INSERT INTO `region` VALUES (NULL, '锦屏县', '522628000000');
INSERT INTO `region` VALUES (NULL, '剑河县', '522629000000');
INSERT INTO `region` VALUES (NULL, '台江县', '522630000000');
INSERT INTO `region` VALUES (NULL, '黎平县', '522631000000');
INSERT INTO `region` VALUES (NULL, '榕江县', '522632000000');
INSERT INTO `region` VALUES (NULL, '从江县', '522633000000');
INSERT INTO `region` VALUES (NULL, '雷山县', '522634000000');
INSERT INTO `region` VALUES (NULL, '麻江县', '522635000000');
INSERT INTO `region` VALUES (NULL, '丹寨县', '522636000000');

INSERT INTO `region` VALUES (NULL, '黔南布依族苗族自治州', '522700000000');
INSERT INTO `region` VALUES (NULL, '都匀市', '522701000000');
INSERT INTO `region` VALUES (NULL, '福泉市', '522702000000');
INSERT INTO `region` VALUES (NULL, '荔波县', '522722000000');
INSERT INTO `region` VALUES (NULL, '贵定县', '522723000000');
INSERT INTO `region` VALUES (NULL, '瓮安县', '522725000000');
INSERT INTO `region` VALUES (NULL, '独山县', '522726000000');
INSERT INTO `region` VALUES (NULL, '平塘县', '522727000000	');
INSERT INTO `region` VALUES (NULL, '罗甸县', '522728000000');
INSERT INTO `region` VALUES (NULL, '长顺县', '522729000000');
INSERT INTO `region` VALUES (NULL, '龙里县', '522730000000');
INSERT INTO `region` VALUES (NULL, '惠水县', '522731000000	');
INSERT INTO `region` VALUES (NULL, '三都水族自治县', '522732000000');
