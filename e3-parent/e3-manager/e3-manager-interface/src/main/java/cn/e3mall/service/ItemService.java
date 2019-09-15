package cn.e3mall.service;

import cn.e3mall.pojo.TbItem;
import cn.e3malll.common.pojo.EasyUIDataFGridResult;

public interface ItemService {
TbItem getItemById(Long itemId);
EasyUIDataFGridResult getItemList(int page ,int rows);
}
