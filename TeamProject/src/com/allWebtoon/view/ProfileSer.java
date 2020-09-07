package com.allWebtoon.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allWebtoon.dao.UserDAO;
import com.allWebtoon.util.MyUtils;
import com.allWebtoon.util.ViewResolver;
import com.allWebtoon.vo.UserVO;

@WebServlet("/profile")
public class ProfileSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		request.setAttribute("profileData", UserDAO.selUser(loginUser.getU_no()));
		ViewResolver.accessForward("profile", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
