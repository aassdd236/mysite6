package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestService {

	@Autowired
	private GuestDao guestDao;

	public List<GuestVo> exeList() {
		System.out.println("GuestService.exeList()");

		return guestDao.list();
	}

	public int exeWrite(GuestVo guestVo) {
		System.out.println("GuestService.exeWrite()");

		int count = guestDao.write(guestVo);

		return count;
	}

	public int exeDelete(GuestVo guestVo) {
		System.out.println("GuestService.exeDelete()");

		int count = guestDao.delete(guestVo);

		return count;
	}
	
	public GuestVo exeAddandGuest(GuestVo guestVo) {
		System.out.println("GuestService.exeAddandGuest()");
		
		//저장
		//System.out.println("번호 저장 전: "+guestVo); //번호 저장 전
		guestDao.insertSelectKey(guestVo);
		//System.out.println("번호 저장 후: "+guestVo); //번호 저장 후
		
		//1명 데이터 가져오기
		GuestVo gVo=guestDao.guestbookSelectOne(guestVo.getNo());
		
		return gVo;
	}

}
