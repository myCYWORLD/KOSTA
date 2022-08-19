package thread;

class Share {
	int i;
	Share(int i) {
		this.i = i;
	}
	public void push() {
		synchronized (this) { //이 작업을 하는 동안에 상대 스레드에 뺏기지 않음 (동기화처리)
			notify();   //아예 밖에 있는게 아니라 synchronized안에 있으므로 이 블럭이 수행되는 동안 다른 cpu에게 뺏기지 않음(공유객체 잠금)
			System.out.print("before push:i=" + i + ", ");
			i++;
			System.out.println("after push:i=" + i);
		}
	}
	public void pop() {   //일시정지 상태에 있다가 notify를 만나서 닷 runnable 상태로 
		synchronized (this) {
			if(i == 0) {
				try {
					wait();	
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.print("before pop:i=" + i + ", ");
			i--;
			System.out.println("after pop:i=" + i );
		}
	}
}

class Pop extends Thread {
	Share s;
	Pop(Share s) {
		this.s = s;
	}
	public void run() {
		for(int i=0; i<10; i++)
			s.pop();
	}
}

class Push extends Thread {
    Share s;
    Push(Share s) {
    	this.s = s;
    }
    public void run() {
    	for(int i=0; i<10; i++) {
    		s.push();
    	}
    }
}
public class ShareTest {
    public static void main(String[] args) {
    	Share s = new Share(7);
//       = s.i = 7;
        Pop pop  = new Pop(s);
//       = pop.s = s;
        Push push = new Push(s);
//       = push.s = s;
        pop.start();
        push.start();
//      before push:i=9before pop:i=7after pop:i=7 //-> before push 후 after push 나와야 하는데 before pop으로 cpu뺏김    
//      Thread 동기화에 관한 예약어(임계영역을 설정) synchronized
    }
}