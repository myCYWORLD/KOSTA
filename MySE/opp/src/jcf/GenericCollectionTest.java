package jcf;

import java.util.ArrayList;
import java.util.List;
/**
 * Element Generic
 * @author KOSTA
 *
 */
public class GenericCollectionTest {
	
	public static void main(String[] args) {
		List list1;
		List<String> list2; //"<>"element generic 변수 선언시, 객체 생성시 생성자 바로 앞에
		
		list1 = new ArrayList();
		list2 = new ArrayList<>();
		
		//List list1; => String타입의 값만 저장하려고 했는데 컴파일 성공해서 실행 결과에 String값이 아닌게 나옴
		list1.add(new String("A"));
		list1.add(new Integer(1));
		list1.add(true);
		//if(list1.get(0) instanceof String) {
		String s1 = (String)list1.get(0); //정상실행
		//}
		String s2 = (String)list1.get(1); //ClassCastException 발생, 예외처리 안했으면 프로그램 종료
		String s3 = (String)list1.get(2); 
		
		//List<String> list2 => element generic 사용으로 컴파일 타임에 오류 (의도대로 String값만 나오게 가능 ---> 코드의 안정성↑)
		list2.add(new String("ABC"));
		list2.add("DEF");
		//list2.add(new Integer(1)); //String 자료만 올 수 있음(element generic에서 String타입 저장했기 때문에)-> 컴파일 오류
		list1.add("GHIJ");
		String s4 = list2.get(0); 
		String s5 = list2.get(1);
		String s6 = list2.get(2);
	}
}