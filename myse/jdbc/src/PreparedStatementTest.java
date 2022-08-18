import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.my.sql.MyConnection;
 
public class PreparedStatementTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
			//stmt = con.createStatement();
			String insertSQL = "INSERT INTO customer(id, pwd,name, status) "
					                     + "VALUES (?,?,?,?)"; 
//값의 위치에서만 물음표가 사용 가능하고 그 외에는 사용 안됨(sql문법이나 컬럼 위치에 사용x) 
//미리 준비해놓은 문법에서만 사용 가능 "?"=바인드 변수 값을 설정할때에는 setter메서드로 물음표 위치 값 대입해줘야함
			pstmt = con.prepareStatement(insertSQL);
			
			System.out.print("추가할 아이디를 입력하세요:");
			String id = sc.nextLine();
			
			System.out.print("추가할 비밀번호를 입력하세요:");
			String pwd = sc.nextLine();
			
			System.out.print("추가할 이름를 입력하세요:");
			String name = sc.nextLine();
			
			System.out.print("일반고객이면 1, 기업고객이면 2를 입력하세요");
			int status = sc.nextInt();
			//status는 0아니면 1이기 때문에 
			//String insertSQL = "INSERT INTO customer(id, pwd,name) "
			//		           + "VALUES ( '"+ id +"','"+pwd+"'   , '"+name+"'   )";
			//						-> 작은따옴표는 오라클문법이어서
			//stmt.executeUpdate(insertSQL);     //여기는 매개변수 있는 것을 써줘야함
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setInt(4, status);
			pstmt.executeUpdate();  //매개변수 없는 걸 써줘야된다.
			System.out.println("고객 등록 완료");
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}

}