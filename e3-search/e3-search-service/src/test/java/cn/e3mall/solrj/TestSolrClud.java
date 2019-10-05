//package cn.e3mall.solrj;
//
//
//import org.apache.solr.client.solrj.impl.CloudSolrClient;
//import org.apache.solr.common.SolrInputDocument;
//import org.junit.Test;
///**
// * 测试超时 系统算力不足
// */

//public class TestSolrClud  {
//
//	@Test
//public void testAddDocument() throws Exception{
//	//创建集群的连接 应该使用CloudSolrClient创建
//	String ZK_HOST="192.168.21.154:2181,192.168.21.155:2181,192.168.21.156:2181";
//	//zkHost： zookeeper的地址列表
//	CloudSolrClient client=new CloudSolrClient.Builder().withZkHost(ZK_HOST).build();
//	//设置一个默认的Collection属性
//    client.setDefaultCollection("mycore");
// 	//创建一个文档对象
//	SolrInputDocument document=new SolrInputDocument();
//	//	向文档中添加域
//	document.setField("id", "doc1");
//	document.setField("item_title", "测试商品01");
//	document.setField("item_price", 1000);
//	//把文件写入索引库
//	client.add(document);
////	//提交
//	client.commit();
//}
//}
