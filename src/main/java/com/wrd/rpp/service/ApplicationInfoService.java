package com.wrd.rpp.service;

import com.wrd.rpp.dataobject.ApplicationInfo;
import com.wrd.rpp.dataobject.PowerPlantBaseInfoUpload;

import java.util.List;

public interface ApplicationInfoService {
    List<ApplicationInfo> findByRegionCodeInAndStatusIs(List<String> regionCode, byte status);
}
