package com.custom.dia.cmmn.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.custom.dia.cmmn.utils.HttpUtils;

@Controller
public class CustomErrorController implements ErrorController {

	@GetMapping("/DIAER01")
	public ModelAndView diaer01(HttpServletRequest request, ModelAndView modelAndView) {
		HttpStatus status = HttpUtils.getStatus(request);
    	
        if (status == HttpStatus.NOT_FOUND) {
        	modelAndView.setViewName("cmmn/error-404");
        } else if (status == HttpStatus.FORBIDDEN) {
        	modelAndView.setViewName("cmmn/error-403");
        } else if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
        	modelAndView.setViewName("cmmn/error-500");
        } else {
        	modelAndView.setViewName("cmmn/error-500");
        }
        
		
		return modelAndView;
	}
	
	@GetMapping("/DIAER02")
	public ModelAndView diaer02(ModelAndView modelAndView) {
		modelAndView.setViewName("cmmn/error-403");
		
		return modelAndView;
	}
	@GetMapping("/DIAER03")
	public ModelAndView diaer03(ModelAndView modelAndView) {
		modelAndView.setViewName("cmmn/error-404");
		
		return modelAndView;
	}
	@GetMapping("/DIAER99")
	public ModelAndView diaer99(ModelAndView modelAndView) {
		modelAndView.setViewName("cmmn/error-500");
		
		return modelAndView;
	}
}
