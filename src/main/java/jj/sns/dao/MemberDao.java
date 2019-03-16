package jj.sns.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jj.sns.dto.MemberDto;

@Repository
public class MemberDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public MemberDto getMember(Map map) {
		return sqlSession.selectOne("member.getMember",map);
	}
	
	public void joinMember(MemberDto member) {
		sqlSession.insert("member.joinMember",member);
	}
	
	public String idDupCheck(String id) {
		return sqlSession.selectOne("member.idDupCheck", id);
	}
	
	public int keepLogin(MemberDto member) {
		return sqlSession.update("member.keepLogin", member);
	}
	
	public MemberDto getMemberBySessionId(String sessionId) {
		return sqlSession.selectOne("member.getMemberBySessionId", sessionId);
	}
	
}
