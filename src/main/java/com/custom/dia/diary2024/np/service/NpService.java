package com.custom.dia.diary2024.np.service;

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
public class NpService {

	@Autowired
	private NpDao npDao;
	
	@Transactional
	public CustomMap insertNotePostInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			String notePostSno = npDao.selectNotePostSno();
			customMap.put("notePostTit", AESUtils.encrypt(customMap.getString("notePostTit"), CmmnUtils.getAesKey()));
			customMap.put("notePostCtt", AESUtils.encrypt(customMap.getString("notePostCtt"), CmmnUtils.getAesKey()));
			customMap.put("sysCreator", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
			customMap.put("notePostSno", notePostSno);
			npDao.insertNotePost(customMap);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new CustomException(CustomExceptionCode.ERR521, new String[] { "게시글" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap getNotePostInfo(CustomMap customMap, HttpSession session) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			resultMap = npDao.selectNotePost(customMap);
			resultMap.put("notePostTit", AESUtils.decrypt(resultMap.getString("notePostTit"), StringUtils.NVL((String) session.getAttribute("aesKey"), "")));
			resultMap.put("notePostCtt", AESUtils.decrypt(resultMap.getString("notePostCtt"), StringUtils.NVL((String) session.getAttribute("aesKey"), "")));
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "게시글" }, e);
		}
		
		return resultMap;
	}
	public CustomMap getNotePostInfoList(CustomMap customMap, HttpSession session) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			customMap.put("pageNum", customMap.getString("pageNum", "1"));
			customMap.put("rowAmountPerPage", customMap.getString("rowAmountPerPage", "10"));
			
			List<CustomMap> npList = npDao.selectNotePostList(customMap);
			
			for (CustomMap np : npList) {
				np.put("notePostTit", AESUtils.decrypt(np.getString("notePostTit"), StringUtils.NVL((String) session.getAttribute("aesKey"), "")));
			}
			resultMap.put("npList", npList);
			resultMap.put("count", npList.size() > 0 ? npList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "게시글" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap updateNotePostInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		try {
			customMap.put("notePostTit", AESUtils.encrypt(customMap.getString("notePostTit"), CmmnUtils.getAesKey()));
			customMap.put("notePostCtt", AESUtils.encrypt(customMap.getString("notePostCtt"), CmmnUtils.getAesKey()));
			customMap.put("sysModifier", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
			npDao.updateNotePost(customMap);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR531, new String[] { "게시글" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap updateNotePostDelYn(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		try {
			customMap.put("notePostDelYn", "1");
			customMap.put("sysModifier", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
			npDao.updateNpDelYn(customMap);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR531, new String[] { "게시글삭제여부" }, e);
		}
		
		return resultMap;
	}
}
