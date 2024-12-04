package com.custom.dia.diary2024.ae.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.custom.dia.cmmn.exception.CustomException;
import com.custom.dia.cmmn.exception.CustomExceptionCode;
import com.custom.dia.cmmn.model.CustomMap;
import com.custom.dia.cmmn.utils.CmmnUtils;
import com.custom.dia.cmmn.web.CustomController;

import lombok.extern.log4j.Log4j2;

@Controller @Log4j2
public class AeControllor extends CustomController {

	/**
	 * <pre>
	 * 메서드명: /DIANP99
	 * 설명: AES 키 세션세팅
	 * </pre>
	 * @param requestMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/DIAAE01")
	@ResponseBody
	public Object diaae01(@RequestBody CustomMap requestMap, HttpSession session) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		try {
			
			session.setAttribute("aesKey", requestMap.getString("aesKey"));
			if (requestMap.getString("aesKey").equals(CmmnUtils.getAesKey())) {
				session.setAttribute("aesKeyValidYn", "1");
				resultMap.put("aesKeyValidYn", "1");
			} else {
				session.setAttribute("aesKeyValidYn", "0");
				resultMap.put("aesKeyValidYn", "0");
			}
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		return getResponse(resultMap);
		
	}
}
