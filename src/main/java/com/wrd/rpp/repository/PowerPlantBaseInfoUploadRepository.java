package com.wrd.rpp.repository;

import com.wrd.rpp.dataobject.PowerPlantBaseInfoUpload;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PowerPlantBaseInfoUploadRepository extends JpaRepository<PowerPlantBaseInfoUpload, String>, JpaSpecificationExecutor<PowerPlantBaseInfoUpload>{
    PowerPlantBaseInfoUpload findOneByPlantName(String plantName);
}
