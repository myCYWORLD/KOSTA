package world;
import world.asia.Korea;   
//import world.europe.*;   //line 2와 3에 나와있는 Korea 와 Japan코드 한줄로 import world.asia*; 만들 수 있음 "*"웬만하면 사용하지말기
import world.asia.Japan;
import java.util.Date;
import java.util.Scanner;
//import java.sql.Date;  //중복된 이름을 import에 쓰는건 권장 x ,
//import java.lang.String; //컴파일시에 자동 포함되어있어 따로 작성 안해도됨 (java lang package말고 다른 패키지 사용시)
public class CountryTest {
	public static void main(String[] args) {
//		world.asia.Korea k;     //앞에 속해있는 패키지 다 작성해야함
//		k = new world.asia.Korea();
//		A a; ->어느 패키지에 속하지 않는 사람은 명확히 알 수 없기 때문에 패키지 *로 묶지 말고 각각 패키지에 명확히 써주면 좋음 (line3 추가설명)
		Korea k;
		k = new Korea();
		
		Japan j = new Japan();
		
		Date d1 = new Date();
		Scanner sc = new Scanner(System.in);
		
		java.sql.Date d2;
//public 누구나 접근할 수 있음 / static  /
// 접근제어자 public(어떤 패키지에서든 접근 가능) default(같은 패키지 객체 내에서 접근 가능) 
//		   private(현재 객체 내에서만 접근 가능->외부에 노출되지 않음,가장 폐쇄적 ->정보 은닉)
//	    k.language = "일본어";      
//		k.capital = "평양";
//		k.population = -1;
		k.setPopulation(-1);
	} 
}