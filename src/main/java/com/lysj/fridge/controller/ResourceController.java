package com.lysj.fridge.controller;

import cn.soul.util.web.ParamUtil;
import cn.soul.util.web.Resp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;



@RestController
@RequestMapping("/upload")
public class ResourceController {

	@PostMapping("image")
	public Resp edit(MultipartFile file, String module) {
		if (ParamUtil.isBlack(module)) {
			return new Resp(Resp.PARAM_ERROR, "请指定模块名!");
		}
		if (file == null || file.isEmpty()) {
			return new Resp(Resp.PARAM_ERROR, "文件为空!");
		}
		String filename = System.currentTimeMillis() + "";
		String path = "E:\\FApic\\" + module;
		String realFileName = fileUpload(file, path, filename);
		return new Resp(Resp.SUCCESS, "图片上传成功!", realFileName);
	}

	/**
	 * 批量处理文件上传
	 */
	public static String filesUpload(MultipartFile[] files, String path, String filename) {
		if (files == null || files.length == 0) {
			return "";
		}
		StringBuilder filenames = new StringBuilder();
		for (int i = 0; i < files.length; i++) {
			if (files[i] == null || files[i].isEmpty()) {
				break;
			}
			String fileName = files[i].getOriginalFilename();// 获取文件名
			String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 获取文件的后缀名
			String descFileName = System.currentTimeMillis() + suffixName;// 文件名
			File dest = new File(path, descFileName);
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			try {
				files[i].transferTo(dest);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			filenames.append(descFileName).append("-");
		}
		return filenames.substring(0, filenames.length() - 1);
	}

	/**
	 * 处理单文件上传 返回文件名
	 */
	public static String fileUpload(MultipartFile file, String destPath, String destFileName) {

		String tempFileName = file.getOriginalFilename();// 获取文件名
		String suffixName = tempFileName.substring(tempFileName.lastIndexOf(".")); // 获取文件的后缀名

		// 判断目录是否存在,不存在则创建目录
		destFileName = destFileName + suffixName;// 文件名
		File dest = new File(destPath, destFileName);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return destFileName;
	}
}
