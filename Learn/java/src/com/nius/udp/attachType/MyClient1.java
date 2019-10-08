package com.nius.udp.attachType;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 发送带格式的数据
 */
public class MyClient1 {
    public static void main(String[] args) {
        createClient();
    }

    public static void createClient() {
        DatagramSocket client = null;
        try {
            // 1.创建客户端+发送端口
            client = new DatagramSocket(6666);

            // 2.准备数据
//            String msg = "你好啊，udp编程";
//            byte[] data = msg.getBytes();


            byte[] data = convert(89.11);

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

    public static byte[] convert(double num) {
        byte[] data = null;

        // 转换流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        try {
            dos.writeDouble(num);
            dos.flush();

            data = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
