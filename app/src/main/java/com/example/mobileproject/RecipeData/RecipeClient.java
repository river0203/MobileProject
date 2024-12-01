package com.example.mobileproject.RecipeData;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.mobileproject.recommend.Recommend_Menu_Page;

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
    private RecipeModel recipeModel = RecipeModel.getInstance();

    public void connectToServer() {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            //String message = "계란, 파, 간장으로 만들수 있는 간단한 레시피 한국어로 자세히 알려줘";

            message = recipeModel.getStrIngredientList();

            if(message == null){
                message = "null";
            }

            byte[] data = message.getBytes();

            ByteBuffer b = ByteBuffer.allocate(4);
            b.order(ByteOrder.LITTLE_ENDIAN);
            b.putInt(data.length);
            out.write(b.array(), 0, 4);
            out.write(data);

            data = new byte[4];
            in.read(data, 0, 4);
            b = ByteBuffer.wrap(data);
            b.order(ByteOrder.LITTLE_ENDIAN);
            int length = b.getInt();
            data = new byte[length];
            in.read(data, 0, length);

            // byte형식의 데이터를 string형식으로 변환한다.
            message = new String(data, "UTF-8");

        recipeModel.setJsonRecipe(message);
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

    // 싱글톤 패턴
    public static RecipeClient getClientInstance()
    {
        if(clientInstance == null)
        {
            clientInstance = new RecipeClient();
        }
        return clientInstance;
    }

}
