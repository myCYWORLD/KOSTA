public class TPC08 {
	public static void main(String[] args) { //main메서드에는 static이 꼭 있어야함
		int a = 30;
		int b = 50;
		int v = add(a,b); //static method 호출
		System.out.println(v);
	}
	//static -> 프로그램을 실행하기 전 메소드를 메모리에 로딩시키기 위해 사용하는 키워드
	public static int add(int a, int b) { //괄호 안 매개변수
		int sum = a+b;
		return sum;
	}
}