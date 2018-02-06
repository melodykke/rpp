package com.wrd.rpp.controller;

import com.wrd.rpp.dataobject.PowerPlantPowerInfo;
import com.wrd.rpp.dto.PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO;
import com.wrd.rpp.enums.SysEnum;
import com.wrd.rpp.exception.SysException;
import com.wrd.rpp.msg.SysMsg;
import com.wrd.rpp.service.PowerPlantService;
import com.wrd.rpp.util.ExcelUtils;
import com.wrd.rpp.util.ResultUtil;
import com.wrd.rpp.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/power-plant")
@Slf4j
public class PowerPlantController {

    @Autowired
    private PowerPlantService powerPlantService;

    @RequestMapping(value = {"/", "index"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("import-annual-report");
        return mv;
    }

    @RequestMapping(value = "error")
    public ModelAndView error(){
        ModelAndView mv = new ModelAndView("error");
        return mv;
    }

    /**
     * 电站基本信息Excel导入
     * @param multipartFile //enctype="multipart/form-data 前端传来的file 封装MultipartFile
     * @return 统一ResultVO
     */
    @PostMapping("/import-plant-base-info-excel")
    public ResultVO importPlantBaseInfoExcel(@RequestParam(value="filename") MultipartFile multipartFile) {
        log.info("Method: import-plant-base-info-excel processing................................");
        SysMsg sysMsg = powerPlantService.savePowerPlantBaseInfoList(ExcelUtils.processPowerPlantBaseInfo(ExcelUtils.processExcel(multipartFile)));
        if(!sysMsg.getCode().equals(SysEnum.OPERATION_SUCCESS.getCode())){
            log.error("【存储错误】 数据存储错误， PowerPlantController.importAnnualReport");
            throw new SysException(SysEnum.DATA_STORE_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     * 电站电量信息Excel导入（电站表一）
     * @param multipartFile
     * @return
     */
    @PostMapping("/import-power-plant-power-info-excel")
    public ResultVO importPowerPlantPowerInfoExcel(@RequestParam(value="filename") MultipartFile multipartFile) {
        log.info("Method: import-power-plant-power-info-excel processing................................filename = {}", multipartFile.getOriginalFilename());
        SysMsg sysMsg = powerPlantService.savePowerPlantPowerInfoList(ExcelUtils.processPowerPlantPowerInfo(ExcelUtils.processExcel(multipartFile)));
        if(!sysMsg.getCode().equals(SysEnum.OPERATION_SUCCESS.getCode())){
            log.error("【存储错误】 数据存储错误， PowerPlantController.importAnnualReport");
            throw new SysException(SysEnum.DATA_STORE_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     * 电站位置信息Excel导入（电站表二）
     * @param multipartFile
     * @return
     */
    @PostMapping("/import-power-plant-location-and-equipment-info-excel")
    public ResultVO importPowerPlantLocationInfoExcel(@RequestParam(value="filename") MultipartFile multipartFile) {
        log.info("Method: import-power-plant-location-info-excel processing................................filename = {}", multipartFile.getOriginalFilename());
        List<PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO> powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTOList =
                ExcelUtils.processPowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO(ExcelUtils.processExcel(multipartFile));
        SysMsg sysMsg = powerPlantService.savePowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTOList(powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTOList);
        if(!sysMsg.getCode().equals(SysEnum.OPERATION_SUCCESS.getCode())){
            log.error("【存储错误】 数据存储错误， PowerPlantController.importAnnualReport");
            throw new SysException(SysEnum.DATA_STORE_ERROR);
        }
        return ResultUtil.success();
    }

}
