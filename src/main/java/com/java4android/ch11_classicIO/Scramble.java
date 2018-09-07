package com.java4android.ch11_classicIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/9/6 16:49
 * @version: 0.1
 */
public class Scramble {
    public static void main(String[] args) {
        String srcpath = "temp/readme.txt";
        String dstpath = "temp/emdaer.txt";

        FileInputStream fis = null;
        FileOutputStream fos = null;
        ScrambledOutputStream scrambledOutputStream = null;

        try {
            fis = new FileInputStream(srcpath);
            fos = new FileOutputStream(dstpath);
            scrambledOutputStream = new ScrambledOutputStream(fos, makeMap());

            int b = 0;
            while (true) {
                b = fis.read();
                if (b != -1) {
                    scrambledOutputStream.write(b);
                } else { // 遇到文件结尾
                    System.out.println("遇到文件结尾");
                    break;
                }
            }

            fis.close();
            fos.close();
            scrambledOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("程序执行完毕");
    }

    private static int[] makeMap() {
        int[] map = new int[256];
        for (int i = 0; i < map.length; i++) {
            map[i] = i;
        }
        // Shuffle the map.
        Random r = new Random(1);   // 只要 seed 采用了同样的值，那么加密的方式都是一样的。
        for (int i = 0; i < map.length; i++) {
            int n = r.nextInt(map.length);
            int temp = map[i];
            map[i] = map[n];
            map[n] = temp;
        }
        return map;
    }
}
