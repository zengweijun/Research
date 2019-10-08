package com.nius.udp.base;

import java.io.IOException;
import java.net.*;

public class MyClient {
    public static void main(String[] args) {
        createClient();
    }

    public static void createClient() {
        DatagramSocket client = null;
        try {
            // 1.创建客户端+发送端口
            client = new DatagramSocket(6666);

            // 2.准备数据
            String msg = "你好啊，udp编程";
            byte[] data = msg.getBytes();

            // 3.封包 (数据+服务器地址+服务器端口)
            InetSocketAddress address = new InetSocketAddress("localhost", 8888);
            DatagramPacket packet = new DatagramPacket(data, data.length, address);

            // 4.发送
            client.send(packet);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            client.close();
        }
    }
}
