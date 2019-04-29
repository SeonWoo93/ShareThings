package com.project.share.member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.project.share.member.model.vo.Member;
import static com.project.share.common.JDBCTemplate.*;

public class MemberDao {
	private Properties prop = new Properties();
	
	public MemberDao() {
		String fileName = MemberDao
							.class
							.getResource("/sql/member/member-query.properties")
							.getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//로그인용 메소드
	public Member loginCheck(Connection con, String userId, String password) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Member loginUser = null;
		
		String query = prop.getProperty("loginCheck");
		System.out.println(con);
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginUser = new Member();
				
				loginUser.setUserId(rset.getString("USER_ID"));
				loginUser.setPassword(rset.getString("USER_PWD"));
				loginUser.setUserName(rset.getString("USER_NAME"));
				loginUser.setGender(rset.getString("GENDER"));
				loginUser.setAge(rset.getInt("AGE"));
				loginUser.setEmail(rset.getString("EMAIL"));
				loginUser.setPhone(rset.getString("PHONE"));
				loginUser.setAddress(rset.getString("ADDRESS"));
				loginUser.setHobby(rset.getString("HOBBY"));
				loginUser.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return loginUser;
	}

	public int updateMember(Connection con, Member reqMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		System.out.println(reqMember.getGender());
		
		String query = prop.getProperty("updateMember");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, reqMember.getUserName());
			pstmt.setString(2, reqMember.getGender());
			pstmt.setInt(3, reqMember.getAge());
			pstmt.setString(4, reqMember.getEmail());
			pstmt.setString(5, reqMember.getPhone());
			pstmt.setString(6, reqMember.getAddress());
			pstmt.setString(7, reqMember.getHobby());
			pstmt.setString(8, reqMember.getUserId());
			pstmt.setString(9, reqMember.getPassword());
			System.out.println(reqMember);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteMember(Connection con, Member deleteMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteMember");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, deleteMember.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int joinMember(Connection con, Member joinMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("joinMember");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, joinMember.getUserId());
			pstmt.setString(2, joinMember.getPassword());
			pstmt.setString(3, joinMember.getUserName());
			pstmt.setString(4, joinMember.getGender());
			pstmt.setInt(5, joinMember.getAge());
			pstmt.setString(6, joinMember.getEmail());
			pstmt.setString(7, joinMember.getPhone());
			pstmt.setString(8, joinMember.getAddress());
			pstmt.setString(9, joinMember.getHobby());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//회원 정보 전체 조회용 메소드
	public ArrayList<Member> selectAll(Connection con) {
		ArrayList<Member> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectAll");
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Member>();
			
			while(rset.next()) {
				Member m = new Member();
				
				m.setUserId(rset.getString("USER_ID"));
				m.setPassword(rset.getString("USER_PWD"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		return list;
	}
	
	//아이디로 검색
	public ArrayList<Member> serarchId(Connection con, String userId) {
		ArrayList<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String quety = prop.getProperty("searchId");
		
		try {
			pstmt = con.prepareStatement(quety);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Member>();
			
			while(rset.next()) {
				Member m = new Member();
				
				m.setUserId(rset.getString("USER_ID"));
				m.setPassword(rset.getString("USER_PWD"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return list;
	}

	//이름으로 검색
	public ArrayList<Member> searchName(Connection con, String userName) {
		ArrayList<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String quety = prop.getProperty("searchName");
		
		try {
			pstmt = con.prepareStatement(quety);
			pstmt.setString(1, userName);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Member>();
			
			while(rset.next()) {
				Member m = new Member();
				
				m.setUserId(rset.getString("USER_ID"));
				m.setPassword(rset.getString("USER_PWD"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return list;
	}

	//성별로 검색
	public ArrayList<Member> searchGender(Connection con, String gender) {
		ArrayList<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String quety = prop.getProperty("searchGender");
		
		try {
			pstmt = con.prepareStatement(quety);
			pstmt.setString(1, gender);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Member>();
			
			while(rset.next()) {
				Member m = new Member();
				
				m.setUserId(rset.getString("USER_ID"));
				m.setPassword(rset.getString("USER_PWD"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return list;
	}

}
