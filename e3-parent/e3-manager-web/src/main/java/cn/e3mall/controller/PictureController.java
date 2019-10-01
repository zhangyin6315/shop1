package cn.e3mall.controller;
/**
 * 图片上传Controller
 * @author User
 *
 */

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.common.utils.FastDFSClient;
import cn.e3mall.common.utils.JsonUtils;
@Controller
public class PictureController {
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
@RequestMapping(value="/pic/upload",produces=MediaType.TEXT_EVENT_STREAM_VALUE+";charset=utf-8")
@ResponseBody
public String uploadFile(MultipartFile uploadFile) {
	//把图片上传到图片服务器
	try {
		FastDFSClient fastDFSClient= new FastDFSClient("classpath:conf/client.conf");
	//取文件扩展名
		String originalFilename=uploadFile.getOriginalFilename();
		String extName=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
	//得到一个图片的地址和文件名
		String url = fastDFSClient.uploadFile(uploadFile.getBytes(),extName);
	//补充为完整的url
		url=IMAGE_SERVER_URL+url;
	//封装到map
		Map result=new HashMap<>();
		result.put("error",0);
		result.put("url", url);
		return JsonUtils.objectToJson(result);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		Map result=new HashMap<>();
		result.put("error",1);
		result.put("message", "图片上传失败");
		return JsonUtils.objectToJson(result);
	}
}
}
