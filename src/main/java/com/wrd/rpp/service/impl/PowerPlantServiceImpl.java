package com.wrd.rpp.service.impl;

import com.wrd.rpp.dataobject.*;
import com.wrd.rpp.dto.PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO;
import com.wrd.rpp.enums.SysEnum;
import com.wrd.rpp.exception.SysException;
import com.wrd.rpp.form.PowerPlantBaseInfoForm;
import com.wrd.rpp.msg.SysMsg;
import com.wrd.rpp.repository.*;
import com.wrd.rpp.service.PowerPlantService;
import com.wrd.rpp.util.KeyUtil;
import com.wrd.rpp.util.PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO2PowerPlantLocationInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    @Autowired
    private PowerPlantBaseInfoUploadRepository powerPlantBaseInfoUploadRepository;
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

    @Override
    public SysMsg savePowerPlantBaseInfoUpload(PowerPlantBaseInfoForm powerPlantBaseInfoForm) {
        if(powerPlantBaseInfoUploadRepository.findOneByPlantName(powerPlantBaseInfoForm.getPlantName()) != null) {
            log.error("【数据存储】 数据存储错误,重复的电站名称，powerPlantBaseInfoForm.plantName = {}", powerPlantBaseInfoForm.getPlantName());
            throw new SysException(SysEnum.DUPLICATED_PLANTNAME);
        }
        PowerPlantBaseInfoUpload powerPlantBaseInfoUpload = new PowerPlantBaseInfoUpload();
        BeanUtils.copyProperties(powerPlantBaseInfoForm, powerPlantBaseInfoUpload);
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            powerPlantBaseInfoUpload.setConstructionTime(powerPlantBaseInfoForm.getConstructionTime() == "" ? null : format.parse(powerPlantBaseInfoForm.getConstructionTime()));
            powerPlantBaseInfoUpload.setConstructionEndTime(powerPlantBaseInfoForm.getConstructionEndTime() == "" ? null : format.parse(powerPlantBaseInfoForm.getConstructionEndTime()));
            powerPlantBaseInfoUpload.setProductionTime(powerPlantBaseInfoForm.getProductionTime() == "" ? null : format.parse(powerPlantBaseInfoForm.getProductionTime()));
            powerPlantBaseInfoUpload.setCompletionAcceptanceTime(powerPlantBaseInfoForm.getCompletionAcceptanceTime() == "" ? null : format.parse(powerPlantBaseInfoForm.getCompletionAcceptanceTime()));
            powerPlantBaseInfoUpload.setCompileTime(powerPlantBaseInfoForm.getCompileTime() == "" ? null : format.parse(powerPlantBaseInfoForm.getCompileTime()));
            powerPlantBaseInfoUpload.setFillFormTime(powerPlantBaseInfoForm.getFillFormTime() == "" ? null : format.parse(powerPlantBaseInfoForm.getFillFormTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        powerPlantBaseInfoUpload.setPlantId(KeyUtil.genUniqueKey());
        PowerPlantBaseInfoUpload powerPlantBaseInfoUploadReturn = powerPlantBaseInfoUploadRepository.save(powerPlantBaseInfoUpload);
        if(powerPlantBaseInfoUploadReturn == null){
            log.error("【数据存储】 数据存储错误，powerPlantBaseInfoUpload = {}", powerPlantBaseInfoUpload);
            throw new SysException(SysEnum.DATA_STORE_ERROR);
        }
        return new SysMsg(SysEnum.OPERATION_SUCCESS, powerPlantBaseInfoUploadReturn);
    }




    //出现储存错误时调用
    private void storeErrorNotice(String containerName, List list) {
        log.error("【数据存储】 数据存储错误， {} = {}", containerName, list);
        throw new SysException(SysEnum.DATA_STORE_ERROR);
    }
}
