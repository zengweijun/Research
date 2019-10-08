package com.nius.udp.attachType;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 服务器
 */
public class MyServer1 {

    public static void main(String[] args) {
        createServer();
    }

    public static void createServer() {
        DatagramSocket server = null;
        try {
            // 1.创建服务器+接收端口(捕获端口可能被占用的异常)
            server = new DatagramSocket(8888);

            // 2.准备接收容器
            byte[] receiveBytes = new byte[1024];

            // 3. 封装为包（解析器）
            DatagramPacket packet = new DatagramPacket(receiveBytes, receiveBytes.length);

//          while (true) {
//              // 4.接收数据
//              server.receive(packet);
//
//              // 5.分析数据
//              byte[] data = packet.getData();
//              System.out.println(new String(data, 0, packet.getLength()));
//          }

              // 4.接收数据
            server.receive(packet);

            // 5.分析数据
            byte[] data = packet.getData();
            double num = convert(data);
            System.out.println(num);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 6.释放资源
            server.close();
        }
    }

    public static double convert(byte[] data) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
        double num = 0;
        try {
            num = dis.readDouble();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return num;
    }
}
