public class WatchTV {
	public static void main(String[] args) {
		TV tv;
		tv = new TV();
		
//		tv.power = true;
		tv.powerOn(); //전원을 켠다
		boolean power = tv.isPower(); //전원이 켜진경우 true를 반환
		                              //꺼진경우 false를 반환
		if(power) { //if(power == true)와 같은의미
		//if(!power) { // if(power==false)
			System.out.println("전원이 켜졌습니다");
			tv.setChannel(7); //채널을 설정한다
			tv.volumeUp(); //음량값을 1증가한다
			System.out.println("현재채널은" + tv.getChannel() //7
			                 + "음량은" + tv.getVolume()     //1
			                 +"입니다"); 
			
			tv.setChannel(-1); //잘못된 채널입니다 출력
			System.out.println("현재채널은" + tv.getChannel() + "음량은" + tv.getVolume() + "입니다");
		}else {
			System.out.println("전원이 꺼졌습니다");
		}
		//main method 끝나고 나면 stack영역의 지역변수 메모리 없어짐
		// power, tv 지역변수 .. 	1000번지 정보값도 소멸
		//
	}
}