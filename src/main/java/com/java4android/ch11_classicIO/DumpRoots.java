package com.java4android.ch11_classicIO;

import java.io.File;
import java.io.IOException;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/9/3 16:45
 * @version: 0.1
 *
 *
 * File.listRoots() 类File中的静态方法，返回一个File对象数组，包含系统上文件系统根节点，和系统本身的实现有关，
 * 例如Windows返回的是： [C:\, D:\, E:\]，而Linux/Unix平台返回的是/
 */
public class DumpRoots {
    public static void main(String[] args) {
        File[] roots = File.listRoots();
        for (File root: roots) {
            System.out.println(root);
        }

        createFiles();
    }

    /**
     * 通过 file.createNewFile() 新建文件
     */
    private static void createFiles() {
        // 相对路径
        String path01 = "temp/text01.txt";

        // 绝对路径，以当前工作目录的驱动器为起始路径，如果在D:\盘，则最终的路径是 D:\\temp\\text02.txt
        String path02 = "/temp/text02.txt";

        // 绝对路径
        String path03 = "F:\\temp\\text03.txt";

        File file = new File(path01);
        try {
            if (file.createNewFile()){
                System.out.println("createNewFile()成功新建文件");
            } else {
                System.out.println(file.getName() + "已经存在");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file.exists()) {
            System.out.println("创建文件成功");
//            file.delete();
        }
    }
}
