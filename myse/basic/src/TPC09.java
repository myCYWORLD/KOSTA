public class TPC09 {
	public static void main(String[] args) {
		int a = 56;
		int b = 40;
		//a + b = ?
		//int v = sum(a, b); -> 호출할 수 없음 아래 sum메소드에 static이 없기 때문 -> 객체를 생성해야함
		TPC09 tpc = new TPC09(); //객체 생성,heap 영역에 생성됨 tpc타입인 변수 TPC09
		int v = tpc.sum(a, b);
		System.out.println(v);
	}
	
	public int sum (int a, int b) {
		int v = a+b;
		return v;
	}
	
}
