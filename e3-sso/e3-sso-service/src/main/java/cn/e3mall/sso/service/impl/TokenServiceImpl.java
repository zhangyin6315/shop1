package cn.e3mall.sso.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.TokenService;
@Service
public class TokenServiceImpl implements TokenService {
@Value("${SESSION_EXPIRE}")
private Integer SESSION_EXPIRE;
	@Autowired
 private JedisClient jedisClient;
	@Override
	public E3Result getUserByToken(String token) {
		//根据token到redis去用户信息
		String json=jedisClient.get("SESSION"+token);
		//取不到返回用户登录过期
		if(StringUtils.isBlank(json)) {
			return E3Result.build(201, "用户登录已过期");
		}
		//取到重新设置过期时间
		jedisClient.expire("SESSION"+token,SESSION_EXPIRE);
		TbUser user =JsonUtils.jsonToPojo(json, TbUser.class);
		return E3Result.ok(user);
	}
	

}
