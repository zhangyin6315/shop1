package cn.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbContentExample.Criteria;

@Service
public class ContentServiceImpl implements ContentService{
@Autowired
private TbContentMapper contentMapper; 
	@Override
	public E3Result addcontent(TbContent content) {
		// 将内容插入到表
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		return E3Result.ok();
	}
	@Override
	public List<TbContent> getContentList(Long categoryId) {
	
		// 根据categoryId查询
				TbContentExample example=new TbContentExample();
				Criteria criteria=example.createCriteria();
				//设置查询条件
				criteria.andCategoryIdEqualTo(categoryId);
				//执行查询
				List<TbContent> list=contentMapper.selectByExample(example);
			
		return list;
	}

}
