package com.wrd.rpp.service;

import com.wrd.rpp.dataobject.*;
import com.wrd.rpp.dto.PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO;
import com.wrd.rpp.form.PowerPlantBaseInfoForm;
import com.wrd.rpp.msg.SysMsg;
import java.util.List;

/**
 * 电站信息维护
 */
public interface PowerPlantService {
    //存储电站基本情况表
    public SysMsg savePowerPlantBaseInfoList(List<PowerPlantBaseInfo> powerPlantBaseInfoList);
    //存储电站电力情况表
    public SysMsg savePowerPlantPowerInfoList(List<PowerPlantPowerInfo> powerPlantPowerInfoList);
    //存储电站位置和装备情况表
    public SysMsg savePowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTOList(
            List<PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO> powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTOList);
    //提交电站基础信息上报信息
    public SysMsg savePowerPlantBaseInfoUpload(PowerPlantBaseInfoForm powerPlantBaseInfoForm);
    //地区代码获取地区
    public Region findByRegionCode(String code);
}
