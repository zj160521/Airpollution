package com.xf.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.entity.Config;
import com.xf.entity.InvitationCode;
import com.xf.entity.User;
import com.xf.security.LoginManage;
import com.xf.security.RandomU;
import com.xf.security.Tools;
import com.xf.service.ConfigService;
import com.xf.service.InvitationCodeService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/invitation/code")
public class InvitationCodeController {

	@Autowired
	private InvitationCodeService theService;

	@Autowired
	private ConfigService configService;
	@Autowired
	private LoginManage loginManage;

	@Autowired
	private InvitationCodeService invitationCodeService;

	@Autowired
	private RandomU randomU;

	@Autowired
	private ResultObj result;

	private InvitationCode check_input(HttpServletRequest request) {
		InvitationCode ret = new InvitationCode();

		String s = new String();
		s = request.getParameter("id");
		ret.setInvitationCode(request.getParameter("invitationCode"));
		s = request.getParameter("status");
		if (s != null && Tools.isInteger(s))
			ret.setStatus(Integer.parseInt(s));

		return ret;
	}

	// ==============================================================================

	int userid;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Object getAll(HttpServletRequest request) {

		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "user not login.");

		User c = loginManage.getLoginUser(request);

		List<InvitationCode> cList = theService.getAll();
		if (cList == null)
			return result.setStatus(-2, "no pollutant.");

		result.put("data", cList);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/invitationCode/{invitationCode}", method = RequestMethod.POST)
	@ResponseBody
	public Object getInvitationCode(@PathVariable String invitationCode,
			HttpServletRequest request) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");
		invitationCodeService.getInvitationCode(invitationCode);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addup(HttpServletRequest request) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "user not login.");
		User c = loginManage.getLoginUser(request);

		try {
			InvitationCode input = check_input(request);

			for (int i = 0; i < 100; i++) {
				input.setInvitationCode(RandomU.generateShortUuid());
				result.put("invitationCode1", RandomU.generateShortUuid());
				if (input.getInvitationCode() != null) {
					input.setInvitationCode(RandomU.generateShortUuid());
					input.setStatus(1);
					theService.add(input);
				}
				result.put("id", input.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/delete/{invitationCode}", method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable String invitationCode,
			HttpServletRequest request) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "user not login.");
		User c = loginManage.getLoginUser(request);

		InvitationCode d = invitationCodeService
				.getInvitationCode(invitationCode);
		if (d == null)
			return result.setStatus(1, "Code not exist.");

		if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "No login.");
		}

		invitationCodeService.delete(invitationCode);

		return result.setStatus(0, "delete code ok");
	}

	@RequestMapping(value = "/set", method = RequestMethod.POST)
	@ResponseBody
	public Object codeSet(HttpServletRequest request) {
		if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "user not login.");
		}

		try {
			String newcode = request.getParameter("code");
			String smallcode = request.getParameter("smallcode");
			String province = request.getParameter("province");
			Config config = null;

			config = configService.getConfig("invitation_code");
			Config conf = configService.getConfig("small_code");
			Config conf2 = configService.getConfig("province");

			if (config == null) {
				config = new Config();
				config.setK("invitation_code");
				config.setV("");
				configService.add(config);
			}
			if (conf == null) {
				conf = new Config();
				conf.setK("small_code");
				conf.setV("");
				configService.add(conf);
			}
			if (conf2 == null) {
				conf2 = new Config();
				conf2.setK("province");
				conf2.setV("");
				configService.add(conf2);
			}
			
			if (newcode != null && newcode != "") {
				if(newcode.equals(conf.getV()))
					return result.setStatus(-2, "邀请码不能相同");
				config.setK("invitation_code");
				config.setV(newcode);
				configService.update(config);
			}
			if (smallcode != null && smallcode != "") {
				if(smallcode.equals(config.getV()))
					return result.setStatus(-2, "邀请码不能相同");
				config.setK("small_code");
				config.setV(smallcode);
				configService.update(config);
			}
			if (province != null && province != "") {
				if(province.equals(conf2.getV()))
					return result.setStatus(-2, "邀请码不能相同");
				config.setK("province");
				config.setV(province);
				configService.update(config);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "修改失败");
		}
		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/getfixedcode", method = RequestMethod.GET)
	@ResponseBody
	public Object getFixedCode(HttpServletRequest request) {
		if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "user not login.");
		}

		String code = configService.get("invitation_code");
		String smallcode = configService.get("small_code");
		String province = configService.get("province");
		result.put("code", code);
		result.put("smallcode", smallcode);
		result.put("province", province);
		return result.setStatus(0, "code ok");

	}

}
