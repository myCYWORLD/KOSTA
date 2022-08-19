package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class yeonIOTest {
	public static void readNWriteByByte() {
		/*입력
		 * resource : kosta.txt
		 * node stream : FileInputstream
		 */
		String fileName = "kosta.txt";
		FileInputStream fis = null;
		/*출력
		 * dest : choyeonyy.txt
		 * node stream : FileOutputstream
		 */
		String copyFileName = "choyeonyy.txt";
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream(fileName);
			fos = new FileOutputStream(copyFileName,true);
			int readValue = -1;
			while((readValue = fis.read()) != -1) {
				fos.write(readValue);				
			}
		} catch (FileNotFoundException e) {
			System.out.println("파일이 없습니다");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		readNWriteByByte();
	}
}
