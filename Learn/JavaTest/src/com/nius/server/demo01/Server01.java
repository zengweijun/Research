package com.nius.server.demo01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server01 {
    private ServerSocket serverSocket;
    public static void main(String[] args) {
        Server01 server01 = new Server01();
        server01.start();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("服务器启动成功...");
            receiver();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }
    }

    public void receiver() {
        try {
            // 阻塞式等待
            Socket client = serverSocket.accept();
            System.out.println("一个客户端建立链接...");
            // 获取请求协议
            InputStream is = client.getInputStream();
            // 这里一次性读取完成，实际服务器中应该逐行甚至逐个字节读取
            // 使用一个1M容器
            byte[] data = new byte[1024 * 1024];
            int len = is.read(data);
            String requestInfo = new String(data, 0, len);
            System.out.println(requestInfo);

            // 响应客户端
            // 1.响应行：HTTP/1.1 200 OK
            // 2.响应头（最后有一个空行）
            /*
            Date:Fri Nov 08 14:32:01 CST 2019
            Server:shsxt Server/0.0.1;charset=GBK
            Content-type:text/html
            Content-length:2342342 (必须是字节长度)

            */
            // 3.正文

            // 先准备正文
            StringBuilder content = new StringBuilder();
            content.append("<html>");
            content.append("<head>");
            content.append("<title>");
            content.append("响应回来的数据");
            content.append("</title>");
            content.append("</head>");
            content.append("<body>");
            content.append("终于回来了，哈哈哈...");
            content.append("</body>");
            content.append("</html>");

            long contentLength = content.toString().getBytes().length;

            StringBuilder responseInfo = new StringBuilder();
            String blank = " ";
            String CRLF = "\r\n";

            // 1)响应行
            // HTTP/1.1 200 OK
            responseInfo.append("HTTP/1.1").append(blank);
            responseInfo.append(200).append(blank);
            responseInfo.append("OK (Request is success)").append(CRLF);


            // 2)响应头
            // Date:Mon,31Dec209904:25:57GMT
            // Server:nius Server/0.0.1;charset=GBK
            // Content-Type:text/html
            // Content-Length:2342342 (必须是字节长度)
            responseInfo.append("Date:").append(new Date()).append(CRLF);
            responseInfo.append("Server:").append("nius Server/0.0.1;charset=UTF-8").append(CRLF);
            responseInfo.append("Content-type:").append("text/html").append(CRLF);
            responseInfo.append("Content-length:").append(contentLength).append(CRLF);
            responseInfo.append(CRLF);

            // 3)正文
            responseInfo.append(content.toString());

            // 写出到客户端
            System.out.println("响应数据\n" + responseInfo.toString());
            OutputStreamWriter osr = new OutputStreamWriter(client.getOutputStream());
            BufferedWriter bw = new BufferedWriter(osr);
            bw.write(responseInfo.toString());
            bw.flush();

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端出问题");
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {

    }
}
