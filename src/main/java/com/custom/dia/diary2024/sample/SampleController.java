package com.custom.dia.diary2024.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.custom.dia.cmmn.model.CustomMap;
import com.custom.dia.cmmn.utils.AESUtils;
import com.custom.dia.cmmn.utils.CmmnUtils;
import com.custom.dia.cmmn.web.CustomController;

@Controller
public class SampleController extends CustomController {

	@RequestMapping("/DIATE01")
	public ModelAndView diate01(ModelAndView modelAndView) {
		modelAndView.setViewName("/home");
		return modelAndView;
	}
	
	@RequestMapping("/DIATE02")
	@ResponseBody
	public Object diate02(ModelAndView modelAndView) {
		CustomMap result = new CustomMap();
		result.put("raw", "test");
		result.put("encrypt", AESUtils.encrypt("test", CmmnUtils.getAesKey()));
		result.put("decrypt", AESUtils.decrypt(AESUtils.encrypt("test", CmmnUtils.getAesKey()), CmmnUtils.getAesKey()));
		return getResponse(result);
	}
}
