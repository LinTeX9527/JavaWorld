package com.lintex9527.demoSerializable;

import java.io.Serializable;

/**
 * @author: lintex9527@yeah.net
 * @date: 2018/8/17 10:14
 * @version: 0.1
 */
public class Animal implements Serializable {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name = " + name + ", age = " + age;
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
