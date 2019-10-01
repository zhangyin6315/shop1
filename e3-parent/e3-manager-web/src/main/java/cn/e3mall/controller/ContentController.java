package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		E3Result erResult=contentService.addcontent(content);
		return erResult;
	}
	@RequestMapping(value="/content/query/list")
	@ResponseBody
	public List<TbContent> getContentList( Long categoryId){
		List<TbContent> list=contentService.getContentList(categoryId);
		return list;
	}
}
