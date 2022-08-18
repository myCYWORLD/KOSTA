package exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionTest {
	public static void test(int i) {
		if(i !=0) {
		int j = 99 % i; //i가 0인 경우 ArithmeticException 발생
			System.out.println("99를 " + i + "로 나눈 나머지값은 " + j);
		}else {
			System.out.println("0으로는 나눌 수 없습니다");
		}
	}
	
//	public static void test(Object obj) {
//		try {
//		String str = obj.toString();
//		System.out.println(str);
//		}catch(NullPointerException e) {
//			System.out.println("obj가 null입니다");
//		}
//	}
	public static void test(Object obj) throws NullPointerException{
	String str = obj.toString();
	System.out.println(str);
	}
//	public static void main (String[] args) {
//		test(new Object());
//		try {
//		test(null);
//		}catch(NullPointerException e) {
//			System.out.println("인자가 null입니다");
//		}
//	}	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자를 입력하세요:");
		int i = 1;
		FileInputStream fis = null;
		try {                  //try블럭에서 선언한 변수는 try안에서만 사용가능 ,try에서는 예외발생가능코드
			 i = sc.nextInt(); //숫자 입력되지 않으면 InputMismatchException발생
			 fis  = new FileInputStream("a.txt"); //파일자원과의 연결
			 int readValue = fis.read();// -> 파일내용 1byte읽기
			 System.out.println("파일의 1바이트 내용은 =" + readValue);
		}catch(FileNotFoundException e) { 
			   System.out.println("a.txt파일이 없습니다");
	    }catch(IOException e) {
	    	
		}catch(InputMismatchException e) {  //숫자 입력되지 않으면 InputMismatchException발생
			System.out.println("숫자로 입력하세요. 숫자가 아니어서 1로 대신합니다"); 
	    }finally {
	    	System.out.println("finally블럭입니다");
	    	if(fis != null) {
				try {
					fis.close();//파일자원과의 연결해제
				}catch(IOException e) {
					
				}
			}			
		}
		test(i);
		System.out.println("끝");
	}
}

		
//RuntimeException 종류는 컴파일러에 의해 감지되지 않기 때문에  코딩할 때 한줄한줄 어떤 예외가 발생할지 생각하면서 코딩(=uncheckedException)    
//		try_catch finally 구문에서는 finally 구문에서 예외발생 여부와 관계없이 실행 항상 마지막에 실행되는데 대표외부자원과의 연결 끊음
//		예외처리방법 : try-catch - try_catch~finally(try블럭에서 에러 발생하면 즉시 catch로 이동) / throws                                        