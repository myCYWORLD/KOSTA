//		우리반 학생들의 이름 성별 나이 사는곳 전공 비전공 유무
class Students{
	  String name;
	  String gender;
	  int age;
	  String address;
	  boolean major;
	  
	  Students(String name, String gender, int age, String address, boolean major) {
	      this.name = name;
	      this.gender = gender;
	      this.age = age;
	      this.address = address;
	      this.major = major;
	  }
	  Students(String name, String gender, int age, boolean major) {
		  this(name, gender, age, null, major);
	  }
	  Students(String name, int age, String gender) {   
	      this(name, gender, age, null, false);
	  
	  }
	  
	  void print() {
		  System.out.println("이름은 " + name + " 나이는 " + age + " 성별은 " + gender);
	  }
 
	  
}
	  
public class StudentsList { 
	public static void main(String[] args) {
		Students student = new Students("황초연", 27, "여자");  //Students 타입의 student 변수
		student.print();
	}

}
