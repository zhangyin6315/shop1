package cn.e3mall.service;

import java.util.List;

import cn.e3mall.pojo.TbItemCat;
import cn.e3malll.common.pojo.EasyUITreeNode;

public interface ItemCatService {
List<EasyUITreeNode> getItemCatlist(Long parentId);

TbItemCat getTbItemCatById(Long itemcatId);
}