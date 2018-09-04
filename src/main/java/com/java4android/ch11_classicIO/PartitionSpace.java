package com.java4android.ch11_classicIO;

import java.io.File;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/9/4 10:35
 * @version: 0.1
 *
 * 获取系统磁盘空间总容量、剩余容量、可用容量
 * 通过一个File对象即可以获得此分区的剩余容量，可用容量，总容量。
 * file.getFreeSpace()
 * file.getUsableSpace()
 * file.getTotalSpace()
 * 
 */
public class PartitionSpace {
    public static void main(String[] args) {
        testRoots();
        testPaths();
    }

    private static void testRoots() {
        File[] roots = File.listRoots();
        for (File root : roots) {
            System.out.println("----------------------------------------------------");
            System.out.println("Partition: " + root);
            System.out.println("Free space on this partition = " + root.getFreeSpace()/1024/1024 + " MB");
            System.out.println("Usable space on this partition = " + root.getUsableSpace()/1024/1024 + " MB");
            System.out.println("Total space on this partition = " + root.getTotalSpace()/1024/1024 + " MB");
        }
    }

    private static void testPaths() {
        String[] paths = new String[] {"C:\\Logs", "D:\\datas", "E:\\logs", "F:\\Innophase"};

        for (String path: paths) {
            File root = new File(path);
            System.out.println("----------------------------------------------------");
            System.out.println("Partition: " + root);
            System.out.println("Free space on this partition = " + root.getFreeSpace()/1024/1024 + " MB");
            System.out.println("Usable space on this partition = " + root.getUsableSpace()/1024/1024 + " MB");
            System.out.println("Total space on this partition = " + root.getTotalSpace()/1024/1024 + " MB");
        }
    }
}
