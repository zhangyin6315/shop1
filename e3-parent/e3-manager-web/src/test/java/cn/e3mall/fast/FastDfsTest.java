package cn.e3mall.fast;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.e3mall.common.utils.FastDFSClient;

public class FastDfsTest {
	@Test
public void testUpload() throws Exception {
	//创建配置文件内容tracker服务器地址
	//全局对象加载配置文件
	ClientGlobal.init("H:\\git\\e3-parent\\e3-parent\\e3-manager-web\\src\\test\\resources\\conf\\client.conf");
	//创建trackerClient对象
	TrackerClient trackerClient=new TrackerClient();
	//通过trackerClient获得TrackServer对象
	TrackerServer trackerServer=trackerClient.getConnection();
	//创建一个StorageServer的引用
	StorageServer storageServer=null;
	//创建一个StorageClient，参数需要TrackServer和StotageClient
	StorageClient storageClient=new StorageClient(trackerServer,storageServer);
	//使用StorageClient上传
	String[] strings=storageClient.upload_file("C:\\Users\\User\\Pictures\\Camera Roll\\无标题.png", "png", null);
	for(String string:strings) {
		System.out.println(string);
	}
	

}
	@Test
public void testFastrDfsClient() throws Exception {
	FastDFSClient fastDFSClient=new FastDFSClient("H:\\git\\e3-parent\\e3-parent\\e3-manager-web\\src\\test\\resources\\conf\\client.conf");
	//使用StorageClient上传
	String strings=fastDFSClient.uploadFile("C:\\Users\\User\\Pictures\\Camera Roll\\无标题.png");
	System.out.println(strings);
	

}
	
	
}
