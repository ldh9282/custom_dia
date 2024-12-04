package com.custom.dia.login.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.custom.dia.cmmn.exception.CustomException;
import com.custom.dia.cmmn.exception.CustomExceptionCode;
import com.custom.dia.cmmn.model.CustomMap;
import com.custom.dia.cmmn.utils.DateUtils;
import com.custom.dia.cmmn.web.CustomController;
import com.custom.dia.login.service.LoginServcie;

@Controller
public class LoginController extends CustomController {

	@Autowired
	private LoginServcie loginService;
	
	@GetMapping("/DIALG01")
	public ModelAndView dialg01(ModelAndView modelAndView) {
		modelAndView.setViewName("/login/login1");
		
		return modelAndView;
	}
	
	
	@GetMapping("/DIALG02")
	public String dialg02(HttpServletRequest request) {
		return "/login/register1";
	}
	
	@PostMapping("/DIALG03")
	@ResponseBody
	public Object dialg03(@RequestBody CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		resultMap = loginService.insertMemberDetail(customMap);
		
		return getResponse(resultMap);
	}
	
	@GetMapping("/DIALG04")
	public ModelAndView dialg04(ModelAndView modelAndView) {
		modelAndView.setViewName("/login/login2");
		
		return modelAndView;
	}
	
	@PostMapping("/DIALG05")
	@ResponseBody
	public Object dialg05(@RequestBody CustomMap customMap, HttpServletRequest request) throws CustomException {
		CustomMap resultMap = new CustomMap();
		CustomMap requestMap = new CustomMap(customMap);
		
		try {
			
			resultMap = loginService.loginJwt(requestMap);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginTime", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] {e.getMessage()}, e);
		}
		
		return getResponse(resultMap);
	}
}
