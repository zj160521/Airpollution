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

import com.xf.entity.gov.CanyinNocert;
//无证
public class ReadExcelNocert {

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
    
	public static List<CanyinNocert> readExcel(String path) throws Exception {
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
	public static List<CanyinNocert> readXlsx(String path) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		CanyinNocert canyin = null;
		List<CanyinNocert> list = new ArrayList<CanyinNocert>();
		// Read the Sheet
		
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
				// 读取一行
				for (int rowNum = 3; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						// 记录行号
						rownum = rowNum;

						try {
							canyin = new CanyinNocert();
							XSSFCell stat = xssfRow.getCell(0);
							if(stat==null){
								break;
							}
							XSSFCell padress = xssfRow.getCell(1);
							XSSFCell name = xssfRow.getCell(2);
							XSSFCell pnum = xssfRow.getCell(3);
							XSSFCell zadress = xssfRow.getCell(4);
							XSSFCell phone = xssfRow.getCell(5);
							XSSFCell amount = xssfRow.getCell(6);
							XSSFCell kind = xssfRow.getCell(7);
							XSSFCell area = xssfRow.getCell(8);
							XSSFCell other = xssfRow.getCell(9);
							if(getValue(stat).isEmpty()){
								break;
							}
							canyin.setShoptype(getValue(stat));
							canyin.setShopaddr(getValue(padress));
							canyin.setName(getValue(name));
							canyin.setIdnum(getValue(pnum));
							canyin.setHomeaddr(getValue(zadress));
							canyin.setTelephone(getValue(phone));
							canyin.setIncoming(Double.valueOf(getValue(amount)));
							canyin.setProductType(getValue(kind));
							canyin.setDistrictId(getValue(area));
							canyin.setRemark(getValue(other));
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
	public static List<CanyinNocert> readXls(String path) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		CanyinNocert canyin = null;
		List<CanyinNocert> list = new ArrayList<CanyinNocert>();
		// Read the Sheet
		
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
				
				// 读取行
				for (int rowNum = 3; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						// 记录行号
						rownum = rowNum;

						try {
							canyin = new CanyinNocert();
							HSSFCell stat = hssfRow.getCell(0);
							if(stat==null){
								break;
							}
							HSSFCell padress = hssfRow.getCell(1);
							HSSFCell name = hssfRow.getCell(2);
							HSSFCell pnum = hssfRow.getCell(3);
							HSSFCell zadress = hssfRow.getCell(4);
							HSSFCell phone = hssfRow.getCell(5);
							HSSFCell amount = hssfRow.getCell(6);
							HSSFCell kind = hssfRow.getCell(7);
							HSSFCell area = hssfRow.getCell(8);
							HSSFCell other = hssfRow.getCell(9);
							if(getValue(stat).isEmpty()){
								break;
							}
							canyin.setShoptype(getValue(stat));
							canyin.setShopaddr(getValue(padress));
							canyin.setName(getValue(name));
							canyin.setIdnum(getValue(pnum));
							canyin.setHomeaddr(getValue(zadress));
							canyin.setTelephone(getValue(phone));
							canyin.setIncoming(Double.valueOf(getValue(amount)));
							canyin.setProductType(getValue(kind));
							canyin.setDistrictId(getValue(area));
							canyin.setRemark(getValue(other));
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
