package com.lintex9527.demoSerializable;

import java.io.*;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/8/16 16:40
 * @version: 0.1
 *
 * 1、如果一个类没有实现Serializable接口，但是它的基类实现了，那么个这个类也是可以序列化的；
 * 2、如果一个类实现了Serializable接口，但是它的父类没有实现，那么这个类还是可以序列化，但是序列化该子类对象，
 *    然后反序列化后输出父类定义的某变量的数值，会发现变量数值与序列化时的数值不同（一般为null或者其他默认数值），
 *    而且这个父类里面必须有一个无参的构造方法，否则反序列化的时候会报错。
 *
 *  这里3个类的继承关系如下
 *  CupA {size, name}
 *  |
 *  |
 * CupB {price}
 * |
 * |
 * CupC {age}
 */
public class TestClassSerializable {
    
    private static final String SERIAL_FILE = "temp/serial.ser";

    /**
     * 主程序入口
     * @param args
     */
    public static void main(String[] args) {

        test_serial();

    }


    /**
     * 因为CupB 实现了Srializable接口，先对CupB进行序列化和反序列化测试，打印序列化前后的成员变量，包括父对象CupA的。
     *
     * TODO: 哪怕在CupB的每个构造方法中调用父类的构造方法，指定了父类对象的值，但是反序列化依旧是未定义的值，为什么？
     * 程序运行结果如下：
     * name = Mark, size = 12, price = 6
     * 序列化成功
     * name = unknown, size = 0, price = 6
     * 反序列化完成
     */
    private static void test_serial() {
        // 初始化对象也指定了父对象中的2个成员的值
        CupB cupB = new CupB("Mark", 12, 6);
        System.out.println(cupB);
        // 序列化
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(SERIAL_FILE);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(cupB);
            out.flush();
            out.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("序列化成功");

        // 反序列化
        try {
            FileInputStream fileInputStream = new FileInputStream(SERIAL_FILE);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            CupB newb = (CupB) objectInputStream.readObject();
            System.out.println(newb);

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("反序列化完成");
    }
}


/**
 * CupA 不实现Serializable 接口，但是CupB 实现，而CupC 是继承CupB的，所以 CupC 也是实现了Serializable 接口的。
 */
class CupA {
    String name;
    int size;

    /**
     * 因为子类实现了Serializable接口，所以父类CupA必须要实现一个无参的构造方法
     * 必须要有，可以为空，否则对CupB对象反序列化会出现 java.io.InvalidClassException 。
     */
    public CupA() {
        name = "unknown";
        size = 0;
    }

    public CupA(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String toString() {
        return "name = " + name + ", size = " + size;
    }
}


class CupB extends CupA implements Serializable {

    private static final long serialVersionUID = -8751836388424111632L;

    int price;

    public CupB() {
        super("noname", 100);
        price = 0;
    }

    public CupB(int price) {
        super("nameXX", 200);
        this.price = price;
    }

    public CupB(String name, int size, int price) {
        super(name, size);
        this.price = price;
    }

    @Override
    public String toString() {
        return super.toString() + ", price = " + price;
    }
}

/**
 * 因为CupB实现了Serializable接口，所以CupC也实现了这个接口，可以序列化。
 */
class CupC extends CupB {
    int age;

    public CupC(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return super.toString() + ", age = " + age;
    }
}