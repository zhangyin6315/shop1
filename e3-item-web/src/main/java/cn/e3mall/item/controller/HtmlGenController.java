package cn.e3mall.item.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;
/*
 * 测试静态页面生成
 * 
 */
@Controller
public class HtmlGenController {
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@RequestMapping("/testHtml")
	@ResponseBody
	public String testHtml () throws Exception{
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		//加载一个模板文件创建一个文件对象
				Template template=configuration.getTemplate("hello.ftl");
				//创建一个数据集 pojo或者map 可以使用map
				Map<Object, Object> data=new HashMap<>();
				data.put("hello", "hello freemarker");
				//创建一个writer对象 指定输出的文件的路径及文件名
				Writer out=new FileWriter(new File("F:\\html\\hello.html"));
				//生成静态页面
				template.process(data, out);
				//关闭流
				out.close();
				return "ok";
		
	}

}
