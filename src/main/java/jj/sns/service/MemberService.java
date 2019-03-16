package jj.sns.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jj.sns.dao.MemberDao;
import jj.sns.dto.MemberDto;

@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;
	
	public MemberDto getMemeber(String id, String pw) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("pw", pw);
		return memberDao.getMember(map);
	}
	
	public void joinMember(MemberDto member) {
		memberDao.joinMember(member);
	}
	
	public boolean idDupCheck(String id) {
		
		String dupId = memberDao.idDupCheck(id);
		if(dupId == null) {
			return true;
		}
		return false;
	}
	
	public void keepLogin(MemberDto member) {
		memberDao.keepLogin(member);
	}
	
	public MemberDto getMemberBySessionId(String sessionId) {
		return memberDao.getMemberBySessionId(sessionId);
	}
	
}
