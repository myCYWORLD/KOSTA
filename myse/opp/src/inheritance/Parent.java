package inheritance;

public class Parent {
	String p1;
    int money;
	public Parent(){} //매개변수가 없는 생성자가 없으면 하위 클래스에 컴파일 오류
	public Parent(String p1, int money) {
		this.p1 = p1;
		this.money = money;
	}
	public void pm() {
		System.out.println("부모의 기능입니다");
		System.out.println("p1=" + p1 + ", money=" + money);
	}
}


//생성자를 따로 만들지 않으면 기본 생성자 자동 생성