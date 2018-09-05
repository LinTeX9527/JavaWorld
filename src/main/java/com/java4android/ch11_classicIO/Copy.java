package com.java4android.ch11_classicIO;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/9/5 13:47
 * @version: 0.1
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileInputStream 和 FileOutputStream 联用，复制文件
 */
public class Copy {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        String inputfilename = "temp/people.ser";
        String outputfilename = "temp/people-copy.ser";

        try {
            fis = new FileInputStream(inputfilename);
            fos = new FileOutputStream(outputfilename);
            int b;
            while ((b = fis.read()) != -1) {
                fos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
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
}
