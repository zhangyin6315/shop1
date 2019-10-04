package cn.e3mall.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.pojo.SearchResult;

/**
 * 商品搜索
 * @author User
 *
 */
@Repository
public class SearchDao {
	@Autowired
	private SolrClient solrClient;
	public SearchResult search(SolrQuery solrQuery) throws Exception {
		//根据solrQuery查询索引库
		QueryResponse queryResponse =solrClient.query(solrQuery);
		//取查询结果
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		//取查询结果总记录数
		Long numFound=solrDocumentList.getNumFound();
		SearchResult result=new SearchResult();
		result.setRecourdCount(numFound);
		
		List <SearchItem>  itemList=new ArrayList<>();
		//取商品列表，需要取高亮显示
		Map< String, Map<String,List<String>>> hightLight=queryResponse.getHighlighting();
		for (SolrDocument solrDocument : solrDocumentList) {
			SearchItem searchItem=new SearchItem();
			searchItem.setId((String) solrDocument.get("id"));
			searchItem.setCategory_name((String) solrDocument.get("item_category_name"));
			searchItem.setImage((String) solrDocument.get("item_image"));
			searchItem.setPrice((Long) solrDocument.get("item_price"));
			searchItem.setSell_point((String) solrDocument.get("item_sell_point"));
			//取高亮
			List<String> list = hightLight.get(solrDocument.get("id")).get("item_title");
			String title="";
			if(list!=null&&list.size()>0) {
				title=list.get(0);
			}else {
				title=(String) solrDocument.get("item_title");
			}
			searchItem.setTitle(title);
			//添加到商品列表
			itemList.add(searchItem);
		}
		result.setItemList(itemList);
		return result;
	}

}
