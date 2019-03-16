package jj.sns.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import jj.sns.common.JJubsStatic;
import jj.sns.dto.MemberDto;
import jj.sns.service.MemberService;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	MemberService memberService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		Object obj = session.getAttribute(JJubsStatic.MEMBER);
		if(obj == null) {
			
			Cookie cookie = WebUtils.getCookie(request, "loginCookie");
			
			if(cookie != null) {
				
				String sessionId =  cookie.getValue();
				MemberDto member = memberService.getMemberBySessionId(sessionId);
				if(member != null) {
					session.setAttribute(JJubsStatic.MEMBER, member);
				}
				
			}
		}
		
		
		
		return true;
	}
	
}
