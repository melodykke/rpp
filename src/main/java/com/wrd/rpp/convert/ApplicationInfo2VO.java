package com.wrd.rpp.convert;

import com.wrd.rpp.dataobject.ApplicationInfo;
import com.wrd.rpp.vo.ApplicationVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ApplicationInfo2VO {
    public static ApplicationVO convertObject(ApplicationInfo applicationInfo){
        ApplicationVO applicationVO = new ApplicationVO();
        BeanUtils.copyProperties(applicationInfo, applicationVO);
        return applicationVO;
    }

    public static List<ApplicationVO> convertList(List<ApplicationInfo> applicationInfoList){
        List<ApplicationVO> applicationVOList = new ArrayList<>();
        for(ApplicationInfo applicationInfo : applicationInfoList){
            ApplicationVO applicationVO = new ApplicationVO();
            BeanUtils.copyProperties(applicationInfo, applicationVO);
            applicationVOList.add(applicationVO);
        }
        return applicationVOList;
    }
}
