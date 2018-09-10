package com.java4android.ch11_classicIO;

import java.io.*;
import java.util.Random;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/9/7 11:19
 * @version: 0.1
 */
public class Unscramble {
    public static void main(String[] args) {
        String srcpath = "temp/emdaer.txt";
        String dstpath = "temp/new-readme.txt";

        ScrambledInputStream sis = null;
        FileOutputStream fos = null;

        try {
            FileInputStream fis = new FileInputStream(srcpath);
            sis = new ScrambledInputStream(fis, makeMap());
            fos = new FileOutputStream(dstpath);
            int b;
            while ((b = sis.read()) != -1) {
                fos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sis != null) {
                try {
                    sis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static int[] makeMap() {
        int[] map = new int[256];
        for (int i = 0; i < map.length; i++) {
            map[i] = i;
        }
        // Shuffle map.
        // 为了看到加密和解密正确的结果，这里的seed必须和 Scramble中的相同。
        Random r = new Random(1);
        for (int i = 0; i < map.length; i++) {
            // 交换map[n] 和 map[i]
            int n = r.nextInt(map.length);
            int temp = map[i];
            map[i] = map[n];
            map[n] = temp;
        }

        int[] temp = new int[256];

        // 还原
        for (int i = 0; i < temp.length; i++) {
            temp[map[i]] = i;
        }

        return temp;
    }
}
