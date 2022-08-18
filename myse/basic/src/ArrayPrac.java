public class ArrayPrac {
//	public static void main(String[] args) {
//	
//	int[] arr = {10, 20, 30, 40, 50};
//	int sum = 0;
//	
//	for(int i=0; i<arr.length; i++) {
//		sum+=arr[i];
//	}
//	System.out.println("sum="+sum);
//	}
	
	public static void main(String[] args) {
		int [][]arr = {
				{5 , 5 , 5 , 5 , 5,},
				{10, 10, 10, 10, 10},
				{20, 20, 20, 20, 20},
				{30, 30, 30, 30, 30}
		};
		
		int total = 0;
		float average = 0;
		float cnt=0.0F;
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				total+=arr[i][j];
				cnt++;
				average=(float)(total/cnt);
			}
		}
		System.out.println("total=" + total);
		System.out.println("average="+average);
	}
}