/**
 * LY.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package org.flightythought.smile.admin.common;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * POI导出工具
 * 
 * @author cl47872
 * @version $Id: ReportExportUtil.java, v 0.1 Jun 8, 2019 11:28:58 AM cl47872 Exp $
 */
public class ReportExportUtil {

    /**
     * 通用导出方法
     * 
     * @param title
     * @param headers
     * @param datalist
     * @return
     */
    public static SXSSFWorkbook exportExcelCommon(String title, List<String> headers, List<List<String>> datalist) {
        // 声明一个工作薄  
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        // 生成一个表格  
        Sheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节  
        sheet.setDefaultColumnWidth((short) 18);

        // 产生表格标题行  
        Row titlerow = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = titlerow.createCell(i);
            cell.setCellStyle(generateHeaderStyle(workbook));
            cell.setCellValue(headers.get(i));
        }
        // 遍历集合数据，产生数据行  
        if (datalist != null && datalist.size() != 0) {
            CellStyle cellStyle = generateContentStyle(workbook);
            for (int rindex = 0; rindex < datalist.size(); rindex++) {
                Row row = sheet.createRow(rindex + 1);
                List<String> rowdata = datalist.get(rindex);
                for (int cindex = 0; cindex < rowdata.size(); cindex++) {
                    Cell cell = row.createCell(cindex);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(rowdata.get(cindex) == null ? "" : rowdata.get(cindex));
                }
            }
        }
        return workbook;
    }

    /**
     * 生成表头的样式
     * @param workbook
     * @return
     */
    public static CellStyle generateHeaderStyle(SXSSFWorkbook workbook) {
        // 生成标题样式  
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成标题字体  
        Font font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        return style;
    }

    /**
     * 生成标题的样式
     * @param workbook
     * @return
     */
    public static CellStyle generateTitleStyle(SXSSFWorkbook workbook) {
        // 生成标题样式  
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // 生成标题字体  
        Font font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        return style;
    }

    /**
     * 生成内容样式
     * @param workbook
     * @return
     */
    public static CellStyle generateContentStyle(SXSSFWorkbook workbook) {
        // 生成内容样式  
        CellStyle style1 = workbook.createCellStyle();
        style1.setFillForegroundColor(HSSFColor.WHITE.index);
        style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        DataFormat format = workbook.createDataFormat();
        style1.setDataFormat(format.getFormat("@"));
        // 生成内容字体  
        Font font1 = workbook.createFont();
        font1.setColor(HSSFColor.BLACK.index);
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        style1.setFont(font1);
        return style1;
    }

    /**
     * 生成特定的样式(红色字体)
     * @param workbook
     * @return
     */
    public static HSSFCellStyle generateSpecialStyle(HSSFWorkbook workbook) {
        // 生成内容样式  
        HSSFCellStyle style1 = workbook.createCellStyle();
        style1.setFillForegroundColor(HSSFColor.WHITE.index);
        style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成内容字体  
        HSSFFont font1 = workbook.createFont();
        font1.setColor(HSSFColor.RED.index);
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style1.setFont(font1);
        return style1;
    }

}