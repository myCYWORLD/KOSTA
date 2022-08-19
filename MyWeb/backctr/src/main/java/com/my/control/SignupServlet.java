package com.my.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.sql.MyConnection;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr"); //상세주소
		String buildingno = request.getParameter("buildingno"); //우편번호 식별자
		//		System.out.println(id + ":" pwd + ":" + name + ":" + addr + ":" + buildingno);

		//--------------------------------------------------------
		//데이터베이스에 정보 저장  -> customer 테이블 사용
		//DB연결
		Connection con = null;     //가입성공 아닐 시 무조건 실패
		//SQL송신
		PreparedStatement pstmt = null; //executeUPdate() -> DML이나 DDL 사용하려면 메서드 사용
		int rs = 0;  //insert일 때에는 자료형 int사용 / select일 때에는 resultSet 사용

		String result = "{\"status\":0, \"msg\": \"가입실패\"}";
		try {
			con = MyConnection.getConnection();
			String insertSQL = "INSERT INTO customer(id,pwd,name, status, buildingno, address) VALUES (?,?,?,1,?,?)";
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, addr);
			pstmt.setString(5, buildingno);
			pstmt.executeUpdate(); 
			result = "{\"status\": 1,  \"msg\": \"가입성공\" }";
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(pstmt, con);
			//			System.out.println(id + ":" + pwd + ":" + name + ":" + addr + ":" + buildingno);

			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(result);
		}
	}
}
