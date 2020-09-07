package com.allWebtoon.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.allWebtoon.db.JdbcSelectInterface;
import com.allWebtoon.db.JdbcTemplate;
import com.allWebtoon.db.JdbcUpdateInterface;
import com.allWebtoon.vo.WebtoonCmtVO;

public class WebtoonCmtDAO {
   public static int updCmt(WebtoonCmtVO param) {
      String sql = " UPDATE t_comment "
            + " SET c_com = ? , c_rating = ? "
            + " WHERE u_no = ? AND w_no = ? ";
      
      return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

         @Override
         public void update(PreparedStatement ps) throws SQLException {
            ps.setNString(1, param.getC_com());
            ps.setFloat(2, param.getC_rating());
            ps.setInt(3, param.getU_no());
            ps.setInt(4, param.getW_no());
         }
      });
   }
   
   public static int insCmt(WebtoonCmtVO param) {
      String sql = " INSERT INTO t_comment "  
             + " (u_no, w_no, c_com, c_rating) " 
             + " VALUES (?, ?, ?, ?) ";
      
      return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
         
         @Override
         public void update(PreparedStatement ps) throws SQLException {
            ps.setInt(1, param.getU_no());
            ps.setInt(2, param.getW_no());
            ps.setNString(3, param.getC_com());
            ps.setFloat(4, param.getC_rating());
         }
      });
   }
      
      
      public static WebtoonCmtVO selCmt(WebtoonCmtVO param) {
         WebtoonCmtVO vo = new WebtoonCmtVO();
         
         String sql = " SELECT c_com, c_rating " + 
               " FROM t_comment " + 
               " WHERE u_no = ? and w_no = ? ";
         
         JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
            
            @Override
            public void prepared(PreparedStatement ps) throws SQLException {
               ps.setInt(1, param.getU_no());
               ps.setInt(2, param.getW_no());
            }
            
            @Override
            public int executeQuery(ResultSet rs) throws SQLException {
               if(rs.next()) {
                  vo.setC_com(rs.getNString("c_com"));
                  vo.setC_rating(rs.getFloat("c_rating"));                  
               }
               return 1;
            }
         });
      return vo;
   }
   

}