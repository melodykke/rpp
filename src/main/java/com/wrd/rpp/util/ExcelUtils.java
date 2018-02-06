package com.wrd.rpp.util;

import com.wrd.rpp.dataobject.PowerPlantBaseInfo;
import com.wrd.rpp.dataobject.PowerPlantLocationInfo;
import com.wrd.rpp.dataobject.PowerPlantPowerInfo;
import com.wrd.rpp.dto.PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO;
import com.wrd.rpp.enums.SysEnum;
import com.wrd.rpp.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelUtils {

    /**
     * 电站基本表 信息元组的封装
     * 将包含有属性List的List具体封装成对应的DO对象 然后返回。
     * @param attributesList
     * @return
     */
    public static List<PowerPlantBaseInfo> processPowerPlantBaseInfo(List<List<String>> attributesList){
        List<PowerPlantBaseInfo> powerPlantBaseInfoList = new ArrayList<PowerPlantBaseInfo>();
        for(List<String> attributes : attributesList) {
            PowerPlantBaseInfo powerPlantBaseInfo = ExcelAttributes2DO.convert2PowerPlantBaseInfo(attributes);
            powerPlantBaseInfoList.add(powerPlantBaseInfo);
        }
        return powerPlantBaseInfoList;
    }


    /**
     * 电站电量表 信息元组的封装
     * 将包含有属性List的List具体封装成对应的DO对象 然后返回。
     * @param attributesList
     * @return
     */
    public static List<PowerPlantPowerInfo> processPowerPlantPowerInfo(List<List<String>> attributesList){
        List<PowerPlantPowerInfo> powerPlantPowerInfoList = new ArrayList<PowerPlantPowerInfo>();
        for(List<String> attributes : attributesList) {
            PowerPlantPowerInfo powerPlantBaseInfo = ExcelAttributes2DO.convert2PowerPlantPowerInfo(attributes);
            powerPlantPowerInfoList.add(powerPlantBaseInfo);
        }
        return powerPlantPowerInfoList;
    }

    /**
     * 电站位置和装备表 信息元组的封装
     * 将包含有属性List的List具体封装成对应的DO对象 然后返回。
     * @param attributesList
     * @return
     */
    public static List<PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO> processPowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO(List<List<String>> attributesList){
        List<PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO> powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTOList =
                new ArrayList<PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO>();
        for(List<String> attributes : attributesList) {
            PowerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO
                    = ExcelAttributes2DO.convert2PowerPlantLocationInfoAndPowerPlantGeneratingEquipment(attributes);
            powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTOList.add(powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTO);
        }
        System.out.println(powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTOList);
        return powerPlantLocationInfoAndPowerPlantGeneratingEquipmentDTOList;
    }











    /**
     * 处理前端传来file（Excel）
     * @param multipartFile 前端提交的Excel文件
     * @return 将Excel文件中的内容元组转换为String属性的List
     */
    public static List<List<String>> processExcel(MultipartFile multipartFile){
        List<List<String>> attributesList = new ArrayList<List<String>>();
        XSSFSheet sheet = null;
        File file = new File("E:\\ftp\\rpp\\annual_report\\" + multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(file);
            //Excel文件
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(ResourceUtils.getFile("E:\\ftp\\rpp\\annual_report\\" + multipartFile.getOriginalFilename())));
            //Excel工作表
            sheet = wb.getSheetAt(0);
        } catch (IOException e) {
            log.error("【EXCEL错误】 EXCEL IO错误");
            e.printStackTrace();
        }
        if(sheet.getPhysicalNumberOfRows()<3) {
            log.error("【EXCEL错误】 内容错误，指定EXCEL无内容！");
            throw new SysException(SysEnum.EMPTY_EXCEL);
        }
        //表头那一行
        XSSFRow titleRow = sheet.getRow(0);
        //表头那个单元格
        XSSFCell titleCell = titleRow.getCell(0);
        //获得cell里面的内容
        String title = titleCell.getStringCellValue();
        //获得第二行起，excel正文
        for(int i = 3; i < sheet.getPhysicalNumberOfRows(); i++){
            XSSFRow contentRow = sheet.getRow(i);
            //装每个EXCEL电站基础信息属性的容器
            List<String> attributes = new ArrayList<String>();
            //取出每一行的每一个cell里的值装进容器
            for(int j = 0; j < contentRow.getPhysicalNumberOfCells(); j++){
                //取得改行第j个cell 并将其设置为StringType
                XSSFCell xssfCell = contentRow.getCell(j);
                xssfCell.setCellType(Cell.CELL_TYPE_STRING);
                String cellValue = xssfCell.getStringCellValue();
                if(cellValue!=null){
                    attributes.add(cellValue);
                }else{
                    attributes.add("");
                }
            }
            attributesList.add(attributes);
        }
        return attributesList;
    }
}

