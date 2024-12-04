package com.custom.dia.diary2024.lw.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.dia.cmmn.model.CustomMap;

@Repository
public class LwDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	/**
	 * <pre>
	 * 메서드명: selectLgwkNotePostSno
	 * 설명: 장편게시글일련번호 조회
	 * </pre>
	 * @param
	 * @return
	 */
	public String selectLgwkNotePostSno() {
		return sst.selectOne("Lw.selectLgwkNotePostSno");
	}
	
	/**
	 * <pre>
	 * 메서드명: insertLw
	 * 설명: 장편게시글 등록
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int insertLw(CustomMap customMap) {
		return sst.insert("Lw.insertLw", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectLw
	 * 설명: 장편게시글 조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public CustomMap selectLw(CustomMap customMap) {
		return sst.selectOne("Lw.selectLw", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectLwList
	 * 설명: 장편게시글 목록조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public List<CustomMap> selectLwList(CustomMap customMap) {
		return sst.selectList("Lw.selectLwList", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: updateLw
	 * 설명: 장편게시글 수정
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int updateLw(CustomMap customMap) {
		return sst.update("Lw.updateLw", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: updateLwDelYn
	 * 설명: 장편게시글 삭제여부 수정
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int updateLwDelYn(CustomMap customMap) {
		return sst.update("Lw.updateLwDelYn", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectLgwkNotePostDtlSno
	 * 설명: 장편게시글상세일련번호 조회
	 * </pre>
	 * @param
	 * @return
	 */
	public String selectLgwkNotePostDtlSno() {
		return sst.selectOne("Lw.selectLgwkNotePostDtlSno");
	}
	
	/**
	 * <pre>
	 * 메서드명: insertLwDtl
	 * 설명: 장편게시글상세내역 등록
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int insertLwDtl(CustomMap customMap) {
		return sst.insert("Lw.insertLwDtl", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: updateLw
	 * 설명: 장편게시글상세내역 수정
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int updateLwDtl(CustomMap customMap) {
		return sst.update("Lw.updateLwDtl", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: updateLwDelYn
	 * 설명: 장편게시글상세내역 삭제여부 수정
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int updateLwDtlDelYn(CustomMap customMap) {
		return sst.update("Lw.updateLwDtlDelYn", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectLwDtl
	 * 설명: 장편게시글상세내역 조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public CustomMap selectLwDtl(CustomMap customMap) {
		return sst.selectOne("Lw.selectLwDtl", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectLwDtlList
	 * 설명: 장편게시글상세내역 목록조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public List<CustomMap> selectLwDtlList(CustomMap customMap) {
		return sst.selectList("Lw.selectLwDtlList", customMap);
	}

	
}
