package com.my.repository;

import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.WriteAbortedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;

public class ProductFileRepository implements ProductRepository {
	private String fileName = "products.txt";
	// 1.products.txt파일이 없다면 현재 project폴더에 파일 생성
    // 2.write하여 해당 파일의 끝에 상품정보(상품번호:상품명:가격) 
	public void insert(Product product) throws AddException{ 
		File prodFile = new File(fileName);     //File 타입의 변수 prodFile에 New 연산자로 fileName을 매개변수로 가지고 있는 File 객체 생성
		FileWriter fw = null;					//FileWriter타입의 변수 fw를 null값으로 초기화
		Scanner sc = null;						//Scanner 타입의 sc변수를 null값으로 초기화
		String prodInfo = product.toStringTwo();//String 타입의 prodInfo에 Product타입의 product에 접근해 toStringTwo 메소드를 호출
		String realProdNo = product.getProdNo();//
		try {
			prodFile = new File(fileName);
			if(!prodFile.exists()) {
				prodFile.createNewFile();
			}	
			sc = new Scanner(prodFile);		
			if(!sc.hasNextLine()) {
				fw = new FileWriter(prodFile,true);
				fw.write(prodInfo);
			}else {
				while(sc.hasNextLine()) {    
					String line = sc.nextLine();
					String[] arr = line.split(":", 3);
					String prodNo = arr[0];
					if(realProdNo.equals(prodNo)) {
						throw new AddException("상품이 이미 있습니다");
					}
				}
				fw = new FileWriter(prodFile,true);
				fw.write(prodInfo); 
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("상품이 없습니다");
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("상품이 없습니다");
		}finally {
			if(fw != null) {
				sc.close();
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	public List<Product> selectAll() throws FindException{
		Scanner sc = null;
	   try {
		   sc = new Scanner(new FileInputStream(fileName));
		   List<Product> all = new ArrayList<>();
		   while(sc.hasNextLine()) {      //== while(sc.hasNextLine()) == true) {}
			   String line = sc.nextLine();
			   String[] arr = line.split(":", 3);
			   String prodNo = arr[0];
			   String prodName = arr[1];
			   int prodPrice = Integer.parseInt(arr[2]);
			   Product p = new Product(prodNo, prodName, prodPrice);
			   all.add(p);
		   }
		   if(all.size() == 0) {
			   throw new FindException("상품이 없습니다");
		   }
		   return all;	
	   } catch (FileNotFoundException e) {
		   e.printStackTrace();
		   throw new FindException("상품이 없습니다");
	   } finally {
		   if(sc != null) {
			   sc.close();
		   }
	   }	
    }
   public Product selectByProdNo(String prodNo) throws FindException{
	   Scanner sc = null;
	   try {
		   sc = new Scanner(new FileInputStream(fileName));
		   Product p = new Product() ;
		   while(sc.hasNextLine()) {
			   String line = sc.nextLine();
			   String[] arr = line.split(":",3);
			   String searchProdNo = arr[0];
			   if(searchProdNo.equals(prodNo)) {
				   String findProdNo = arr[0];
				   String findProdName = arr[1];
				   int findProdPrice = Integer.parseInt(arr[2]);
				   Product prodNoInfo = new Product(findProdNo, findProdName, findProdPrice);
				   p = prodNoInfo;
				   return p;
			   }
		   }
		   throw new FindException("찾으시는 상품이 없습니다.");
	   }catch (FileNotFoundException e) {	
		   e.printStackTrace();
		   throw new FindException("상품이 없습니다");
	   } finally {
		   if(sc != null) {
			   sc.close();
		   }	   
	   }
   }

   public List<Product> selectByProdNoOrProdName(String word) throws FindException{
	   Scanner sc = null;
	   try {
		   sc = new Scanner(new FileInputStream(fileName));
		   List<Product> resultList = new ArrayList<>();
		   while(sc.hasNextLine()) {               //반복문 (sc.hashNextLine() = true일때)
			   String line = sc.nextLine();        //
			   String[] arr = line.split(":",3);
			   String prodNo = arr[0];
			   String prodName = arr[1];
			   if(prodNo.contains(word) ||  prodName.contains(word)) {
				   String findProdNo = arr[0];
				   String findProdName = arr[1];
				   int findProdPrice = Integer.parseInt(arr[2]);
				   Product prodMatter = new Product(findProdNo, findProdName, findProdPrice);
				   resultList.add(prodMatter);               //resulList의 add메소드에 prodMatter 매개변수
			   }
		   }
		   if(resultList.size() == 0) {                      //resultList의 size 메소드 크기가 0이면 FindException 발생
				throw new FindException("상품이 없습니다");
		   }
		   return resultList;	                             // resultList값 반환
	   }catch (FileNotFoundException e) {
		   e.printStackTrace();
		   throw new FindException("상품이 없습니다");
	   } finally {
		   if(sc != null) {
			   sc.close();
		   }
	   }	
   }
} 