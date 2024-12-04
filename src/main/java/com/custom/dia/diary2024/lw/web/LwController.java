package com.custom.dia.diary2024.lw.web;

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
import com.custom.dia.diary2024.lw.service.LwService;

import lombok.extern.log4j.Log4j2;

@Controller @Log4j2
public class LwController extends CustomController {

	@Autowired
	private LwService lwService;
	
	/**
	 * <pre>
	 * 메서드명: DIALW01
	 * 설명: 장편게시판 등록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/DIALW01")
	public ModelAndView dialw01(@RequestParam Map<String, Object> map, ModelAndView modelAndView) {
		CustomMap requestMap = new CustomMap(map);
		
		modelAndView.setViewName("diary2024/lw/lwReg");
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메서드명: /DIALW02
	 * 설명: 장편게시판 등록요청
	 * </pre>
	 * @param requestMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/DIALW02")
	@ResponseBody
	public Object dialw02(@RequestBody CustomMap requestMap) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		try {
			
			lwService.insertLwInfo(requestMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		return getResponse(resultMap);
		
	}
	
	/**
	 * <pre>
	 * 메서드명: DIALW03
	 * 설명: 장편게시판 목록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/DIALW03")
	public ModelAndView dialw03(@RequestParam Map<String, Object> map, ModelAndView modelAndView, HttpSession session) throws CustomException {
		CustomMap requestMap = new CustomMap(map);
		
		try {
			CustomMap lwListMap = lwService.getLwInfoList(requestMap, session);
			
			List<CustomMap> lwList =  lwListMap.getCustomMapList("lwList");
			requestMap.put("count",  lwListMap.getString("count"));
			CustomMap pagingCreator = new PagingCreator(requestMap).toCustomMap();
			
			modelAndView.addObject("pagingCreator", pagingCreator);
			modelAndView.addObject("lwList", lwList);
			modelAndView.addObject("requestMap", requestMap);
			
			modelAndView.addObject("today", DateUtils.format(new Date(), "yyyy-MM-dd"));
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		modelAndView.setViewName("diary2024/lw/lwList");
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메서드명: DIALW04
	 * 설명: 장편게시판 상세페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/DIALW04")
	public ModelAndView dialw04(@RequestParam Map<String, Object> map, ModelAndView modelAndView, HttpSession session) throws CustomException {
		CustomMap requestMap = new CustomMap(map);
		
		try {
			CustomMap lw = lwService.getLwInfo(requestMap, session);
			
			CustomMap lwDtlListMap = lwService.getLwDtlInfoList(requestMap, session);
			
			List<CustomMap> lwDtlList =  lwDtlListMap.getCustomMapList("lwDtlList");
			requestMap.put("count",  lwDtlListMap.getString("count"));
			CustomMap pagingCreator = new PagingCreator(requestMap).toCustomMap();
			
			modelAndView.addObject("pagingCreator", pagingCreator);
			modelAndView.addObject("lw", lw);
			modelAndView.addObject("lwDtlList",lwDtlList);
			modelAndView.addObject("requestMap", requestMap);
			
			modelAndView.addObject("today", DateUtils.format(new Date(), "yyyy-MM-dd"));
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		modelAndView.setViewName("diary2024/lw/lwDetail");
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메서드명: DIALW05
	 * 설명: 장편게시판 수정페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/DIALW05")
	public ModelAndView dialw05(@RequestParam Map<String, Object> map, ModelAndView modelAndView, HttpSession session) throws CustomException {
		CustomMap requestMap = new CustomMap(map);
		
		try {
			CustomMap lw = lwService.getLwInfo(requestMap, session);
			modelAndView.addObject("lw", lw);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		modelAndView.setViewName("diary2024/lw/lwModify");
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메서드명: /DIALW06
	 * 설명: 장편게시판 삭제요청
	 * </pre>
	 * @param requestMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/DIALW06")
	@ResponseBody
	public Object dialw06(@RequestBody CustomMap requestMap, HttpSession session) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		try {
			String aesKeyValidYn = StringUtils.NVL((String) session.getAttribute("aesKeyValidYn"), "");
			if (!"1".equals(aesKeyValidYn) || !SecurityUtils.hasAuthorized(CustomAuthCode.A001)) {
				throw new CustomException(CustomExceptionCode.ERR541, new String[] { "장편게시판" });
			}
			lwService.updateLwDelYn(requestMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		return getResponse(resultMap);
		
	}
	
	/**
	 * <pre>
	 * 메서드명: /DIALW07
	 * 설명: 장편게시판 수정요청
	 * </pre>
	 * @param requestMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/DIALW07")
	@ResponseBody
	public Object dialw07(@RequestBody CustomMap requestMap, HttpSession session) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		try {
			String aesKeyValidYn = StringUtils.NVL((String) session.getAttribute("aesKeyValidYn"), "");
			if (!"1".equals(aesKeyValidYn) || !SecurityUtils.hasAuthorized(CustomAuthCode.A001)) {
				throw new CustomException(CustomExceptionCode.ERR531, new String[] { "장편게시판" });
			}
			lwService.updateLwInfo(requestMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		return getResponse(resultMap);
		
	}
	
	/**
	 * <pre>
	 * 메서드명: DIALW08
	 * 설명: 장편게시판상세 등록페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 * @throws CustomException 
	 */
	@RequestMapping("/DIALW08")
	public ModelAndView dialw08(@RequestParam Map<String, Object> map, ModelAndView modelAndView, HttpSession session) throws CustomException {
		CustomMap requestMap = new CustomMap(map);
		
		try {
			CustomMap lw = lwService.getLwInfo(requestMap, session);
			modelAndView.addObject("lw", lw);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		modelAndView.setViewName("diary2024/lw/lwDtlReg");
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메서드명: /DIALW09
	 * 설명: 장편게시판상세 등록요청
	 * </pre>
	 * @param requestMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/DIALW09")
	@ResponseBody
	public Object dialw09(@RequestBody CustomMap requestMap) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		try {
			
			lwService.insertLwDtlInfo(requestMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		return getResponse(resultMap);
		
	}
	
	/**
	 * <pre>
	 * 메서드명: DIALW10
	 * 설명: 장편게시판상세 상세페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/DIALW10")
	public ModelAndView dialw10(@RequestParam Map<String, Object> map, ModelAndView modelAndView, HttpSession session) throws CustomException {
		CustomMap requestMap = new CustomMap(map);
		
		try {
			CustomMap lwDtl = lwService.getLwDtlInfo(requestMap, session);
			
			modelAndView.addObject("lwDtl", lwDtl);
			
			modelAndView.addObject("today", DateUtils.format(new Date(), "yyyy-MM-dd"));
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		modelAndView.setViewName("diary2024/lw/lwDtlDetail");
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메서드명: DIALW11
	 * 설명: 장편게시판상세 수정페이지
	 * </pre>
	 * @param map
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/DIALW11")
	public ModelAndView dialw11(@RequestParam Map<String, Object> map, ModelAndView modelAndView, HttpSession session) throws CustomException {
		CustomMap requestMap = new CustomMap(map);
		
		try {
			CustomMap lwDtl = lwService.getLwDtlInfo(requestMap, session);
			
			modelAndView.addObject("lwDtl", lwDtl);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		modelAndView.setViewName("diary2024/lw/lwDtlModify");
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * 메서드명: /DIALW12
	 * 설명: 장편게시판상세 삭제요청
	 * </pre>
	 * @param requestMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/DIALW12")
	@ResponseBody
	public Object dialw12(@RequestBody CustomMap requestMap, HttpSession session) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		try {
			String aesKeyValidYn = StringUtils.NVL((String) session.getAttribute("aesKeyValidYn"), "");
			if (!"1".equals(aesKeyValidYn) || !SecurityUtils.hasAuthorized(CustomAuthCode.A001)) {
				throw new CustomException(CustomExceptionCode.ERR541, new String[] { "장편게시판상세" });
			}
			lwService.updateLwDtlDelYn(requestMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		return getResponse(resultMap);
		
	}
	
	/**
	 * <pre>
	 * 메서드명: /DIALW13
	 * 설명: 장편게시판 수정요청
	 * </pre>
	 * @param requestMap
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/DIALW13")
	@ResponseBody
	public Object dialw13(@RequestBody CustomMap requestMap, HttpSession session) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		try {
			String aesKeyValidYn = StringUtils.NVL((String) session.getAttribute("aesKeyValidYn"), "");
			if (!"1".equals(aesKeyValidYn) || !SecurityUtils.hasAuthorized(CustomAuthCode.A001)) {
				throw new CustomException(CustomExceptionCode.ERR531, new String[] { "장편게시판상세" });
			}
			lwService.updateLwDtlInfo(requestMap);
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR500, e);
		}
		
		return getResponse(resultMap);
		
	}
}
