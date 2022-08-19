package net.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.NoRouteToHostException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

class TCPBroadThread extends Thread {
	private Socket s;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private String clientIP = null;
	private List<TCPBroadThread> list;
	public TCPBroadThread(Socket s,List<TCPBroadThread> list) throws IOException{
		this.s = s;
		this.list = list;
		InetAddress client = s.getInetAddress();			   //변수 초기화
		clientIP = client.getHostAddress();					  //변수 초기화	
		dis = new DataInputStream(s.getInputStream());		  //변수 초기화
		dos = new DataOutputStream(s.getOutputStream());     //변수 초기화
//   -> 초기화를 미리 시키고 시작할건지 cpu점유 후에 초기화 시킬건지 생각해봐야,,
		for(int i=0; i<list.size(); i++) {
//		System.out.println(clientIP + "가 접속했습니다");
		TCPBroadThread t = list.get(i);
		t.dos.writeUTF(clientIP + "가 접속했습니다");
		}
		
	}
	public void broadcast(String msg) {
		for(int i=0; i<list.size(); i++) {
//			System.out.println(clientIP + "가 접속했습니다");
			TCPBroadThread t = list.get(i);
			try {
				t.dos.writeUTF(msg);
			} catch (IOException e) {
				
			}
		}
	}
	public void run() {
		try {
//			InetAddress client = s.getInetAddress();			   //변수 초기화
//			clientIP = client.getHostAddress();					  //변수 초기화	
//			dis = new DataInputStream(s.getInputStream());		  //변수 초기화
//			dos = new DataOutputStream(s.getOutputStream());     //변수 초기화
			String receiveData = null;
			while(!(receiveData = dis.readUTF()).equals("quit")) {
//				for(int i=0; i<list.size(); i++) {
//				TCPBroadThread t = list.get(i);
//				t.dos.writeUTF(receiveData);
//			}
				broadcast(clientIP + ">" + receiveData);
			}	
		} catch(SocketException e) {
		} catch(IOException e) {
		} finally {
			if(s != null) {
				try {
					list.remove(this);
					s.close();
				}catch(IOException e) {
				}
			}
			broadcast(clientIP + "와 연결을 종료합니다");
//			System.out.print(clientIP==null?"클라이언트":clientIP);
//			System.out.println("와 연결을 종료합니다");
		}
	}
}

public class TCPBroadServerTest {
	public static void main(String[] args) {
		int port = 5433;
		ServerSocket ss = null;

		try {
			//1. port열기
			ss = new ServerSocket(port);
			//List생성
			List<TCPBroadThread> list = new Vector<>(); 
			//ArryaList에서 삭제를 마무리하지 못하고 cpu를 뺏기게 된다면 삭제가 안된 상태에서 
			//vector가 더 안전
			while(true){
				Socket s = null;

				try {
					//2. 클라이언트기다리기, 소켓생성
					//(소켓이 있어야 스트림을 만들 수 있고 메인스레드와 새 스레드 사이에서의 공유객체(자원))
					s = ss.accept();   //s가 소켓타입의 변수

					//새로운 스레드 생성시작
					TCPBroadThread t = new TCPBroadThread(s, list); 
					//리스트 추가
					list.add(t);
					//스레드시작
					t.start();    //throws만나서 시작되지 못 함
					//ㄴ>여기까지가 메인스레드의 할 일 (클라이언트 기다리기, 소켓 생성, 새로운 스레드 생성)
				}catch (IOException e) {
					
				}

					//새로운 스레드가 할 일 → 소켓으로 데이터 수신송신
					//				
			}//end while(true)
		} catch (BindException e) {
			System.out.println(port+"포트가 이미 사용중입니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}