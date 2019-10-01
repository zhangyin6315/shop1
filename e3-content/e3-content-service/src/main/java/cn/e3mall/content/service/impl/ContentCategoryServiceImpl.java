package cn.e3mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;

/**
 * 内容分类管理
 * @author User
 *
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{
@Autowired
private TbContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EasyUITreeNode> getContentCatList(Long parentId) {
		// 根据parentId查询子节点
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria=example.createCriteria();
		//设置查询条件
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> catList=contentCategoryMapper.selectByExample(example);
		//转换为EasyUITreeNode的列表
		List<EasyUITreeNode> nodelist=new ArrayList<EasyUITreeNode>();
		for(TbContentCategory tbContentCategory:catList) {
			EasyUITreeNode  node=new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			//添加到列表
			nodelist.add(node);
		
		}
		return nodelist;
	}
	@Override
	public E3Result addContentCategory(Long parentId, String name) {
		// 创建一个tb_content_category 的pojo 
		TbContentCategory contentCategory =new TbContentCategory();
		
		//设置pojo属性
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		//1正常 2删除
		contentCategory.setStatus(1);
		//新添加的一定是叶子节点
		contentCategory.setIsParent(false);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		
		//插入到数据库
		contentCategoryMapper.insert(contentCategory);
		//判断父节点的isparent，如果不是false改为TRUE
		TbContentCategory parent=contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parent.getIsParent()) {
			parent.setIsParent(true);
			//更新到数据库
			contentCategoryMapper.updateByPrimaryKey(parent);
		}
		//返回结果
		
		return E3Result.ok(contentCategory);
	}
	@Override
	public E3Result updateContentCategory(Long id, String name) {
		// 创建一个tb_content_category 的pojo 
		TbContentCategory contentCategory=contentCategoryMapper.selectByPrimaryKey(id);
		
				//设置pojo属性
				
				contentCategory.setName(name);
				
				contentCategory.setUpdated(new Date());
				
				//插入到数据库
				contentCategoryMapper.updateByPrimaryKey(contentCategory);
				//返回结果
				
				return E3Result.ok(contentCategory);
	}
	@Override
	public E3Result deleteContentCategory(Long id) {
				
				// 创建一个tb_content_category 的pojo 
		TbContentCategory contentCategory=contentCategoryMapper.selectByPrimaryKey(id);
			if(!contentCategory.getIsParent()) {
				Long parentId=contentCategory.getParentId();
				contentCategoryMapper.deleteByPrimaryKey(id);
				int count=contentCategoryMapper.selectByParentKey(parentId);
				//判断节点的是否是叶子节点，如果不是true改为false
				TbContentCategory parent=contentCategoryMapper.selectByPrimaryKey(parentId);
				if(count==0) {
					parent.setIsParent(false);
					//更新到数据库
					contentCategoryMapper.updateByPrimaryKey(parent);
				}
				//返回结果
				
				return E3Result.ok();}
			else
			{
				return E3Result.build(404, "删除失败");
			
			}
		}

}
