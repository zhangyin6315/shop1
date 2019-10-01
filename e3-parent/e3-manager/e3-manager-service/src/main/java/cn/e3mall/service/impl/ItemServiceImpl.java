package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.EasyUIDataFGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;


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
	@Override
	public E3Result delItem(String ids) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		TbItemDesc tbItemDesc=itemDescMapper.selectByPrimaryKey(itemId);
		
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
	

}
