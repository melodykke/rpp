package com.wrd.rpp.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class PowerPlantBaseInfo {
    @Id
    private String plantId;
    private String plantCode;
    private String plantName;
    private String plantNature;
    private String plantStatus;
    private String plantRiverLocation;
    private String plantAffiliation;
    private BigDecimal waterDepartmentStockShare;
    private String plantStockCharacter;

    private Date createTime;
    private Date updateTime;

}
