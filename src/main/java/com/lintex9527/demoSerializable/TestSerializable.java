package com.lintex9527.demoSerializable;

import java.io.*;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/8/16 15:14
 * @version: 0.1
 */
public class TestSerializable {

    // 序列化文件的后缀名通常为ser
    private static final String PEOPLE_PATH = "temp/people.ser";

    public static void main(String[] args) {
        test_serial();
        test_deserial();
    }


    /**
     * 把一个对象序列化
     */
    private static void test_serial() {
        // 构造一个People对象
        People people = new People("James", 12);
        System.out.println(people.toString());
        System.out.println("\n进行序列化\n");
        // 进行序列化
        try {
            // 原来运行时的pwd就是当前项目的目录
            FileOutputStream fileOutputStream = new FileOutputStream(PEOPLE_PATH);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(people);
            out.close();
            fileOutputStream.close();
            System.out.println("Serialized People in " + PEOPLE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 反序列化--读取序列化文件，重新构建一个对象
     */
    private static void test_deserial() {
        System.out.println("\n进行反序列化\n");

        People xPeople = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(PEOPLE_PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            xPeople = (People) objectInputStream.readObject(); // 读取序列化文件并重新构建一个对象
            System.out.println(xPeople.toString());

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("People class not found");
            e.printStackTrace();
        }
    }
}
