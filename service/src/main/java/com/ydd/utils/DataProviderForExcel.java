package com.ydd.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * DataProvider 对excel读取供testng DataProvider使用
 * @author HP
 *
 */
public class DataProviderForExcel {
	private static ArrayList<String>  arrkey = new ArrayList<String>();
	public static Object[][] getTestData(String filepath,String filename,String sheetname)throws IOException, EncryptedDocumentException, InvalidFormatException{
	      File file=new File(filepath+"\\"+filename);
	      FileInputStream inputStream=new FileInputStream(file);
	      Workbook workbook=null;
	      String fileExtensionName=filename.substring(filename.indexOf("."));
	      if(fileExtensionName.equals(".xlsx")){
	    	  workbook=new XSSFWorkbook(inputStream);
	      }
	      else if(fileExtensionName.equals(".xls")){
	    	  workbook=(Workbook) new HSSFWorkbook(inputStream);
	      }
	     Sheet sheet=workbook.getSheet(sheetname);
	     int rows = sheet.getLastRowNum();
	     int columns = sheet.getRow(0).getPhysicalNumberOfCells();
	     @SuppressWarnings("unchecked")
		Map<String, String>[][] arrmap = new HashMap[rows][1];
	     for (int i = 0; i < rows; i++) {
             arrmap[i][0] = new HashMap<>();
         }
	     for (int c = 0; c < columns; c++) {
	            String cellvalue = sheet.getRow(0).getCell(c).getStringCellValue();
	            arrkey.add(cellvalue);
	        }
	     for (int r = 1; r <= rows; r++) {
	            for (int c = 0; c < columns; c++) {
	            	Cell cell = sheet.getRow(r).getCell(c);
	            	String str = "";
	                switch (cell.getCellType()) {
					case 0:
						 str = String.valueOf(cell.getNumericCellValue()).endsWith(".0")?String.valueOf(cell.getNumericCellValue()).replaceAll(".0", ""):String.valueOf(cell.getNumericCellValue());
						break;
					case 1:
						str = cell.getStringCellValue();
						break;
					default:
						break;
					}
	                arrmap[r - 1][0].put(arrkey.get(c), str);
	            }
	            
	        }
		return arrmap;
	    }
	  }
	
