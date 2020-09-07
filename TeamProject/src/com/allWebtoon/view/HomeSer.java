package com.allWebtoon.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allWebtoon.dao.WebtoonListDAO;
import com.allWebtoon.util.MyUtils;
import com.allWebtoon.util.ViewResolver;
import com.allWebtoon.vo.UserVO;
import com.allWebtoon.vo.WebtoonVO;
@WebServlet("/home")
public class HomeSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//홈에 이미지 출력할 배열 생성
		//세션 정보 담긴 변수
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		ArrayList<WebtoonVO> list = new ArrayList<WebtoonVO>();
		System.out.println("로그인 유저 없음");
		//네이버 정보 출력
		list = WebtoonListDAO.selRandomWebtoonList(list, 1, 15);
		//카카오 정보 출력
		list = WebtoonListDAO.selRandomWebtoonList(list, 3, 15);
		//레진 정보 출력
		list = WebtoonListDAO.selRandomWebtoonList(list, 4, 15);
		//네이버 정보 출력
		request.setAttribute("list", list);
		ViewResolver.viewForward("home", request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
