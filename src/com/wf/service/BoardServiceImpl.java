package com.wf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wf.dao.BoardDaoImpl;
import com.wf.dto.BoardDto;
import com.wf.util.page.BoardConstance;
import com.wf.util.page.PageNavigation;

public class BoardServiceImpl implements BoardService{

	private static BoardService boardService;
	
	static{
		boardService = new BoardServiceImpl();
	}
	
	private BoardServiceImpl () {}
	
	public static BoardService getBoardService() {
		return boardService;
	}


	/**
	 * 작성된 글을 DB에 입력
	 * @param	BoardDto 입력받은 제목,내용 
     * */
	@Override
	public void write(BoardDto boardDto) {
		BoardDaoImpl.getBoardDao().write(boardDto);
		
	}
	/**
	 * DB에 저장된 게시글의 List를 조회
	 * @param	pg 현재페이지 
	 * end 현재페이지의 마지막 게시글 count
	 * start 현재페이지의 첫번째 게시글 count
     * */
	@Override
	public List<BoardDto> list(int pg) {
		int end = pg * BoardConstance.LIST_SIZE;
		int start = end - BoardConstance.LIST_SIZE;
		Map<String,String> map = new HashMap<String,String>();
		map.put("start", start+"");
		map.put("end", end+"");
		
		return BoardDaoImpl.getBoardDao().list(map);
	}
	/**
	 * 원하는 글번호의 게시글을 조회
	 * @param	seq 현재글번호 
     * */
	@Override
	public BoardDto view(String seq) {
		return BoardDaoImpl.getBoardDao().view(seq);
	}
	/**
	 * 수정을 원하는 글번호의 게시글을 조회
	 * @param	seq 현재글번호 
     * */
	@Override
	public void modify(BoardDto boardDto) {
		BoardDaoImpl.getBoardDao().modify(boardDto);
	}
	/**
	 * 삭제를 원하는 글번호의 게시글을 조회
	 * @param	seq 현재글번호 
     * */
	@Override
	public void delete(String seq) {
		BoardDaoImpl.getBoardDao().delete(seq);
	}
	/**
	 * 페이징처리
	 * @param	pg 현재페이지 
	 * totalArticleCount 총게시글수
	 * totalPageCount 총페이지수
	 * NowFirst 첫번째페이지 유무
	 * NowEnd 마지막페이지 유무
	 * PageNo 현재페이지
     * */
	@Override
	public PageNavigation makePageNavigation(int pg) {
		PageNavigation pageNavigation = new PageNavigation();
		int totalArticleCount =BoardDaoImpl.getBoardDao().totalArticleCount();
		int totalPageCount = (totalArticleCount-1 )/ BoardConstance.LIST_SIZE+1;
		pageNavigation.setTotalPageCount(totalPageCount);
		pageNavigation.setNowFirst(pg <=BoardConstance.PAGE_SIZE);
		pageNavigation.setNowEnd((totalPageCount-1)/BoardConstance.PAGE_SIZE == (pg-1)/BoardConstance.PAGE_SIZE);
		pageNavigation.setPageNo(pg);
		
		return pageNavigation;
	}
}
