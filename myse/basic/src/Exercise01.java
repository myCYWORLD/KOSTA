public class Exercise01 {
	public static void main(String[] args) {
	    
//	   	while(true) {
//	   		int i = (int)(Math.random()*6) + 1;
//		    int j = (int)(Math.random()*6) + 1;
//		
//	   		if (i+j == 5) {
//	   			System.out.println("("+i+","+j+")");
//	   			break;
//	   		}
//	   		System.out.println("("+i+","+j+")");
//	   	}
//1~100까지 정수중에 3의 배수 갯수	

		int cnt = 0;
		
		for(int i=1; i<100; i++) {
			if(i%3 == 0) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}