package com.wrd.rpp.service.impl;

import com.wrd.rpp.dataobject.PowerPlantBaseInfo;
import com.wrd.rpp.dataobject.PowerPlantGeneratingEquipment;
import com.wrd.rpp.dataobject.PowerPlantLocationInfo;
import com.wrd.rpp.dataobject.PowerPlantPowerInfo;
import com.wrd.rpp.dto.PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO;
import com.wrd.rpp.enums.SysEnum;
import com.wrd.rpp.exception.SysException;
import com.wrd.rpp.msg.SysMsg;
import com.wrd.rpp.repository.PowerPlantBaseInfoRepository;
import com.wrd.rpp.repository.PowerPlantGeneratingEquipmentRepository;
import com.wrd.rpp.repository.PowerPlantLocationInfoRepository;
import com.wrd.rpp.repository.PowerPlantPowerInfoRepository;
import com.wrd.rpp.service.PowerPlantService;
import com.wrd.rpp.util.PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO2PowerPlantLocationInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class PowerPlantServiceImpl implements PowerPlantService {

    @Autowired
    private PowerPlantBaseInfoRepository powerPlantBaseInfoRepository;
    @Autowired
    private PowerPlantPowerInfoRepository powerPlantPowerInfoRepository;
    @Autowired
    private PowerPlantLocationInfoRepository powerPlantLocationInfoRepository;
    @Autowired
    private PowerPlantGeneratingEquipmentRepository powerPlantGeneratingEquipmentRepository;

    @Override //存储电站基本数据
    public SysMsg savePowerPlantBaseInfoList(List<PowerPlantBaseInfo> powerPlantBaseInfoList){
        List<PowerPlantBaseInfo> powerPlantBaseInfoListReturn = powerPlantBaseInfoRepository.save(powerPlantBaseInfoList);
        if(powerPlantBaseInfoListReturn == null){
            this.storeErrorNotice("powerPlantBaseInfoList", powerPlantBaseInfoList);
        }
        return new SysMsg(SysEnum.OPERATION_SUCCESS);
    }

    @Override //存储电站电力数据
    public SysMsg savePowerPlantPowerInfoList(List<PowerPlantPowerInfo> powerPlantPowerInfoList) {
        List<PowerPlantPowerInfo> powerPlantPowerInfoListReturn = powerPlantPowerInfoRepository.save(powerPlantPowerInfoList);
        if(powerPlantPowerInfoListReturn == null){
            this.storeErrorNotice("powerPlantPowerInfoList", powerPlantPowerInfoList);
        }
        return new SysMsg(SysEnum.OPERATION_SUCCESS);
    }

    @Override //存储电站位置数据
    public SysMsg savePowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTOList(List<PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO>
                                                                                                powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTOList) {
        List<PowerPlantLocationInfo> powerPlantLocationInfoTotalList = new ArrayList<>();
        List<PowerPlantGeneratingEquipment> powerPlantGeneratingEquipmentTotalList = new ArrayList<>();

        for (PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO :
                powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTOList) {
            PowerPlantLocationInfo powerPlantLocationInfo = PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO2PowerPlantLocationInfoUtil
                    .convert(powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO);
/*            PowerPlantGeneratingEquipment powerPlantGeneratingEquipment = PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO2PowerPlantGeneratingEquipmentUtil
                    .convert(powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO);*/
            List<PowerPlantGeneratingEquipment> powerPlantGeneratingEquipmentList = powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO.getPowerPlantGeneratingEquipmentList();
            powerPlantLocationInfoTotalList.add(powerPlantLocationInfo);
            if(powerPlantGeneratingEquipmentList != null){
                for(PowerPlantGeneratingEquipment powerPlantGeneratingEquipment : powerPlantGeneratingEquipmentList){
                    powerPlantGeneratingEquipmentTotalList.add(powerPlantGeneratingEquipment);
                }
            }
        }
        System.out.println("----------------");
        List<PowerPlantLocationInfo> powerPlantLocationInfoListReturn = powerPlantLocationInfoRepository.save(powerPlantLocationInfoTotalList);
        List<PowerPlantGeneratingEquipment> powerPlantGeneratingEquipmentListReturn = powerPlantGeneratingEquipmentRepository.save(powerPlantGeneratingEquipmentTotalList);
        if (powerPlantLocationInfoListReturn == null || powerPlantGeneratingEquipmentListReturn == null) {
            this.storeErrorNotice("powerPlantLocationInfoList 和 powerPlantGeneratingEquipmentList"
                    , powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTOList);
        }
        return new SysMsg(SysEnum.OPERATION_SUCCESS);
    }


    //出现储存错误时调用
    private void storeErrorNotice(String containerName, List list) {
        log.error("【数据存储】 数据存储错误， {} = {}", containerName, list);
        throw new SysException(SysEnum.DATA_STORE_ERROR);
    }
}
