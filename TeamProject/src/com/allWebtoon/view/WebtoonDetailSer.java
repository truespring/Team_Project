package com.allWebtoon.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allWebtoon.dao.WebtoonCmtDAO;
import com.allWebtoon.dao.WebtoonListDAO;
import com.allWebtoon.util.MyUtils;
import com.allWebtoon.util.ViewResolver;
import com.allWebtoon.vo.UserVO;
import com.allWebtoon.vo.WebtoonCmtVO;
import com.allWebtoon.vo.WebtoonVO;

@WebServlet("/webtoon/detail")
public class WebtoonDetailSer extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // 웹툰 정보 뿌리기 - 시작
      int w_no = MyUtils.getIntParameter(request, "w_no");
      
      WebtoonVO data = WebtoonListDAO.webtoonDetail(w_no);
      data.setW_no(w_no);
      request.setAttribute("data", data);
      // 웹툰 정보 뿌리기 - 끝
      
      // 내 댓글 뿌리기 - 시작
      UserVO loginUser = MyUtils.getLoginUser(request);
      
      if(loginUser != null) {
         WebtoonCmtVO myCmt = new WebtoonCmtVO();
         myCmt.setW_no(w_no);
         myCmt.setU_no(loginUser.getU_no());
         WebtoonCmtVO param = WebtoonCmtDAO.selCmt(myCmt);
         System.out.println("내 댓글 : " + param.getC_com());
         System.out.println("내 별점 : " + param.getC_rating());
         request.setAttribute("myCmt", param);
      }
      
      // 내 댓글 뿌리기 - 끝
      
      ViewResolver.viewForward("webtoonDetail", request, response);
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
   }

}