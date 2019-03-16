package jj.sns.dto;

import jj.sns.common.JJubsStatic;

public class BoardPager {

	private int totalCount;
	private int totalPage;
	
	private int curPage;
	private int startNum;
	
	private int boardListCount;
	
	private int curBlock;
	private int blockBegin;
	private int blockEnd;
	
	private int totalBlock;
	private int prevPage;//이전 페이지
	private int nextPage;//다음 페이지
	
	public BoardPager(int curPage, int totalCount) {
		this.totalCount = totalCount;
		this.curPage = curPage;
		this.boardListCount = JJubsStatic.BOARD_LIST_COUNT;
		double fTotalPage = (double)totalCount / (double)boardListCount;
		this.totalPage = (int) Math.ceil(fTotalPage);
		this.startNum = (curPage-1) * boardListCount;
		
		double fTotalBlock = (double)totalPage / (double)JJubsStatic.BOARD_BLOCK_COUNT;
		totalBlock = (int) Math.ceil(fTotalBlock);
		curBlock = (curPage-1)/JJubsStatic.BOARD_BLOCK_COUNT+1;
		blockBegin = (curBlock-1) * JJubsStatic.BOARD_BLOCK_COUNT + 1;
		blockEnd = blockBegin + JJubsStatic.BOARD_BLOCK_COUNT - 1;
		if(blockEnd > totalPage) blockEnd = totalPage;
		
		prevPage = (curPage==1) ? 1 : (curBlock-1)*JJubsStatic.BOARD_BLOCK_COUNT;
		nextPage = curBlock > totalPage ? (curBlock * JJubsStatic.BOARD_BLOCK_COUNT) : (curBlock * JJubsStatic.BOARD_BLOCK_COUNT)+1;
		if(nextPage > totalPage) nextPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getBoardListCount() {
		return boardListCount;
	}

	public void setBoardListCount(int boardListCount) {
		this.boardListCount = boardListCount;
	}

	public int getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}

	public int getBlockBegin() {
		return blockBegin;
	}

	public void setBlockBegin(int blockBegin) {
		this.blockBegin = blockBegin;
	}

	public int getBlockEnd() {
		return blockEnd;
	}

	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
	
	
}
