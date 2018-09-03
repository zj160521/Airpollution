package com.xf.security;

public class LocalYear {
	// 定义了一个ThreadLocal变量，用来保存int或Integer数据
	private ThreadLocal<Integer> year = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};

	public Integer get() {
		return year.get();
	}
	
	public void set(Integer i) {
		year.set(i);
	}
	
}
