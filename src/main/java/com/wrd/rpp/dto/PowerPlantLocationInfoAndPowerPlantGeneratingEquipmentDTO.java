package com.wrd.rpp.dto;

import com.wrd.rpp.dataobject.PowerPlantGeneratingEquipment;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO {
    private String plantId;
    private String plantCode;
    private String plantName;
    private String plantNature;
    private String plantVillageAddress;
    private String plantTownAddress;
    private String plantAffiliation;
    private BigDecimal generalIncome;
    private BigDecimal taxes;
    private BigDecimal fixedAssets;
    private BigDecimal storage;
    private List<PowerPlantGeneratingEquipment> powerPlantGeneratingEquipmentList = new ArrayList<>();
}
