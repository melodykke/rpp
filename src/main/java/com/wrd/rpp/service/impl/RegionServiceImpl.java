package com.wrd.rpp.service.impl;

import com.wrd.rpp.dataobject.Region;
import com.wrd.rpp.repository.RegionRepository;
import com.wrd.rpp.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionRepository regionRepository;

    @Override
    public Region findRegionByRegionCode(String regionCode) {
        return regionRepository.findRegionByRegionCode(regionCode);
    }
}
