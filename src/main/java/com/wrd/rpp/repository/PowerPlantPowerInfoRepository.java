package com.wrd.rpp.repository;

import com.wrd.rpp.dataobject.PowerPlantPowerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PowerPlantPowerInfoRepository extends JpaRepository<PowerPlantPowerInfo, String> {
}
