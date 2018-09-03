package com.xf.security;

public class SqlSwitch {
	// 定义了一个ThreadLocal变量，用来保存int或Integer数据
	private ThreadLocal<Integer> s = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};

	public Integer get() {
		return s.get();
	}
	
	public void set(Integer i) {
		s.set(i);
	}
	
}
