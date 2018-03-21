package com.wrd.rpp.repository;

import com.wrd.rpp.dataobject.ApplicationInfo;
import com.wrd.rpp.dataobject.PowerPlantBaseInfoUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationInfoRepository extends JpaRepository<ApplicationInfo, String> {
    List<ApplicationInfo> findByRegionCodeInAndStatusIs(List<String> regionCode, byte status);
}
