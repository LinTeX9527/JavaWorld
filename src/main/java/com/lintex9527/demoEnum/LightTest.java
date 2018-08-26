package com.lintex9527.demoEnum;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * @author : lintex9527@yeah.net
 * @date : 2018-08-26 2018/8/26
 */
public class LightTest {
    public static void main(String[] args) {

        System.out.println("演示枚举类型的遍历。。。");
        testTraversalEnum();

        System.out.println("演示EnumMap对象的使用和遍历。。。");
        testEnumMap();

        System.out.println("演示EnumSet对象的使用和遍历。。。");
        testEnumSet();
    }


    /**
     * 演示枚举类型的遍历
     */
    private static void testTraversalEnum() {
        Light[] allLight = Light.values();
        for (Light alight : allLight) {
            System.out.println("当前灯 name = " + alight.name());
            System.out.println("当前灯 ordinal = " + alight.ordinal());
            System.out.println("当前灯：" + alight);

        }
    }


    /**
     * 演示EnumMap对象，EnumMap对象的构造方法需要参数传入，默认是key的类的类型，
     * 例如这里的参数就是 Light.class
     */
    private static void testEnumMap() {
        EnumMap<Light, String> currEnumMap = new EnumMap<Light, String>(Light.class);
        currEnumMap.put(Light.RED, "红灯");
        currEnumMap.put(Light.GREEN, "绿灯");
        currEnumMap.put(Light.YELLOW, "黄灯");

        // 遍历对象
        for (Light alight : Light.values()) {
            System.out.println("key = " + alight.name() + ", value = " + currEnumMap.get(alight));
        }
    }


    /**
     * 演示EnumSet如何使用， EnumSet 是一个抽象类，获取一个类型的枚举类型内容
     */
    private static void testEnumSet() {
        EnumSet<Light> currEnumSet = EnumSet.allOf(Light.class);
        for (Light alight : currEnumSet) {
            System.out.println("EnumSet中此元素数据为 = " + alight.name());
            System.out.println("EnumSet中此元素字符串为 = " + alight.toString());
        }
    }


}
