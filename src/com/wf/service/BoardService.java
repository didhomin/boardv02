package com.wf.service;

import java.util.List;

import com.wf.dto.BoardDto;
import com.wf.util.page.PageNavigation;

public interface BoardService {
	List<BoardDto> list(int pg);
	BoardDto view(String seq);
	void write(BoardDto boardDto);
	void modify(BoardDto boardDto);
	void delete(String seq);
	PageNavigation makePageNavigation(int pg);
}
