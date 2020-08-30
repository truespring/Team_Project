package com.myhome.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myhome.dao.BoardDao;
import com.myhome.dao.CommentDao;
import com.myhome.dto.BoardDto;
import com.myhome.dto.CommentDto;

public class BoardReadAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward actionForward = null;
		BoardDao boardDao = BoardDao.getInstance();
		BoardDto boardDto = null;
		int boardNum = Integer.parseInt( request.getParameter("brdNo")); // url�� ÷�ε� �۹�ȣ �Ķ���� �ޱ�
		CommentDao commentDao = CommentDao.getInstance(); // ��� dao
		ArrayList<CommentDto> commentList = commentDao.getList(boardNum); // ��� ����Ʈ ��������
		String attName = "brd_" + boardNum; // ���ΰ�ħ �� ��ȸ �� ������ �������� ���ǿ� ���� ����
		HttpSession session = request.getSession();
		
		if(session.getAttribute(attName) == null) { // ���� ������ �� ���� ������ ���ٸ� 
			boardDao.updateHit(boardNum); // ��ȸ�� 1 ����
			session.setAttribute(attName,  "Y"); // attName (��. "brd_22") �� session�� attribute�� ��� 
		}
		boardDto = boardDao.select(boardNum); // DB ��ȸ + �����  Dto�� ����
		request.setAttribute("brdDto", boardDto);
		request.setAttribute("commentList", commentList);
		
		
		actionForward = new ActionForward();
		actionForward.setRedirect(false);
		actionForward.setNextPath("BoardReadView.do");
		return actionForward;
	}
}
