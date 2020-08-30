package com.myhome.file;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/file/FileList")
public class FileList extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// �ڷ�� ��� �����
		String storagePath = request.getServletContext().getRealPath("/storage");
		File storageDirectory = new File(storagePath); // storage ���丮�� File ��üȭ 
		File[] files = storageDirectory.listFiles();
		request.setAttribute("files", files); // ���� ��� �迭�� ��Ʈ����Ʈ�� �߰�
		
		request.getRequestDispatcher("fileListView.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
