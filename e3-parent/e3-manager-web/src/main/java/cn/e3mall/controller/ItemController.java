package cn.e3mall.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDataFGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.ItemService;
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
//	@RequestMapping("/item/{itemId}")
//	@ResponseBody
//	public TbItem getTItemById(@PathVariable Long itemId) {
//		TbItem tbItem=itemService.getItemById(itemId);
//		return tbItem;
//	}
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
	/**
	 * 商品删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/rest/item/delete",method=RequestMethod.POST)
	@ResponseBody
	public  E3Result delItem(@RequestParam("ids") String ids) {
	
		E3Result result=itemService.delItem(ids);
		return result;
	}
	/**
	 * 商品下架
	 */
	@RequestMapping(value="/rest/item/instock",method=RequestMethod.POST)
	@ResponseBody
	public  E3Result instockItem(@RequestParam("ids") String ids) {
		E3Result result=itemService.instockItem(ids);
		return result;
	}
	/**
	 * 商品上架
	 */
	@RequestMapping(value="/rest/item/reshelf",method=RequestMethod.POST)
	@ResponseBody
	public  E3Result reshelfItem(@RequestParam("ids") String ids) {
		E3Result result=itemService.reshelfItem(ids);
		return result;
	}
	/**
	 * 商品编辑
	 * @param item
	 * @param desc
	 * @return
	 */
		@RequestMapping(value="/rest/item/query/item/desc/{itemId}")
		@ResponseBody
		public TbItemDesc selectTbTbItemDesc(@PathVariable Long itemId) {
			
			TbItemDesc tbItemDesc=itemService.selectTbItemDesc(itemId);
		return tbItemDesc;
		}
	@RequestMapping(value="/rest/item/param/item/query/{itemId}")
	@ResponseBody
	public TbItem getTItemById(@PathVariable Long itemId) {
		
		TbItem tbItem=itemService.getItemById(itemId);
	return tbItem;
	}
	@RequestMapping(value="/rest/item/update",method=RequestMethod.POST)
	@ResponseBody
	public E3Result updateItem(TbItem item ,String desc) {
		E3Result result =itemService.updateItem(item,desc);
		return result;
	}

	
}
