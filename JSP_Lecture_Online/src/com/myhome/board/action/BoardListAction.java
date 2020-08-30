package com.myhome.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myhome.dao.BoardDao;
import com.myhome.dto.BoardDto;

public class BoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<BoardDto> list = BoardDao.getInstance().getList();//DB���� list������ �޾ƿ´�.
		ActionForward actionForward = new ActionForward();
		request.setAttribute("list", list); //request�������� list �Ӽ��� �߰�
		actionForward.setNextPath("BoardListView.do");//execute() ���� �� BoardListView.do�� ��û�ϰڴ�
		actionForward.setRedirect(false);
		return actionForward;
	}

}
