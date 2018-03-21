package com.wrd.rpp.service.impl;

import com.wrd.rpp.RppApplication;
import com.wrd.rpp.service.PowerPlantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RppApplication.class)
public class PowerPlantServiceImplTest {
    @Autowired
    private PowerPlantService powerPlantService;
    @Test
    public void findApprovalList() throws Exception {
        List<String> regionCodeList = new ArrayList<>();
        regionCodeList.add("520603000000");
        regionCodeList.add("520602000000");
       /* System.out.println(powerPlantService.findApprovalList(regionCodeList));*/
    }

}