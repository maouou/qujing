package qj.admin.filter;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.websocket.AuthenticationException;
import org.hibernate.id.enhanced.TableStructure;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.web.servlet.HandlerInterceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import qj.admin.pojo.Admin;
import qj.admin.util.JwtUtil;

public class AuthenticationInterceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
		System.out.println("拦截器开始执行");
		String token = httpServletRequest.getHeader("Authorization");
		System.out.println(token);
		if (token!=null) {
            Claims claims =JwtUtil.parseJwt(token);
            if(JwtUtil.isTokenExpired(claims.getExpiration()))
            {
            	throw new AuthenticationException("登入过期，请重新登入");
            }
            return true;
		}
		else {
			System.out.println("token为空");
			throw new AuthenticationException("请登入");
		}
	}
}
