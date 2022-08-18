public class TPC04 {
	public static void main(String[] args) {
		//4.데이터 이동 (변수 VS 배열) 
		//변수 개별적으로 만들면 이동하기 어렵고
		//배열로 만들면 이동하기 쉬움 
		int a,b,c;
		a = 10;
		b = 20;
		c = 30;
		// a+b+c = ? 메서드처리 -> hap()
		hap(a,b,c); //-> 메서드 호출 
		
		//int[]arr;
		//arr = new int[3];
		int[] arr = new int[3];
		arr[0] = a;
		arr[1] = b;
		arr[2] = c;
		
		hap1(arr);
	}
	public static void hap(int x, int y, int z) {
		int sum = x+y+z;
		System.out.println(sum);
	}
	public static void hap1(int[] x) {
		//반복문 활용 - for, while
		//int sum = x[0]+x[1]+x[2];
		int sum = 0;
		for(int i=0; i<x.length; i++) {
			sum+=x[i];
		}
		System.out.println(sum);
	}
}
