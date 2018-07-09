package com.fengqiao.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStream;  
import java.text.DecimalFormat;  
import java.text.SimpleDateFormat;  
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFDateUtil;  
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.xssf.usermodel.XSSFCell;  
import org.apache.poi.xssf.usermodel.XSSFRow;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;


public class ExcelUtil {  
    //默认单元格内容为数字时格式  
    static DecimalFormat df = new DecimalFormat("0");  
    // 默认单元格格式化日期字符串   
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
    // 格式化数字  
    static DecimalFormat nf = new DecimalFormat("0");    
    public static ArrayList<ArrayList<Object>> readExcel(File file){  
        if(file == null){  
            return null;  
        }  
        if(file.getName().endsWith("xlsx")){  
            //处理ecxel2007  
            return readExcel2007(file);  
        }else{  
            //处理ecxel2003  
            return readExcel2003(file);  
        }  
    }  
 
    //2003版本excel
    public static ArrayList<ArrayList<Object>> readExcel2003(File file){  
        try{  
            ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();  
            ArrayList<Object> colList;  
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));  
            HSSFSheet sheet = wb.getSheetAt(0);  
            HSSFRow row;  
            HSSFCell cell;  
            Object value;  
            for(int i = sheet.getFirstRowNum() , rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows() ; i++ ){  
                row = sheet.getRow(i);  
                colList = new ArrayList<Object>();  
                if(row == null){  
                    //当读取行为空时  
                    if(i != sheet.getPhysicalNumberOfRows()){//判断是否是最后一行  
                        rowList.add(colList);  
                    }  
                    continue;  
                }else{  
                    rowCount++;  
                }  
                for( int j = row.getFirstCellNum() ; j <= row.getLastCellNum() ;j++){  
                    cell = row.getCell(j);  
                    if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){  
                        //当该单元格为空  
                        if(j != row.getLastCellNum()){//判断是否是该行中最后一个单元格  
                            colList.add("");  
                        }  
                        continue;  
                    }  
                    switch(cell.getCellType()){  
                     case XSSFCell.CELL_TYPE_STRING:    
                            //System.out.println(i + "行" + j + " 列 is String type");    
                            value = cell.getStringCellValue();    
                            break;    
                        case XSSFCell.CELL_TYPE_NUMERIC:    
                            if ("@".equals(cell.getCellStyle().getDataFormatString())) {    
                                value = df.format(cell.getNumericCellValue());    
                            } else if ("General".equals(cell.getCellStyle()    
                                    .getDataFormatString())) {    
                                value = nf.format(cell.getNumericCellValue());    
                            } else {    
                                value = sdf.format(HSSFDateUtil.getJavaDate(cell    
                                        .getNumericCellValue()));    
                            }    
                            //System.out.println(i+"行"+j+" 列 is Number type ; DateFormt:"+ value.toString());   
                            break;    
                        case XSSFCell.CELL_TYPE_BOOLEAN:    
                            //System.out.println(i + "行" + j + " 列 is Boolean type");    
                            value = Boolean.valueOf(cell.getBooleanCellValue());  
                            break;    
                        case XSSFCell.CELL_TYPE_BLANK:    
                            //System.out.println(i + "行" + j + " 列 is Blank type");    
                            value = "";    
                            break;    
                        default:    
                            //System.out.println(i + "行" + j + " 列 is default type");    
                            value = cell.toString();    
                    }// end switch  
                    colList.add(value);  
                }//end for j  
                rowList.add(colList);  
            }//end for i  
              
            return rowList;  
        }catch(Exception e){  
            return null;  
        }  
    }  
      
    //2007版本excel
    public static ArrayList<ArrayList<Object>> readExcel2007(File file){  
        try{  
            ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();  
            ArrayList<Object> colList;  
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));  
            XSSFSheet sheet = wb.getSheetAt(0);  
            XSSFRow row;  
            XSSFCell cell;  
            Object value;  
            for(int i = sheet.getFirstRowNum() , rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows() ; i++ ){  
                row = sheet.getRow(i);  
                colList = new ArrayList<Object>();  
                if(row == null){  
                    //当读取行为空时  
                    if(i != sheet.getPhysicalNumberOfRows()){//判断是否是最后一行  
                        rowList.add(colList);  
                    }  
                    continue;  
                }else{  
                    rowCount++;  
                }  
                for( int j = row.getFirstCellNum() ; j <= row.getLastCellNum() ;j++){  
                    cell = row.getCell(j);  
                    if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){  
                        //当该单元格为空  
                        if(j != row.getLastCellNum()){//判断是否是该行中最后一个单元格  
                            colList.add("");  
                        }  
                        continue;  
                    }  
                    switch(cell.getCellType()){  
                     case XSSFCell.CELL_TYPE_STRING:    
                            //System.out.println(i + "行" + j + " 列 is String type");    
                            value = cell.getStringCellValue();    
                            break;    
                        case XSSFCell.CELL_TYPE_NUMERIC:    
                            if ("@".equals(cell.getCellStyle().getDataFormatString())) {    
                                value = df.format(cell.getNumericCellValue());    
                            } else if ("General".equals(cell.getCellStyle()    
                                    .getDataFormatString())) {    
                                value = nf.format(cell.getNumericCellValue());    
                            } else {    
                                value = sdf.format(HSSFDateUtil.getJavaDate(cell    
                                        .getNumericCellValue()));    
                            }    
                            //System.out.println(i+"行"+j+" 列 is Number type ; DateFormt:"+value.toString());   
                            break;    
                        case XSSFCell.CELL_TYPE_BOOLEAN:    
                            //System.out.println(i + "行" + j + " 列 is Boolean type");    
                            value = Boolean.valueOf(cell.getBooleanCellValue());  
                            break;    
                        case XSSFCell.CELL_TYPE_BLANK:    
                            //System.out.println(i + "行" + j + " 列 is Blank type");    
                            value = "";    
                            break;    
                        default:    
                            //System.out.println(i + "行" + j + " 列 is default type");    
                            value = cell.toString();    
                    }// end switch  
                    colList.add(value);  
                }//end for j  
                rowList.add(colList);  
            }//end for i  
              
            return rowList;  
        }catch(Exception e){  
            System.out.println("exception");  
            return null;  
        }  
    }  
      
    //指定目录输出
    public static void writeExcel(ArrayList<ArrayList<Object>> result,String path){  
        if(result == null){  
            return;  
        }  
        HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet("sheet1");  
        for(int i = 0 ;i < result.size() ; i++){  
             HSSFRow row = sheet.createRow(i);  
            if(result.get(i) != null){  
                for(int j = 0; j < result.get(i).size() ; j ++){  
                    HSSFCell cell = row.createCell(j);  
                    cell.setCellValue(result.get(i).get(j).toString());  
                }  
            }  
        }  
        ByteArrayOutputStream os = new ByteArrayOutputStream();  
        try  
        {  
            wb.write(os);  
        } catch (IOException e){  
            e.printStackTrace();  
        }  
        byte[] content = os.toByteArray();  
        File file = new File(path);//Excel文件生成后存储的位置。  
        OutputStream fos  = null;  
        try  
        {  
            fos = new FileOutputStream(file);  
            fos.write(content);  
            os.close();  
            fos.close();  
        }catch (Exception e){  
            e.printStackTrace();  
        }             
    }  
      
    public static DecimalFormat getDf() {  
        return df;  
    }  
    public static void setDf(DecimalFormat df) {  
        ExcelUtil.df = df;  
    }  
    public static SimpleDateFormat getSdf() {  
        return sdf;  
    }  
    public static void setSdf(SimpleDateFormat sdf) {  
        ExcelUtil.sdf = sdf;  
    }  
    public static DecimalFormat getNf() {  
        return nf;  
    }  
    public static void setNf(DecimalFormat nf) {  
        ExcelUtil.nf = nf;  
    }  
    
    //导出excel
/*    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException{
		System.out.println("进入下载页面");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String fileName= df.format(new Date());
		String xlsFile = "中文测试"+fileName+".xls"; //产生的Excel文件的名称
		HSSFWorkbook workbook = new HSSFWorkbook(); //产生工作簿对象
		HSSFSheet sheet = workbook.createSheet(); //产生工作表对象,设置第一个工作表的名称为firstSheet//为了工作表能支持中文，设置字符编码为UTF_16
		workbook.setSheetName(0,"firstSheet");//产生一行
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell = row.createCell(0);
		cell.setCellValue("序号");
		cell = row.createCell(1);
		cell.setCellValue("编号");
		cell = row.createCell(2);
		cell.setCellValue("名称");
		String hql = "select u from Alarmtype u";
		String sql = "select * from alarmtype";
		Query query = entityManager.createNativeQuery(sql);
		//Query query = entityManager.createQuery(hql);
	    List<Alarmtype> list = query.getResultList();
		for (int i = 0; i < list.size(); i++) {			
			HSSFRow circle = sheet.createRow(i+1);
			cell = circle.createCell(0);
  			//控制循环行和列
			cell.setCellValue(list.get(i).getAlarmname());
  			cell = circle.createCell(1);
  			cell.setCellValue(list.get(i).getAlarmtypeid());
  			cell = circle.createCell(2);	
		}
		//放到工程文件的根目录下
		//FileOutputStream fOut = new FileOutputStream("e:/"+xlsFile);
			//workbook.write(fOut);
			//fOut.flush();
			//fOut.close();
		//以下语句读取生成的Excel文件内容
		System.out.println(xlsFile);
		//response.setHeader("Content-Disposition","attachment;filename="+xlsFile+"固定资产");//指定下载的文件名
		response.setHeader("Content-Disposition", "attachment;filename="+ new String(xlsFile.getBytes(),"iso-8859-1") + ".xls");  
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		OutputStream output = response.getOutputStream();
		BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
		try {
			bufferedOutPut.flush();
			workbook.write(bufferedOutPut);
			bufferedOutPut.close();
		}catch(IOException e){
			e.printStackTrace();
			System.out.println( "Output   is   closed ");
		}	
			
	}*/
    
    //上传文件,先将excel文件写入到服务器端，然后从服务器端再获取
    public synchronized void upload(String filename,MultipartFile file) throws IOException{
    	File outFile = new File(filename);
    	BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(outFile)); // 创建文件输出流
    	//根据上传的文件，创建缓冲区，生成字符流，存入缓冲区
        byte[] buffer = file.getBytes();//创建文件流       
        //把缓冲区的字符流 写入 字符流目的地(把输入流写入输出流)
        stream.write(buffer);
        //刷新流，关闭流
        stream.close();
    }
}  
