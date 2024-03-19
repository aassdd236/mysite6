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

	
	// 글 전체 갯수 //리스트(검색O,페이징O)
	public int selectTotalCnt3(String keyword) {
		System.out.println("TboardDao.selectTotalCnt3()");

		int totalCount = sqlSession.selectOne("tboard.selectTotalCnt3", keyword);

		return totalCount;
	}
	
	// 리스트(검색O,페이징O)
		public List<TboardVo> boardSelectList3(Map<String, Object> limitMap) {
			System.out.println("TboardDao.boardSelectList3()");

			List<TboardVo> boardList = sqlSession.selectList("tboard.selectList3", limitMap);

			return boardList;

		}
		
	// 리스트(검색X,페이징O)
		public List<TboardVo> boardSelectList2(Map<String, Integer> limitMap) {
			System.out.println("TboardDao.boardSelectList2()");
			
			List<TboardVo> boardList = sqlSession.selectList("tboard.selectList2", limitMap);
			return boardList;
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
