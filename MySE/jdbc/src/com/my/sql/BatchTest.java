package com.my.sql;

import java.io.InterruptedIOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchTest {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String insertSQL = "INSERT INTO a_tbl VALUES (?)";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(insertSQL);
//			for(int i=101; i<=200; i++) {
//				pstmt.setInt(1, i);
//				pstmt.executeUpdate();
//			}
			for(int i=101; i<=200; i++) {
				pstmt.setInt(1, i);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(pstmt, con);
		}
	}

}
