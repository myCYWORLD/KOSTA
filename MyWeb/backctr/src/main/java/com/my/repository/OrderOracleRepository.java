package com.my.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import com.my.dto.OrderInfo;
import com.my.dto.OrderLine;
import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;

public class OrderOracleRepository implements OrderRepository {

	@Override
	public void insert(OrderInfo info) throws AddException {
		Connection con = null;
		try {
			con = MyConnection.getConnection(); //connection을 얻음
			insertInfo(con, info); //connection사용 (insertInfo와 Lines에서도)
			insertLines(con, info.getLines());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MyConnection.close(null, con);
		}
	}
	private void insertInfo(Connection con, OrderInfo info) throws SQLException{
		PreparedStatement pstmt = null;
		String insertInfoSQL = 
				//시퀀스 사용 order_seq.NEXTVAL, ?, SYSDATE (같은 sesssion내에서는 current value 가능하지만 다른 session에서는 못함)
				"INSERT INTO order_info(ORDER_NO,ORDER_ID,ORDER_DT) VALUES (order_seq.NEXTVAL, ?, SYSDATE)";
		pstmt = con.prepareStatement(insertInfoSQL);
		pstmt.setString(1, info.getOrderId());  //재사용성을 위해서 웹과 관련없이 독립적으로 만들어줘야함
		//		예외 발생시 throws 한게 메서드 호출한 곳에서 catch로 잡음
		pstmt.executeUpdate();
	}
	private void insertLines(Connection con, List<OrderLine> lines) throws SQLException{
		PreparedStatement pstmt = null;
		String insertLineSQL = 
				"INSERT INTO order_line(ORDER_NO, ORDER_PROD_NO,ORDER_QUANTITY) VALUES (order_seq.CURRVAL, ?, ?)";
		pstmt = con.prepareStatement(insertLineSQL);
		for(OrderLine line: lines) {
			String prodNo = line.getOrderP().getProdNo();
			int orderQuantity = line.getOrderQuantity();
			pstmt.setString(1, prodNo);
			pstmt.setInt(2, orderQuantity);
			//			addBatch로 일괄 처리
			pstmt.addBatch();
		}
		pstmt.executeBatch();
	}
	@Override
	/*
	 * 주문내역을 db에서 가져온다
	 * 이 메소드의 파라미터로는 스트링타입의 orderId가 와야함
	 */
	//최근주문번호 오름차순 상품번호 내림차순 
	public List<OrderInfo> selectById(String orderId) throws FindException {
		//
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String viewOrder = "SELECT * FROM ORDER_LINE, ORDER_INFO, PRODUCT WHERE ORDER_LINE.ORDER_NO = ORDER_INFO.ORDER_NO";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(viewOrder);
			rs = pstmt.executeQuery();	//rs 변수에 주문내역을 할당
			List<OrderInfo> orderInfo = new ArrayList<>();
			while (rs.next()) {
				int orderNo = rs.getInt("order_no");
				String orderProdNo= rs.getString("order_prod_no");
				int orderQuantity =  rs.getInt("order_quantity");
				String orderIds = rs.getString("order_id");
				Date orderDT = rs.getDate("order_dt");
				String prodName = rs.getString("prod_name");
				Integer prodPrice = rs.getInt("prod_price");
				Product orderP = new Product(orderProdNo,prodName, prodPrice);		
				List<OrderLine> orderLine = new ArrayList<>();
				orderLine.add(new OrderLine (orderNo, orderP, orderQuantity));
				orderInfo.add(new OrderInfo(orderNo, orderId, orderDT,orderLine));
			}
			return orderInfo;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
