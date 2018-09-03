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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.xf.entity.gov.VehicleFactor;

public class ReadExcelVehiclfactor {
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

	public static List<VehicleFactor> readExcel(String path) throws Exception {
		// 判断路径是否为空
		if (path == null || EMPTY.equals(path)) {
			return null;
		} else {
			// 截取文件后缀名
			String postfix = getPostfix(path);
			if (!EMPTY.equals(postfix)) {
				// 判断excel的格式
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
	public static List<VehicleFactor> readXlsx(String path) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		VehicleFactor vehi = null;
		List<VehicleFactor> list = new ArrayList<VehicleFactor>();
		// Read the Sheet

		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		// 读取一行
		for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			if (xssfRow != null) {
				// 记录行号
				rownum = rowNum;
				try {
					vehi = new VehicleFactor();
					String pollutant=getMergedRegionValue(xssfSheet,rowNum,0);
					if (pollutant==null) {
						break;
					}
					XSSFCell standard = xssfRow.getCell(1);
					XSSFCell guestmini_rentgas = xssfRow.getCell(2);
					XSSFCell guestmini_rentrest = xssfRow.getCell(3);
					XSSFCell guestmini_restgas = xssfRow.getCell(4);
					XSSFCell guestmini_restrest = xssfRow.getCell(5);
					XSSFCell guestsmall_rentgas = xssfRow.getCell(6);
					XSSFCell guestsmall_rentdiesel = xssfRow.getCell(7);
					XSSFCell guestsmall_rentrest = xssfRow.getCell(8);
					XSSFCell guestsmall_restgas = xssfRow.getCell(9);
					XSSFCell guestsmall_restdiesel = xssfRow.getCell(10);
					XSSFCell guestsmall_restrest = xssfRow.getCell(11);
					XSSFCell guestmiddle_busgas = xssfRow.getCell(12);
					XSSFCell guestmiddle_busdiesel = xssfRow.getCell(13);
					XSSFCell guestmiddle_busrest = xssfRow.getCell(14);
					XSSFCell guestmiddle_restgas = xssfRow.getCell(15);
					XSSFCell guestmiddle_restdiesel = xssfRow.getCell(16);
					XSSFCell guestmiddle_restrest = xssfRow.getCell(17);
					XSSFCell guestlarge_busgas = xssfRow.getCell(18);
					XSSFCell guestlarge_busdiesel = xssfRow.getCell(19);
					XSSFCell guestlarge_busrest = xssfRow.getCell(20);
					XSSFCell guestlarge_restgas = xssfRow.getCell(21);
					XSSFCell guestlarge_restdiesel = xssfRow.getCell(22);
					XSSFCell guestlarge_restrest = xssfRow.getCell(23);
					XSSFCell goodsmini_gas = xssfRow.getCell(24);
					XSSFCell goodsmini_diesel = xssfRow.getCell(25);
					XSSFCell goodssmall_gas = xssfRow.getCell(26);
					XSSFCell goodssmall_diesel = xssfRow.getCell(27);
					XSSFCell goodsmiddle_gas = xssfRow.getCell(28);
					XSSFCell goodsmiddle_diesel = xssfRow.getCell(29);
					XSSFCell goodslarge_gas = xssfRow.getCell(30);
					XSSFCell goodslarge_diesel = xssfRow.getCell(31);
					XSSFCell tricycle = xssfRow.getCell(32);
					XSSFCell goodsslow = xssfRow.getCell(33);
					XSSFCell motorcycle_ordinary = xssfRow.getCell(34);
					XSSFCell motorcycle_light = xssfRow.getCell(35);
					vehi.setPollutant(pollutant);
					vehi.setStandard(getValue(standard));
					if (!getValue(guestmini_rentgas).isEmpty())
						vehi.setGuestmini_rentgas(Double
								.parseDouble(getValue(guestmini_rentgas)));
					if (!getValue(guestmini_rentrest).isEmpty())
						vehi.setGuestmini_rentrest(Double
								.parseDouble(getValue(guestmini_rentrest)));
					if (!getValue(guestmini_restgas).isEmpty())
						vehi.setGuestmini_restgas(Double
								.parseDouble(getValue(guestmini_restgas)));
					if (!getValue(guestmini_restrest).isEmpty())
						vehi.setGuestmini_restrest(Double
								.parseDouble(getValue(guestmini_restrest)));
					if (!getValue(guestsmall_rentgas).isEmpty())
						vehi.setGuestsmall_rentgas(Double
								.parseDouble(getValue(guestsmall_rentgas)));
					if (!getValue(guestsmall_rentdiesel).isEmpty())
						vehi.setGuestsmall_rentdiesel(Double
								.parseDouble(getValue(guestsmall_rentdiesel)));
					if (!getValue(guestsmall_rentrest).isEmpty())
						vehi.setGuestsmall_rentrest(Double
								.parseDouble(getValue(guestsmall_rentrest)));
					if (!getValue(guestsmall_restgas).isEmpty())
						vehi.setGuestsmall_restgas(Double
								.parseDouble(getValue(guestsmall_restgas)));
					if (!getValue(guestsmall_restdiesel).isEmpty())
						vehi.setGuestsmall_restdiesel(Double
								.parseDouble(getValue(guestsmall_restdiesel)));
					if (!getValue(guestsmall_restrest).isEmpty())
						vehi.setGuestsmall_restrest(Double
								.parseDouble(getValue(guestsmall_restrest)));
					if (!getValue(guestmiddle_busgas).isEmpty())
						vehi.setGuestmiddle_busgas(Double
								.parseDouble(getValue(guestmiddle_busgas)));
					if (!getValue(guestmiddle_busdiesel).isEmpty())
						vehi.setGuestmiddle_busdiesel(Double
								.parseDouble(getValue(guestmiddle_busdiesel)));
					if (!getValue(guestmiddle_busrest).isEmpty())
						vehi.setGuestmiddle_busrest(Double
								.parseDouble(getValue(guestmiddle_busrest)));
					if (!getValue(guestmiddle_restgas).isEmpty())
						vehi.setGuestmiddle_restgas(Double
								.parseDouble(getValue(guestmiddle_restgas)));
					if (!getValue(guestmiddle_restdiesel).isEmpty())
						vehi.setGuestmiddle_restdiesel(Double
								.parseDouble(getValue(guestmiddle_restdiesel)));
					if (!getValue(guestmiddle_restrest).isEmpty())
						vehi.setGuestmiddle_restrest(Double
								.parseDouble(getValue(guestmiddle_restrest)));
					if (!getValue(guestlarge_busgas).isEmpty())
						vehi.setGuestlarge_busgas(Double
								.parseDouble(getValue(guestlarge_busgas)));
					if (!getValue(guestlarge_busdiesel).isEmpty())
						vehi.setGuestlarge_busdiesel(Double
								.parseDouble(getValue(guestlarge_busdiesel)));
					if (!getValue(guestlarge_busrest).isEmpty())
						vehi.setGuestlarge_busrest(Double
								.parseDouble(getValue(guestlarge_busrest)));
					if (!getValue(guestlarge_restgas).isEmpty())
						vehi.setGuestlarge_restgas(Double
								.parseDouble(getValue(guestlarge_restgas)));
					if (!getValue(guestlarge_restdiesel).isEmpty())
						vehi.setGuestlarge_restdiesel(Double
								.parseDouble(getValue(guestlarge_restdiesel)));
					if (!getValue(guestlarge_restrest).isEmpty())
						vehi.setGuestlarge_restrest(Double
								.parseDouble(getValue(guestlarge_restrest)));
					if (!getValue(goodsmini_gas).isEmpty())
						vehi.setGoodsmini_gas(Double
								.parseDouble(getValue(goodsmini_gas)));
					if (!getValue(goodsmini_diesel).isEmpty())
						vehi.setGoodsmini_diesel(Double
								.parseDouble(getValue(goodsmini_diesel)));
					if (!getValue(goodssmall_gas).isEmpty())
						vehi.setGoodssmall_gas(Double
								.parseDouble(getValue(goodssmall_gas)));
					if (!getValue(goodssmall_diesel).isEmpty())
						vehi.setGoodssmall_diesel(Double
								.parseDouble(getValue(goodssmall_diesel)));
					if (!getValue(goodsmiddle_gas).isEmpty())
						vehi.setGoodsmiddle_gas(Double
								.parseDouble(getValue(goodsmiddle_gas)));
					if (!getValue(goodsmiddle_diesel).isEmpty())
						vehi.setGoodsmiddle_diesel(Double
								.parseDouble(getValue(goodsmiddle_diesel)));
					if (!getValue(goodslarge_gas).isEmpty())
						vehi.setGoodslarge_gas(Double
								.parseDouble(getValue(goodslarge_gas)));
					if (!getValue(goodslarge_diesel).isEmpty())
						vehi.setGoodslarge_diesel(Double
								.parseDouble(getValue(goodslarge_diesel)));
					if (!getValue(tricycle).isEmpty())
						vehi.setTricycle(Double.parseDouble(getValue(tricycle)));
					if (!getValue(goodsslow).isEmpty())
						vehi.setGoodsslow(Double
								.parseDouble(getValue(goodsslow)));
					if (!getValue(motorcycle_ordinary).isEmpty())
						vehi.setMotorcycle_ordinary(Double
								.parseDouble(getValue(motorcycle_ordinary)));
					if (!getValue(motorcycle_light).isEmpty())
						vehi.setMotorcycle_light(Double
								.parseDouble(getValue(motorcycle_light)));
					list.add(vehi);
				} catch (Exception e) {

					e.printStackTrace();
					throw new MyException("导入第" + (rownum + 1) + "行出错");
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
	public static List<VehicleFactor> readXls(String path) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		VehicleFactor vehi = null;
		List<VehicleFactor> list = new ArrayList<VehicleFactor>();
		// Read the Sheet

		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		// 读取行
		for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (hssfRow != null) {
				// 记录行号
				rownum = rowNum;

				try {
					vehi = new VehicleFactor();
					String pollutant=getMergedRegionValue(hssfSheet,rowNum,0);
					if (pollutant==null) {
						break;
					}
					HSSFCell standard = hssfRow.getCell(1);
					HSSFCell guestmini_rentgas = hssfRow.getCell(2);
					HSSFCell guestmini_rentrest = hssfRow.getCell(3);
					HSSFCell guestmini_restgas = hssfRow.getCell(4);
					HSSFCell guestmini_restrest = hssfRow.getCell(5);
					HSSFCell guestsmall_rentgas = hssfRow.getCell(6);
					HSSFCell guestsmall_rentdiesel = hssfRow.getCell(7);
					HSSFCell guestsmall_rentrest = hssfRow.getCell(8);
					HSSFCell guestsmall_restgas = hssfRow.getCell(9);
					HSSFCell guestsmall_restdiesel = hssfRow.getCell(10);
					HSSFCell guestsmall_restrest = hssfRow.getCell(11);
					HSSFCell guestmiddle_busgas = hssfRow.getCell(12);
					HSSFCell guestmiddle_busdiesel = hssfRow.getCell(13);
					HSSFCell guestmiddle_busrest = hssfRow.getCell(14);
					HSSFCell guestmiddle_restgas = hssfRow.getCell(15);
					HSSFCell guestmiddle_restdiesel = hssfRow.getCell(16);
					HSSFCell guestmiddle_restrest = hssfRow.getCell(17);
					HSSFCell guestlarge_busgas = hssfRow.getCell(18);
					HSSFCell guestlarge_busdiesel = hssfRow.getCell(19);
					HSSFCell guestlarge_busrest = hssfRow.getCell(20);
					HSSFCell guestlarge_restgas = hssfRow.getCell(21);
					HSSFCell guestlarge_restdiesel = hssfRow.getCell(22);
					HSSFCell guestlarge_restrest = hssfRow.getCell(23);
					HSSFCell goodsmini_gas = hssfRow.getCell(24);
					HSSFCell goodsmini_diesel = hssfRow.getCell(25);
					HSSFCell goodssmall_gas = hssfRow.getCell(26);
					HSSFCell goodssmall_diesel = hssfRow.getCell(27);
					HSSFCell goodsmiddle_gas = hssfRow.getCell(28);
					HSSFCell goodsmiddle_diesel = hssfRow.getCell(29);
					HSSFCell goodslarge_gas = hssfRow.getCell(30);
					HSSFCell goodslarge_diesel = hssfRow.getCell(31);
					HSSFCell tricycle = hssfRow.getCell(32);
					HSSFCell goodsslow = hssfRow.getCell(33);
					HSSFCell motorcycle_ordinary = hssfRow.getCell(34);
					HSSFCell motorcycle_light = hssfRow.getCell(35);
					vehi.setPollutant(pollutant);
					vehi.setStandard(getValue(standard));
					if (!getValue(guestmini_rentgas).isEmpty())
						vehi.setGuestmini_rentgas(Double
								.parseDouble(getValue(guestmini_rentgas)));
					if (!getValue(guestmini_rentrest).isEmpty())
						vehi.setGuestmini_rentrest(Double
								.parseDouble(getValue(guestmini_rentrest)));
					if (!getValue(guestmini_restgas).isEmpty())
						vehi.setGuestmini_restgas(Double
								.parseDouble(getValue(guestmini_restgas)));
					if (!getValue(guestmini_restrest).isEmpty())
						vehi.setGuestmini_restrest(Double
								.parseDouble(getValue(guestmini_restrest)));
					if (!getValue(guestsmall_rentgas).isEmpty())
						vehi.setGuestsmall_rentgas(Double
								.parseDouble(getValue(guestsmall_rentgas)));
					if (!getValue(guestsmall_rentdiesel).isEmpty())
						vehi.setGuestsmall_rentdiesel(Double
								.parseDouble(getValue(guestsmall_rentdiesel)));
					if (!getValue(guestsmall_rentrest).isEmpty())
						vehi.setGuestsmall_rentrest(Double
								.parseDouble(getValue(guestsmall_rentrest)));
					if (!getValue(guestsmall_restgas).isEmpty())
						vehi.setGuestsmall_restgas(Double
								.parseDouble(getValue(guestsmall_restgas)));
					if (!getValue(guestsmall_restdiesel).isEmpty())
						vehi.setGuestsmall_restdiesel(Double
								.parseDouble(getValue(guestsmall_restdiesel)));
					if (!getValue(guestsmall_restrest).isEmpty())
						vehi.setGuestsmall_restrest(Double
								.parseDouble(getValue(guestsmall_restrest)));
					if (!getValue(guestmiddle_busgas).isEmpty())
						vehi.setGuestmiddle_busgas(Double
								.parseDouble(getValue(guestmiddle_busgas)));
					if (!getValue(guestmiddle_busdiesel).isEmpty())
						vehi.setGuestmiddle_busdiesel(Double
								.parseDouble(getValue(guestmiddle_busdiesel)));
					if (!getValue(guestmiddle_busrest).isEmpty())
						vehi.setGuestmiddle_busrest(Double
								.parseDouble(getValue(guestmiddle_busrest)));
					if (!getValue(guestmiddle_restgas).isEmpty())
						vehi.setGuestmiddle_restgas(Double
								.parseDouble(getValue(guestmiddle_restgas)));
					if (!getValue(guestmiddle_restdiesel).isEmpty())
						vehi.setGuestmiddle_restdiesel(Double
								.parseDouble(getValue(guestmiddle_restdiesel)));
					if (!getValue(guestmiddle_restrest).isEmpty())
						vehi.setGuestmiddle_restrest(Double
								.parseDouble(getValue(guestmiddle_restrest)));
					if (!getValue(guestlarge_busgas).isEmpty())
						vehi.setGuestlarge_busgas(Double
								.parseDouble(getValue(guestlarge_busgas)));
					if (!getValue(guestlarge_busdiesel).isEmpty())
						vehi.setGuestlarge_busdiesel(Double
								.parseDouble(getValue(guestlarge_busdiesel)));
					if (!getValue(guestlarge_busrest).isEmpty())
						vehi.setGuestlarge_busrest(Double
								.parseDouble(getValue(guestlarge_busrest)));
					if (!getValue(guestlarge_restgas).isEmpty())
						vehi.setGuestlarge_restgas(Double
								.parseDouble(getValue(guestlarge_restgas)));
					if (!getValue(guestlarge_restdiesel).isEmpty())
						vehi.setGuestlarge_restdiesel(Double
								.parseDouble(getValue(guestlarge_restdiesel)));
					if (!getValue(guestlarge_restrest).isEmpty())
						vehi.setGuestlarge_restrest(Double
								.parseDouble(getValue(guestlarge_restrest)));
					if (!getValue(goodsmini_gas).isEmpty())
						vehi.setGoodsmini_gas(Double
								.parseDouble(getValue(goodsmini_gas)));
					if (!getValue(goodsmini_diesel).isEmpty())
						vehi.setGoodsmini_diesel(Double
								.parseDouble(getValue(goodsmini_diesel)));
					if (!getValue(goodssmall_gas).isEmpty())
						vehi.setGoodssmall_gas(Double
								.parseDouble(getValue(goodssmall_gas)));
					if (!getValue(goodssmall_diesel).isEmpty())
						vehi.setGoodssmall_diesel(Double
								.parseDouble(getValue(goodssmall_diesel)));
					if (!getValue(goodsmiddle_gas).isEmpty())
						vehi.setGoodsmiddle_gas(Double
								.parseDouble(getValue(goodsmiddle_gas)));
					if (!getValue(goodsmiddle_diesel).isEmpty())
						vehi.setGoodsmiddle_diesel(Double
								.parseDouble(getValue(goodsmiddle_diesel)));
					if (!getValue(goodslarge_gas).isEmpty())
						vehi.setGoodslarge_gas(Double
								.parseDouble(getValue(goodslarge_gas)));
					if (!getValue(goodslarge_diesel).isEmpty())
						vehi.setGoodslarge_diesel(Double
								.parseDouble(getValue(goodslarge_diesel)));
					if (!getValue(tricycle).isEmpty())
						vehi.setTricycle(Double.parseDouble(getValue(tricycle)));
					if (!getValue(goodsslow).isEmpty())
						vehi.setGoodsslow(Double
								.parseDouble(getValue(goodsslow)));
					if (!getValue(motorcycle_ordinary).isEmpty())
						vehi.setMotorcycle_ordinary(Double
								.parseDouble(getValue(motorcycle_ordinary)));
					if (!getValue(motorcycle_light).isEmpty())
						vehi.setMotorcycle_light(Double
								.parseDouble(getValue(motorcycle_light)));
					list.add(vehi);
				} catch (Exception e) {
					e.printStackTrace();
					throw new MyException("导入第" + (rownum + 1) + "行出错");
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

	public static boolean isMergedRegion(Sheet sheet, Cell cell) {
		// 得到一个sheet中有多少个合并单元格
		int sheetmergerCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetmergerCount; i++) {
			// 得出具体的合并单元格
			CellRangeAddress ca = sheet.getMergedRegion(i);
			// 得到合并单元格的起始行, 结束行, 起始列, 结束列
			int firstC = ca.getFirstColumn();
			int lastC = ca.getLastColumn();
			int firstR = ca.getFirstRow();
			int lastR = ca.getLastRow();
			// 判断该单元格是否在合并单元格范围之内, 如果是, 则返回 true
			if (cell.getColumnIndex() <= lastC
					&& cell.getColumnIndex() >= firstC) {
				if (cell.getRowIndex() <= lastR && cell.getRowIndex() >= firstR) {
					return true;
				}
			}
		}
		return false;
	}

	public static String getMergedRegionValue(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);

					return getValue((XSSFCell) fCell);
				}
			}
		}

		return null;
	}
}
