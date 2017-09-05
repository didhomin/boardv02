package com.wf.dao;

import java.util.List;
import java.util.Map;

import com.wf.dto.BoardDto;

public interface BoardDao {
	List<BoardDto> list(Map<String,String> map);
	BoardDto view(String seq);
	void write(BoardDto boardDto);
	void modify(BoardDto boardDto);
	void delete(String seq);
	int totalArticleCount();
}
