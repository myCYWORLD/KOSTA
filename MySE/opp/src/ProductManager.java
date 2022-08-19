import java.util.Scanner;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.repository.ProductListRepositoryPrac;

public class ProductManager {

	public static void main(String[] args) {
		ProductListRepositoryPrac repository;
		repository = new ProductListRepositoryPrac();
		
//		repository.insert(new Product("D0001","상품1",1000));
//		repository.insert(new Product("D0002","상품2",1000));
//		repository.insert(new Product("D0003","상품3",1000));
//		repository.insert(new Product("D0002","상품2",4000)); //이미 존재하는 상품입니다 출력
//	      try {
//	          List<Product> list = repository.selectAll();
//	          for(int i = 0; i < list.size(); i++) {       //배열에서는 length       리스트에서는 size
//	             Product p = list.get(i);            //배열은 방의 길이를 반환 리스트에서는 저장되어있는 요소의 개수
//	             System.out.println(p); //p.toString 자동 호출됨 하지만, 매서드 오버라이딩안돼서 Object의 toString이 나옴 
//	             //상품번호는 D0001, 상품명은 상품1, 가격은 1000
//	             //상품번호는 D0002, 상품명은 상품2, 가격은 1000
//	             //상품번호는 D0003, 상품명은 상품3, 가격은 1000
//	          }
//	       }catch(com.my.exception.FindException e) {
//	          System.out.println(e.getMessage()); //상품이 없습니다
//			//출력 값이 위와 같이 나오기 위해선 product 쪽에 overriding 해야함
//			//상품 번호가 중복되면 저장x. 이미 존재하는 상품입니다 출력
//		}
//		--------------------------------------------------------------------
		Scanner sc = new Scanner(System.in);
		System.out.println("상품추가 테스트");
		System.out.println("상품번호");
		String prodNo = sc.nextLine(); //입력된 엔터값이전 문자열
		
		System.out.println("상품명:");
		String prodName = sc.nextLine(); //Finds and returns the next complete token from this scanner
		
		System.out.println("상품가격:");
		String strPrice = sc.nextLine();
		int prodPrice = Integer.parseInt(strPrice);
		try {
			repository.insert(new Product(prodNo, prodName, prodPrice));
		}catch(AddException ae) {
			
		}
		try {
			Product p = repository.selectByProdNo("D0001");
			System.out.println(p); //상품번호는D0001, 상품명은 상품1, 가격은1000
		}catch(FindException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			Product p = repository.selectByProdNo("없는상품번호");
			System.out.println(p); 
		}catch(FindException e) {
			System.out.println(e.getMessage()); //상품이 없습니다
		}
	}
	
}