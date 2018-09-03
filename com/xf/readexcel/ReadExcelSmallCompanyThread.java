package com.xf.readexcel;

import java.util.concurrent.Callable;

public class ReadExcelSmallCompanyThread implements Callable<Integer>{

	private static ReadExcelSmallCompanyThread thread;
	private static ReadExcelSmallCompany readExcel;
	private static String path;
	private static int fillyear;
	
	public Integer call() throws Exception {
		readExcel.readExcel(path, fillyear);
		return 1;
	}
	public static ReadExcelSmallCompanyThread getRead(ReadExcelSmallCompany readExcel1,String path1,int fillyear1){
		if(thread==null){
			synchronized (ReadExcelSmallCompanyThread.class) {
				if (thread == null) {
				   thread=new ReadExcelSmallCompanyThread();
				   readExcel=readExcel1;
				   path=path1;
				   fillyear=fillyear1;
				}
			}
		}
		return thread;
	}
}
