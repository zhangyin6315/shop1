package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.service.ItemCatService;
import cn.e3mall.service.ItemService;
import cn.e3malll.common.pojo.EasyUIDataFGridResult;
import cn.e3malll.common.pojo.EasyUITreeNode;
@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCatList(@RequestParam(name="id",defaultValue="0") Long parentId) {
		//调用服务层查询节点列表
	List<EasyUITreeNode> list=itemCatService.getItemCatlist(parentId);
	return list;
	}
	@RequestMapping("/item/param/query/itemcatid/{itemcatId}")
	@ResponseBody
	public TbItemCat getTbItemCatById(@PathVariable Long itemcatId) {
		TbItemCat tbItemCat =itemCatService.getTbItemCatById(itemcatId);
		return tbItemCat;
	}
	
}
