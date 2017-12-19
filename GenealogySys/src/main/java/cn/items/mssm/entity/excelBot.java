package cn.items.mssm.entity;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelBot {
	private final static String excel2003L=".xls";
	private final static String excel2007L=".xlsx";
	
	public List<Object> getBankListByExcel(InputStream in,String fileName) throws Exception{
		Workbook work = this.getWorkbook(in, fileName); 
        if(null == work){  
            throw new Exception("����Excel������Ϊ�գ�");  
        }  
        Sheet sheet = null; 
        Cell cell=null;
        Row row = null;  
        
        List<Object> list=new ArrayList<>();
        for (int i = 0; i < work.getNumberOfSheets(); i++){
        	sheet = work.getSheetAt(i);
        	if(sheet==null){continue;}
        	for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
        		row = sheet.getRow(j);
        		if(row!=null){
        			List<Object> li = new ArrayList<Object>();  
                    for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {  
                        cell = row.getCell(y);
                        li.add(this.getCellValue(cell));  
                    }  
                    list.add(li); 
        		}
        	} 
        }
		return list;	
	}
	
	public Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
		 Workbook wb = null;  
	     String fileType = fileName.substring(fileName.lastIndexOf("."));  
	     if(excel2003L.equals(fileType)){  
	         wb = new HSSFWorkbook(inStr);  //2003-  
	     }else if(excel2007L.equals(fileType)){  
	         wb = new XSSFWorkbook(inStr);  //2007+  
	     }else{  
	         throw new Exception("�������ļ���ʽ����");  
	     }  
	     return wb;		 
	 }
	 
	 public Object getCellValue(Cell cell){  
	        Object value = null;  
	        DecimalFormat df = new DecimalFormat("0");  //��ʽ��number String�ַ�  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //���ڸ�ʽ��  
	        DecimalFormat df2 = new DecimalFormat("0.00");  //��ʽ������  
	          
	        switch (cell.getCellType()) {  
	        case Cell.CELL_TYPE_STRING:  
	            value = cell.getRichStringCellValue().getString();  
	            break;  
	        case Cell.CELL_TYPE_NUMERIC:  
	            if("General".equals(cell.getCellStyle().getDataFormatString())){  
	                value = df.format(cell.getNumericCellValue());  
	            }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){  
	                value = sdf.format(cell.getDateCellValue());  
	            }else{  
	                value = df2.format(cell.getNumericCellValue());  
	            }  
	            break;  
	        case Cell.CELL_TYPE_BOOLEAN:  
	            value = cell.getBooleanCellValue();  
	            break;  
	        case Cell.CELL_TYPE_BLANK:  
	            value = "";  
	            break;  
	        default:  
	            break;  
	        }  
	        return value;  
	    }  
}
