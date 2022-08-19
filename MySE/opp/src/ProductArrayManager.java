/**
 *--상품--
 *상품번호 F0001  G0001  D0001
 *상품명   스콘    머그1   아메리카노
 *상품가격 1000   2000    1000  
 *상품상세정보 "~~"
 *상품제조일자  22/04/20
 *           220420
 *           042022
 *           2022/04/02
 *           Date   
 * @author kosta
 *
 */
import java.net.BindException;
import java.util.Date;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.repository.ProductArrayRepository;
public class ProductArrayManager {
	public static void main(String[] args) {
		ProductArrayRepository repository;
		repository = new ProductArrayRepository(); //최대5개까지 저장할 수 있는 저장소를 생성한다
		try {
			for(int i=1; i<=10; i++) {
//			try{
				repository.insert(new Product("D000"+i, "상품"+i, 1500, new Date()));
//			}catch(ArrayIndexOutOfBoundsException e) {
//				System.out.println("저장소가 꽉찼습니다.");
			}
//	    }catch(ArrayIndexOutOfBoundsException e) {
//	    	System.out.println("저장소가 꽉찼습니다.");
//	    }
		}catch(AddException e) {
			System.out.println(e.getMessage());
		}

		Product[]products = repository.selectAll(); //저장된 상품들을 반환한다
		
		for(int i=0; i<products.length; i++) {
			products[i].print(); 
			
			
			//상품번호:D0001, 상품명:상품1, 가격:1500, 상세정보:null, 제조일자:~~가 출력됨
			//상품번호:D0002, 상품명:상품2, 가격:1500, 상세정보:null, 제조일자:~~가 출력됨			
		}
	}
}
//		A a = new A();
//		B b = new B();
//		C c = new C();
//		try {
//			a.~~();
//			b.~~();
//			c.~~();
//		}catch(AException ae) {
//			~~~
//		}catch(BException be) {
//			~~~
//		}catch(CException ce) {
//			~~~
//		}