package com.wrd.rpp.repository;

import com.wrd.rpp.RppApplication;
import com.wrd.rpp.dataobject.PowerPlantBaseInfoUpload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RppApplication.class)
public class PowerPlantBaseInfoUploadRepositoryTest {

    @Autowired
    private PowerPlantBaseInfoUploadRepository powerPlantBaseInfoUploadRepository;

/*    @Test
    public void testFindPowerPlantBaseInfoUploadsByRegionCodeIn() throws Exception {
        List<String> regionCodeList = new ArrayList<>();
        regionCodeList.add("520603000000");
        regionCodeList.add("520602000000");
        System.out.println(powerPlantBaseInfoUploadRepository.findPowerPlantBaseInfoUploadsByRegionCodeIn(regionCodeList).size());
    }*/

    @Test
    public void findPowerPlantBaseInfoUploadsByRegionCodeIn() throws Exception {
        List<String> regionCodeList = new ArrayList<>();
        regionCodeList.add("520603000000");
        regionCodeList.add("520602000000");
       /* List<PowerPlantBaseInfoUpload> powerPlantBaseInfoUploadList = powerPlantBaseInfoUploadRepository
              .findPowerPlantBaseInfoUploadsByRegionCodeInAndStatusIs(regionCodeList, (byte)0);
        System.out.println(powerPlantBaseInfoUploadList.size());*/
    }

}