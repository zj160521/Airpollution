package com.xf.readexcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.xf.entity.gov.Gknumber;

//锅炉窑炉数量调查表Excel导入

public class ReadExcelGknumber {

	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
	public static final String EMPTY = "";
	public static final String POINT = ".";
	public static final String LIB_PATH = "lib";
	public static final String STUDENT_INFO_XLS_PATH = LIB_PATH
			+ "/student_info" + POINT + OFFICE_EXCEL_2003_POSTFIX;
	public static final String STUDENT_INFO_XLSX_PATH = LIB_PATH
			+ "/student_info" + POINT + OFFICE_EXCEL_2010_POSTFIX;
	public static final String NOT_EXCEL_FILE = " : 不是 Excel 文件类型!";
	public static final String PROCESSING = "正在读取...";
	public static int rownum;

	public List<Gknumber> readExcel(String path, int citynum)
			throws IOException {
		// 判断路径是否为空
		if (path == null || EMPTY.equals(path)) {
			return null;
		} else {
			// 截取文件后缀
			String postfix = getPostfix(path);
			if (!EMPTY.equals(postfix)) {
				// 判断excel的格
				if (OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
					return readXls(path, citynum);
				} else if (OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
					return readXlsx(path, citynum);
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
	public List<Gknumber> readXlsx(String path, int citynum) throws IOException {
		// 记录正在读取的行

		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		Gknumber Gknu = null;
		List<Gknumber> list = new ArrayList<Gknumber>();
		// Read the Sheet

		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// 读取
			for (int rowNum = 5; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {
					// 记录行号
					rownum = rowNum;
					try {
						Gknu = new Gknumber();
						XSSFCell companyName = xssfRow.getCell(1);
						XSSFCell city = xssfRow.getCell(2);
						if (city == null) {
							break;
						}
						XSSFCell town = xssfRow.getCell(3);
						XSSFCell country = xssfRow.getCell(4);
						XSSFCell street = xssfRow.getCell(5);
						XSSFCell model = xssfRow.getCell(6);
						XSSFCell gktype = xssfRow.getCell(7);
						XSSFCell shippingTon = xssfRow.getCell(8);
						XSSFCell fuelType = xssfRow.getCell(9);
						XSSFCell yearlyFuel = xssfRow.getCell(10);
						XSSFCell unit = xssfRow.getCell(11);
						XSSFCell dateUsed = xssfRow.getCell(12);
						String ret = null;
						if (getValue(companyName).isEmpty())
							break;
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd");
						SimpleDateFormat form = new SimpleDateFormat(
								"yyyy/MM/dd");
						formatter.setLenient(false);
						form.setLenient(false);
						try {
							if (DateUtil.isCellDateFormatted(dateUsed)) {
								Date theDate = dateUsed.getDateCellValue();
								ret = formatter.format(theDate);
							}
						} catch (IllegalStateException e) {
							try {
								form.parse(getValue(dateUsed));
								ret = getValue(dateUsed);
							} catch (ParseException ex) {
								formatter.parse(getValue(dateUsed));
								ret = getValue(dateUsed);
							}
						}
						XSSFCell height = xssfRow.getCell(13);
						Gknu.setCompanyName(getValue(companyName));
						Gknu.setCity(citynum);
						Gknu.setCityName(getValue(city));
						Gknu.setTownName(getValue(town));
						Gknu.setModel(getValue(model));
						Gknu.setGktypeName(getValue(gktype));
						Gknu.setShippingTon(Double
								.valueOf(getValue(shippingTon)));
						Gknu.setFuelTypeName(getValue(fuelType));
						Gknu.setYearlyFuel(Double.valueOf(getValue(yearlyFuel)));
						Gknu.setUnit(getValue(unit));
						Gknu.setDateUsed(ret);
						Gknu.setHeight(Double.valueOf(getValue(height)));
						Gknu.setCountryname(getValue(country));
						Gknu.setStreetname(getValue(street));
						list.add(Gknu);
					} catch (Exception e) {
						e.printStackTrace();
						throw new MyException("导入第" + (rownum + 1) + "行出错");
					}
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
	public List<Gknumber> readXls(String path, int citynum) throws IOException {
		// 记录正在读取的行

		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		Gknumber Gknu = null;
		List<Gknumber> list = new ArrayList<Gknumber>();

		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 读取
			for (int rowNum = 5; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					// 记录行号
					rownum = rowNum;

					try {
						Gknu = new Gknumber();
						HSSFCell companyName = hssfRow.getCell(1);
						HSSFCell city = hssfRow.getCell(2);
						if (city == null) {
							break;
						}
						HSSFCell town = hssfRow.getCell(3);
						HSSFCell country = hssfRow.getCell(4);
						HSSFCell street = hssfRow.getCell(5);
						HSSFCell model = hssfRow.getCell(6);
						HSSFCell gktype = hssfRow.getCell(7);
						HSSFCell shippingTon = hssfRow.getCell(8);
						HSSFCell fuelType = hssfRow.getCell(9);
						HSSFCell yearlyFuel = hssfRow.getCell(10);
						HSSFCell unit = hssfRow.getCell(11);
						HSSFCell dateUsed = hssfRow.getCell(12);
						HSSFCell height = hssfRow.getCell(13);
						if (getValue(companyName).isEmpty())
							break;
						String ret = null;
						if (getValue(companyName).isEmpty())
							break;
						
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd");
						SimpleDateFormat form = new SimpleDateFormat(
								"yyyy/MM/dd");
						try {
							if (DateUtil.isCellDateFormatted(dateUsed)) {
								Date theDate = dateUsed.getDateCellValue();
								ret = formatter.format(theDate);
							}
						} catch (IllegalStateException e) {
							try {
								form.parse(getValue(dateUsed));
								ret = getValue(dateUsed);
							} catch (ParseException ex) {
								formatter.parse(getValue(dateUsed));
								ret = getValue(dateUsed);
							}
						}
						Gknu.setCompanyName(getValue(companyName));
						Gknu.setCity(citynum);
						Gknu.setCityName(getValue(city));
						Gknu.setTownName(getValue(town));
						Gknu.setModel(getValue(model));
						Gknu.setGktypeName(getValue(gktype));
						Gknu.setShippingTon(Double
								.valueOf(getValue(shippingTon)));
						Gknu.setFuelTypeName(getValue(fuelType));
						Gknu.setYearlyFuel(Double.valueOf(getValue(yearlyFuel)));
						Gknu.setUnit(getValue(unit));
						Gknu.setDateUsed(ret);
						Gknu.setHeight(Double.valueOf(getValue(height)));
						Gknu.setCountryname(getValue(country));
						Gknu.setStreetname(getValue(street));
						list.add(Gknu);
					} catch (Exception e) {
						e.printStackTrace();
						throw new MyException("导入第" + (rownum + 1) + "行出错");
					}
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
