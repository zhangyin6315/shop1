package cn.e3mall.mapper;

import java.util.List;

import cn.manager.model.ParamItem;

//遵循四个原则
//返回值一致
//接口方法名一致 id
//方法传入参数一致
//命名空间绑定接口
public interface ParamItemMapper {

	List<ParamItem> selectParamItemList();

}
