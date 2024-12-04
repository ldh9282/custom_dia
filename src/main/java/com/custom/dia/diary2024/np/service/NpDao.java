package com.custom.dia.diary2024.np.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.dia.cmmn.model.CustomMap;

@Repository
public class NpDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	/**
	 * <pre>
	 * 메서드명: selectNotePostSno
	 * 설명: 게시글일련번호 조회
	 * </pre>
	 * @param
	 * @return
	 */
	public String selectNotePostSno() {
		return sst.selectOne("Np.selectNotePostSno");
	}
	
	/**
	 * <pre>
	 * 메서드명: insertNotePost
	 * 설명: 게시글 등록
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int insertNotePost(CustomMap customMap) {
		return sst.insert("Np.insertNotePost", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectNotePost
	 * 설명: 게시글 조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public CustomMap selectNotePost(CustomMap customMap) {
		return sst.selectOne("Np.selectNotePost", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: selectNotePostList
	 * 설명: 게시글 목록조회
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public List<CustomMap> selectNotePostList(CustomMap customMap) {
		return sst.selectList("Np.selectNotePostList", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: updateNotePost
	 * 설명: 게시글 수정
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int updateNotePost(CustomMap customMap) {
		return sst.update("Np.updateNotePost", customMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: updateNpDelYn
	 * 설명: 게시글 삭제여부 수정
	 * </pre>
	 * @param customMap
	 * @return
	 */
	public int updateNpDelYn(CustomMap customMap) {
		return sst.update("Np.updateNpDelYn", customMap);
	}

	
}
