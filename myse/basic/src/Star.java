public class Star {
	public static void main(String[] args) {
		String star = "*";            //string 타입의 star에 "*"대입 (변수 선언,할당)
		//*
		//**
		//***
		//****
		//*****

		for(int i=1; i<=5; i++) {              //반복문 for문 (int타입의 변수 i가 1이고 i가 5보다 같거나 작으면, i는 1 증감)
			for(int j=1; j<=i; j++) {		   //반복문 for문 (int타입의 변수 j가 1이고 j가 5보다 같거나 작으면, j는 1 증감)
				System.out.print(star);  	   // 출력문 (변수 star 출력) => for문 돌 때 모두 true 여야 star 출력
			}
			System.out.println();			   // 줄바꿈
		}  										
		
//		int cnt = 0;
//		
//		for(int i=1; i<=100; i++) {
//			if(i%3 == 0) {
//				cnt++;
//			}
//		}
//		System.out.println(cnt);		
	}
} 

