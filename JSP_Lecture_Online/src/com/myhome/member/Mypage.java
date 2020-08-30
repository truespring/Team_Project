package com.myhome.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myhome.dao.MemberDao;
import com.myhome.dto.MemberDto;

@WebServlet("/mypage/mypageLogic.jsp")
public class Mypage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDao dao = new MemberDao();
		MemberDto dto = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("currentId"); //���� ���� ���� ID
		String password = request.getParameter("user_password"); //��� �Է��� ��й�ȣ
		dto = dao.select(id, password);
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("mypageResultView.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
