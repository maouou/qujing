package qj.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import qj.admin.pojo.Admin;
import qj.admin.service.AdminService;
import qj.admin.util.JwtUtil;

@Controller
@RequestMapping("/adminlogin")
@CrossOrigin(origins="*",maxAge=3600)
public class AdminLoginController {
	@Autowired
	HttpServletResponse response;
	@Autowired(required=false)
	HttpServletRequest request;
	@Autowired
	AdminService adminService;
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(int username,int password)
	{
		response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
		System.out.println("进来验证了");
		String usernameString = String.valueOf(username);
		String passwordString = String.valueOf(password);
		if(!adminService.isExited(usernameString))
		{
			return "404 该管理员用户不存在";
		}
		if(adminService.isMatched(usernameString, passwordString))
		{
			String jwtToken = JwtUtil.creatJwt(JwtUtil.JWT_ID, usernameString, JwtUtil.JWT_EXPIRE);
			System.out.println("签发token");
			System.out.println(jwtToken);
			response.setHeader(JwtUtil.AUTH_HEADER, jwtToken);
			System.out.println("成功签发token并返回");
			HttpSession session = request.getSession();
			session.setAttribute("username", usernameString);
			session.setAttribute("password", passwordString);
			System.out.println("session的用户名：" + session.getAttribute("username"));
			response.setStatus(200);
			return "success";
		}
		else
			return "403 账号密码不匹配";
	}
}
