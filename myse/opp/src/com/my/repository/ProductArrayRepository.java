package com.my.repository; //(저장소라는 의미로 repository 패키지 생성 - 자료저장 추가 수정 삭제 조회)

import com.my.dto.Product;
import com.my.exception.AddException;
/**
 * 배열 저장소
 * @author User
 *
 */
public class ProductArrayRepository {
		private Product[] products;  //배열저장소
		private int cnt;  //저장소에 저장된 상품수
		public ProductArrayRepository() {                  //중요//
			this.products = new Product [5];
//			=Product[] products = new Product[5];  -> 길이로 초기화
		}
		public ProductArrayRepository(int i) {
			this.products= new Product [i];
		}
		public Product[] selectAll() {
			Product[] result = new Product[cnt];
			for(int i = 0; i < products.length; i++) {
				result[i] = products[i];
			}
			return result;
			
		}
		public void insert(Product product) throws AddException{//throws ArrayIndexOutOfBoundsException {
		   try {
		     this.products[this.cnt]=product;
	         this.cnt++;
//		   products[this.cnt++] = product;  위에 두줄코드와 똑같음 한줄로 쓰면 간단하지만 위험함
	       System.out.println("상품종류개수: " + this.cnt);
		  }catch(ArrayIndexOutOfBoundsException e) {      //프로그램이 갑자기 죽지않도록 catch사용
				System.out.println("저장소가 꽉찼습니다. 현재 상품종류개수:" + this.cnt);
				throw new AddException(("저장소가 꽉찼습니다. 현재 상품종류개수:" + this.cnt)); //catch로 잡고 AddException으로 가공
		  	}
		 }
}
//Add Exception ->추가하다 발생
//Find Exception->
//Remove Exception ->
//



