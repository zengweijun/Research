package com.nius.tcp.chat.demo1;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 9999);

        // 客户端：输出->服务器
        // 控制台输入流
        System.out.println("请输入请求内容");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader consle = new BufferedReader(isr);
        String inputStr = consle.readLine();

        // 输出到服务器:客户端->服务器
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF(inputStr);
        dos.flush();

        // 接受服务器的响应:服务器->客户端
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String response = dis.readUTF();
        System.out.println(response);
    }
}
