package com.myhome.file;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/file/Upload")
public class Upload extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName1 = null; // ���ε� �� ù �� ° ����
		String fileName2 = null; // ���ε� �� �� �� ° ����
		File file1 = null; // �뷮 Ȯ�� ��
		File file2 = null;
		long fileSize1 = 0;
		long fileSize2 = 0;
		
		// ���� ���� ��� ���
		String realPath = request.getServletContext().getRealPath("/storage");
		System.out.println("storage ������ ���� ��� = " + realPath);

		// ���ε�
		// DefaultFileRenamePolicy 
		// : �ڷ�� ���ϰ� �̸��� �ߺ��Ǵ� ���, ���ε��� ���� �̸��� ���ڸ� ���ٿ��� �����ϵ��� �Ѵ�.
		MultipartRequest mr = new MultipartRequest(
									request, 		// ���� �Ķ���Ͱ� �ִ� request ��ü 
									realPath, 		// �Ķ����(����)�� ������ ���� ���
									5 * 1024 * 1024, // ���� �뷮 (5 * 1024 * 1024 = 5MB)
									"UTF-8", 		// ���ڵ� ����
									new DefaultFileRenamePolicy() // �ߺ� �̸� ��å
								);

		// ������ ���� �̸�
		String originalFileName1 = mr.getOriginalFileName("user_file1");
		String originalFileName2 = mr.getOriginalFileName("user_file2");
		
		System.out.println("originalFileName1 : " + originalFileName1);
		System.out.println("originalFileName2 : " + originalFileName2);
		if (originalFileName1 != null) {
			// ���ε�� ������ �� �̸�(�ߺ��̸� ���� ����, �ƴϸ� ���� �̸� �״�� ���)
			fileName1 = mr.getFilesystemName("user_file1");
			
			// �ش� ������ File�� ��ü�� �޾� ��
			file1 = mr.getFile("user_file1");
			
			// ������ ũ��
			fileSize1 = file1.length();
		}

		if (originalFileName2 != null) {
			// ���ε�� ������ �� �̸�(�ߺ��̸� ���� ����, �ƴϸ� ���� �̸� �״�� ���)
			fileName2 = mr.getFilesystemName("user_file2");
			
			// �ش� ������ File�� ��ü�� �޾� ��
			file2 = mr.getFile("user_file2");
			
			// ������ ũ��
			fileSize2 = file2.length();
		}
		
		request.setAttribute("originalFileName1", originalFileName1);
		request.setAttribute("fileName1", fileName1);
		request.setAttribute("fileSize1", fileSize1);

		request.setAttribute("originalFileName2", originalFileName2);
		request.setAttribute("fileName2", fileName2);
		request.setAttribute("fileSize2", fileSize2);
		
		request.getRequestDispatcher("/file/fileUploadResult.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
