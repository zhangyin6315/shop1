package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;
import cn.e3malll.common.pojo.EasyUIDataFGridResult;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Override
	public TbItem getItemById(Long itemId) {
		// TODO Auto-generated method stub
		TbItem tbItem=itemMapper.selectByPrimaryKey(itemId);
		
		return tbItem;
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
		//生成商品id
		Long itemId=IDUtils.genItemId();
		//补全item 属性
		item.setId(itemId);
		//1正常,2下架,3删除
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//向商品表插入
		itemMapper.insert(item);
		//创建一个商品描述表对应的pojo
		TbItemDesc itemDesc=new TbItemDesc();
		//补全属性
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		//向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
		//返回成功
		
		return E3Result.ok();
	}

}
