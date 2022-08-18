public class StaticAndFinal {
	public static void main(String[] args) {
	}
}


/*
 class A {
 int iv;          =>non-static variable = instance variable
 static int sv;   => =class variable = static variable
                      ㄴ non-static과 static 변수가 멤버변수
 void main() {}
 static void sm(){}
 }
 
 class Test{
 public static void main(String[]args){
 A a;
 a=new A();
 a=null; ->GC대상(인스턴스변수=객체 생성시 자동 초기화되며 생성되었을 때 제거됨)
 a=newA();
 }
 
 과정
 
 java -cp bin test
 test.class 파일찾기
 JVM으로 로딩
 바이트코드 검증
 0,1로 재해석
 CLASS Area에 기억
 static 변수를 만나게되면 static변수 메모리 할당되면서 자동 초기화
 main ()에 호출
 stack 영역에 a 변수 생성 -> heap 영역에 할당되면서 객체 생성(인스턴스변수는 객체 생성 후에 사용 가능)
 객체 생성시 static을 제외한 변수들은 초기화되어 heap에 할당 (각각 할당 되어지는 메모리가 다 다름)
 웬만해서는 static변수 하지 말기 
 
/* final
   한번 선언하면 실행중에 수정x
   static과 같이 사용 가능
   상수를 만들고 싶으면 대문자로
 */

/* import 사용해서 패키지 단위
 * package 아래 하위패키지로 생각하지 말고 각각 독립적인걸로 생각
 */

/*
객체지향 프로그래밍의 목적 : 클래스의 재사용성을 높이는데에 있음
객체 -> 실세계의 사물,추상화된 것
객체지향언어의 특징 
1.캡슐화 -> 정보공개와 은닉
2.상속 -> 상위클래스에서 하위클래스로 필드와 메소드등을 상속
3.다형성
*/