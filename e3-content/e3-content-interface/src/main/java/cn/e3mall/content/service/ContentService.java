package cn.e3mall.content.service;

import java.util.List;


import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbContent;

public interface ContentService {
E3Result addcontent(TbContent content);

List<TbContent> getContentList(Long categoryId);
E3Result editContent(TbContent content);

E3Result deleteContent(String ids);
}
