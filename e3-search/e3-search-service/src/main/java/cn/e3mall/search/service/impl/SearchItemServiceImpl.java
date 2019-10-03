package cn.e3mall.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient.Builder;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.search.mapper.ItemMapper;
import cn.e3mall.search.service.SearchItemService;
/**
 * 维护索引库
 * @author User
 *
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
@Autowired
private ItemMapper itemMapper;
@Autowired
private SolrClient solrClient;
	@Override
	public E3Result importAllItems() {

		try {
			// 查询商品
		List<SearchItem> itemList=itemMapper.getItemList();
		//遍历列表
		for (SearchItem searchItem : itemList) {
			//创建一个文档对象SolrInputDocument
			SolrInputDocument document=new SolrInputDocument();
			//向对象中添加域。文档中必须有一个域的id所有域的名称必须在manger—schema中定义
			document.addField("id", searchItem.getId());
			document.addField("item_title",searchItem.getTitle());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			//将文档写入索引库
			solrClient.add(document);
//			solrClient.deleteById(searchItem.getId());
			
			}
		solrClient.commit();
		return E3Result.ok();

		} catch (Exception e) {
			
				// TODO Auto-generated catch block
				e.printStackTrace();
				return E3Result.build(500, "数据导入异常");
			}
	
		


		
		
	}

}
