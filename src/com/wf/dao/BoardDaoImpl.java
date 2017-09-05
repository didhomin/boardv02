package com.wf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wf.dto.BoardDto;
import com.wf.util.db.DBClose;
import com.wf.util.db.DBConnection;

public class BoardDaoImpl implements BoardDao {
	private static BoardDao boardDao;
	
	static {
		boardDao = new BoardDaoImpl();
	}
	
	private BoardDaoImpl() {}
	
	public static BoardDao getBoardDao() {
		return boardDao;
	}
	/**
	 * 작성된 글을 DB에 입력
	 * @param	BoardDto 입력받은 제목,내용 
     * */
	@Override
	public void write(BoardDto boardDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into \n");
			sql.append("board (seq,subject,content,insertdate,updatedate) \n");
			sql.append("values (seq_board.nextval,?,?,sysdate,sysdate)");

			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, boardDto.getSubject());
			pstmt.setString(2, boardDto.getContent());
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);

		}
				
	}
	/**
	 * DB에 저장된 게시글의 List를 조회
	 * @param	pg 현재페이지 
	 * end 현재페이지의 마지막 게시글 count
	 * start 현재페이지의 첫번째 게시글 count
     * */
	@Override
	public List<BoardDto> list(Map<String,String> map) {
		List<BoardDto> list =new ArrayList<BoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			conn=DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select b.* \n");
			sql.append("from ( \n");
			sql.append("	select rownum rn, a.* \n");
			sql.append("	from ( \n");
			sql.append("		select seq,subject,insertdate,updatedate \n");
			sql.append("		from board \n");
			sql.append("		order by seq desc \n");
			sql.append("		) a \n");
			sql.append("	where rownum <=? \n");
			sql.append("	) b \n");
			sql.append("where b.rn>? \n");
			pstmt=conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, map.get("end"));
			pstmt.setString(2, map.get("start"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setSeq(rs.getString("seq"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setInsertdate(rs.getString("insertdate"));
				boardDto.setUpdatedate(rs.getString("updatedate"));
				list.add(boardDto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return list;
	}
	/**
	 * 원하는 글번호의 게시글을 조회
	 * @param	seq 현재글번호 
     * */
	@Override
	public BoardDto view(String seq) {
		BoardDto boardDto =null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			conn=DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select seq,subject,content, \n");
			sql.append("	insertdate, \n");
			sql.append("	updatedate \n"); 
			sql.append("from board \n");
			sql.append("where seq = ?");
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, seq);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				boardDto = new BoardDto();
				boardDto.setSeq(rs.getString("seq"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setInsertdate(rs.getString("insertdate"));
				boardDto.setUpdatedate(rs.getString("updatedate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return boardDto;
	}
	/**
	 * 수정을 원하는 글번호의 게시글을 조회
	 * @param	seq 현재글번호 
     * */
	@Override
	public void modify(BoardDto boardDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update board \n");
			sql.append("set	subject=?,content=?,updatedate=sysdate \n");
			sql.append("where seq=?");

			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, boardDto.getSubject());
			pstmt.setString(2, boardDto.getContent());
			pstmt.setString(3, boardDto.getSeq());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
	}
	/**
	 * 삭제를 원하는 글번호의 게시글을 조회
	 * @param	seq 현재글번호 
     * */
	@Override
	public void delete(String seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("delete \n");
			sql.append("from board \n");
			sql.append("where seq=?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, seq);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);

		}
		
	}
	/**
	 * 페이징 처리를 위한 총 게시글 수 조회 
	 * @return int 게시글수
     * */
	@Override
	public int totalArticleCount() {
		int cnt = 0 ;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			conn=DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) cnt \n");
			sql.append("from board \n");
			pstmt=conn.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return cnt;
	}

}
