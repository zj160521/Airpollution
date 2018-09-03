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

import com.xf.entity.gov.MotorVehicle;

public class ReadExcelMotor {
	
	
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
    
	public static List<MotorVehicle> readExcel(String path) throws Exception {
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
	public static List<MotorVehicle> readXlsx(String path) throws Exception {
		// 记录正在读取的行号
		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		MotorVehicle moto = null;
		List<MotorVehicle> list = new ArrayList<MotorVehicle>();
		// Read the Sheet
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
				// 读取一行
				for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow != null) {
						// 记录行号
						rownum = rowNum;
						try {
							moto = new MotorVehicle();
							XSSFCell administrationID = xssfRow.getCell(0);
							XSSFCell city = xssfRow.getCell(1);
							XSSFCell fillyear = xssfRow.getCell(2);
							XSSFCell variation = xssfRow.getCell(3);
							XSSFCell standard = xssfRow.getCell(4);
							if(xssfRow.getCell(1) == null || getValue(city).isEmpty() 
									||xssfRow.getCell(2) == null || getValue(fillyear).isEmpty()
									|| xssfRow.getCell(3) == null || getValue(variation).isEmpty() 
									||xssfRow.getCell(4) == null || getValue(standard).isEmpty()){
								break;
							}

							XSSFCell guestmini_rentgas = xssfRow.getCell(6);
							XSSFCell guestmini_rentrest = xssfRow.getCell(7);
							XSSFCell guestmini_restgas = xssfRow.getCell(8);
							XSSFCell guestmini_restrest = xssfRow.getCell(9);
							XSSFCell guestsmall_rentgas = xssfRow.getCell(10);
							XSSFCell guestsmall_rentdiesel = xssfRow.getCell(11);
							XSSFCell guestsmall_rentrest = xssfRow.getCell(12);
							XSSFCell guestsmall_restgas = xssfRow.getCell(13);
							XSSFCell guestsmall_restdiesel = xssfRow.getCell(14);
							XSSFCell guestsmall_restrest = xssfRow.getCell(15);
							
							XSSFCell guestmiddle_busgas = xssfRow.getCell(16);
							XSSFCell guestmiddle_busdiesel = xssfRow.getCell(17);
							XSSFCell guestmiddle_busrest = xssfRow.getCell(18);
							XSSFCell guestmiddle_restgas = xssfRow.getCell(19);
							XSSFCell guestmiddle_restdiesel = xssfRow.getCell(20);
							XSSFCell guestmiddle_restrest = xssfRow.getCell(21);
							XSSFCell guestlarge_busgas = xssfRow.getCell(22);
							XSSFCell guestlarge_busdiesel = xssfRow.getCell(23);
							XSSFCell guestlarge_busrest = xssfRow.getCell(24);
							XSSFCell guestlarge_restgas = xssfRow.getCell(25);
							
							XSSFCell guestlarge_restdiesel = xssfRow.getCell(26);
							XSSFCell guestlarge_restrest = xssfRow.getCell(27);
							XSSFCell goodsmini_gas = xssfRow.getCell(28);
							XSSFCell goodsmini_diesel = xssfRow.getCell(29);
							XSSFCell goodssmall_gas = xssfRow.getCell(30);
							XSSFCell goodssmall_diesel = xssfRow.getCell(31);
							XSSFCell goodsmiddle_gas = xssfRow.getCell(32);
							XSSFCell goodsmiddle_diesel = xssfRow.getCell(33);
							XSSFCell goodslarge_gas = xssfRow.getCell(34);
							XSSFCell goodslarge_diesel = xssfRow.getCell(35);
							
							XSSFCell tricycle = xssfRow.getCell(36);
							XSSFCell goodsslow = xssfRow.getCell(37);
							XSSFCell motorcycle_ordinary = xssfRow.getCell(38);
							XSSFCell motorcycle_light = xssfRow.getCell(39);
							
							try {
								if(xssfRow.getCell(0) != null && !getValue(administrationID).isEmpty()){
									Double.parseDouble(getValue(administrationID));
								}
							} catch (NumberFormatException e) {
								e.printStackTrace();
								break;
							}
							if(!getValue(administrationID).equals("")) moto.setAdministrationID((int)Double.parseDouble(getValue(administrationID)));
							if(!getValue(city).equals("")) moto.setCityName(getValue(city));
							if(!getValue(fillyear).equals("")) moto.setFillyear((int)Double.parseDouble(getValue(fillyear)));
							if(!getValue(variation).equals("")) moto.setVariation(getValue(variation));
							if(!getValue(standard).equals("")) moto.setStandard(getValue(standard));
							
							if(!getValue(guestmini_rentgas).equals("")) moto.setGuestmini_rentgas((int)Double.parseDouble(getValue(guestmini_rentgas)));
							if(!getValue(guestmini_rentrest).equals("")) moto.setGuestmini_rentrest((int)Double.parseDouble(getValue(guestmini_rentrest)));
							if(!getValue(guestmini_restgas).equals("")) moto.setGuestmini_restgas((int)Double.parseDouble(getValue(guestmini_restgas)));
							if(!getValue(guestmini_restrest).equals("")) moto.setGuestmini_restrest((int)Double.parseDouble(getValue(guestmini_restrest)));
							
							if(!getValue(guestsmall_rentgas).equals("")) moto.setGuestsmall_rentgas((int)Double.parseDouble(getValue(guestsmall_rentgas)));
							if(!getValue(guestsmall_rentdiesel).equals("")) moto.setGuestsmall_rentdiesel((int)Double.parseDouble(getValue(guestsmall_rentdiesel)));
							if(!getValue(guestsmall_rentrest).equals("")) moto.setGuestsmall_rentrest((int)Double.parseDouble(getValue(guestsmall_rentrest)));
							if(!getValue(guestsmall_restgas).equals("")) moto.setGuestsmall_restgas((int)Double.parseDouble(getValue(guestsmall_restgas)));
							if(!getValue(guestsmall_restdiesel).equals("")) moto.setGuestsmall_restdiesel((int)Double.parseDouble(getValue(guestsmall_restdiesel)));
							if(!getValue(guestsmall_restrest).equals("")) moto.setGuestsmall_restrest((int)Double.parseDouble(getValue(guestsmall_restrest)));
							
							if(!getValue(guestmiddle_busgas).equals("")) moto.setGuestmiddle_busgas((int)Double.parseDouble(getValue(guestmiddle_busgas)));
							if(!getValue(guestmiddle_busdiesel).equals("")) moto.setGuestmiddle_busdiesel((int)Double.parseDouble(getValue(guestmiddle_busdiesel)));
							if(!getValue(guestmiddle_busrest).equals("")) moto.setGuestmiddle_busrest((int)Double.parseDouble(getValue(guestmiddle_busrest)));
							if(!getValue(guestmiddle_restgas).equals("")) moto.setGuestmiddle_restgas((int)Double.parseDouble(getValue(guestmiddle_restgas)));
							if(!getValue(guestmiddle_restdiesel).equals("")) moto.setGuestmiddle_restdiesel((int)Double.parseDouble(getValue(guestmiddle_restdiesel)));
							if(!getValue(guestmiddle_restrest).equals("")) moto.setGuestmiddle_restrest((int)Double.parseDouble(getValue(guestmiddle_restrest)));
							
							if(!getValue(guestlarge_busgas).equals("")) moto.setGuestlarge_busgas((int)Double.parseDouble(getValue(guestlarge_busgas)));
							if(!getValue(guestlarge_busdiesel).equals("")) moto.setGuestlarge_busdiesel((int)Double.parseDouble(getValue(guestlarge_busdiesel)));
							if(!getValue(guestlarge_busrest).equals("")) moto.setGuestlarge_busrest((int)Double.parseDouble(getValue(guestlarge_busrest)));
							if(!getValue(guestlarge_restgas).equals("")) moto.setGuestlarge_restgas((int)Double.parseDouble(getValue(guestlarge_restgas)));
							if(!getValue(guestlarge_restdiesel).equals("")) moto.setGuestlarge_restdiesel((int)Double.parseDouble(getValue(guestlarge_restdiesel)));
							if(!getValue(guestlarge_restrest).equals("")) moto.setGuestlarge_restrest((int)Double.parseDouble(getValue(guestlarge_restrest)));
							
							if(!getValue(goodsmini_gas).equals("")) moto.setGoodsmini_gas((int)Double.parseDouble(getValue(goodsmini_gas)));
							if(!getValue(goodsmini_diesel).equals("")) moto.setGoodsmini_diesel((int)Double.parseDouble(getValue(goodsmini_diesel)));
							if(!getValue(goodssmall_gas).equals("")) moto.setGoodssmall_gas((int)Double.parseDouble(getValue(goodssmall_gas)));
							if(!getValue(goodssmall_diesel).equals("")) moto.setGoodssmall_diesel((int)Double.parseDouble(getValue(goodssmall_diesel)));
							if(!getValue(goodsmiddle_gas).equals("")) moto.setGoodsmiddle_gas((int)Double.parseDouble(getValue(goodsmiddle_gas)));
							if(!getValue(goodsmiddle_diesel).equals("")) moto.setGoodsmiddle_diesel((int)Double.parseDouble(getValue(goodsmiddle_diesel)));
							if(!getValue(goodslarge_gas).equals("")) moto.setGoodslarge_gas((int)Double.parseDouble(getValue(goodslarge_gas)));
							if(!getValue(goodslarge_diesel).equals("")) moto.setGoodslarge_diesel((int)Double.parseDouble(getValue(goodslarge_diesel)));
							
							if(!getValue(tricycle).equals("")) moto.setTricycle((int)Double.parseDouble(getValue(tricycle)));
							if(!getValue(goodsslow).equals("")) moto.setGoodsslow((int)Double.parseDouble(getValue(goodsslow)));
							if(!getValue(motorcycle_ordinary).equals("")) moto.setMotorcycle_ordinary((int)Double.parseDouble(getValue(motorcycle_ordinary)));
							if(!getValue(motorcycle_light).equals("")) moto.setMotorcycle_light((int)Double.parseDouble(getValue(motorcycle_light)));
							
							list.add(moto);
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
	public static List<MotorVehicle> readXls(String path) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		MotorVehicle moto = null;
		List<MotorVehicle> list = new ArrayList<MotorVehicle>();
		// Read the Sheet
		
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
				// 读取行
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						// 记录行号
						rownum = rowNum;
						
						try {
							moto = new MotorVehicle();
							HSSFCell administrationID = hssfRow.getCell(0);
							if(administrationID == null){
								break;
							}
							HSSFCell city = hssfRow.getCell(1);
							HSSFCell fillyear = hssfRow.getCell(2);
							HSSFCell variation = hssfRow.getCell(3);
							HSSFCell standard = hssfRow.getCell(4);

							HSSFCell guestmini_rentgas = hssfRow.getCell(6);
							HSSFCell guestmini_rentrest = hssfRow.getCell(7);
							HSSFCell guestmini_restgas = hssfRow.getCell(8);
							HSSFCell guestmini_restrest = hssfRow.getCell(9);
							HSSFCell guestsmall_rentgas = hssfRow.getCell(10);
							HSSFCell guestsmall_rentdiesel = hssfRow.getCell(11);
							HSSFCell guestsmall_rentrest = hssfRow.getCell(12);
							HSSFCell guestsmall_restgas = hssfRow.getCell(13);
							HSSFCell guestsmall_restdiesel = hssfRow.getCell(14);
							HSSFCell guestsmall_restrest = hssfRow.getCell(15);
							
							HSSFCell guestmiddle_busgas = hssfRow.getCell(16);
							HSSFCell guestmiddle_busdiesel = hssfRow.getCell(17);
							HSSFCell guestmiddle_busrest = hssfRow.getCell(18);
							HSSFCell guestmiddle_restgas = hssfRow.getCell(19);
							HSSFCell guestmiddle_restdiesel = hssfRow.getCell(20);
							HSSFCell guestmiddle_restrest = hssfRow.getCell(21);
							HSSFCell guestlarge_busgas = hssfRow.getCell(22);
							HSSFCell guestlarge_busdiesel = hssfRow.getCell(23);
							HSSFCell guestlarge_busrest = hssfRow.getCell(24);
							HSSFCell guestlarge_restgas = hssfRow.getCell(25);
							
							HSSFCell guestlarge_restdiesel = hssfRow.getCell(26);
							HSSFCell guestlarge_restrest = hssfRow.getCell(27);
							HSSFCell goodsmini_gas = hssfRow.getCell(28);
							HSSFCell goodsmini_diesel = hssfRow.getCell(29);
							HSSFCell goodssmall_gas = hssfRow.getCell(30);
							HSSFCell goodssmall_diesel = hssfRow.getCell(31);
							HSSFCell goodsmiddle_gas = hssfRow.getCell(32);
							HSSFCell goodsmiddle_diesel = hssfRow.getCell(33);
							HSSFCell goodslarge_gas = hssfRow.getCell(34);
							HSSFCell goodslarge_diesel = hssfRow.getCell(35);
							
							HSSFCell tricycle = hssfRow.getCell(36);
							HSSFCell goodsslow = hssfRow.getCell(37);
							HSSFCell motorcycle_ordinary = hssfRow.getCell(38);
							HSSFCell motorcycle_light = hssfRow.getCell(39);
							
							try {
								Double.parseDouble(getValue(administrationID));
							} catch (NumberFormatException e) {
								e.printStackTrace();
								break;
							}
							if(!getValue(administrationID).equals("")) moto.setAdministrationID((int)Double.parseDouble(getValue(administrationID)));
							if(!getValue(city).equals("")) moto.setCityName(getValue(city));
							if(!getValue(fillyear).equals("")) moto.setFillyear((int)Double.parseDouble(getValue(fillyear)));
							if(!getValue(variation).equals("")) moto.setVariation(getValue(variation));
							if(!getValue(standard).equals("")) moto.setStandard(getValue(standard));
							
							if(!getValue(guestmini_rentgas).equals("")) moto.setGuestmini_rentgas((int)Double.parseDouble(getValue(guestmini_rentgas)));
							if(!getValue(guestmini_rentrest).equals("")) moto.setGuestmini_rentrest((int)Double.parseDouble(getValue(guestmini_rentrest)));
							if(!getValue(guestmini_restgas).equals("")) moto.setGuestmini_restgas((int)Double.parseDouble(getValue(guestmini_restgas)));
							if(!getValue(guestmini_restrest).equals("")) moto.setGuestmini_restrest((int)Double.parseDouble(getValue(guestmini_restrest)));
							
							if(!getValue(guestsmall_rentgas).equals("")) moto.setGuestsmall_rentgas((int)Double.parseDouble(getValue(guestsmall_rentgas)));
							if(!getValue(guestsmall_rentdiesel).equals("")) moto.setGuestsmall_rentdiesel((int)Double.parseDouble(getValue(guestsmall_rentdiesel)));
							if(!getValue(guestsmall_rentrest).equals("")) moto.setGuestsmall_rentrest((int)Double.parseDouble(getValue(guestsmall_rentrest)));
							if(!getValue(guestsmall_restgas).equals("")) moto.setGuestsmall_restgas((int)Double.parseDouble(getValue(guestsmall_restgas)));
							if(!getValue(guestsmall_restdiesel).equals("")) moto.setGuestsmall_restdiesel((int)Double.parseDouble(getValue(guestsmall_restdiesel)));
							if(!getValue(guestsmall_restrest).equals("")) moto.setGuestsmall_restrest((int)Double.parseDouble(getValue(guestsmall_restrest)));
							
							if(!getValue(guestmiddle_busgas).equals("")) moto.setGuestmiddle_busgas((int)Double.parseDouble(getValue(guestmiddle_busgas)));
							if(!getValue(guestmiddle_busdiesel).equals("")) moto.setGuestmiddle_busdiesel((int)Double.parseDouble(getValue(guestmiddle_busdiesel)));
							if(!getValue(guestmiddle_busrest).equals("")) moto.setGuestmiddle_busrest((int)Double.parseDouble(getValue(guestmiddle_busrest)));
							if(!getValue(guestmiddle_restgas).equals("")) moto.setGuestmiddle_restgas((int)Double.parseDouble(getValue(guestmiddle_restgas)));
							if(!getValue(guestmiddle_restdiesel).equals("")) moto.setGuestmiddle_restdiesel((int)Double.parseDouble(getValue(guestmiddle_restdiesel)));
							if(!getValue(guestmiddle_restrest).equals("")) moto.setGuestmiddle_restrest((int)Double.parseDouble(getValue(guestmiddle_restrest)));
							
							if(!getValue(guestlarge_busgas).equals("")) moto.setGuestlarge_busgas((int)Double.parseDouble(getValue(guestlarge_busgas)));
							if(!getValue(guestlarge_busdiesel).equals("")) moto.setGuestlarge_busdiesel((int)Double.parseDouble(getValue(guestlarge_busdiesel)));
							if(!getValue(guestlarge_busrest).equals("")) moto.setGuestlarge_busrest((int)Double.parseDouble(getValue(guestlarge_busrest)));
							if(!getValue(guestlarge_restgas).equals("")) moto.setGuestlarge_restgas((int)Double.parseDouble(getValue(guestlarge_restgas)));
							if(!getValue(guestlarge_restdiesel).equals("")) moto.setGuestlarge_restdiesel((int)Double.parseDouble(getValue(guestlarge_restdiesel)));
							if(!getValue(guestlarge_restrest).equals("")) moto.setGuestlarge_restrest((int)Double.parseDouble(getValue(guestlarge_restrest)));
							
							if(!getValue(goodsmini_gas).equals("")) moto.setGoodsmini_gas((int)Double.parseDouble(getValue(goodsmini_gas)));
							if(!getValue(goodsmini_diesel).equals("")) moto.setGoodsmini_diesel((int)Double.parseDouble(getValue(goodsmini_diesel)));
							if(!getValue(goodssmall_gas).equals("")) moto.setGoodssmall_gas((int)Double.parseDouble(getValue(goodssmall_gas)));
							if(!getValue(goodssmall_diesel).equals("")) moto.setGoodssmall_diesel((int)Double.parseDouble(getValue(goodssmall_diesel)));
							if(!getValue(goodsmiddle_gas).equals("")) moto.setGoodsmiddle_gas((int)Double.parseDouble(getValue(goodsmiddle_gas)));
							if(!getValue(goodsmiddle_diesel).equals("")) moto.setGoodsmiddle_diesel((int)Double.parseDouble(getValue(goodsmiddle_diesel)));
							if(!getValue(goodslarge_gas).equals("")) moto.setGoodslarge_gas((int)Double.parseDouble(getValue(goodslarge_gas)));
							if(!getValue(goodslarge_diesel).equals("")) moto.setGoodslarge_diesel((int)Double.parseDouble(getValue(goodslarge_diesel)));
							
							if(!getValue(tricycle).equals("")) moto.setTricycle((int)Double.parseDouble(getValue(tricycle)));
							if(!getValue(goodsslow).equals("")) moto.setGoodsslow((int)Double.parseDouble(getValue(goodsslow)));
							if(!getValue(motorcycle_ordinary).equals("")) moto.setMotorcycle_ordinary((int)Double.parseDouble(getValue(motorcycle_ordinary)));
							if(!getValue(motorcycle_light).equals("")) moto.setMotorcycle_light((int)Double.parseDouble(getValue(motorcycle_light)));
						
							list.add(moto);

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
		if(xssfRow == null)
			return "";
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
