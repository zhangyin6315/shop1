package cn.e3mall.service;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3malll.common.pojo.EasyUIDataFGridResult;

public interface ItemService {
TbItem getItemById(Long itemId);
EasyUIDataFGridResult getItemList(int page ,int rows);
E3Result addItem(TbItem item ,String desc);
}
