package org.apache.ibatis.mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.springframework.web.servlet.MyDispatcherServlet;

import com.xf.security.LocalYear;
import com.xf.security.SeperateTable;
import com.xf.security.SqlSwitch;

public class BoundSql {

	private LocalYear curyear = new LocalYear();

	private String sql;
	private List<ParameterMapping> parameterMappings;
	private Object parameterObject;
	private Map<String, Object> additionalParameters;
	private MetaObject metaParameters;

	public BoundSql(Configuration configuration, String sql,
			List<ParameterMapping> parameterMappings, Object parameterObject) {
		
		this.sql = changeTables(sql);
		this.parameterMappings = parameterMappings;
		this.parameterObject = parameterObject;
		this.additionalParameters = new HashMap<String, Object>();
		this.metaParameters = configuration.newMetaObject(additionalParameters);
	}
	
	private String changeTables(String sql) {
		
		SqlSwitch s = MyDispatcherServlet.getSwitch();
		if (s.get() > 0) {
			return sql;
		}
		
		curyear = MyDispatcherServlet.getYear();
		int year = curyear.get();
		if (year < 1970) return sql;

		SeperateTable stables = SeperateTable.instance();
		ArrayList<String> tables = stables.get();
		HashSet<String> reptables = new HashSet<String>();
		
		Pattern p = Pattern.compile("ap_\\w+");
		Matcher m = p.matcher(sql);
		while(m.find()) {
			String table = m.group();
			if (tables.contains(table))
				reptables.add(table);
		}
		
		for(String t : reptables) {
			sql = sql.replaceAll("\\b" + t + "\\b", t+"_"+year);
		}
		
		return sql;
	}

	public String getSql() {
		return sql;
	}

	public List<ParameterMapping> getParameterMappings() {
		return parameterMappings;
	}

	public Object getParameterObject() {
		return parameterObject;
	}

	public boolean hasAdditionalParameter(String name) {
		return metaParameters.hasGetter(name);
	}

	public void setAdditionalParameter(String name, Object value) {
		metaParameters.setValue(name, value);
	}

	public Object getAdditionalParameter(String name) {
		return metaParameters.getValue(name);
	}
}
