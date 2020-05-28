package qj.admin.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.hibernate.annotations.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import qj.admin.pojo.User;
import qj.admin.service.AdminUserManageService;
import qj.util.Page;

@Controller
@RequestMapping("/admin/usermanage")
public class AdminUserManageController {
	@Autowired
	AdminUserManageService adminUserManageService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@RequestMapping("/list")
	@ResponseBody
	public JSONArray list(/*Map<String, Object>map,Integer start*/)
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		List<User> users = null;
		/*Page page;
		if(start==null)
		{
			page = new Page(0, 10);
		}
		else
		{
			page = new Page(start, 10);
		}
		int total = adminUserManageService.getTotal();
		page.setTotal(total);*/
		System.out.println("用户list方法");
		System.out.println("list用户前");
		users = adminUserManageService.list();
		String jsonString = "[";
		//String jsonString = "";
		System.out.println("循环体前");
		for(int i = 0; i < users.size(); i++)
		{
			User user = users.get(i);
			String userIDNumber = user.getIDNumber();
			String userName = user.getUsername();
			String points = String.valueOf(user.getPoints());
			String state = String.valueOf(user.state);
			if(state.equals("0"))
			{
				state="被注销";
			}
			else if(state.equals("1")){
				state="正常";
			}
			else if(state.equals("2"))
			{
				state="封禁";
			}
			String tempString = "{\"userIDNumber\":\"" + userIDNumber + "\",\"userName\":\"" + userName + "\",\"points\":\"" + points
								+ "\",\"state\":\"" + state + "\"}";
			if(i == users.size()-1)
				jsonString = jsonString + tempString;
			else
				jsonString = jsonString + tempString + ",";
			System.out.print(i + "  ");
		}
		jsonString += "]";
		System.out.println(jsonString);
		System.out.println("以上就是jsonString");
		JSONArray jsonObject = JSONArray.parseArray(jsonString);
		/*map.put("users", users);
		map.put("page", page);*/
		return jsonObject;
	}
	@RequestMapping("/resetpassword")
	@ResponseBody
	public JSONArray resetPassword(int IDNumber)
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		System.out.println("重置用户密码");
		adminUserManageService.handleUser(IDNumber, 1);
		return list();
	}
	@RequestMapping("/deleteaccount")
	@ResponseBody
	public JSONArray deleteAccount(int IDNumber)
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		adminUserManageService.handleUser(IDNumber, 2);
		return list();
	}
	@RequestMapping("/stopaccount")
	@ResponseBody
	public JSONArray stopAccount(int IDNumber)
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		adminUserManageService.handleUser(IDNumber, 3);
		return list();
	}
	@RequestMapping("/wakeaccount")
	@ResponseBody
	public JSONArray wakeAccount(int IDNumber)
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		adminUserManageService.handleUser(IDNumber, 4);
		return list();
	}
	@RequestMapping("/changepoints")
	@ResponseBody
	public JSONArray changePoints(int IDNumber,int points)
	{
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		System.out.println("修改积分");
		adminUserManageService.changePoints(IDNumber, points);
		return list();
	}
}
