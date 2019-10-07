package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.pojo.EasyUIDataFGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemDescExample;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbItemExample.Criteria;
import cn.e3mall.service.ItemService;


@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private JmsTemplate jmsTemplate;
	@Resource
	private Destination topicDestination;
	@Autowired
	private JedisClient jedisClient;
@Value("${REDIS_ITEM_PER}")
private String REDIS_ITEM_PER;
@Value("${ITEM_CACHE_EXPIRE}")
private Integer ITEM_CACHE_EXPIRE;
	@Override
	public TbItem getItemById(Long itemId) {
		try {
			//查询缓存
			String json = jedisClient.get("REDIS_ITEM_PER:"+itemId+"BASE");
			if(StringUtils.isNoneBlank(json)) {
				TbItem tbItem=JsonUtils.jsonToPojo(json, TbItem.class);
				return tbItem;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		TbItem tbItem=itemMapper.selectByPrimaryKey(itemId);
		TbItemExample example =new TbItemExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andIdEqualTo(itemId);
		//执行查询
		List<TbItem> list=itemMapper.selectByExample(example);
		if(list!=null&&list.size()>0) {
			try {
			//添加缓存
			jedisClient.set("REDIS_ITEM_PER:"+itemId+"BASE",JsonUtils.objectToJson(list.get(0)));
			//设置过期时间
			jedisClient.expire("REDIS_ITEM_PER:"+itemId+"BASE",ITEM_CACHE_EXPIRE);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return list.get(0);
		}
		
		return null;
	}
	@Override
	public EasyUIDataFGridResult getItemList(int page, int rows) {
		// 设置分页信息
		PageHelper.startPage(page,rows);
		//执行查询
		TbItemExample example=new TbItemExample();
		List<TbItem> list=itemMapper.selectByExample(example);
		//创建返回值对象
		EasyUIDataFGridResult result=new EasyUIDataFGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<TbItem> pageInfo=new PageInfo<>(list);
		//取总记录数
		long total=pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}
	

	
	
	@Override
	public E3Result addItem(TbItem item, String desc) {
		//补全item 属性
		
		//1正常,2下架,3删除
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//向商品表插入
		itemMapper.insert(item);
		//创建一个商品描述表对应的pojo
		TbItemDesc itemDesc=new TbItemDesc();
		//补全属性
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		//向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
	
		return E3Result.ok();
	}
	@Override
	public E3Result delItem(String ids) {
		String [] a=ids.split(",");
		for(int i=0;i<a.length;i++) {
			itemMapper.deleteByPrimaryKey(Long.valueOf(a[i]));
			itemDescMapper.deleteByPrimaryKey(Long.valueOf(a[i]));
		}
		return E3Result.ok();
	}
	/**
	 * 下架
	 */
	@Override
	public E3Result instockItem(String ids) {
				TbItem tbItem=null;
				String [] a=ids.split(",");
				for(int i=0;i<a.length;i++) {
					tbItem=itemMapper.selectByPrimaryKey(Long.valueOf(a[i]));
					//1正常,2下架,3删除
					tbItem.setStatus((byte)2);
					itemMapper.updateByPrimaryKey(tbItem);
				}
				return E3Result.ok();
	}
	/**
	 * 上架
	 */
	@Override
	public E3Result reshelfItem(String ids) {
				TbItem tbItem=null;
				String [] a=ids.split(",");
				for(int i=0;i<a.length;i++) {
					tbItem=itemMapper.selectByPrimaryKey(Long.valueOf(a[i]));
					//1正常,2下架,3删除
					tbItem.setStatus((byte)1);
					itemMapper.updateByPrimaryKey(tbItem);
				}
				return E3Result.ok();
	}
	@Override
	public TbItemDesc selectTbItemDesc(Long itemId) {
	
		try {
			//查询缓存
			String json = jedisClient.get("REDIS_ITEM_PER:"+itemId+"DESC");
			if(StringUtils.isNoneBlank(json)) {
				TbItemDesc tbItemDesc=JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return tbItemDesc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItemDesc tbItemDesc=itemDescMapper.selectByPrimaryKey(itemId);
			try {
			//添加缓存
			jedisClient.set("REDIS_ITEM_PER:"+itemId+"DESC",JsonUtils.objectToJson(tbItemDesc));
			//设置过期时间
			jedisClient.expire("REDIS_ITEM_PER:"+itemId+"DESC",ITEM_CACHE_EXPIRE);
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		return tbItemDesc;
	}
	@Override
	public E3Result updateItem(TbItem item, String desc) {
				item.setCreated(itemMapper.selectByPrimaryKey(item.getId()).getCreated());
				
				//1正常,2下架,3删除		
				item.setStatus((byte)1);
				item.setUpdated(new Date());
				//向商品表插入
				itemMapper.updateByPrimaryKey(item);
				//创建一个商品描述表对应的pojo
				TbItemDesc itemDesc=new TbItemDesc();
				//补全属性
				itemDesc.setCreated(itemDescMapper.selectByPrimaryKey(item.getId()).getCreated());
				itemDesc.setItemDesc(desc);
				itemDesc.setUpdated(new Date());
				//向商品描述表插入数据
				itemDescMapper.updateByPrimaryKey(itemDesc);
				//返回成功
				return E3Result.ok();
	}
	@Override
	public void sendaddItemMessage(String itemId) {
		// TODO Auto-generated method stub
		//发送消息
		jmsTemplate.send(topicDestination,new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				
				return session.createTextMessage(itemId);
			}
		});
	}
	@Override
	public TbItemDesc getItemDescById(Long itemId) {
		TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		return tbItemDesc;
	}
	

}
