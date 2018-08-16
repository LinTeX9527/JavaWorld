package com.lintex9527.demoSerializable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/8/16 15:14
 * @version: 0.1
 */
public class TestSerializable {

    // 序列化文件的后缀名通常为ser
    private static final String PEOPLE_PATH = "temp/people.ser";

    public static void main(String[] args) {
//        test_serial();
//        test_deserial();

//        test_multi_serial();
        test_multi_serial02();
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
            out.flush(); // 写入之后最后进行 flush() 操作。
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


    /**
     * 问题：序列化多个对象，然后再反序列化，能够读取同样多的对象，且数据没有变化吗？
     * 结果：经过测试是一样的。
     *
     * 在反序列化时遇到java.io.EOFException 参考：
     * https://blog.csdn.net/ysk_xh_521/article/details/77396696
     *
     */
    private static void test_multi_serial() {
        // 构建多个对象
        List<People> peopleList = new ArrayList<People>();
        peopleList.add(new People("LiLei", 12));
        peopleList.add(new People("Hanmeimei", 11));
        peopleList.add(new People("Xiaoming", 12));
        System.out.print(peopleList.toString());

        // 序列化
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PEOPLE_PATH);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            // 直接写入这个列表，而不是一个一个对象的写入
            objectOutputStream.writeObject(peopleList);
            objectOutputStream.flush(); // 写入之后最好调用 flush()
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("\n序列化多个对象完成");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 反序列化
        List<People> xList;
        People xPeople = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(PEOPLE_PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            xList = (List<People>) objectInputStream.readObject();
            System.out.println(xList.toString());
            objectInputStream.close();
            fileInputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n反序列化完成");
    }


    /**
     * 多个对象的序列化和反序列化
     * 方法二：一个一个对象写入到文件，最后写入一个null对象；
     * 读取的时候也要一个一个的读取，最后需要判断是否读到一个null对象标志结尾。
     */
    private static void test_multi_serial02() {
        // 构建多个对象
        List<People> peopleList = new ArrayList<People>();
        peopleList.add(new People("LiLei", 12));
        peopleList.add(new People("Hanmeimei", 11));
        peopleList.add(new People("Xiaoming", 12));
        System.out.print(peopleList.toString());

        // 序列化
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PEOPLE_PATH);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            // 将对象一个一个的写入到文件中
            for (People aPeopleList : peopleList) {
                objectOutputStream.writeObject(aPeopleList);
            }
            // 最后以一个null对象表示结束
            objectOutputStream.writeObject(null);
            objectOutputStream.flush(); // 写入之后最好调用 flush()
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("\n序列化多个对象完成");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 反序列化
        List<People> xList = new ArrayList<People>();
        People xPeople = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(PEOPLE_PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            // 和上面的写入过程对应，这里一个一个的读出来
            while ((xPeople = (People) objectInputStream.readObject()) != null){
                xList.add(xPeople);
            }
            System.out.println(xList.toString());
            objectInputStream.close();
            fileInputStream.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n反序列化完成");
    }

}
