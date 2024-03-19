package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.TboardService;
import com.javaex.vo.TboardVo;

@Controller
public class TboardController {

	@Autowired
	private TboardService tboardService;

	// 리스트(검색X,페이징 O)
	@RequestMapping(value = "/tboard/list2", method = { RequestMethod.GET, RequestMethod.POST })
	public String list2(@RequestParam(value="crtPage", required=false, defaultValue="1") int crtPage) {
		//파라미터 crtPage가 없으면 1로 처리한다는 뜻
		System.out.println("TboardController.list2()");

		System.out.println(crtPage);

		List<TboardVo> boardList = tboardService.exeList2(crtPage);
		
		return "tboard/list";
	}

	// 리스트(검색X,페이징 X)
	@RequestMapping(value = "/tboard/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("TboardController.list()");

		// boardService를 통해서 리스트를 가져온다
		List<TboardVo> boardList = tboardService.exeList();
		// 모델에 리스트를 담는다(포워드)
		model.addAttribute("boardList", boardList);

		return "tboard/list";
	}

}
