package inheritance;
abstract class Shape {
	protected double area;  // private사용하면 상속이 안되는게 아니라 직접 접근이 어려워 캡슐화(get,set)을 통해 접근
	double getArea() {      //private이나 public으로만 사용하는게 나음
	return this.area;
	}
	void print() {
		System.out.println("이것은 도형입니다");
	}    //상속관계에서 상위메소드와 하위메소드가 똑같은것 = 메소드오버라이딩 ㅡ>(void print () System.out.println("반지름이" + radius + "인 원입니다");와 오버라이딩됨
    abstract void calcArea() ; //-> 오버라이딩 (하위클래스들에도 똑같은 메소드들 있음)
}   //-> abstract를 하나라도 가지고 있으면 클래스에도 abstract써줘야 컴파일 오류 안남

class Circle extends Shape{
	private int radius;
	
	Circle(){
	}
	
	Circle(int radius){
		this.radius = radius;
	}
	void print() {
		super.print();
//		System.out.println("이것은 도형입니다");// ->하고 싶으면 상위 클래스 메소드에 있는걸 super.을 사용해서
		System.out.println("반지름이" + radius + "인 원입니다");
	}
	void calcArea() {
		this.area = (this.radius)*(this.radius)*Math.PI;   //Math.PI -제곱
	}
}

class Rectangle extends Shape{
	private int width;  
	private int height;

	Rectangle (){
	}
	
	Rectangle(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	Rectangle(int width){
		this(width,width);
	}
	
	void calcArea() {
		this.area = (this.width)*(this.height); 
	}
	
	void print() {
		System.out.println("가로" + width + ", 세로" + height + "인 사각형입니다");
	}
}
class Triangle extends Shape{
	int width;  // 밑변
	int height; // 높이
	Triangle(int width, int height) {
		this.width = width;
		this.height = height;
	}
	void print() {		
	}
	void calcArea() {
		this.area = (this.width)*(this.height)/2.0;
	}
	
}
public class ShapeTest {
//	public static void printArea(Circle c) {
//		System.out.println(c.getArea()); //면적값 반환하여 출력
//	}
//	public static void printArea(Rectangle r) {
//		System.out.println(r.getArea()); //면적값 반환하여 출력
//	}
//  ㄴ메소드 오버로드 -> 중복됨
	public static void printArea(Shape s) {
		s.print(); //s.super.print->잘못작성(this나 super는 메소드 내에서만 사용가능)
		System.out.println(s.getArea());
	}
	// shape 영역만 참조 (상위클래스)
	public static void main(String[] args) {
		Shape[] arr = new Shape[4];
		arr[0] = new Circle(5);//참조는 shape타입을 참조하지만 print는 객체가 생성될 당시 오버라이딩된 메소드가 유효 -> rectangle이 유효
		arr[1] = new Rectangle(3,4);
		arr[2] = new Rectangle(5);
		arr[3] = new Triangle(5,3);
		for(int i=0; i<arr.length; i++) { 
			arr[i].calcArea();          //Shape 클래스에 print 메소드 생성해줘서 컴파일 오류 제거함 =>만들고 보니까 하위 메소드와 같이 겹침(오버라이딩)	
			printArea(arr[i]);          //arr~arr[i]가 호출하는 객체에 따라 오버라이딩된 메소드가 호출이 됨 ->다양한 객체 형태의 메소드가 호출 (=다형성)
		}                              //선언부의 메소드 형태가 같아야 한줄코드로 사용가능(오버라이딩 조건= 상속관계에서 매개변수와 상속타입이 같아야함)
		Circle c = new Circle(5); //반지름이 5인 원객체 생성
		c.print(); //반지름이 5인 원입니다 출력
	}                                 
//		c.calcArea(); //원면적 계산
//		printArea(c);
//		상속관계에 있는 상태에서 하위클래스의 객체가 상위클래스의 객체에 대입되어 자동형변환됨(upcasting) -> 변수에 또 다른 변수를 대입한다고 생각
//		Rectangle r = new Rectangle(3,4); //가로3, 세로4인 사각형객체 생성
//		r.print();  //가로3, 세로4인 사각형입니다 출력
//		r.calcArea(); // 사각형 면적 계산
//		printArea(r);
//		
//		Rectangle r1 = new Rectangle(5); //가로 5, 세로 5인 사각형 객체 생성
//		r1.print();  //가로5, 세로5인 사각형입니다 출력
//		r1.calcArea();
//		printArea(r1);
//		//circle 전체 영역 참조(하위클래스)
//	}
}
