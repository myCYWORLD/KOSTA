import java.util.Scanner;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.repository.ProductListRepositoryBYMe;
import com.my.repository.ProductListRepositoryBYMe;
public class ProductManagerConsoleNByMe {
	Scanner sc = new Scanner(System.in); 
//	ProductListRepositoryBYMe prodRepo = new ProductListRepositoryBYMe();
// PLR타입의 변수 prodRepo에 PLR이라는 객체 생성해서 대입
	public void add() {
//  리턴값 없는 add메서드
		System.out.println(">>상품등록<<");
//		System.out.print("상품 번호 입력 바람: ");
//		String prodNo = sc.nextLine();
//		System.out.print("상품명 입력 바람: ");
//		String prodName = sc.nextLine();
//		System.out.print("상품가격 입력바람: ");
//		int prodPrice = Integer.parseInt(sc.nextLine());
//		Product p = new Product(prodNo,prodName,prodPrice);
//		try {
//			r.insert(p);
//		}catch (AddException e){
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
	}
	
		
	public void findAll() {	
//	리턴값 없는 findAll 메서드	
		System.out.println(">>상품조회<<");
		try {
			prodRepo.selectAll();
		}catch (FindException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	public void findByProdNo() {
//	리턴값이 없는 findByProdNo 메서드
		System.out.println(">>상품번호로 조회<<");
		System.out.print("상품 번호 입력 바람: ");
		try {
			prodRepo.selectByProdNo(sc.nextLine());
		}catch (FindException e) {
			System.out.println(e.getMessage());
		}
	}
	public void findByProdNoOrName() {
		System.out.println(">>상품번호나 이름으로 조회<<");
		System.out.println("검색어 입력 바람: ");
		try {
			prodRepo.selectBySearch(sc.nextLine());
		}catch (FindException e) {
			System.out.println(e.getMessage());
		}
	}
	public void modify() {
		System.out.println(">>상품 수정<<");
		System.out.println("상품 번호 입력 바람: ");
		try {
			prodRepo.selectModify(sc.nextLine());
		}catch (FindException e) {
			System.out.println(e.getMessage());
		}
	}
	//메서드 네임 수정
	
	//ProductListRepository도 완성하세요
	public static void main(String[] args) {
		ProductManagerConsoleNByMe managerConsole = new ProductManagerConsoleNByMe();
		//PMC타입의 managerConsole 변수에 ProductManagerConsole라는 객체 생성
		while(true) { //while문  -> true 일때
			System.out.println("작업구분 :상품등록-1, 상품전체조회-2, 상품번호로 조회-3, 검색어로 조회-4, 상품수정-5, 종료-9");
			//출력
			Scanner sc = new Scanner(System.in);
			//Scanner 타입의 변수 sc에 Scanner 객체 생성 System.in 매개변수
			String opt = sc.nextLine();  //String 타입의 opt변수에 sc.next-
			switch(opt) {              	 //switch구문
			case "1":                	 //case1
				managerConsole.add();    //managerConsole에 add()
				break;				     // case1 탈ㄿ출ㄹㄹㄹ
			case "2":
				managerConsole.findAll();
				break;
			case "3":
				managerConsole.findByProdNo();
				break;
			case "4":
				managerConsole.findByProdNoOrName();
				break;
			case "5":
				managerConsole.modify();
				break;
			case "9":
				System.exit(0);
			}
		}
	}

}
