package com.lintex9527.demoEnum;

/**
 * @author : lintex9527@yeah.net
 * @date : 2018-08-26 2018/8/26
 */
public class ColorTest {

    public enum Color {
        RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLOW("黄色", 4);

        // 成员变量
        private String name;
        private int index;

        Color(String name, int index) {
            this.name = name;
            this.index = index;
            System.out.println("2个参数的构造方法, index = " + index + ", name = " + name);
        }


        /**
         * 覆盖方法
         * @return
         */
        @Override
        public String toString() {
            return "索引 = " + this.index + "， 名字 = " + this.name;
        }
    }

    public static void main(String[] args) {
        System.out.println(Color.RED.toString());

        System.out.println(Color.YELLOW.toString());
    }

}
