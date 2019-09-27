package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;
import cn.e3malll.common.pojo.EasyUIDataFGridResult;
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getTItemById(@PathVariable Long itemId) {
		TbItem tbItem=itemService.getItemById(itemId);
		return tbItem;
	}
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataFGridResult getItemList(Integer page ,Integer rows) {
		//调用服务层查询商品列表
	EasyUIDataFGridResult result=itemService.getItemList(page, rows);
	return result;
	}
/**
 * 商品添加
 * @param item
 * @param desc
 * @return
 */
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result addItem(TbItem item ,String desc) {
		E3Result result =itemService.addItem(item,desc);
	return result;
	}
}
