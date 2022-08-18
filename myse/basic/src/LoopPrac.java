import javax.lang.model.type.ArrayType;

public class LoopPrac {
	public static void main(String[] args) {
//		int max = 0;
//		int [] array = {3, 14, 5, 17, 25};
//		
//		for(int i=0; i<array.length; i++) {
//			if(array[i]>max) {
//				max = array[i];				
//			}
//		}
//		System.out.println("max: " +max);
// 전체 항목의 합과 평균값		
		int[][]array = {
				{95, 86},
				{83, 92, 96},
				{78, 83, 93, 87, 88}
		};
		
		int sum = 0;
		double avg = 0.0;
		
		
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<array[i].length; j++) {
				sum += array [i][j];
				avg = (double)sum/10;
			}
		}	
		System.out.println("sum: " + sum);
		System.out.println("avg: " + avg);		
	}
}