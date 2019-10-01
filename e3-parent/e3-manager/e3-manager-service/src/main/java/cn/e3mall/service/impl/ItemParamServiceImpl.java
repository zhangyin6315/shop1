package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.EasyUIDataFGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.mapper.ParamItemMapper;
import cn.e3mall.mapper.TbItemParamItemMapper;
import cn.e3mall.pojo.TbItemParamItem;
import cn.e3mall.service.ItemParamService;
import cn.manager.model.ParamItem;
@Service
public class ItemParamServiceImpl implements ItemParamService {
	@Autowired
	private ParamItemMapper paramItemMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	@Override
	public EasyUIDataFGridResult getParamItemList(Integer page, Integer rows) {
					// 设置分页信息
					PageHelper.startPage(page,rows);
					//执行查询
					List<ParamItem> list=paramItemMapper.selectParamItemList();
					//取分页结果
					PageInfo<ParamItem> pageInfo=new PageInfo<>(list);
					//创建结果集
					EasyUIDataFGridResult result=new EasyUIDataFGridResult();
					//取总记录数
					long total=pageInfo.getTotal();
					result.setTotal(total);
					result.setRows(list);
					return result;
	}
	@Override
	public E3Result delItemParam(String ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.split(",").length;i++) {
			paramItemMapper.deleteById(Long.valueOf(ids.split(",")[i]));
		}
	
		return E3Result.ok();
	}
	@Override
	public E3Result insertItemParam(Long itemCatid, String paramData) {
		// TODO Auto-generated method stub
		TbItemParamItem tbItemParamItem=new TbItemParamItem();
		tbItemParamItem.setId(itemCatid);
		tbItemParamItem.setParamData(paramData);
		tbItemParamItem.setCreated(new Date());
		tbItemParamItem.setUpdated(new Date());
		
		tbItemParamItemMapper.insert(tbItemParamItem);
		return E3Result.ok();
	}
	

}
