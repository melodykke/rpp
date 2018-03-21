package com.wrd.rpp.controller;

import com.wrd.rpp.dataobject.ApplicationInfo;
import com.wrd.rpp.dataobject.PowerPlantBaseInfoUpload;
import com.wrd.rpp.dto.PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO;
import com.wrd.rpp.enums.ApplicationTypeEnum;
import com.wrd.rpp.enums.SysEnum;
import com.wrd.rpp.exception.SysException;
import com.wrd.rpp.form.PowerPlantBaseInfoForm;
import com.wrd.rpp.msg.SysMsg;
import com.wrd.rpp.service.PowerPlantService;
import com.wrd.rpp.service.WebSocket;
import com.wrd.rpp.shiro.bean.UserInfo;
import com.wrd.rpp.util.ExcelUtils;
import com.wrd.rpp.util.KeyUtil;
import com.wrd.rpp.util.ResultUtil;
import com.wrd.rpp.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/power-plant")
@Slf4j
public class PowerPlantController {

    @Autowired
    private PowerPlantService powerPlantService;
    @Autowired
    private WebSocket webSocket;

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

    /**
     * 电站基础信息页面
     * @return
     */
    @GetMapping("/power-plant-base-info-upload")
    @RequiresRoles("county")
    public ModelAndView powerPlantBaseInfoUpload(){
        ModelAndView modelAndView = new ModelAndView("power-plant-base-info-upload");
        return modelAndView;
    }

    /**
     * 电站基础信息录入
     * @param powerPlantBaseInfoForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/power-plant-base-info-upload")
    @RequiresRoles("county")
    public ResultVO<PowerPlantBaseInfoUpload> powerPlantBaseInfoUpload(@Valid PowerPlantBaseInfoForm powerPlantBaseInfoForm, BindingResult bindingResult, Map<String, Object> map){
        if(bindingResult.hasErrors()){
            log.error("【电站信息】 电站基础信息填报错误， 参数不正确 powerPlantBaseInfoForm = {}， 错误：{}", powerPlantBaseInfoForm, bindingResult.getFieldError().getDefaultMessage());
            throw new SysException(SysEnum.DATA_SUBMIT_FAILED.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        SysMsg sysMsg = powerPlantService.savePowerPlantBaseInfoUpload(powerPlantBaseInfoForm);
        //录入成功后ws向上级提示消息
        webSocket.sendMsg("有新的电站基础信息录入待审批!", (UserInfo)SecurityUtils.getSubject().getPrincipal());
        return ResultUtil.success(sysMsg.getObject());
    }

    /**
     * 电站信息维护页面
     */
    @GetMapping("/power-plant-upload-management")
    public ModelAndView powerPlantUploadManagement(HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView("power-plant-upload-management");
        return modelAndView;
    }
}
