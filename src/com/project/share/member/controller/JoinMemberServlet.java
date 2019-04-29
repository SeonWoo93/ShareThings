package com.project.share.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.share.member.model.service.MemberService;
import com.project.share.member.model.vo.Member;

/**
 * Servlet implementation class JoinMemberServlet
 */
@WebServlet("/joinMember")
public class JoinMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinMemberServlet() {
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
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String[] hobbies = request.getParameterValues("hobby");
		String hobby = String.join(",", hobbies);
		
		Member JoinMember = new Member();
		
		JoinMember.setUserId(userId);
		JoinMember.setPassword(password);
		JoinMember.setUserName(userName);
		JoinMember.setEmail(email);
		JoinMember.setPhone(phone);
		JoinMember.setAddress(address);
		JoinMember.setGender(gender);
		JoinMember.setAge(age);
		JoinMember.setHobby(hobby);
		
		int result = new MemberService().joinMember(JoinMember);
		
		String page = "";
		if(result > 0) {
			page = "index.jsp";
			
			response.sendRedirect(page);
		} else {
			page = "views/common/errorPage.jsp";
			
			request.setAttribute("msg", "회원가입 에러 발생");
			RequestDispatcher view =
					request.getRequestDispatcher(page);
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
