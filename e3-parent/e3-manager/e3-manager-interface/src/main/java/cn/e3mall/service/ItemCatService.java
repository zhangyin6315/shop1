package cn.e3mall.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.pojo.TbItemCat;

public interface ItemCatService {
List<EasyUITreeNode> getItemCatlist(Long parentId);

TbItemCat getTbItemCatById(Long itemcatId);
}
