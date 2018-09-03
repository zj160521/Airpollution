package com.xf.readexcel;

import java.io.FileInputStream;
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

import com.xf.entity.gov.AnimalsFarm;

public class ReadExcelAnimalsFarm {
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
    
	public List<AnimalsFarm> readExcel(String path,int citynum) throws Exception {
		//判断路径是否为空
		if (path == null || EMPTY.equals(path)) {
			return null;
		} else {
			//截取文件后缀名
			String postfix = getPostfix(path);
			if (!EMPTY.equals(postfix)) {
				//判断excel的格式
				if (OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
					return readXls(path,citynum);
				} else if (OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
					return readXlsx(path,citynum);
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
	 * @throws Exception 
	 */
	public List<AnimalsFarm> readXlsx(String path,int citynum) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		AnimalsFarm animal = null;
		List<AnimalsFarm> list = new ArrayList<AnimalsFarm>();
		// Read the Sheet
		
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
				// 读取一行
				try {
					for (int rowNum = 7; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
						XSSFRow xssfRow = xssfSheet.getRow(rowNum);
						if (xssfRow != null) {
							// 记录行号
							rownum = rowNum;

							animal = new AnimalsFarm();
							XSSFCell farmname = xssfRow.getCell(0);
							
							XSSFCell city = xssfRow.getCell(1);
							
							XSSFCell town = xssfRow.getCell(2);
							if(farmname==null){
								break;
							}
							XSSFCell country = xssfRow.getCell(3);
							XSSFCell street = xssfRow.getCell(4);
							XSSFCell method = xssfRow.getCell(5);
							XSSFCell beef = xssfRow.getCell(6);
							XSSFCell beefcycle = xssfRow.getCell(7);
							XSSFCell cow = xssfRow.getCell(8);
							XSSFCell cowcycle = xssfRow.getCell(9);
							XSSFCell goat = xssfRow.getCell(10);
							XSSFCell goatcycle = xssfRow.getCell(11);
							XSSFCell sheep = xssfRow.getCell(12);
							XSSFCell sheepcycle = xssfRow.getCell(13);
							XSSFCell pig = xssfRow.getCell(14);
							XSSFCell pigcycle = xssfRow.getCell(15);
							XSSFCell chicken = xssfRow.getCell(16);
							XSSFCell chickencycle = xssfRow.getCell(17);
							XSSFCell duck = xssfRow.getCell(18);
							XSSFCell duckcycle = xssfRow.getCell(19);
							XSSFCell goose = xssfRow.getCell(20);
							XSSFCell goosecycle = xssfRow.getCell(21);
							XSSFCell hen = xssfRow.getCell(22);
							XSSFCell layingduck = xssfRow.getCell(23);
							XSSFCell layinggoose = xssfRow.getCell(24);
							XSSFCell sow = xssfRow.getCell(25);
							XSSFCell rabbit = xssfRow.getCell(26);
							XSSFCell horse = xssfRow.getCell(27);
							if(getValue(farmname).isEmpty()){
								break;
							}
							animal.setFarmname(getValue(farmname));
							animal.setCityName(getValue(city));
							animal.setCity(citynum);
							animal.setTownName(getValue(town));
							animal.setMethod(getValue(method));
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
							animal.setCountryname(getValue(country));
							animal.setStreetname(getValue(street));
							list.add(animal);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new MyException("导入第"+(rownum+1)+"行出错");
				}

		return list;
	}

	

	/**
	 * Read the Excel 2003-2007
	 * 
	 * @param path
	 *            the path of the Excel
	 * @return
	 * @throws Exception 
	 */
	public List<AnimalsFarm> readXls(String path,int citynum) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		AnimalsFarm animal = null;
		List<AnimalsFarm> list = new ArrayList<AnimalsFarm>();
		// Read the Sheet
		
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
				
				// 读取行
				for (int rowNum = 7; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						// 记录行号
						rownum = rowNum;

						try {
							animal = new AnimalsFarm();
							HSSFCell farmname = hssfRow.getCell(0);
							
							HSSFCell city = hssfRow.getCell(1);
							
							HSSFCell town = hssfRow.getCell(2);
							if(farmname==null){
								break;
							}
							HSSFCell country = hssfRow.getCell(3);
							HSSFCell street = hssfRow.getCell(4);
							HSSFCell method = hssfRow.getCell(5);
							HSSFCell beef = hssfRow.getCell(6);
							HSSFCell beefcycle = hssfRow.getCell(7);
							HSSFCell cow = hssfRow.getCell(8);
							HSSFCell cowcycle = hssfRow.getCell(9);
							HSSFCell goat = hssfRow.getCell(10);
							HSSFCell goatcycle = hssfRow.getCell(11);
							HSSFCell sheep = hssfRow.getCell(12);
							HSSFCell sheepcycle = hssfRow.getCell(13);
							HSSFCell pig = hssfRow.getCell(14);
							HSSFCell pigcycle = hssfRow.getCell(15);
							HSSFCell chicken = hssfRow.getCell(16);
							HSSFCell chickencycle = hssfRow.getCell(17);
							HSSFCell duck = hssfRow.getCell(18);
							HSSFCell duckcycle = hssfRow.getCell(19);
							HSSFCell goose = hssfRow.getCell(20);
							HSSFCell goosecycle = hssfRow.getCell(21);
							HSSFCell hen = hssfRow.getCell(22);
							HSSFCell layingduck = hssfRow.getCell(23);
							HSSFCell layinggoose = hssfRow.getCell(24);
							HSSFCell sow = hssfRow.getCell(25);
							HSSFCell rabbit = hssfRow.getCell(26);
							HSSFCell horse = hssfRow.getCell(27);
							if(getValue(farmname).isEmpty()){
								break;
							}
							animal.setFarmname(getValue(farmname));
							animal.setCityName(getValue(city));
							animal.setCity(citynum);
							animal.setTownName(getValue(town));
							animal.setMethod(getValue(method));
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
							animal.setCountryname(getValue(country));
							animal.setStreetname(getValue(street));
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
