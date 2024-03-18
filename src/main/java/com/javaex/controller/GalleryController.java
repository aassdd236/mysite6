package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping(value="/gallery/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(HttpServletRequest request) {
		System.out.println("GalleryController.list()");
		
		List<GalleryVo> galleryList = galleryService.exeList();
		request.setAttribute("galleryList", galleryList);
		
		return "gallery/list";
	}
	
	@RequestMapping(value="/gallery/upload", method= {RequestMethod.POST})
	public String upload(@RequestParam(value = "file") MultipartFile file, Model model) {
		System.out.println("GalleryController.upload()");
		
		 String saveName = galleryService.exeUpload(file);
	     model.addAttribute("saveName",saveName);
		
		return "gallery/list";
	}
}
