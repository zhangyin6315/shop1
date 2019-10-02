package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;
/**
 * 内容管理
 * @author User
 *
 */
@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	@RequestMapping(value="/content/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result addContent(TbContent content){
		E3Result e3Result=contentService.addcontent(content);
		return e3Result;
	}
	@RequestMapping(value="/content/query/list")
	@ResponseBody
	public List<TbContent> getContentList( Long categoryId){
		List<TbContent> list=contentService.getContentList(categoryId);
		return list;
	}
//	"/rest/content/edit",$("#contentEditForm").serialize(), function(data)
	@RequestMapping(value="/rest/content/edit",method=RequestMethod.POST)
	@ResponseBody
	public E3Result editContent(TbContent content) {
		E3Result e3Result=contentService.editContent(content);
		return e3Result;
		
	}
//	/content/delete
	@RequestMapping(value="/content/delete",method=RequestMethod.POST)
	@ResponseBody
	public E3Result deleteContent(@RequestParam("ids") String ids) {
		E3Result e3Result=contentService.deleteContent(ids);
		return e3Result;
		
	}
}
