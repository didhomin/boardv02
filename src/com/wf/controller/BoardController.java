package com.wf.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wf.dto.BoardDto;
import com.wf.service.BoardServiceImpl;
import com.wf.util.page.PageNavigation;

/**
 * Servlet implementation class BoardController
 * /list.do 목록
 * /view.do 상세보기
 * /write.do 글작성페이지
 * /writeSave.do 글작성
 * /modify.do 글수정페이지
 * /modifySave.do 글수정
 * /delete.do" 글삭제
 */
@WebServlet({ "/list.do", "/view.do", "/write.do", "/writeSave.do", "/modify.do", "/modifySave.do", "/delete.do" })
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * act path를 act에 담아서 act에따라 다른 동작적용
	 * flag true면 forward방식, false면 redirect방식으로 하기위한 boolean
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getServletPath();
		String path = "/index.jsp";
		boolean flag = true;
		/**
		 * write.jsp 페이지로 이동
		 */
		if(act.equals("/write.do")) {
			path = "/WEB-INF/page/write.jsp";
		
		/**
		 * 입력받은 subject,content를 boardDto에 담아 서비스호출 
		 * 목록으로 이동
		 * redirect이므로 flag=false
		 */
		} else if(act.equals("/writeSave.do")) {
			BoardDto boardDto = new BoardDto();
			boardDto.setSubject(request.getParameter("subject"));
			boardDto.setContent(request.getParameter("content"));
			BoardServiceImpl.getBoardService().write(boardDto);
			path = "/list.do";
			flag=false;
			
		/**
		 * page정보가 없을경우 1  
		 * 있을경우 parameter값을 받음
		 * page를 가지고 해당페이지에 게시글 조회
		 * 페이징처리(PageNavigation)
		 * list.jsp로 이동
		 */
		} else if(act.equals("/list.do")) {
			int pg =1;
			if(request.getParameter("pg")!=null) {
				pg= Integer.parseInt(request.getParameter("pg"));
			}
			List<BoardDto> boardList = BoardServiceImpl.getBoardService().list(pg);
			request.setAttribute("boardList", boardList);
			PageNavigation pageNavigation = BoardServiceImpl.getBoardService().makePageNavigation(pg);
			pageNavigation.setNavigator();
			request.setAttribute("page", pageNavigation.getNavigator());
			path="/WEB-INF/page/list.jsp";
			
		/**
		 * seq 글번호 
		 * 글번호에 해당하는 게시글 정보 조회
		 * view.jsp로 이동
		 */
		} else if(act.equals("/view.do")) {
			String seq = request.getParameter("seq");
			BoardDto boardDto = BoardServiceImpl.getBoardService().view(seq);
			request.setAttribute("article", boardDto);
			path="/WEB-INF/page/view.jsp";
		/**
		 * seq 글번호 
		 * 글번호에 해당하는 게시글 정보 조회
		 * modify.jsp로 이동
		 */
		} else if(act.equals("/modify.do")) {
			String seq = request.getParameter("seq");
			BoardDto boardDto = BoardServiceImpl.getBoardService().view(seq);
			request.setAttribute("article", boardDto);
			path="/WEB-INF/page/modify.jsp";
		/**
		 * seq와 입력받은 subject,content를 boardDto에 담아 서비스호출 
		 * 목록으로 이동
		 * redirect이므로 flag=false
		 */
		} else if(act.equals("/modifySave.do")) {
			BoardDto boardDto = new BoardDto();
			boardDto.setSeq(request.getParameter("seq"));
			boardDto.setSubject(request.getParameter("subject"));
			boardDto.setContent(request.getParameter("content"));
			BoardServiceImpl.getBoardService().modify(boardDto);
			path = "/list.do";
			flag=false;
		/**
		 * seq 글번호 
		 * 글번호에 해당하는 게시글 삭제
		 * 목록으로 이동
		 * redirect이므로 flag=false
		 */
		} else if(act.equals("/delete.do")) {
			String seq = request.getParameter("seq");
			BoardServiceImpl.getBoardService().delete(seq);
			path = "/list.do";
			flag=false;
		}
		/**
		 * flag true면 forward방식, false면 redirect방식
		 */
		if(flag) {
			RequestDispatcher disp= request.getRequestDispatcher(path);
			disp.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+path);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 한글처리
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
