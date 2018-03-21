package com.wrd.rpp.repository;

import com.wrd.rpp.dataobject.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    Region findRegionByRegionCode(String regionCode);
}
