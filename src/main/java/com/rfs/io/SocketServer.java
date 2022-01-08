package com.rfs.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author renfushuai
 * @date 2021/9/29
 * BIO
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true){
            System.out.println("等待连接。。。");
            Socket clientSocket = serverSocket.accept();
            System.out.println("有客户端连接了");
            byte[] bytes = new byte[1024];
            int read = clientSocket.getInputStream().read(bytes);
            if (read!=-1){
                System.out.println("接收到客户端的数据："+new String(bytes,0,read));
            }
            clientSocket.getOutputStream().write("hello client ".getBytes(StandardCharsets.UTF_8));
            clientSocket.getOutputStream().flush();
        }
    }
}
