package com.xf.readexcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.xf.entity.gov.VehicleAction;

public class ReadExcelVehicl {
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

	public static List<VehicleAction> readExcel(String path) throws Exception {
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
	public static List<VehicleAction> readXlsx(String path) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		VehicleAction vehi = null;
		List<VehicleAction> list = new ArrayList<VehicleAction>();
		// Read the Sheet

		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		// 读取一行
		for (int rowNum = 3; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			if (xssfRow != null) {
				// 记录行号
				rownum = rowNum;
				try {
					vehi = new VehicleAction();
					XSSFCell serial = xssfRow.getCell(0);
					if (getValue(serial).isEmpty()) {
						break;
					}
					XSSFCell vehicletype = xssfRow.getCell(1);
					XSSFCell platescolor = xssfRow.getCell(2);
					XSSFCell registerdate = xssfRow.getCell(3);
					XSSFCell checkdate = xssfRow.getCell(4);
					String regis = null;
					String check = null;
					regis=getDate(registerdate);
					check=getDate(checkdate);

					XSSFCell mileage = xssfRow.getCell(5);
					XSSFCell gastype = xssfRow.getCell(6);
					vehi.setSerial((int) Double.parseDouble(getValue(serial)));
					vehi.setVehicletype(getValue(vehicletype));
					vehi.setPlatescolor(getValue(platescolor));
					vehi.setRegisterdate(regis);
					vehi.setCheckdate(check);
					vehi.setMileage(Double.parseDouble(getValue(mileage)));
					vehi.setGastype(getValue(gastype));
					
					if(getValue(vehicletype).equals("公交车") && (getValue(gastype).equals("汽油")||getValue(gastype).equals("柴油")||getValue(gastype).equals("其他"))){
						VehicleAction vehi1=new VehicleAction();
						vehi1.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi1.setVehicletype(getValue(vehicletype));
						vehi1.setPlatescolor(getValue(platescolor));
						vehi1.setRegisterdate(regis);
						vehi1.setCheckdate(check);
						vehi1.setMileage(Double.parseDouble(getValue(mileage)));
						vehi1.setGastype(getValue(gastype));
						vehi1.setVehicletype("中型公交车");
						list.add(vehi1);
						VehicleAction vehi2=new VehicleAction();
						vehi2.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi2.setVehicletype(getValue(vehicletype));
						vehi2.setPlatescolor(getValue(platescolor));
						vehi2.setRegisterdate(regis);
						vehi2.setCheckdate(check);
						vehi2.setMileage(Double.parseDouble(getValue(mileage)));
						vehi2.setGastype(getValue(gastype));
						vehi2.setVehicletype("大型公交车");
						list.add(vehi2);
					}else if(getValue(vehicletype).equals("出租车") && (getValue(gastype).equals("汽油")||getValue(gastype).equals("柴油")||getValue(gastype).equals("其他"))){
						VehicleAction vehi1=new VehicleAction();
						vehi1.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi1.setVehicletype(getValue(vehicletype));
						vehi1.setPlatescolor(getValue(platescolor));
						vehi1.setRegisterdate(regis);
						vehi1.setCheckdate(check);
						vehi1.setMileage(Double.parseDouble(getValue(mileage)));
						vehi1.setGastype(getValue(gastype));
						vehi1.setVehicletype("微型出租车");
						list.add(vehi1);
						VehicleAction vehi2=new VehicleAction();
						vehi2.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi2.setVehicletype(getValue(vehicletype));
						vehi2.setPlatescolor(getValue(platescolor));
						vehi2.setRegisterdate(regis);
						vehi2.setCheckdate(check);
						vehi2.setMileage(Double.parseDouble(getValue(mileage)));
						vehi2.setGastype(getValue(gastype));
						vehi2.setVehicletype("小型出租车");
						list.add(vehi2);
					}else if(getValue(vehicletype).equals("小型载客车") && (getValue(gastype).equals("汽油")||getValue(gastype).equals("柴油")||getValue(gastype).equals("其他"))){
						VehicleAction vehi1=new VehicleAction();
						vehi1.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi1.setVehicletype(getValue(vehicletype));
						vehi1.setPlatescolor(getValue(platescolor));
						vehi1.setRegisterdate(regis);
						vehi1.setCheckdate(check);
						vehi1.setMileage(Double.parseDouble(getValue(mileage)));
						vehi1.setGastype(getValue(gastype));
						vehi1.setVehicletype("微型载客车");
						list.add(vehi1);
						VehicleAction vehi2=new VehicleAction();
						vehi2.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi2.setVehicletype(getValue(vehicletype));
						vehi2.setPlatescolor(getValue(platescolor));
						vehi2.setRegisterdate(regis);
						vehi2.setCheckdate(check);
						vehi2.setMileage(Double.parseDouble(getValue(mileage)));
						vehi2.setGastype(getValue(gastype));
						vehi2.setVehicletype("小型载客车");
						list.add(vehi2);
					}else if(getValue(vehicletype).equals("轻型载货车") && (getValue(gastype).equals("汽油")||getValue(gastype).equals("柴油")||getValue(gastype).equals("其他"))){
						VehicleAction vehi1=new VehicleAction();
						vehi1.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi1.setVehicletype(getValue(vehicletype));
						vehi1.setPlatescolor(getValue(platescolor));
						vehi1.setRegisterdate(regis);
						vehi1.setCheckdate(check);
						vehi1.setMileage(Double.parseDouble(getValue(mileage)));
						vehi1.setGastype(getValue(gastype));
						vehi1.setVehicletype("微型载货车");
						list.add(vehi1);
						VehicleAction vehi2=new VehicleAction();
						vehi2.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi2.setVehicletype(getValue(vehicletype));
						vehi2.setPlatescolor(getValue(platescolor));
						vehi2.setRegisterdate(regis);
						vehi2.setCheckdate(check);
						vehi2.setMileage(Double.parseDouble(getValue(mileage)));
						vehi2.setGastype(getValue(gastype));
						vehi2.setVehicletype("轻型载货车");
						list.add(vehi2);
						VehicleAction vehi3=new VehicleAction();
						vehi3.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi3.setVehicletype(getValue(vehicletype));
						vehi3.setPlatescolor(getValue(platescolor));
						vehi3.setRegisterdate(regis);
						vehi3.setCheckdate(check);
						vehi3.setMileage(Double.parseDouble(getValue(mileage)));
						vehi3.setGastype(getValue(gastype));
						vehi3.setVehicletype("低速三轮车");
						list.add(vehi3);
						VehicleAction vehi4=new VehicleAction();
						vehi4.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi4.setVehicletype(getValue(vehicletype));
						vehi4.setPlatescolor(getValue(platescolor));
						vehi4.setRegisterdate(regis);
						vehi4.setCheckdate(check);
						vehi4.setMileage(Double.parseDouble(getValue(mileage)));
						vehi4.setGastype(getValue(gastype));
						vehi4.setVehicletype("低速货车");
						list.add(vehi4);
					}else{
						list.add(vehi);
					}
					
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
	public static List<VehicleAction> readXls(String path) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		VehicleAction vehi = null;
		List<VehicleAction> list = new ArrayList<VehicleAction>();
		// Read the Sheet

		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		// 读取行
		for (int rowNum = 3; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (hssfRow != null) {
				// 记录行号
				rownum = rowNum;

				try {
					vehi = new VehicleAction();
					HSSFCell serial = hssfRow.getCell(0);
					if (getValue(serial).isEmpty()) {
						break;
					}
					HSSFCell vehicletype = hssfRow.getCell(1);
					HSSFCell platescolor = hssfRow.getCell(2);
					HSSFCell registerdate = hssfRow.getCell(3);
					HSSFCell checkdate = hssfRow.getCell(4);
					String regis = null;
					String check = null;
					regis=getDate(registerdate);
					check=getDate(checkdate);
					HSSFCell mileage = hssfRow.getCell(5);
					HSSFCell gastype = hssfRow.getCell(6);
					vehi.setSerial((int) Double.parseDouble(getValue(serial)));
					vehi.setVehicletype(getValue(vehicletype));
					vehi.setPlatescolor(getValue(platescolor));
					vehi.setRegisterdate(regis);
					vehi.setCheckdate(check);
					vehi.setMileage(Double.parseDouble(getValue(mileage)));
					vehi.setGastype(getValue(gastype));
					
					if(getValue(vehicletype).equals("公交车") && (getValue(gastype).equals("汽油")||getValue(gastype).equals("柴油")||getValue(gastype).equals("其他"))){
						VehicleAction vehi1=new VehicleAction();
						vehi1.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi1.setVehicletype(getValue(vehicletype));
						vehi1.setPlatescolor(getValue(platescolor));
						vehi1.setRegisterdate(regis);
						vehi1.setCheckdate(check);
						vehi1.setMileage(Double.parseDouble(getValue(mileage)));
						vehi1.setGastype(getValue(gastype));
						vehi1.setVehicletype("中型公交车");
						list.add(vehi1);
						VehicleAction vehi2=new VehicleAction();
						vehi2.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi2.setVehicletype(getValue(vehicletype));
						vehi2.setPlatescolor(getValue(platescolor));
						vehi2.setRegisterdate(regis);
						vehi2.setCheckdate(check);
						vehi2.setMileage(Double.parseDouble(getValue(mileage)));
						vehi2.setGastype(getValue(gastype));
						vehi2.setVehicletype("大型公交车");
						list.add(vehi2);
					}else if(getValue(vehicletype).equals("出租车") && (getValue(gastype).equals("汽油")||getValue(gastype).equals("柴油")||getValue(gastype).equals("其他"))){
						VehicleAction vehi1=new VehicleAction();
						vehi1.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi1.setVehicletype(getValue(vehicletype));
						vehi1.setPlatescolor(getValue(platescolor));
						vehi1.setRegisterdate(regis);
						vehi1.setCheckdate(check);
						vehi1.setMileage(Double.parseDouble(getValue(mileage)));
						vehi1.setGastype(getValue(gastype));
						vehi1.setVehicletype("微型出租车");
						list.add(vehi1);
						VehicleAction vehi2=new VehicleAction();
						vehi2.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi2.setVehicletype(getValue(vehicletype));
						vehi2.setPlatescolor(getValue(platescolor));
						vehi2.setRegisterdate(regis);
						vehi2.setCheckdate(check);
						vehi2.setMileage(Double.parseDouble(getValue(mileage)));
						vehi2.setGastype(getValue(gastype));
						vehi2.setVehicletype("小型出租车");
						list.add(vehi2);
					}else if(getValue(vehicletype).equals("小型载客车") && (getValue(gastype).equals("汽油")||getValue(gastype).equals("柴油")||getValue(gastype).equals("其他"))){
						VehicleAction vehi1=new VehicleAction();
						vehi1.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi1.setVehicletype(getValue(vehicletype));
						vehi1.setPlatescolor(getValue(platescolor));
						vehi1.setRegisterdate(regis);
						vehi1.setCheckdate(check);
						vehi1.setMileage(Double.parseDouble(getValue(mileage)));
						vehi1.setGastype(getValue(gastype));
						vehi1.setVehicletype("微型载客车");
						list.add(vehi1);
						VehicleAction vehi2=new VehicleAction();
						vehi2.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi2.setVehicletype(getValue(vehicletype));
						vehi2.setPlatescolor(getValue(platescolor));
						vehi2.setRegisterdate(regis);
						vehi2.setCheckdate(check);
						vehi2.setMileage(Double.parseDouble(getValue(mileage)));
						vehi2.setGastype(getValue(gastype));
						vehi2.setVehicletype("小型载客车");
						list.add(vehi2);
					}else if(getValue(vehicletype).equals("轻型载货车") && (getValue(gastype).equals("汽油")||getValue(gastype).equals("柴油")||getValue(gastype).equals("其他"))){
						VehicleAction vehi1=new VehicleAction();
						vehi1.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi1.setVehicletype(getValue(vehicletype));
						vehi1.setPlatescolor(getValue(platescolor));
						vehi1.setRegisterdate(regis);
						vehi1.setCheckdate(check);
						vehi1.setMileage(Double.parseDouble(getValue(mileage)));
						vehi1.setGastype(getValue(gastype));
						vehi1.setVehicletype("微型载货车");
						list.add(vehi1);
						VehicleAction vehi2=new VehicleAction();
						vehi2.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi2.setVehicletype(getValue(vehicletype));
						vehi2.setPlatescolor(getValue(platescolor));
						vehi2.setRegisterdate(regis);
						vehi2.setCheckdate(check);
						vehi2.setMileage(Double.parseDouble(getValue(mileage)));
						vehi2.setGastype(getValue(gastype));
						vehi2.setVehicletype("轻型载货车");
						list.add(vehi2);
						VehicleAction vehi3=new VehicleAction();
						vehi3.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi3.setVehicletype(getValue(vehicletype));
						vehi3.setPlatescolor(getValue(platescolor));
						vehi3.setRegisterdate(regis);
						vehi3.setCheckdate(check);
						vehi3.setMileage(Double.parseDouble(getValue(mileage)));
						vehi3.setGastype(getValue(gastype));
						vehi3.setVehicletype("低速三轮车");
						list.add(vehi3);
						VehicleAction vehi4=new VehicleAction();
						vehi4.setSerial((int) Double.parseDouble(getValue(serial)));
						vehi4.setVehicletype(getValue(vehicletype));
						vehi4.setPlatescolor(getValue(platescolor));
						vehi4.setRegisterdate(regis);
						vehi4.setCheckdate(check);
						vehi4.setMileage(Double.parseDouble(getValue(mileage)));
						vehi4.setGastype(getValue(gastype));
						vehi4.setVehicletype("低速货车");
						list.add(vehi4);
					}else{
						list.add(vehi);
					}
					
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
	
	private static String getDate(HSSFCell hssfCell){
        DecimalFormat df = new DecimalFormat("#");
        if(hssfCell == null){
            return "";
        }
        switch (hssfCell.getCellType()){
        case HSSFCell.CELL_TYPE_NUMERIC:
            if(HSSFDateUtil.isCellDateFormatted(hssfCell)){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

                return sdf.format(HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue()));
            }

                return df.format(hssfCell.getNumericCellValue());
        case HSSFCell.CELL_TYPE_STRING:
            return hssfCell.getStringCellValue();
        case HSSFCell.CELL_TYPE_FORMULA:
            return hssfCell.getCellFormula();
        case HSSFCell.CELL_TYPE_BLANK:
            return "";

        }
    return "";
    }
	
	private static String getDate(XSSFCell xssfCell){
        DecimalFormat df = new DecimalFormat("#");
        if(xssfCell == null){
            return "";
        }
        switch (xssfCell.getCellType()){
        case XSSFCell.CELL_TYPE_NUMERIC:
            if(HSSFDateUtil.isCellDateFormatted(xssfCell)){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

                return sdf.format(HSSFDateUtil.getJavaDate(xssfCell.getNumericCellValue()));
            }

                return df.format(xssfCell.getNumericCellValue());
        case XSSFCell.CELL_TYPE_STRING:
            return xssfCell.getStringCellValue();
        case XSSFCell.CELL_TYPE_FORMULA:
            return xssfCell.getCellFormula();
        case XSSFCell.CELL_TYPE_BLANK:
            return "";

        }
    return "";
    }
}
