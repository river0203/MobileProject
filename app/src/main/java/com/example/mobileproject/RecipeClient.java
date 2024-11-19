package com.example.mobileproject;

import android.util.Log;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

// TODO : gpt에서 동일한 답변을 출력하는지 확인하고 프롬프트 조정

public class RecipeClient {
    private static final String SERVER_IP = "10.0.2.2";
    private static final int SERVER_PORT = 8000;
    private static RecipeClient clientInstance;

    private static String message;
    private RecipeModel ingredientList = new RecipeModel();

    public void connectToServer() {try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            //String message = "계란, 파, 간장으로 만들수 있는 간단한 레시피 한국어로 자세히 알려줘";

            message = ingredientList.getStrIngredientList();

            if(message == null){
                message = "null";
            }

            byte[] data = message.getBytes();

            ByteBuffer b = ByteBuffer.allocate(4);
            // byte포멧은 little 엔디언이다.
            b.order(ByteOrder.LITTLE_ENDIAN);
            b.putInt(data.length);
            // 데이터 길이 전송
            out.write(b.array(), 0, 4);
            // 데이터 전송
            out.write(data);

            data = new byte[4];
            // 데이터 길이를 받는다.
            in.read(data, 0, 4);
            // ByteBuffer를 통해 little 엔디언 형식으로 데이터 길이를 구한다.
            b = ByteBuffer.wrap(data);
            b.order(ByteOrder.LITTLE_ENDIAN);
            int length = b.getInt();
            // 데이터를 받을 버퍼를 선언한다.
            data = new byte[length];
            // 데이터를 받는다.
            in.read(data, 0, length);

            // byte형식의 데이터를 string형식으로 변환한다.
            message = new String(data, "UTF-8");
            // 콘솔에 출력한다.
            System.out.println(message);

        } catch (SocketTimeoutException e) {
            Log.e("Socket Error", "Connection timed out", e);
        } catch (EOFException e) {
            Log.e("Socket Error", "End of stream reached unexpectedly", e);
        } catch (IOException e) {
            Log.e("Socket Error", "I/O Error", e);
        } catch (Exception e){
            System.out.println("예외발생");
        }
    }

    public static RecipeClient getClientInstance()
    {
        if(clientInstance == null)
        {
            clientInstance = new RecipeClient();
        }
        return clientInstance;
    }

}
