package cn.e3mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.e3mall.pojo.TbItemCatExample.Criteria;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.service.ItemCatService;
import cn.e3malll.common.pojo.EasyUITreeNode;
@Service
public class ItemCatServiceImpl implements ItemCatService {
@Autowired
private TbItemCatMapper  tbItemCatMapper;
	
	@Override
	public List<EasyUITreeNode> getItemCatlist(Long parentId) {
		// TODO Auto-generated method stub
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list=tbItemCatMapper.selectByExample(example);
		List<EasyUITreeNode> resultlist=new ArrayList<>();
		for(TbItemCat tbItemCat:list) {
			EasyUITreeNode node=new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			resultlist.add(node);
		}
		
		return resultlist;
	}

	@Override
	public TbItemCat getTbItemCatById(Long itemcatId) {
		// TODO Auto-generated method stub
		TbItemCat tbItemCat=tbItemCatMapper.selectByPrimaryKey(itemcatId);
		return tbItemCat;
	}

}
