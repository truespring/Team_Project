package com.myhome.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myhome.dao.MemberDao;
import com.myhome.dto.MemberDto;

@WebServlet("/login/loginLogic.jsp")
public class Login extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("user_id");
		String password = request.getParameter("user_password");
		
		MemberDao dao = new MemberDao();
		MemberDto dto = dao.select(id, password);
		
		boolean rememberMe = Boolean.parseBoolean(request.getParameter("remember_me")); 
		if(rememberMe) {
			Cookie cookie = new Cookie("rememberId", id);
			cookie.setMaxAge(60 * 60 * 24 * 365); // 1��
			cookie.setPath("/");
			response.addCookie(cookie);
		} else { // üũ�� �ȵǾ��ִٸ�, ������ ���� �� �ִ� ��Ű�� ����
			Cookie cookie = new Cookie("rememberId", null); // rememberId �̸��� �ӽ���Ű �����
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie); // ���� remeberId ��Ű �����
		}
		if(dto != null){
			//request.setAttribute("nickname", dto.getNickname());
			HttpSession session = request.getSession();
			session.setAttribute("currentNickname", dto.getNickname());
			session.setAttribute("currentId", id);
		}
		
		//pageContext.forward("loginResultView.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("loginResultView.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
