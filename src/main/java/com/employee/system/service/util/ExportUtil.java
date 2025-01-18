package com.employee.system.service.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExportUtil {

    private ExportUtil() {
        throw new IllegalStateException("Cannot create instance of utility class");
    }

    public static void addMetadata(Workbook workbook, Sheet sheet, int headersCount, String title) {
        CellStyle metadataStyle = workbook.createCellStyle();
        CellStyle brandStyle = workbook.createCellStyle();
        Font metadataFont = workbook.createFont();
        metadataFont.setColor(IndexedColors.DARK_TEAL.index);
        metadataFont.setBold(true);
        Font brandFont = workbook.createFont();
        brandFont.setFontHeightInPoints((short) 18);
        metadataStyle.setFont(metadataFont);
        metadataStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        metadataStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.index);
        brandStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        brandStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.index);
        brandStyle.setFont(brandFont);
        sheet.createRow(0).createCell(0).setCellStyle(metadataStyle);
        Row row1 = sheet.createRow(1);
        Cell brandCell = row1.createCell(0);
        brandCell.setCellValue("Employees Report");
        brandCell.setCellStyle(brandStyle);
        Row row2 = sheet.createRow(2);
        Cell titleCell = row2.createCell(0);
        titleCell.setCellValue(title);
        titleCell.setCellStyle(metadataStyle);
        Row row3 = sheet.createRow(3);
        Cell dateCell = row3.createCell(0);
        dateCell.setCellValue("Exported at " + new SimpleDateFormat("dd-MM-yyyy -- HH:mm").format(new Date()));
        dateCell.setCellStyle(metadataStyle);
        sheet.createRow(4).createCell(0).setCellStyle(metadataStyle);
        if(headersCount!=1) {
            for (int i = 0; i < 5; i++) {
                sheet.addMergedRegion(new CellRangeAddress(i, i, 0, headersCount - 1));
            }
        }
    }

    public static void addHeaders(Workbook workbook, Sheet sheet, String[] headers) {
        Row headerRow = sheet.createRow(5);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_TEAL.index);
        Font headerFont = workbook.createFont();
        headerFont.setColor(IndexedColors.WHITE.index);
        headerStyle.setFont(headerFont);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
    }

}
