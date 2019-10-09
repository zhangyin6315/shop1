package cn.e3mall.sso.service;

import cn.e3mall.common.utils.E3Result;

public interface LoginService {
//测试用户名密码 
	//判断用户名 密码是否正确
	//不对返回失败
	//正确生成token
	//把信息写进redis key token value 用户信息
	//设置Session过期时间
	//返回token
	//E3Result 包含token
	E3Result userLogin(String username, String password);
	
}
