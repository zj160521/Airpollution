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
import org.springframework.transaction.annotation.Transactional;

import com.xf.entity.gov.CanyinStat;
//业态
public class ReadExcelCanyinStat {

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
    
	public static List<CanyinStat> readExcel(String path) throws Exception {
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
	public static List<CanyinStat> readXlsx(String path) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		CanyinStat canyin = null;
		List<CanyinStat> list = new ArrayList<CanyinStat>();
		// Read the Sheet
		
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
				// 读取一行
				for (int rowNum = 5; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						// 记录行号
						rownum = rowNum;

						try {
							canyin = new CanyinStat();
							XSSFCell certified = xssfRow.getCell(0);
							if(certified==null){
								break;
							}
							if(getValue(certified).isEmpty()){
								break;
							}
							XSSFCell city = xssfRow.getCell(1);
							XSSFCell town = xssfRow.getCell(2);
							XSSFCell total = xssfRow.getCell(3);
							XSSFCell canguan_huge = xssfRow.getCell(4);
							XSSFCell canguan_big = xssfRow.getCell(5);
							XSSFCell canguan_mid = xssfRow.getCell(6);
							XSSFCell canguan_small = xssfRow.getCell(7);
							XSSFCell snack = xssfRow.getCell(8);
							XSSFCell fastfood = xssfRow.getCell(9);
							XSSFCell milk = xssfRow.getCell(10);
							XSSFCell drink = xssfRow.getCell(11);
							XSSFCell shitang_shiye = xssfRow.getCell(12);
							XSSFCell shitang_school = xssfRow.getCell(13);
							XSSFCell shitang_gongdi = xssfRow.getCell(14);
							XSSFCell incoming = xssfRow.getCell(15);
							canyin.setCityName(getValue(city));
							canyin.setTownName(getValue(town));
							canyin.setCertified((int)Double.parseDouble(getValue(certified)));
							canyin.setTotal((int)Double.parseDouble(getValue(total)));
							canyin.setCanguan_huge((int)Double.parseDouble(getValue(canguan_huge)));
							canyin.setCanguan_big((int)Double.parseDouble(getValue(canguan_big)));
							canyin.setCanguan_mid((int)Double.parseDouble(getValue(canguan_mid)));
							canyin.setCanguan_small((int)Double.parseDouble(getValue(canguan_small)));
							canyin.setSnack((int)Double.parseDouble(getValue(snack)));
							canyin.setFastfood((int)Double.parseDouble(getValue(fastfood)));
							canyin.setMilk((int)Double.parseDouble(getValue(milk)));
							canyin.setDrink((int)Double.parseDouble(getValue(drink)));
							canyin.setShitang_shiye((int)Double.parseDouble(getValue(shitang_shiye)));
							canyin.setShitang_school((int)Double.parseDouble(getValue(shitang_school)));
							canyin.setShitang_gongdi((int)Double.parseDouble(getValue(shitang_gongdi)));
							canyin.setIncoming(Double.valueOf(getValue(incoming)));
							list.add(canyin);
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
	public static List<CanyinStat> readXls(String path) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		CanyinStat canyin = null;
		List<CanyinStat> list = new ArrayList<CanyinStat>();
		// Read the Sheet
			
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
				// 读取行
				for (int rowNum = 5; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						// 记录行号
						rownum = rowNum;

						try {
							canyin = new CanyinStat();
							HSSFCell certified = hssfRow.getCell(0);
							if(certified==null){
								break;
							}
							if(getValue(certified).isEmpty()){
								break;
							}
							HSSFCell city = hssfRow.getCell(1);
							HSSFCell town = hssfRow.getCell(2);
							HSSFCell total = hssfRow.getCell(3);
							HSSFCell canguan_huge = hssfRow.getCell(4);
							HSSFCell canguan_big = hssfRow.getCell(5);
							HSSFCell canguan_mid = hssfRow.getCell(6);
							HSSFCell canguan_small = hssfRow.getCell(7);
							HSSFCell snack = hssfRow.getCell(8);
							HSSFCell fastfood = hssfRow.getCell(9);
							HSSFCell milk = hssfRow.getCell(10);
							HSSFCell drink = hssfRow.getCell(11);
							HSSFCell shitang_shiye = hssfRow.getCell(12);
							HSSFCell shitang_school = hssfRow.getCell(13);
							HSSFCell shitang_gongdi = hssfRow.getCell(14);
							HSSFCell incoming = hssfRow.getCell(15);
							canyin.setCityName(getValue(city));
							canyin.setTownName(getValue(town));
							canyin.setCertified((int)Double.parseDouble(getValue(certified)));
							canyin.setTotal((int)Double.parseDouble(getValue(total)));
							canyin.setCanguan_huge((int)Double.parseDouble(getValue(canguan_huge)));
							canyin.setCanguan_big((int)Double.parseDouble(getValue(canguan_big)));
							canyin.setCanguan_mid((int)Double.parseDouble(getValue(canguan_mid)));
							canyin.setCanguan_small((int)Double.parseDouble(getValue(canguan_small)));
							canyin.setSnack((int)Double.parseDouble(getValue(snack)));
							canyin.setFastfood((int)Double.parseDouble(getValue(fastfood)));
							canyin.setMilk((int)Double.parseDouble(getValue(milk)));
							canyin.setDrink((int)Double.parseDouble(getValue(drink)));
							canyin.setShitang_shiye((int)Double.parseDouble(getValue(shitang_shiye)));
							canyin.setShitang_school((int)Double.parseDouble(getValue(shitang_school)));
							canyin.setShitang_gongdi((int)Double.parseDouble(getValue(shitang_gongdi)));
							canyin.setIncoming(Double.valueOf(getValue(incoming)));
							list.add(canyin);
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
