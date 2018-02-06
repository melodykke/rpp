package com.wrd.rpp.util;

import com.wrd.rpp.dataobject.PowerPlantLocationInfo;
import com.wrd.rpp.dto.PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

public class PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO2PowerPlantLocationInfoUtil {
    public static PowerPlantLocationInfo convert(PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO){
        PowerPlantLocationInfo powerPlantLocationInfo = new PowerPlantLocationInfo();
        BeanUtils.copyProperties(powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO, powerPlantLocationInfo);
        return powerPlantLocationInfo;
    }
}
