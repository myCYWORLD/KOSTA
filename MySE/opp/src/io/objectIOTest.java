package io;

import java.awt.RadialGradientPaint;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

import com.my.dto.Product;

class A implements Serializable{ //serializable 인터페이스만 구현하면 객체 직렬화 ,역직렬화가 가능
	int i;
	A(int i) {
		this.i = i;
	}
}

public class objectIOTest {
	public static void write(String fileName) {
		/*
		 * desc : fileName파일
		 * node stream : FileOutputStream
		 * fileter stream : ObjectOutputStream
		 */
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fileName));  //변수 하나를 없애고 두줄코드를 한줄코드로 만든거임
			oos.writeObject(new Date());
			A a = new A(7);
			oos.writeObject(a);
			
			Product p = new Product("D0001", "아메리카노", 1000);
			oos.writeObject(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void read(String fileName) {
		/*
		 * resource :  fileName파일
		 * node stream : FileInputStream
		 * filter stream : ObjectInputStream
		 */
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(fileName));
			Object obj = ois.readObject();
			System.out.println(obj);      //obj.toString()자동호출됨
			
			Object obj1 = ois.readObject();
			A a = (A)obj1;
			System.out.println(a.i);
			
//			Product p = (Product)ois.readObject();
			Object obj2 = ois.readObject();
			System.out.println(obj2.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("객체쓰기-1, 객체읽기-2, 종료-9");
		int opt = sc.nextInt();
		String fileName = "a.ser";
		switch(opt) {
		case 1:
			write(fileName);
			break;
		case 2:
			read(fileName);
			break;
		}
//		write(fileName);
//		read(fileName);
	}

}
