package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class GuestbookController {

	@Autowired
	private GuestService guestService;

	// 게시글 리스트
	@RequestMapping(value = "/guest/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(HttpServletRequest request) {
		System.out.println("GuestbookController.list()");

		List<GuestVo> guestList = guestService.exeList();

		request.setAttribute("guestList", guestList);

		return "guestbook/addList";
	}

	// 게시글 작성
	@RequestMapping(value = "/guest/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute GuestVo guestVo) {
		System.out.println("GuestbookController.write()");

		guestService.exeWrite(guestVo);

		return "redirect:/guest/list";
	}

	// 게시글 삭제 폼
	@RequestMapping(value = "/guest/deleteform", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm(@RequestParam int no, Model model) {
		System.out.println("GuestbookController.deleteForm()");

		 model.addAttribute("no", no);
		return "guestbook/deleteForm";
	}

	// 게시글 삭제
	@RequestMapping(value = "/guest/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute GuestVo guestVo) {
		System.out.println("GuestbookController.delete()");

		guestService.exeDelete(guestVo);

		return "redirect:/guest/list";
	}
	
	//////////////////////////////////
	//ajax 방명록 메인
	@RequestMapping(value = "/guest/ajaxindex", method = { RequestMethod.GET, RequestMethod.POST})
	public String ajaxIndex() {
		System.out.println("GuestbookController.ajaxIndex()");
		
		return "guestbook/ajaxIndex";
	}
	
	//등록
	public int exeAddandGuest(GuestVo guestVo) {
		System.out.println("GuestbookController.exeAddandGuest()");
		
		return 1;
	}
	
	
	
	
	
	
	
	
	

}
