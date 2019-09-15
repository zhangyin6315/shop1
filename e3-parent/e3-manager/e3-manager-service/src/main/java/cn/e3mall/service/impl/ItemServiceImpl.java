package cn.e3mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;
import cn.e3malll.common.pojo.EasyUIDataFGridResult;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
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

}
