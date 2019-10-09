package cn.e3mall.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.mapper.TbUserMapper;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.pojo.TbUserExample;
import cn.e3mall.pojo.TbUserExample.Criteria;
import cn.e3mall.sso.service.RegisterService;
/**
 * 用戶注冊
 */
@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private TbUserMapper tbUserMapper;
/**
 *校验数据
 */
	@Override
	public E3Result checkData(String param, int type) {
		//根据不同type生成不同条件
		TbUserExample tbUserExample=new TbUserExample();
		Criteria criteria=tbUserExample.createCriteria();
		//1用户名2 手机号3邮箱
		if(type==1) {
			criteria.andUsernameEqualTo(param);
		}else if(type==2) {
			criteria.andPhoneEqualTo(param);
		}else if(type==3) {
			criteria.andEmailEqualTo(param);
		}else {
			E3Result.build(400, "数据类型错误");
			}	
		//执行查询
		List<TbUser> list=tbUserMapper.selectByExample(tbUserExample);
		if(list!=null&&list.size()>0) {
			return E3Result.ok(false);
		}
		return  E3Result.ok(true);
	}
@Override
public E3Result register(TbUser user) {
//	校验数据
	if(StringUtils.isBlank(user.getUsername())||StringUtils.isBlank(user.getPassword())||StringUtils.isBlank(user.getPhone())) {
		return E3Result.build(400, "用户数据不完整，注册失败");
	}
	if(!(boolean)checkData(user.getUsername(), 1).getData()) {
		return E3Result.build(400, "用户名已经存在，注册失败");
	}
	if(!(boolean)checkData(user.getPhone(), 2).getData()) {
		return E3Result.build(400, "手机号已经存在，注册失败");
	}

//	补全pojo
	user.setCreated(new Date());
	user.setUpdated(new Date());
//	密码加密
	user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
//	对用户数据插入数据库
	tbUserMapper.insert(user);
//	返回结果
	return E3Result.ok();
}
	

}
