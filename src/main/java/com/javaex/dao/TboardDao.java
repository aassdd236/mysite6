package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.TboardVo;

@Repository
public class TboardDao {

	@Autowired
	private SqlSession sqlSession;

	// 리스트(검색X,페이징X)
		public List<TboardVo> boardSelectList2(Map<String, Integer> limitMap) {
			System.out.println("TboardDao.boardSelectList2()");
			
			List<TboardVo> boardList = sqlSession.selectList("tboard.selectList2", limitMap);
			System.out.println(boardList);
			return null;
		}
	
	// 리스트(검색X,페이징X)
	public List<TboardVo> boardSelectList() {
		System.out.println("TboardDao.boardSelectList()");

		List<TboardVo> boardList = sqlSession.selectList("tboard.selectList");

		return boardList;

	}

	// 글 전체 갯수 //리스트(검색X,페이징X)
	public int selectTotalCnt() {
		System.out.println("TboardDao.selectTotalCnt()");

		int totalCount = sqlSession.selectOne("tboard.selectTotalCnt");

		return totalCount;
	}
	

}
