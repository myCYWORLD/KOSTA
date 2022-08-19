public class Employee {
	String no; // 사번
	String name; // 이름
	int salary; // 급여
	Employee(){ //default Constructor가 X. 
		System.out.println("생성자가 호출됨!");
}
	Employee(String no,String name){
//		this.no = no; 
//		this.name = name;
//		this.salary = 1000;
//		System.out.println("this생성자호출 전"); ->this 생성자 호출은 생성자 내부의 첫줄에서만 가능 
		this(no, name, 1000); //현재 객체의 다른 생성자를 호출하라
	}
	Employee(String no, String name, int salary){
		this.no = no;
		this.name = name;
		this.salary = salary;
	}
	// compile타임에 소스코드에 생성자 없을 때 자동으로 만들어지는 것이 기본 생성자 
	// 생성자는 return타입 이름 자체가 없고, 반드시 class이름과 같아야함
	void Employee() {} // method. return타입이 반드시 형성되어 있어야함
	//this();

}
