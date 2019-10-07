package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUIDataFGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;

public interface ItemService {
TbItem getItemById(Long itemId);
EasyUIDataFGridResult getItemList(int page ,int rows);
E3Result addItem(TbItem item ,String desc);
E3Result delItem(String ids);
E3Result instockItem(String ids);
E3Result reshelfItem(String ids);
TbItemDesc selectTbItemDesc(Long itemId);
E3Result updateItem(TbItem item, String desc);
TbItemDesc getItemDescById(Long itemId);
void sendaddItemMessage(String string);
}
