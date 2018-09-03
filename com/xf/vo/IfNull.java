package com.xf.vo;

public class IfNull {
	public Double ifNullDouble(Double data){
		if(data == null) data=(double) 0;
		return data;
	}

}
