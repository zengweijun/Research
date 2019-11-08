package com.nius.foundation.tcp.chat.demo2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    // 管理与客户端的所有连接
    private List<ClientProxy> proxies = new ArrayList();

    public static void main(String[] args) throws IOException {
        new Server().start();
    }

    private void start() throws IOException {
        ServerSocket server = new ServerSocket(9999);

        // 不断接收来自客户端的链接请求
        // 为了不造成程序延时卡顿，这里使用多线程
        while (true) {
            // 1.等待链接
            Socket socket = server.accept();

            // 2.开辟线程
            ClientProxy proxy = new ClientProxy(socket);
            proxies.add(proxy); // 保存proxy
            new Thread(proxy).start();
        }
    }

    // 客户端代理，每一个客户端对应一个代理
    // 动态内部类，可以使用外部类的成员变量
    // 这个代理负责接收请求和响应数据
    private class ClientProxy implements Runnable {
        private DataInputStream dis; // 接收器
        private DataOutputStream dos; // 响应器
        private boolean isRunning = true; // 控制标识

        // 构造器
        public ClientProxy(Socket socket) {
            try {
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                //e.printStackTrace();
                stopThread();
            }
        }

        // 接收数据
        private String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                //e.printStackTrace();
                stopThread();
                proxies.remove(this);
            }
            return msg;
        }

        // 响应数据
        private void responseToClient(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                // e.printStackTrace();
                stopThread();
                proxies.remove(this);
            }
        }

        // 响应数据给除自己外的所有群聊用户
        private void responseToAllClient() {
            String msg = receive();

            for(ClientProxy cp: proxies) {
                // if (cp == this) continue;
                cp.responseToClient("这是经过服务器处理后的数据: " + msg);
            }
        }

        @Override
        public void run() {
            while (isRunning) {
                responseToAllClient();
            }
        }

        private void stopThread() {
            CloseIOUtil.closeIOStream(dis, dos);
            isRunning = false;
        }
    }


}
