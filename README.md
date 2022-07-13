# BaseBall_Game


//Computer서버에 Client서버를 Tcp 소켓 프로토콜을 통해 연결
//Client가 Computer의 IP주소와 port번호를 통해 서버연결 요청



*프로토콜 : 컴퓨터 간의 정보를 주고 받을 때의 통신방법에 대한 규약으로 접속이나, 전달방식, 데이터형식, 검증 방법등을 맞추기 위한 약속
종류 
1) TCP  - 데이터의 전달의 신뢰성을 최대한 보장하기 위한 방식 <br />
        - 연결지향형 통신 <br />
        - 순차적으로 데이터를 전송하고 확인 및 오류 시 재 전송
        - HTTP, FTP등에 사용

2) UDP  - 데이터의 빠른 전달을 보장하기 위해 사용
        - 비 연결 지향형 통신
        - 확인 및 재전송 작업이 없음
        - 실시간 스트리밍 서비스 등에 사용
