package com.project.share.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.share.member.model.service.MemberService;
import com.project.share.member.model.vo.Member;

/**
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet("/updateMember")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String userName = request.getParameter("userName");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String[] hobby = request.getParameterValues("hobby");
		String hobbies = String.join(",", hobby);
		
		//String hobbies = "";
		/*for(int i = 0; i < hobby.length; i++) {
			if(i == 0) {
				hobbies += hobby[i];
			} else {
				hobbies += ", " + hobby[i];
			}
		}*/
		
		Member reqMember = new Member();
		reqMember.setUserId(userId);
		reqMember.setPassword(password);
		reqMember.setUserName(userName);
		reqMember.setGender(gender);
		reqMember.setAge(age);
		reqMember.setEmail(email);
		reqMember.setPhone(phone);
		reqMember.setAddress(address);
		reqMember.setHobby(hobbies);
		
		int result = new MemberService().updateMember(reqMember);
		
		//처리 결과에 따른 뷰 결정
		String page = "";
		if(password.contentEquals(password2)) {
			if(result > 0) {
				page = "views/member/memberUpdateForm.jsp";
				request.getSession().setAttribute("loginUser", reqMember);
				//같은 키값으로 다른 객체를 넘길시 나중에 넘긴 객체가 최종 반영됨
				response.sendRedirect(page);
			} else {
				page = "views/common/errorPage.jsp";
				request.setAttribute("msg", "회원정보 수정에 실패하였습니다!!");
				request.getRequestDispatcher(page).forward(request, response);
			}
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "회원정보 수정에 실패하였습니다!!");
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	private String join(String string, String[] hobbies) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
