package com.nius.udp.base;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.channels.DatagramChannel;

/**
 * 服务器
 */
public class MyServer {

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
            System.out.println(new String(data, 0, packet.getLength()));

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 6.释放资源
            server.close();
        }
    }
}
