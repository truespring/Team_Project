package com.myhome.board.action;

public class ActionForward {	//�������� ���� ���� �������� ����ΰ�
	private boolean isRedirect;	//true : redirect, false : forward
	private String nextPath;	//���� ���

	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getNextPath() {
		return nextPath;
	}
	public void setNextPath(String nextPath) {
		this.nextPath = nextPath;
	}
}
