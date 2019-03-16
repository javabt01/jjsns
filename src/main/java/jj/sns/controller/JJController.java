package jj.sns.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jj.sns.common.JJubsStatic;
import jj.sns.dto.BoardDto;
import jj.sns.dto.BoardPager;
import jj.sns.dto.MemberDto;
import jj.sns.service.BoardService;

@Controller
@RequestMapping("/jj")
public class JJController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	BoardService boardService;

	
	@RequestMapping(value="/boardList", method = RequestMethod.GET)
	public void boardList(
			@RequestParam(defaultValue="1") int curPage,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model) {
		
		int totalCount = boardService.getBoardTotalCount();
		
		BoardPager boardPager = new BoardPager(curPage, totalCount);
		
		List<BoardDto> boardList = boardService.getBoardList(boardPager);
		
//		model.addAttribute("boardList", boardList);
//		model.addAttribute("boardPager", boardPager);
//		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("boardList", boardList);
		map.put("boardPager", boardPager);
		
		ObjectMapper mapper = new ObjectMapper();
		String sValue = null;
		try {
			sValue = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		try {
			response.getWriter().print(sValue);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/writeFrom", method = RequestMethod.GET)
	public String writeFrom(HttpServletRequest request, Model model) {

		return "writeFrom";
	}
	
	@RequestMapping(value="/write",method = RequestMethod.POST)
	public String write(HttpServletRequest request, HttpSession session,
			@RequestParam("selectfile")MultipartFile multiFile,  Model model) {
		
		String returnUrl = "template/template";
		
		MemberDto member = (MemberDto) session.getAttribute(JJubsStatic.MEMBER);
		String content = request.getParameter("content");
		BoardDto board = new BoardDto(member.getUid(),content);
		
		
		try {
			boardService.write(member.getUid(), multiFile, board);
		} catch (IOException e) {
			return returnUrl;
		}
		
		
		
		return returnUrl;
	}
	

	
	@RequestMapping(value="/readBoard",method = RequestMethod.GET)
	public String readBoard(
			@RequestParam int seq, 
			@RequestParam(defaultValue="1") int curPage, 
			HttpServletRequest request, 
			HttpSession session, Model model) {
		
		String returnUrl = "readBoard";
		BoardDto boardDto = boardService.getBoard(seq);
		
		if(boardDto == null) {
			returnUrl = "template2";
		}
		model.addAttribute("boardDto", boardDto);
		model.addAttribute("curPage", curPage);
		
		
		
		return returnUrl;
	}
	
	
	@RequestMapping(value="/myPage")
	public String myPage(HttpSession session, Model model) {
		
		MemberDto member = (MemberDto) session.getAttribute(JJubsStatic.MEMBER);
		model.addAttribute("center", "./myPage.jsp");
		model.addAttribute("member", member);
		
		return "template/template";
		
	}
	
	@RequestMapping(value="/userBoardList")
	public void userBoardList(
			HttpServletResponse response,
			HttpSession session,
			@RequestParam(defaultValue="1") int curPage) {
		
		MemberDto member = (MemberDto) session.getAttribute(JJubsStatic.MEMBER);
		Map<String,Object> map = boardService.getUserBoardList(curPage,member.getUid());
		
		 
		
		

		ObjectMapper mapper = new ObjectMapper();
		String sValue = null;
		try {
			sValue = mapper.writeValueAsString(map);
			response.getWriter().print(sValue);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
