package net.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClientTest {
	public static void main(String[] args) {
		String serverIP = "192.168.0.27";//="localhost"; 
		//127.0.0.1은 네트워크가 끊겨도 나 혼자 쓸 수 있는 아이디 
		//ㄴ>(서로 테스트 할 때에는 접속할 아이피값)
		int serverPORT = 9493;
		Socket s = null;
		DataOutputStream dos = null;
		Scanner sc = new Scanner(System.in);
		try {
			s = new Socket(serverIP, serverPORT);
			dos = new DataOutputStream(s.getOutputStream());
			//1.for 2.while 3.do-while
			String sendData = null;
//			while(true) {
//				sendData = sc.nextLine();
//				dos.writeUTF(sendData);
//				if(sendData.equals("quit")) {
//					break;
//				}
//			}
			do {
				sendData = sc.nextLine();
				dos.writeUTF(sendData);
			}while(!sendData.equals("quit"));
		} catch (UnknownHostException e) {
//			e.printStackTrace();
			System.out.println("IP가 잘못되었거나 호스트명이 잘못되었습니다");
		} catch (ConnectException e) {
			System.out.println("서버와의 연결이 실패되었습니다");
		} catch (SocketException se) {
			System.out.println("소켓이 끊겼습니다. 서버 장애인지 확인하세요");
			//서버쪽에 장애가 생겨 소켓이 끊김
//	connectException이 발생하면 연결이 안되거나 끊겨서 생기는것(port가 끊겼을때만 나오는거X)
//	port문제라고 하고 싶으면 s = new Socket 아래 작성하지 x 
		} catch (IOException e) { 
//	실제 발생한 exception은 connextException -> IOException의 자식이라 업캐스팅
			e.printStackTrace();
		}
	}
}