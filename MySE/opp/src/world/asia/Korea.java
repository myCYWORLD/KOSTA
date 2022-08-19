package world.asia;

public class Korea {
	private String language = "한국어";   //언어
	private String capital;    //수도
	private int population;    //인구
    public Korea() {
    	capital = "서울";
    }
    public void setPopulation(int population) {     //오픈되어있다보니 옳지 않은 값이 들어가 있는지 항상 필터링해야함ㄴ
    	if (population < 1) {
    		System.out.println("인구는 1이상이어야합니다");
    		return;
    	}
    	this.population = population;
    }
}
