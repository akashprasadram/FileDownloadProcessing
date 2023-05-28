package com.spring.junittest;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileProcessing {
	@Value("${uploadDir}")
	private String UPLOAD_DIR;
	
	@RequestMapping(value="/home",method = RequestMethod.GET)
	public String home(ModelMap map) {
		System.out.println("Home page called............");
		map.addAttribute("fileName", "");
		map.addAttribute("success", "");
		return "uploadpage";
	}
	
	@RequestMapping(value="/home1",method = RequestMethod.GET)
	public String home1(ModelMap map) {
		System.out.println("Home page called............");
		//Since I am not storing file in database i am again returning empty string
		map.addAttribute("fileName", "");
		map.addAttribute("success", "FileDownloaded Successfully");
		return "uploadpage";
	}
	
	@RequestMapping(value="/fileupload", method= RequestMethod.POST)
	public String uploadfile(@RequestParam("file") MultipartFile file,ModelMap map) throws IllegalStateException, IOException {
		file.transferTo(new File(UPLOAD_DIR+file.getOriginalFilename()));
		
		//We can store all the file names in a database and fetch them and return them as list in modelAttribute
		//For now I am not storing any file names into the database and only return the same file which is uploaded
		map.addAttribute("fileName", file.getOriginalFilename());
		map.addAttribute("success", "File Uploaded Successfully");
		return "uploadpage";
	}
}
