//package cn.e3mall.solrj;
//
//import org.apache.solr.client.solrj.SolrClient;
//import org.apache.solr.client.solrj.impl.HttpSolrClient;
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
//
//}
