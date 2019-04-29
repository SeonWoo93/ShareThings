package com.project.share.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.project.share.member.model.dao.MemberDao;
import com.project.share.member.model.vo.Member;
import static com.project.share.common.JDBCTemplate.*;

public class MemberService {

	public Member loginCheck(String userId, String password) {
		Connection con = getConnection();
		Member loginUser = new MemberDao().loginCheck(con, userId, password);
		
		close(con);
		return loginUser;
	}

	public int updateMember(Member reqMember) {
		Connection con = getConnection();
		
		int result = new MemberDao().updateMember(con, reqMember);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public int deleteMember(Member deleteMember) {
		Connection con = getConnection();
		
		int result = new MemberDao().deleteMember(con, deleteMember);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public int joinMember(Member joinMember) {
		Connection con = getConnection();
		
		int result = new MemberDao().joinMember(con, joinMember);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		return result;
	}

	//회원 정보 전체 조회용 메소드
	public ArrayList<Member> selectAll() {
		Connection con = getConnection();
		ArrayList<Member> list = new MemberDao().selectAll(con);
		
		close(con);
		return list;
	}

	//아이디로 검색
	public ArrayList<Member> searchId(String userId) {
		Connection con = getConnection();
		ArrayList<Member> list = null;
		
		list = new MemberDao().serarchId(con, userId);
		
		close(con);
		return list;
	}

	//이름으로 검색
	public ArrayList<Member> searchName(String userName) {
		Connection con = getConnection();
		ArrayList<Member> list = null;
		
		list = new MemberDao().searchName(con, userName);
		
		close(con);
		return list;
	}

	//성별로 검색
	public ArrayList<Member> searchGender(String gender) {
		Connection con = getConnection();
		ArrayList<Member> list = null;
		
		list = new MemberDao().searchGender(con, gender);
		
		close(con);
		return list;
	}

}
