package cn.e3mall.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;


import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerTest {
	
	@Test
	public void testFreemarker() throws Exception{
		//创建一个模板文件
		//创建一个configuration 对象 设置模板文件保存密目录 设置模板文件编码 UTf-8
		Configuration configuration=new Configuration(Configuration.getVersion());
		configuration.setDirectoryForTemplateLoading(new File("C:/Users/User/Workspace/shop/e3-item-web/src/main/webapp/WEB-INF/ftl"));
		configuration.setDefaultEncoding("utf-8");
		//加载一个模板文件创建一个文件对象
		Template template=configuration.getTemplate("student.ftl");
		//创建一个数据集 pojo或者map 可以使用map
		Map<Object, Object> data=new HashMap<>();
		data.put("hello", "hello freemarker");
		Student student =new Student(1,"小米",10,"北京");
		data.put("student",student);
		List< Student> list =new ArrayList<Student>();
		list.add(new Student(1,"小米",10,"北京"));
		list.add(new Student(2,"小米",10,"北京"));
		list.add(new Student(3,"小米",10,"北京"));
		list.add(new Student(4,"小米",10,"北京"));
		list.add(new Student(5,"小米",10,"北京"));
		list.add(new Student(6,"小米",10,"北京"));
		list.add(new Student(7,"小米",10,"北京"));
		list.add(new Student(8,"小米",10,"北京"));
		list.add(new Student(9,"小米",10,"北京"));
		list.add(new Student(10,"小米",10,"北京"));
		list.add(new Student(11,"小米",10,"北京"));
		data.put("stulist", list);
		//添加日期类型
		data.put("date",new Date());
		//空值处理
		data.put("val", "a");
		//创建一个writer对象 指定输出的文件的路径及文件名
		Writer out=new FileWriter(new File("F:\\html\\student.html"));
		//生成静态页面
		template.process(data, out);
		//关闭流
		out.close();
		
	}

}
