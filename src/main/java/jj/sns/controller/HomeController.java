package jj.sns.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import jj.sns.common.JJubsStatic;
import jj.sns.dto.MemberDto;
import jj.sns.service.MemberService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {

		String returnUrl = "home";
		Object obj = session.getAttribute(JJubsStatic.MEMBER);
		
		if(obj != null) {
			returnUrl = "main";
		}
		
		return returnUrl;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes, 
			HttpSession session) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		logger.info("id:{}, pw :{}",id,pw);
		
		MemberDto memberDto =  memberService.getMemeber(id, pw);
		
		if(memberDto == null) {
			request.setAttribute("id", id);
			request.setAttribute("loginMsg", "아이디나 비밀번호가 틀렸습니다.");
			
			return "home";
		}
		
		String auto = request.getParameter("auto");
		if(auto != null && auto.equals("on")) {
			Cookie cookie = new Cookie("loginCookie", session.getId());
			cookie.setPath("/");//모든경로에서 접근가능
			
			int amount = 60*60*24*7*1000;
			cookie.setMaxAge(amount); // 단위는 (초)임으로 7일정도로 유효시간을 설정해 준다.
            // 쿠키를 적용해 준다.
            response.addCookie(cookie);
            memberDto.setSession_id(session.getId());
            memberDto.setSession_limit(new Date(System.currentTimeMillis() + amount));
            memberService.keepLogin(memberDto);
		}
		
		session.setAttribute(JJubsStatic.MEMBER, memberDto);

//		model.addAttribute("memberDto", memberDto);
		return "main";
		
	}
	
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public String joinGet(HttpServletRequest request, Model model) {
		return "joinForm";
	}
	
	
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String joinPost(HttpServletRequest request, Model model) {
		
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDto member = new MemberDto();
		member.setId(id);
		member.setPw(pw);
		
		memberService.joinMember(member);

		model.addAttribute("member", member);
		return "redirect:/";
		
	}
	
	@RequestMapping(value="/idDupCheck", method = RequestMethod.GET)
	public void idDupCheck(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		logger.info("idDupCheck");
		
		boolean isUse = memberService.idDupCheck(id);
		
		try {
			response.getWriter().print(isUse);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		Object obj = session.getAttribute(JJubsStatic.MEMBER);
		
		if(obj != null) {
			MemberDto member = (MemberDto)obj;
			session.removeAttribute(JJubsStatic.MEMBER);
			session.invalidate();
			
			Cookie cookie = WebUtils.getCookie(request, "loginCookie");
			if(cookie != null) {
				cookie.setPath("/");
				cookie.setMaxAge(0);
				
				response.addCookie(cookie);
				member.setSession_id(session.getId());
				member.setSession_limit(new Date(System.currentTimeMillis()));
				memberService.keepLogin(member);
			}
					
		}
		
		return "home";
		
	}
	
	@RequestMapping(value = "/template", method = RequestMethod.GET)
	public String template(Locale locale, Model model, HttpSession session) {
		model.addAttribute("center", "./center.jsp");
		return "template/template";
	}
	
	
}
