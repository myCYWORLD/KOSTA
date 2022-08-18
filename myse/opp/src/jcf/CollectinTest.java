package jcf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class A{
	int i;
	A(int i) {
		this.i = i;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(i);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		A other = (A) obj;
		return i == other.i; //= return this.hashCode() == other.hashCode();    //true or false 리턴
	    //hashCode와 equals메소드 둘 다 오버라이딩 되어야 중복시킬 수 있음
	}
	@Override
	public String toString() {
		return "A [i=" + i + "]";
	}
}

public class CollectinTest {
//	static Integer inte = new Integer(1);
	
	@SuppressWarnings("removal")
	public static void test(Collection<Object> c) {
		c.add(new String("one")); //add()의 매개변수 타입은 Object이다. String이 Object로 업캐스팅
		c.add(new Integer(2));    //Integer가 Object로 업캐스팅
		c.add(3); //컴파일시에 c.add(new Integer(3));로 바뀜 (=AutoBoxing) ,Integer가 Object로 업캐스팅
		c.add(new Integer(2)); //AutoBoxing
		c.add(new A(5));       //객체는 다르지만 같게 하고 싶으면 강제화(오버라이딩 이용해서 만들 수 있음)
		c.add(new A(5)); 	   
		
//		System.out.println(inte.hashCode());                           //hashCode 1;
//		System.out.println(new Integer(inte.intValue()).hashCode());   //hashCode 1; =>중복으로 판단해서 1
//		System.out.println(new Long(inte.longValue()).hashCode());     //hashCode 1;
//		System.out.println(new Long(1).hashCode());                    //hashCode 1; =>중복으로 판단해서 1
//		set 자료구조 중복 안된다고요,,아시게써여?

//		c.add(inte);     //equals메소드 호출시 전달 인자가 null이 아니고,같은 타입의 객체이고,같은 값을 가지고 있을 때(hashCode자동호출) true 반환
//		c.add(inte.intValue());   //저장 안 됨 (중복)
//		c.add(inte.longValue());  //자료형 달라서 false => 저장
//		c.add(new Long(1));       //저장 안  됨(중복)
		
		System.out.println("자료수 : "+ c.size());
		System.out.println(c); //c.toString()자동호출됨 (hashSet에서 toString 오버라이딩; []형태로 return-> ","로 분류 -> 분류된 각 요소별로 toString) 
	}						   //class A(오버라이딩 X) 결과 jcf.A@24 (각 요소의 toString메소드가 오버라이딩 안되어있어 object 상속으로 쓰임)
							   //	    (오버라이딩 O) 결과 A [i=5]
	
	public static void test(Map m) {
		m.put("one",           "first");
		m.put(new Integer(2), "second");  //new Integer(2)와 2가 중복 (해시코드가 같음) => 덮어쓰기돼서 fourth로 나옴
		m.put(3,               "third");
		m.put(2,              "fourth");
		m.put(new A(5),           "fi");
		m.put(new A(5),           "si");  // 자료수 4개 나옴(중복; 두번째에 fourth덮어쓰기 되고 다섯번째에 si 덮어쓰기 돼서 4개)
		System.out.println("자료수 :" + m.size());
		System.out.println(m); //m.toString()자동호출됨
	}
	
	public static void main(String[] args) {
		//List list = new ArrayList();    //List도 인터페이스라 직접 생성 못 함
  		//test(list);
		test(new ArrayList());  //List계열은 인덱스 생성 => 결과 - 자료 갯수 4개, 중복저장O, 순차저장X 
		test(new HashSet());    //Set 계열은 인덱스 없음 => 결과 - 자료 갯수 3개, 중복저장X, 순차저장O
		test(new HashMap());    //test 메서드 인자로 HashMap 사용 못함 (컴파일 오류뜸) Map은 컬렉션과 아무 관계 아니라 업캐스팅 안됨
								//ArrayList랑 HashSet은 컬렉선과 상속관계에 있어서 업캐스팅 가능한거
	}
}
//정규표현식
//stack queue