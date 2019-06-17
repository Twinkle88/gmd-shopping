package com.springcloud.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springcloud.common.UploadUtils;
import com.springcloud.vo.ResultValue;

@RestController
public class FileUploadController {

	// 从application.properties文件中获得指定键的值，并赋给相应的成员变量
	@Value("${web.user-path}")
	private String userPath;

	@Value("${web.goods-path}")
	private String goodsPath;

	/**
	 * 上传用户图片
	 * 
	 * @param file 图片文件
	 * @return 成功返回0以及图片的文件名和扩展名，失败返回1
	 */
	@RequestMapping(value = "/userUpload")
	public ResultValue userUpload(@RequestParam("userPhoto") MultipartFile file) {
		ResultValue rv = new ResultValue();
		try {
			Map<String, Object> map = this.UploadFile(userPath, file);
			if (map != null && map.size() > 0) {
				rv.setCode(0);
				rv.setDataMap(map);
				return rv;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户头像上传失败！！！");
		return rv;

	}

	/**
	 * 上传商品图片
	 * 
	 * @param file 图片文件
	 * @return 成功返回0以及图片的文件名和扩展名，失败返回1
	 */
	@RequestMapping(value = "/goodsUpload")
	public ResultValue goodsUpload(@RequestParam("goodsPhoto") MultipartFile file) {
		ResultValue rv = new ResultValue();
		try {
			Map<String, Object> map = this.UploadFile(goodsPath, file);
			if (map != null && map.size() > 0) {
				rv.setCode(0);
				rv.setDataMap(map);
				return rv;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("商品图片上传失败！！！");
		return rv;

	}

	@RequestMapping(value = "/deleteGoodsPhoto")
	public ResultValue deleteGoodsPhoto(@RequestParam("goodsPhoto") String goodsPhoto) {
		ResultValue rv = new ResultValue();
		try {
			// 从url中获得商品图片的名字
			int indexOf = goodsPhoto.lastIndexOf("/");
			if (indexOf != -1) {
				String fileName = goodsPhoto.substring(indexOf + 1);
				File file = new File(this.goodsPath + fileName);
				
				file.delete();
				rv.setCode(0);
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;

	}
	
	
	@RequestMapping(value = "/deleteUsersPhoto")
	public ResultValue deleteUsersPhoto(@RequestParam("userPhoto") String userPhoto) {
		ResultValue rv = new ResultValue();
		try {
			// 从url中获得商品图片的名字
			int indexOf = userPhoto.lastIndexOf("/");
			if (indexOf != -1) {
				String fileName = userPhoto.substring(indexOf + 1);
				File file = new File(this.userPath + fileName);
				
				file.delete();
				rv.setCode(0);
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;

	}

	

	/**
	 * 上传文件
	 * 
	 * @param path 上传文件的路径
	 * @param file 上传的文件
	 * @return 成功返回java.util.Map类型的实例，否则返回null
	 * @throws IOException
	 */
	private Map<String, Object> UploadFile(String path, MultipartFile file) throws IOException {
		// 先获得新的文件名
		String fileName = UploadUtils.getFileName();
		// 根据上传的文件名获得文件的扩展名
		String extendedName = UploadUtils.getExendedName(file.getOriginalFilename());
		// 上传文件,三步
		// 1,将文件转换为字节类型的数组
		byte[] bytes = file.getBytes();
		// 2,创建一个文件File类对象，并且设置文件上传的路径及文件名
		File saveFile = new File(path + fileName + extendedName);
		// 3,上传文件
		FileCopyUtils.copy(bytes, saveFile);

		// 当文件上传成功时，将文件新的文件名与扩展名传递回视图层，显示图片，存入数据库
		Map<String, Object> map = new HashMap<>();
		map.put("fileName", fileName);
		map.put("extendedName", extendedName);
		return map;
	}
}
