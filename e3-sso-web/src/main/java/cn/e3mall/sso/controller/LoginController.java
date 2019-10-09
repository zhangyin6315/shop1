package cn.e3mall.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.sso.service.LoginService;

@Controller
public class LoginController {
	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	@Autowired 
	private LoginService loginService;
	@RequestMapping(value="page/login")
	public String showLogin() {
		return "login";
	}
	
	
	@RequestMapping(value="/user/login")
	@ResponseBody
	public E3Result login(String username,String password,HttpServletRequest request,HttpServletResponse response) {
//		System.out.print(username+password);
		E3Result e3Result=loginService.userLogin(username, password);
		//判断登录情况
		if(e3Result.getStatus()==200) {
			String token=e3Result.getData().toString();
			//写入cook
			CookieUtils.setCookie(request, response, TOKEN_KEY, token);
			
		}
		return e3Result;
		
	}
	
}
