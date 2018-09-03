package com.xf.readexcel;
//加油站
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

import com.xf.entity.gov.Gasstation;

public class ReadExcelGasstation {
	
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
    
	public List<Gasstation> readExcel(String path,int citynum) throws Exception {
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
	 * @throws IOException
	 */
	public List<Gasstation> readXlsx(String path,int citynum) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		Gasstation gas = null;
		List<Gasstation> list = new ArrayList<Gasstation>();
		// Read the Sheet
		
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
				// 读取一行
				for (int rowNum = 5; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						// 记录行号
						rownum = rowNum;

						try {
							gas = new Gasstation();
							XSSFCell gasStationName = xssfRow.getCell(0);
							if(gasStationName==null){
								break;
							}
							XSSFCell city = xssfRow.getCell(1);
							XSSFCell town = xssfRow.getCell(2);
							XSSFCell country = xssfRow.getCell(3);
							XSSFCell street = xssfRow.getCell(4);
							XSSFCell gasolineGross = xssfRow.getCell(5);
							XSSFCell dieselGross = xssfRow.getCell(6);
							XSSFCell natgasGross = xssfRow.getCell(7);
							XSSFCell gasolineSellMonth = xssfRow.getCell(8);
							XSSFCell dieselSellMonth = xssfRow.getCell(9);
							XSSFCell natgasSellMonth = xssfRow.getCell(10);
							XSSFCell gasolineSpear = xssfRow.getCell(11);
							XSSFCell dieselSpear = xssfRow.getCell(12);
							XSSFCell natgasSpear = xssfRow.getCell(13);
							XSSFCell recycleDevice = xssfRow.getCell(14);
							XSSFCell recovery = xssfRow.getCell(15);
							if(getValue(gasStationName).isEmpty()){
								break;
							}
							gas.setGasStationName(getValue(gasStationName));
							gas.setCityName(getValue(city));
							gas.setCity(citynum);
							gas.setTownName(getValue(town));
							gas.setGasolineGross(Double.valueOf(getValue(gasolineGross)));
							gas.setDieselGross(Double.valueOf(getValue(dieselGross)));
							gas.setNatgasGross(Double.valueOf(getValue(natgasGross)));
							gas.setGasolineSellMonth(Double.valueOf(getValue(gasolineSellMonth)));
							gas.setDieselSellMonth(Double.valueOf(getValue(dieselSellMonth)));
							gas.setNatgasSellMonth(Double.valueOf(getValue(natgasSellMonth)));
							gas.setGasolineSpear((int)Double.parseDouble(getValue(gasolineSpear)));
							gas.setDieselSpear((int)Double.parseDouble(getValue(dieselSpear)));
							gas.setNatgasSpear((int)Double.parseDouble(getValue(natgasSpear)));
							gas.setRecycleDevice((int)Double.parseDouble(getValue(recycleDevice)));
							gas.setRecovery(Double.valueOf(getValue(recovery)));
							gas.setCountryname(getValue(country));
							gas.setStreetname(getValue(street));
							list.add(gas);
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
	public List<Gasstation> readXls(String path,int citynum) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		Gasstation gas = null;
		List<Gasstation> list = new ArrayList<Gasstation>();
		// Read the Sheet
		
			
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
				// 读取行
				for (int rowNum = 5; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						// 记录行号
						rownum = rowNum;

						try {
							gas = new Gasstation();
							HSSFCell gasStationName = hssfRow.getCell(0);
							if(gasStationName==null){
								break;
							}
							HSSFCell city = hssfRow.getCell(1);
							HSSFCell town = hssfRow.getCell(2);
							HSSFCell country = hssfRow.getCell(3);
							HSSFCell street = hssfRow.getCell(4);
							HSSFCell gasolineGross = hssfRow.getCell(5);
							HSSFCell dieselGross = hssfRow.getCell(6);
							HSSFCell natgasGross = hssfRow.getCell(7);
							HSSFCell gasolineSellMonth = hssfRow.getCell(8);
							HSSFCell dieselSellMonth = hssfRow.getCell(9);
							HSSFCell natgasSellMonth = hssfRow.getCell(10);
							HSSFCell gasolineSpear = hssfRow.getCell(11);
							HSSFCell dieselSpear = hssfRow.getCell(12);
							HSSFCell natgasSpear = hssfRow.getCell(13);
							HSSFCell recycleDevice = hssfRow.getCell(14);
							HSSFCell recovery = hssfRow.getCell(15);
							if(getValue(gasStationName).isEmpty()){
								break;
							}
							gas.setGasStationName(getValue(gasStationName));
							gas.setCityName(getValue(city));
							gas.setCity(citynum);
							gas.setTownName(getValue(town));
							gas.setGasolineGross(Double.valueOf(getValue(gasolineGross)));
							gas.setDieselGross(Double.valueOf(getValue(dieselGross)));
							gas.setNatgasGross(Double.valueOf(getValue(natgasGross)));
							gas.setGasolineSellMonth(Double.valueOf(getValue(gasolineSellMonth)));
							gas.setDieselSellMonth(Double.valueOf(getValue(dieselSellMonth)));
							gas.setNatgasSellMonth(Double.valueOf(getValue(natgasSellMonth)));
							gas.setGasolineSpear((int)Double.parseDouble(getValue(gasolineSpear)));
							gas.setDieselSpear((int)Double.parseDouble(getValue(dieselSpear)));
							gas.setNatgasSpear((int)Double.parseDouble(getValue(natgasSpear)));
							gas.setRecycleDevice((int)Double.parseDouble(getValue(recycleDevice)));
							gas.setRecovery(Double.valueOf(getValue(recovery)));
							gas.setCountryname(getValue(country));
							gas.setStreetname(getValue(street));
							list.add(gas);
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
