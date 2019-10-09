package cn.e3mall.sso.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.mapper.TbUserMapper;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.pojo.TbUserExample;
import cn.e3mall.pojo.TbUserExample.Criteria;
import cn.e3mall.sso.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService {
@Autowired
private TbUserMapper tbUserMapper;
@Autowired
private JedisClient jedisClient;
@Value("${SESSION_EXPIRE}")
private Integer SESSION_EXPIRE;
	@Override
	public E3Result userLogin(String username, String password) {
		//测试用户名密码 
		TbUserExample example =new TbUserExample();
		Criteria criteria=example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = tbUserMapper.selectByExample(example);
		//判断用户名 密码是否正确
		if(list==null||list.size()==0) {
			//不对返回失败
			return E3Result.build(400, "用户名或者密码错误");
		}
		TbUser user=list.get(0);
		if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			//不对返回失败
			return E3Result.build(400, "用户名或者密码错误");
		}
		//正确生成token
		String token=UUID.randomUUID().toString();
		//把信息写进redis key token value 用户信息
		user.setPassword(null);
		jedisClient.set("SESSION"+token,JsonUtils.objectToJson(user));
		//设置Session过期时间
		jedisClient.expire("SESSION"+token,SESSION_EXPIRE);
		//返回token
		return E3Result.ok(token);
	}
	

}
