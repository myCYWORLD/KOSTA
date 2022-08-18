package net.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServerTest {
   public static void main(String[] args) {
      int port = 9493;
      ServerSocket ss = null;
      
      try {
         //1. port열기
         ss = new ServerSocket(port);
         
         while(true) {
            Socket s = null; // while문 안에서 매번 초기화가 되어야하므로 옮김
            DataInputStream dis = null;
            InetAddress client = s.getInetAddress();
            String clientIP = null;
            try {
               //2. 클라이언트기다리기, 소켓생성
               s = ss.accept();
               // C - while
               dis = new DataInputStream(s.getInputStream());
               String receiveData = null;
               while(!(receiveData = dis.readUTF()).equals("quit")) {
                  System.out.println( clientIP + "가 보내준 내용:" + receiveData);
               }
            } catch (SocketException e) {   // Client에서 연결을 끊을때 발생함
               //System.out.println("클라이언트에서의 장애로 인해 연결이 끊겼습니다.");
            } catch (IOException e) {
               //e.printStackTrace();
            } finally {
               if(s != null) {
                  try {
                     s.close();
                  } catch (IOException e) {
                  }
               }
               System.out.println(clientIP==null?"클라이언트":clientIP);
               System.out.println("클라이언트와 연결을 종료합니다.");
            }
         } // end while(true)
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