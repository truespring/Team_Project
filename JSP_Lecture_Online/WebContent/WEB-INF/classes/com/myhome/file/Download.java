package com.myhome.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/file/Download")
public class Download extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �Ѱ� ���� ���ڰ� (���ϸ�)
		String fileName = request.getParameter("fileName");

		// storage ������ ���� ���� �޾ƿ��� 
		String realFolder = request.getServletContext().getRealPath("/storage");

		// �ٿ� �ް��� �ϴ� ���� �޾ƿ��� 
		File file = new File(realFolder, fileName);

		// response ��� ���� 
		// ��) Content-Disposition:attachement;fileName=���ϸ�
		//     Content-Length:����ũ��
		fileName="attachment;fileName="+new String(URLEncoder.encode(fileName,"UTF-8")).replaceAll("\\+"," ");
		response.setHeader("Content-Disposition", fileName);
		response.setHeader("Content-Length", String.valueOf(file.length()));

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

		byte[] b = new byte[(int)file.length()];
		bis.read(b, 0, b.length); // bis�� 0�� byte���� b.length��ŭ b�� ����
		bos.write(b);
		
		bis.close();
		bos.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
