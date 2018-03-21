package com.wrd.rpp.dataobject;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class ApplicationInfo {
    @Id
    private String applicationId;
    private String ppbiuPlantId;
    private String regionCode;
    private String applicationType;
    private Byte status;
    private String marks;
    private Date createTime;
    private Date updateTime;

    public ApplicationInfo() {
    }

    public ApplicationInfo(String applicationId, String ppbiuPlantId, String regionCode, String applicationType, Byte status, String marks) {
        this.applicationId = applicationId;
        this.ppbiuPlantId = ppbiuPlantId;
        this.regionCode = regionCode;
        this.applicationType = applicationType;
        this.status = status;
        this.marks = marks;
    }
}
