package com.poiutil;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;

import com.bean.T07_blacklist;
import com.bean.User;
import com.database.ConnectionUtils;

/*
 * @Author zyd
 * @date 20190119
 * @version 1.0
 * @des 支持office2003 office 2007
 */
public class ExcelUtil {

private static final String EXTENSION_XLS = "xls";
private static final String EXTENSION_XLSX = "xlsx";
private SimpleDateFormat si=new SimpleDateFormat("yyyy-MM-dd");

/*
 * zyd
 * 2003和2007采用不同的解析对象
 */

private Workbook getWorkbook(String filePath) throws IOException {
        Workbook workbook = null;
        InputStream is = new FileInputStream(filePath);
        if (filePath.endsWith(EXTENSION_XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (filePath.endsWith(EXTENSION_XLSX)) {
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }   
 
/*
 * 获取单元格值
 */
private String getCellValue(Cell cell, boolean treatAsStr) {
        if (cell == null) {
            return "";
        }

        if (treatAsStr) {
            // 虽然excel中设置的都是文本，但是数字文本还被读错，如“1”取成“1.0”
            // 加上下面这句，临时把它当做文本来读取
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }

        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

 public static void main(String[] args) throws Exception {
	 ArrayList<com.bean.UserBean> list=new ConnectionUtils(true).getlist();
	 System.out.println(new Date().getMinutes());
	 new ExcelUtil().createWorkbook(list,"大额报送统计2019-01-01-2019-09-01","大额报送统计\r\n2019-01-01-2019-09-01","姓名,客户号,j,l,h,n,m","obj_name,party_id,organkey,create_dt,isuse,party_class_cd,list_type",new FileOutputStream("E:\\123.xls"),"1");
	 System.out.println(new Date().getMinutes());
	 
	 
}
 
 /**
  * xls 文件生成
  * @param abfilename :文件绝对路径
  * @param filedorder 属性顺序
  * @param bookname工作薄名称
  * @param obj   list对象
  * @param tile  标题
  * @param rowtitle 行标题  
  * @return
  * @throws IOException
 * @throws IllegalAccessException 
 * @throws NoSuchFieldException 
 * @throws IllegalArgumentException 
 * @throws SecurityException 
  */
 public String createWorkbook(ArrayList list,String bookname,String tile,String rowtitle,String filedorder,OutputStream out,String autoflag) throws IOException, SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException{
	 Workbook wk=new XSSFWorkbook();
	 Sheet st=wk.createSheet(bookname);
	 String[] columns=rowtitle.split(",");
	 CellStyle stt=wk.createCellStyle();
	 stt=setCellStyle1(wk,tile,columns,stt);
	 setWorkBookContent(wk, list, 2, columns, filedorder,stt);
	 setAutoSize(st, columns, stt,autoflag);
      wk.write(out);
    out.flush();
    out.close();
   // System.out.println("llll");
    return "";
 }
 
 
 /**
  * 创建表头
  * 标准表样1
  */
 public CellStyle  setCellStyle1(Workbook wk,String titile,String[] tile,CellStyle stt){


	 stt=wk.createCellStyle();
	
	 //居中
	 stt.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	 stt.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
	 //背景
	// stt.setFillForegroundColor(IndexedColors.YELLOW.index);
	// stt.setFillPattern(CellStyle.SOLID_FOREGROUND);
	 //边框
	 stt.setBorderLeft(CellStyle.BORDER_THIN);
	 stt.setBorderRight(CellStyle.BORDER_THIN);
	 stt.setBorderTop(CellStyle.BORDER_THIN);
	 stt.setBorderBottom(CellStyle.BORDER_THIN);

	//列数
	Integer column=tile.length;
	 
  //合并标题
	CellRangeAddress ad=new CellRangeAddress(0,0, 0, column-1); //合并单元格
	Sheet sheet0=wk.getSheetAt(0);
	sheet0.addMergedRegion(ad);
	//字体
	 Font ftFonts=wk.createFont();
	 ftFonts.setFontHeightInPoints((short)13);//字号
	 ftFonts.setFontName("仿宋");
	 stt.setFont(ftFonts);

	//行标题 和行
    Row row1=sheet0.createRow(0);
    row1.setHeight((short)900);
    for(int i=0;i<column;i++){	
    	Cell cell =row1.createCell(i,Cell.CELL_TYPE_STRING);
    	if(i==0){
    		
    		/*ftFonts.setUnderline(Font.U_SINGLE);
    		XSSFRichTextString s=new XSSFRichTextString(titile);
    		s.applyFont(1, 3, ftFonts);*/
    		row1.getCell(i).setCellValue(titile);
    	}
    	cell.setCellStyle(stt);
    }
 
	 //行2
    Row row2=sheet0.createRow(1);
    row2.setHeight((short)600);
    for(int i=0;i<column;i++){
    	Cell cell=row2.createCell(i,Cell.CELL_TYPE_STRING);
    	cell.setCellValue(tile[i]);
    	cell.setCellStyle(stt);
    }
  
    return stt;
	
 }
 
 public void setAutoSize(Sheet sheet0,String[] tile,CellStyle stt,String autoflag){
		Integer column=tile.length;
		if("1".equals(autoflag)){
			    for (int i=0;i<column;i++){
			      sheet0.autoSizeColumn(i);//自适应列宽
			    }
		}else			
		stt.setWrapText(true); //自动换行

	    
 }
 
 /**
  * @param list 内容
 * @throws IllegalAccessException 
 * @throws NoSuchFieldException 
 * @throws IllegalArgumentException 
 * @throws SecurityException 
  */
 
 public void setWorkBookContent(Workbook wk,ArrayList list,Integer rowbegin,String[] tile ,String order,CellStyle ct) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException{
	 Integer columnnum=tile.length;
	 Integer rowadd=list.size();
	 String[] orders=order.split(",");
	 Sheet sheet0=wk.getSheetAt(0);
	 CellBeanTemp bean=new CellBeanTemp();
	 Object obj=null;
	 Cell cell=null;
	    for(int i=0;i<rowadd;i++){
	    	 Row row=sheet0.createRow(rowbegin+i);
	    	   row.setHeight((short)600);
	    	 obj=list.get(i);    	 
	    	for(int c=0;c<columnnum;c++){
	    	   getCellByObj(obj, orders[c],bean);
	    	    cell= row.createCell(c,bean.getType());
	    	    cell.setCellStyle(ct);
	            if(bean.getValue()==null||"null".equals(bean.getValue())){
	            	cell.setCellValue("");
	            }else{
	            	cell.setCellValue(bean.getValue());
	            }
	            
	    	}
	    }
 }
 
 
 /**
  * 根据属性名称  获取类型 和值
 * @throws NoSuchFieldException 
 * @throws SecurityException 
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException 
  */
 public void getCellByObj(Object obj,String field, CellBeanTemp cellbean) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
	 Class cls=obj.getClass();
	 Field fieldt=cls.getDeclaredField(field);
    fieldt.setAccessible(true);
    String typetemp=fieldt.getGenericType().toString();
    Integer tp;
    String value;
    cellbean.setKey("");
    cellbean.setType(null);
    cellbean.setValue(null);
  
    if(typetemp.indexOf("Long")>0){
    	tp=Cell.CELL_TYPE_NUMERIC;
    	value=String.valueOf((Long)fieldt.get(obj));
    }else if(typetemp.indexOf("Integer")>0) {
    	tp=Cell.CELL_TYPE_NUMERIC;
    	value=String.valueOf((Integer)fieldt.get(obj));
    }
    else if(typetemp.indexOf("Date")>0) {
    	tp=Cell.CELL_TYPE_STRING;
    	value=si.format((Date)fieldt.get(obj));
    }
    else if(typetemp.indexOf("Double")>0) {
    	tp=Cell.CELL_TYPE_NUMERIC;
    	value=String.valueOf((Double)fieldt.get(obj));
    }
    else if(typetemp.indexOf("Float")>0) {
    	tp=Cell.CELL_TYPE_NUMERIC;
    	value=String.valueOf((Float)fieldt.get(obj));
    }else{
     	tp=Cell.CELL_TYPE_STRING;
     	value=(String)fieldt.get(obj);
    }
    
   // String methodname="get"+field.substring(0, 1).toUpperCase()+field.substring(1);
    cellbean.setKey(field);
	 cellbean.setType(tp);
	 cellbean.setValue(value);
	// return  cellbean ;
 }
 
/*
 * 获取单元格行列地址
 */
public void readExcel(String filePath,String type) throws Exception {
        Workbook workbook = null;
        try {
                workbook = this.getWorkbook(filePath);
                Sheet sheet = workbook.getSheetAt(0);
                if (sheet == null) {
                   return;
                 }
                int firstRowIndex = sheet.getFirstRowNum();
                int lastRowIndex = sheet.getLastRowNum();
                // 读取首行 即,表头
                Row firstRow = sheet.getRow(firstRowIndex);
               for (int i = firstRow.getFirstCellNum(); i <= firstRow.getLastCellNum(); i++) {
                    Cell cell = firstRow.getCell(i);
                    String cellValue = this.getCellValue(cell, true);
                    System.out.print(" " + cellValue + "\t");
                  
                }         
                // 读取数据行
                for (int rowIndex = firstRowIndex + 2; rowIndex <= lastRowIndex; rowIndex++) {
                    Row currentRow = sheet.getRow(rowIndex);// 当前行
                    int firstColumnIndex = currentRow.getFirstCellNum(); // 首列
                    int lastColumnIndex = currentRow.getLastCellNum();// 最后一列
                    for (int columnIndex = firstColumnIndex; columnIndex <= lastColumnIndex; columnIndex++) {
                    	//if(columnIndex ==2||columnIndex ==3){
                    	
                        Cell currentCell = currentRow.getCell(columnIndex);// 当前单元格
                        if(currentCell!=null){
                        String currentCellValue = this.getCellValue(currentCell, true);// 当前单元格的值
                        currentCell.setCellValue(String.valueOf(currentCell.getRowIndex())+String.valueOf(currentCell.getColumnIndex()));
                        System.out.print(currentCellValue + "\t");
                        }
                        
                   // }
                }
                }
               OutputStream s =new FileOutputStream("E:\\sss.xlsx");
                workbook.write(s);  
                s.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
}
public void readExcel(String filePath) throws FileNotFoundException, FileFormatException {
    // 检查
  //  this.preReadCheck(filePath);
    // 获取workbook对象
    Workbook workbook = null;

    try {
        workbook = this.getWorkbook(filePath);
        // 读文件 一个sheet一个sheet地读取
        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
            Sheet sheet = workbook.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            System.out.println("=======================" + sheet.getSheetName() + "=========================");

            int firstRowIndex = sheet.getFirstRowNum();
            int lastRowIndex = sheet.getLastRowNum();

            // 读取首行 即,表头
            Row firstRow = sheet.getRow(firstRowIndex);
            for (int i = firstRow.getFirstCellNum(); i <= firstRow.getLastCellNum(); i++) {
                Cell cell = firstRow.getCell(i);
                String cellValue = this.getCellValue(cell, true);
                System.out.print(" " + cellValue + "\t");
            }
            System.out.println("");

            // 读取数据行
            for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
                Row currentRow = sheet.getRow(rowIndex);// 当前行
                int firstColumnIndex = currentRow.getFirstCellNum(); // 首列
                int lastColumnIndex = currentRow.getLastCellNum();// 最后一列
                for (int columnIndex = firstColumnIndex; columnIndex <= lastColumnIndex; columnIndex++) {
                    Cell currentCell = currentRow.getCell(columnIndex);// 当前单元格
                    String currentCellValue = this.getCellValue(currentCell, true);// 当前单元格的值
                    System.out.print(currentCellValue + "\t");
                }
                System.out.println("");
            }
            System.out.println("======================================================");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } /*finally {
        if (workbook != null) {
            try {
                this.workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
}
/*
 * zyd 20190120
 * 根据单元格  行列编号 值进行改变    
 * @param filepath  模板Excel存放绝对路径   
 * @param  outpath  输出路径
 * 
 */
public void updateExcel(ArrayList<ExcelBean> beanlist,String filePath,String outpath) throws Exception{
	  Workbook workbook = null;
	  try {
          workbook = this.getWorkbook(filePath);
          Sheet sheet = workbook.getSheetAt(0);
          if (sheet == null) {
             return;
           }
         for(int i=0;i<beanlist.size();i++){
             ExcelBean excelbean=beanlist.get(i);
             Row firstRow = sheet.getRow(excelbean.getRowNum());
             Cell cell=firstRow.getCell(excelbean.getColumnNum());
             cell.setCellValue(excelbean.getCellval());
         }
         OutputStream s =new FileOutputStream(outpath);
         workbook.write(s);  
         s.close();
  } catch (Exception e) {
      e.printStackTrace();
      throw e;
  }
 }
/*
 * zyd 20190120
 * 根据单元格  行列编号 值进行改变    
 * @param filepath  模板Excel存放绝对路径   
 * @param  outpath  输出路径
 * 
 */
public void updateExcel(ArrayList<ExcelBean> beanlist,String filePath,OutputStream os) throws Exception{
	  Workbook workbook = null;
	  try {
          workbook = this.getWorkbook(filePath);
          Sheet sheet = workbook.getSheetAt(0);
          if (sheet == null) {
             return;
           }
         for(int i=0;i<beanlist.size();i++){
             ExcelBean excelbean=beanlist.get(i);
             Row firstRow = sheet.getRow(excelbean.getRowNum());
             Cell cell=firstRow.getCell(excelbean.getColumnNum());
             cell.setCellValue(excelbean.getCellval());
         }
         workbook.write(os);  
         os.close();
  } catch (Exception e) {
      e.printStackTrace();
      throw e;
  }
 }

/*
 * 临时对象
 */
class CellBeanTemp {
	private String key;
	private Integer type;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}

class ExcelBean {
private int rowNum;
private int columnNum;
private String cellval;
private Date tx_dt;
private Long numbs;
private String organkey;
private Integer amt;

public Integer getAmt() {
	return amt;
}
public void setAmt(Integer amt) {
	this.amt = amt;
}
public Date getTx_dt() {
	return tx_dt;
}
public void setTx_dt(Date tx_dt) {
	this.tx_dt = tx_dt;
}
public Long getNumbs() {
	return numbs;
}
public void setNumbs(Long numbs) {
	this.numbs = numbs;
}
public String getOrgankey() {
	return organkey;
}
public void setOrgankey(String organkey) {
	this.organkey = organkey;
}
public int getRowNum() {
	return rowNum;
}
public void setRowNum(Integer rowNum) {
	this.rowNum = rowNum;
}
public int getColumnNum() {
	return columnNum;
}
public void setColumnNum(int columnNum) {
	this.columnNum = columnNum;
}
public String getCellval() {
	return cellval;
}
public void setCellval(String cellval) {
	this.cellval = cellval;
}
}

}