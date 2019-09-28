package cn.e3mall.service;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbItemParamItem;
import cn.e3malll.common.pojo.EasyUIDataFGridResult;

public interface ItemParamService {

	EasyUIDataFGridResult getParamItemList(Integer page, Integer rows);

	E3Result delItemParam(String ids);

	E3Result insertItemParam(Long itemCatid, String paramData);

}
