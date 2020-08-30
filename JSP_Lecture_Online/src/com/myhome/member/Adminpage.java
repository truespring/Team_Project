package com.myhome.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myhome.dao.MemberDao;
import com.myhome.dto.MemberDto;

@WebServlet("/mypage/adminpage.jsp")
public class Adminpage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDao dao = new MemberDao();
		ArrayList<MemberDto> list = dao.getMemberList();
		request.setAttribute("memberList", list);
		request.getRequestDispatcher("adminpageResultView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
