package com.nius.foundation.tcp.base;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// 可以链接多个客户端的服务器
public class MyServer1 {
    public static void main(String[] args) throws IOException {

        // 1、服务器端使用ServerSocket
        ServerSocket server = new ServerSocket(8080);
        int i = 0;
        while (true) {
            ++i;
            // 2、等待连接socket（socket可以理解为一根管道）
            System.out.println("等待链接");
            Socket socket = server.accept();
            System.out.println("已经收到一个客户端链接" + i);

            // 3、响应客户端
            // 发送数据给客户端，使用输出流
            // 先拿到socket中的流，往流里边写东西
            String msg = "欢迎使用!" + i;

            // 这里也可以使用DataOutputStream
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(msg);
            dos.flush();
            dos.close();
        }

        // 事实上服务器一直在服务，基本不会调用close
        // server.close();
    }
}
