package cn.e3mall.search.message;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;


import cn.e3mall.search.service.SearchItemService;
/**
 * 监听商品添加事件
 * @author User
 *
 */
public class ItemAddMessageLister implements MessageListener {
@Autowired
private SearchItemService searchItemService;
	@Override
	public void onMessage(Message message) {
		
		try {
			// 取商品id
			TextMessage textMessage=(TextMessage) message;
			String text = textMessage.getText();
			Long itemId =new Long(text);
//			System.out.println(new Data());
//			Thread.sleep(1000);
//			System.out.println(new Data());
			searchItemService.importItemById(itemId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
