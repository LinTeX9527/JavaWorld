package com.java4android.ch11_classicIO;

import java.io.*;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/9/4 13:10
 * @version: 0.1
 */

/**
 * 假设编写一个文本编辑器，用户对于文本的操作不是每一次都保存到文件上，而是只有用户确定要保存的时候才把修改保存
 * 到文件上。其他的时候用户的修改都保存到临时文件上，当应用退出的时候删除这个临时文件。
 */
public class DemoTempFile {
    public static void main(String[] args) {
        // 获得系统的临时文件的存放目录
        System.out.println(System.getProperty("java.io.tmpdir"));

        try {
            // 创建临时文件，指定文件的前缀以及后缀，实际创建的时候系统指定补充中间的文件名称，做到唯一。
            File tempFile = File.createTempFile("temp", "txt");
            System.out.println("实际创建的临时文件：" + tempFile);

            // 这里执行读写操作
            System.out.println("这里修改文本内容，执行结束删除临时文件");

            // 应用执行完毕删除临时文件，这个操作不是立刻执行的，而是在虚拟机关闭的时候才删除这个文件。
            tempFile.deleteOnExit();
            System.out.println("临时文件是否存在: " + tempFile.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
