/**
 * TV 객체가 될 클래스입니다.
 * @author 한미래
 * @version 1.0
 */
public class TV {   //TV 클래스 위에 패키지가 없으므로 default패키지
	private boolean power;
	private int channel; //멤버변수
	private int volume;
	public boolean isPower() {
		return this.power; //power 앞 현재 사용중인 tv타입의 객체 생략됨 this. 생략 가능
//	this -> 현재 사용중인 객체 / this. 현재 사용중인 객체의 		
	}
	/**
	 * tv의 채널을 설정한다.
	 * @param channel 설정할 채널값 
	 */
	public void setChannel(int channel) { //매개변수 . 직관적이고 명확한 매개변수 이름 설정/ ch보다 channel이 적절
		this.channel = channel; // 멤버변수 이름 = 매개변수 이름일 경우에 매개변수 이름이 우선됨 -> 매개변수 매개변수에 대입하게 되어버림
	} //따라서 this.(현재 사용하는 객체의)을 항상 써 주어야함
	/**
	 * 채널을 반환한다.
	 * @return 
	 */
	public void getChannel() {
		if (channel < 0) {
			System.out.println("잘못된 채널입니다");
		}
	}
	/**
	 * 음량을 1 증가시킨다.
	 */
	//매개변수 멤버변수 이름 중복될 경우 정확히 사용 위해 멤버변수 앞에 this.을 붙여야함!!
	public void volumeUp() {
		volume++;
	}
	/**
	 * 음량을 반환한다.
	 * @return 음량값
	 */
	public int getVolume() {
		return volume;
	}
	
	
    public void powerOn () {
    	power = true;
    }

}