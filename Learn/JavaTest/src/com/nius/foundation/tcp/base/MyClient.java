package com.nius.foundation.tcp.base;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) throws IOException {
        // 1、发起链接
        Socket socket = new Socket("localhost", 8080);

        // 2、解析数据
        /**
         InputStream is = socket.getInputStream();
         InputStreamReader isr = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(isr);
         String str = br.readLine();
         br.close();
         System.out.println(str);
         */

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        String str = dis.readUTF();
        dis.close();
        System.out.println(str);

        socket.close();
    }
}
