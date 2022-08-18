import kr.bit.Book;
import kr.bit.PersonVO;

public class TPC03 { 
	public static void main(String[] args) {
		// 관계를 이해하라. PDT VS UDDT
		// 정수 1개를 저장하기 위한 변수를 선언하시오.
		int a; //int 타입의 변수 a를 선언 (int는 정수타입쓰,,,)
		a = 10;// 변수 a에 10을 대입
		
		// 책 1권을 저장하기 위한 변수를 선언하시오.
		Book b;  //Book은 여러개의 상태정보로 이루어져있음
				 //변수 b에 데이터 1개만 저장가능 -> 객체 생성 후에 b에 대입가넝
				 //객체 생성한 메모리 번지
		b = new Book(); //new -> 객체생성 /b에 Book이라는 객체 생성
						// b -> 인스턴스변수,객체변수/ Book이라는 객체는 실체 => 인스턴스
		b.title = "자바";
		b.price = 15000;
		b.company = "한빛미디어";
		b.page = 700;
		
		System.out.print(b.title+"\t");
		System.out.print(b.price+"\t");
		System.out.print(b.company+"\t");
		System.out.println(b.page);
		
		PersonVO p;
		p = new PersonVO();
		p.name = "뫄뫄뫄";
		p.age = 28;
		p.weight = 68.4f;
		p.height =
				188.5f;
		
		System.out.print(p.name + "\t");
		System.out.print(p.age + "\t");
		System.out.print(p.weight + "\t");
		System.out.println(p.height);
	}
}