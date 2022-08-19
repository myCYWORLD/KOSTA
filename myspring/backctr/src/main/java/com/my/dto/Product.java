package com.my.dto; //DataTransferObject의 약자 - 자료 전달용 객체

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Product implements Serializable{ 
	private String prodNo;
	private String prodName;
	transient private int prodPrice;  //직렬화 제외할때 transient사용
	private String prodInfo;
	private Date prodMfd; //java.util package에 존재 
	public Product() {}
	public Product(String prodNo,String prodName,int prodPrice){
		this(prodNo, prodName, prodPrice, null,null); //맨 밑의 product에서 
//		this.prodNo=prodNo;
//		this.prodName=prodName;
//		this.prodPrice=prodPrice;
	}
	public Product(String prodNo,String prodName,int prodPrice,Date prodMfd){
		this(prodNo, prodName, prodPrice, null, prodMfd);
//		this.prodNo=prodNo;
//		this.prodName=prodName;
//		this.prodPrice=prodPrice;
//		this.prodMfd=prodMfd;
	}
	public Product(String prodNo,String prodName,int prodPrice,String prodInfo,Date prodMfd){
		this.prodNo=prodNo;
		this.prodName=prodName;
		this.prodPrice=prodPrice;
		this.prodInfo = prodInfo;
		this.prodMfd=prodMfd;
	}
	public void print() {
		System.out.println("상품번호:"+prodNo+"상품명:"+prodName+ "가격:"+ prodPrice+"상세정보:"+prodInfo+"제조일자:"+prodMfd);
	}
	@Override
	   public String toString() {
	      return ("상품번호는 " + prodNo + ", 상품명은 " + prodName + ", 가격은 " + prodPrice );
	   }
	   
	   @Override
	   public int hashCode() {
	      return Objects.hash(prodNo);
	   }
	   @Override
	   public boolean equals(Object obj) {
	      if (this == obj)
	         return true;
	      if (obj == null)
	         return false;
	      if (getClass() != obj.getClass())
	         return false;
	      Product other = (Product) obj;
	      return Objects.equals(prodNo, other.prodNo); //return prodNo.equals 
	   }
	   public String getProdNo() {
	      return prodNo;
	   }
	   public void setProdNo(String prodNo) {
	      this.prodNo = prodNo;
	   }
	   public String getProdName() {
	      return prodName;
	   }
	   public void setProdName(String prodName) {
	      this.prodName = prodName;
	   }
	   public int getProdPrice() {
	      return prodPrice;
	   }
	   public void setProdPrice(int prodPrice) {
	      this.prodPrice = prodPrice;
	   }
	   public String getProdInfo() {
	      return prodInfo;
	   }
	   public void setProdInfo(String prodInfo) {
	      this.prodInfo = prodInfo;
	   }
	   public Date getProdMfd() {
	      return prodMfd;
	   }
	   public void setProdMfd(Date prodMfd) {
	      this.prodMfd = prodMfd;
	   }
	   
	   


//	public String toString() {
//		return ("상품번호는 " + prodNo + ", 상품명은 " + prodName + ", 가격은 " + prodPrice);
//	}
//	public String toStringTwo() {
//		return (prodNo + ":" + prodName + ":" + prodPrice + "\r\n");
//	}
//	public String getProdNo() {
//		return prodNo;
//	}
//	public void setProdNo(String prodNo) {
//		this.prodNo = prodNo;
//	}
//	public String getProdName() {
//		return prodName;
//	}
//	public void setProdName(String prodName) {
//		this.prodName = prodName;
//	}
//	public int getProdPrice() {
//		return prodPrice;
//	}
//	public void setProdPrice(int prodPrice) {
//		this.prodPrice = prodPrice;
//	}
//	public String getProdInfo() {
//		return prodInfo;
//	}
//	public void setProdInfo(String prodInfo) {
//		this.prodInfo = prodInfo;
//	}
//	public Date getProdMfd() {
//		return prodMfd;
//	}
//	public void setProdMfd(Date prodMfd) {
//		this.prodMfd = prodMfd;
//	}
	

}


/**
 * 자바빈 = 자바컴포넌트
 * public 생성자()
 * property는 public이 아닌 mf로 선언
 * property용 설정 메서드는 public setter메서드 필요 (public setSize(){}, setS
 * 			 조회 메서드는 public getter메서드 필요(public
*/