package com.myhome.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action { //Model ���� �� execute()�� �������̵��ؼ� ����Ѵ�
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
} //���� �ڷ����� ActionForward 
//�߻�޼ҵ� �ϳ��� ���ִ�.