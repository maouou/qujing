package qj.admin.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import qj.admin.pojo.AdminLog;
import qj.admin.service.LogtableService;
import qj.admin.util.SysContent;
import qj.admin.aspect.LogAnno;

@Component
@Aspect
public class LogAopAspect {
	@Autowired
	LogtableService logtableService;
    @Autowired(required=false)
	HttpServletRequest request;
	@Around("@annotation(qj.admin.aspect.LogAnno)")
	public Object around(ProceedingJoinPoint pjp) throws Throwable
	{
		MethodSignature methodSignature =(MethodSignature)pjp.getSignature();
		Method method = methodSignature.getMethod();
		String targetName = pjp.getTarget().getClass().getName(); 
		System.out.println("增强的类是:"+targetName);
		System.out.println("增强的方法是:"+method.getName());
		Method realMethod = pjp.getTarget().getClass().getDeclaredMethod(methodSignature.getName(), method.getParameterTypes());
		LogAnno logAnno = realMethod.getAnnotation(LogAnno.class);
		String operatortype = logAnno.operatorType();
		//String operatortype = "修改用户积分";
		AdminLog adminLog = new AdminLog();
		adminLog.setOperatortype(operatortype);
		adminLog.setOperator("221701211");
		Object resultObject = null;
		Object [] parameter = pjp.getArgs();
		try {
			resultObject = pjp.proceed();
			adminLog.setOperatorresult("正常 "+Arrays.toString(parameter));
		} catch (Exception e) {
			// TODO: handle exception
			adminLog.setOperatorresult("失败");
		}
		finally {
			adminLog.setOperatordate(new Date());
			logtableService.addLog(adminLog);
		}
		return resultObject;
	}
}
