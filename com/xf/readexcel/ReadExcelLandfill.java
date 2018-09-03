package com.xf.readexcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.xf.entity.gov.DumpField;

public class ReadExcelLandfill {
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";

    public static final String EMPTY = "";
    public static final String POINT = ".";
    public static final String LIB_PATH = "lib";
    public static final String STUDENT_INFO_XLS_PATH = LIB_PATH + "/student_info" + POINT + OFFICE_EXCEL_2003_POSTFIX;
    public static final String STUDENT_INFO_XLSX_PATH = LIB_PATH + "/student_info" + POINT + OFFICE_EXCEL_2010_POSTFIX;
    public static final String NOT_EXCEL_FILE = " : 不是 Excel 文件类型!";
    public static final String PROCESSING = "正在读取...";
    public static int rownum;
    
	public static List<DumpField> readExcel(String path) throws Exception {
		//判断路径是否为空
		if (path == null || EMPTY.equals(path)) {
			return null;
		} else {
			//截取文件后缀名
			String postfix = getPostfix(path);
			if (!EMPTY.equals(postfix)) {
				//判断excel的格式
				if (OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
					return readXls(path);
				} else if (OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
					return readXlsx(path);
				}
			} else {
				System.out.println(path + NOT_EXCEL_FILE);
			}
		}
		return null;
	}

	/**
	 * Read the Excel 2010
	 * 
	 * @param path
	 *            the path of the excel file
	 * @return
	 * @throws IOException
	 */
	public static List<DumpField> readXlsx(String path) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		DumpField dump = null;
		List<DumpField> list = new ArrayList<DumpField>();
		// Read the Sheet
		
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
				// 读取一行
				for (int rowNum = 5; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						// 记录行号
						rownum = rowNum;

						try {
							dump = new DumpField();
							XSSFCell fanctoryname = xssfRow.getCell(0);
							if(fanctoryname==null){
								break;
							}
							XSSFCell fanctorytype = xssfRow.getCell(1);
							XSSFCell city = xssfRow.getCell(2);
							XSSFCell townName = xssfRow.getCell(3);
							XSSFCell country = xssfRow.getCell(4);
							XSSFCell street = xssfRow.getCell(5);
							XSSFCell rubbish_burn = xssfRow.getCell(6);
							XSSFCell rubbish_bury = xssfRow.getCell(7);
							XSSFCell rubbish_hill = xssfRow.getCell(8);
							XSSFCell rubbish_capability = xssfRow.getCell(9);
							XSSFCell rubbish_used = xssfRow.getCell(10);
							XSSFCell sewerage_total = xssfRow.getCell(11);
							if(getValue(fanctoryname).isEmpty()){
								break;
							}
							dump.setFactoryname(getValue(fanctoryname));
							dump.setFactorytype(getValue(fanctorytype));
							dump.setCityName(getValue(city));
							dump.setTownName(getValue(townName));
							dump.setCountry(getValue(country));
							dump.setStreet(getValue(street));
							dump.setRubbish_burn(Double.parseDouble(getValue(rubbish_burn)));
							dump.setRubbish_bury(Double.parseDouble(getValue(rubbish_bury)));
							dump.setRubbish_hill(Double.parseDouble(getValue(rubbish_hill)));
							dump.setRubbish_capability(Double.parseDouble(getValue(rubbish_capability)));
							dump.setRubbish_used(Double.parseDouble(getValue(rubbish_used)));
							dump.setSewerage_total(Double.parseDouble(getValue(sewerage_total)));
							list.add(dump);
						} catch (Exception e) {
							e.printStackTrace();
							throw new MyException("导入第"+(rownum+1)+"行出错");
						}
					}
				}
			
		
		return list;
	}

	/**
	 * Read the Excel 2003-2007
	 * 
	 * @param path
	 *            the path of the Excel
	 * @return
	 * @throws IOException
	 */
	public static List<DumpField> readXls(String path) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		DumpField dump = null;
		List<DumpField> list = new ArrayList<DumpField>();
		// Read the Sheet
		
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
				
				// 读取行
				for (int rowNum = 5; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						// 记录行号
						rownum = rowNum;

						try {
							dump = new DumpField();
							HSSFCell fanctoryname = hssfRow.getCell(0);
							if(fanctoryname==null){
								break;
							}
							HSSFCell fanctorytype = hssfRow.getCell(1);
							HSSFCell city = hssfRow.getCell(2);
							HSSFCell townName = hssfRow.getCell(3);
							HSSFCell country = hssfRow.getCell(4);
							HSSFCell street = hssfRow.getCell(5);
							HSSFCell rubbish_burn = hssfRow.getCell(6);
							HSSFCell rubbish_bury = hssfRow.getCell(7);
							HSSFCell rubbish_hill = hssfRow.getCell(8);
							HSSFCell rubbish_capability = hssfRow.getCell(9);
							HSSFCell rubbish_used = hssfRow.getCell(10);
							HSSFCell sewerage_total = hssfRow.getCell(11);
							if(getValue(fanctoryname).isEmpty()){
								break;
							}
							dump.setFactoryname(getValue(fanctoryname));
							dump.setFactorytype(getValue(fanctorytype));
							dump.setCityName(getValue(city));
							dump.setTownName(getValue(townName));
							dump.setCountry(getValue(country));
							dump.setStreet(getValue(street));
							dump.setRubbish_burn(Double.parseDouble(getValue(rubbish_burn)));
							dump.setRubbish_bury(Double.parseDouble(getValue(rubbish_bury)));
							dump.setRubbish_hill(Double.parseDouble(getValue(rubbish_hill)));
							dump.setRubbish_capability(Double.parseDouble(getValue(rubbish_capability)));
							dump.setRubbish_used(Double.parseDouble(getValue(rubbish_used)));
							dump.setSewerage_total(Double.parseDouble(getValue(sewerage_total)));
							list.add(dump);
						} catch (Exception e) {
							e.printStackTrace();
							throw new MyException("导入第"+(rownum+1)+"行出错");
						}
					}
				}
			
		
		return list;
	}

	@SuppressWarnings("static-access")
	private static String getValue(XSSFCell xssfRow) {
		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfRow.getNumericCellValue());
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_FORMULA) {
			try {
				return String.valueOf(xssfRow.getNumericCellValue());
			} catch (IllegalStateException e) {
				return String.valueOf(xssfRow.getRichStringCellValue());
			}
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}

	@SuppressWarnings("static-access")
	private static String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_FORMULA) {
			try {
				return String.valueOf(hssfCell.getNumericCellValue());
			} catch (IllegalStateException e) {
				return String.valueOf(hssfCell.getRichStringCellValue());
			}
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
	public static String getPostfix(String path) {
        if (path == null || EMPTY.equals(path.trim())) {
            return EMPTY;
        }
        if (path.contains(POINT)) {
            return path.substring(path.lastIndexOf(POINT) + 1, path.length());
        }
        return EMPTY;
    }
}
