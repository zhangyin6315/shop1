package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUIDataFGridResult;
import cn.e3mall.common.utils.E3Result;

public interface ItemParamService {

	EasyUIDataFGridResult getParamItemList(Integer page, Integer rows);

	E3Result delItemParam(String ids);

	E3Result insertItemParam(Long itemCatid, String paramData);

}
