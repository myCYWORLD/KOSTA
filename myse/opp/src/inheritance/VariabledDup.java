package inheritance;
class PP{
	int ppv;
	String sv;
}
class P extends PP{
	int pv;
}
class C extends P{
	int cv;
	int sv;
	void c() {
		System.out.println(super.sv);  //null
		System.out.println(super.pv);  //0
	}
}
public class VariabledDup {
	public static void main(String[] args) {
		C c = new C();  //상위클래스와 하위클래스 변수 이름 같을시 하위클래스 변수가 우선적용
		System.out.println(c.sv);
		
//		상위 클래스 변수 지칭하고 싶을 때 super.을 사용하는건 좋지만 
//		->System.out.println(c.super.sv); 이 경우는 아님 ->클래스에 가서 super 사용
//		예약어 super 이용 (1.변수 이름 같을 때,
		c.c();
	}

}
