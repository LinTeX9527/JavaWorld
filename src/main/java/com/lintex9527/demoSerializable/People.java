package com.lintex9527.demoSerializable;

import java.io.Serializable;

/**
 * @author: LinTeX9527
 * @date: 2018年8月16日 上午10:29:44
 *
 * 演示Java中序列化的工作流程
 *
 * https://www.cnblogs.com/wmyskxz/p/9485251.html
 *
 */
public class People implements Serializable {

    // 序列化版本号
    private static final long serialVersionUID = -8010913300770939739L;
    private String name;
    // 如果某个属性不希望进行序列化（例如一些敏感信息），可以加上 transient 关键字
//	private transient int age;
    private int age;


    /**
     * 构造方法
     * @param name 名字
     * @param age 年龄
     */
    public People(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public void sayHello() {
        System.out.println("Hello, My name is " + name);
    }


    @Override
    public String toString() {
        return "My name is " + name + ", age = " + age;
    }


    @Override
    public boolean equals(Object obj) {
        People anPeople = (People) obj;
        return name.equals(anPeople.getName()) && age == anPeople.getAge();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}