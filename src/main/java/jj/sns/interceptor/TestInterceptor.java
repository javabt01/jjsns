package jj.sns.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jj.sns.service.MemberService;

public class TestInterceptor  extends HandlerInterceptorAdapter{

	@Autowired
	MemberService memberService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		Object obj = session.getAttribute("member");
		if(obj == null) {
			response.sendRedirect("/");
			return false;
		}
		
		
		
		return true;
	}
	
}
