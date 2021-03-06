package com.wrd.rpp.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


import java.math.BigDecimal;

@Data
public class PowerPlantBaseInfoForm {
    private String plantCode;
    @NotEmpty(message = "电站名称为空！")
    private String plantName; // 电站名称
    private String administrativeArea; // 行政区
    private String plantRiverLocation; // 河流名称
    private BigDecimal longitude; // 经度
    private BigDecimal latitude; // 纬度
    private String location; // 建设地点
    private String developmentMode; // 开发方式
    private String installedCapacity; //装机规模(台/容量)
    private String designedHydraulicHead; // 设计水头
    private BigDecimal designedPowerPerYear; // 设计年发电量
    private BigDecimal guaranteedOutput; // 保证出力
    private BigDecimal ecoFlow; // 生态流量
    private BigDecimal flowForPower; // 发电引用流量
    private String regulationPerformance; // 调节性能
    private BigDecimal damIntervalLength; // 坝间河道长度
    private BigDecimal averageFlowForYears; // 多年平均流量
    private BigDecimal damContralWatershedArea; // 堤坝控制流域面积
    private BigDecimal normalStorageLevel; // 正常蓄水位
    private BigDecimal deadWaterLevel; // 死水位
    private BigDecimal limitingLevelDuringFloodSeason; // 防洪限制水位
    private BigDecimal totalStorage; // 总库容
    private BigDecimal normalWaterLevelStorage; // 正常蓄水位相应库容
    private String plantStatus; // 建站状态
    private BigDecimal deadStorage; // 死库容
    private BigDecimal adjustStorage; // 调节库容
    private String powerFactoryForm; // 发电厂房形式
    private String integratedUtilization; // 综合利用
    private BigDecimal submergedCultivatedLand; // 水库淹没耕地
    private BigDecimal submergedForest; // 水库淹没林地
    private BigDecimal damHight; // 坝高
    private String mainDamType; // 主坝类型
    private BigDecimal migrantedPopulation; // 迁移人口
    private BigDecimal staticTotalInvestment; // 静态总投资
    private String constructionTime; // 建设开始时间
    private String constructionEndTime; // 建设结束时间
    private String productionTime; // 投产时间
    private String completionAcceptanceTime; // 竣工验收时间
    private String compileTime; // 编制时间
    private String compileUnit; // 编制单位
    private String compileLegalPerson; // 项目法人
    private String contact; // 联系人
    private String tel; // 联系电话
    private String useWarrantNumber; // 使用权证编号
    private String certificateNumber; // 证书编号
    private String registrationNumber; // 水库大坝登记注册号码
    private String safetyAppraisalType; // 安全鉴定情况(类别)
    private String safetyAppraisalTime; // 安全鉴定情况(时间)
    private String safetyProductionLevel; // 安全生产建设(等级)
    private String safetyProductionTime; // 安全生产建设(时间)
    private String safetyProductionProductionSubject; // 安全生产生产主体
    private String safetyProductionSupervisingSubject; // 安全生产监管主体
    private String greenSmallHydropowerConstruction; // 绿色小水电建设
    private String dataSource; // 资料来源
    private String fillFormUnit; // 填报单位
    private String fillFormPerson; // 填报人员
    private String fillFormPersonTel; // 填报人员电话
    private String fillFormTime; // 填报时间
    private String marks; // 备注
}
