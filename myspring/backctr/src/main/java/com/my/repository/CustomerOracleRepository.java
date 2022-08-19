package com.my.repository;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
@Repository(value="customerOracleRepository")
public class CustomerOracleRepository implements CustomerRepository {

	@Autowired
	private SqlSessionFactory sessionFactory;
//	@Override
//	public Customer selectByIdAndPwd(String id, String pwd) throws FindException {
//
//		//DB와 연결
//		Connection con = null;
//		//SQL송신
//		PreparedStatement pstmt = null;
//		//송신결과
//		ResultSet rs = null;
//
//		try {
//			con = MyConnection.getConnection();
//			String selectIdNPwdSQL = "SELECT * FROM customer WHERE id=? AND pwd=?";
//			pstmt = con.prepareStatement(selectIdNPwdSQL);
//			pstmt.setString(1,  id);
//			pstmt.setString(2,  pwd);
//			pstmt.executeQuery();
//			rs = pstmt.executeQuery();// executequery(); = 결과값을 나타내는 함수
//			if(rs.next()) { //행이 존재하면 로그인 성공된 것 
//				return new Customer(rs.getString("id"),
//						rs.getString("pwd"),
//						rs.getString("name"),
//						rs.getString("ADDRESS"),
//						rs.getInt("STATUS"),
//						rs.getString("BUILDINGNO")
//						);
//			}
//			throw new FindException("고객이 없습니다");
//		}catch (Exception e) {
//			throw new FindException(e.getMessage());
//		}finally {
//			MyConnection.close(rs, pstmt, con);
//		}
//	}

	@Override
	public void insert(Customer c) throws AddException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.insert("com.my.mapper.CustomerMapper.insert", c);
		}catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
//		//DB연결
//		Connection con = null;     //가입성공 아닐 시 무조건 실패
//		//SQL송신
//		PreparedStatement pstmt = null; //executeUPdate() -> DML이나 DDL 사용하려면 메서드 사용
//		int rs = 0;  //insert일 때에는 자료형 int사용 / select일 때에는 resultSet 사용
//
//		String result = "{\"status\":0, \"msg\": \"가입실패\"}";
//		try {
//			con = ds.getConnection();
//			String insertSQL = "INSERT INTO customer(id,pwd,name, status, buildingno, address) VALUES (?,?,?,1,?,?)";
//			pstmt = con.prepareStatement(insertSQL);
//			pstmt.setString(1, customer.getId());
//			pstmt.setString(2, customer.getPwd());
//			pstmt.setString(3, customer.getName());
//			pstmt.setString(4, customer.getAddress());
//			pstmt.setString(5, customer.getBuildingno());
//			pstmt.executeUpdate(); 
//			result = "{\"status\": 1,  \"msg\": \"가입성공\" }";
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new AddException(e.getMessage());
//		} finally {
//			MyConnection.close(pstmt, con);
//		}
//	}

	@Override
	public Customer selectById(String id) throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession(); //Connection과 같은 역할
			Customer c = session.selectOne("com.my.mapper.CustomerMapper.selectById", id); // 2가지의 파라미터가 온다. 하나의 행만 찾는 것임 ,pool을 사용할 때는 connection 을 끊어주지 않아도 된다. 
			if (c == null) {
				throw new FindException("고객이 없습니다.");
			}
			return c;
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();//굳이 안해줘도 된다. 이유는 풀에 반납한다는 의미인데 안해줘도 자동반납?
			}
		}
	}
}