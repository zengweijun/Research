package com.nius.tcp.base;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) throws IOException {

        // 1、服务器端使用ServerSocket
        ServerSocket serverSocket = new ServerSocket(8080);

        // 2、等待连接socket（socket可以理解为一根管道）
        System.out.println("等待链接");
        Socket socket = serverSocket.accept();
        System.out.println("已经收到一个客户端链接");

        // 3、响应客户端
        // 发送数据给客户端，使用输出流
        // 先拿到socket中的流，往流里边写东西
        String msg = "欢迎使用!";

        /**
         OutputStream sos = socket.getOutputStream();
         OutputStreamWriter osw = new OutputStreamWriter(sos);
         BufferedWriter bw = new BufferedWriter(osw);

         bw.write(msg);
         bw.newLine(); // 让客户端可以使用readLine读取
         bw.flush();

         bw.close();
        */

        // 这里也可以使用DataOutputStream
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(msg);
        dos.flush();
        dos.close();

        // 事实上服务器一直在服务，基本不会调用close
        serverSocket.close();
    }
}
