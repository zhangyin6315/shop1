package cn.e3mall.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.dao.SearchDao;
import cn.e3mall.search.service.SearchService;
/**
 * 商品搜索
 */
@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDao searchDao;
	@Override
	public SearchResult search(String keyword, int page, int rows) throws Exception {
		//创建一个solrQuery对象
		SolrQuery solrQuery=new SolrQuery();
		//设置查询条件
		solrQuery.setQuery(keyword);
		//设置分页
		if (page<=0) {
			page=1;
		}
		solrQuery.setStart((page-1)*rows);
		solrQuery.setRows(rows);
		//设置默认搜索域
		solrQuery.set("df", "item_title");
		solrQuery.setHighlight(true);
		solrQuery.set("hl.fl","item_title");
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		solrQuery.setHighlightSimplePost("<em>");
		//执行查询
		SearchResult searchResult = searchDao.search(solrQuery);
		long recordCount=searchResult.getRecourdCount();
		int totalPages=(int) (recordCount/rows);
		if(recordCount%rows>0) {
			totalPages++;
		}
		searchResult.setTotalPages(totalPages);
		return searchResult;
	}

	

}
