package cn.e3mallpagehelper; 

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;

public class PageHelperTest {
	@Test
 public void testPageHelper() throws Exception{
	 //初始化Spring容器
	 ApplicationContext applicationContext =new ClassPathXmlApplicationContext("classpath:/spring/applicationContext-dao.xml");
	 //从容器中取的Maper代理对象
	 TbItemMapper itemMapper= applicationContext.getBean(TbItemMapper.class);
	 //执行SQL语句前设置分页信息使用PageHelper的startPage方法
	 PageHelper.startPage(1,10);
	 
	 //执行查询
	 TbItemExample example=new TbItemExample();
	 List<TbItem> list=itemMapper.selectByExample(example);
	 //取得分页信息，PageInfo1、总记录数、2总页数。当前页码
	 PageInfo<TbItem> pageInfo=new PageInfo<>(list);
	 System.err.println(pageInfo.getTotal());
	 System.out.println(pageInfo.getPages());
	 System.out.println(pageInfo.getPageNum());
	 System.out.println(list.size());
	//关闭applicationContext
	 ((ConfigurableApplicationContext)applicationContext).close();
 }
}
