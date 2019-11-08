package com.nius.foundation.tcp.chat.demo1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);

        Socket socket = server.accept();

        // 接收客户端的请求
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        String requestData = dis.readUTF();

        // 处理数据
        String response = requestData + "【该数据已经过服务器处理】";

        // 响应客户端的请求
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(response);;
        dos.flush();
    }
}
