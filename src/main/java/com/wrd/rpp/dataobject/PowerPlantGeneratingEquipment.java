package com.wrd.rpp.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
public class PowerPlantGeneratingEquipment {
    @Id
    @GeneratedValue
    private Integer equipmentId;
    private String plantCode;
    private Integer equipmentQuantity;
    private Integer equipmentPower;
    private Date createTime;
    private Date updateTime;
}
