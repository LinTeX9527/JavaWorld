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

        test_serial2();
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
        System.out.println("原始的: " + cupB);
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
            // TODO: 原来这里的反序列化直接转换对象为CupB，并没有new CupB()，所以没有调用 CupB的构造方法，
            // 但是为了重建CupB里面的父对象的元素，所以这里调用了父类无参的构造方法。
            CupB newb = (CupB) objectInputStream.readObject();
            System.out.println("反序列化得到: " + newb);

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("反序列化完成");
    }


    /**
     * 上面的测试是CupA 没有实现Serializable接口，但是子类CupB继承了；
     * 这个测试中CupC继承自CupB，但是没有明确实现Serializable接口。理论上CupC是自动实现了Serializable接口。
     *
     * TODO: 继承关系CupA -- CupB -- CupC 但是反序列化得到CupC的时候，只是调用了CupA的无参的构造方法，而CupB,CupC的
     * 任何构造方法都没有调用，说明反序列化不会使用new 来新建一个对象，只是强制转换了值，只是没有实现Serializable的部分值，就
     * 必须调用对应的那一个类的无参的构造方法。
     *
     * 运行结果如下：
     * CupA() 两个参数
       CupB() 无参数
       CupC() 一个参数
       原始的: name = noname, size = 100, price = 80, age = 10
       开始序列化
       开始反序列化
       CupA() 无参数
       反序列化得到的：name = unknown, size = 0, price = 80, age = 10
     */
    private static void test_serial2() {
        CupC cupC = new CupC(10);
        System.out.println("原始的: " + cupC);

        // 序列化
        System.out.println("开始序列化");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(SERIAL_FILE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(cupC);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 反序列化
        System.out.println("开始反序列化");
        try {
            FileInputStream fileInputStream = new FileInputStream(SERIAL_FILE);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            CupC newC = (CupC) objectInputStream.readObject();
            System.out.println("反序列化得到的：" + newC);
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        System.out.println("CupA() 无参数");
    }

    public CupA(String name, int size) {
        this.name = name;
        this.size = size;
        System.out.println("CupA() 两个参数");
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
        System.out.println("CupB() 无参数");
        price = 80;
    }

    public CupB(int price) {
        super("nameXX", 200);
        System.out.println("CupB() 一个参数");
        this.price = price;
    }

    public CupB(String name, int size, int price) {
        super(name, size);
        System.out.println("CupB() 3个参数");
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
        System.out.println("CupC() 一个参数");
    }

    @Override
    public String toString() {
        return super.toString() + ", age = " + age;
    }
}