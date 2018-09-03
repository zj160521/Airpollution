package com.xf.readexcel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xf.dao.ISfacilityDao;
import com.xf.entity.Company;
import com.xf.entity.CompanyFill;
import com.xf.entity.DevFill;
import com.xf.entity.Devices;
import com.xf.entity.District;
import com.xf.entity.Product;
import com.xf.entity.ProductFill;
import com.xf.entity.SmallFacility;
import com.xf.entity.Static;
import com.xf.entity.Trade;
import com.xf.service.CompanyFillService;
import com.xf.service.CompanyService;
import com.xf.service.DevFillService;
import com.xf.service.DeviceService;
import com.xf.service.DistrictService;
import com.xf.service.ProductFillService;
import com.xf.service.ProductService;
import com.xf.service.StaticService;
import com.xf.service.TradeService;

@Component
public class ReadExcelSmallCompany {
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

	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyFillService companyFillService;
	@Autowired
	private StaticService staticService;
	@Autowired
	private TradeService tradeService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private DevFillService dfillService;
	@Autowired
	private ISfacilityDao fDao;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductFillService pfService;

	public Object readExcel(String path, int fillyear) throws Exception {
		// 判断路径是否为空
		if (path == null || EMPTY.equals(path)) {
			return null;
		} else {
			// 截取文件后缀名
			String postfix = getPostfix(path);
			if (!EMPTY.equals(postfix)) {
				// 判断excel的格式
				if (OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
					readXls(path, fillyear);
				} else if (OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
					readXlsx(path, fillyear);
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
	public void readXlsx(String path, int fillyear) throws Exception {
		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

		// 固定信息查询
		Map<Integer, String> staticmap = new HashMap<Integer, String>();
		List<Static> staticlist = staticService.getAll();
		for (Static s : staticlist) {
			staticmap.put(s.getId(), s.getName());
		}
		//地区查询
		List<District> dislist=districtService.getAll();
		
		// 行业查询
		List<Trade> tradelist = tradeService.getAll();

		// 公司查询
		Map<Integer, String> companymap = new HashMap<Integer, String>();
		List<Company> companys = companyService.getAll(0);
		for (Company c : companys) {
			companymap.put(c.getId(), c.getCompanyName());
		}

		// 锅炉查询
		List<Devices> dlist = deviceService.getAll();

		//产品查询
		List<Product> plist=productService.getAll();
		// Read the Sheet

		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		// 读取一行
		try {
			for (int rowNum = 2; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {
					// 记录行号
					rownum = rowNum;

					Company company = new Company();

					XSSFCell companyname = xssfRow.getCell(0);
					if (companyname == null) {
						break;
					}
					int companyid = getCompanyId(companymap, getValue(companyname));

					XSSFCell gdp = xssfRow.getCell(9);
					XSSFCell totalhour = xssfRow.getCell(11);
					XSSFCell totalelec = xssfRow.getCell(12);

					if (companyid == 0) {
						company.setIsmall(1);
						company.setCompanyName(getValue(companyname));
						XSSFCell companyCode = xssfRow.getCell(1);
						company.setCompanyCode(getValue(companyCode));
						XSSFCell address = xssfRow.getCell(2);
						
						String adr=getValue(address);
						for(District d:dislist){
							if(d.getDistrictLevel()==0&&adr.contains(d.getDistrictName())){
								company.setProvince(d.getId());
							}
							if(d.getDistrictLevel()==1&&adr.contains(d.getDistrictName())){
								company.setCity(d.getId());
							}
							if(d.getDistrictLevel()==2&&adr.contains(d.getDistrictName())){
								company.setTown(d.getId());;
							}
						}
						
						company.setAddress(adr);
						XSSFCell n_point = xssfRow.getCell(3);
						company.setE_point(Double.parseDouble(getValue(n_point)));
						XSSFCell e_point = xssfRow.getCell(4);
						company.setN_point(Double.parseDouble(getValue(e_point)));
						XSSFCell tradeName = xssfRow.getCell(5);
						XSSFCell trade = xssfRow.getCell(6);
						Trade t = getTrade(tradelist, (int)Double.parseDouble(getValue(trade))+"");
						// 行业递归
						getTree(tradelist, t, company);

						XSSFCell contact = xssfRow.getCell(7);
						company.setContact(getValue(contact));
						XSSFCell contactNo = xssfRow.getCell(8);
						company.setContactNo(getValue(contactNo));
						XSSFCell yuanqu = xssfRow.getCell(10);
						company.setIndustrialPark(getValue(yuanqu));
						company.setName(getValue(companyname));
						// 密码6个0
						company.setPassword("670b14728ad9902aecba32e22fa4f6bd");
						company.setTypeid(2);
						companyService.add(company);

						companyid = company.getId();
						CompanyFill fill = new CompanyFill();
						fill.setEnterpriceId(company.getId());
						fill.setGdp(Double.parseDouble(getValue(gdp)));
						fill.setTotalElec(Double.parseDouble(getValue(totalelec)));
						fill.setTotalHour(Double.parseDouble(getValue(totalhour)));
						fill.setFillyear(fillyear);
						companyFillService.add(fill);
					} else {
						CompanyFill cf = new CompanyFill();
						cf.setEnterpriceId(companyid);
						cf.setFillyear(fillyear);
						List<CompanyFill> cflist = companyFillService.getByCompanyYear(cf);
						if (cflist.size() == 0) {
							CompanyFill fill = new CompanyFill();
							fill.setEnterpriceId(companyid);
							fill.setGdp(Double.parseDouble(getValue(gdp)));
							fill.setTotalElec(Double.parseDouble(getValue(totalelec)));
							fill.setTotalHour(Double.parseDouble(getValue(totalhour)));
							fill.setFillyear(fillyear);
							companyFillService.add(fill);
						} else {
							CompanyFill fill = new CompanyFill();
							fill.setEnterpriceId(companyid);
							fill.setGdp(Double.parseDouble(getValue(gdp)));
							fill.setTotalElec(Double.parseDouble(getValue(totalelec)));
							fill.setTotalHour(Double.parseDouble(getValue(totalhour)));
							fill.setFillyear(fillyear);
							fill.setId(cflist.get(0).getId());
							companyFillService.update(fill);
						}
					}
					XSSFCell deviceSerial = xssfRow.getCell(20);
					String serial=getValue(deviceSerial);
					Devices d = null;
					XSSFCell fuelcost2 = xssfRow.getCell(25);
					XSSFCell materialName = xssfRow.getCell(16);
					XSSFCell productName = xssfRow.getCell(17);
					XSSFCell productUnit = xssfRow.getCell(18);
					XSSFCell productTotalYear = xssfRow.getCell(19);
					
					Product pd=getProduct(plist,companyid,getValue(productName));
					if(pd==null){
						Product product=new Product();
						product.setProductName(getValue(productName));
						product.setEnterpriceId(companyid);
						product.setUnit(getValue(productUnit));
						productService.add(product);
						product.setGroupid(product.getId());
						productService.update(product);
						
						ProductFill pf=new ProductFill();
						pf.setEnterpriceId(companyid);
						pf.setProductId(product.getId());
						pf.setFillyear(fillyear);
						pf.setRealOutput(Double.parseDouble(getValue(productTotalYear)));
						pf.setStatus(3);
						XSSFCell unit2 = xssfRow.getCell(24);
						pf.setFuelunit(getValue(unit2));
						XSSFCell fuelname2 = xssfRow.getCell(23);
						int fuelid = getStaticId(staticmap, getValue(fuelname2));
						pf.setFuelId(fuelid);
						pf.setFuelValue(Double.parseDouble(getValue(fuelcost2)));
						pfService.add(pf);
					}else{
						ProductFill pf=new ProductFill();
						pf.setEnterpriceId(companyid);
						pf.setProductId(pd.getId());
						pf.setFillyear(fillyear);
						pf.setRealOutput(Double.parseDouble(getValue(productTotalYear)));
						pf.setStatus(3);
						XSSFCell unit2 = xssfRow.getCell(24);
						pf.setFuelunit(getValue(unit2));
						XSSFCell fuelname2 = xssfRow.getCell(23);
						int fuelid = getStaticId(staticmap, getValue(fuelname2));
						pf.setFuelId(fuelid);
						pf.setFuelValue(Double.parseDouble(getValue(fuelcost2)));
						List<ProductFill> pflist=pfService.getByYear(fillyear, companyid);
						if(pflist.size()>0){
							pfService.update2(pf);
						}else{
							pfService.add(pf);
						}
					}
					
					if(serial.equals("0")){
						continue;
					}else{
						d = getDevice(dlist, getValue(deviceSerial), companyid);
						if (d == null&&!getValue(deviceSerial).equals("0")) {
							Devices device = new Devices();
							device.setDevClass(0);
							device.setEnabled(1);
							device.setDeviceSerial(getValue(deviceSerial));
							XSSFCell shippingTon = xssfRow.getCell(21);
							device.setShippingTon(Double.parseDouble(getValue(shippingTon)));
							XSSFCell deviceModel = xssfRow.getCell(22);
							device.setDeviceModel(getValue(deviceModel));
							XSSFCell fuelname2 = xssfRow.getCell(23);
							int fuelid = getStaticId(staticmap, getValue(fuelname2));
							device.setFuel(fuelid);
							XSSFCell unit2 = xssfRow.getCell(24);
							device.setUnit(getValue(unit2));
							XSSFCell serviceLife = xssfRow.getCell(26);
							device.setServiceLife(getValue(serviceLife));
							if (getValue(fuelname2).contains("煤")) {
								device.setDeviceTypeId(1001);
							} else if (getValue(fuelname2).contains("油")) {
								device.setDeviceTypeId(1002);
							} else if (getValue(fuelname2).contains("气")) {
								device.setDeviceTypeId(1003);
							} else if (getValue(fuelname2).contains("垃圾")) {
								device.setDeviceTypeId(1005);
							} else if (getValue(fuelname2).contains("电")) {
								device.setDeviceTypeId(1006);
							} else {
								device.setDeviceTypeId(1004);
							}
							device.setEnterpriceId(companyid);
							deviceService.add(device);

							DevFill fill = new DevFill();
							fill.setFillyear(fillyear);
							fill.setDeviceId(device.getId());
							fill.setFuelcost(Double.parseDouble(getValue(fuelcost2)));
							fill.setStatus(3);
							fill.setProductName(getValue(productName));
							fill.setProductUnit(getValue(productUnit));
							fill.setProductTotalYear(Double.parseDouble(getValue(productTotalYear)));
							fill.setMaterialName(getValue(materialName));
							dfillService.add(fill);
						} else if(d!=null){
							List<DevFill> dfill = dfillService.getByDevId(d.getId(), fillyear);
							if(dfill.size()>0){
								DevFill fill = new DevFill();
								fill.setId(dfill.get(0).getId());
								fill.setFillyear(fillyear);
								fill.setDeviceId(d.getId());
								fill.setFuelcost(Double.parseDouble(getValue(fuelcost2)));
								fill.setStatus(3);
								fill.setProductName(getValue(productName));
								fill.setProductUnit(getValue(productUnit));
								fill.setProductTotalYear(Double.parseDouble(getValue(productTotalYear)));
								fill.setMaterialName(getValue(materialName));
								dfillService.update(fill);
							}else{
								DevFill fill = new DevFill();
								fill.setFillyear(fillyear);
								fill.setDeviceId(d.getId());
								fill.setFuelcost(Double.parseDouble(getValue(fuelcost2)));
								fill.setStatus(3);
								fill.setProductName(getValue(productName));
								fill.setProductUnit(getValue(productUnit));
								fill.setProductTotalYear(Double.parseDouble(getValue(productTotalYear)));
								fill.setMaterialName(getValue(materialName));
								dfillService.add(fill);
							}
						}
					}
					
					
					XSSFCell fuelname = xssfRow.getCell(13);
					XSSFCell unit = xssfRow.getCell(14);
					XSSFCell fuelcost = xssfRow.getCell(15);

					XSSFCell technique1Name = xssfRow.getCell(27);
					int technique1 = getStaticId(staticmap, getValue(technique1Name));
					XSSFCell technique2Name = xssfRow.getCell(28);
					int technique2 = getStaticId(staticmap, getValue(technique2Name));
					XSSFCell technique3Name = xssfRow.getCell(29);
					int technique3 = getStaticId(staticmap, getValue(technique3Name));
					XSSFCell technique4Name = xssfRow.getCell(30);
					int technique4 = getStaticId(staticmap, getValue(technique4Name));

					SmallFacility facility = new SmallFacility();
					facility.setEnterpriceId(companyid);
					facility.setFillyear(fillyear);
					List<SmallFacility> sflist = fDao.getAll(facility);
					if (sflist.size() > 0) {
						SmallFacility sf = new SmallFacility();
						sf.setId(sflist.get(0).getId());
						sf.setEnterpriceId(companyid);
						sf.setFillyear(fillyear);
						sf.setTechnique1(technique1);
						sf.setTechnique2(technique2);
						sf.setTechnique3(technique3);
						sf.setTechnique4(technique4);
						sf.setStatus(3);
						fDao.update(sf);
					} else {
						SmallFacility sf = new SmallFacility();
						sf.setEnterpriceId(companyid);
						sf.setFillyear(fillyear);
						sf.setTechnique1(technique1);
						sf.setTechnique2(technique2);
						sf.setTechnique3(technique3);
						sf.setTechnique4(technique4);
						sf.setStatus(3);
						fDao.add(sf);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("导入第" + (rownum + 1) + "行出错");
		}

	}

	public Product getProduct(List<Product> list,int companyid,String productName){
		for(Product p:list){
			if(p.getEnterpriceId()==companyid&&p.getProductName().equals(productName)){
				return p;
			}
		}
		return null;
	}
	
	public Trade getTrade(List<Trade> list, String code) {
		for (Trade trade : list) {
			if (trade.getTradeNo().equals(code)) {
				return trade;
			}
		}
		return null;
	}

	public Trade getTrade2(List<Trade> list, int pid) {
		for (Trade t : list) {
			if (pid == t.getId()) {
				return t;
			}
		}
		return null;
	}

	// 递归遍历行业
	public void getTree(List<Trade> list, Trade t, Company company) {
		Trade tr = getTrade2(list, t.getParentId());
		if (tr != null) {
			if (tr.getTradeLevel() == 0) {
				company.setTrade1(tr.getId());
			} else if (tr.getTradeLevel() == 1) {
				company.setTrade2(tr.getId());
			} else if (tr.getTradeLevel() == 2) {
				company.setTrade3(tr.getId());
			} else if (tr.getTradeLevel() == 3) {
				company.setTrade4(tr.getId());
			}
		}
		if (tr != null) {
			getTree(list, tr, company);
		}
	}

	public int getStaticId(Map<Integer, String> map, String name) {
		for (Entry<Integer, String> e : map.entrySet()) {
			if (e.getValue().equals(name)) {
				return e.getKey();
			}
		}
		return 0;
	}

	public int getCompanyId(Map<Integer, String> map, String name) {
		for (Entry<Integer, String> e : map.entrySet()) {
			if (e.getValue().equals(name)) {
				return e.getKey();
			}
		}
		return 0;
	}

	public Devices getDevice(List<Devices> list, String name, int id) {
		for (Devices d : list) {
			if (d.getEnterpriceId() == id && d.getDeviceSerial().equals(name)) {
				return d;
			}
		}
		return null;
	}

	/**
	 * Read the Excel 2003-2007
	 * 
	 * @param path
	 *            the path of the Excel
	 * @return
	 * @throws Exception
	 */
	public void readXls(String path, int fillyear) throws Exception {

		// 记录正在读取的行号

		System.out.println(PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

		// 固定信息查询
		Map<Integer, String> staticmap = new HashMap<Integer, String>();
		List<Static> staticlist = staticService.getAll();
		for (Static s : staticlist) {
			staticmap.put(s.getId(), s.getName());
		}
		// 行业查询
		List<Trade> tradelist = tradeService.getAll();

		//地区查询
		List<District> dislist=districtService.getAll();
		// 公司查询
		Map<Integer, String> companymap = new HashMap<Integer, String>();
		List<Company> companys = companyService.getAll(0);
		for (Company c : companys) {
			companymap.put(c.getId(), c.getCompanyName());
		}
		//产品查询
		List<Product> plist=productService.getAll();
		// 锅炉查询
		List<Devices> dlist = deviceService.getAll();

		List<Company> clist = new ArrayList<Company>();
		// Read the Sheet

		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		// 读取一行
		try {
			for (int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					// 记录行号
					rownum = rowNum;

					Company company = new Company();

					HSSFCell companyname = hssfRow.getCell(0);
					if (companyname == null) {
						break;
					}
					int companyid = getCompanyId(companymap, getValue(companyname));

					HSSFCell gdp = hssfRow.getCell(9);
					HSSFCell totalhour = hssfRow.getCell(11);
					HSSFCell totalelec = hssfRow.getCell(12);

					if (companyid == 0) {
						company.setIsmall(1);
						company.setCompanyName(getValue(companyname));
						HSSFCell companyCode = hssfRow.getCell(1);
						company.setCompanyCode(getValue(companyCode));
						HSSFCell address = hssfRow.getCell(2);
						
						String adr=getValue(address);
						for(District d:dislist){
							if(d.getDistrictLevel()==0&&adr.contains(d.getDistrictName())){
								company.setProvince(d.getId());
							}
							if(d.getDistrictLevel()==1&&adr.contains(d.getDistrictName())){
								company.setCity(d.getId());
							}
							if(d.getDistrictLevel()==2&&adr.contains(d.getDistrictName())){
								company.setTown(d.getId());;
							}
						}
						
						company.setAddress(getValue(address));
						HSSFCell n_point = hssfRow.getCell(3);
						company.setN_point(Double.parseDouble(getValue(n_point)));
						HSSFCell e_point = hssfRow.getCell(4);
						company.setE_point(Double.parseDouble(getValue(e_point)));
						HSSFCell tradeName = hssfRow.getCell(5);
						HSSFCell trade = hssfRow.getCell(6);
						Trade t = getTrade(tradelist, (int)Double.parseDouble(getValue(trade))+"");
						// 行业递归
						getTree(tradelist, t, company);

						HSSFCell contact = hssfRow.getCell(7);
						company.setContact(getValue(contact));
						HSSFCell contactNo = hssfRow.getCell(8);
						company.setContactNo(getValue(contactNo));
						HSSFCell yuanqu = hssfRow.getCell(10);
						company.setIndustrialPark(getValue(yuanqu));
						company.setName(getValue(companyname));
						// 密码6个0
						company.setPassword("670b14728ad9902aecba32e22fa4f6bd");
						company.setTypeid(2);
						companyService.add(company);

						companyid = company.getId();
						CompanyFill fill = new CompanyFill();
						fill.setEnterpriceId(company.getId());
						fill.setGdp(Double.parseDouble(getValue(gdp)));
						fill.setTotalElec(Double.parseDouble(getValue(totalelec)));
						fill.setTotalHour(Double.parseDouble(getValue(totalhour)));
						fill.setFillyear(fillyear);
						fill.setStatus(3);
						companyFillService.add(fill);
					} else {
						CompanyFill cf = new CompanyFill();
						cf.setEnterpriceId(companyid);
						cf.setFillyear(fillyear);
						List<CompanyFill> cflist = companyFillService.getByCompanyYear(cf);
						if (cflist.size() == 0) {
							CompanyFill fill = new CompanyFill();
							fill.setEnterpriceId(companyid);
							fill.setGdp(Double.parseDouble(getValue(gdp)));
							fill.setTotalElec(Double.parseDouble(getValue(totalelec)));
							fill.setTotalHour(Double.parseDouble(getValue(totalhour)));
							fill.setFillyear(fillyear);
							fill.setStatus(3);
							companyFillService.add(fill);
						} else {
							CompanyFill fill = new CompanyFill();
							fill.setEnterpriceId(companyid);
							fill.setGdp(Double.parseDouble(getValue(gdp)));
							fill.setTotalElec(Double.parseDouble(getValue(totalelec)));
							fill.setTotalHour(Double.parseDouble(getValue(totalhour)));
							fill.setFillyear(fillyear);
							fill.setId(cflist.get(0).getId());
							fill.setStatus(3);
							companyFillService.update(fill);
						}
					}
					HSSFCell deviceSerial = hssfRow.getCell(20);
					HSSFCell fuelcost2 = hssfRow.getCell(25);
					HSSFCell materialName = hssfRow.getCell(16);
					HSSFCell productName = hssfRow.getCell(17);
					HSSFCell productUnit = hssfRow.getCell(18);
					HSSFCell productTotalYear = hssfRow.getCell(19);
					
					Product pd=getProduct(plist,companyid,getValue(productName));
					if(pd==null){
						Product product=new Product();
						product.setProductName(getValue(productName));
						product.setEnterpriceId(companyid);
						product.setUnit(getValue(productUnit));
						productService.add(product);
						product.setGroupid(product.getId());
						productService.update(product);
						
						ProductFill pf=new ProductFill();
						pf.setEnterpriceId(companyid);
						pf.setProductId(product.getId());
						pf.setFillyear(fillyear);
						pf.setRealOutput(Double.parseDouble(getValue(productTotalYear)));
						pf.setStatus(3);
						HSSFCell unit2 = hssfRow.getCell(24);
						pf.setFuelunit(getValue(unit2));
						HSSFCell fuelname2 = hssfRow.getCell(23);
						int fuelid = getStaticId(staticmap, getValue(fuelname2));
						pf.setFuelId(fuelid);
						pf.setFuelValue(Double.parseDouble(getValue(fuelcost2)));
						pfService.add(pf);
					}else{
						ProductFill pf=new ProductFill();
						pf.setEnterpriceId(companyid);
						pf.setProductId(pd.getId());
						pf.setFillyear(fillyear);
						pf.setRealOutput(Double.parseDouble(getValue(productTotalYear)));
						pf.setStatus(3);
						HSSFCell unit2 = hssfRow.getCell(24);
						pf.setFuelunit(getValue(unit2));
						HSSFCell fuelname2 = hssfRow.getCell(23);
						int fuelid = getStaticId(staticmap, getValue(fuelname2));
						pf.setFuelId(fuelid);
						pf.setFuelValue(Double.parseDouble(getValue(fuelcost2)));
						List<ProductFill> pflist=pfService.getByYear(fillyear, companyid);
						if(pflist.size()>0){
							pfService.update2(pf);
						}else{
							pfService.add(pf);
						}
					}
					
					String serial=getValue(deviceSerial);
					Devices d = null;
					if(serial.equals("0")){
						continue;
					}else{
						d = getDevice(dlist, getValue(deviceSerial), companyid);
						
						if (d == null&&!getValue(deviceSerial).equals("0")) {
							Devices device = new Devices();
							device.setEnterpriceId(companyid);
							device.setDevClass(0);
							device.setEnabled(1);
							device.setDeviceSerial(getValue(deviceSerial));
							HSSFCell shippingTon = hssfRow.getCell(21);
							device.setShippingTon(Double.parseDouble(getValue(shippingTon)));
							HSSFCell deviceModel = hssfRow.getCell(22);
							device.setDeviceModel(getValue(deviceModel));
							HSSFCell fuelname2 = hssfRow.getCell(23);
							int fuelid = getStaticId(staticmap, getValue(fuelname2));
							device.setFuel(fuelid);
							HSSFCell unit2 = hssfRow.getCell(24);
							device.setUnit(getValue(unit2));
							HSSFCell serviceLife = hssfRow.getCell(26);
							device.setServiceLife(getValue(serviceLife));
							if (getValue(fuelname2).contains("煤")) {
								device.setDeviceTypeId(1001);
							} else if (getValue(fuelname2).contains("油")) {
								device.setDeviceTypeId(1002);
							} else if (getValue(fuelname2).contains("气")) {
								device.setDeviceTypeId(1003);
							} else if (getValue(fuelname2).contains("垃圾")) {
								device.setDeviceTypeId(1005);
							} else if (getValue(fuelname2).contains("电")) {
								device.setDeviceTypeId(1006);
							} else {
								device.setDeviceTypeId(1004);
							}
							deviceService.add(device);

							DevFill fill = new DevFill();
							fill.setFillyear(fillyear);
							fill.setDeviceId(device.getId());
							fill.setFuelcost(Double.parseDouble(getValue(fuelcost2)));
							fill.setStatus(3);
							fill.setProductName(getValue(productName));
							fill.setProductUnit(getValue(productUnit));
							fill.setProductTotalYear(Double.parseDouble(getValue(productTotalYear)));
							fill.setMaterialName(getValue(materialName));
							dfillService.add(fill);
						} else if(d != null){
							List<DevFill> dfill = dfillService.getByDevId(d.getId(), fillyear);
							if(dfill.size()>0){
								DevFill fill = new DevFill();
								fill.setId(dfill.get(0).getId());
								fill.setFillyear(fillyear);
								fill.setDeviceId(d.getId());
								fill.setFuelcost(Double.parseDouble(getValue(fuelcost2)));
								fill.setStatus(3);
								fill.setProductName(getValue(productName));
								fill.setProductUnit(getValue(productUnit));
								fill.setProductTotalYear(Double.parseDouble(getValue(productTotalYear)));
								fill.setMaterialName(getValue(materialName));
								dfillService.update(fill);
							}else{
								DevFill fill = new DevFill();
								fill.setFillyear(fillyear);
								fill.setDeviceId(d.getId());
								fill.setFuelcost(Double.parseDouble(getValue(fuelcost2)));
								fill.setStatus(3);
								fill.setProductName(getValue(productName));
								fill.setProductUnit(getValue(productUnit));
								fill.setProductTotalYear(Double.parseDouble(getValue(productTotalYear)));
								fill.setMaterialName(getValue(materialName));
								dfillService.add(fill);
							}
						}
					}
							
					
					HSSFCell fuelname = hssfRow.getCell(13);
					HSSFCell unit = hssfRow.getCell(14);
					HSSFCell fuelcost = hssfRow.getCell(15);

					HSSFCell technique1Name = hssfRow.getCell(27);
					int technique1 = getStaticId(staticmap, getValue(technique1Name));
					HSSFCell technique2Name = hssfRow.getCell(28);
					int technique2 = getStaticId(staticmap, getValue(technique2Name));
					HSSFCell technique3Name = hssfRow.getCell(29);
					int technique3 = getStaticId(staticmap, getValue(technique3Name));
					HSSFCell technique4Name = hssfRow.getCell(30);
					int technique4 = getStaticId(staticmap, getValue(technique4Name));

					SmallFacility facility = new SmallFacility();
					facility.setEnterpriceId(companyid);
					facility.setFillyear(fillyear);
					List<SmallFacility> sflist = fDao.getAll(facility);
					if (sflist.size() > 0) {
						SmallFacility sf = new SmallFacility();
						sf.setId(sflist.get(0).getId());
						sf.setEnterpriceId(companyid);
						sf.setFillyear(fillyear);
						sf.setTechnique1(technique1);
						sf.setTechnique2(technique2);
						sf.setTechnique3(technique3);
						sf.setTechnique4(technique4);
						sf.setStatus(3);
						fDao.update(sf);
					} else {
						SmallFacility sf = new SmallFacility();
						sf.setEnterpriceId(companyid);
						sf.setFillyear(fillyear);
						sf.setTechnique1(technique1);
						sf.setTechnique2(technique2);
						sf.setTechnique3(technique3);
						sf.setTechnique4(technique4);
						sf.setStatus(3);
						fDao.add(sf);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException("导入第" + (rownum + 1) + "行出错");
		}

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
