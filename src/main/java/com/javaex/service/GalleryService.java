package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;

	public List<GalleryVo> exeList() {
		System.out.println("GalleryService.exeList()");

		return galleryDao.list();
	}

	public String exeUpload(MultipartFile file) {
		System.out.println("GalleryService.exeUpload()");

		// 파일 저장 폴더
		String saveDir = "C:\\javaStudy\\upload";

		// (0) 파일 관련 정보 수집
		// 오리지널 파일명
		String orgName = file.getOriginalFilename();
		System.out.println("orgName: " + orgName);

		// 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("exName: " + exName);
		//System.out.println(orgName.substring(4));
		//System.out.println(orgName.lastIndexOf("."));

		// 저장 파일명(겹치지 않아야 함)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName: " + saveName);

		// 파일 사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize: " + fileSize);

		// 파일 전체 경로(저장파일명 포함)
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath: " + filePath);

		//내용 저장
		String content = null;
		System.out.println("content: "+content);
		
		// (1) 파일 정보를 db에 저장
		// vo 묶어주고
		GalleryVo galleryVo = new GalleryVo(content, filePath, orgName, saveName, fileSize);
		System.out.println(galleryVo);

		// db에 저장
		// dao의 메소드 호출해서 저장

		
		// (2) 파일을 하드디스크에 저장
		// 파일저장
		try {
			byte[] fileData = file.getBytes();

			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);

			bos.write(fileData);
			bos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return saveName;
	}
}
