package com.java4android.ch11_classicIO;

import java.io.File;

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
    }
}
