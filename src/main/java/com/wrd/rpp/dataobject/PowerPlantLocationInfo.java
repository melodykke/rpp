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
public class PowerPlantLocationInfo {
    @Id
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

    private Date createTime;
    private Date updateTime;
}
