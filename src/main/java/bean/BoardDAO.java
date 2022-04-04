package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.DisconnDB;
import oracle.OracleDB;

/*	<멤버 함수 목록>
 * BoardInput     - 게시판 글쓰기
 * getCount       - 총 게시글 수
 * getAllList     - 총 게시글 리스트
 * getMyAllList   - 나의 게시글 리스트
 * getMyCount     - 나의 게시글 수
 * readCountUp    - 조회수
 * getContnet     - 게시글 내용 보기
 * getSearchCount - 검색 결과 수
 * getSearchList  - 게시물 검색 결과
 * editBoard      - 글 수정
 * deleteBoard    - 글 삭제
 * getPrev  	  - 이전글 버튼
 * getNext 		  - 다음글 버튼
 */

public class BoardDAO {
	private PreparedStatement pstmt=null;
	private Connection conn=null;
	private ResultSet rs=null;
	
	public int BoardInput(BoardDTO dto) {
		int result=0;
		try 
		{
			conn=OracleDB.getConnection();
			pstmt=conn.prepareStatement("insert into board values(board_seq.nextval,?,?,?,?,?,sysdate,0,0)");
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getFilename());
			pstmt.setString(5, dto.getCategory());
			result=pstmt.executeUpdate();
		} catch (Exception e) 
		{
			e.printStackTrace();
		} finally 
		{
			DisconnDB.close(conn, pstmt, rs);
		}
		return result;
	}
	
	public int getCount() {
		int result=0;
		try {
			conn=OracleDB.getConnection();
			pstmt=conn.prepareStatement("select count(*) from board");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DisconnDB.close(conn, pstmt, rs);
		}
		return result;
	}
	
	public List<BoardDTO> getAllList(int start, int end) {
		List<BoardDTO> list = null;
		try {
			conn=OracleDB.getConnection();
			pstmt=conn.prepareStatement("select * from "
					+ " (select num, writer, subject, content, filename, category, good, reg, readcount, rownum r from "
					+ " (select * from board order by num desc)) "
					+ " where r >=? and r <=?");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs=pstmt.executeQuery();
			list = new ArrayList();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setFilename(rs.getString("filename"));
				dto.setCategory(rs.getString("category"));
				dto.setGood(rs.getInt("good"));
				dto.setReg(rs.getTimestamp("reg"));
				dto.setReadcount(rs.getInt("readcount"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DisconnDB.close(conn, pstmt, rs);
		}
		return list;
	}
	public List<BoardDTO> getMyAllList(String writer, int start, int end) {
		List<BoardDTO> list = null;
		try {
			conn=OracleDB.getConnection();
			pstmt=conn.prepareStatement("select * from "
					+ " (select num, writer, subject, content, filename, category, good, reg, readcount, rownum r from "
					+ " (select * from board where writer=? order by num desc)) "
					+ " where r >=? and r <=?");
			pstmt.setString(1, writer);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs=pstmt.executeQuery();
			list = new ArrayList();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setFilename(rs.getString("filename"));
				dto.setCategory(rs.getString("category"));
				dto.setGood(rs.getInt("good"));
				dto.setReg(rs.getTimestamp("reg"));
				dto.setReadcount(rs.getInt("readcount"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DisconnDB.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public int getMyCount(String id) {
		int result = 0;
		try {
			conn=OracleDB.getConnection();
			pstmt=conn.prepareStatement("select count(*) from board where writer=?");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DisconnDB.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	public void readCountUp(BoardDTO dto) {
		try {
			conn=OracleDB.getConnection();
			pstmt=conn.prepareStatement("update board set readcount=readcount+1 where num=?");
			pstmt.setInt(1, dto.getNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DisconnDB.close(conn, pstmt, rs);
		}
	}
	
	public BoardDTO getContent(BoardDTO dto) {
		try {
			conn=OracleDB.getConnection();
			pstmt=conn.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, dto.getNum());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setCategory(rs.getString("category"));
				dto.setFilename(rs.getString("filename"));
				dto.setReg(rs.getTimestamp("reg"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setGood(rs.getInt("good"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DisconnDB.close(conn, pstmt, rs);
		}
		return dto;
	}
	
	public int getSearchCount(String colum, String search) {
		int result=0;
		try {
			conn=OracleDB.getConnection();
			pstmt=conn.prepareStatement("select count(*) from board where "+colum+" like '%"+search+"%'");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DisconnDB.close(conn, pstmt, rs);
		}
		return result;
	}
	
	public List<BoardDTO> getSearchList(String colum, String search, int start, int end){
		List<BoardDTO> list = null;
		try {
			conn=OracleDB.getConnection();
			String sql = "select * from " 
					+ " (select num, writer, subject, content,category, filename, reg, readcount, good, rownum r from "
					+ " (select * from board where "+colum+" like '%"+search+"%' order by num desc)) "
					+ " where r>=? and r<=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			list=new ArrayList();
			while(rs.next()){
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setCategory(rs.getString("category"));
				dto.setFilename(rs.getString("filename"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setGood(rs.getInt("good"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DisconnDB.close(conn, pstmt, rs);
		}
		return list;
	}	
	
	public int editBoard(BoardDTO dto) {
		int result = 0;
		try {
			conn=OracleDB.getConnection();
			String sql = "update Board set subject=?, content=?, filename=?, category=? where num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getFilename());
			pstmt.setString(4, dto.getCategory());
			pstmt.setInt(5, dto.getNum());
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DisconnDB.close(conn, pstmt, rs);
		}
		return result;
	}
	
	public String deleteBoard(int num) {
		String result = null;
		try {
			conn=OracleDB.getConnection();
			pstmt=conn.prepareStatement("select filename from board where num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getString("filename");
			}
			pstmt=conn.prepareStatement("delete form board where num=?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DisconnDB.close(conn, pstmt, rs);
		}
		return result;
	}
	
	public BoardDTO getPrev(BoardDTO dto) {
		try {
			conn=OracleDB.getConnection();
			pstmt=conn.prepareStatement("select num_prev from (select num, writer, subject, content, lag(num,1) over(order by num) as num_prev from board) where num=?");
			pstmt.setInt(1, dto.getNum());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				dto.setNum_prev(rs.getInt("num_prev"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DisconnDB.close(conn, pstmt, rs);
		}return dto;
	}	
	
	public BoardDTO getNext(BoardDTO dto) {
		try {
			conn=OracleDB.getConnection();
			pstmt=conn.prepareStatement("select num_next from (select num, writer, subject, content, lead(num,1) over(order by num) as num_next from board) where num=?");
			pstmt.setInt(1, dto.getNum());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				dto.setNum_next(rs.getInt("num_next"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DisconnDB.close(conn, pstmt, rs);
		}return dto;
	}	
}

















