public class Loop {

	public static void main(String[] args) {
		// 변수를 설정하는목적은 값이 계속 변하기 때문에 설정함.
		// 동일한 이름의 변수를 중복 선언이 불가능함 (자료형이 달라도 불가능)
		
//		int score = 10;
//		int score1 = 20;
//		int score2 = 30;
//		int score3 = 40;
//		int score4 = 50;
//		int totalScore = score + score1 + score2 + score3 + score4;
//		System.out.println(totalScore);
		
//		int num;
//		num = 1;
//		System.out.println(num);
//		
//		num = 2;
//		System.out.println(num);
//		
//		num = 3;
//		System.out.println(num);
//		num = 0;
//		while(num < 5) {
//			num++;
//			System.out.println(num);
//			
//			
//		}
//		num = 1;
//		while(num < 6) {
//			System.out.println(num);
//			num++;
//		}
		
//		int cnt = 0;
//		int sum = 0;
//		num = 1;
//		while(cnt < 4) {
//			System.out.println(num);
//			num+=2;
//			cnt++;
//		}
//		System.out.println(sum);
		
		// 피보나치 수열을 9개 출력하시오.
		int doublePastNum = 1;
		int pastNum = 0;
		int currentNum;
		for( int i = 0; i < 9; i++) {
			currentNum = doublePastNum+pastNum; // 현재수에 이전이전수와 이전수를 더한 값을 대입
			System.out.println(currentNum);
			
			doublePastNum = pastNum; // 이전이전수에 이전수를 대입
			pastNum = currentNum; // 이전수에 이전이전수와 이전수를 합한 현재수를 대입
//		}
		
		// 랜덤함수는 반환값이 double타입의 메서드이다.그러므로 int변수에 담기 위해서
		// int형으로 캐스팅해줘야 한다.
		
//		int[] lotto = new int[6];
//		for ( int cnt = 0; cnt < lotto.length; cnt++ ) {
//			int rand = (int)(Math.random()*45)+1; // 1부터 46미만의 숫자를 출력
//			for ( int j = 0; j < lotto.length; j++ ) {
//				if (IntStream.of(lotto).anyMatch(x -> x == rand)) {
//					break;
//				} else {
//					lotto[cnt] = rand;
//					System.out.println(lotto[cnt]);
//				}
//				
//			}
//			
//			// 배열에 들어간수와 중복인지 체크를 하고 저장한다. 
//			// 1번째 들어간 수는 그냥 들어간다.
//			// 2번째 들어간 수는 1번째 수와 비교하고 저장한다.
//			// 3번째 수는 1,2번수와 비교하여 저장. ......
//
//		}
		
//		int[] lottoNum = new int[6]; // 6개의 정수를 담는 배열 선언
//		// 중복이 일어나면 반복횟수가 늘어남.
//		for(int insertNum = 0; insertNum < lottoNum.length ; insertNum++){
//		    lottoNum[insertNum] = (int)(Math.random() * 45) + 1;
//		    
//		    // 중복되는 숫자가 있는지 체크
//		    // 만약 중복수가 있으면 증감연산자를 이용하여 그 자리에 다시 난수를 발생시킨뒤 다시 중복 검사실행
//		    for(int searchNum = 0; searchNum < insertNum; searchNum ++){
//		        if(lottoNum[insertNum] == lottoNum[searchNum]){
//		            insertNum--; // insertNum를 앞으로 민다
//		            break;
//		        }
//		    }
//		    
//		}
//		// 로또숫자  출력
//		for( int i = 0; i < lottoNum.length; i++ ) {
//			System.out.println(lottoNum[i]);
//		}
	    }
	}
}	