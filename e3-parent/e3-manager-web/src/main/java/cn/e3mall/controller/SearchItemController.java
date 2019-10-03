package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 导入商品到索引库
 * @author User
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.search.service.SearchItemService;
@Controller
public class SearchItemController {
	@Autowired
	private SearchItemService searchItemService;
@RequestMapping(value="/index/item/import" ,method=RequestMethod.POST)
@ResponseBody
public E3Result importItemList() {
	E3Result e3Result=searchItemService.importAllItems();
	return e3Result;
	
}
}
