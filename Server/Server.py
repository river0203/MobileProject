import socket

from RecipeOutput import RecipeOutput

bot = RecipeOutput()

host = ''  # 모든 인터페이스에 대해 접속 허용
port = 8000  # 포트 번호를 클라이언트와 동일하게 설정

server_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_sock.bind((host, port))
server_sock.listen(1)

print("서버 대기 중...")

while True:
    client_sock, addr = server_sock.accept()
    print('Connected by', addr)

    try:
        while True:
            # 1. 클라이언트에서 전송한 메시지 길이 받기
            data = client_sock.recv(4)
            if not data:
                break
            length = int.from_bytes(data, "little")

            # 2. 길이만큼 메시지 받기
            msg = client_sock.recv(length)
            if not msg:
                break

            # 받은 메시지 출력 및 에코 메시지 작성
            msg = msg.decode()
            msg = bot.gpt_send_msg(msg)
            print("Received from client:", msg)

            # 3. 에코 메시지 준비
            response_msg = "Echo: " + msg
            response_data = response_msg.encode()

            # 4. 응답 길이와 메시지 보내기
            response_length = len(response_data)
            client_sock.sendall(response_length.to_bytes(4, byteorder="little"))
            client_sock.sendall(response_data)

    except Exception as e:
        print("서버 오류:", e)

    finally:
        client_sock.close()
        print("클라이언트 연결 종료")

server_sock.close()
