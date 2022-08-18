package io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.WriteAbortedException;

public class DataIOTest {
	public static void write(String fileName) {
		/*
		 * dest : fileName
		 * node Stream : FileOutputStream
		 * filter stream : DataOutputStream
		 */
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		try {
			fos = new FileOutputStream(fileName);
			dos = new DataOutputStream(fos);
			dos.writeInt(10);           //4byte  
			dos.writeBoolean(false);    //1byte
			dos.writeDouble(1.2);		//8byte
			dos.writeUTF("가나다");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(dos != null) {
				try {
					dos.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
				if(fos != null) {
					try {
						fos.close();
					}catch (IOException e) {	
						e.printStackTrace();
					}
				}		
			}
		}
	}
//			fos.close();//->1번    
//			dos.close();//->2번  = 2번만 닫아도 1번도 닫힘 (안전하게 둘 다 닫고 싶으면  2번먼저 닫고 그 다음에 1번을 닫으면 됨 dos->fos)
	public static void read(String fileName) {
		FileInputStream fis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(fileName);
			dis = new DataInputStream(fis);
			int intValue = dis.readInt();
			boolean booleanValue = dis.readBoolean();
			double doubleValue = dis.readDouble();
			String strValue = dis.readUTF();
			System.out.println(intValue+ ":" + booleanValue+ ":" + doubleValue+ ":" + strValue);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		String fileName = "a.dat";
		write(fileName);
		read(fileName);
	}
}