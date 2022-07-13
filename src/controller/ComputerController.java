package controller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ComputerController {
	public void server() {		
		  //다른 pc에서 돌린다는가정
	      int port = 8080;
	      Random r = new Random();
	      ServerSocket server = null;
	      DataOutputStream dos = null;	//보조아웃풋 스트림
	      DataInputStream dis = null;	//보조인풋 스트림
	      
	      try {
	         
	         server = new ServerSocket(port);
	         System.out.println("<<<<<<< Baseball Game >>>>>>>");
	         
	         while(true) {
	            
	            System.out.println("클라이언트의 접속을 기다립니다.....");
	            
	            int[] comNum = new int[3];			
	            for(int i=0;i<comNum.length;i++) {
	               comNum[i] = r.nextInt(9)+1;		//1~10이내의 랜덤한 숫자을 배열로 입력
	               for(int j=0;j<i;j++) {			//랜덤 숫자에 대한 중복체크
	                  if(comNum[i] == comNum[j]) {	//중복일시 인덱스에서 그 값을 제거해준다.
	                     i--;
	                     break;
	                  }
	               }
	            }
	            int count = 0;
	            
	            Socket client = server.accept();
	            OutputStream out = client.getOutputStream();
	            InputStream in = client.getInputStream();
	            
	            dos = new DataOutputStream(out);
	            dis = new DataInputStream(in);
	            
	            System.out.println("클라이언트가 접속했습니다.");	//서버숫자 출력
	            System.out.print("서버 숫자 : ");
	            for(int i=0; i<comNum.length; i++) {
	               System.out.print(comNum[i] + " ");
	            }
	            System.out.println();
	            while(true) {	//반복문 시작
	               
	               System.out.println("<<<<<<< Game Start >>>>>>>");
	               
	               int strike = 0;
	               int ball = 0;
	               
	               int clientNum1 = dis.readInt();	//client로 부터 3개의 정수를 받아온다.
	               int clientNum2 = dis.readInt();
	               int clientNum3 = dis.readInt();
	               count++;
	               
	               System.out.println("클라이언트가 입력한 수 -> " + clientNum1 + " " + clientNum2 + " " + clientNum3);
	   
	               if(comNum[0]==clientNum1) strike++;	//숫자가 서로 동일할 경우 strike++
	               if(comNum[1]==clientNum2) strike++;
	               if(comNum[2]==clientNum3) strike++;
	               
	               //서로다른 위치의 배열값이 일치하지만 같은 위치의 값과는 다를경우 ball++ (strike의 값과 ball값이 동시에 올라가는것을 방지)
	               if((comNum[0]==clientNum2&&comNum[0]!=clientNum1)
	                     || (comNum[0]==clientNum3&&comNum[0]!=clientNum1)) {
	                  ball++;
	                  
	               }
	               if((comNum[1]==clientNum1&&comNum[1]!=clientNum2) 
	                     || (comNum[1]==clientNum3&&comNum[1]!=clientNum2)) {
	                  ball++;
	               }
	               
	               if((comNum[2]==clientNum1&&comNum[2]!=clientNum3) 
	                     || (comNum[2]==clientNum2&&comNum[2]!=clientNum3)) {
	                  ball++;
	               }
	               
	               String sendMsg = count + "회 ------> " + strike +  "스트라이크" + ball + "볼";
	               
	               System.out.println(sendMsg);
	               dos.writeInt(count);	//count strike ball의 값을 client에게 넘겨준다.	
	               dos.writeInt(strike);
	               dos.writeInt(ball); 
	               
	               if(count == 10) {	//시도횟수를 10회에 도달했을때,
	                  dos.writeUTF(comNum[0] + " " + comNum[1] + " " + comNum[2]);
	                  System.out.println("시도횟수 " + count + "회로 클라이언트 접속을 종료합니다.");
	                  break;
	               }
	               
	               if(strike == 3) {	//클라이언트의 승리
	                  System.out.println("3스트라이크 정답 현재 클리언트와 접속을 종료합니다.");
	                  break;
	               }
	            }	//반복문 종료
	            
	         }
	         
	         
	      } catch (IOException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            dos.close();
	            dis.close();
	            server.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      }
	      
	   }

}
