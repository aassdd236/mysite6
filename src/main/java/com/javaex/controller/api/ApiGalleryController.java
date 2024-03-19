package com.javaex.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.javaex.service.GalleryService;

@Controller
public class ApiGalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	
}
