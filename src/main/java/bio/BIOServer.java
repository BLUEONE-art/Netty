package bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 1.使用 BIO 模型编写一个服务器端，监听 6666 端口，当有客户端连接时，启动一个线程与之通讯
 * 2.要求使用线程池机制改善，可以连接多个客户端
 * 3.服务器端可以接收客户端发送的数据(telnet 方式即可)
 */
public class BIOServer {
    public static void main(String[] args) throws Exception{
        // 线程池机制
        // 思路
        // 1.创建一个线程池
        // 2.有客户端连接时，启动一个线程与之通讯(单独写一个方法)

        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        // 创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了");

        while (true) {

            // 监听，等待客户端连接
            System.out.println("等待连接......");
            final Socket socket = serverSocket.accept();
            System.out.println("连接到了一个客户端");

            // 启动一个线程与之通讯(单独写一个方法)
            newCachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    // 可以和客户端通讯
                    handler(socket);
                }
            });
        }
    }

    // 编写一个 handler 方法，和客户端通信
    public static void handler(Socket socket) {

        try {
            System.out.println("线程信息 id = " + Thread.currentThread().getId() + "，名字 = " + Thread.currentThread().getName());
            byte[] bytes = new byte[1024]; // 接收数据
            // 通过 socket 获取输入流
            InputStream inputStream = socket.getInputStream();

            // 循环的读取客户端发送的数据
            while (true) {

                System.out.println("线程信息 id = " + Thread.currentThread().getId() + "，名字 = " + Thread.currentThread().getName());

                System.out.println("read......");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read)); // 输出客户端发送的数据
                } else {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和 client 的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
