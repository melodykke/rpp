package com.wrd.rpp.service;

import com.wrd.rpp.dataobject.Region;

public interface RegionService {
    Region findRegionByRegionCode(String regionCode);
}
