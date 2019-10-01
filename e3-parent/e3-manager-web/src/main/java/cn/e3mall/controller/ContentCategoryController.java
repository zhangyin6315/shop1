package cn.e3mall.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;

/**
 * 内容分类管理
 * @author User
 *
 */
@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;
@RequestMapping("/content/category/list")
@ResponseBody
public List<EasyUITreeNode> getContentCategoryList(@RequestParam(name="id",defaultValue="0") Long parentId){
	List< EasyUITreeNode> list=contentCategoryService.getContentCatList(parentId);
	return list;
}
/**
 * 
 * 添加分类节点
 * @return
 */
@RequestMapping(value="/content/category/create", method=RequestMethod.POST)
@ResponseBody
public E3Result createContentCategory(Long parentId,String name) {
	//调用服务添加节点
		E3Result e3Result=contentCategoryService.addContentCategory(parentId, name);
		return e3Result;
	
}
@RequestMapping(value="/content/category/update", method=RequestMethod.POST)
@ResponseBody
public E3Result updateContentCategory(Long id,String name) {
	//调用服务修改节点
		E3Result e3Result=contentCategoryService.updateContentCategory(id, name);
		return e3Result;
	
}
@RequestMapping(value="/content/category/delete", method=RequestMethod.POST)
@ResponseBody
public E3Result deleteContentCategory(Long id) {
	//调用服务删除节点
		E3Result e3Result=contentCategoryService.deleteContentCategory(id);
		return e3Result;
	
}

}
