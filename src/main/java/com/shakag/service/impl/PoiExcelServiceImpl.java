package com.shakag.service.impl;

import com.shakag.service.PoiExcelService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class PoiExcelServiceImpl implements PoiExcelService {
    @Override
    public void read() throws Exception {
        Workbook workbook = WorkbookFactory.create(new File("C:\\poi.xlsx"));
        Sheet sheet = workbook.getSheet("CreateSheet");
        Cell cell = sheet.getRow(1).getCell(2);
        //最后一行坐标
        int lastRowNum = sheet.getLastRowNum();
        //总行数
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        //总列数
        int physicalNumberOfCells = sheet.getRow(0).getPhysicalNumberOfCells();
    }

    @Override
    public void write() throws Exception{
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet("CreateSheet");

        //样式
        sheet.setColumnWidth(2,10000); //设置列宽
        //sheet.trackAllColumnsForAutoSizing(); // 跟踪所有用于自动调整大小的列，性能会下降不止一倍
        //sheet.autoSizeColumn(2); //自适应列宽，性能会下降不止一倍
        CellStyle cellStyle = getCellStyle(workbook);

        //第一行
        String[] titles = {"ID","姓名","年龄"};
        Row row1 = sheet.createRow(0);
        row1.setHeight((short) 500); //设置行高
        for (int i = 0; i < titles.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(titles[i]);
        }

        //第二行
        Row row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("1");
        row2.createCell(1).setCellValue("shakag");
        row2.createCell(2).setCellValue("23");

        FileOutputStream fileOutputStream = new FileOutputStream("poi.xlsx");
        workbook.write(fileOutputStream);
    }

    /**
     * 单元格样式——首行
     * @param workbook 表对象
     * @return 样式
     */
    public static CellStyle getCellStyle(SXSSFWorkbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();

        //表格样式
        cellStyle.setAlignment(HorizontalAlignment.CENTER); //横向居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); //竖向居中

        //颜色
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());

        //设置边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);

        //字体样式
        Font font = workbook.createFont();
        //font.setBold(true); //加粗
        font.setColor(Font.COLOR_NORMAL);//黑色
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 12); //字体大小
        cellStyle.setFont(font);
        return cellStyle;
    }
}
