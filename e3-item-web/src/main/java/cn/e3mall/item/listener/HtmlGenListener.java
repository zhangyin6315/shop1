package cn.e3mall.item.listener;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.e3mall.item.pojo.Item;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HtmlGenListener implements MessageListener {
	@Autowired
	private ItemService itemService;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Value("${HTML_GEN_PATH}")
	private String HTML_GEN_PATH;
	
	@Override
	public void onMessage(Message message) {
		try {
		//创建1个模板
		//取商品ID
		TextMessage textMessage=(TextMessage) message;
		String text=textMessage.getText();
		Long itemId=new Long(text);
		//查询商品信息 基本信息和商品描述
		TbItem tbItem = itemService.getItemById(itemId);
		Item item=new Item(tbItem);
		//取商品描述消息
		TbItemDesc tbItemDesc=itemService.getItemDescById(itemId);
		//创建一个map数据集 数据封装
		Map<Object, Object> data= new HashMap<>();
		data.put("item", item);
		data.put("itemDesc", tbItemDesc);
		
		//加载模板对象
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		Template template = configuration.getTemplate("item.ftl");
		//创建一个输出流指定文件名
		Writer out =new FileWriter(new File(HTML_GEN_PATH+itemId +".html"));		 
		template.process(data, out);
		//关闭流
		out.close();
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	}
}
