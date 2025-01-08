package com.custom.dia.diary2024.lw.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.custom.dia.cmmn.exception.CustomException;
import com.custom.dia.cmmn.exception.CustomExceptionCode;
import com.custom.dia.cmmn.model.CustomMap;
import com.custom.dia.cmmn.security.utils.SecurityUtils;
import com.custom.dia.cmmn.utils.AESUtils;
import com.custom.dia.cmmn.utils.CmmnUtils;
import com.custom.dia.cmmn.utils.StringUtils;

@Service
public class LwService {

	@Autowired
	private LwDao lwDao;
	
	@Transactional
	public CustomMap insertLwInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			String lgwkNotePostSno = lwDao.selectLgwkNotePostSno();
			customMap.put("lgwkNotePostTit", AESUtils.encrypt(customMap.getString("lgwkNotePostTit"), CmmnUtils.getAesKey()));
			customMap.put("lgwkNotePostDesc", AESUtils.encrypt(customMap.getString("lgwkNotePostDesc"), CmmnUtils.getAesKey()));
			customMap.put("sysCreator", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
			customMap.put("lgwkNotePostSno", lgwkNotePostSno);
			lwDao.insertLw(customMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new CustomException(CustomExceptionCode.ERR521, new String[] { "장편게시글" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap getLwInfo(CustomMap customMap, HttpSession session) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			resultMap = lwDao.selectLw(customMap);
			resultMap.put("lgwkNotePostTit", AESUtils.decrypt(resultMap.getString("lgwkNotePostTit"), StringUtils.NVL((String) session.getAttribute("aesKey"), "")));
			resultMap.put("lgwkNotePostDesc", AESUtils.decrypt(resultMap.getString("lgwkNotePostDesc"), StringUtils.NVL((String) session.getAttribute("aesKey"), "")));
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "장편게시글" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap getLwInfoList(CustomMap customMap, HttpSession session) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			customMap.put("pageNum", customMap.getString("pageNum", "1"));
			customMap.put("rowAmountPerPage", customMap.getString("rowAmountPerPage", "10"));
			
			List<CustomMap> lwList = lwDao.selectLwList(customMap);
			
			for (CustomMap lw : lwList) {
				lw.put("lgwkNotePostTit", AESUtils.decrypt(lw.getString("lgwkNotePostTit"), StringUtils.NVL((String) session.getAttribute("aesKey"), "")));
			}
			resultMap.put("lwList", lwList);
			resultMap.put("count", lwList.size() > 0 ? lwList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "장편게시글" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap updateLwInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		try {
			customMap.put("lgwkNotePostTit", AESUtils.encrypt(customMap.getString("lgwkNotePostTit"), CmmnUtils.getAesKey()));
			customMap.put("lgwkNotePostDesc", AESUtils.encrypt(customMap.getString("lgwkNotePostDesc"), CmmnUtils.getAesKey()));
			customMap.put("sysModifier", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
			lwDao.updateLw(customMap);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR531, new String[] { "장편게시글" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap updateLwDelYn(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		try {
			customMap.put("lgwkNotePostDelYn", "1");
			customMap.put("sysModifier", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
			lwDao.updateLwDelYn(customMap);
			customMap.put("lgwkNotePostDtlDelYn", "1");
			lwDao.updateLwDtlDelYn(customMap);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR531, new String[] { "장편게시글삭제여부" }, e);
		}
		
		return resultMap;
	}
	
	@Transactional
	public CustomMap insertLwDtlInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			String lgwkNotePostDtlSno = lwDao.selectLgwkNotePostDtlSno();
			customMap.put("lgwkNotePostDtlTit", AESUtils.encrypt(customMap.getString("lgwkNotePostDtlTit"), CmmnUtils.getAesKey()));
			customMap.put("lgwkNotePostDtlCtt", AESUtils.encrypt(customMap.getString("lgwkNotePostDtlCtt"), CmmnUtils.getAesKey()));
			customMap.put("sysCreator", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
			customMap.put("lgwkNotePostDtlSno", lgwkNotePostDtlSno);
			lwDao.insertLwDtl(customMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new CustomException(CustomExceptionCode.ERR521, new String[] { "장편게시글상세내역" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap getLwDtlInfo(CustomMap customMap, HttpSession session) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			resultMap = lwDao.selectLwDtl(customMap);
			resultMap.put("lgwkNotePostTit", AESUtils.decrypt(resultMap.getString("lgwkNotePostTit"), StringUtils.NVL((String) session.getAttribute("aesKey"), "")));
			resultMap.put("lgwkNotePostDesc", AESUtils.decrypt(resultMap.getString("lgwkNotePostDesc"), StringUtils.NVL((String) session.getAttribute("aesKey"), "")));
			resultMap.put("lgwkNotePostDtlTit", AESUtils.decrypt(resultMap.getString("lgwkNotePostDtlTit"), StringUtils.NVL((String) session.getAttribute("aesKey"), "")));
			resultMap.put("lgwkNotePostDtlCtt", AESUtils.decrypt(resultMap.getString("lgwkNotePostDtlCtt"), StringUtils.NVL((String) session.getAttribute("aesKey"), "")));
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "장편게시글상세내역" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap getLwDtlInfoList(CustomMap customMap, HttpSession session) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			customMap.put("pageNum", customMap.getString("pageNum", "1"));
			customMap.put("rowAmountPerPage", customMap.getString("rowAmountPerPage", "10"));
			
			List<CustomMap> lwDtlList = lwDao.selectLwDtlList(customMap);
			
			for (CustomMap lwDtl : lwDtlList) {
				lwDtl.put("lgwkNotePostTit", AESUtils.decrypt(lwDtl.getString("lgwkNotePostTit"), StringUtils.NVL((String) session.getAttribute("aesKey"), "")));
				lwDtl.put("lgwkNotePostDtlTit", AESUtils.decrypt(lwDtl.getString("lgwkNotePostDtlTit"), StringUtils.NVL((String) session.getAttribute("aesKey"), "")));
			}
			resultMap.put("lwDtlList", lwDtlList);
			resultMap.put("count", lwDtlList.size() > 0 ? lwDtlList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "장편게시글상세내역" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap updateLwDtlInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		try {
			customMap.put("lgwkNotePostDtlTit", AESUtils.encrypt(customMap.getString("lgwkNotePostDtlTit"), CmmnUtils.getAesKey()));
			customMap.put("lgwkNotePostDtlCtt", AESUtils.encrypt(customMap.getString("lgwkNotePostDtlCtt"), CmmnUtils.getAesKey()));
			customMap.put("sysModifier", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
			lwDao.updateLwDtl(customMap);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR531, new String[] { "장편게시글상세내역" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap updateLwDtlDelYn(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		try {
			customMap.put("lgwkNotePostDtlDelYn", "1");
			customMap.put("sysModifier", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
			lwDao.updateLwDtlDelYn(customMap);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR531, new String[] { "장편게시글상세내역 삭제여부" }, e);
		}
		
		return resultMap;
	}
}
