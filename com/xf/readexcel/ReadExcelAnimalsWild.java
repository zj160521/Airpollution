package com.xf.readexcel;
//散养
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

import com.xf.entity.gov.AnimalsWild;

public class ReadExcelAnimalsWild {

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
    
	public static List<AnimalsWild> readExcel(String path) throws Exception {
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
	public static List<AnimalsWild> readXlsx(String path) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		AnimalsWild animal = null;
		List<AnimalsWild> list = new ArrayList<AnimalsWild>();
		// Read the Sheet
			
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
				// 读取一行
				for (int rowNum = 7; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						// 记录行号
						rownum = rowNum;

						try {
							animal = new AnimalsWild();
							XSSFCell city = xssfRow.getCell(0);
							XSSFCell town = xssfRow.getCell(1);
							XSSFCell beef = xssfRow.getCell(2);
							XSSFCell beefcycle = xssfRow.getCell(3);
							XSSFCell cow = xssfRow.getCell(4);
							XSSFCell cowcycle = xssfRow.getCell(5);
							XSSFCell goat = xssfRow.getCell(6);
							XSSFCell goatcycle = xssfRow.getCell(7);
							XSSFCell sheep = xssfRow.getCell(8);
							XSSFCell sheepcycle = xssfRow.getCell(9);
							XSSFCell pig = xssfRow.getCell(10);
							XSSFCell pigcycle = xssfRow.getCell(11);
							XSSFCell chicken = xssfRow.getCell(12);
							XSSFCell chickencycle = xssfRow.getCell(13);
							XSSFCell duck = xssfRow.getCell(14);
							XSSFCell duckcycle = xssfRow.getCell(15);
							XSSFCell goose = xssfRow.getCell(16);
							XSSFCell goosecycle = xssfRow.getCell(17);
							XSSFCell hen = xssfRow.getCell(18);
							XSSFCell layingduck = xssfRow.getCell(19);
							XSSFCell layinggoose = xssfRow.getCell(20);
							XSSFCell sow = xssfRow.getCell(21);
							XSSFCell rabbit = xssfRow.getCell(22);
							XSSFCell horse = xssfRow.getCell(23);
							if(town==null){
								break;
							}
							if(getValue(town).isEmpty()){
								break;
							}
							animal.setCityName(getValue(city));
							animal.setTownName(getValue(town));
							animal.setBeef((int)Double.parseDouble(getValue(beef)));
							animal.setBeefcycle((int)Double.parseDouble(getValue(beefcycle)));
							animal.setCow((int)Double.parseDouble(getValue(cow)));
							animal.setCowcycle((int)Double.parseDouble(getValue(cowcycle)));
							animal.setGoat((int)Double.parseDouble(getValue(goat)));
							animal.setGoatcycle((int)Double.parseDouble(getValue(goatcycle)));
							animal.setSheep((int)Double.parseDouble(getValue(sheep)));
							animal.setSheepcycle((int)Double.parseDouble(getValue(sheepcycle)));
							animal.setPig((int)Double.parseDouble(getValue(pig)));
							animal.setPigcycle((int)Double.parseDouble(getValue(pigcycle)));
							animal.setChicken((int)Double.parseDouble(getValue(chicken)));
							animal.setChickencycle((int)Double.parseDouble(getValue(chickencycle)));
							animal.setDuck((int)Double.parseDouble(getValue(duck)));
							animal.setDuckcycle((int)Double.parseDouble(getValue(duckcycle)));
							animal.setGoose((int)Double.parseDouble(getValue(goose)));
							animal.setGoosecycle((int)Double.parseDouble(getValue(goosecycle)));
							animal.setHen((int)Double.parseDouble(getValue(hen)));
							animal.setLayingduck((int)Double.parseDouble(getValue(layingduck)));
							animal.setLayinggoose((int)Double.parseDouble(getValue(layinggoose)));
							animal.setSow((int)Double.parseDouble(getValue(sow)));
							animal.setRabbit((int)Double.parseDouble(getValue(rabbit)));
							animal.setHorse((int)Double.parseDouble(getValue(horse)));
							list.add(animal);
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
	public static List<AnimalsWild> readXls(String path) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		AnimalsWild animal = null;
		List<AnimalsWild> list = new ArrayList<AnimalsWild>();
		// Read the Sheet
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
				// 读取行
				for (int rowNum = 7; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						// 记录行号
						rownum = rowNum;

						try {
							animal = new AnimalsWild();
							HSSFCell city = hssfRow.getCell(0);
							HSSFCell town = hssfRow.getCell(1);
							HSSFCell beef = hssfRow.getCell(2);
							HSSFCell beefcycle = hssfRow.getCell(3);
							HSSFCell cow = hssfRow.getCell(4);
							HSSFCell cowcycle = hssfRow.getCell(5);
							HSSFCell goat = hssfRow.getCell(6);
							HSSFCell goatcycle = hssfRow.getCell(7);
							HSSFCell sheep = hssfRow.getCell(8);
							HSSFCell sheepcycle = hssfRow.getCell(9);
							HSSFCell pig = hssfRow.getCell(10);
							HSSFCell pigcycle = hssfRow.getCell(11);
							HSSFCell chicken = hssfRow.getCell(12);
							HSSFCell chickencycle = hssfRow.getCell(13);
							HSSFCell duck = hssfRow.getCell(14);
							HSSFCell duckcycle = hssfRow.getCell(15);
							HSSFCell goose = hssfRow.getCell(16);
							HSSFCell goosecycle = hssfRow.getCell(17);
							HSSFCell hen = hssfRow.getCell(18);
							HSSFCell layingduck = hssfRow.getCell(19);
							HSSFCell layinggoose = hssfRow.getCell(20);
							HSSFCell sow = hssfRow.getCell(21);
							HSSFCell rabbit = hssfRow.getCell(22);
							HSSFCell horse = hssfRow.getCell(23);
							if(town==null){
								break;
							}
							if(getValue(town).isEmpty()){
								break;
							}
							animal.setCityName(getValue(city));
							animal.setTownName(getValue(town));
							animal.setBeef((int)Double.parseDouble(getValue(beef)));
							animal.setBeefcycle((int)Double.parseDouble(getValue(beefcycle)));
							animal.setCow((int)Double.parseDouble(getValue(cow)));
							animal.setCowcycle((int)Double.parseDouble(getValue(cowcycle)));
							animal.setGoat((int)Double.parseDouble(getValue(goat)));
							animal.setGoatcycle((int)Double.parseDouble(getValue(goatcycle)));
							animal.setSheep((int)Double.parseDouble(getValue(sheep)));
							animal.setSheepcycle((int)Double.parseDouble(getValue(sheepcycle)));
							animal.setPig((int)Double.parseDouble(getValue(pig)));
							animal.setPigcycle((int)Double.parseDouble(getValue(pigcycle)));
							animal.setChicken((int)Double.parseDouble(getValue(chicken)));
							animal.setChickencycle((int)Double.parseDouble(getValue(chickencycle)));
							animal.setDuck((int)Double.parseDouble(getValue(duck)));
							animal.setDuckcycle((int)Double.parseDouble(getValue(duckcycle)));
							animal.setGoose((int)Double.parseDouble(getValue(goose)));
							animal.setGoosecycle((int)Double.parseDouble(getValue(goosecycle)));
							animal.setHen((int)Double.parseDouble(getValue(hen)));
							animal.setLayingduck((int)Double.parseDouble(getValue(layingduck)));
							animal.setLayinggoose((int)Double.parseDouble(getValue(layinggoose)));
							animal.setSow((int)Double.parseDouble(getValue(sow)));
							animal.setRabbit((int)Double.parseDouble(getValue(rabbit)));
							animal.setHorse((int)Double.parseDouble(getValue(horse)));
							list.add(animal);
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
