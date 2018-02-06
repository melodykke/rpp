package com.wrd.rpp.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class PowerPlantPowerInfo {
    @Id
    private String plantId;
    private String plantCode;
    private String plantName;
    private String administrativeDivision;
    private BigDecimal equipmentTotalPower;
    private BigDecimal totalPowerPerYear;
    private BigDecimal plantPowerConsumption;
    private BigDecimal plantPowerConsumptionRate;
    private BigDecimal quantityToNationalGrid;
    private BigDecimal priceToNationalGrid;
    private BigDecimal quantityToLocalGrid;
    private BigDecimal priceToLocalGrid;
    private BigDecimal quantityToRuralGrid;
    private BigDecimal priceToRuralGrid;

    private Date createTime;
    private Date updateTime;
}
