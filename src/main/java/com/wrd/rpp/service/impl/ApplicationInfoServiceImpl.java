package com.wrd.rpp.service.impl;

import com.wrd.rpp.dataobject.ApplicationInfo;
import com.wrd.rpp.dataobject.PowerPlantBaseInfoUpload;
import com.wrd.rpp.repository.ApplicationInfoRepository;
import com.wrd.rpp.service.ApplicationInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ApplicationInfoServiceImpl implements ApplicationInfoService {

    @Autowired
    private ApplicationInfoRepository applicationInfoRepository;

    @Override
    public List<ApplicationInfo> findByRegionCodeInAndStatusIs(List<String> regionCode, byte status) {
        return applicationInfoRepository.findByRegionCodeInAndStatusIs(regionCode, status);
    }
}
