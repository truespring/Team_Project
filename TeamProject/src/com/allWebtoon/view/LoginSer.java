package com.allWebtoon.view;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.allWebtoon.api.KakaoAPI;
import com.allWebtoon.dao.UserDAO;
import com.allWebtoon.util.Const;
import com.allWebtoon.util.MyUtils;
import com.allWebtoon.util.ViewResolver;
import com.allWebtoon.vo.UserVO;

@WebServlet("/login")
public class LoginSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//플랫폼 넘버 구분
		String platNo = request.getParameter("platNo");
		System.out.println(platNo);
		//네이버 : 난수 발생용
		if(platNo == null) {
			System.out.println("네이버");
			SecureRandom random = new SecureRandom();
			String state = new BigInteger(130, random).toString();
			request.setAttribute("state", state);
		}
		//카카오 로그인 로직
		if(platNo != null && platNo.equals("1")) {
			String access_token = KakaoAPI.getAccessToken(request.getParameter("code"));
			UserVO userInfo = KakaoAPI.getUserInfo(access_token);
			int result = UserDAO.selKakaoUser(userInfo);
			if(result == 0) {
				UserDAO.insUser(userInfo);
				response.sendRedirect("/choGenre?user_id="+userInfo.getUser_id());
				return;
			}
			
			System.out.println("result : "+result);
			//에러처리
			if(result == 2) {		
				String msg = "비밀번호가 틀렸습니다.";
				request.setAttribute("msg",msg);
			}
			request.setAttribute("user_id", userInfo.getName());
			HttpSession hs = request.getSession();
			hs.setAttribute(Const.LOGIN_USER,userInfo);
			
			System.out.println("로그인성공");
			response.sendRedirect("/");
			return;
		}
		ViewResolver.accessForward("login", request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String encrypt_pw = MyUtils.encryptString(user_pw);
		
		UserVO param = new UserVO();
		
		
		param.setUser_id(user_id);
		param.setUser_password(encrypt_pw);
		
		int result = UserDAO.login(param);


		if(result != 1) {		//에러처리
			String msg = null;
			switch(result) {
				case 0:
				msg = "에러가 발생했습니다";
				break;
				case 2:
				msg = "비밀번호가 틀렸습니다.";
				break;
				case 3:
				msg = "아이디가 없음";
				break;
			}
			request.setAttribute("msg",msg);
			request.setAttribute("user_id", user_id);
			doGet(request,response);
			return;
		}
		
		HttpSession hs = request.getSession();
		hs.setAttribute(Const.LOGIN_USER,param);
		
		System.out.println("로그인성공");
		response.sendRedirect("/");
		
	}

}