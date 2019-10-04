//package cn.e3mall.solrj;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.solr.client.solrj.SolrClient;
//import org.apache.solr.client.solrj.SolrQuery;
//import org.apache.solr.client.solrj.impl.HttpSolrClient;
//import org.apache.solr.client.solrj.response.QueryResponse;
//import org.apache.solr.common.SolrDocument;
//import org.apache.solr.common.SolrDocumentList;
//import org.apache.solr.common.SolrInputDocument;
//import org.junit.Test;
//
//public class TestSolrJ {
//	@Test
//public void addDocument() throws Exception{
//	//创建一个SolrServer对象，创建一个连接 参数solr服务的url
//	SolrClient solrServer = new HttpSolrClient.Builder("http://192.168.21.144:8983/solr/mycore").build();
//	//创建一个文档对象SolrInputDocument
//	SolrInputDocument document=new SolrInputDocument();
//	
//	//向对象中添加域。文档中必须有一个域的id所有域的名称必须在manger—schema中定义
//	document.addField("id", "doc1");
//	document.addField("item_title", "测试商品01");
//	document.addField("item_price", 1000);
//	//将文档写入索引库
//	solrServer.add(document);
//	//提交
//	solrServer.commit();
// 
//}
//@Test
//	public void delDocument() throws Exception{
//		//创建一个SolrServer对象，创建一个连接 参数solr服务的url
//		SolrClient solrServer = new HttpSolrClient.Builder("http://192.168.21.144:8983/solr/mycore").build();
//	
//		//向对象中添加域。文档中必须有一个域的id所有域的名称必须在manger—schema中定义
//	
//		solrServer.deleteById("doc1");
////		solrServer.deleteByQuery("id:doc1");
//		//提交
//		solrServer.commit();
//	 
//	}
//@Test
//public void queryIndex() throws Exception{
//	//创建一个SolrServer对象，创建一个连接 参数solr服务的url
//	SolrClient solrServer = new HttpSolrClient.Builder("http://192.168.21.144:8983/solr/mycore").build();
//	//创建一个solrQuery对象
//	SolrQuery solrQuery=new SolrQuery();
//	//设置查询条件
//	solrQuery.setQuery("*:*");
////	solrQuery.set("q", "*:*");
//	
//	//执行查询QueryResponse对象
//	QueryResponse queryResponse=solrServer.query(solrQuery);
//	//取文档列表获取查询的总记录数
//	SolrDocumentList solrDocumentList=queryResponse.getResults();
//	System.out.println(solrDocumentList.getNumFound());
//	//遍历列表，取出域的内容
//	for (SolrDocument solrDocument : solrDocumentList) {
//		System.out.println(solrDocument.get("id"));
//		System.out.println(solrDocument.get("item_title"));
//		System.out.println(solrDocument.get("item_price"));
//		System.out.println(solrDocument.get("item_image"));
//		System.out.println(solrDocument.get("item_sell_point"));
//		
//	}
//}
//@Test
//public void queryIndex1() throws Exception{
//	//创建一个SolrServer对象，创建一个连接 参数solr服务的url
//	SolrClient solrServer = new HttpSolrClient.Builder("http://192.168.21.144:8983/solr/mycore").build();
//	//创建一个solrQuery对象
//	SolrQuery solrQuery=new SolrQuery();
//	//设置查询条件
//	solrQuery.setQuery("手机");
//	solrQuery.setStart(0);
//	solrQuery.setRows(20);
//	solrQuery.set("df", "item_title");
//	solrQuery.setHighlight(true);
//	solrQuery.set("hl.fl","item_title");
//	solrQuery.setHighlightSimplePre("<em>");
//	solrQuery.setHighlightSimplePost("<em>");
////	solrQuery.set("q", "*:*");
//	
//	//执行查询QueryResponse对象
//	QueryResponse queryResponse=solrServer.query(solrQuery);
//	//取文档列表获取查询的总记录数
//	SolrDocumentList solrDocumentList=queryResponse.getResults();
//	System.out.println(solrDocumentList.getNumFound());
//	//遍历列表，取出域的内容
//	for (SolrDocument solrDocument : solrDocumentList) {
//		System.out.println(solrDocument.get("id"));
//		//取高亮
//		Map<String, Map<String, List<String>>> hightlight=queryResponse.getHighlighting();
//		List<String> list = hightlight.get(solrDocument.get("id")).get("item_title");
//		String title="";
//		if(list!=null&&list.size()>0) {
//			title=list.get(0);
//		}else {
//			title=(String) solrDocument.get("item_title");
//		}
//		System.out.println(title);
//		System.out.println(solrDocument.get("item_price"));
//		System.out.println(solrDocument.get("item_image"));
//		System.out.println(solrDocument.get("item_sell_point"));
//		
//	}
//}
//}
