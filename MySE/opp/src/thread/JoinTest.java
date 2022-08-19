package thread;
class Join extends Thread{
	int begin, end;
	int sum;
	Join(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}
	public void run() {
		for(int i=begin; i<=end; i++) {
			sum+=i;
		}
	}
}
public class JoinTest {
	public static void main(String[] args) {
		Join j1 = new Join(1,10);
		Join j2 = new Join(11,20);
		j2.start();
		j1.start();
		try {
			j1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			j2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		j1하고 j2스레드는 cpu를 점유하기 위해 다투고 메인스레드만 cpu를 점유하지 않고 기다림
		System.out.println(j1.begin +"부터 " + j1.end+"의 합은" +j1.sum);
		System.out.println(j2.begin +"부터 " + j2.end+"의 합은" +j2.sum);
	}

}
