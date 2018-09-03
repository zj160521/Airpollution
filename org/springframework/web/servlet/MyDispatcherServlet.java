package org.springframework.web.servlet;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xf.security.LocalYear;
import com.xf.security.SqlSwitch;

public class MyDispatcherServlet extends DispatcherServlet {

	private static final long serialVersionUID = 8600797796665227148L;
	private static LocalYear curyear = new LocalYear();
	private static SqlSwitch sqlswitch = new SqlSwitch();
	
	public static LocalYear getYear() {
		return curyear;
	}
	
	public static SqlSwitch getSwitch() {
		return sqlswitch;
	}
	
	@Override
	protected void doDispatch(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession s = request.getSession();
		Object o = s.getAttribute("curyear");
		System.out.println(o);
		Integer year = 0;
		if (o == null) {
			Calendar cal = Calendar.getInstance();
			year = cal.get(Calendar.YEAR) - 1;
			s.setAttribute("curyear", year);
		} else {
			year = (Integer)o;
		}
		
		curyear.set(year);

		super.doDispatch(request, response);
	}

}
