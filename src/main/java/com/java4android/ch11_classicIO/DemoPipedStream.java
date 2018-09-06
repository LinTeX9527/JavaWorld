package com.java4android.ch11_classicIO;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/9/6 14:49
 * @version: 0.1
 *
 * PipedOutputStream 和 PipedInputStream 联合使用
 * 两者必须连接才能发送、接收，否则抛出异常。
 *
 * 可以在实例化对象的时候进行连接，也可以在实例化之后调用 connect() 方法进行连接。
 * 连接方法1：
 * PipedOutputStream pos = new PipedOutpurStream();
 * PipedInputStream pis = new PipedInputStream(pos);
 *
 * 或者
 * PipedInputStream pis = new PipedInputStream();
 * PipedOutputStream pos = new PipedOutputStream(pis);
 *
 * 或者
 * PipedOutputStream pos = new PipedOutputStream();
 * PipedInputStream pis = new PipedInputStream();
 * pos.connect(pis);
 *
 */


import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 下面这个案例演示两个线程之间分别调用管道流进行输入输出。
 */
public class DemoPipedStream {

    public static void main(String[] args) {
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream();
        try {
            pos.connect(pis);
            Runnable senderTask = new Runnable() {
                @Override
                public void run() {
                    final int LIMIT = 10;
                    for (int i = 0; i < LIMIT; i++) {
                        int number = (int) (Math.random() * 256);
                        try {
                            pos.write(number);
                            System.out.println("Sender: " + number);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // 关闭管道
                    try {
                        pos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Sender Over");
                }
            };

            Runnable receiverTask = new Runnable() {
                @Override
                public void run() {
                    int number = 0;
                    do {
                        try {
                            number = pis.read();
                            if (number == -1) {
                                System.out.println("Receiver: 读到流的末尾，停止");
                                break;
                            }
                            System.out.println("\t\tReceiver: " + number);
                        } catch (IOException e) {
                            System.out.println("Receiver: 发送者停止了发送:" + e.getMessage());
                            break;
                        }
                    } while(true);

                    // 关闭管道
                    try {
                        pis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            Thread sendTh = new Thread(senderTask);
            Thread recvTh = new Thread(receiverTask);

            // 开始两个新线程
            System.out.println("启动两个子线程");
            sendTh.start();
            recvTh.start();

            // 等待线程结束
            sendTh.join();
            recvTh.join();

            // 清理资源
            // 经过测试，多次关闭管道并不会抛出异常
            pis.close();
            pos.close();
            System.out.println("主线程结束");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
