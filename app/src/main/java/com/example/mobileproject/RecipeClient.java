package com.example.mobileproject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class RecipeClient {
    //change to method
    //call the client -> btn event
    public static void main(String[] args) {
        try(Socket client = new Socket())
        {
            InetSocketAddress ipep = new InetSocketAddress("localhost", 8080);
            client.connect(ipep);

            try(OutputStream sender = client.getOutputStream(); InputStream receiver = client.getInputStream();)
            {
                for (int i = 0; i < 10; i++)
                {

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
