package com.nius.tcp.chat.demo2;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        createClient();
    }

    private static void createClient()  throws IOException {
        Socket client = new Socket("localhost", 9999);
        /// 发送请求，使用一个线程(此处使用匿名类)
        new Thread(new Runnable() {
            private BufferedReader consle;
            private DataOutputStream dos;
            private boolean isRunning = true;

            // 匿名类的构造函数就是一个代码块
            { initConsle(); initDos(); }

            private void initConsle() {
                InputStreamReader isr = new InputStreamReader(System.in);
                consle = new BufferedReader(isr);
            }
            private void initDos() {
                try {
                    dos = new DataOutputStream(client.getOutputStream());
                } catch (IOException e) {
                    //e.printStackTrace();
                    isRunning = false;
                    CloseIOUtil.closeIOStream(dos, consle);
                }
            }

            private void request() {
                try {
                    String msg = "";
                    while (isNeedReInput(msg)) {
                        msg = consle.readLine();
                        if (isNeedReInput(msg)) {
                            System.out.println("请输入请求有误，请重新输入:");
                        }
                    }

                    dos.writeUTF(msg);
                    dos.flush();

                } catch (IOException e) {
                    //e.printStackTrace();
                    isRunning = false;
                    CloseIOUtil.closeIOStream(dos, consle);
                }
            }

            private boolean isNeedReInput(String msg) {
                return null == msg || msg.equals("");
            }

            @Override
            public void run() {
                while (isRunning) {
                    request();
                }
            }
        }).start();



        /// 接收响应，使用另一个线程(此处使用匿名类)
        new Thread(new Runnable() {
            private DataInputStream dis;
            private boolean isRunning = true;

            // 匿名类的构造函数就是一个代码块
            { initDis(); }

            private void initDis() {
                try {
                    dis = new DataInputStream(client.getInputStream());
                } catch (IOException e) {
                    //e.printStackTrace();
                    isRunning = false;
                    CloseIOUtil.closeIOStream(dis);
                }
            }

            private String response() {
                String msg = "";
                try {
                    msg = dis.readUTF();
                } catch (IOException e) {
                    // e.printStackTrace();
                    isRunning = false;
                    CloseIOUtil.closeIOStream(dis);
                }
                return msg;
            }

            @Override
            public void run() {
                System.out.println("请输入请求参数:");
                while (isRunning) {
                    String msg = response();
                    System.out.println(msg);
                    System.out.println("请输入请求参数:");
                }
            }
        }).start();
    }
}
