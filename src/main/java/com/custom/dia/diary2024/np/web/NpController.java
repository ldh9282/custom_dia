package com.custom.dia.diary2024.np.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.custom.dia.cmmn.constant.CustomAuthCode;
import com.custom.dia.cmmn.exception.CustomException;
import com.custom.dia.cmmn.exception.CustomExceptionCode;
import com.custom.dia.cmmn.model.CustomMap;
import com.custom.dia.cmmn.paging.PagingCreator;
import com.custom.dia.cmmn.security.utils.SecurityUtils;
import com.custom.dia.cmmn.utils.CmmnUtils;
import com.custom.dia.cmmn.utils.DateUtils;
import com.custom.dia.cmmn.utils.StringUtils;
import com.custom.dia.cmmn.web.CustomController;
import com.custom.dia.diary2024.np.service.NpService;

import lombok.extern.log4j.Log4j2;

@Controller @Log4j2
public class NpController extends CustomController {

	@Autowired
	private NpService npService;
	
	/**
	 * <pre>
	 * 메서드명: DIANP01
	 * 설명: 게시판 등록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/DIANP01")
	public ModelAndView dianp01(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		CustomMap requestMap = new CustomMap(map);
		
		modelAndView.setViewName("diary2024/np/npReg");
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메서드명: /DIANP02
	 * 설명: 게시판 등록요청
	 * </pre>
	 * @param requestMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/DIANP02")
	@ResponseBody
	public Object dianp02(@RequestBody CustomMap requestMap) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		try {
			
			npService.insertNotePostInfo(requestMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		return getResponse(resultMap);
		
	}
	
	/**
	 * <pre>
	 * 메서드명: DIANP03
	 * 설명: 게시판 목록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/DIANP03")
	public ModelAndView dianp03(@RequestParam Map<String, Object> map, ModelAndView modelAndView, HttpSession session) throws CustomException {
		CustomMap requestMap = new CustomMap(map);
		
		try {
			CustomMap npListMap = npService.getNotePostInfoList(requestMap, session);
			
			List<CustomMap> npList =  npListMap.getCustomMapList("npList");
			requestMap.put("count",  npListMap.getString("count"));
			CustomMap pagingCreator = new PagingCreator(requestMap).toCustomMap();
			
			modelAndView.addObject("pagingCreator", pagingCreator);
			modelAndView.addObject("npList", npList);
			modelAndView.addObject("requestMap", requestMap);
			
			modelAndView.addObject("today", DateUtils.format(new Date(), "yyyy-MM-dd"));
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		modelAndView.setViewName("diary2024/np/npList");
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메서드명: DIANP04
	 * 설명: 게시판 상세페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/DIANP04")
	public ModelAndView dianp04(@RequestParam Map<String, Object> map, ModelAndView modelAndView, HttpSession session) throws CustomException {
		CustomMap requestMap = new CustomMap(map);
		
		try {
			CustomMap np = npService.getNotePostInfo(requestMap, session);
			modelAndView.addObject("np", np);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		modelAndView.setViewName("diary2024/np/npDetail");
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메서드명: DIANP05
	 * 설명: 게시판 수정페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/DIANP05")
	public ModelAndView dianp05(@RequestParam Map<String, Object> map, ModelAndView modelAndView, HttpSession session) throws CustomException {
		CustomMap requestMap = new CustomMap(map);
		
		try {
			CustomMap np = npService.getNotePostInfo(requestMap, session);
			modelAndView.addObject("np", np);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		modelAndView.setViewName("diary2024/np/npModify");
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메서드명: /DIANP06
	 * 설명: 게시판 삭제요청
	 * </pre>
	 * @param requestMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/DIANP06")
	@ResponseBody
	public Object dianp06(@RequestBody CustomMap requestMap, HttpSession session) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		try {
			String aesKeyValidYn = StringUtils.NVL((String) session.getAttribute("aesKeyValidYn"), "");
			if (!"1".equals(aesKeyValidYn) || !SecurityUtils.hasAuthorized(CustomAuthCode.A001)) {
				throw new CustomException(CustomExceptionCode.ERR541, new String[] { "게시판" });
			}
			npService.updateNotePostDelYn(requestMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		return getResponse(resultMap);
		
	}
	/**
	 * <pre>
	 * 메서드명: /DIANP07
	 * 설명: 게시판 수정요청
	 * </pre>
	 * @param requestMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/DIANP07")
	@ResponseBody
	public Object dianp07(@RequestBody CustomMap requestMap, HttpSession session) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		try {
			String aesKeyValidYn = StringUtils.NVL((String) session.getAttribute("aesKeyValidYn"), "");
			if (!"1".equals(aesKeyValidYn) || !SecurityUtils.hasAuthorized(CustomAuthCode.A001)) {
				throw new CustomException(CustomExceptionCode.ERR531, new String[] { "게시판" });
			}
			npService.updateNotePostInfo(requestMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		return getResponse(resultMap);
		
	}
	
}
