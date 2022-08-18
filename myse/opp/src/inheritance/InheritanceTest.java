package inheritance;
public class InheritanceTest {
	public static void main(String[] args) {
		Parent p = new Parent("부모변수", 1000);    //Parent쪽에 default constructor가 없기 때문에 매개변수가 없는 사용자 호출 못함
		p.pm();
		
		Child c = new Child(); // Child에 default 생성자 없으면 오류
//		c.c1 = "자식변수";
//	    System.out.println(c.p1);    //null
//	    System.out.println(c.money); //0
		System.out.println("------");
	    c.cm();                      //자식의 기능입니다
	    System.out.println("------");
	    c.pm();                      //부모의 기능입니다
	    
	    c = new Child("자식변수");
	    System.out.println("------");
	    c.cm();           //public Child(String c1) -> super("자식에서 설정한 부모변수",10000); -> this.c1 = c1;
	   
	}
}
