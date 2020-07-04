//package com.health;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//
//public class POITest {
//    @Test
//    public void test1() throws Exception {
//        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("F:\\POI.xlsx")));
//        XSSFSheet sheet = excel.getSheetAt(0);
//        for (Row row : sheet) {
//            for (Cell cell : row) {
//                String stringCellValue = cell.getStringCellValue();
//                System.out.println(stringCellValue);
//            }
//        }
//        excel.close();
//
//    }
//
//    @Test
//    public void test2() throws Exception {
//        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("F:\\POI.xlsx")));
//        XSSFSheet sheet = excel.getSheetAt(0);
//        int lastRowNum = sheet.getLastRowNum();
//        for (int i = 0; i <= lastRowNum; i++) {
//            XSSFRow row = sheet.getRow(i);
//            short lastCellNum = row.getLastCellNum();
//            for (short j = 0; j < lastCellNum; j++) {
//                String stringCellValue = row.getCell(j).getStringCellValue();
//                System.out.println(stringCellValue);
//            }
//        }
//        excel.close();
//    }
//    @Test
//    public void test3() throws Exception{
//       XSSFWorkbook excel = new XSSFWorkbook();
//        XSSFSheet sheet = excel.createSheet("测试页签");
//        XSSFRow title = sheet.createRow(0);
//        title.createCell(0).setCellValue("编号");
//        title.createCell(1).setCellValue("姓名");
//        title.createCell(2).setCellValue("年龄");
//
//        XSSFRow row1 = sheet.createRow(1);
//        row1.createCell(0).setCellValue("1");
//        row1.createCell(1).setCellValue("汤姆");
//        row1.createCell(2).setCellValue("20");
//
//        XSSFRow row2 = sheet.createRow(2);
//        row2.createCell(0).setCellValue("2");
//        row2.createCell(1).setCellValue("杰瑞");
//        row2.createCell(2).setCellValue("22");
//
//        FileOutputStream out = new FileOutputStream("F:\\POI2.xlsx");
//        excel.write(out);
//        out.flush();
//        out.close();
//        excel.close();
//    }
//}
