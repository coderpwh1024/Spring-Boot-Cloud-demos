package com.why.MyProject.Controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/***
 *
 */

public class Main {

    public static void main(String[] args) throws IOException {
        String[] head = {"姓名", "电话", "地址", "注册日期", "备注"};
        ByteArrayOutputStream byteArrayOutputStream = exportToExcel(head, 100);
        File fileToSave = new File("1.xls");
        assert byteArrayOutputStream != null;
        FileCopyUtils.copy(byteArrayOutputStream.toByteArray(), fileToSave);
    }


    /**
     *
     * @param head
     * @param maxLength
     * @return if (head[j].equals("注册日期")) {
    cell = row.createCell(j);
    cell.setCellValue(String.valueOf((char) ((Math.random() + 1) * 10000000000L)));
    }
     * @throws IOException
     */
    private static ByteArrayOutputStream exportToExcel(String[] head, int maxLength) throws IOException {


        if (head == null || head.length < 1) {
            System.err.println("exportToExcel:head list is 0");
            return null;
        }

        int rowIndex = 0;

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(0);


        for (int i = 0; i < head.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(head[i]);
        }

        for (int i = 0; i < maxLength; i++) {
            rowIndex++;
            row = sheet.createRow(rowIndex);
            for (int j = 0; j < head.length; j++) {
                if (head[j].equals("姓名")) {
                    cell = row.createCell(j);
                    cell.setCellValue(String.valueOf((char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)))));
                }
                if (head[j].equals("电话")) {
                    cell = row.createCell(j);
                    cell.setCellValue(String.valueOf((long) ((Math.random() + 1) * 10000000000L)));
                }
                if (head[j].equals("地址")) {
                    cell = row.createCell(j);
                    cell.setCellValue(String.valueOf((char) ((Math.random() + 1) * 10000000000L)));
                }
                if (head[j].equals("注册日期")) {
                    cell = row.createCell(j);
                    cell.setCellValue(String.valueOf((char) ((Math.random() + 1) * 10000000000L)));
                }
                if (head[j].equals("备注")) {
                    cell = row.createCell(j);
                    cell.setCellValue(String.valueOf((char) ((Math.random() + 1) * 10000000000L)));
                }



            }
        }


        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);

        return os;


    }


}