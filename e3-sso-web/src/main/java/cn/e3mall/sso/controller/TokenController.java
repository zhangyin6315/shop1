package cn.e3mall.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.sso.service.TokenService;

@Controller
public class TokenController {
@Autowired
private TokenService tokenService;
@RequestMapping(value="/user/token/{token}",produces="application/json;charset=utf-8")
@ResponseBody
public String getuserByToken(@PathVariable String token,String callback) {
	E3Result result =tokenService.getUserByToken(token);
	//响应结果前判断是否为jsonp请求
	if(StringUtils.isNoneBlank(callback)) {
		return callback+"("+JsonUtils.objectToJson(result)+");";
	}
	return JsonUtils.objectToJson(result);
//	return result;
}

//Spring5.0 不支持
//@RequestMapping(value="/user/token/{token}")
//@ResponseBody
//public Object getuserByToken(@PathVariable String token,String callback) {
//	E3Result result =tokenService.getUserByToken(token);
//	//响应结果前判断是否为jsonp请求
//	if(StringUtils.isNoneBlank(callback)) {
//		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(result);
//		mappingJacksonValue.setJsonpFunction(callback);
//		return mappingJacksonValue;
//	}
//	return result;
//}
}
