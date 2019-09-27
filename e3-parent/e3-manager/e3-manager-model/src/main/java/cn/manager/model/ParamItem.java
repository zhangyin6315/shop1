package cn.manager.model;

import cn.e3mall.pojo.TbItemParam;
import cn.e3mall.pojo.TbItemParamItem;
/*
 *         id'
           itemCatId',
           itemCatName'
           paramData
           created
		   updated
 */
public class ParamItem extends TbItemParamItem{
private String itemCatName;

public String getItemCatName() {
	return itemCatName;
}

public void setItemCatName(String itemCatName) {
	this.itemCatName = itemCatName;
} 


}
