package thread;

import java.text.SimpleDateFormat;
import java.util.Date;

class First extends Thread{
	@Override
	public void run()  {   //run method:재정의해서 thread가 할 일 작성 , start method:새로운 thread의 run thread 호출(시작하려고할 때)
		for(int i=1; i<=100; i++) {
			System.out.println(Thread.currentThread().getName() + ":i=" + i);
		}
	}
}
class Second implements Runnable {   //일반적으로는 impmlements 말고 extends thread하면 됨
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //import java.text.SimpleDateFormat; 사용
	@Override
	public void run()  {   //throws Interrupted Exception -> run method에서는 try~catch만 사용할 수 있음 Interrupted사용시 컴파일오류
		for(int i=0; i<10; i++) {
			Date dt = new Date();
			String formatStr = sdf.format(dt);
			System.out.println(Thread.currentThread().getName() + "현재시간은=" +formatStr);   
			//System.out.println(dt);
			long millis = 1000L;
			try {
				Thread.sleep(millis);           //간단한 schedulling method중에 하나 (일시중지 시키는 일을 함)
			} catch (InterruptedException e) {  
				e.printStackTrace();  //e.printSttackTrace()메서드는 예외종류, 내용, 예외 발생위치를 모두 분석해서 콘솔창에 출력 (디버깅할때)
									  //e.getMessage()메서드는 예외 내용만 반환 (배포할때)
			}        
		}
	}
}
public class ThreadTest {
	public static void main(String[] args) {
		Thread ct = Thread.currentThread();
		String ctName = ct.getName();
		System.out.println("현재 사용중인 스레드이름:" + ctName);       
		
		First one = new First();  //객체 생성시 thread 이름 자동할당 
		First two = new First();   
		Second second = new Second();
		Thread three = new Thread(second); 
		Thread four = new Thread(second); 
		//one.run(); ->>main thread에서 run()가 호출됨 
		//직접 run method 호출X 반드시 start method사용해야함
		one.start();              // Thread-0   -> 새로운 thread가 하는 일
		two.start();			  // Thread-1
//		second.start();              컴파일 오류 
		three.start();			  // thread-2 
		four.start();			  // thread-3
		System.out.println("끝"); // main thread가 출력
	}
}
