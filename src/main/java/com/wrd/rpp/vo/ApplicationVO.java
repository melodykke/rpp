package com.wrd.rpp.vo;

import lombok.Data;
import java.util.Date;

@Data
public class ApplicationVO {
    private String applicationId;
    private String ppbiuPlantId;
    private String regionCode;
    private String applicationType;
    private Byte status;
    private Date createTime;
    private String marks;
}
