package cn.e3mall.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDataFGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.service.ItemParamService;
@Controller
public class ItemParamController {
	@Autowired 
	private ItemParamService itemParamService;
	/**
	 *查询规格参数
	 */
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EasyUIDataFGridResult getParamItemList(Integer page ,Integer rows) {
		EasyUIDataFGridResult result=itemParamService.getParamItemList(page,rows);
		return result;
		
	}

	@RequestMapping(value="/item/param/delete",method=RequestMethod.POST)
	@ResponseBody
	public E3Result delItemParam(@RequestParam("ids") String ids) {
		E3Result result=itemParamService.delItemParam(ids);
		return result;
		
	}
	@RequestMapping(value="/item/param/save/{ItemCatid}",method=RequestMethod.POST)
	@ResponseBody
	public E3Result addItemParam(@PathVariable Long ItemCatid,@RequestParam("paramData") String paramData) {
		E3Result result=itemParamService.insertItemParam(ItemCatid,paramData);
		return result;
		
	}
	
}
