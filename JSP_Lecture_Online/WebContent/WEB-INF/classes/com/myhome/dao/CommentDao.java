package com.myhome.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.myhome.dto.BoardDto;
import com.myhome.dto.CommentDto;

public class CommentDao {
	private static CommentDao dao;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	private static DataSource ds;
	static {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// �̱��� ����
	private CommentDao() {
	}

	public static CommentDao getInstance() {
		if (dao == null) {
			dao = new CommentDao();
		}
		return dao;
	}

	// close�ϴ� �޼ҵ�1
	private static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// close�ϴ� �޼ҵ�2 (�����ε�)
	private static void close(Connection con, PreparedStatement ps) {
		close(con, ps, null);
	}

	// ��� �߰� �޼ҵ�
	public boolean insert(CommentDto dto) {
		boolean result = false;
		sql = "INSERT INTO comments VALUES(comments_seq.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
		// ������, �ۺ���, �ۼ���ID, �ۼ���NICK
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getParentNum());
			ps.setString(2, dto.getComment());
			ps.setString(3, dto.getId());
			ps.setString(4, dto.getNickname());
			result = ps.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		return result;
	}

	// ��� ���
	public ArrayList<CommentDto> getList(int parentNum) {
		ArrayList<CommentDto> list = new ArrayList<CommentDto>();
		CommentDto dto = null;
		sql = "SELECT * FROM comments WHERE parentNum = ? ORDER BY num ASC" ;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, parentNum);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new CommentDto();
				dto.setNum(rs.getInt("num")); // ��� ���� ��ȣ
				dto.setParentNum(rs.getInt("parentNum")); // ����� ��� �Խñ�
				dto.setComment(rs.getString("comments")); // ��� ����
				dto.setId(rs.getString("id")); // ��� �ۼ��� id
				dto.setNickname(rs.getString("nickname")); // ��� �ۼ��� nickname
				dto.setRegdate(rs.getString("regdate")); // �������
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list.isEmpty() ? null : list;
	}

	// ��� ���� �޼ҵ�
	public boolean delete(int num) { 
		boolean result = false;
		sql = "DELETE FROM comments WHERE num = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			result = ps.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return result;
	}
}
