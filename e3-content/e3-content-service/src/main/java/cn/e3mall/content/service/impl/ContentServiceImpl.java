package cn.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbContentExample.Criteria;

@Service
public class ContentServiceImpl implements ContentService{
@Autowired
private TbContentMapper contentMapper; 
@Autowired
private JedisClient jedisClient;
@Value("${CONTENT_LIST}")
private String CONTENT_LIST;
	@Override
	public E3Result addcontent(TbContent content) {
		// 将内容插入到表
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		//删除缓存中对应数据
		jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		return E3Result.ok();
	}
	@Override
	public List<TbContent> getContentList(Long categoryId) {
		//查询缓存
		try {
		String json=jedisClient.hget(CONTENT_LIST, categoryId+"");
			if(StringUtils.isNotBlank(json)) {
				//有直接返回
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//没有查询数据库
	
		// 根据categoryId查询
			TbContentExample example=new TbContentExample();
			Criteria criteria=example.createCriteria();
			//设置查询条件
			criteria.andCategoryIdEqualTo(categoryId);
			//执行查询
				List<TbContent> list=contentMapper.selectByExampleWithBLOBs(example);
			//把结果添加到缓存
				try {
					jedisClient.hset(CONTENT_LIST, categoryId+"", JsonUtils.objectToJson(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
		return list;
	}
	@Override
	public E3Result editContent(TbContent content) {
		content.setUpdated(new Date());
		contentMapper.updateByPrimaryKey(content);
		//删除缓存中对应数据
		jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		return E3Result.ok();
	}
	@Override
	public E3Result deleteContent(String ids) {
		
		for(int i=0;i<ids.split(",").length;i++) {
			String id=ids.split(",")[i];
			//删除缓存中对应数据
		jedisClient.hdel(CONTENT_LIST,contentMapper.selectByPrimaryKey(Long.valueOf(id)).getCategoryId().toString());			
		contentMapper.deleteByPrimaryKey(Long.valueOf(id));
		}
		
		return E3Result.ok();
		}
}
