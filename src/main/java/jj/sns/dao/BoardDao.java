package jj.sns.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jj.sns.dto.BoardDto;
import jj.sns.dto.BoardPager;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public int write(BoardDto board) {
		return sqlSession.insert("board.write",board);
	}
	
	public int getBoardTotalCount() {
		return sqlSession.selectOne("board.getBoardTotalCount");
	}
	
	public List<BoardDto> getBoardList(BoardPager boardPager) {
		return sqlSession.selectList("board.getBoardList", boardPager);
	}
	
	public List<BoardDto> getUserBoardList(Map map) {
		return sqlSession.selectList("board.getUserBoardList", map);
	}
	
	public BoardDto getBoard(long seq) {
		return sqlSession.selectOne("board.getBoard",seq);
	}
	
	public int addViewCount(long seq) {
		return sqlSession.update("board.addViewCount",seq);
	}
	
	public int getUserBoardTotalCount(int uid) {
		return sqlSession.selectOne("board.getUserBoardTotalCount",uid);
	}
	
}
