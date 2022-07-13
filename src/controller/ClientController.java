package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientController {
	private Scanner sc;
	int count =0;
	
	public ClientController() {
		super();
		sc = new Scanner(System.in);
	}
	
	public void Controller() {
		String serverIp = "192.168.10.45";	//서버 IP주소
		int serverPort = 8080; 				//서버 포트번호
		Socket socket = null;				//소켓
		DataOutputStream dos = null;		//보조 아웃풋스트림
		DataInputStream dis = null;			//보조 인풋스트림
		
		try {
			socket = new Socket(serverIp, serverPort);
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			dis = new DataInputStream(in);
			dos = new DataOutputStream(out);
			System.out.println("< < < < BaseBall Game > > > > >");
			System.out.println("< < < < Game Start > > > > ");
			while(true) {
				for(int i = 0 ;i<3;i++) {
					System.out.print((i+1)+"번째 숫자입력 :" );	//정수 3개 추출
					int num = sc.nextInt();					//정수 입력
					dos.writeInt(num);						//3개의 정수 서버로 전송
					
				}
				
				int count = dis.readInt();	//서버로 부터 count, strike, ball을 정수값으로 받아온다.
				int strike = dis.readInt();
				int ball = dis.readInt();
				
				
				if(count<10) {			//회수가 10회 이내일 경우 출력값
					System.out.println(count+"회 ----->" +strike+"스트라이크"+ball+"볼");
					if(strike==3) {		//횟수가 10회 이내이고 strike값이 3일경우 승리&break
						System.out.println("축하합니다.");
						System.out.println("프로그램을 종료합니다");
						break;
					}
				}else if(count==10) {	//회수가 10회가 될시, 졌습니다 메서드 출력 및 종료
					String lose = dis.readUTF();	//String값으로 정답을 서버에서 얻어온다.
					System.out.println("정답은 ["+ lose +"] 입니다.");
					System.out.println("졌습니다 프로그램을 종료합니다.");
					break;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {	//스트림 반환
			try {
				dos.close();
				dis.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
