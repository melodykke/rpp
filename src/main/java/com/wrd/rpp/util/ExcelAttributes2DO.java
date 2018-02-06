package com.wrd.rpp.util;

import com.wrd.rpp.dataobject.PowerPlantBaseInfo;
import com.wrd.rpp.dataobject.PowerPlantGeneratingEquipment;
import com.wrd.rpp.dataobject.PowerPlantLocationInfo;
import com.wrd.rpp.dataobject.PowerPlantPowerInfo;
import com.wrd.rpp.dto.PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO;
import com.wrd.rpp.enums.SysEnum;
import com.wrd.rpp.exception.SysException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelAttributes2DO {

    /**
     * 将Excel元组的Cell中的值收集并装换为PowerPlantBaseInfo对象
     *
     * @param attributes
     * @return
     */
    public static PowerPlantBaseInfo convert2PowerPlantBaseInfo(List<String> attributes) {
        if (attributes.size() < 8) {
            log.error("【属性转换】 获得的 PowerPlantBaseInfo List attributes = {}, 与PowerPlantBaseInfo所要求的数量不符合（要求8个）", attributes);
            throw new SysException(SysEnum.CONVERT_ERROR);
        }
        PowerPlantBaseInfo powerPlantBaseInfo = new PowerPlantBaseInfo();
        powerPlantBaseInfo.setPlantId(KeyUtil.genUniqueKey());
        powerPlantBaseInfo.setPlantCode(attributes.get(0));
        powerPlantBaseInfo.setPlantName(attributes.get(1));
        powerPlantBaseInfo.setPlantNature(attributes.get(2));
        powerPlantBaseInfo.setPlantStatus(attributes.get(3));
        powerPlantBaseInfo.setPlantRiverLocation(attributes.get(4));
        powerPlantBaseInfo.setPlantAffiliation(attributes.get(5));
        powerPlantBaseInfo.setWaterDepartmentStockShare(new BigDecimal(attributes.get(6).equals("") ? String.valueOf(BigInteger.ZERO) : attributes.get(6)));
        powerPlantBaseInfo.setPlantStockCharacter(attributes.get(7));
        return powerPlantBaseInfo;
    }

    /**
     * 将Excel元组的Cell中的值收集并装换为PowerPlantPowerInfo对象
     *
     * @param attributes
     * @return
     */
    public static PowerPlantPowerInfo convert2PowerPlantPowerInfo(List<String> attributes) {
        if (attributes.size() < 13) {
            log.error("【属性转换】 获得的 PowerPlantPowerInfo List attributes = {}, 与PowerPlantPowerInfo所要求的数量不符合（要求13个）", attributes);
            throw new SysException(SysEnum.CONVERT_ERROR);
        }
        PowerPlantPowerInfo powerPlantPowerInfo = new PowerPlantPowerInfo();
        powerPlantPowerInfo.setPlantId(KeyUtil.genUniqueKey());
        powerPlantPowerInfo.setPlantCode(attributes.get(0));
        powerPlantPowerInfo.setPlantName(attributes.get(1));
        powerPlantPowerInfo.setAdministrativeDivision(attributes.get(2));
        powerPlantPowerInfo.setEquipmentTotalPower(new BigDecimal(attributes.get(3).equals("") ? String.valueOf(BigInteger.ZERO) : attributes.get(3)));
        powerPlantPowerInfo.setTotalPowerPerYear(new BigDecimal(attributes.get(4).equals("") ? String.valueOf(BigInteger.ZERO) : attributes.get(4)));
        powerPlantPowerInfo.setPlantPowerConsumption(new BigDecimal(attributes.get(5).equals("") ? String.valueOf(BigInteger.ZERO) : attributes.get(5)));
        powerPlantPowerInfo.setPlantPowerConsumptionRate(new BigDecimal(attributes.get(6).equals("") ? String.valueOf(BigInteger.ZERO) : attributes.get(6)));
        powerPlantPowerInfo.setQuantityToNationalGrid(new BigDecimal(attributes.get(7).equals("") ? String.valueOf(BigInteger.ZERO) : attributes.get(7)));
        powerPlantPowerInfo.setPriceToNationalGrid(new BigDecimal(attributes.get(8).equals("") ? String.valueOf(BigInteger.ZERO) : attributes.get(8)));
        powerPlantPowerInfo.setQuantityToLocalGrid(new BigDecimal(attributes.get(9).equals("") ? String.valueOf(BigInteger.ZERO) : attributes.get(9)));
        powerPlantPowerInfo.setPriceToLocalGrid(new BigDecimal(attributes.get(10).equals("") ? String.valueOf(BigInteger.ZERO) : attributes.get(10)));
        powerPlantPowerInfo.setQuantityToRuralGrid(new BigDecimal(attributes.get(11).equals("") ? String.valueOf(BigInteger.ZERO) : attributes.get(11)));
        powerPlantPowerInfo.setPriceToRuralGrid(new BigDecimal(attributes.get(12).equals("") ? String.valueOf(BigInteger.ZERO) : attributes.get(12)));
        return powerPlantPowerInfo;
    }

    /**
     * 将Excel元组的Cell中的值收集并装换为PowerPlantLocationInfo 和 PowerPlantGeneratingEquipment对象
     *
     * @param attributes
     * @return
     */
    public static PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO convert2PowerPlantLocationInfoAndPowerPlantGeneratingEquipment(List<String> attributes) {
        if (attributes.size() < 26) {
            log.error("【属性转换】 获得的 PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO List " +
                    "attributes = {}, 与PowerPlantLocationInfoAndPowerPlantGeneratingEquipment所要求的数量" +
                    "不符合（要求26个）", attributes);
            throw new SysException(SysEnum.CONVERT_ERROR);
        }
        PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO =
                new PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO();
        List<PowerPlantGeneratingEquipment> powerPlantGeneratingEquipmentList = new ArrayList<>();
        powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO.setPlantId(KeyUtil.genUniqueKey());
        powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO.setPlantCode(attributes.get(0));
        powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO.setPlantName(attributes.get(1));
        powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO.setPlantNature(attributes.get(2));
        powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO.setPlantVillageAddress(attributes.get(3));
        powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO.setPlantTownAddress(attributes.get(4));
        powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO.setPlantAffiliation(attributes.get(5));
        powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO.setGeneralIncome(new BigDecimal(attributes.get(6).equals("") ? String.valueOf(BigInteger.ZERO) : attributes.get(6)));
        powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO.setTaxes(new BigDecimal(attributes.get(7).equals("") ? String.valueOf(BigInteger.ZERO) : attributes.get(7)));
        powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO.setFixedAssets(new BigDecimal(attributes.get(8).equals("") ? String.valueOf(BigInteger.ZERO) : attributes.get(8)));
        powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO.setStorage(new BigDecimal(attributes.get(9).equals("") ? String.valueOf(BigInteger.ZERO) : attributes.get(9)));

        for(int i = 10; i < attributes.size(); i++){
            if(i+2<attributes.size() && !attributes.get(i).equals("") && !attributes.get(i+2).equals("") && attributes.get(i+1).equals("*")){
                PowerPlantGeneratingEquipment powerPlantGeneratingEquipment = new PowerPlantGeneratingEquipment();
                powerPlantGeneratingEquipment.setPlantCode(attributes.get(0));
                powerPlantGeneratingEquipment.setEquipmentQuantity(new Integer(attributes.get(i)));
                powerPlantGeneratingEquipment.setEquipmentPower(new Integer(attributes.get(i+2)));
                powerPlantGeneratingEquipmentList.add(powerPlantGeneratingEquipment);
            }
        }
        powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO.setPowerPlantGeneratingEquipmentList(powerPlantGeneratingEquipmentList);
        return powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO;
    }
}

