package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.Reader;

public class KeyboardTest {
	
	public static void main(String[] args) {	
		InputStream is = System.in; //node stream
//		try {
//			int readValue = is.read();
//			System.out.println(readValue + "=" + (char)readValue);
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
//		Reader r = new InputStreamReader(is); //filter stream
//		try {
//			int readValue = -1;
//			while((readValue = r.read()) != -1){ //r.read한 값을 readValue에 받아오고 그 값이 -1(스트림의 끝을 만나는게)이 아니라면 while 구문실행  // char단위로 읽기	
//				System.out.println(readValue + "=" + (char)readValue);  //13->첫번쨰 컬럼으로 이동 10->다음으로 이동(13,10은 엔터의값)
//			} 	//ctrl + z -> 스트림 끝 -1이 나와서 반복문 종료
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
		
		Reader r = new InputStreamReader(is);
		try {
			int readCnt = -1;
			char []cbuf = new char[3];
			int off = 0;
			int len = cbuf.length;
			while((readCnt = r.read(cbuf, off, len)) != -1){ //r.read한 값을 readValue에 받아오고 그 값이 -1(스트림의 끝을 만나는게)이 아니라면 while 구문실행  // char단위로 읽기	
//				for(char c: cbuf) {  //ㄴ> cbuf의 off(0번인덱스부터) length까지 cuf에 채워라
//				for(int i=0; i<readCnt; i++) {     
//					System.out.println(cbuf[i]);
//				}
//				System.out.println("-----------");
				System.out.println(new String(cbuf, 0, readCnt));  
//				System.out.println(readValue + "=" + (char)readValue);  //13->첫번쨰 컬럼으로 이동 10->다음으로 이동(13,10은 엔터의값)
//				System.out.println(new String(cbuf));  //문자 읽어온 갯수
			} 	//ctrl + z -> 스트림 끝 -1이 나와서 반복문 종료
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
