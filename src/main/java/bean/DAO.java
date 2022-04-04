package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import oracle.DisconnDB;
import oracle.OracleDB;

public class DAO {
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Connection conn = null;
	
	
	public int memberInput(DTO dto) {  // �����ͺ��̽��� ȸ������ ���(ȸ������)
		int result=0;
		try {
			conn = OracleDB.getConnection();
			pstmt=conn.prepareStatement("insert into member values(?,?,?,sysdate)");
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getPassword());
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {try {pstmt.close();}catch(SQLException s) {}}
			if(rs!=null) {try {rs.close();}catch(SQLException s) {}}
			if(conn!=null) {try {conn.close();}catch(SQLException s) {}}
		}
		return result;
	}
	
	
	public boolean loginCheck(DTO dto) { // DB������ �α��� üũ
		boolean result=false;
		try {
			conn = OracleDB.getConnection();
			pstmt=conn.prepareStatement("select * from member where email=? and password=?");
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getPassword());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {try {pstmt.close();}catch(SQLException s) {}}
			if(rs!=null) {try {rs.close();}catch(SQLException s) {}}
			if(conn!=null) {try {conn.close();}catch(SQLException s) {}}
		}
		return result;
	}
	
	
	public boolean emailCheck(DTO dto) {
		boolean result=false;
		try {
			conn = OracleDB.getConnection();
			pstmt=conn.prepareStatement("select * from member where email=?");
			pstmt.setString(1, dto.getEmail());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result=true;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DisconnDB.close(conn, pstmt, rs);
		}
		return result;
	}

	
	public boolean idCheck(DTO dto) {
		boolean result=false;
		try {
			conn = OracleDB.getConnection();
			pstmt=conn.prepareStatement("select * from member where id=?");
			pstmt.setString(1, dto.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DisconnDB.close(conn, pstmt, rs);
		}
		return result;
	}
}














