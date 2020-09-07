package com.allWebtoon.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allWebtoon.dao.WebtoonCmtDAO;
import com.allWebtoon.util.MyUtils;
import com.allWebtoon.vo.UserVO;
import com.allWebtoon.vo.WebtoonCmtVO;

@WebServlet("/webtoon/cmt")
public class WebtoonCmtSer extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   // 댓글 삭제
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

   // 댓글 ( 작성 / 수정 )
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      UserVO loginUser = MyUtils.getLoginUser(request);
      int u_no = loginUser.getU_no();
      int w_no = MyUtils.getIntParameter(request, "w_no");
      String strC_rating = request.getParameter("c_rating");
      float c_rating = Float.parseFloat(strC_rating);
      String c_com = request.getParameter("c_com");
      System.out.println("점수 확인 : " + c_rating);
      
      
      WebtoonCmtVO param = new WebtoonCmtVO();
      param.setU_no(u_no);
      param.setW_no(w_no);
      param.setC_com(c_com);
      param.setC_rating(c_rating);
      
      String cmtChk = request.getParameter("cmtChk"); // 댓글 등록인지 수정인지 판단하는 변수
      
      int result;
      switch(cmtChk) {
      case "0": // 등록
         result = WebtoonCmtDAO.insCmt(param);
         System.out.println("댓글 등록 : " + result);
         break;
      default: // 수정
         result = WebtoonCmtDAO.updCmt(param);
         System.out.println("댓글 수정 : " + result);
         break;
      }
      response.sendRedirect("/webtoon/detail?w_no=" + w_no);
   }

}