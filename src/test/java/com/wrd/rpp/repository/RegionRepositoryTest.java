package com.wrd.rpp.repository;

import com.wrd.rpp.RppApplication;
import com.wrd.rpp.dataobject.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RppApplication.class)
public class RegionRepositoryTest {
    @Autowired
    private RegionRepository regionRepository;

    @Test
    public void findRegionByRegionCode() throws Exception {
        Region region = regionRepository.findOne(1);
        System.out.println(region);
        //System.out.println(regionRepository.findRegionByRegionCode("520000000000"));
    }

}