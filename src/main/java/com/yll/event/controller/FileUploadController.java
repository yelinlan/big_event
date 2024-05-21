package com.yll.event.controller;

import com.yll.event.pagehelper.Result;
import com.yll.event.util.AliOssUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/")
@Tag(name = "文件上传", description = "文件上传")
@CrossOrigin
public class FileUploadController {

	@Operation(summary = "文件上传", description = "文件上传")
	@PostMapping("/upload")
	public Result upload(MultipartFile file) throws IOException {
		String url = AliOssUtil.uploadFile(file.getOriginalFilename(), file.getInputStream());
		return Result.success(url);
	}


}