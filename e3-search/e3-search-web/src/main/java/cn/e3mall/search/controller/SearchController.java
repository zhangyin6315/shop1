package cn.e3mall.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.service.SearchService;

/**
 * 商品搜索
 * @author User
 *
 */
@Controller
public class SearchController {
	@Autowired 
	private SearchService searchService;
	@Value("${SEARCH_RESULT_ROWS}")
	private Integer SEARCH_RESULT_ROWS;
	@RequestMapping("/search")
	public String searchItemList(String keyword,@RequestParam(defaultValue="1") Integer page,Model model) throws Exception {
		
		keyword=new String(keyword.getBytes("iso-8859-1"),"utf-8");
		//查询商品列表
	SearchResult searchResult= 	searchService.search(keyword, page, SEARCH_RESULT_ROWS);
		model.addAttribute("query",keyword );
		model.addAttribute("totalPages",searchResult.getTotalPages() );
		model.addAttribute("page",page );
		model.addAttribute("recourdCount",searchResult.getRecourdCount() );
		model.addAttribute("itemList",searchResult.getItemList());
//		int i=1/0;//测试全局异常处理器
		return "search";
		
		
		
	}

}
