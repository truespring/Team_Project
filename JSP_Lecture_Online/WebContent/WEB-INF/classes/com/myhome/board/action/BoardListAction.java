package com.myhome.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myhome.dao.BoardDao;
import com.myhome.dto.BoardDto;

public class BoardListAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = Integer.parseInt(request.getParameter("page"));
		BoardDao dao = BoardDao.getInstance();
		ArrayList<BoardDto> list = dao.getList(page); // DB���� list ������ �޾ƿ´�.
		int totalpages = dao.getTotalPages();
		ActionForward actionForward = new ActionForward();
		request.setAttribute("list", list); // request�������� list �Ӽ��� �߰��Ѵ�.
		request.setAttribute("totalPages", totalpages); 
		request.setAttribute("currentPage", page);
		actionForward.setNextPath("BoardListView.do"); // execute() ���� �� BoardListView.do�� ��û�ϰڴ�.
		actionForward.setRedirect(false);   // forward �ϰڴ�.
		return actionForward; 
	}
}
